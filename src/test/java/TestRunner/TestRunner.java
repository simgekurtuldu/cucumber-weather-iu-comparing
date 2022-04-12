package TestRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty" ,"html:target/cucumber",
        "json:target/MyReports/report.json",
        "junit:target/MyReports/report.xml"},
        features="src/test/resources/Features/WeatherAPI.feature",
        glue={"StepDefination"},
        publish = true,
        dryRun = false,
        monochrome = false
)
public class TestRunner {
}
