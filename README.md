# Test de Interfaz Web con Selenium y Cucumber

## Descripción
Este proyecto corresponde a la segunda práctica de la asignatura **Test i Qualitat del Software**, centrada en el **testing de una página web** utilizando **Selenium WebDriver** y **Cucumber con Java**.  

La página web seleccionada para realizar las pruebas es:   [OpenCart Demo](https://opencart.abstracta.us/index.php?route=common/home)

El objetivo es automatizar **UI tests (Black-Box Testing)** sobre las principales funcionalidades de la web, como por ejemplo:
- Navegación por la web.
- Interacción con los formularios.
- Proceso de compra de productos.
- Verificación de contenido mostrado en la interfaz.

## Tecnologías utilizadas
- **Java** (lenguaje principal).
- **Selenium WebDriver** (automatización de la interacción con la web).
- **Cucumber** (definición de test cases en Gherkin).
- **JUnit** (ejecutor de pruebas).
- **Maven** (gestor de dependencias).

El programa **Eclipse IDE** ha sido el seleccionado como la plataforma software para el desarrollo del proyecto.

## Estructura del proyecto
    
    ├──📂 drivers
        └── 📥 Archivos '.exe' para chrome y firefox
    ├──📂 src/test/java
        ├── 📂 features
            └── 📄 Archivos '.feature' con escenarios en Gherkin
        ├── 📂 steps
            └── 📄 Implementación de todos steps de Cucumber
        ├── 📄 Archivo '.java' para ejecutar todos los features
        └── 📄 Archivo '.java' para ejecutar los features por separado

## Ejecución de los tests
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/usuario/practica2-testing.git
   cd practica2-testing
2. Importar el proyecto en Eclipse IDE:
    ```bash
    Abrir Eclipse
    File > Import > Existing Maven Projects
    Seleccionar la carpeta del repositorio clonado

3. Ejecutar los tests:
    ```bash
    Abrir RunAllFeatures.java (ubicada en src/test/java)
    Click derecho sobre el archivo → Run As > JUnit Test

## Informe

En el repositorio también se incluye un informe (informe.pdf) donde se documentan:
- Los test scenarios desarrollados.
- Las explicaciones de los step definitions más reutilizados.
- Conclusiones de la práctica.

## Autores
- Javier Comes Tello
- David Bonilla Medina
