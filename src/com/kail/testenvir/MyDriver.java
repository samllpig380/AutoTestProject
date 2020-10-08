package com.kail.testenvir;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.kail.util.log.LogUtil;



public class MyDriver{
	
	private WebDriver wd=null;
	private Browsers browser=Browsers.IE;
	private LogUtil log;

	
	public MyDriver(Browsers theBrowser){
		this.browser=theBrowser;
		this.wd=InitWebDriver();
		log=new LogUtil("MyDriver");
	}
	public WebDriver getCurWd(){
		return this.wd;
	}
	
	@SuppressWarnings("deprecation")
	private WebDriver InitWebDriver(){
		WebDriver theDriver=null;
		DesiredCapabilities ieCapabilities;
		switch(browser){
		case IE:
			System.setProperty("webdriver.ie.driver", ""
					+ "./Driver/IEDriverServer.exe");
			ieCapabilities = DesiredCapabilities
				     .internetExplorer();
			ieCapabilities
				     .setCapability(
				       InternetExplorerDriver
				       .INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				       true);
			theDriver=new InternetExplorerDriver(ieCapabilities);
			break;
		case Chrome:
			System.setProperty("webdriver.chrome.driver", ""
					+"./Driver/chromedriver.exe");		
			theDriver=new ChromeDriver();
			break;
		case FireFox:
			System.setProperty("webdriver.gecko.driver", ""
					+"./Driver/geckodriver.exe");
			theDriver=new FirefoxDriver();
			break;
		default:
			System.setProperty("webdriver.ie.driver", ""
					+ "./Driver/IEDriverServer.exe");
			ieCapabilities = DesiredCapabilities
				     .internetExplorer();
			ieCapabilities.setCapability(
				       InternetExplorerDriver
				       .INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				       true);
			theDriver=new InternetExplorerDriver(ieCapabilities);
			
		}
		return theDriver;
		
	}
	//ָ����ʽ�ȴ�ʱ��
	public void ImplicitlyWait(long seconds){
		wd.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		
	}
	
	/**
	 * 判断元素是否存在
	 * @author lys
	 * @param locator
	 * @return
	 */
	public boolean isElementExit(By locator){
    	
    	boolean flag=false;
    	
    	try{
    		
    		WebElement element= wd.findElement(locator);
    		flag=null!=element;
    		
    	}catch(NoSuchElementException e){
    		
    		log.log("查找的元素不存在");
    	}
    	
    	return flag;
    }
	//�ȴ�ҳ�������ɣ���id���������ض�����ֵ�ж�
	public void WaitForPage(final String idOrClassOrName){
		WebDriverWait wait=new WebDriverWait(wd,5);
		wait.until(new ExpectedCondition<Boolean>() {  

		    public Boolean apply(WebDriver driver) {  
		        Boolean result = false;  
		        try {  
		            driver.findElement(By.id(idOrClassOrName));  
		            result = true;  
		        } catch(Exception e){         
		        }  
		        return result;  
		    }  
		}); 
		
	}
	/*
	 * navigate():����ģ���������������һЩ��ť�Ĺ���
	 */
	//ת��ָ��ҳ��
	public void GoToUrl(String url){
		wd.navigate().to(url);
		
	}
	//ˢ����תҳ��
	public void Refresh()
    {
        wd.navigate().refresh();
    }
	//����תҳ�淵��
	public void Back()
    {
		wd.navigate().back();;
    }

    public void Forward()
    {
    	wd.navigate().forward();
    
    }
    //�õ���ǰ��url
    public String GetCurrUrl(){
    	return wd.getCurrentUrl();
    }
    //�õ�ҳ�����
    public String GetTitle(){
    	return wd.getTitle();
    }
    //�õ����е�cookies
    public Dictionary<String, String> GetAllCookies(){
    	
    	Dictionary<String, String> cookies=new Hashtable<String,String>();
    	Set<Cookie> allCookies=wd.manage().getCookies();
    	for(Cookie cookie:allCookies){
    		cookies.put(cookie.getName(), cookie.getValue());
    	}
    	return cookies;
    }
	//ɾ��ǰҳ���cookies
    public void DeleteAllCookies(){
    	wd.manage().deleteAllCookies();
    }
    //��ת��ָ������
    //title:���ڱ��⣬exactMatch:true-��ȷƥ�䣬false-ģ��ƥ��
    public void GoToWindow(String title, Boolean exactMatch){
    	//String currentWindow=wd.getWindowHandle();
    	Set<String> wHandles=wd.getWindowHandles();
    	//�õ����ڵĵ����
    	Iterator<String> it=wHandles.iterator();
    	while(it.hasNext()){
    		if(exactMatch){
    			wd.switchTo().window(it.next());
    			if(wd.getTitle().toLowerCase()==title.toLowerCase()){
    				return;
    			}
    			
    		}else{
    			wd.switchTo().window(it.next());
    			if(wd.getTitle().contains(title)){
    				return;
    			}
    		}
    	}
    	
    }
    //��ת��ָ����iframe
    public void GoToFrame(String name){
    	WebElement theFrame = null;
    	for(WebElement frame:wd.findElements(By.tagName("iframe"))){
    		if(frame.getAttribute("name")
    				.toLowerCase()==name.toLowerCase()){
    			theFrame=frame;
    			break;
    		}
    	}
    	if(theFrame!=null){
    		wd.switchTo().frame(theFrame);
    		log.log("进入iframe");
    	}
    }
    //��ת����֪��iframe��ͨ��frame������
    public void GoToFrame(WebElement frame){
    	wd.switchTo().frame(frame);
    }
    //�����ǰframe
    public void GoToDefault(){
    	wd.switchTo().defaultContent();
    }
    //�õ�Alert��Text��Ϣ
    public String GetAlertText(){
    	String text=null;
    	Alert alert=null;
    	alert=wd.switchTo().alert();
    	if(alert!=null){
    		text=alert.getText();
    	}
    
    		return text;

    }
    
    //ȷ��alert�Ի���
    public void AlertAccept(){
    	Alert alert=null;
    	alert=wd.switchTo().alert();
    	if(alert!=null){
    		alert.accept();
    	}
    }
    //ȡ��alert�Ի���
    public void AlertDismiss(){
    	Alert alert=null;
    	alert=wd.switchTo().alert();
    	if(alert!=null){
    		alert.dismiss();;
    	}
    	
    }
    //�ƶ���ֱ���������ײ�
    public void PageVscrollToBottom(){
    	String js="document.documentElement.scrollTop=10000";
    	((JavascriptExecutor)wd).executeScript(js);
    }
    //�ƶ�ˮƽ������
    public void PageHscrollToRight(){
    	String js="document.documentElement.scrollLeft=10000";
    	((JavascriptExecutor)wd).executeScript(js);
    }
    //����ָ��Ԫ�صĹ�����
    public void ElementScrollToBottom(WebElement element){
    	String id=element.getAttribute("id");
    	String name=element.getAttribute("name");
    	String js=null;
    	if(id!=null){
    		js = "document.getElementById('" + id.trim() + "').scrollTop=10000";
    		
    	}else if(name!=null){
    		js = "document.getElementById('" + name.trim() + "').scrollTop=10000";
    	}
    	
    	if(js!=null){
    		((JavascriptExecutor)wd).executeScript(js);
    	}else{
    		((JavascriptExecutor)wd).executeScript("alert(\"��ǰ����������ǿյ�!\")");
    	}
    
    	
    }
    //��ͼ
    public void TakeScreenshot(String savePath){
    	try{
    		File screenShotFile = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        	FileUtils.copyFile(screenShotFile, new File(savePath));
    	}catch(Exception e){
    		e.printStackTrace();
    		
    	}	
    }
    //ͨ��ID����Ԫ��
    public WebElement FindElementById(String id){
    	WebElement theElement=null;
    	theElement=wd.findElement(By.id(id));
    	return theElement;
    	
    }
    //ͨ��name����Ԫ��
    public WebElement FindElementByName(String name){
    	WebElement theElement=null;
    	theElement=wd.findElement(By.name(name));
    	return theElement;
    	
    }
    //ͨ��classname����Ԫ��
    public WebElement FindElementByClassName(String clsName){
    	WebElement theElement=null;
    	theElement=wd.findElement(By.className(clsName));
    	return theElement;
    }
    //ͨ��cssname����Ԫ��
    public WebElement FindElementByCss(String cssName){
    	WebElement theElement=null;
    	theElement=wd.findElement(By.cssSelector(cssName));
    	return theElement;
    }
    //ͨ��XPath����Ԫ��
    public WebElement FindElementByXpath(String xpath){
    	WebElement theElement=null;
    	theElement=wd.findElement(By.xpath(xpath));
    	return theElement;
    	
    }
    //ͨ��LinkText����Ԫ��
    public WebElement FindElementByLinkText(String text){
    	WebElement theElement=null;
    	theElement=wd.findElement(By.linkText(text));
    	return theElement;
    }
    //�ҵ�linktext���󼯺�
    public List<WebElement> FindElementsByLinkText(String text){
    	List<WebElement> theElements=null;
    	theElements=wd.findElements(By.linkText(text));
    	return theElements;
    }
    //�ҵ�PartiaLinkText���󼯺�
    public List<WebElement> FindElementsByPartiaLinkText(String text){
    	List<WebElement> theElements=null;
    	theElements=wd.findElements(By.partialLinkText(text));
    	return theElements;
    }
    //�ҵ�classname���󼯺�
    public List<WebElement> FindElementsByClassName(String clsName)
    {
        List<WebElement> theElement = null;
        theElement = wd.findElements(By.className(clsName));
        return theElement;
    }
    public List<WebElement> FindElementsByTagName(String tagName)
    {
        List<WebElement> theElement = null;
        theElement = wd.findElements(By.tagName(tagName));
        return theElement;
    }
    public List<WebElement> FindElementsByCssSelector(String css)
    {
        List<WebElement> theElement = null;
        theElement = wd.findElements(By.cssSelector(css));
        return theElement;
    }

    public List<WebElement> FindElementsByXPathName(String xpath)
    {
        List<WebElement> theElement = null;
        theElement = wd.findElements(By.xpath(xpath));
        return theElement;
    }
    public void ExecuteJS(String js){
    	((JavascriptExecutor)wd).executeScript(js);
    }
    public void Exit(){
    	wd.quit();
    }
    
}
