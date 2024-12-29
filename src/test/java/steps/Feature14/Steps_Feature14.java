package steps.Feature14;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Steps_Feature14 {

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

	@Given("El usuario está en la página del producto que desea valorar")
	public void el_usuario_esta_en_la_pagina_del_producto_que_desea_valorar() {
	    driver.navigate().to("https://opencart.abstracta.us/index.php?route=product/product&product_id=40");
	}
	
	@When("El usuario accede a la sección de valoraciones")
	public void el_usuario_accede_a_la_seccion_de_valoraciones() {
	    WebElement reviewsTab = driver.findElement(By.cssSelector("a[href='#tab-review']"));
	    reviewsTab.click();
	}

	@When("El usuario escribe su nombre como {string}")
	public void el_usuario_escribe_su_nombre_como(String nombre) {
	    WebElement nombreField = driver.findElement(By.id("input-name"));
	    nombreField.clear();
	    nombreField.sendKeys(nombre);
	}

	@When("El usuario escribe un comentario {string}")
	public void el_usuario_escribe_un_comentario(String comentario) {
	    WebElement comentarioField = driver.findElement(By.id("input-review"));
	    comentarioField.clear();
	    comentarioField.sendKeys(comentario);
	}

	@When("El usuario selecciona una calificación de {int} estrellas")
	public void el_usuario_selecciona_una_calificacion_de_estrellas(int estrellas) {
	    WebElement estrellaOption = driver.findElement(By.cssSelector("input[name='rating'][value='" + estrellas + "']"));
	    estrellaOption.click();
	}
	
	@When("El usuario hace clic en el botón {string}")
	public void el_usuario_hace_clic_en_el_boton(String boton) {
	    WebElement continueButton = driver.findElement(By.id("button-review"));
	    continueButton.click();
	}
	
	@Then("Debería aparecer un mensaje de éxito que dice {string}")
	public void deberia_aparecer_un_mensaje_de_exito_que_dice(String mensajeExito) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	    WebElement mensaje = driver.findElement(By.cssSelector(".alert-success"));
	    Assert.assertTrue(mensaje.getText().contains(mensajeExito), "El mensaje de éxito no se mostró.");
	}
	
	@Then("Debería aparecer un mensaje de error {string}")
	public void deberia_aparecer_un_mensaje_de_error(String mensajeError) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	    WebElement mensaje = driver.findElement(By.cssSelector(".alert-danger"));
	    Assert.assertTrue(mensaje.getText().contains(mensajeError), "El mensaje de error no se mostró.");
	}	
}
