package by.test.photoapptest.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SlowAR on 11.05.2017.
 */

public class SignUserResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private SignUserOutDto user;

    public SignUserResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SignUserOutDto getUser() {
        return user;
    }

    public void setUser(SignUserOutDto user) {
        this.user = user;
    }
}
