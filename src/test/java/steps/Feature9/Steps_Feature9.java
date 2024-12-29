package steps.Feature9;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Steps_Feature9 {

    private WebDriver driver;

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

    // El usuario abre la página de inicio
    @Given("El usuario está en la página de inicio para buscar")
    public void usuarioEnPaginaInicio() {
        // Navega a la página de inicio de OpenCart
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
    }

    // El usuario introduce un producto en la barra de búsqueda
    @When("Introduce {string} en la barra de búsqueda")
    public void introduceEnBarraBusqueda(String producto) {
        // Encuentra el campo de búsqueda y lo limpia antes de ingresar el producto
        WebElement searchBox = findElementByName("search");
        searchBox.clear();
        searchBox.sendKeys(producto);
    }

    // El usuario hace clic en el botón de búsqueda
    @When("Hace clic en el botón de buscar")
    public void clicEnBotonBuscar() {
        // Encuentra y hace clic en el botón de búsqueda
        WebElement searchButton = findElementByCss("button.btn.btn-default.btn-lg");
        searchButton.click();
    }

    // Verifica que aparecen resultados relevantes relacionados con el producto
    @Then("Aparecen resultados relevantes relacionados con {string}")
    public void aparecenResultadosRelevantes(String producto) {
        // Llama a la función común para verificar si se encuentra el producto
        boolean productoEncontrado = verificarElementoConTexto("div#content a", producto);
        
        // Asegura que al menos un enlace contiene el texto del producto
        Assert.assertTrue(productoEncontrado,
                "No se encontró un enlace relacionado con el producto '" + producto + "' en los resultados.");
    }

    // Verifica que no aparecen resultados y muestra el mensaje correspondiente
    @Then("No aparecen resultados y se muestra un mensaje de {string}")
    public void noAparecenResultados(String mensaje) {
        // Llama a la función común para verificar si se encuentra el mensaje esperado
        boolean mensajeEncontrado = verificarElementoConTexto("div#content p", mensaje);
        
        // Asegura que se encontró el mensaje esperado
        Assert.assertTrue(mensajeEncontrado, 
                "No se encontró el mensaje '" + mensaje + "' en los párrafos de la página.");
    }
    
    // Función común para verificar la presencia de texto en elementos
    private boolean verificarElementoConTexto(String selector, String textoEsperado) {
        // Encuentra todos los elementos correspondientes al selector
        List<WebElement> elementos = driver.findElements(By.cssSelector(selector));
        
        // Itera sobre los elementos y verifica si alguno contiene el texto esperado
        for (WebElement elemento : elementos) {
            if (elemento.getText().toLowerCase().contains(textoEsperado.toLowerCase())) {
                return true;  // Retorna true si se encuentra el texto
            }
        }
        return false;  // Retorna false si no se encuentra el texto
    }

    // Métodos auxiliares para encontrar elementos por nombre y por selector CSS
    private WebElement findElementByName(String name) {
        return driver.findElement(By.name(name));
    }

    private WebElement findElementByCss(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }
}


