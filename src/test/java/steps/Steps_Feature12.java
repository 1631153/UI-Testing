package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

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

    // Paso 1: El usuario está logeado en su cuenta
    @Given("El usuario está logeado en su cuenta 2")
    public void el_usuario_esta_logeado_en_su_cuenta2(Map<String, String> userDetails) {
        // Obtenemos los datos de login
        String email = userDetails.get("Email");
        String password = userDetails.get("Password");

        // Navega a la página de inicio
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");

        // Hace clic en 'Mi cuenta'
        driver.findElement(By.cssSelector("a[title='My Account']")).click();

        // Hace clic en 'Login'
        driver.findElement(By.linkText("Login")).click();

        // Introduce el email y la contraseña
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);

        // Hace clic en el botón de login
        driver.findElement(By.cssSelector("input.btn.btn-primary[value='Login']")).click();
    }

    // Paso 2: El usuario hace clic en el enlace 'Edit Account'
    @When("El usuario hace clic en el enlace \"Edit Account\" 2")
    public void el_usuario_hace_clic_en_el_enlace_edit_account2() {
        // Utiliza partialLinkText para buscar un fragmento del texto y hacer clic en el enlace
        driver.findElement(By.partialLinkText("Edit Account")).click();
    }

    // Paso 3: El usuario actualiza su información personal con datos
    @When("El usuario actualiza su información personal con")
    public void el_usuario_actualiza_su_informacion_personal(Map<String, String> userDetails) {
        String nombre = userDetails.get("Nombre");
        String apellido = userDetails.get("Apellido");
        String correo = userDetails.get("Correo");
        String telefono = userDetails.get("Telefono");

        // Actualiza los campos de nombre, apellido, correo y teléfono
        driver.findElement(By.id("input-firstname")).clear();
        driver.findElement(By.id("input-firstname")).sendKeys(nombre);

        driver.findElement(By.id("input-lastname")).clear();
        driver.findElement(By.id("input-lastname")).sendKeys(apellido);

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(correo);

        driver.findElement(By.id("input-telephone")).clear();
        driver.findElement(By.id("input-telephone")).sendKeys(telefono);

        // Hacer clic en el botón de "Continue" para guardar la nueva información
        driver.findElement(By.cssSelector("div.pull-right input[type='submit']")).click();
    }

    // Paso 4: Verificar el mensaje de éxito
    @Then("El mensaje de éxito debería aparecer")
    public void el_mensaje_de_exito_deberia_aparecer() {
        // Verifica que el mensaje de éxito se ha mostrado
        boolean mensajeExitoVisible = driver.findElements(By.xpath("//div[contains(@class, 'alert-success')]")).size() > 0;
        Assert.assertTrue(mensajeExitoVisible, "El mensaje de éxito no se ha mostrado.");
    }

    // Paso 5: La información personal del usuario debería actualizarse correctamente
    @Then("La información personal del usuario debería actualizarse correctamente")
    public void la_informacion_personal_del_usuario_deberia_actualizarse_correctamente() {
        // Verifica que los campos se han actualizado correctamente usando .getDomAttribute("value")
        String nombre = driver.findElement(By.id("input-firstname")).getDomAttribute("value");
        String apellido = driver.findElement(By.id("input-lastname")).getDomAttribute("value");
        String correo = driver.findElement(By.id("input-email")).getDomAttribute("value");
        String telefono = driver.findElement(By.id("input-telephone")).getDomAttribute("value");

        Assert.assertEquals(nombre, "Javier");
        Assert.assertEquals(apellido, "González");
        Assert.assertEquals(correo, "david.javi@test.com");
        Assert.assertEquals(telefono, "987654321");
    }

    // Paso 6: La información personal del usuario debería restaurarse correctamente
    @Then("La información personal del usuario debería restaurarse correctamente")
    public void la_informacion_personal_del_usuario_deberia_restaurarse_correctamente() {
        // Verifica que los campos se han restaurado correctamente a los valores originales usando .getDomAttribute("value")
        String nombreRestaurado = driver.findElement(By.id("input-firstname")).getDomAttribute("value");
        String apellidoRestaurado = driver.findElement(By.id("input-lastname")).getDomAttribute("value");
        String correoRestaurado = driver.findElement(By.id("input-email")).getDomAttribute("value");
        String telefonoRestaurado = driver.findElement(By.id("input-telephone")).getDomAttribute("value");

        Assert.assertEquals(nombreRestaurado, "Javi");
        Assert.assertEquals(apellidoRestaurado, "David");
        Assert.assertEquals(correoRestaurado, "javi.david@test.com");
        Assert.assertEquals(telefonoRestaurado, "123456789");
    }
    
    // Paso 7: El usuario deja los campos vacíos y hace clic en "Continue"
    @When("El usuario deja los campos vacíos y hace clic en \"Continue\"")
    public void el_usuario_deja_los_campos_vacios_y_hace_clic_en_continue() {
        // Limpiar los campos de nombre, apellido, correo y teléfono
        driver.findElement(By.id("input-firstname")).clear();
        driver.findElement(By.id("input-lastname")).clear();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-telephone")).clear();

        // Hacer clic en el botón de "Continue" para intentar guardar la nueva información, aunque los campos estén vacíos
        driver.findElement(By.cssSelector("div.pull-right input[type='submit']")).click();
    }

    // Paso 8: Verificar los mensajes de error correspondientes
    @Then("El sistema debería mostrar los mensajes de error correspondientes")
    public void el_sistema_deberia_mostrar_mensajes_de_error() {
    	// Verificar que los mensajes de error se muestran para cada campo
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'text-danger') and contains(text(), 'First Name must be between 1 and 32 characters!')]")).isDisplayed(), "El mensaje de error para el campo 'First Name' no se muestra.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'text-danger') and contains(text(), 'Last Name must be between 1 and 32 characters!')]")).isDisplayed(), "El mensaje de error para el campo 'Last Name' no se muestra.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'text-danger') and contains(text(), 'E-Mail Address does not appear to be valid!')]")).isDisplayed(), "El mensaje de error para el campo 'E-Mail' no se muestra.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'text-danger') and contains(text(), 'Telephone must be between 3 and 32 characters!')]")).isDisplayed(), "El mensaje de error para el campo 'Telephone' no se muestra.");
    }
}
