package QKART_TESTNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {

    @Override
    public void onStart(ITestContext context){
        System.out.println("onStart method started"); 
    }

    @Override
    public void onFinish(ITestContext context){
        System.out.println("onFinish method started");
    }

    @Override
    public void onTestStart(ITestResult result){
        QKART_Tests.takeScreenshot(QKART_Tests.driver, "TestStart", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        QKART_Tests.takeScreenshot(QKART_Tests.driver, "TestSuccess", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        QKART_Tests.takeScreenshot(QKART_Tests.driver, "TestFailure", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result){
        QKART_Tests.takeScreenshot(QKART_Tests.driver, "TestSkipped", result.getName());
    }

}