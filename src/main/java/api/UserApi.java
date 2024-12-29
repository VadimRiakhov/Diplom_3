package api;

import io.qameta.allure.Step;
import model.UserCreateData;
import model.UserLoginData;

import static constants.EndPoints.*;
import static constants.URL.MAIN_PAGE_URL;
import static io.restassured.RestAssured.given;

public class UserApi extends RestApi{
    @Step("Создание пользователя через API")
    public String createUser(UserCreateData user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(USER_CREATE)
                .then()
                .extract().path("accessToken");
    }

    @Step("Авторизация пользователя через API")
    public String loginUser(UserLoginData user){
        return given()
                .baseUri(MAIN_PAGE_URL)
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(USER_LOGIN)
                .then()
                .extract().path("accessToken");
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(String accessToken){
            given()
                    .baseUri(MAIN_PAGE_URL)
                    .spec(requestSpecification())
                    .header("Authorization", accessToken)
                    .when()
                    .delete(USER_DELETE)
                    .then()
                    .log().all();
    }
}
