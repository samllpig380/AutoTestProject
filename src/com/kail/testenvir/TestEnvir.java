package com.kail.testenvir;
import com.kail.testenvir.ParseEnvirConf;


public class TestEnvir {
	
	private MyDriver driver;
	private static TestEnvir instance;
	private ParseEnvirConf pec;
	private TestEnvir(){
		
	}
	
	public static synchronized TestEnvir getInstance(){
		
		if(instance==null){
			instance=new TestEnvir();
		}
		return instance;
	}
	
	public MyDriver getDriver(){
		
		pec = new ParseEnvirConf();
		String envir = pec.GetTestEnvir();
		
		if(envir.equals("IE")){
			driver = this.getIEDriver();
		}else if(envir.equals("FireFox")){
			driver = this.getFireFoxDriver();
		}else if(envir.equals("Chrome")){
			driver = this.getChromeDriver();
		}else{
			
			driver = this.getIEDriver();
		}
		
		return driver;
	}
	
	public MyDriver getIEDriver(){
		
		driver=new MyDriver(Browsers.IE);
		
		return driver;
	}
	
	public MyDriver getFireFoxDriver(){
		
		driver=new MyDriver(Browsers.FireFox);
		
		return driver;
	}
	
	public MyDriver getChromeDriver(){
		
		driver=new MyDriver(Browsers.Chrome);
		
		return driver;
	}
	

}
