package com.kail.testenvir;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.kail.util.log.LogUtil;
/**
 * 解析测试环境配置文件
 * @author kail
 *
 */
public class ParseEnvirConf {
	
	private final String filePath = "./TestEnvir/TestEnvir.properties";
	private LogUtil log;
	
	public ParseEnvirConf(){
		log = new LogUtil("TestEnvir");
	}
	
	public String GetTestEnvir(){
		
		Properties ppt = new Properties();
		
		try {
			InputStream in  = new BufferedInputStream(new FileInputStream(filePath));
			ppt.load(in);
			String value = ppt.getProperty("environment");
			log.log("测试环境: "+ value);
			return value;
			
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.log("加载测试环境失败,文件未找到!");
			return null;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.log("加载测试环境失败,IO读写异常!");
			return null;
		}
		
	}
	
	public void SetTestEnvir(String value){
		
		Properties ppt = new Properties();
		try {
			InputStream in = new FileInputStream(filePath);
			ppt.load(in);
			OutputStream out = new FileOutputStream(filePath);
			ppt.setProperty("environment", value);
			ppt.store(out,"");
			out.close();
			log.log("设置测试环境:"+value);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.log("加载测试环境失败,文件未找到!");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.log("加载测试环境失败,IO读写异常!");
		}
		
	}
	
  public static void main(String[] args){
	  
	  ParseEnvirConf pec = new ParseEnvirConf();
	  pec.SetTestEnvir("FireFox");
	  System.out.print(pec.GetTestEnvir());
  }
}

