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

    // Paso 1: El usuario está logeado en su cuenta
    @Given("El usuario está logeado en su cuenta")
    public void el_usuario_esta_logeado_en_su_cuenta(Map<String, String> userDetails) {
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

    // Paso 2: El usuario hace clic en el enlace "Password" para cambiar la contraseña
    @When("El usuario hace clic en el enlace {string}")
    public void el_usuario_hace_clic_en_el_enlace_password(String enlace) {
        // Hace clic en el enlace "Password"
        driver.findElement(By.linkText(enlace)).click();
    }

    // Paso 3: El usuario introduce una nueva contraseña
    @When("El usuario introduce una nueva contraseña:")
    public void el_usuario_introduce_una_nueva_contraseña(Map<String, String> newPasswordDetails) {
        String nuevaContraseña = newPasswordDetails.get("Contraseña");

        // Introduce la nueva contraseña
        driver.findElement(By.id("input-password")).sendKeys(nuevaContraseña);
    }

    // Paso 4: El usuario introduce la confirmación de la nueva contraseña
    @When("El usuario introduce la confirmación de la nueva contraseña:")
    public void el_usuario_introduce_la_confirmacion_de_la_nueva_contraseña(Map<String, String> confirmPasswordDetails) {
        String confirmarContraseña = confirmPasswordDetails.get("Confirmar Contraseña");

        // Introduce la confirmación de la nueva contraseña
        driver.findElement(By.id("input-confirm")).sendKeys(confirmarContraseña);
    }

    // Paso 5: El usuario hace clic en el botón de continuar para guardar la nueva contraseña
    @When("El usuario hace clic en el botón de continuar para guardar la nueva contraseña")
    public void el_usuario_hace_clic_en_el_boton_de_continuar_para_guardar_la_nueva_contraseña() {
        // Hace clic en el botón de continuar para guardar la nueva contraseña
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    // Paso 6: Verificar si el mensaje de error sobre contraseñas no coincidentes aparece
    @Then("El sistema debería mostrar un mensaje de error indicando que las contraseñas no coinciden")
    public void el_sistema_debería_mostrar_un_mensaje_de_error_indicando_que_las_contraseñas_no_coinciden() {
        // Verifica que el mensaje de error se ha mostrado
        boolean mensajeErrorVisible = driver.findElements(By.xpath("//div[contains(@class, 'text-danger')]")).size() > 0;
        Assert.assertTrue(mensajeErrorVisible, "El mensaje de error no se ha mostrado.");
    }

    // Paso 7: La contraseña del usuario debería cambiarse con éxito
    @Then("La contraseña del usuario debería cambiarse con éxito")
    public void la_contraseña_del_usuario_debería_cambiarse_con_éxito() {
        // Verifica que el mensaje de éxito se ha mostrado
        boolean mensajeExitoVisible = driver.findElements(By.xpath("//div[contains(@class, 'alert-success')]")).size() > 0;
        Assert.assertTrue(mensajeExitoVisible, "El mensaje de éxito no se ha mostrado.");
    }
}
