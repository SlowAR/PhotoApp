package by.test.photoapptest.ui.signin;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;
import by.test.photoapptest.model.user.SignUserDtoIn;
import by.test.ui.signin.FragmentSignupPageBinding;

public class SignUpPageFragment extends Fragment implements View.OnClickListener {

    @Inject
    SignUpPresenter mPresenter;

    private FragmentSignupPageBinding mBinding;
    private SignInListener mListener;

    public SignUpPageFragment() {
        // Required empty public constructor
    }

    public static SignUpPageFragment newInstance(SignInListener listener) {
        SignUpPageFragment fragment = new SignUpPageFragment();
        fragment.setListener(listener);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup_page, container, false);
        mBinding.signup.setOnClickListener(this);
        return mBinding.getRoot();
    }

    private void setListener(SignInListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        String login = null;
        String password = null;

        try {
            login = mBinding.loginSignup.getText().toString();
            password = mBinding.passwordSignup.getText().toString();
            String confirmPassword = mBinding.passwordConfirmSignup.getText().toString();
            if (!password.equals(confirmPassword)) {
                Toast.makeText(getContext(), "Password and Confirm Password do not match!", Toast.LENGTH_LONG).show();
                return;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        SignUserDtoIn user = new SignUserDtoIn(login, password);
        mPresenter.signUpUser(user);
    }
}