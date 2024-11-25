package com.automation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = {
                "src/test/resources/features/EndToEndTest.feature",
                "src/test/resources/features/DataDrivenTest.feature"
        },
        glue = "com.automation.steps",
        plugin = {
                "pretty",
                "json:target/cucumber-report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
@RunWith(Cucumber.class)
public class TestRunner {
}
