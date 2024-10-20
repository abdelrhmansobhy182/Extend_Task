package data_helper;

import apis.UserAPIs;
import helpers.UserHelper;
import io.restassured.response.Response;
import models.User;

public class UserDataHelper {

    public static User createUser(){
        User userData = UserHelper.generateUserData();
        Response response = UserAPIs.createUser(userData);
        return response.body().as(User.class);
    }
}
