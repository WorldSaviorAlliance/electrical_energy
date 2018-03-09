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
	 * 读取流保存文件
	 * @param baseDir 
	 * @param fileName
	 * @param input
	 * @param allowedFileTypes 限制允许的文件类型，可以为null，如果为null则为不限制文件类型
	 * @return
	 */
	public static String saveFile(String baseDir, String fileName, InputStream input, String[] allowedFileTypes) {
		FileOutputStream fis = null;
		try {
			if(!fileName.contains(".")) {
				throw new EemException("无效的文件");
			}
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
			checkFileHeader(input, allowedFileTypes);
			byte[] bs = new byte[4096];
			int readLen = -1;
			while ((readLen = input.read(bs)) != -1) {
				fis.write(bs, 0, (int) readLen);
				fis.flush();
			}
			return fn;
		} catch (Exception e) {
			if (EemException.class.isInstance(e) || EemException.class.isInstance(e.getCause())) {
				throw (EemException)e;
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
	 * 检查文件头 判断文件是否有效
	 * @param in
	 * @param allowedFileTypes
	 * @throws IOException
	 */
	private static void checkFileHeader(InputStream in, String[] allowedFileTypes) throws IOException {
		byte[] buf = new byte[10];
		in.read(buf, 0, buf.length);
		String fileType = bytesToHexString(buf);
		boolean isFind = false;
		if(allowedFileTypes != null) {
			for(String allowedType : allowedFileTypes) {
				if(allowedType.equalsIgnoreCase(fileType)) {
					isFind = true;
					break;
				}
			}
			if(!isFind) {
				throw new EemException("不允许的文件类型");
			}
		}
	}
	
	private static String bytesToHexString(byte[] src) {  
        StringBuilder builder = new StringBuilder();  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        String hv;  
        for (int i = 0; i < src.length; i++) {  
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写  
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();  
            if (hv.length() < 2) {  
                builder.append(0);  
            }  
            builder.append(hv);  
        }  
        //System.out.println(builder.toString());  
        return builder.toString();  
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
