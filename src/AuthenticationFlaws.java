import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.kail.testenvir.MyDriver;
import com.kail.util.log.LogUtil;
import com.kail.robot.test.BusinessLogic;

@SuppressWarnings("all")
public class AuthenticationFlaws {
	
	private LogUtil log;
	private BusinessLogic bl;
	
	public AuthenticationFlaws(){
		log = new LogUtil("AuthenticationFlaws");
		bl = new BusinessLogic();
	}
	
	public void multiLevelLogin(MyDriver driver,Map<String,List<String>> tdata){
		
		log.log("multiLevelLogin测试用例开始执行");
		
		driver.FindElementByXpath("/html/body/div[1]/div[5]/a").click();
		driver.ImplicitlyWait(3);
		driver.FindElementByXpath("/html/body/div[1]/div[26]/table/tbody/tr[5]/td/a").click();
		
		for(int i = 0;i<tdata.size();i++){
			String name = tdata.get(String.valueOf(i+1)).get(0);
			String pwd = tdata.get(String.valueOf(i+1)).get(1);
			driver.FindElementByName("user").sendKeys(name);
			driver.FindElementByName("pass").sendKeys(pwd);
			driver.FindElementByName("Submit").click();
		}
		
	}

}
