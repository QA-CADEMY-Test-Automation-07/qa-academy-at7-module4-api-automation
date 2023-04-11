package com.qaacademy.module4.automation.trello.api.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = "com.qaacademy.module4.automation.trello.api",
        features = "src/test/resources/features/"
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}
