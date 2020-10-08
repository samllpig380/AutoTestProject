package com.kail.iter;

public interface ITestLogic {
	
	String testInfo();//显示测试信息
	void startUp();//启动自动化测试
	void record();//录制无法识别的控件，或CS版程序
	void play();//测试录制的内容
	void report();//打开测试报告所在的文件夹
	

}
