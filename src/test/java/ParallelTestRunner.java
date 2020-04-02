import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import courgette.api.CucumberOptions;
import courgette.api.junit.Courgette;
import org.junit.runner.RunWith;

// Parallel Test Runner, can be used to specific thread count

@RunWith(Courgette.class)
@CourgetteOptions(
        threads = 1,
        runLevel = CourgetteRunLevel.SCENARIO,
        showTestOutput = true,
        reportTargetDir = "output",
        cucumberOptions = @CucumberOptions(
                features = {"src/test/resources/features"},
                glue= {"StepDefinitions"},
                tags = {"@web","@DemoTest"},
                plugin = {
                        "pretty",
                        "json:output/cucumber/cucumber.json",
                        "html:output/cucumber/cucumber.html"}
        ))
public class ParallelTestRunner {
}
