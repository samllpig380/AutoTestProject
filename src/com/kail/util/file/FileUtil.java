package com.kail.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * <p>功能描述: [文件工具类]</p>
 * @author  yhao
 * @date:2014-11-4/下午5:35:07
 * @version 
 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
public class FileUtil {
	/**
	 * 
	 * <p>功能描述:[删除文件]</p>
	 * @param srcFile
	 * @author:yhao
	 * @date:2014-4-25/上午11:31:03
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public  static void deleteFile(String srcFile) {
		File file = new File(srcFile);
		file.delete();
	}
	
	/**
	 * 
	 * <p>功能描述:[检查文件是否存在]</p>
	 * @param pathName
	 * @param fileName
	 * @return
	 * @author:yhao
	 * @date:2014-4-25/上午11:31:42
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public  static boolean checkFile(String pathName,
			String fileName) {
		int j = 0;
		boolean sign = false;
		File file = new File(pathName);
		File[] files = file.listFiles();
		if (files != null) {
			String[] s = new String[files.length];
			for (int i = 0; i < files.length; ++i) {
				s[i] = files[i].getName();
				if (s[i].equals(fileName)) {
					break;
				}
				++j;
			}
			if (j == files.length)
				sign = false;
			else {
				sign = true;
			}
		}
		return sign;
	}

	/**
	 * 
	 * <p>功能描述:[检查文件是否存在]</p>
	 * @param filePath
	 * @return
	 * @author:yhao
	 * @date:2014-4-25/上午11:32:00
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public  static boolean checkFile(String filePath) {
		if (filePath != null && !"".equals(filePath)) {
			File file = new File(filePath);
			return file.exists();
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <p>功能描述:[拷贝文件]</p>
	 * @param srcPath
	 * @param targetPath
	 * @author:yhao
	 * @date:2014-4-25/上午11:32:13
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public  static void copyFile(String srcPath, String targetPath) {
		File f1 = new File(srcPath);
		File f2 = new File(targetPath);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(f1);
			out = new FileOutputStream(f2);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

		} catch (Exception e) {
			// logger.error();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e2) {
			}
		}
	}

	/**
	 * 
	 * <p>功能描述:[拷贝文件]</p>
	 * @param srcFile
	 * @param targetFile
	 * @return
	 * @author:yhao
	 * @date:2014-4-25/上午11:32:30
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public  static boolean copyFileReturnBoolean(String srcFile,
			String targetFile) {
		boolean flag = false;
		File f1 = new File(srcFile);
		File f2 = new File(targetFile);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(f1);
			out = new FileOutputStream(f2);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			flag = true;
		} catch (Exception e) {
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e2) {
			}
		}
		return flag;
	}
	/**
	 * 
	 * <p>功能描述:[读取txt文件]</p>
	 * @param filePath
	 * @param charSet
	 * @return
	 * @author:yhao
	 * @date:2014-4-25/上午11:32:43
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static List<String> readTxt(String filePath, Charset charSet) {
		BufferedReader br = null;
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(filePath)), charSet));
			String content = "";
			while ((content = br.readLine()) != null) {
				list.add(content);
			}
		} catch (Exception e) {
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e2) {
			}
		}
		return list;
	}

	/**
	 * 
	 * <p>功能描述:[读取txt文件]</p>
	 * @param filePath
	 * @return
	 * @author:yhao
	 * @date:2014-4-25/上午11:33:01
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static List<String> readTxt(String filePath) {
		Charset charSet = Charset.forName("GBK");
		return readTxt(filePath, charSet);
	}

	/**
	 * 
	 * <p>功能描述:[写文件]</p>
	 * @param filePath
	 * @param contentList
	 * @param charset
	 * @author:yhao
	 * @date:2014-4-25/上午11:33:25
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static void writeTxt(String filePath, List<String> contentList,
			Charset charset) {
		if (contentList == null || contentList.size() < 1) {
			return;
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(filePath)), charset));
			for (int i = 0; i < contentList.size(); i++) {
				StringBuffer content = new StringBuffer().append(contentList
						.get(i));
				if (i != contentList.size() - 1) {
					String osName = System.getProperties().getProperty(
							"os.name");
					if (osName.startsWith("Windows")) {
						content.append("\r\n");
					} else {
						content.append("\n");
					}
				}
				bw.write(content.toString());
			}
			bw.flush();
		} catch (Exception e) {
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 
	 * <p>功能描述:[写文件]</p>
	 * @param filePath
	 * @param contentList
	 * @author:yhao
	 * @date:2014-4-25/上午11:33:38
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static void writeTxt(String filePath, List<String> contentList) {
		Charset charset = Charset.forName("GBK");
		writeTxt(filePath, contentList, charset);
	}

	/**
	 * 
	 * <p>功能描述:[检查目录是否存在]</p>
	 * @param directoryPath
	 * @return
	 * @author:yhao
	 * @date:2014-4-25/上午11:33:52
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static boolean checkDirectoryIsExist(String directoryPath) {
		boolean flag = false;
		File file = new File(directoryPath);
		flag = file.isDirectory();
		return flag;
	}

	/**
	 * 
	 * <p>功能描述:[创建目录]</p>
	 * @param directoryPath
	 * @return
	 * @author:yhao
	 * @date:2014-4-25/上午11:34:10
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static boolean createDirectory(String directoryPath) {
		boolean isExist = checkDirectoryIsExist(directoryPath);
		boolean flag = false;
		File file = new File(directoryPath);
		if (!isExist) {
			flag = file.mkdirs();
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * <p>功能描述:[追加文件]</p>
	 * @param fileName
	 * @param content
	 * @author:yhao
	 * @date:2014-4-25/上午11:34:36
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static void appentContent(String fileName, String content) {
		RandomAccessFile randomFile = null;
		try {
			randomFile = new RandomAccessFile(fileName, "rw");
			randomFile.seek(randomFile.length());
			randomFile.write((content + "\n").getBytes("UTF-8"));
		} catch (IOException e) {
		} finally {
			try {
				randomFile.close();
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 得到所有的文件和文件夹
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<File> listFile(String fileName)throws IOException{
		File file=new File(fileName);
		if(!file.exists()){
			throw new IOException("文件未找到："+fileName);
		}
		
		return Arrays.asList(file.listFiles());
		
		
	}
	
	/**
	 * 查找指定文件，返回匹配的文件
	 * 注意：如果没有匹配的文件，返回最后一个文件，看日志
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	
	public static File getFilePath(String dirName,String fileName)throws IOException{
		
		   
			List<File> files=listFile(dirName);
			File file=null;
			if(files.size()>0){
				
				for(int i=0;i<files.size();i++){
					file=files.get(i);
					//System.out.println(file.getName());
					if(file.getName().equals(fileName)){
						return file;
					}else{
						
						if(checkDirectoryIsExist(file.getCanonicalPath())){
							//System.out.println(file.getCanonicalPath());
							file=getFilePath(file.getCanonicalPath(),fileName);
							return file;
						}
					}
				}
			}
	
		
		
			return file;

		
	}
	
	/*public static void main(String[] args){
		
		try {
			File file=FileUtil.getFilePath("./TestCases", "监测管理.xls");
			
			System.out.println("lys="+file.getName());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}*/
}
