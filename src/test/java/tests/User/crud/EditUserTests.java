package tests.User.crud;

import apis.UserAPIs;
import data_helper.UserDataHelper;
import helpers.UserHelper;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

public class EditUserTests extends BaseTest {
    private User user;

    @BeforeMethod
    private void createTestData() {
        user = UserDataHelper.createUser();
    }

    @Test
    public void testEditUser() {
        test = createTest("testEditUser", "Verify that a user can be edited successfully");

        test.info("Created User Data: " + user.getEmail());

        String newEmail = UserHelper.generateEmail();
        user.setEmail(newEmail);
        test.info("Updated User Email: " + newEmail);

        Response response = UserAPIs.updateUser(user.getId(), user);
        test.info("API Response: " + response.asString());

        User returnedUser = response.body().as(User.class);

        try {
            Assert.assertEquals(returnedUser.getEmail(), newEmail);
            test.pass("Email updated successfully: " + newEmail);

            Assert.assertEquals(response.statusCode(), 201);
            test.pass("Status Code is 201 as expected.");
        } catch (AssertionError e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }
}
