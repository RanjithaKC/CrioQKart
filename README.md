# QKART-Testing
The application under test is QKart, an e-commerse platform.

### About the Project
The TestNG script is designed to evaluate the functionality of a dummy e-commerce website. The script performs various tests, checking functionality of features and integated functionalities.

### During this project
- Automated testing with selenium.
- Utilized implicit and explicit waits effectively to avoid synchronization issues.
- Improved the test with XPath.
- Debugged failing test cases and issues with log statements.
- Migrated tests to testng automaton framework.

### Scope of Work
- Modularized existing test code for readability and to avoid duplication.
- Fixed various bugs present in the existing code base for register and login pages.
- Used IDE debugger with breakpoints to find and resolve issues faster.

### Skills Used
Java, Selenium, Locators, HTML, Developer Tools, XPath, TestNG.

### Features 
- Registration and Login functionality testing.
- Search box functionality testing.
- Cart functionality testing.
- Checkout functionality testing.
- Contact us form functionality testing.
- Advertisement functionality testing.
- Privacy policy and About us functionality testing.

### Setup
This Project requires the following software and dependencies :
- Java JDK 11.x.x : Ensure you have JDK version 11 and above installed.
- Gradle 8.x.x : MAke sure you have gradle version 8 or above installed.
- WebDriverManager : To manage WebDriverManager binaries automatically. Add WebDriverManager dependency to your project.
- Selenium : This project relies on Selenium for automated testing. Ensure that you have the latest version of selenium webdriver added to your dependencies.
- TestNG Framework : This project uses TestNG for execution and reporting. Add TestNG dependency to your project.

### Example Gradle Dependency Configuration :
```
gradle
dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
    implementation group: 'io.github.bonigarcia', name: 'webdrivermanager', version: '4.4.3'
    implementation group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '4.0.0-alpha-1'
    testImplementation group: 'org.testng', name: 'testng', version: '7.6.0'
}
```

>[!Note]
> Some of the dependencies may not work in future. Update to their latest version.

### Instructions
Clone or Download the code to your local machine. Pull the code stubs/unpack the file. Open your terminal or shell. Navigate to the project folder in your terminal.

> For Windows : Execute this command in the terminal (command prompt or powershell).
```
gradle build
```
> For Mac/Linux : Execute this command in the bash terminal
```
./gradlew run
```
Wait for sometime you will have the screenshots in the folder named screenshots.

### Thanks for taking the time to view our project! We hope that you found it interesting and informative.
