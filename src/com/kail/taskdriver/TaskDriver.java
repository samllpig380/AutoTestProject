package com.kail.taskdriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

import com.kail.testbean.TestCase;
import com.kail.testenvir.MyDriver;
import com.kail.testenvir.TestEnvir;
import com.kail.util.file.FileUtil;
import com.kail.util.log.LogUtil;

@SuppressWarnings("all")
public class TaskDriver {
	
	
	private LogUtil log;
	private TestCase tc;
	
	public TaskDriver(TestCase tc){
		
		this.tc=tc;
		log=new LogUtil("TaskDriver");
	}
	
	public void LoadClass(MyDriver driver) throws Exception{
		
		log.log("加载测试用例"+tc.getDescription());
		
		/*动态加载指定类*/
		File file=FileUtil.getFilePath("./Script", tc.getBizName()+".class");//类路径(包文件上一层)
		
		//URL url=file.toURI().toURL();
		//System.out.println(url.toString());
		//new URL("file:/"+"D:/javawork/AutoProject/Script");
		
		ClassLoader loader=new URLClassLoader(new URL[]{new URL("file:/"+file.getParent()+"/")});//创建类加载器
		Class<?> cls=loader.loadClass(tc.getBizName());//加载指定类，注意一定要带上类的包名
		Object obj=cls.newInstance();//初始化一个实例
		
		if(tc.getBizDataTable().equals("")){
			Method method=cls.getMethod(tc.getTaskName(),MyDriver.class);//方法名和对应的参数类型
			method.invoke(obj, driver);
			
		}else{
			Method method=cls.getMethod(tc.getTaskName(),MyDriver.class,Map.class);//方法名和对应的参数类型
			method.invoke(obj,driver, tc.getTdata());
		}
		
		
		
		
		//System.out.println(String.valueOf(o));//输出"chenleixing"
		
		/*动态加载指定jar包调用其中某个类的方法
		file=new File("D:/test/commons-lang3.jar");//jar包的路径
		url=file.toURI().toURL();
		loader=new URLClassLoader(new URL[]{url});//创建类加载器
		cls=loader.loadClass("org.apache.commons.lang3.StringUtils");//加载指定类，注意一定要带上类的包名
		method=cls.getMethod("center",String.class,int.class,String.class);//方法名和对应的各个参数的类型
		o=method.invoke(null,"chen",Integer.valueOf(10),"0");//调用得到的上边的方法method(静态方法，第一个参数可以为null)
		System.out.println(String.valueOf(o));//输出"000chen000","chen"字符串两边各加3个"0"字符串
*/	}
	
	public static void main(String[] args){
		File file;
		try {
			file = FileUtil.getFilePath("./Script", "Login.class");
			//URL url=file.toURI().toURL();
			System.out.println(file.getParent());
			//new URL("file:/"+"D:/javawork/AutoProject/Script/aaa")
			ClassLoader loader=new URLClassLoader(new URL[]{new URL("file:/"+file.getParent()+"/")});
			Class<?> cls=loader.loadClass("Login");
			Object obj=cls.newInstance();//初始化一个实例
			
			
			//Object o=method.invoke(obj,"chen","leixing");//调用得到的上边的方法method
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
}
