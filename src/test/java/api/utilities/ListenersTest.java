package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import extentreport.ExtentReportGenerate;

public class ListenersTest implements ITestListener
{
	private static ExtentReportsUtility extentReport;
	
	@Override
	public void onTestStart(ITestResult result)
	{
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentReport.logTestpassed(result.getMethod().getMethodName()+"is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		extentReport.logTestFailed(result.getMethod().getMethodName()+"is failed");
		extentReport.logTestFailedWithException(result.getThrowable());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	
	@Override
	public void onStart(ITestContext context) {
		extentReport=ExtentReportsUtility.getInstance();
		extentReport.startExtentReport();
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extentReport.endReport();
	}
	
}
