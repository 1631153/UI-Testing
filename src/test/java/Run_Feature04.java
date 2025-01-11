import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/features/F04.feature",  // Ruta al archivo de características (features)
    glue = "steps"  // Paquete donde se encuentran los pasos definidos (steps)
)
public class Run_Feature04 extends AbstractTestNGCucumberTests {

    // Esta clase es utilizada para ejecutar los escenarios definidos en el archivo F4.feature con Cucumber y TestNG.
}
