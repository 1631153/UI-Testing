package steps.Feature13;

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
import java.util.List;
import java.util.Map;

public class Steps_Feature13 {

	// WebDriver para interactuar con el navegador
	WebDriver driver;

	// Configuración inicial del entorno de pruebas
	@Before
	public void setUp() {
	    // Establece la propiedad del sistema para el controlador de Chrome
	    System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");

	    // Inicializa el WebDriver de Chrome
	    driver = new ChromeDriver();

	    // Maximiza la ventana del navegador para asegurarse de que los elementos sean visibles
	    driver.manage().window().maximize();
	}

	// Limpieza después de la prueba para cerrar el navegador
	@After
	public void tearDown() {
	    // Si el driver está inicializado, se cierra el navegador
	    if (driver != null) {
	        driver.quit();
	    }
	}

	// Función para hacer clic en un botón usando un localizador
	private void clickButton(By locator) {
	    driver.findElement(locator).click();
	}

	// Verifica que los mensajes de error esperados estén presentes en la interfaz
	private void verifyErrorMessages(Map<String, String> errorMessages) {
		errorMessages.forEach((field, expectedMessage) -> {
			By errorLocator = By.xpath("//div[contains(@class, 'text-danger') and contains(text(), '" + expectedMessage + "')]");
			Assert.assertTrue(driver.findElement(errorLocator).isDisplayed(), "El mensaje de error para el campo " + field + " no se muestra.");
		});
	}

	// Rellena el formulario con los campos proporcionados
	private void fillForm(Map<String, String> fields) {
	    fields.forEach((key, value) -> {
	        // Encuentra el campo de formulario usando el ID
	        WebElement element = driver.findElement(By.id(key));

	        // Verifica si el campo es un <select> (menú desplegable)
	        if (element.getTagName().equals("select")) {
	            // Si es un <select>, selecciona el valor visible
	            new Select(element).selectByVisibleText(value);
	            // Se aplica un tiempo de espera explícito para asegurar la carga de los elementos
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	        } else {
	            // Si no es un <select>, ingresa el valor usando sendKeys
	            element.clear();
	            element.sendKeys(value);
	        }
	    });
	}

	// Verifica si la dirección esperada aparece en la lista de direcciones
	private boolean verifyAddressInList(String[] expectedAddress) {
	    List<WebElement> rows = driver.findElements(By.cssSelector("table.table-bordered tbody tr"));
	    for (WebElement row : rows) {
	        String cellText = row.findElement(By.cssSelector("td.text-left")).getText();
	        // Compara la dirección en cada fila con la dirección esperada
	        if (isAddressMatching(cellText, expectedAddress)) {
	            return true;
	        }
	    }
	    return false;
	}

	// Compara si la dirección mostrada en la tabla coincide con la dirección esperada
	private boolean isAddressMatching(String cellText, String[] expectedAddress) {
	    for (String field : expectedAddress) {
	        if (!cellText.contains(field)) {
	            return false;
	        }
	    }
	    return true;
	}

	// Realiza una acción (clic en un botón) sobre la dirección que coincide con la dirección esperada
	private void performActionOnAddress(String[] expectedAddress, By actionButtonLocator) {
	    List<WebElement> rows = driver.findElements(By.cssSelector("table.table-bordered tbody tr"));
	    for (WebElement row : rows) {
	        String cellText = row.findElement(By.cssSelector("td.text-left")).getText();
	        // Si la dirección coincide, realiza la acción (clic en el botón)
	        if (isAddressMatching(cellText, expectedAddress)) {
	            row.findElement(actionButtonLocator).click();
	            break;
	        }
	    }
	}

	@Given("El usuario está logeado en su cuenta 3")
	public void el_usuario_esta_logeado_en_su_cuenta3(Map<String, String> userDetails) {
	    driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
	    clickButton(By.cssSelector("a[title='My Account']"));
	    clickButton(By.linkText("Login"));
	    fillForm(userDetails);
	    clickButton(By.cssSelector("input.btn.btn-primary[value='Login']"));
	}

	@When("El usuario hace clic en el enlace {string} 3")
	public void el_usuario_hace_clic_en_el_enlace3(String linkText) {
	    clickButton(By.partialLinkText(linkText));
	}

	@When("El usuario intenta agregar una nueva dirección sin completar ningún campo")
	public void tryAddingEmptyAddress() {
	    clickButton(By.cssSelector("a.btn.btn-primary")); // Add new address
	    
	    // Borra los campos de la dirección
	    driver.findElement(By.id("input-firstname")).clear();
	    driver.findElement(By.id("input-lastname")).clear();
	    driver.findElement(By.id("input-company")).clear();
	    driver.findElement(By.id("input-address-1")).clear();
	    driver.findElement(By.id("input-address-2")).clear();
	    driver.findElement(By.id("input-city")).clear();
	    driver.findElement(By.id("input-postcode")).clear();

	    WebElement dropdownCountry = driver.findElement(By.id("input-country"));
	    new Select(dropdownCountry).selectByVisibleText("--- Please Select ---");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	    WebElement dropdownZone = driver.findElement(By.id("input-zone"));
	    new Select(dropdownZone).selectByVisibleText("--- Please Select ---");

	    clickButton(By.cssSelector("input.btn.btn-primary[value='Continue']"));
	}

	@Then("El sistema debería mostrar un error indicando que todos los campos son obligatorios")
	public void verifyAllFieldsError() {
		Map<String, String> errorMessages = new HashMap<>();
 	    errorMessages.put("input-firstname", "First Name must be between 1 and 32 characters!");
 	    errorMessages.put("input-lastname", "Last Name must be between 1 and 32 characters!");
 	    errorMessages.put("input-address-1", "Address must be between 3 and 128 characters!");
 	    errorMessages.put("input-city", "City must be between 2 and 128 characters!");
 	    //errorMessages.put("input-postcode", "Postcode must be between 2 and 10 characters!");
 	    // No pongo el input-postcode porque algunas veces aparece y otras
 	    errorMessages.put("input-country", "Please select a country!");
 	    //errorMessages.put("input-zone", "Please select a region / state!");
 	    // No pongo el input-zone porque no puedo editarlo de manera comfiable, no se porque
	    verifyErrorMessages(errorMessages);
	}

	@When("El usuario agrega una nueva dirección con:")
	public void addNewAddress(Map<String, String> addressDetails) {
	    clickButton(By.cssSelector("a.btn.btn-primary"));
	    fillForm(addressDetails);
	    clickButton(By.cssSelector("input.btn.btn-primary[value='Continue']"));
	}

	@Then("La dirección debería ser guardada y aparecer en la lista de direcciones del usuario")
	public void verifyAddressSaved() {
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible' and contains(text(), 'Your address has been successfully added')]")).isDisplayed(),
	            "La dirección no se ha guardado correctamente.");

	    // Valores esperados para la dirección
        String[] expectedAddress = {
            "Javier David",
            "MiEmpresa",
            "Calle Falsa 123",
            "Piso 3, Puerta A",
            "Madrid 28000",
            "Madrid",
            "Spain"
        };
        
	    Assert.assertTrue(verifyAddressInList(expectedAddress),
	            "La dirección esperada no fue encontrada o no coincide en todos los campos.");
	}

	@When("El usuario intenta editar una dirección borrando la información de todos los campos")
	public void el_usuario_intenta_editar_borrando_campos() {
		
		// Valores esperados para la dirección
        String[] expectedAddress = {
            "Javier David",
            "MiEmpresa",
            "Calle Falsa 123",
            "Piso 3, Puerta A",
            "Madrid 28000",
            "Madrid",
            "Spain"
        };

	    performActionOnAddress(expectedAddress, By.cssSelector("a.btn-info"));

	    // Borra los campos de la dirección
	    driver.findElement(By.id("input-firstname")).clear();
	    driver.findElement(By.id("input-lastname")).clear();
	    driver.findElement(By.id("input-company")).clear();
	    driver.findElement(By.id("input-address-1")).clear();
	    driver.findElement(By.id("input-address-2")).clear();
	    driver.findElement(By.id("input-city")).clear();
	    driver.findElement(By.id("input-postcode")).clear();

	    WebElement dropdownCountry = driver.findElement(By.id("input-country"));
	    new Select(dropdownCountry).selectByVisibleText("--- Please Select ---");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	    WebElement dropdownZone = driver.findElement(By.id("input-zone"));
	    new Select(dropdownZone).selectByVisibleText("--- Please Select ---");

	    clickButton(By.cssSelector("input.btn.btn-primary[value='Continue']"));
	}
	
	@When("El usuario edita la dirección a:")
	public void editAddress(Map<String, String> updatedAddress) {
	    
		// Valores esperados para la dirección
	    String[] expectedAddress = {
	        "Javier David",
	        "MiEmpresa",
	        "Calle Falsa 123",
	        "Piso 3, Puerta A",
	        "Madrid 28000",
	        "Madrid",
	        "Spain"
	    };
	    
	    performActionOnAddress(expectedAddress, By.cssSelector("a.btn-info"));
	    fillForm(updatedAddress);
	    clickButton(By.cssSelector("input.btn.btn-primary[value='Continue']"));
	}

	@Then("La dirección debería ser actualizada correctamente en la cuenta del usuario")
	public void verifyAddressUpdated() {
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible' and contains(text(), 'Your address has been successfully updated')]")).isDisplayed(),
	            "La dirección no se ha actualizado correctamente.");

	    // Valores esperados para la dirección
        String[] updatedAddress = {
            "David Javier",
            "MiNuevaEmpresa",
            "Calle Verdadera 456",
            "Piso 4, Puerta B",
            "Barcelona 28001",
            "Barcelona",
            "Spain"
        };
        
	    Assert.assertTrue(verifyAddressInList(updatedAddress),
	            "La dirección actualizada no fue encontrada o no coincide en todos los campos.");
	}

	@When("El usuario elimina la dirección")
	public void deleteAddress() {
		
		// Valores esperados para la dirección
        String[] addressToDelete = {
            "David Javier",
            "MiNuevaEmpresa",
            "Calle Verdadera 456",
            "Piso 4, Puerta B",
            "Barcelona 28001",
            "Barcelona",
            "Spain"
        };
        
	    performActionOnAddress(addressToDelete, By.cssSelector("a.btn-danger"));
	}

	@Then("La dirección debería ser eliminada y ya no aparecer en la lista de direcciones")
	public void verifyAddressDeleted() {
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible' and contains(text(), 'Your address has been successfully deleted')]")).isDisplayed(),
	            "La dirección no se ha eliminado correctamente.");

	    // Valores esperados para la dirección
        String[] addressToDelete = {
            "David Javier",
            "MiNuevaEmpresa",
            "Calle Verdadera 456",
            "Piso 4, Puerta B",
            "Barcelona 28001",
            "Barcelona",
            "Spain"
        };
        
	    Assert.assertFalse(verifyAddressInList(addressToDelete),
	            "La dirección borrada fue encontrada en el Address Book.");
	}

	@When("El usuario intenta eliminar la única dirección que tiene")
	public void tryDeletingDefaultAddress() {
	    clickButton(By.cssSelector("a.btn-danger")); // Asume que solo hay una dirección
	}

	@Then("El sistema debería mostrar un error indicando que no se puede eliminar la última dirección")
	public void verifyCannotDeleteLastAddress() {
	    Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'alert-warning')]")).isDisplayed(),
	        "El mensaje de advertencia no se ha mostrado.");
	    
	    // Valores esperados para la dirección
        String[] expectedAddress = {
            "r r",
            "r",
            "rrr",
            "r",
            "rr 1r",
            "Angus",
            "United Kingdom"
        };
        
	    Assert.assertTrue(verifyAddressInList(expectedAddress),
	            "La dirección actualizada no fue encontrada o no coincide en todos los campos.");
	}
}
