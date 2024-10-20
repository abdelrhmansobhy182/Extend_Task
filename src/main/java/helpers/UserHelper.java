package helpers;

import com.github.javafaker.Faker;
import models.User;

public class UserHelper {

    static Faker faker = new Faker();

    public static String generateName(){
        return faker.name().firstName();
    }

    public static String generateJob(){
        return faker.job().title();
    }

    public static String generateEmail(){
        return faker.internet().emailAddress();
    }

    public static String generateAvatar(){
        return faker.internet().avatar();
    }

    public static User generateUserData(){
        return new User(
                generateName(),
                generateName(),
                generateEmail(),
                generateJob(),
                generateAvatar()
        );
    }
}
