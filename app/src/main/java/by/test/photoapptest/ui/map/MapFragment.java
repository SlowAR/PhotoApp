package by.test.photoapptest.ui.map;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import by.test.photoapptest.R;
import by.test.photoapptest.di.App;
import by.test.photoapptest.ui.model.photo.ImageDtoOut;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private final int MAX_ZOOM = 15;

    @Inject
    MapPresenter mPresenter;
    private MapFragmentBinding mBinding;

    private double minLatitude;
    private double maxLatitude;
    private double minLongitude;
    private double maxLongitude;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.mapFragment, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        List<ImageDtoOut> photoList = mPresenter.getPhotosFromDb();
        addMapMarkers(googleMap, photoList);
        setMapCamera(googleMap);
    }

    private void addMapMarkers(GoogleMap googleMap, List<ImageDtoOut> photoList) {
        minLatitude = photoList.get(0).getLatitude();
        maxLatitude = photoList.get(0).getLatitude();
        minLongitude = photoList.get(0).getLongitude();
        maxLongitude = photoList.get(0).getLongitude();

        for (ImageDtoOut image : photoList) {
            Glide.with(getContext())
                    .load(image.getUrl())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Bitmap resizedBitmap = Bitmap.createScaledBitmap(resource, 100, 100, false);
                            addMarker(googleMap, image, resizedBitmap);
                        }
                    });

            if (minLatitude > image.getLatitude()) {
                minLatitude = image.getLatitude();
            }
            if (maxLatitude < image.getLatitude()) {
                maxLatitude = image.getLatitude();
            }
            if (minLongitude > image.getLongitude()) {
                minLongitude = image.getLongitude();
            }
            if (maxLongitude < image.getLongitude()) {
                maxLongitude = image.getLongitude();
            }
        }
    }

    private void addMarker(GoogleMap googleMap, ImageDtoOut image, Bitmap icon) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(image.getLatitude(), image.getLongitude()))
                .icon(BitmapDescriptorFactory.fromBitmap(icon)));
    }

    private void setMapCamera(GoogleMap googleMap) {
        double averageLatitude = (minLatitude + maxLatitude) / 2;
        double averageLongitude = (minLongitude + maxLongitude) / 2;
        double zoom = MAX_ZOOM - (maxLatitude / minLatitude + maxLongitude / minLongitude);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(averageLatitude, averageLongitude))
                .zoom((float) zoom)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate, 4000, null);
    }
}