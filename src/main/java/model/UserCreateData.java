package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateData {
    // имя пользователя
    private String name;
    // пароль пользователя
    private String password;
    // email пользователя
    private String email;

}
