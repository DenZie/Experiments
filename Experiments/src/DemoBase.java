
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class DemoBase {

	WebDriver driver;
	
	@BeforeClass
	@Parameters({"browser"})
	public void setup(String browser) {
		driver = makeDriver(browser);
	}
	
	public WebDriver makeDriver(String browser) {
		WebDriver driver = null;
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if(browser.equals("ie")) {
			driver = new InternetExplorerDriver();
		} else if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	@AfterClass
	public void stop() {
		driver.quit();
	}
}
