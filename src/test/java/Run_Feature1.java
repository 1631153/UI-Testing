import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features/F1.feature", glue="steps")
public class Run_Feature1 extends AbstractTestNGCucumberTests {

}