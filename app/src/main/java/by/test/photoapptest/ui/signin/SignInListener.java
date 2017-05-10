package by.test.photoapptest.ui.signin;

import android.support.annotation.NonNull;

import by.test.photoapptest.model.user.SignUserOutDto;

/**
 * Created by SlowAR on 05.05.2017.
 */

public interface SignInListener {

    void onSignInClick(@NonNull SignUserOutDto user);

    void onSignUpClick(@NonNull SignUserOutDto user);
}
