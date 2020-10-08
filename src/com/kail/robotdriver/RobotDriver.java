package com.kail.robotdriver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kail.reportdriver.ReportDriver;
import com.kail.taskdriver.TaskDriver;
import com.kail.testbean.Report;
import com.kail.testbean.Table1;
import com.kail.testbean.TestCase;
import com.kail.testbean.TestSet;
import com.kail.testdriver.TestCaseDriver;
import com.kail.testdriver.TestSetDriver;
import com.kail.testenvir.MyDriver;
import com.kail.testenvir.TestEnvir;
import com.kail.util.file.DateUtil;

public class RobotDriver {

	private TestSetDriver tsd;
	private TestCaseDriver tcd;
	private TaskDriver td;
	private Report re;
	private Table1 t1;
	private ReportDriver rd;
	//private final MyDriver driver=TestEnvir.getInstance().getIEDriver();
	private final MyDriver driver=TestEnvir.getInstance().getDriver();
	
	
	public RobotDriver(){
		
		tsd=new TestSetDriver("./TestSet/testSet.xls");
		
	}
	
	public void robotDriver(){
		
		tsd.loadTestSet();
		List<TestSet> ts=tsd.getTestSet();
		List<String> ln=new ArrayList<String>();//存成功运行的模块
		List<String> lm=new ArrayList<String>();//存所有的待运行的模块
		int control=0;
		long sTime=0L;
		double execT=0.0;
		for(int i=0;i<ts.size();i++){
		
			re=new Report();
			re.settStartTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			sTime=System.currentTimeMillis();
			re.setProjectName(ts.get(i).getName());//得到项目名称
			re.setVersion(ts.get(i).getVersion());//得到版本信息
			re.settPlace(ts.get(i).gettPlace());//得到测试地点
			re.setPerformer(ts.get(i).getPerformer());//得到执行者
			re.setInfoCpu(ts.get(i).getPcCpu());
			re.setInfoDisk(ts.get(i).getPcDisk());
			re.setInfoMem(ts.get(i).getPcMem());
			re.setInfoOffice(ts.get(i).getPcOffice());
			re.setInfoRobot(ts.get(i).getPcRobot());
			re.setInfoSystem(ts.get(i).getPcOS());
			re.setModifyVersion(ts.get(i).getModifyVersion());
			re.setWriteDate(DateUtil.getDate("yyyy-MM-dd"));
			tcd=new TestCaseDriver(ts.get(i));
			tcd.loadTestCase();
			List<TestCase> ls=tcd.getTestCase();
			re.setExecTc(ls.size()+"");
			for(int k=0;k<ls.size();k++){
				
				lm.add(ls.get(k).getBizName());
			}
				for(int j=0;j<ls.size();j++){
					
					
					td=new TaskDriver(ls.get(j));
					try {
						
						td.LoadClass(driver);
						ln.add(ls.get(j).getBizName());
						
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
					
						
						
						re.setFailTc((ls.size()-ln.size())+"");
						re.setPassTc(ln.size()+"");
						re.setExecTc(ls.size()+"");
						execT=(System.currentTimeMillis()-sTime)/1000/60.00;
						DecimalFormat df=new DecimalFormat("0.00");
						re.setExecTime(df.format(execT));
						re.settEndTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
						createReport(re, this.setTable(this.removeDup(ln), this.fiterTable(ln),
								this.fiterTable(lm)));
						control+=1;
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						e.printStackTrace();
						
					}
					
				}
				if(control>0){
					System.exit(0);
				}
			re.setFailTc("0");
			re.setPassTc(re.getExecTc());
			execT=(System.currentTimeMillis()-sTime)/1000/60.00;
			DecimalFormat df=new DecimalFormat("0.00");
			re.setExecTime(df.format(execT));
			re.settEndTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
			createReport(re, this.setTable(this.removeDup(ln), this.fiterTable(ln),
					this.fiterTable(lm)));
			
		}
	}
	
	public void  createReport(Report re,List<Table1> lt1){
		
		rd=new ReportDriver(re,lt1);
		rd.createDoc();
		
		
	}
	//返回当前测试用例中的模块出现的次数，以数组方式存储
	public int[] fiterTable(List<String> ls){
		
		
		Object[] obj1=ls.toArray();
		Set<Object> s = new HashSet<Object>();//HashSet用来去掉重复
		for(Object o:obj1){
			s.add(o);
		}  //现在的集合s中无重复的包含obj1中的所有元素
		
		Object[] obj2 = s.toArray();//把集合s中的元素存入数组obj2中
		int[] n = new int[obj2.length];//这个数组用来存放每一个元素出现的次数

		for(int i=0;i<obj2.length;i++){
			int cout = 0;
			for(int j=0;j<obj1.length;j++){
				if(obj2[i].equals(obj1[j]))cout++;
                                                    //用obj2中的元素跟obj1中的每一个比较，如果相同cout自增
			}
			n[i] = cout;//每一个元素出现的次数存入数组n
                                       //数组n的下标i跟数组obj2的下标是一一对应的。
			
		}
		
		return n;
		
		
	}
	//获得不重复的模块名称
	public List<String> removeDup(List<String> ls){
		
		List<String> mn=new ArrayList<String>();
		Object[] obj1=ls.toArray();
		
		Set<Object> s = new HashSet<Object>();//HashSet用来去掉重复
		for(Object o:obj1){
			s.add(o);
		}  //现在的集合s中无重复的包含obj1中的所有元素
		Object[] obj2 = s.toArray();//把集合s中的元素存入数组obj2中
		for(int i=0;i<obj2.length;i++){
			
			mn.add(obj2[i].toString());
		}
		return mn;
	}
	//返回测试报告中需要的测试充分性列表
	public List<Table1> setTable(List<String> mn,int[] pass,int[] total){
		
		List<Table1> ltb=new ArrayList<Table1>();
		
		if(pass.length==total.length){
			
			for(int i=0;i<mn.size();i++){
				
				t1=new Table1();
				t1.setFuncName(mn.get(i));
				t1.setFuncPass(pass[i]+"");
				t1.setFuncFail((total[i]-pass[i])+"");
				t1.setFuncTotal(total[i]+"");
				ltb.add(t1);
			}
		}else{
			
			for(int i=0;i<mn.size();i++){
				
				t1=new Table1();
				t1.setFuncName(mn.get(i));
				t1.setFuncPass(pass[i]+"");
				t1.setFuncFail((total[i]-pass[i])+"");
				t1.setFuncTotal(total[i]+"");
				ltb.add(t1);
			}
			
		}
		return ltb;
		
	}
	public static void main(String[] args){
		
		RobotDriver rd=new RobotDriver();
		rd.robotDriver();
		
	}
}
