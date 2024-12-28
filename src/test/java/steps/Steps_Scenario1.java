package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Steps_Scenario1 {
    
    static WebDriver driver;
    
    //Scenario 1
    @Given("the user is in the homepage")
	public void TheUserIsInTheIndexPage() {
		System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
		driver.manage().window().maximize();
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
    public void TheUserAgreesToThePrivacyPolicy() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.findElement(By.name("agree")).click();
    }
    
    @When("the user clicks on the continue button")
    public void TheUserClicksOnTheContinueButton() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.findElement(By.cssSelector("input.btn.btn-primary[value='Continue']")).click();
    }
    
    @When("the user should see a private policy warning message")
    public void theUserShouldSeeAPrivatePolicyWarningMessage() throws InterruptedException {
    	Thread.sleep(1000);
    	WebElement emailField = driver.findElement(By.id("input-email"));
        String emailType = emailField.getAttribute("type");

        Assert.assertEquals(emailType, "email", "El campo no tiene el atributo 'type=email'.");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        String emailValue = emailField.getAttribute("value");
        Assert.assertFalse(emailValue.contains("@"), "El email ingresado no es inválido.");
    }
    
    @Then("the user should see a confirmation message")
    public void theUserShouldSeeAConfirmationMessage() {
        String expectedMessage = "Congratulations! Your new account has been successfully created!";
        String actualMessage = driver.findElement(By.cssSelector("div#content p")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "The success message is incorrect.");
    }
    
    @Then("the user should see a email warning message")
    public void theUserShouldSeeAEmailWarningMessage() throws InterruptedException {
    	Thread.sleep(1000);
        WebElement warningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger"));
        Assert.assertTrue(warningMessage.isDisplayed(), "El mensaje de advertencia no se mostró.");

        String expectedMessage = "Warning: E-Mail Address is already registered!";
        String actualMessage = warningMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "El mensaje de advertencia no es el esperado.");
    }

    @Then("the user should be in the same page")
    public void TheUserShouldBeInTheSamePage() throws InterruptedException {
    	Thread.sleep(1000);
    	Assert.assertEquals("https://opencart.abstracta.us/index.php?route=account/register", driver.getCurrentUrl(), "La pagina actual no es la correcta.");
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
    	Assert.assertEquals("https://opencart.abstracta.us/index.php?route=account/account", driver.getCurrentUrl(), "La pagina actual no es la correcta.");
    }
    
    @Then("the user should see a no matching message")
    public void theUserShouldSeeANoMatchingMessage() throws InterruptedException {
    	Thread.sleep(1000);
        WebElement warningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger"));
        Assert.assertTrue(warningMessage.isDisplayed(), "El mensaje de advertencia no se mostró.");

        String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
        String actualMessage = warningMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "El mensaje de advertencia no es el esperado.");
    }
    
    //Scenario 3
    @Given("the user is on the login page")
	public void TheUserIsOnTheLoginPage() {
    	driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        Assert.assertEquals("https://opencart.abstracta.us/index.php?route=account/login", driver.getCurrentUrl(), "La pagina actual no es la correcta.");
	} 
    
    @Given("the user is in the Shopping Cart page")
   	public void TheUserIsInTheShoppingCartPage() {
       	driver.findElement(By.cssSelector("a[title='Shopping Cart']")).click();
   	} 
    
    @Given("the user is on My Account page")
    public void TheUserIsOnMyAccountPage() {
    	driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.linkText("My Account")).click();
    }
    
    @When("the user clicks on MacBook from Featured")
    public void theUserClicksOnMacBookFromFeatured() {
        driver.findElement(By.linkText("MacBook")).click();
    }
    
    @When("the user clicks Add to Cart button")
    public void theUserClicksAddToCartButton() {
        driver.findElement(By.id("button-cart")).click();
    }
    
    @When("the user clicks on Checkout button")
    public void TheUserClicksOnCheckoutButton() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.findElement(By.linkText("Checkout")).click();
    }
    
    @When("the user clicks on another continue button")
    public void TheUserClicksOnAnotherContinueButton() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.findElement(By.id("button-payment-method")).click();
    }
    
    @When("the user clicks on the Confirm Order button")
    public void TheUserClicksOnTheConfirmOrderButton() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.findElement(By.id("button-confirm")).click();
    }
    
    @When("the user clicks on Order History")
    public void TheUserClicksOnOrderHistory() {
    	driver.findElement(By.linkText("Order History")).click();
    }
    
    @Then("the user should see the cart summary")
    public void theUserShouldSeeTheCartSummary() throws InterruptedException {
    	Thread.sleep(1000);
    	WebElement successMessage = driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible"));
        Assert.assertTrue(successMessage.isDisplayed(), "El mensaje de éxito no se mostró.");

        String cartText = driver.findElement(By.id("cart-total")).getText();
        Assert.assertTrue(cartText.contains("1 item(s)"), "El carrito no tiene el producto esperado.");
    }
    
    @Then("the user should see an order confirmation message")
    public void theUserShouldSeeAnOrderConfirmationMessage() throws InterruptedException {
    	Thread.sleep(1000);
        String expectedMessage = "Your order has been successfully processed!";
        String actualMessage = driver.findElement(By.cssSelector("div#content p")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "The success message is incorrect.");
    }
    
    @Then("the user should see the date of the last product to confirm")
    public void TheUserShouldSeeTheDateOfTheLastProductToConfirm() throws InterruptedException {
        Thread.sleep(1000);
        WebElement lastOrderDate = driver.findElement(By.cssSelector("table tbody tr:first-child td:nth-child(6)"));
        String dateText = lastOrderDate.getText();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Assert.assertEquals(dateText, today.format(formatter), "La fecha del último pedido no es la de hoy.");
    }

    
    //Scenario 4
    @When("the user clicks on the currency dropdown to select Euro")
    public void TheUserClicksOnTheCurrencyDropdownToSelectEuro() {
    	driver.findElement(By.className("dropdown-toggle")).click();
    	driver.findElement(By.name("EUR")).click();
    }
    
    @Then("the prices on the homepage should be displayed all in euros")
    public void ThePricesOnTheHomepageShouldBeDisplayedAllInEuros() throws InterruptedException {
    	Thread.sleep(1000);
    	List<WebElement> priceElements = driver.findElements(By.cssSelector(".price"));
        boolean allPricesInEuros = true;

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            if (!priceText.contains("€")) {
                allPricesInEuros = false;
                break;
            }
        }

        Assert.assertTrue(allPricesInEuros, "No todos los precios están en euros.");
    	
    }




    
    
    
    
    
}