import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	features = {
			"src/test/java/features/F02.feature",
			"src/test/java/features/F03.feature",
			"src/test/java/features/F04.feature",
			"src/test/java/features/F05.feature",
			"src/test/java/features/F06.feature",
			"src/test/java/features/F07.feature",
			"src/test/java/features/F08.feature",
			"src/test/java/features/F09.feature",
			"src/test/java/features/F10.feature",
			"src/test/java/features/F11.feature",
			"src/test/java/features/F12.feature",
			"src/test/java/features/F13.feature",
			"src/test/java/features/F14.feature",
			"src/test/java/features/F15.feature",
			"src/test/java/features/F16.feature",
			"src/test/java/features/F17.feature"
	},  								// Directorio que contiene todas las features
    glue = "steps"                      // Paquete con los step definitions
)
public class RunAllFeatures extends AbstractTestNGCucumberTests {
}