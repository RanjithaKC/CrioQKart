package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";
    // WebElement searchTextBox = driver.findElement(By.xpath("//input[@name='search']"));

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
           // WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
           WebDriverWait wait = new WebDriverWait(driver, 10);
           WebElement logout_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("MuiButton-text")));
            logout_button.click();

            // Wait for Logout to Complete
            //Thread.sleep(3000);

            wait.until(ExpectedConditions.urlToBe("https://crio-qkart-frontend-qa.vercel.app/"));
            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box
            WebElement searchTextBox = driver.findElement(By.xpath("//input[@name='search']"));
            searchTextBox.clear();
            searchTextBox.sendKeys(product);
            Thread.sleep(3000);
            //SLEEP_STMT_09: Wait for search to complete
            WebDriverWait wait = new WebDriverWait(driver, 10);
           // List<WebElement> elementsLocatedWithWait = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']"))); 
           // System.out.println("waiting for page to load after searching until all elements are visible by locator ");  
            wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']")),ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()=' No products found ']"))));
            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {};
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results
            searchResults = driver
                    .findElements(By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']"));
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            WebElement searchTextBox = driver.findElement(By.xpath("//input[@name='search']"));
            WebElement errorMsg =
                    driver.findElement(By.xpath("//h4[text()=' No products found ']"));
            searchTextBox.clear();
            searchTextBox.sendKeys("Gesundheit");
            //Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 10);

            wait.until(ExpectedConditions.textToBePresentInElementValue(errorMsg, " No products found "));
            if (errorMsg.getText().contains(" No products found ")) {
                status = true;
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding to the
             * matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> searchedProduct = driver.findElements(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
            List<WebElement> addToCartButton = driver.findElements(By.xpath("//button[text()='Add to cart']"));
            // cannot use for each loop bcz based on product name have to click on add to cart so there is a dependency hence better to use index based for loop
            // for(WebElement resultProductName : searchedProduct){
            //     if(resultProductName.getText().equals(productName)){
            //         addToCartButton.click();
            //         Thread.sleep(2000);
            //         break;
            //     }
            //     return true;
            // }
            for(int i=0; i<searchedProduct.size(); i++){
                WebElement searchedProductName = searchedProduct.get(i);
                String searchedProductText = searchedProductName.getText();
                System.out.println("The searched product is "+ searchedProductName.getText());
                if(searchedProductText.equals(productName)){
                    addToCartButton.get(i).click();
                    Thread.sleep(4000);
                    return true;
                } 
            }
            System.out.println("Unable to find the given product");
            return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            WebElement checkoutButton = driver.findElement(By.xpath("//button[text()='Checkout']"));
            checkoutButton.click();
            //Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 10);

            wait.until(ExpectedConditions.urlContains("/checkout"));
            String checkoutPageUrl = driver.getCurrentUrl();
            if(checkoutPageUrl.contains("checkout")){
                status = true;
            }
            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5
            WebDriverWait wait = new WebDriverWait(driver, 10);
            // Find the item on the cart with the matching productName
            List<WebElement> cartParentElement = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']"));
            // Increment or decrement the quantity of the matching product until the current
            int productQuantityInCart;
            for(WebElement productCard : cartParentElement){
                WebElement productNameInCart = productCard.findElement(By.xpath("./div[1]"));
                if(productNameInCart.getText().equals(productName)){
                    WebElement productQuantity = productCard.findElement(By.xpath("./div[2]/div/div"));
                   String productQuantityInCartText = productQuantity.getText();
                    productQuantityInCart = Integer.parseInt(productQuantityInCartText);
                    while(productQuantityInCart!=quantity){
                    if(productQuantityInCart<quantity){ //1<2
                        WebElement increamentOperator = productCard.findElement(By.xpath("./div[2]/div/button[2]"));
                        increamentOperator.click();
                       // Thread.sleep(6000);
                       wait.until(ExpectedConditions.textToBePresentInElement(productCard.findElement(By.xpath("./div[2]/div/div")),String.valueOf(productQuantityInCart+1)));
                       productQuantityInCart++;

                    }else if(productQuantityInCart>quantity){
                        WebElement decreamentOperator = productCard.findElement(By.xpath("./div[2]/div/button[1]"));
                        decreamentOperator.click();
                       // Thread.sleep(6000);
                       wait.until(ExpectedConditions.textToBePresentInElement(productCard.findElement(By.xpath("./div[2]/div/div")),String.valueOf(productQuantityInCart-1)));
                        productQuantityInCart--;
                    }else if(productQuantityInCart == quantity){
                        return true;
                    }
                }
            }
        }
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)


            return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            WebElement cartParent = driver.findElement(By.className("cart"));
            List<WebElement> cartContents = cartParent.findElements(By.className("css-zgtx0t"));

            ArrayList<String> actualCartContents = new ArrayList<String>() {};
            for (WebElement cartItem : cartContents) {
                actualCartContents.add(
                        cartItem.findElement(By.className("css-1gjj37g")).getText().split("\n")[0]);
            }

            for (String expected : expectedCartContents) {
                if (!actualCartContents.contains(expected)) {
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
