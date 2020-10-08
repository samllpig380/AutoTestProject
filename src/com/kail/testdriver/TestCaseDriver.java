package com.kail.testdriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kail.testbean.TestCase;
import com.kail.testbean.TestSet;
import com.kail.util.file.ExcelUtil;
import com.kail.util.file.FileUtil;
import com.kail.util.file.poi.PoiExcel2003ReadHandler;
import com.kail.util.file.poi.PoiExcelReadHandler;
import com.kail.util.log.LogUtil;

@SuppressWarnings("all")
public class TestCaseDriver {
		
	private TestSet ts;
	private TestCase tc;
	private PoiExcelReadHandler pex;
	private LogUtil log;
	private List<TestCase> lt;
	private List<String> tdata;
	
	public TestCaseDriver(TestSet ts){
		
		this.ts=ts;
		log=new LogUtil("TestCase");
		lt=new ArrayList<TestCase>();
		tdata=new ArrayList<String>();
		
	}
	
	public void loadTestCase(){
		
		log.log("加载TestCase: "+ts.getTable());
		
		File file;
		
		try {
				
				file = FileUtil.getFilePath("./TestCases", ts.getTable());
				pex=ExcelUtil.getPoiExcelReadHandler(file.getCanonicalPath());
				List<List<List<String>>> ls=pex.readExcel();
				if(ls==null){
					log.log("TestCaseDriver--未获取到TestCase文件中的内容");
					return;
				}
				
				
				
					
					List<List<String>> rows=ls.get(0);
					
					for(int j=0;j<rows.size();j++){
						
						if(j==0||j==1){
							continue;
						}
						
						List<String> cols=rows.get(j);
						tc=new TestCase();
						for(int k=0;k<cols.size();k++){
							
							if(cols.get(k).equals("x")){
								break;
							}else{
								
								if(k==1){
									
									tc.setBizName(cols.get(k));
									
								}else if(k==2){
									
									tc.setTaskName(cols.get(k));
									
								}else if(k==3){
									
									if(cols.get(k).equals("")||cols.get(k)==null){
										
										tc.setBizDataTable("");
									}else{
										tc.setBizDataTable(cols.get(k));
										Map<String,List<String>> t=this.getTestDataEx(pex, 
												tc.getTaskName(), tc.getBizDataTable());
										if(t==null){
											
											log.log("测试数据为空");
											return;
										}
										tc.setTdata(t);
									}
									
									
								}else if(k==4){
									
									tc.setFilter(cols.get(k));
									
								}else if(k==5){
									
									tc.setDescription(cols.get(k));
								}
							}
							
							
							
						}
						
						if(tc.getTaskName()!=null){
							
							if(tc.getBizName().equals("")){
								
							}else{
								lt.add(tc);
							}
						
						}

						
					}
					
					
			
				
				
		} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
		}
			
			
					
		}
	
	public Map<String,List<String>> getTestDataEx(
			PoiExcelReadHandler pex,
			String taskName,
			String bizDataTable
			){
		
		PoiExcel2003ReadHandler poi;
		List<String> td = new ArrayList<String>();
		Map<String,List<String>> tdMap=new HashMap<String,List<String>>();
		int rowCount=0;
		int count=0;
		if(taskName.equals("")&&pex==null){
			
			log.log("Excel对象不存在，测试任务（taskName）为空");
			
			return null;
			
		}else{
			
			poi=(PoiExcel2003ReadHandler)pex;
			try {
				
				List<String> sheetName=poi.getSheetList(poi.getHSSFWorkbook());
				
				for(int i=0;i<sheetName.size();i++){
					
					if(sheetName.get(i).equals(bizDataTable)){
						
						List<List<String>> rows = poi.readExcel(i);
						
							for(int j=0;j<rows.size();j++){
								
								List<String> cols=rows.get(j);
								
									for(int k=0;k<cols.size();k++){
									
										if(cols.get(k).equals(taskName)){
										
											rowCount=j;
										
										}
									}
									
								}
							
							
							
							for(int j=rowCount;j<rows.size();j++){
								
								List<String> cols=rows.get(j);
									for(int k=0;k<cols.size();k++){
									
										if(cols.get(k).equals(taskName)
												||cols.get(k).equals("序号")){
									
											break;
									
										}else{

											if(cols.get(k).equals("a")){
											
												return tdMap;
											}
											if(k==0){
												continue;
											}
											//log.log(cols.get(k));
											this.tdata.add(cols.get(k));
										
										
									}
								}
									if(this.tdata.size()==0){
										continue;
									}
									td=this.tdata;
									
									tdMap.put(String.valueOf(++count), td);
									
									this.tdata=new ArrayList<String>();
							}
						}
				}
				
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				log.log("excel文件未找到");
				e.printStackTrace();
				
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				log.log(e, "IO读取异常");
				e.printStackTrace();
				
			}
			
			
		}
		return tdMap;
	}
	
	public List<String> getTestData(
			PoiExcelReadHandler pex,
			String taskName,
			String bizDataTable){
		
		PoiExcel2003ReadHandler poi;
		List<String> td = new ArrayList<String>();
		Map<String,List<String>> tdMap=new HashMap<String,List<String>>();
		
		if(taskName.equals("")&&pex==null){
			
			log.log("Excel对象不存在，测试任务（taskName）为空");
			
			return null;
			
		}else{
			
			poi=(PoiExcel2003ReadHandler)pex;
			try {
				
				List<String> sheetName=poi.getSheetList(poi.getHSSFWorkbook());
				
				for(int i=0;i<sheetName.size();i++){
					
					if(sheetName.get(i).equals(bizDataTable)){
						
						List<List<String>> rows = poi.readExcel(i);
						
							for(int j=0;j<rows.size();j++){
								
								List<String> cols=rows.get(j);
								
									for(int k=0;k<cols.size();k++){
									
										if(cols.get(k).equals(taskName)
												||cols.get(k).equals("序号")){
										
											break;
										
										}else{

											if(cols.get(k).equals("a")){
												
												return tdata;
											}
											if(k==0){
												continue;
											}
											//log.log(cols.get(k));
											this.tdata.add(cols.get(k));
											
											
										}
									}
									if(this.tdata.size()==0){
										continue;
									}
									td=this.tdata;
									
									tdMap.put(String.valueOf(j+1), td);
									this.tdata=new ArrayList<String>();
									System.out.println(String.valueOf(j+1));
									System.out.println(tdMap.get(String.valueOf(j+1)));
									
								}
						}
				}
				
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				log.log("excel文件未找到");
				e.printStackTrace();
				
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				log.log(e, "IO读取异常");
				e.printStackTrace();
				
			}
			
			if(this.tdata.size()>0){
				
				int size = tdata.size();
				
				for(int i=0;i<size;i++){
					
					if(taskName.equals(tdata.get(i))){
						
						if(size-1 == i){
							
							break;
						}
						td.add(tdata.get(i+1));
					}
				}
				return td;
			}else{
				
				log.log("测试数据tdata为空");
				return null;
			}
		}
		
	}
	
	public List<TestCase> getTestCase() {

		return lt;
	}
			
	public static void main(String[] args){
	
	
	/*TestSetDriver tsd=new TestSetDriver("./TestSet/testSet.xls");
	tsd.loadTestSet();
	TestSet tset;
	List<TestSet> ts=tsd.getTestSet();
	TestCaseDriver tcd=new TestCaseDriver(ts.get(0));
	tcd.loadTestCase();
	System.out.println(tcd.getTestCase().get(0).getBizName());*/
		/*TestSetDriver tsd=new TestSetDriver("./TestSet/testSet.xls");
		tsd.loadTestSet();
		TestSet tset;
		List<TestSet> ts=tsd.getTestSet();
		for(int i=0;i<ts.size();i++){
			
			TestCaseDriver tcd=new TestCaseDriver(ts.get(i));
			tcd.loadTestCase();
			List<TestCase> ls=tcd.getTestCase();
			System.out.println(ls.get(0).getTdata().get("1"));
			
		}*/
		
		
		
	
	
}	

}
