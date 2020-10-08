package com.kail.reportdriver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kail.testbean.Report;
import com.kail.testbean.Table1;
import com.kail.util.file.DateUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SuppressWarnings("all")
public class ReportDriver {
	private Configuration configuration = null;
    private Report rp;
    private List table=new ArrayList<Table1>();
    
	public ReportDriver(Report trp,List<Table1> tb) {
		 //创建配置实例
		configuration = new Configuration();
		//设置编码
		configuration.setDefaultEncoding("UTF-8");
		this.rp=trp;
		this.table=tb;
	}

	public void createDoc() {
		// 要填入模本的数据文件
		Map dataMap = new HashMap();
		Date myDate=new Date();
		SimpleDateFormat sDateformat = new SimpleDateFormat("yyyy-MM-DD HH-mm-ss");
		getData(dataMap);
		// 设置模本装置方法和路径
		// 这里我们的模板是放在src.model包下面
		configuration.setClassForTemplateLoading(this.getClass(),
				"/Template");
		Template t = null;
		try {
			t = configuration.getTemplate("T_Report.xml");	// 装载模板
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 输出文档路径及名称
		File outFile = new File("Report/Report"
				+DateUtil.getDate("yyyy-MM-dd HH-mm-ss").replaceAll(" ", "-")
				+".doc");
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile),"utf-8"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 注意dataMap里存放的数据Key值要与模板中的参数相对应
	 * @param dataMap
	 */
	private void getData(Map dataMap) {
		dataMap.put("report", this.rp);
		dataMap.put("table1", this.table);
	}
	
	public static void main(String[] args){
		/*Report re=new Report();
		List<Table1> table=new ArrayList<Table1>();
		
		re.setProjectName("无线电监测平台");
		re.setVersion("1.0");
		re.setModifyVersion("A");
		re.setExecTc("100");
		re.setExecTime("15m");
		re.setFailTc("10");
		re.setInfoCpu("i5");
		re.setInfoDisk("1T");
		re.setInfoMem("8G");
		re.setInfoOffice("2003");
		re.setInfoRobot("RLI1.0");
		re.setInfoSystem("windows7");
		re.setPassTc("90");
		re.setPerformer("pig");
		re.settEndTime("16:38");
		re.settPlace("西乡");
		re.settStartTime("16:00");
		re.setWriteDate("2016-7-17");
		
		for(int i=0;i<5;i++){
			
			Table1 t=new Table1();
			t.setFuncFail("12");
			t.setFuncName("模块"+(i+1));
			t.setFuncPass("50");
			t.setFuncTotal("62");
			table.add(t);
			
		}
		ReportDriver free=new ReportDriver(re,table);
		free.createDoc();*/
	}
}
