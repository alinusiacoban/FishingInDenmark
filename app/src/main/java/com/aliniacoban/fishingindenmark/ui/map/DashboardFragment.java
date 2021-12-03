package com.aliniacoban.fishingindenmark.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aliniacoban.fishingindenmark.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DashboardFragment extends Fragment {

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng horsens = new LatLng(55.858123, 9.84756);
            LatLng bygholm = new LatLng(55.870431665202666, 9.813493505582645);
            LatLng horsensFjord = new LatLng(55.858926457637736, 10.030964840629437);
            LatLng hanstedA = new LatLng(55.89312218974992, 9.805042980749304);
            LatLng lilHanstedA = new LatLng(55.889081553055924, 9.827865271052378);
            googleMap.addMarker(new MarkerOptions().position(bygholm).title("Bygholm"));
            googleMap.addMarker(new MarkerOptions().position(horsens).title("Horsens"));
            googleMap.addMarker(new MarkerOptions().position(horsensFjord).title("Horsens Fjord"));
            googleMap.addMarker(new MarkerOptions().position(hanstedA).title("St. Hansted Å"));
            googleMap.addMarker(new MarkerOptions().position(lilHanstedA).title("Lille Hansted Å"));
            float zoomLevel = 16.0f;
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(horsens, zoomLevel));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}