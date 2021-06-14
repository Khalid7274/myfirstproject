package Reporting;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllListener implements ITestListener{
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}
	
	/*
	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	*/
	
	@Attachment(value = "Screenshot", type="image/png")
		public byte[] saveScreenshotOnFailure(WebDriver driver) {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
	
	
	
	@Attachment(value = "{0}", type="text/plain")
	
		public static String saveTextLog(String message) {
		return message;
	}

	public void onStart(ITestContext context) {
		System.out.println("I am onStart method" + context.getName());
		context.setAttribute("WebDriver", BaseClass.getDriver());
	}

	public void onFinish(ITestContext context) {
		System.out.println("I am in onFinish method" + context.getName());
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("I am onTestStart method" + getTestMethodName(result) + " start");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("I am onTestSucess method" + getTestMethodName(result) + " succeed");
	}
	/*
	public void onTestFailure(ITestResult result) {
		System.out.println("I am onTestFailure method" + getTestMethodName(result) + " faild");
		
		Object testClass = result.getInstance();
		WebDriver driver = BaseClass.getDriver();
		//Allure screenshot and SaveTestLog
		if(driver instanceof WebDriver) {
			System.out.println("Screenshot Captured for Test Case: " + getTestMethodName(result) + " faild");
		}
		saveTextLog(getTestMethodName(result) + " faild and screenshot taken!");
	}
*/
	public void onTestFailure(ITestResult result) {
		System.out.println("I am onTestFailure method" + getTestMethodName(result) + " faild");
		saveScreenshotOnFailure(BaseClass.getDriver());
		saveTextLog(result.getMethod().getConstructorOrMethod().getName());
	}
	public void onTestSkipped(ITestResult result) {
		System.out.println("I am onTestSkipped method" + getTestMethodName(result) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test faild but it's on sucess percentage" + getTestMethodName(result) + "success ratio");
	}	
	
}
