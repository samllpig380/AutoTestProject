import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.kail.testenvir.MyDriver;
import com.kail.util.log.LogUtil;
import com.kail.robot.test.BusinessLogic;

@SuppressWarnings("all")
public class Login {
	
	private LogUtil log;
	private BusinessLogic bl;

	
	public Login(){
		 log=new LogUtil("webgoat_Login");
		 bl = new BusinessLogic();
	}
	
	//登陆
	public void login(MyDriver driver,Map<String,List<String>> tdata ){
		
		log.log("开始登陆系统");
		
		for(int i=0;i<tdata.size();i++){
			
			driver.GoToUrl(tdata.get(String.valueOf(i+1)).get(0));
			
		}
		try {
			Thread.sleep(3000);
			bl.playBack("./Transaction/wgLogin.xml");
			driver.FindElementByName("start").click();
			
			
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.log(e.getMessage());
		}
		
		log.log("成功登陆系统");
	}
//退出
	public void logout(MyDriver driver,Map<String,List<String>> tdata){
	
		driver.Exit();
	}	

}
