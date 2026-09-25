package com.thinktimetechno.Shrido.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.thinktimetechno.hooks.CucumberListener;
import com.thinktimetechno.utils.EmailSendUtils;
import com.thinktimetechno.utils.FailedApiTracker;
import com.thinktimetechno.utils.ZipUtils;

@Test
@CucumberOptions(
        features = "src/test/resources/features/ShridoFeatures",
        glue = {"com.thinktimetechno.projects.website.Shrido1.stepdefinitions",
                "com.thinktimetechno.hooks"},
        plugin = {"com.thinktimetechno.hooks.CucumberListener",
                "pretty",
                "html:target/cucumber-reports/LoginCMSTestRunner.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        , monochrome = true,
        		tags = "@Shrido"
	
)

public class ShridoTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("================ AFTER SUITE ================");
        ZipUtils.zipReportFolder();
        EmailSendUtils.sendEmail(
        	    CucumberListener.count_totalTCs,
        	    CucumberListener.count_passedTCs,
        	    FailedApiTracker.getFailedApis() // ✅ Dynamically passed failed API list
        	);
        }
}
