import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features/F3.feature", glue="steps")
public class Run_Scenario1 extends AbstractTestNGCucumberTests {

}
