package by.test.photoapptest.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class SignUserDtoIn {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    public SignUserDtoIn() {
    }

    public SignUserDtoIn(String login, String password) {
        this.login = login;
        this.password = password;
    }


}
