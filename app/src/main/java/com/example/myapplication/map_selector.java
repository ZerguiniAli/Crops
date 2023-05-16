package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link map_selector#newInstance} factory method to
 * create an instance of this fragment.
 */
public class map_selector extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    GoogleMap gmap ;
    FrameLayout map;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public map_selector() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment map_selector.
     */
    // TODO: Rename and change types and number of parameters
    public static map_selector newInstance(String param1, String param2) {
        map_selector fragment = new map_selector();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_map_selector, container, false);

        map = view.findViewById(R.id.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        this.gmap = googleMap;

        LatLng algeria = new LatLng(28.0339,1.6596);

        this.gmap.moveCamera(CameraUpdateFactory.newLatLng(algeria));
        this.gmap.getUiSettings();
        this.gmap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(),R.raw.map_style));


    }
}