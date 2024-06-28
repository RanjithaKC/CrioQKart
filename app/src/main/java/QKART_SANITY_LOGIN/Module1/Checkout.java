package QKART_SANITY_LOGIN.Module1;

import java.util.List;
import org.apache.commons.lang3.SystemUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address text
             * box and click on the "ADD" button to save the address
             */
            WebElement addNewAddressButton = driver.findElement(By.id("add-new-btn"));
            addNewAddressButton.click();
           // Thread.sleep(3000);
            WebElement textArea = driver.findElement(
                    By.xpath("(//textarea[contains(@class,'MuiOutlinedInput-input')])[1]"));
            textArea.sendKeys(addresString);
            Thread.sleep(2000);
            WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
            addButton.click();
          //  List<WebElement> addedAddress = driver.findElements(By.xpath("//div[@class='address-item not-selected MuiBox-root css-0']/div/p"));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            List<WebElement> addedAddress = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='address-item not-selected MuiBox-root css-0']/div/p")));
            for(WebElement added : addedAddress){
                String add = added.getText();
                System.out.println("added address = "+add);
                wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='address-item not-selected MuiBox-root css-0']/div/p"), addresString));
                break;
            }
           // Thread.sleep(5000);
            // WebElement selectAddress =
            // driver.findElement(By.xpath("//span[@class='css-hyxlzm']"));
            // selectAddress.click();

            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching text,
             * addressToSelect and click on it
             */
            List<WebElement> addressElements = driver.findElements(By
                    .xpath("//div[@class='address-item not-selected MuiBox-root css-0']/div[1]/p"));
            for (WebElement address : addressElements) {
                System.out.println("address is " + address.getText());
                if (address.getText().equals(addressToSelect)) {
                    address.click();
                   // Thread.sleep(6000);
                    System.out.println("selected address is " + address.getText());
                    return true;
                }
            }
            System.out.println("Unable to find the given address");
            return false;
        } catch (Exception e) {
            System.out.println(
                    "Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            WebElement placeOrderButton =
                    driver.findElement(By.xpath("//button[text()='PLACE ORDER']"));
            placeOrderButton.click();
           // Thread.sleep(4000);
           
            return false;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement balanceErrorMsg =
                    driver.findElement(By.id("notistack-snackbar"));
                    wait.until(ExpectedConditions.visibilityOf(balanceErrorMsg));
            String insuficientBalanceErrorMsg = balanceErrorMsg.getText();
            System.out.println("Balance error msg displayed or not "+ balanceErrorMsg.isDisplayed());
            if (balanceErrorMsg.isDisplayed()) {
                if (insuficientBalanceErrorMsg.contains(
                        "You do not have enough balance in your wallet for this purchase")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println(
                    "Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
