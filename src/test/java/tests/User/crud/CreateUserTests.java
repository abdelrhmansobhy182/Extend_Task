package tests.User.crud;

import apis.UserAPIs;
import helpers.UserHelper;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class CreateUserTests extends BaseTest {

    @Test
    public void testTheCreationOfNewUser() {
        test = createTest("testTheCreationOfNewUser", "Verify the creation of a new user");

        User user = UserHelper.generateUserData();
        test.info("Generated User Data: " + user.getEmail());

        Response response = UserAPIs.createUser(user);
        test.info("API Response: " + response.asString());

        User returnedUser = response.body().as(User.class);

        try {
            Assert.assertEquals(response.statusCode(), 201);
            test.pass("Status Code is 201 as expected.");

            Assert.assertEquals(user.getEmail(), returnedUser.getEmail());
            test.pass("Email is matched: " + user.getEmail());

        } catch (AssertionError e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }
}
