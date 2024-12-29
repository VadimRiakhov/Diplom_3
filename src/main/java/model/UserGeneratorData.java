package model;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGeneratorData {
    @Step("Генерация рандомного пользователя")
    public static UserCreateData getRandomUser(){
        String name = RandomStringUtils.randomAlphabetic(8);
        String password = RandomStringUtils.randomAlphabetic(8);
        String email = RandomStringUtils.randomAlphabetic(8)+"@yandex.ru";
        return new UserCreateData(name, password, email);
    }

}
