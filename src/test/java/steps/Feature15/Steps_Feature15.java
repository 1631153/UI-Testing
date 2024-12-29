package steps.Feature15;
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
import java.util.List;
import java.util.Map;

public class Steps_Feature15 {

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

    // Rellena el formulario con los campos proporcionados
    private void fillForm(Map<String, String> fields) {
        fields.forEach((key, value) -> {
            WebElement element = driver.findElement(By.id(key));

            // Verifica si el campo es un <select>
            if (element.getTagName().equals("select")) {
                new Select(element).selectByVisibleText(value);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            } else {
                element.clear();
                element.sendKeys(value);
            }
        });
    }

    private void clickButton(By locator) {
        driver.findElement(locator).click();
    }

    @Given("El usuario está logeado en su cuenta deseos")
    public void el_usuario_esta_logeado_en_su_cuenta_deseos(Map<String, String> userDetails) {
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
        clickButton(By.cssSelector("a[title='My Account']"));
        clickButton(By.linkText("Login"));
        fillForm(userDetails);
        clickButton(By.cssSelector("input.btn.btn-primary[value='Login']"));
    }

    @Given("El usuario está en la página de detalles de un producto")
    public void usuarioEnPaginaDeDetallesDelProducto() {
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=product/product&path=57&product_id=47");
    }

    @When("El usuario hace clic en el botón {string} para anadir el producto a lista de deseos")
    public void usuarioHaceClicEnBoton(String boton) {
        clickButton(By.cssSelector("button[data-original-title='" + boton + "']"));
    }

    @Then("El sistema debería mostrar una notificación confirmando que el producto {string} ha sido agregado")
    public void sistemaMuestraNotificacionProductoAgregado(String producto) {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement notification = driver.findElement(By.cssSelector("div.alert.alert-success"));
        Assert.assertTrue(notification.getText().contains("Success: You have added " + producto + " to your wish list!"));
    }

    @When("El usuario hace clic en el botón {string} para ir a la lista de deseos")
    public void usuarioHaceClicEnBotonIrAListaDeseos(String boton) {
        clickButton(By.id(boton));
    }
    
    @Then("La lista debería mostrar el producto {string} previamente agregado")
    public void listaDeDeseosMuestraProducto(String nombreProducto) {
        List<WebElement> enlaces = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//a[text()='" + nombreProducto + "']"));
        Assert.assertFalse(enlaces.isEmpty(), "El producto " + nombreProducto + " no se encuentra en la lista de deseos.");
    }

    @Then("La lista de deseos no debería contener duplicados de {string}")
    public void listaDeDeseosSinDuplicados(String nombreProducto) {
        List<WebElement> productRows = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//a[text()='" + nombreProducto + "']"));
        Assert.assertTrue(productRows.size() == 1, "Hay productos duplicados en la lista de deseos.");
    }
    
    @Given("El usuario está en la página de detalles de un producto diferente")
    public void usuarioEnPaginaDeDetallesDeUnProductoDiferente() {
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=product/product&path=57&product_id=49");
    }
    
    @When("El usuario hace clic en el botón {string} junto al producto {string}")
    public void usuarioHaceClicEnEliminar(String boton, String producto) {
        // Encuentra todas las filas de la tabla
        List<WebElement> filas = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tr"));
        
        for (WebElement fila : filas) {
            // Verifica si la fila contiene el nombre del producto
            if (fila.getText().contains(producto)) {
                // Encuentra el botón de eliminar en esta fila y hace clic en él
                WebElement botonEliminar = fila.findElement(By.xpath(".//a[contains(@data-original-title, '" + boton + "')]"));
                botonEliminar.click();
                break; // Sale del bucle después de hacer clic
            }
        }
    }
    
    @Then("El producto {string} debería ser eliminado de la lista de deseos")
    public void productoEliminadoDeListaDeseos(String producto) {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    	List<WebElement> enlaces = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//a[text()='" + producto + "']"));
        Assert.assertTrue(enlaces.isEmpty(), "El producto " + producto + " todavia se encuentra en la lista de deseos.");
    }

    @Then("El sistema debería mostrar una notificación confirmando que el producto ha sido eliminado")
    public void sistemaMuestraNotificacionProductoEliminado() {
        WebElement notification = driver.findElement(By.cssSelector("div.alert.alert-success"));
        Assert.assertTrue(notification.getText().contains("Success: You have modified your wish list!"));
    }

    @Then("El sistema debería mostrar un mensaje indicando {string} cuando no haya productos en la lista")
    public void sistemaMuestraMensajeListaVacia(String mensaje) {
        WebElement emptyMessage = driver.findElement(By.cssSelector("div#content p"));
        Assert.assertTrue(emptyMessage.getText().contains(mensaje), "El mensaje de lista vacía no es correcto.");
    }
}