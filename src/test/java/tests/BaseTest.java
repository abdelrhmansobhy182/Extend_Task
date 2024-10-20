package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    static Dotenv dotenv = Dotenv.load();
    static String GRAPHQL_ENDPOINT = dotenv.get("GRAPHQL_ENDPOINT");
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public static void setupENV() {
        RestAssured.baseURI = GRAPHQL_ENDPOINT;}

    @BeforeClass
    public void setupExtent() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports/TestReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "API Testing");
            extent.setSystemInfo("Tester", "Your Name");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("API Test Report");
            spark.config().setReportName("API Testing Report");
        }
    }


    protected ExtentTest createTest(String testName, String description) {
        test = extent.createTest(testName, description);
        return test;
    }

    @AfterClass
    public void tearDownExtent() {
        extent.flush();
    }
}
