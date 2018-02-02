package com.warrior.eem.dao.support;

/**
 * 
 * @author seangan
 *
 */
public class SqlRequest implements Condition {
	
	private static final long serialVersionUID = -1885442528704830870L;
	
	/**
	 * 需要查询的属性列 当为null时，查询实体所有属性
	 */
	private MultiSelector select;
	
	/**
	 * 连接
	 */
	private Joiner joiner;
	
	/**
	 * 查询条件， 可以为null
	 */
	private Condition cdt;
	
	/**
	 * 排序参数 可以为null
	 */
	private Order order;
	
	/**
	 * 如果page不为null，则翻页参数默认页数为1  页面显示20条
	 */
	private Page page;
	
	/**
	 * 分组信息 可以为null
	 */
	private GroupBy groupBy;
	
	public SqlRequest() {
		
	}
	
	public SqlRequest(Condition cdt) {
		this.cdt = cdt;
	}
	
	public SqlRequest(MultiSelector select, Condition cdt) {
		this.select = select;
		this.cdt = cdt;
		this.page = new Page();
	}
	
	public SqlRequest(MultiSelector select, Condition cdt, Order order) {
		this.select = select;
		this.cdt = cdt;
		this.order = order;
		this.page = new Page();
	}

	public SqlRequest(Condition cdt, Page page) {
		this.cdt = cdt;
		this.page = page;
	}
	
	public SqlRequest(Condition cdt, Order order, Page page) {
		this.cdt = cdt;
		this.order = order;
		this.page = page;
	}

	public SqlRequest(MultiSelector select, Condition cdt, Order order, Page page) {
		this.select = select;
		this.cdt = cdt;
		this.order = order;
		this.page = page;
	}

	public SqlRequest(MultiSelector select, Condition cdt, Order order, Page page, GroupBy groupBy) {
		this.select = select;
		this.cdt = cdt;
		this.order = order;
		this.page = page;
		this.groupBy = groupBy;
	}

	public GroupBy getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(GroupBy groupBy) {
		this.groupBy = groupBy;
	}

	public MultiSelector getSelect() {
		return select;
	}

	public Condition getCdt() {
		return cdt;
	}

	public Order getOrder() {
		return order;
	}

	public Page getPage() {
		return page;
	}

	public Joiner getJoiner() {
		return joiner;
	}

	public void setJoiner(Joiner joiner) {
		this.joiner = joiner;
	}

	public void setSelect(MultiSelector select) {
		this.select = select;
	}

	public void setCdt(Condition cdt) {
		this.cdt = cdt;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toSqlString() {
		// 待完善
		return null;
	}

}
