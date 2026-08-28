package utils;

import com.github.javafaker.Faker;
import objects.User;

public class UserUtils {

    private static final Faker faker = new Faker();

    private UserUtils() {
    }

    public static User generateRandomUser() {

        return new User(

                faker.name().firstName(),

                faker.name().lastName(),

                faker.internet().emailAddress(),

                faker.internet().password(8,15,true,true)

        );

    }

}