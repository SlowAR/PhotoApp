package by.test.photoapptest.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;
import by.test.photoapptest.model.user.SignUserDtoIn;
import by.test.photoapptest.model.user.SignUserOutDto;
import by.test.photoapptest.ui.photos.PhotosActivity;
import by.test.photoapptest.ui.signin.SignInListener;
import by.test.photoapptest.ui.signin.SignInPagerAdapter;
import by.test.ui.signin.ActivityPhotoAppBinding;

public class PhotoAppActivity extends AppCompatActivity implements SignInListener {

    private ActivityPhotoAppBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.buildAppComponent(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_app);

        mBinding.pager.setAdapter(new SignInPagerAdapter(this, mBinding.pager, getSupportFragmentManager()));
        mBinding.tabLayout.setupWithViewPager(mBinding.pager);
    }

    @Override
    public void onSignInClick(@NonNull SignUserOutDto user) {
        Intent intent = new Intent(this, PhotosActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void onSignUpClick(@NonNull SignUserOutDto user) {
        Intent intent = new Intent(this, PhotosActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
