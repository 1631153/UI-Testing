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
import java.util.HashMap;
import java.util.Map;

public class Steps_Feature12 {

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

 	// Verifica que un mensaje de éxito esté presente
 	private void verifySuccessMessage(String message) {
 	    By successLocator = By.xpath("//div[contains(@class, 'alert-success') and contains(text(), '" + message + "')]");
 	    Assert.assertTrue(driver.findElement(successLocator).isDisplayed(), "El mensaje de éxito no se ha mostrado.");
 	}

 	// Función para verificar los valores de los campos después de una actualización
 	private void verifyUpdatedPersonalInfo(Map<String, String> expectedValues) {
 	    expectedValues.forEach((key, expectedValue) -> {
 	        WebElement element = driver.findElement(By.id(key));
 	        String actualValue = element.getDomAttribute("value");
 	        Assert.assertEquals(expectedValue, actualValue, "El valor del campo " + key + " no es el esperado.");
 	    });
 	}
 	
 	// Verifica que los mensajes de error esperados estén presentes en la interfaz
 	private void verifyErrorMessages(Map<String, String> errorMessages) {
 		errorMessages.forEach((field, expectedMessage) -> {
 	        By errorLocator = By.xpath("//div[contains(@class, 'text-danger') and contains(text(), '" + expectedMessage + "')]");
 	        Assert.assertTrue(driver.findElement(errorLocator).isDisplayed(), "El mensaje de error para el campo " + field + " no se muestra.");
 	    });
 	}

	@Given("El usuario está logeado en su cuenta 2")
	public void el_usuario_esta_logeado_en_su_cuenta2(Map<String, String> userDetails) {
	    driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
	    clickButton(By.cssSelector("a[title='My Account']"));
	    clickButton(By.linkText("Login"));
	    fillForm(userDetails);
	    clickButton(By.cssSelector("input.btn.btn-primary[value='Login']"));
	}
	
	@When("El usuario hace clic en el enlace {string} 2")
	public void el_usuario_hace_clic_en_el_enlace2(String linkText) {
	    clickButton(By.partialLinkText(linkText));
	}
	
	@When("El usuario actualiza su información personal con")
	public void el_usuario_actualiza_su_informacion_personal(Map<String, String> userDetails) {
	    fillForm(userDetails);
	    clickButton(By.cssSelector("div.pull-right input[type='submit']"));
	}
	
	@Then("El mensaje de éxito debería aparecer")
	public void el_mensaje_de_exito_deberia_aparecer() {
	    verifySuccessMessage("Your account has been successfully updated.");
	}
	
	@Then("La información personal del usuario debería actualizarse correctamente")
	public void la_informacion_personal_del_usuario_deberia_actualizarse_correctamente() {
	    Map<String, String> expectedValues = new HashMap<>();
	    expectedValues.put("input-firstname", "Javier");
	    expectedValues.put("input-lastname", "González");
	    expectedValues.put("input-email", "david.javi@test.com");
	    expectedValues.put("input-telephone", "987654321");
	
	    verifyUpdatedPersonalInfo(expectedValues);
	}
	
	@Then("La información personal del usuario debería restaurarse correctamente")
	public void la_informacion_personal_del_usuario_deberia_restaurarse_correctamente() {
	    Map<String, String> expectedValues = new HashMap<>();
	    expectedValues.put("input-firstname", "Javi");
	    expectedValues.put("input-lastname", "David");
	    expectedValues.put("input-email", "javi.david@test.com");
	    expectedValues.put("input-telephone", "123456789");
	
	    verifyUpdatedPersonalInfo(expectedValues);
	}
	
	@When("El usuario deja los campos vacíos y hace clic en \"Continue\"")
	public void el_usuario_deja_los_campos_vacios_y_hace_clic_en_continue() {
	    driver.findElement(By.id("input-firstname")).clear();
	    driver.findElement(By.id("input-lastname")).clear();
	    driver.findElement(By.id("input-email")).clear();
	    driver.findElement(By.id("input-telephone")).clear();
	    clickButton(By.cssSelector("div.pull-right input[type='submit']"));
	}
	
	@Then("El sistema debería mostrar los mensajes de error correspondientes")
	public void el_sistema_deberia_mostrar_mensajes_de_error() {
		Map<String, String> errorMessages = new HashMap<>();
 	    errorMessages.put("input-firstname", "First Name must be between 1 and 32 characters!");
 	    errorMessages.put("input-lastname", "Last Name must be between 1 and 32 characters!");
 	    errorMessages.put("input-email", "E-Mail Address does not appear to be valid!");
 	    errorMessages.put("input-telephone", "Telephone must be between 3 and 32 characters!");

 	   verifyErrorMessages(errorMessages);
	}
}
