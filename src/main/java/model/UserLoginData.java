package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginData {
    // email пользователя
    private String email;
    // пароль пользователя
    private String password;


    public UserLoginData(UserCreateData userCreateData) {
        this.password = userCreateData.getPassword();
        this.email = userCreateData.getEmail();
    }
}