package com.kail.testplatform;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Test {
	public static void main(String[] args)
	{
		try {
			//BeautyEyeLNFHelper.launchBeautyEyeLNF();
			//UIManager.put("RootPane.setupButtonVisible",false);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String name=("RLI--V1.0");
		//让界面呈现跟系统一致的外观
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		TestPlatform note=new TestPlatform(name);		
		note.init();
		note.setVisible(true);
	}
}
