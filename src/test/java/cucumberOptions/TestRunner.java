package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
(features = "src/test/java/features", plugin = {"pretty","json:target/jsonReports/cucumber-reports.json","html:target/cucumber-reports.html"} , glue ={"StepDefinitions"},
tags = "@DeletePlace")

public class TestRunner {

}
