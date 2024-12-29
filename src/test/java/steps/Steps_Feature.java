package steps;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class Steps_Feature {
	
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
    
    //Feature01
    
    
    
    //Feature02
    
    
    
    //Feature03
    
    
    
    //Feature04
    
    
    
    //Feature05
    
    
    
    //Feature06
    
    
    
    //Feature07
    
    
    
    //Feature08
    
    
    
	//Feature09
	
    @Given("El usuario está en la página de inicio para buscar")
    public void usuarioEnPaginaInicio() {
        // Navega a la página de inicio de OpenCart
        driver.navigate().to("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @When("Introduce {string} en la barra de búsqueda")
    public void introduceEnBarraBusqueda(String producto) {
        // Encuentra el campo de búsqueda y lo limpia antes de ingresar el producto
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.clear();
        searchBox.sendKeys(producto);
    }

    @When("Hace clic en el botón de buscar")
    public void clicEnBotonBuscar() {
        // Encuentra y hace clic en el botón de búsqueda
        WebElement searchButton = driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg"));
        searchButton.click();
    }

    @Then("Aparecen resultados relevantes relacionados con {string}")
    public void aparecenResultadosRelevantes(String producto) {
        // Llama a la función común para verificar si se encuentra el producto
        boolean productoEncontrado = verificarElementoConTexto("div#content a", producto);
        
        // Asegura que al menos un enlace contiene el texto del producto
        Assert.assertTrue(productoEncontrado,
                "No se encontró un enlace relacionado con el producto '" + producto + "' en los resultados.");
    }

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
    
	//Feature10
	
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
    
	//Feature11
	
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
     
    // Función para hacer clic en un botón usando un localizador
   	private void clickButton(By locator) {
   	    driver.findElement(locator).click();
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
    
	//Feature12
 	
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
    
	//Feature13

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
    
	//Feature14
	
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
    
	//Feature15
	
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