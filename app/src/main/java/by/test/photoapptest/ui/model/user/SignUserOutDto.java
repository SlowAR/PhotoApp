package by.test.photoapptest.ui.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.Serializable;
import java.util.List;

import by.test.photoapptest.ui.model.photo.ImageDtoOut;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class SignUserOutDto implements Serializable {

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("login")
    private String login;

    @JsonProperty("token")
    private String token;

    public SignUserOutDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
