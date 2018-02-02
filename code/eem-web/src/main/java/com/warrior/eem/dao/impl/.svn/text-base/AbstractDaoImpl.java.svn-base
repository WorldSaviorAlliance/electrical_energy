package com.warrior.eem.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;


import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.support.Condition;
import com.warrior.eem.dao.support.GroupBy;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.MultiSelector;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Sql_Operator;
import com.warrior.eem.exception.EemException;

/**
 * 抽象持久层
 * 
 * @author seangan
 *
 */
public abstract class AbstractDaoImpl<T> implements IDao<T> {

	@PersistenceContext
	private EntityManager em;

	private CriteriaBuilder cb;

	@PostConstruct
	private void initProps() {
		cb = em.getCriteriaBuilder();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public T getEntity(Serializable pk) {
		return em.find(getEntityClass(), pk);
	}

	@Override
	public T getReference(Serializable pk) {
		return em.getReference(getEntityClass(), pk);
	}

	@Override
	public void createDo(T obj) {
		em.persist(obj);
	}

	@Override
	public void updateDo(T obj) {
		em.merge(obj);
	}

	@Override
	public void deleteDo(Serializable pk) {
		T t = getEntity(pk);
		if (t == null) {
			throw new EemException("无效的id：" + pk);
		}
		em.remove(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> listDos(SqlRequest req) {
		CriteriaQuery<?> cq = cb.createQuery(getEntityClass());
		boolean isTuple = false;
		if(req.getSelect() != null && req.getSelect().getPropNames().size() > 0) {
			cq = cb.createTupleQuery();
			isTuple = true;
		}
		final Root<T> root = cq.from(getEntityClass());
		Page page = new Page();
		if (req != null) {
			MultiSelector ms = req.getSelect();
			Condition cdt = req.getCdt();
			Order order = req.getOrder();
			page = req.getPage() == null ? page : req.getPage();
			if (ms != null) {
				List<Selection<?>> ls = new ArrayList<Selection<?>>();
				for (String propName : ms.getPropNames()) {
					ls.add(root.get(propName));
				}
				if (ls.size() > 0) {
					cq.multiselect(ls);
				}
			}
			Predicate predicate = parseCondition(cdt, root);
			if (predicate != null) {
				cq.where(predicate);
			}
			if (order != null) {
				List<javax.persistence.criteria.Order> orders = new ArrayList<javax.persistence.criteria.Order>();
				int i = 0;
				for (String orderName : order.getPropNames()) {
					orders.add(order.getTypes().get(i).equals(Order_Type.DESC) ? cb.desc(root.get(orderName))
							: cb.asc(root.get(orderName)));
					i++;
				}
				if (orders.size() > 0) {
					cq.orderBy(orders);
				}
			}
		}
		GroupBy gb = req.getGroupBy();
		if(gb != null && gb.getGroupPropNames().size() > 0) {
			List<Expression<?>> groupNames = new ArrayList<Expression<?>>();
			for(String gbName : gb.getGroupPropNames()) {
				groupNames.add(root.get(gbName));
			}
			if(groupNames.size() > 0) {
				cq.groupBy(groupNames);
			}
		}
//		cq.having(restrictions);
		TypedQuery<?> tq = em.createQuery(cq);
		if (page != null) {
			tq.setFirstResult((page.getStartPageNum() - 1) * page.getPerPageNum());
			tq.setMaxResults(page.getPerPageNum());
		}
		List<?> resDb = tq.getResultList();
		if(isTuple) {
			List<Object[]> res = new LinkedList<Object[]>();
			for(Tuple t : (List<Tuple>)resDb) {
				res.add(t.toArray());
			}
			resDb = res;
		}
		return resDb;
	}

	/**
	 * 解析条件
	 * 
	 * @param cdt
	 * @param root
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Predicate parseCondition(Condition cdt, Root<T> root) {
		if (cdt != null) {
			if (cdt instanceof LogicalCondition) {
				LogicalCondition lcdt = (LogicalCondition) cdt;
				if (lcdt.getOperator().equals(Sql_Operator.AND)) {
					return cb.and(parseCondition(lcdt.getLc(), root), parseCondition(lcdt.getRc(), root));
				} else if (lcdt.getOperator().equals(Sql_Operator.OR)) {
					return cb.or(parseCondition(lcdt.getLc(), root), parseCondition(lcdt.getRc(), root));
				} else {
					throw new EemException("无效的sql条件:" + lcdt.getOperator().getOptName());
				}
			} else if (cdt instanceof SimpleCondition) {
				SimpleCondition scdt = (SimpleCondition) cdt;
				try {
					Method m = cb.getClass().getDeclaredMethod(scdt.getOperator().getDbMethodName(),
							scdt.getOperator().getMethodParamClasses());
					m.setAccessible(true);
					try {
						if(Sql_Operator.IN.equals(scdt.getOperator()) || Sql_Operator.NOT_IN.equals(scdt.getOperator())) {
							In in = cb.in(root.get(scdt.getPropName()));
							for(Object obj : (Object[])scdt.getPropVal()) {
								in = in.value(obj);
							}
							return Sql_Operator.IN.equals(scdt.getOperator()) ? in : cb.not(in);
						} else if(Sql_Operator.BETWEEN.equals(scdt.getOperator())) {
							return (Predicate) m.invoke(cb,
									new Object[] { root.get(scdt.getPropName()), ((Object[])scdt.getPropVal())[0], ((Object[])scdt.getPropVal())[1] });
						} else {
							return (Predicate) m.invoke(cb,
									new Object[] { root.get(scdt.getPropName()), scdt.getPropVal() });
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						throw new EemException("执行方法(" + scdt.getOperator().getDbMethodName() + ")错误，请检查下参数！");
					}
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public long countDos(Condition cdt) {
		final CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		final Root<T> root = cq.from(getEntityClass());
		cq.where(parseCondition(cdt, root));
		TypedQuery<T> res = em.createQuery(cq);
		res.getResultList();
		return 0;
	}

	/**
	 * 获取具体业务实体class
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();

}
