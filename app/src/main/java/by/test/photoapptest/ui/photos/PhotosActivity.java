package by.test.photoapptest.ui.photos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import by.test.photoapptest.R;
import by.test.photoapptest.model.photo.ImageDtoOut;
import by.test.photoapptest.model.user.SignUserOutDto;
import by.test.photoapptest.ui.camera.CameraActivity;
import by.test.photoapptest.ui.map.MapFragment;
import by.test.photoapptest.ui.photos.detail.DetailPhotoFragment;

public class PhotosActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener,
        PhotoListFragment.OnFragmentInteractionListener {

    private final int PHOTO_FRAGMENT_TYPE = 0;
    private final int MAP_FRAGMENT_TYPE = 1;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ActivityPhotosBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photos);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mBinding.openCamera.setOnClickListener(this);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case PHOTO_FRAGMENT_TYPE:
                SignUserOutDto user = (SignUserOutDto)getIntent().getSerializableExtra("user");
                fragmentTransaction.replace(R.id.container, PhotoListFragment.newInstance(user));
                break;
            case MAP_FRAGMENT_TYPE:
                fragmentTransaction.replace(R.id.container, MapFragment.newInstance());
                break;
            default:
                throw new UnsupportedOperationException("No such fragment type");
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        SignUserOutDto user = (SignUserOutDto)getIntent().getSerializableExtra("user");
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void onPhotoItemClicked(@NonNull ImageDtoOut photo, @NonNull SignUserOutDto user) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, DetailPhotoFragment.newInstance(photo, user))
                .commit();
    }
}