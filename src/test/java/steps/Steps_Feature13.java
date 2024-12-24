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

public class Steps_Feature13 {

    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("El usuario está logeado en su cuenta 3")
    public void el_usuario_esta_logeado_en_su_cuenta3(Map<String, String> userDetails) {
        String email = userDetails.get("Email");
        String password = userDetails.get("Password");

        driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.cssSelector("input.btn.btn-primary[value='Login']")).click();
    }

    @When("El usuario hace clic en el enlace \"Address Book\" 3")
    public void el_usuario_hace_clic_en_el_enlace_address_book3() {
        driver.findElement(By.partialLinkText("Address Book")).click();
    }

    @When("El usuario intenta agregar una nueva dirección sin completar ningún campo")
    public void el_usuario_intenta_agregar_una_nueva_direccion_sin_completar() {
        driver.findElement(By.cssSelector("a.btn.btn-primary")).click(); // Hacer clic en "Añadir nueva dirección"    
        driver.findElement(By.cssSelector("input.btn.btn-primary[value='Continue']")).click(); // Intentar enviar el formulario vacío
    }

    @Then("El sistema debería mostrar un error indicando que todos los campos son obligatorios")
    public void el_sistema_deberia_mostrar_error_campos_obligatorios() {
        // Verificar los mensajes de error específicos para campos vacíos
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'First Name must be between 1 and 32 characters!')]")).isDisplayed(), "El error de First Name no se ha mostrado.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Last Name must be between 1 and 32 characters!')]")).isDisplayed(), "El error de Last Name no se ha mostrado.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Address must be between 3 and 128 characters!')]")).isDisplayed(), "El error de Address no se ha mostrado.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'City must be between 2 and 128 characters!')]")).isDisplayed(), "El error de City no se ha mostrado.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Please select a region / state!')]")).isDisplayed(), "El error de Region no se ha mostrado.");
    }

    @When("El usuario agrega una nueva dirección con:")
    public void el_usuario_agrega_una_nueva_direccion(Map<String, String> addressDetails) {
        driver.findElement(By.cssSelector("a.btn.btn-primary")).click(); // Hacer clic en "Añadir nueva dirección"

        driver.findElement(By.id("input-firstname")).sendKeys(addressDetails.get("Nombre"));
        driver.findElement(By.id("input-lastname")).sendKeys(addressDetails.get("Apellido"));
        driver.findElement(By.id("input-company")).sendKeys(addressDetails.get("Empresa"));
        driver.findElement(By.id("input-address-1")).sendKeys(addressDetails.get("Dirección 1"));
        driver.findElement(By.id("input-address-2")).sendKeys(addressDetails.get("Dirección 2"));
        driver.findElement(By.id("input-city")).sendKeys(addressDetails.get("Ciudad"));
        driver.findElement(By.id("input-postcode")).sendKeys(addressDetails.get("Código Postal"));

        // Seleccionar país del menú desplegable
        WebElement countryDropdown = driver.findElement(By.id("input-country"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(addressDetails.get("País"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        // Seleccionar región/estado del menú desplegable
        WebElement zoneDropdown = driver.findElement(By.id("input-zone"));
        Select selectZone = new Select(zoneDropdown);
        selectZone.selectByVisibleText(addressDetails.get("Región/Estado"));

        driver.findElement(By.cssSelector("input.btn.btn-primary[value='Continue']")).click();
    }

    @Then("La dirección debería ser guardada y aparecer en la lista de direcciones del usuario")
    public void la_direccion_deberia_ser_guardada() {
        // Verificar que la dirección se ha guardado correctamente
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible' and contains(text(), 'Your address has been successfully added')]")).isDisplayed(),
                "La dirección no se ha guardado correctamente.");
    }

    @When("El usuario intenta editar una dirección borrando la información de todos los campos")
    public void el_usuario_intenta_editar_borrando_campos() {
        WebElement editButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[2]/td[2]/a[1]"));
        editButton.click();

        // Borrar todos los campos
        driver.findElement(By.id("input-firstname")).clear();
        driver.findElement(By.id("input-lastname")).clear();
        driver.findElement(By.id("input-company")).clear();
        driver.findElement(By.id("input-address-1")).clear();
        driver.findElement(By.id("input-address-2")).clear();
        driver.findElement(By.id("input-city")).clear();
        driver.findElement(By.id("input-postcode")).clear();

        // Intentar enviar el formulario con la opción vacía seleccionada en las listas desplegables
        WebElement countryDropdown = driver.findElement(By.id("input-country"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByIndex(0); // Seleccionar la primera opción en el menú desplegable "País"

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        
        WebElement zoneDropdown = driver.findElement(By.id("input-zone"));
        Select selectZone = new Select(zoneDropdown);
        selectZone.selectByIndex(0); // Seleccionar la primera opción en el menú desplegable "Región/Estado"

        driver.findElement(By.cssSelector("input.btn.btn-primary[value='Continue']")).click();
    }

    @When("El usuario edita la dirección a:")
    public void el_usuario_edita_la_direccion(Map<String, String> updatedAddress) {
        // Localizar el enlace "Edit" basado en la dirección de la calle
        WebElement editButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[2]/td[2]/a[1]"));
        editButton.click();

        driver.findElement(By.id("input-firstname")).clear();
        driver.findElement(By.id("input-firstname")).sendKeys(updatedAddress.get("Nombre"));
        driver.findElement(By.id("input-lastname")).clear();
        driver.findElement(By.id("input-lastname")).sendKeys(updatedAddress.get("Apellido"));
        driver.findElement(By.id("input-company")).clear();
        driver.findElement(By.id("input-company")).sendKeys(updatedAddress.get("Empresa"));
        driver.findElement(By.id("input-address-1")).clear();
        driver.findElement(By.id("input-address-1")).sendKeys(updatedAddress.get("Dirección 1"));
        driver.findElement(By.id("input-address-2")).clear();
        driver.findElement(By.id("input-address-2")).sendKeys(updatedAddress.get("Dirección 2"));
        driver.findElement(By.id("input-city")).clear();
        driver.findElement(By.id("input-city")).sendKeys(updatedAddress.get("Ciudad"));
        driver.findElement(By.id("input-postcode")).clear();
        driver.findElement(By.id("input-postcode")).sendKeys(updatedAddress.get("Código Postal"));

        // Seleccionar país y región/estado actualizados
        WebElement countryDropdown = driver.findElement(By.id("input-country"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(updatedAddress.get("País"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        WebElement zoneDropdown = driver.findElement(By.id("input-zone"));
        Select selectZone = new Select(zoneDropdown);
        selectZone.selectByVisibleText(updatedAddress.get("Región/Estado"));

        driver.findElement(By.cssSelector("input.btn.btn-primary[value='Continue']")).click();
    }

    @Then("La dirección debería ser actualizada correctamente en la cuenta del usuario")
    public void la_direccion_deberia_ser_actualizada() {
        // Verificar si el mensaje de éxito es visible
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible' and contains(text(), 'Your address has been successfully updated')]")).isDisplayed(),
                "La dirección no se ha actualizado correctamente.");
    }

    @When("El usuario elimina la dirección")
    public void el_usuario_elimina_la_direccion() {
        // Localizar el enlace "Delete" basado en la dirección de la calle
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[2]/td[2]/a[2]")).click();
    }

    @Then("La dirección debería ser eliminada y ya no aparecer en la lista de direcciones")
    public void la_direccion_deberia_ser_eliminada() {
        // Verificar que el mensaje de éxito es visible
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible' and contains(text(), 'Your address has been successfully deleted')]")).isDisplayed(),
                "La dirección no se ha eliminado correctamente.");
    }

    @When("El usuario intenta eliminar la única dirección que tiene")
    public void el_usuario_intenta_eliminar_la_unica_direccion() {
        // Eliminar la única dirección disponible
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[2]/a[2]")).click();
    }

    @Then("El sistema debería mostrar un error indicando que no se puede eliminar la última dirección")
    public void el_sistema_deberia_mostrar_error_no_se_puede_eliminar() {
        // Verificar si el error es visible
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'alert-warning') and contains(text(), 'Warning: You can not delete your default address!')]")).isDisplayed(),
        		"El mensaje de advertencia no se ha mostrado.");
    }
}
