import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/features/F12.feature",  // Ruta al archivo de características (features)
    glue = "steps.Feature12"  // Paquete donde se encuentran los pasos definidos (steps)
)
public class Run_Feature12 extends AbstractTestNGCucumberTests {

    // Esta clase es utilizada para ejecutar los escenarios definidos en el archivo F12.feature con Cucumber y TestNG.
}
