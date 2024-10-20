package tests.User.crud;

import apis.UserAPIs;
import helpers.APIsHelper;
import helpers.UserHelper;
import io.restassured.response.Response;
import models.UserListResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ListUsersTests extends BaseTest {

    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        test = extent.createTest("testTheUserListIsReturnedCorrectly", "Verify that user list is returned correctly");
    }

    @Test
    public void testTheUserListIsReturnedCorrectly() throws IOException {
        Random random = new Random();
        int pageNumber = random.nextInt(2) + 1;

        test.info("Requesting user list for page: " + pageNumber);
        Response response = UserAPIs.getUsers(pageNumber);

        Assert.assertEquals(response.statusCode(), 200);
        test.pass("Status Code is 200 as expected.");

        UserListResponse userListResponse = APIsHelper.mapResponse(response, UserListResponse.class);
        Assert.assertEquals(userListResponse.getData().size(), userListResponse.getPer_page(), "Data size does not match the expected per_page");
        Assert.assertEquals(pageNumber, userListResponse.getPage(), "Page number does not match the requested page");
        test.pass("User list validated successfully for page: " + pageNumber);
    }
}
