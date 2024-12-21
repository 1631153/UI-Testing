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

    // El usuario está en la página de inicio para iniciar sesión
    @Given("El usuario está en la página de inicio para iniciar sesión")
    public void elUsuarioEstaEnLaPaginaDeInicioParaIniciarSesion() {
        // Configura el driver de Chrome
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        
        // Navega a la página de inicio de OpenCart
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
    }

    // El usuario hace clic en la opción para ir a la página de inicio de sesión
    @When("El usuario hace clic para ir a la página de inicio de sesión")
    public void elUsuarioHaceClicParaIrALaPaginaDeInicioDeSesion() {
        // Hace clic en el enlace 'Mi cuenta'
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        
        // Hace clic en 'Login' para ir a la página de inicio de sesión
        driver.findElement(By.linkText("Login")).click();
    }

    // El usuario ingresa el correo electrónico y la contraseña
    @When("El usuario ingresa el correo electrónico y la contraseña:")
    public void elUsuarioIngresaEmailYContraseña(Map<String, String> userDetails) {
        // Ingresa el correo electrónico
        driver.findElement(By.id("input-email")).sendKeys(userDetails.get("Email"));
        
        // Ingresa la contraseña
        driver.findElement(By.id("input-password")).sendKeys(userDetails.get("Password"));
    }

    // El usuario hace clic en el botón de inicio de sesión
    @When("El usuario hace clic en el botón de Iniciar sesión")
    public void elUsuarioHaceClicEnElBotonDeIniciarSesion() {
        // Hace clic en el botón 'Login' para enviar el formulario de inicio de sesión
        driver.findElement(By.cssSelector("input.btn.btn-primary[value='Login']")).click();
    }

    // El usuario debería ver la página de su cuenta después de iniciar sesión
    @Then("El usuario debería ver la página de su cuenta")
    public void elUsuarioDeberiaVerLaPaginaDeSuCuenta() {
        // Verifica que la URL actual sea la de la página de cuenta
        Assert.assertEquals("https://opencart.abstracta.us/index.php?route=account/account", 
                            driver.getCurrentUrl(), 
                            "La página actual no es la página de cuenta.");
    }
}
