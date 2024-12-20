package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.util.Map;

public class Steps_Feature2 {
    
    WebDriver driver;
    
    @Given("the user is in the homepage")
	public void TheUserIsInTheIndexPage() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
	} 
    
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
    public void TheUserShouldSeeTheAccountPage() {
    	Assert.assertEquals("https://opencart.abstracta.us/index.php?route=account/account", driver.getCurrentUrl(), "The actual page is not the account page.");
    }
}