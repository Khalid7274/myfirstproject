package Reporting;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllListener.class})
public class Tests extends BaseClass{
		public WebDriver driver;
		
		
		
		@BeforeClass
		public void setup()
		{
			BaseClass bs= new BaseClass();
			bs.initialize_driver();
			driver = getDriver();
			//driver = getDriver();
//			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://demo.nopcommerce.com/");
//			driver.manage().window().maximize();
		}
		
		@Test(priority=1)
		public void logoPresence()
		{
			boolean disstatus=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
			Assert.assertEquals(disstatus, true);
		}
		
		@Test(priority=2)
		public void loginTest()
		{
			driver.findElement(By.xpath("//a[normalize-space()='Log in']")).click();
			driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("naimt.khalid@gmail.com");
			driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Kabul@123");
			driver.findElement(By.xpath("//input[@value='Log in']")).click();
			
			Assert.assertEquals(driver.getTitle(), "nopCommerce demo store2");
		}
		
		@Test(priority=3)
		public void registrationTest()
		{
			throw new SkipException("Skipping this test");
		}
		
		@AfterClass
		public void tearDown()
		{
			driver.quit();
		}

}
