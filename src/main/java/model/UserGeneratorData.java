package model;

import io.qameta.allure.Step;
import net.datafaker.Faker;

public class UserGeneratorData {

    private static Faker faker = new Faker();

    @Step("Генерация рандомного пользователя")
    public static UserCreateData getRandomUser(){
        String name = faker.name().firstName();
        String password = faker.internet().password(6, 10, true);
        String email = faker.internet().emailAddress();
        return new UserCreateData(name, password, email);
    }

}
