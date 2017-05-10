package by.test.photoapptest.ui.camera;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flurgle.camerakit.CameraListener;

import javax.inject.Inject;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;

public class CameraActivity extends AppCompatActivity {

    private by.test.photoapptest.ui.camera.ActivityCameraBinding mBinding;

    @Inject
    CameraPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);

        mPresenter.connectGoogleApi(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_camera);

        mBinding.camera.setCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] picture) {
                super.onPictureTaken(picture);
                mPresenter.pushImageToCloud(CameraActivity.this, picture);
            }
        });
    }

    @Override
    protected void onStart() {
        mPresenter.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
        mBinding.camera.start();
    }

    @Override
    protected void onPause() {
        mBinding.camera.stop();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void onMakePhotoClick(View view) {
        mBinding.camera.captureImage();
    }
}
