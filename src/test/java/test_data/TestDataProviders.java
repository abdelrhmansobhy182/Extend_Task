package test_data;

import org.testng.annotations.DataProvider;

public class TestDataProviders {

    @DataProvider(name = "loginDataProvider")
    public static Object[][] loginDataProvider() {
        return new Object[][] {
                {"eve.holt@reqres.in", "cityslicka", true},
                {"eve.holt@reqres.in", "wrongpassword", false}
        };
    }
}
