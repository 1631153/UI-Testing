package steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps_Feature10 {

    private WebDriver driver;

    // Configuración antes de cada escenario
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Limpieza después de cada escenario
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("El usuario está en la categoria de Desktops")
    public void elUsuarioEstaEnLaCategoriaDeDesktops() {
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=product/category&path=20");
    }

    @When("El usuario selecciona {string} en el menú de ordenación")
    public void elUsuarioSeleccionaEnElMenuDeOrdenacion(String criterio) {
        // Encuentra el menú desplegable
        WebElement sortDropdown = driver.findElement(By.id("input-sort"));

        // Usa la clase Select para seleccionar la opción
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(criterio);
    }

    @Then("Los productos deberían mostrarse ordenados por precio en orden ascendente")
    public void losProductosDeberianMostrarseOrdenadosPorPrecioAscendente() {
        verificarOrdenDePrecios(true);
    }

    @Then("Los productos deberían mostrarse ordenados por precio en orden descendente")
    public void losProductosDeberianMostrarseOrdenadosPorPrecioDescendente() {
        verificarOrdenDePrecios(false);
    }

    @Then("Los productos deberían mostrarse ordenados alfabéticamente de la A a la Z")
    public void losProductosDeberianMostrarseOrdenadosAlfabeticamenteAZ() {
        verificarOrdenAlfabetico(true);
    }

    @Then("Los productos deberían mostrarse ordenados alfabéticamente de la Z a la A")
    public void losProductosDeberianMostrarseOrdenadosAlfabeticamenteZA() {
        verificarOrdenAlfabetico(false);
    }

    @Then("Los productos deberían mostrarse con las calificaciones más altas primero")
    public void losProductosDeberianMostrarseConLasCalificacionesMasAltasPrimero() {
        verificarOrdenDeCalificaciones(true);
    }
    
    @Then("Los productos deberían mostrarse con las calificaciones más bajas primero")
    public void losProductosDeberianMostrarseConLasCalificacionesMasBajasPrimero() {
        verificarOrdenDeCalificaciones(false);
    }

    // Métodos auxiliares para verificar orden

    private void verificarOrdenDePrecios(boolean ascendente) {
        // Encuentra los precios de los productos en la página
        List<WebElement> preciosElementos = driver.findElements(By.cssSelector(".price-tax"));

        // Extrae los precios como valores numéricos
        List<Double> precios = new ArrayList<>();
        for (WebElement elemento : preciosElementos) {
            String precioTexto = elemento.getText().replaceAll("[^\\d.]", ""); // Limpia texto
            if (!precioTexto.isEmpty()) {
                precios.add(Double.parseDouble(precioTexto));
            }
        }

        // Crea una copia para compararla con la ordenación
        List<Double> preciosOrdenados = new ArrayList<>(precios);
        if (ascendente) {
            Collections.sort(preciosOrdenados); // Ordena de menor a mayor
        } else {
            preciosOrdenados.sort(Collections.reverseOrder()); // Ordena de mayor a menor
        }

        // Compara las listas
        Assert.assertEquals(precios, preciosOrdenados, "Los precios no están ordenados correctamente.");
    }

    private void verificarOrdenAlfabetico(boolean ascendente) {
        // Encuentra los nombres de los productos en la página
        List<WebElement> nombresElementos = driver.findElements(By.cssSelector(".caption h4 a"));

        // Extrae los nombres
        List<String> nombres = new ArrayList<>();
        for (WebElement elemento : nombresElementos) {
            nombres.add(elemento.getText().toLowerCase().trim());
        }

        // Crea una copia para compararla con la ordenación
        List<String> nombresOrdenados = new ArrayList<>(nombres);
        if (ascendente) {
            Collections.sort(nombresOrdenados); // Ordena de A a Z
        } else {
            nombresOrdenados.sort(Collections.reverseOrder()); // Ordena de Z a A
        }

        // Compara las listas
        Assert.assertEquals(nombres, nombresOrdenados, "Los nombres no están ordenados correctamente.");
    }

    private void verificarOrdenDeCalificaciones(boolean descendente) {
        // Encuentra las calificaciones de los productos en la página
        List<WebElement> calificacionesElementos = driver.findElements(By.cssSelector(".rating"));

        // Extrae las calificaciones (suponiendo que cada estrella tiene un atributo o clase)
        List<Integer> calificaciones = new ArrayList<>();
        for (WebElement elemento : calificacionesElementos) {
            int estrellas = elemento.findElements(By.cssSelector(".fa-star")).size(); // Cuenta estrellas
            calificaciones.add(estrellas);
        }

        // Crea una copia para compararla con la ordenación
        List<Integer> calificacionesOrdenadas = new ArrayList<>(calificaciones);
        if (descendente) {
            calificacionesOrdenadas.sort(Collections.reverseOrder()); // Ordena de mayor a menor
        } else {
            Collections.sort(calificacionesOrdenadas); // Ordena de menor a mayor
        }

        // Compara las listas
        Assert.assertEquals(calificaciones, calificacionesOrdenadas, "Las calificaciones no están ordenadas correctamente.");
    }
}