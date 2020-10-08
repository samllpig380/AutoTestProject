package com.kail.testdriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kail.testbean.TestSet;
import com.kail.util.file.ExcelUtil;
import com.kail.util.file.poi.PoiExcelReadHandler;
import com.kail.util.log.LogUtil;

public class TestSetDriver {
	


	private String path;
	private TestSet ts;
	private PoiExcelReadHandler pex;
	private LogUtil log;
	private List<TestSet> lt;
	
	public TestSetDriver(String filePath){
		
		this.path=filePath;
		
	    log=new LogUtil("TestSet");
	    
	    lt=new ArrayList<TestSet>();
	    
	}
	
	public void loadTestSet(){
		
		log.log("加载TestSet: "+this.path);
		
		pex=ExcelUtil.getPoiExcelReadHandler(path);
		
		List<List<List<String>>> ls=null;
		
		try {
			
			ls=pex.readExcel();
			
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		if(ls==null){
			log.log("TestSetDriver--未获取到TestSet文件中的内容");
			return;
		}
		
		int sheetCounts= ls.size();
		
		for(int i=0;i<sheetCounts;i++){
			
			List<List<String>> rows=ls.get(i);
			
			for(int j=0;j<rows.size();j++){
				
				if(j==0||j==1){
					continue;
				}
				
				List<String> cols=rows.get(j);
				ts=new TestSet();
				for(int k=0;k<cols.size();k++){
					
					if(cols.get(k).equals("x")){
						break;
					}else{
						
						if(k==1){
							
							ts.setName(cols.get(k));
							
						}else if(k==2){
							
							ts.setTable(cols.get(k));
							
						}else if(k==3){
							
							ts.setSheet(cols.get(k));
							
						}else if(k==4){
							
							ts.setPrority(cols.get(k));
							
						}else if(k==5){
							
							ts.setDescription(cols.get(k));
						}else if(k==6){
							ts.setVersion(cols.get(k));
						}else if(k==7){
							ts.setPerformer(cols.get(k));
						}else if(k==8){
							ts.settPlace(cols.get(k));
						}else if(k==9){
							ts.setPcCpu(cols.get(k));
						}else if(k==10){
							ts.setPcDisk(cols.get(k));
						}else if(k==11){
							ts.setPcMem(cols.get(k));
						}else if(k==12){
							ts.setPcOffice(cols.get(k));
						}else if(k==13){
							ts.setPcRobot(cols.get(k));
						}else if(k==14){
							ts.setPcOS(cols.get(k));
						}else if(k==15){
							ts.setModifyVersion(cols.get(k));
						}
					}
					
					
					
				}
				
				if(ts.getDescription()!=null){
					
					if(ts.getName().equals("")){
						
					}else{
						lt.add(ts);
					}
				
				}

				
			}
			
			
		}
		
		
	}
	
	public List<TestSet> getTestSet() {

		return lt;
	}
	
	/*public static void main(String[] args){
		
		//boolean file=FileUtil.checkFile("./TestSet/testSet.xls");
		//System.out.println(file);
		TestSetDriver tsd=new TestSetDriver("./TestSet/testSet.xls");
		tsd.loadTestSet();
		TestSet tset;
		List<TestSet> ts=tsd.getTestSet();
		for(int i=0;i<ts.size();i++){
			
			tset=ts.get(i);
			if(tset.getName().equals("")){
				System.out.println("lsy");
			}
			System.out.println(tset.getVersion());
		}
		System.out.println(ts.size());
	}
	*/
}
