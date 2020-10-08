package com.kail.testplatform;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;













import com.kail.iter.ITestLogic;
import com.kail.robot.constant.Constant;
import com.kail.robot.test.BusinessLogic;
import com.kail.robotdriver.RobotDriver;
import com.kail.testbean.TestCase;
import com.kail.testbean.TestSet;
import com.kail.testdriver.TestCaseDriver;
import com.kail.testdriver.TestSetDriver;

public class TestLogic implements ITestLogic {
    
	BusinessLogic bl=new BusinessLogic();
	@Override
	public String testInfo() {
		// TODO 自动生成的方法存根
		TestSetDriver tsd=new TestSetDriver("./TestSet/testSet.xls");
		tsd.loadTestSet();
		List<TestSet> ts=tsd.getTestSet();
		StringBuffer tssb=new StringBuffer();
		tssb.append("测试计划: ");
		for(int i=0;i<ts.size();i++){
			tssb.append(ts.get(i).getName()+" ");
			
		}
		
		tssb.append("\n");
		tssb.append("测试用例: "+"\n");
		
		for(int i=0;i<ts.size();i++){
			TestCaseDriver tcd=new TestCaseDriver(ts.get(i));
			tcd.loadTestCase();
			List<TestCase> ls=tcd.getTestCase();
			
			for(int j=0;j<ls.size();j++){
				
				tssb.append(ls.get(j).getDescription()+"  ");
				if(j/3==0){
					tssb.append("\n");
				}
			}
		}
		
		String tsb=tssb.toString();
		return tsb;
		
	}

	@Override
	public void startUp() {
		// TODO 自动生成的方法存根
		RobotDriver rd=new RobotDriver();
		rd.robotDriver();
	}

	@Override
	public void record() {
		// TODO 自动生成的方法存根
		String strValue=JOptionPane.showInputDialog("请设置脚本名称（英文）");
		Constant.actionData.setName(strValue);
		bl.record();
		
	}

	@Override
	public void play() {
		// TODO 自动生成的方法存根

		JFileChooser jfc=new JFileChooser("./Transaction");  
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.setBounds(50, 50, 50, 50);
        jfc.showDialog(new JLabel(), "选择"); 
		bl.playBack(jfc.getSelectedFile());
       
		
	}

	@Override
	public void report() {
		// TODO 自动生成的方法存根
		try {
			java.awt.Desktop.getDesktop().open(new File("Report"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		/*TestLogic tl=new TestLogic();
		System.out.println(tl.testInfo());*/
		
	}

}
