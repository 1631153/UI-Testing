package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class Steps_Scenario1 {
    
    WebDriver driver;
    
    //Scenario 1
    @Given("the user is in the homepage")
	public void TheUserIsInTheIndexPage() {
		System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
	} 
    
    @When("the user clicks to go to the registration page")
    public void TheUserClicksToGoToTheRegistrationPage() {
    	driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
    }
    
    @When("the user fills in the registration form with the following details:")
    public void theUserFillsInTheRegistrationFormWithTheFollowingDetails(Map<String, String> userDetails) {
    	driver.findElement(By.id("input-firstname")).sendKeys(userDetails.get("First Name"));
        driver.findElement(By.id("input-lastname")).sendKeys(userDetails.get("Last Name"));
        driver.findElement(By.id("input-email")).sendKeys(userDetails.get("Email"));
        driver.findElement(By.id("input-telephone")).sendKeys(userDetails.get("Telephone"));
        driver.findElement(By.id("input-password")).sendKeys(userDetails.get("Password"));
        driver.findElement(By.id("input-confirm")).sendKeys(userDetails.get("Confirm"));
    }
    
    @When("the user agrees to the privacy policy")
    public void TheUserAgreesToThePrivacyPolicy() {
    	driver.findElement(By.name("agree")).click();
    }
    
    @When("the user clicks on the continue button")
    public void TheUserClicksOnTheContinueButton() {
    	driver.findElement(By.cssSelector("input.btn.btn-primary[value='Continue']")).click();
    }
    
    @Then("the user should see a confirmation message")
    public void theUserShouldSeeAConfirmationMessage() {
        String expectedMessage = "Congratulations! Your new account has been successfully created!";
        String actualMessage = driver.findElement(By.cssSelector("div#content p")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "The success message is incorrect.");
    }
    
    //Scenario 2
    @When("the user clicks to go to the login page")
    public void TheUserClicksToGoToTheLoginPage() {
    	driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
    }
    
    @When("the user fills email and password:")
    public void theUserFillsEmailAndPassword(Map<String, String> userDetails) {
        driver.findElement(By.id("input-email")).sendKeys(userDetails.get("Email"));
        driver.findElement(By.id("input-password")).sendKeys(userDetails.get("Password"));
    }
    
    @When("the user clicks on the Login button")
    public void TheUserClicksOnTheLoginButton() {
    	driver.findElement(By.cssSelector("input.btn.btn-primary[value='Login']")).click();
    }
    
    @Then("the user should see the account page")
    public void TheUserShouldSeeTheAccountPage() throws InterruptedException {
    	Thread.sleep(1000);
    	Assert.assertEquals("https://opencart.abstracta.us/index.php?route=account/account", driver.getCurrentUrl(), "The actual page is not the account page.");
    }
    //Scenario 3
    
    //Scenario 4
    @When("the user clicks on the currency dropdown to select Euro")
    public void TheUserClicksOnTheCurrencyDropdownToSelectEuro() {
    	driver.findElement(By.className("dropdown-toggle")).click();
    	driver.findElement(By.name("EUR")).click();
    }
    
    @Then("the prices on the homepage should be displayed all in euros")
    public void ThePricesOnTheHomepageShouldBeDisplayedAllInEuros() {
    	List<WebElement> priceElements = driver.findElements(By.cssSelector(".price"));
        boolean allPricesInEuros = true;

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            if (!priceText.contains("€")) {
                allPricesInEuros = false;
                System.out.println("El precio no está en euros: " + priceText);
                break;
            }
        }

        Assert.assertTrue(allPricesInEuros, "No todos los precios están en euros.");
    	
    }
    
    
    //Scenario 3
    @When("the user clicks on MacBook from Featured")
    public void theUserClicksOnMacBookFromFeatured() {
        driver.findElement(By.linkText("MacBook")).click();
    }
    
    @When("the user clicks Add to Cart button")
    public void theUserClicksAddToCartButton() {
        driver.findElement(By.id("button-cart")).click();
    }
    
    @Then("the user should see the cart summary")
    public void theUserShouldSeeTheCartSummary() throws InterruptedException {
    	Thread.sleep(1000);
    	WebElement successMessage = driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible"));
        Assert.assertTrue(successMessage.isDisplayed(), "El mensaje de éxito no se mostró.");

        String cartText = driver.findElement(By.id("cart-total")).getText();
        Assert.assertTrue(cartText.contains("1 item(s)"), "El carrito no tiene el producto esperado.");
    }


    //Scenario 4
    @When("the user clicks on the currency dropdown to select Euro")
    public void TheUserClicksOnTheCurrencyDropdownToSelectEuro() {
    	driver.findElement(By.className("dropdown-toggle")).click();
    	driver.findElement(By.name("EUR")).click();
    }

    @Then("the prices on the homepage should be all displayed in euros")
    public void ThePricesOnTheHomepageShouldBeDisplayedAllInEuros() throws InterruptedException {
    	Thread.sleep(1000);
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".price"));
        boolean allPricesInEuros = true;

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            if (!priceText.contains("€")) {
                allPricesInEuros = false;
                System.out.println("El precio no está en euros: " + priceText);
                break;
            }
        }

        Assert.assertTrue(allPricesInEuros, "No todos los precios están en euros.");
    }

    
    
    
    
    
}