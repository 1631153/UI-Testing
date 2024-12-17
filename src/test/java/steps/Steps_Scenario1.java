package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;

public class Steps_Scenario1 {
    
    WebDriver driver;
    
    @Given("the user is in the homepage")
    public void TheUserIsInTheHomepage() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
        driver.findElement(By.className("fc-button-label")).click();
    }
}