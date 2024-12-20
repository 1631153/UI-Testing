import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features/F2.feature", glue="steps")
public class Run_Feature2 extends AbstractTestNGCucumberTests {

}