package com.warrior.eem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.warrior.eem.exception.EemException;

/**
 * 上传文件工具初版
 * 
 * @author seangan
 *
 */
public class FileUtil {

	/**
	 * 保存合约
	 */
	public static String saveFile(String baseDir, String fileName, InputStream input) {
		FileOutputStream fis = null;
		try {
			String fn = fileName.split("\\.")[0] + "-" + System.currentTimeMillis() + "." + fileName.split("\\.")[1];
			File f = new File(baseDir + fn);
			if (!f.getParentFile().exists()) {
				if (!f.getParentFile().mkdirs()) {
					throw new EemException("创建目录失败");
				}
			}
			if (!f.exists()) {
				if (!f.createNewFile()) {
					throw new EemException("创建文件（" + fileName + "）失败");
				}
			}
			fis = new FileOutputStream(f);
			byte[] bs = new byte[4096];
			int readLen = -1;
			while ((readLen = input.read(bs)) != -1) {
				fis.write(bs, 0, (int) readLen);
				fis.flush();
			}
			return fn;
		} catch (Exception e) {
			if (e.getCause() instanceof EemException) {
				throw new EemException(e.getMessage());
			} else {
				throw new EemException("上传文件（" + fileName + "" + "）失败");
			}
		} finally {
			try {
				if (null != fis) {
					fis.close();
				}
			} catch (IOException e) {
			}
		}

	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		} else {
			throw new EemException("无效的文件路径");
		}
	}
	
	/**
	 * 检查文件是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean isExists(String filePath) {
		return new File(filePath).exists();
	}

	/**
	 * 下载文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static void downloadFile(String filePath, OutputStream out) {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new EemException("文件不存在");
		}
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(f);
			byte[] buf = new byte[4096];
			int len = -1;
			while ((len = fin.read(buf)) != -1) {
				out.write(buf, 0, len);
				out.flush();
			}
			out.close();
		} catch (IOException e) {
			throw new EemException("读取文件流失败");
		} finally {
			try {
				if (fin != null) {
					fin.close();
				}
			} catch (IOException e) {
				throw new EemException("关闭文件流失败");
			}
		}
	}
}
