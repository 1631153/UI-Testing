package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

public class Steps_Feature11 {

    WebDriver driver;
    
    // Configuración antes de cada escenario de prueba
    @Before
    public void setUp() {
        // Establece la ubicación del driver de Chrome
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        
        // Inicializa el driver de Chrome
        driver = new ChromeDriver();
        
        // Maximiza la ventana del navegador
        driver.manage().window().maximize();
    }

    // Limpieza después de cada escenario de prueba
    @After
    public void tearDown() {
        // Cierra el navegador si el driver no es null
        if (driver != null) {
            driver.quit();
        }
    }
    
    // Función para hacer clic en un botón usando un localizador
 	private void clickButton(By locator) {
 	    driver.findElement(locator).click();
 	}

 	// Rellena el formulario con los campos proporcionados
 	private void fillForm(Map<String, String> fields) {
 	    fields.forEach((key, value) -> {
 	        // Encuentra el campo de formulario usando el ID
 	        WebElement element = driver.findElement(By.id(key));

 	        // Ingresa el valor usando sendKeys
 	        element.clear();
 	        element.sendKeys(value);
 	    });
 	}

    @Given("El usuario está logeado en su cuenta")
    public void el_usuario_esta_logeado_en_su_cuenta(Map<String, String> userDetails) {
    	driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
	    clickButton(By.cssSelector("a[title='My Account']"));
	    clickButton(By.linkText("Login"));
	    fillForm(userDetails);
	    clickButton(By.cssSelector("input.btn.btn-primary[value='Login']"));
    }

    @When("El usuario hace clic en el enlace {string}")
    public void el_usuario_hace_clic_en_el_enlace_password(String enlace) {
        // Hace clic en el enlace "Password"
    	clickButton(By.linkText(enlace));
    }

    @When("El usuario introduce una nueva contraseña:")
    public void el_usuario_introduce_una_nueva_contraseña(Map<String, String> newPasswordDetails) {
    	fillForm(newPasswordDetails);
    }

    @When("El usuario introduce la confirmación de la nueva contraseña:")
    public void el_usuario_introduce_la_confirmacion_de_la_nueva_contraseña(Map<String, String> confirmPasswordDetails) {
    	fillForm(confirmPasswordDetails);
    }

    @When("El usuario hace clic en el botón de continuar para guardar la nueva contraseña")
    public void el_usuario_hace_clic_en_el_boton_de_continuar_para_guardar_la_nueva_contraseña() {
        // Hace clic en el botón de continuar para guardar la nueva contraseña
    	clickButton(By.cssSelector("input[type='submit']"));
    }
    
    @Then("El sistema debería mostrar un mensaje de error en la contraseña")
    public void el_sistema_debería_mostrar_un_mensaje_de_error_en_la_contraseña() {
        // Verifica que el mensaje de error se ha mostrado
    	By errorLocator = By.xpath("//div[contains(@class, 'text-danger') and contains(text(), 'Password must be between 4 and 20 characters!')]");
		Assert.assertTrue(driver.findElement(errorLocator).isDisplayed(), "El mensaje de error no se ha mostrado.");
    }

    @Then("El sistema debería mostrar un mensaje de error indicando que las contraseñas no coinciden")
    public void el_sistema_debería_mostrar_un_mensaje_de_error_indicando_que_las_contraseñas_no_coinciden() {
        // Verifica que el mensaje de error se ha mostrado
    	By errorLocator = By.xpath("//div[contains(@class, 'text-danger') and contains(text(), 'Password confirmation does not match password!')]");
		Assert.assertTrue(driver.findElement(errorLocator).isDisplayed(), "El mensaje de error no se ha mostrado.");
    }

    @Then("La contraseña del usuario debería cambiarse con éxito")
    public void la_contraseña_del_usuario_debería_cambiarse_con_éxito() {
        // Verifica que el mensaje de éxito se ha mostrado
        boolean mensajeExitoVisible = driver.findElements(By.xpath("//div[contains(@class, 'alert-success')]")).size() > 0;
        Assert.assertTrue(mensajeExitoVisible, "El mensaje de éxito no se ha mostrado.");
    }
}
