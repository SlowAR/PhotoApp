package by.test.photoapptest.ui.signin;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;
import by.test.photoapptest.ui.model.user.SignUserDtoIn;

public class SignInPageFragment extends Fragment implements View.OnClickListener {

    @Inject
    SignInPresenter mPresenter;

    private by.test.photoapptest.ui.signin.FragmentSigninPageBinding mBinding;
    private SignInListener mListener;

    public SignInPageFragment() {
        // Required empty public constructor
    }

    public static SignInPageFragment newInstance(SignInListener listener) {
        SignInPageFragment fragment = new SignInPageFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin_page, container, false);
        mBinding.signin.setOnClickListener(this);
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
            login = mBinding.loginSignin.getText().toString();
            password = mBinding.passwordSignin.getText().toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        SignUserDtoIn user = new SignUserDtoIn(login, password);
        mPresenter.signInUser(user);
    }
}