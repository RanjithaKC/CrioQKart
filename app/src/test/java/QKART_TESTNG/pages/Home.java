package QKART_TESTNG.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

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
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("css-1urhf6j"), "Logout"));

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // Clear the contents of the search box and Enter the product name in the search
            // box
            WebElement searchBox = driver.findElement(By.xpath("//input[@name='search'][1]"));
            searchBox.clear();
            searchBox.sendKeys(product);

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(String
                    .format("//div[@class='MuiCardContent-root css-1qw96cp'][1]/p[contains(text(),'%s')]", product))));
            Thread.sleep(3000);
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
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            // Find all webelements corresponding to the card content section of each of
            // search results
            searchResults = driver.findElementsByClassName("css-1qw96cp");
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
            status = driver.findElementByXPath("//h4[text()=' No products found ']").isDisplayed();
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
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> gridContent = driver.findElementsByClassName("css-sycj1h");
            for (WebElement cell : gridContent) {
                if (productName.contains(cell.findElement(By.className("css-yg30e6")).getText())) {
                    cell.findElement(By.tagName("button")).click();

                    WebDriverWait wait = new WebDriverWait(driver, 30);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                            String.format("//*[@class='MuiBox-root css-1gjj37g']/div[1][text()='%s']", productName))));
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
            // Find and click on the the Checkout button
            WebElement checkoutBtn = driver.findElement(By.className("checkout-btn"));
            checkoutBtn.click();

            status = true;
            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {

          //  WebElement cartParent = driver.findElement(By.className("cart"));
          //  List<WebElement> cartContents = cartParent.findElements(By.className("css-zgtx0t"));

          List<WebElement> cartContents = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']"));

            int currentQty;
            for (WebElement item : cartContents) {
                // if (productName.contains(
                //         item.findElement(By.xpath("//*[@class='MuiBox-root css-1gjj37g']/div[1]")).getText())) {

                    if (productName.contains(
                         item.findElement(By.xpath("./div[1]")).getText())) {
                   // currentQty = Integer.valueOf(item.findElement(By.className("css-olyig7")).getText());
                   currentQty = Integer.valueOf(item.findElement(By.xpath("./div[2]/div/div")).getText());


                    System.out.println("current quantity" + currentQty);

                    while (currentQty != quantity) {
                        if (currentQty < quantity) {
                          //  item.findElements(By.tagName("button")).get(1).click();
                          item.findElement(By.xpath("./div[2]/div/button[2]")).click();


                        } else {
                           // item.findElements(By.tagName("button")).get(0).click();
                          item.findElement(By.xpath("./div[2]/div/button[1]")).click();

                        }

                        synchronized (driver) {
                            driver.wait(3000);
                        }

                        currentQty = Integer
                                .valueOf(item.findElement(By.xpath("//div[@data-testid=\"item-qty\"]")).getText());
                    }

                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println(("exception occurred when updating cart"));
            return false;
        }
    }

    // public Boolean changeProductQuantityinCart(String productName, int quantity) {
    //     try {
    //         // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5
    //         WebDriverWait wait = new WebDriverWait(driver, 10);
    //         // Find the item on the cart with the matching productName
    //         List<WebElement> cartParentElement = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']"));
    //         // Increment or decrement the quantity of the matching product until the current
    //         int productQuantityInCart;
    //         for(WebElement productCard : cartParentElement){
    //             WebElement productNameInCart = productCard.findElement(By.xpath("./div[1]"));
    //            // System.out.println("product name "+ productNameInCart );
    //             if(productNameInCart.getText().equals(productName)){
    //                 WebElement productQuantity = productCard.findElement(By.xpath("./div[2]/div/div"));
    //                String productQuantityInCartText = productQuantity.getText();
    //                 productQuantityInCart = Integer.parseInt(productQuantityInCartText);
    //                 System.out.println("product quantity "+ productQuantityInCart);
    //                 while(productQuantityInCart!=quantity){
    //                 if(productQuantityInCart<quantity){ //1<2
    //                     WebElement increamentOperator = productCard.findElement(By.xpath("./div[2]/div/button[2]"));
    //                     increamentOperator.click();
    //                    // Thread.sleep(6000);
    //                    wait.until(ExpectedConditions.textToBePresentInElement(productCard.findElement(By.xpath("./div[2]/div/div")),String.valueOf(productQuantityInCart+1)));
    //                    productQuantityInCart++;
    //                    // return true;
    //                 }else if(productQuantityInCart>quantity){
    //                     WebElement decreamentOperator = productCard.findElement(By.xpath("./div[2]/div/button[1]"));
    //                     decreamentOperator.click();
    //                    Thread.sleep(6000);
    //                    wait.until(ExpectedConditions.textToBePresentInElement(productCard.findElement(By.xpath("./div[2]/div/div")),String.valueOf(productQuantityInCart-1)));
    //                     productQuantityInCart--;
    //                    // return true;
    //                 }else if(productQuantityInCart == quantity){
    //                     return true;
    //                 }
    //             }
    //         }
    //    }
    //     //    quantity is reached (Note: Keep a look out when then input quantity is 0,
    //       //  here we need to remove the item completely from the cart)

    //         return false;
    //     } catch (Exception e) {
    //         if (quantity == 0)
    //             return true;
    //         System.out.println("exception occurred when updating cart: " + e.getMessage());
    //         return false;
    //     }
    // }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            List<WebElement> cartContents = driver.findElementsByXPath(
                    "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-3 css-1q5pok1']//div[@class='MuiBox-root css-1gjj37g']/div[not(@class)]");
            ArrayList<String> actualCartContents = new ArrayList<String>() {

            };
            for (WebElement cartItem : cartContents) {
                actualCartContents.add(cartItem.getText());
            }

            for (String expected : expectedCartContents) {
                // To trim as getText() trims cart item title
                if (!actualCartContents.contains(expected.trim())) {
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
