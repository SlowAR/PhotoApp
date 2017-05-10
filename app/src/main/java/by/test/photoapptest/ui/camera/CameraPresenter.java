package by.test.photoapptest.ui.camera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;

import by.test.photoapptest.util.Constants;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by SlowAR on 10.05.2017.
 */

public class CameraPresenter implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Context mContext;
    private LocationRequest mLocationRequest;

    public void onStart() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    public void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    public void pushImageToCloud(@NonNull Context context, byte[] photo) {
        String base64Image = resizeBitmap(photo);
        double latitude = mLastLocation.getLatitude();
        double longitude = mLastLocation.getLongitude();
        int currentDate = (int)(System.currentTimeMillis() / 1000L);
    }

    private String resizeBitmap(byte[] photo) {
        Bitmap bitmapPhoto = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        int width = bitmapPhoto.getWidth();
        int height = bitmapPhoto.getHeight();
        float ratio = width / height;
        if (ratio > 1) {
            if (width >= Constants.MAX_PHOTO_SIZE) {
                width = Constants.MAX_PHOTO_SIZE;
                height = (int) (width / ratio);
            }
        } else {
            if (height >= Constants.MAX_PHOTO_SIZE) {
                height = Constants.MAX_PHOTO_SIZE;
                width = (int) (height * ratio);
            }
        }
        bitmapPhoto = Bitmap.createScaledBitmap(bitmapPhoto, width, height, false);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapPhoto.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] reseizedBytePhoto = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(reseizedBytePhoto, Base64.DEFAULT);
    }

    public void connectGoogleApi(@NonNull Context context) {
        mContext = context;
        enableGps(context);
        initLocationRequest();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void enableGps(@NonNull Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((CameraActivity) context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
    }

    private void initLocationRequest() {
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000)
                .setFastestInterval(1000);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((CameraActivity) mContext,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return;
            }
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }
}