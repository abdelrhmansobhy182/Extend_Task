package tests.User;

import apis.UserAPIs;
import io.restassured.response.Response;
import models.LoginRequestResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_data.TestDataProviders;
import tests.BaseTest;
import com.aventstack.extentreports.ExtentTest;

public class LoginTests extends BaseTest {

    @Test(dataProvider = "loginDataProvider", dataProviderClass = TestDataProviders.class)
    public void testUserCanLoginSuccessfully(String email, String password, boolean isValid) {
        ExtentTest test = extent.createTest("testUserCanLoginSuccessfully", "Verify user login functionality");
        test.info("Testing login with Email: " + email + ", Password: " + password);
        LoginRequestResponse loginRequest = new LoginRequestResponse();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        Response response = UserAPIs.login(loginRequest);

        try {
            if (isValid) {
                Assert.assertEquals(response.statusCode(), 200);
                test.pass("Status Code is 200 as expected.");
                LoginRequestResponse loginResponse = response.body().as(LoginRequestResponse.class);
                Assert.assertNotNull(loginResponse.getToken());
                test.pass("User logged in successfully. Token: " + loginResponse.getToken());
            } else {
                Assert.assertEquals(response.statusCode(), 400);
                test.pass("Status Code is 400 as expected.");

                LoginRequestResponse loginResponse = response.body().as(LoginRequestResponse.class);
                Assert.assertNull(loginResponse.getToken());
                test.pass("Login failed as expected. No token received.");
            }
        } catch (AssertionError e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }
}
