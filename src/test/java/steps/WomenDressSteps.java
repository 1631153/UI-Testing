package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class WomenDressSteps {
	
	WebDriver driver;
	
	@Given("the user is in the index page")
	public void TheUserIsInTheIndexPage() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("https://automationexercise.com/");
		driver.findElement(By.className("fc-button-label")).click();
	}
	
	@When("the user clicks the products option")
	public void TheUserClicksTheProductsOption() {
		driver.findElement(By.partialLinkText("Products")).click();
	}
	
	@When("the user enters dress in the search bar")
	public void TheUserEntersDressInTheSearchBar() {
		driver.findElement(By.id("search_product")).sendKeys("dress");
	}
	
	@When("the user clicks the search button")
	public void TheUserClicksTheSearchButton() {
		driver.findElement(By.id("submit_search")).click();
	}
	
	@Then("the dress list appears")
	public void TheDressListAppears() {
		String title = driver.findElement(By.className("features_items")).getText();
		Assert.assertTrue(title.contains("SEARCHED PRODUCTS"));
	}
}
