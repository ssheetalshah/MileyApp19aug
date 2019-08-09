package com.ics.newapp.fregment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.ics.newapp.R;
import com.ics.newapp.fregment.Favorite_list;
import com.ics.newapp.fregment.Host_Event_Screen;
import com.ics.newapp.fregment.ListFragment;
import com.ics.newapp.fregment.profile_fragment;

import java.io.IOException;
import java.util.List;


public class MapFregment extends Fragment implements OnMapReadyCallback,
        com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    LinearLayout fav_list, add_event, view_profile;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    Button btn_M_view, btn_L_view;
    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker, mCurrLocationMarker1, mCurrLocationMarker2;
    String addressstring;
    GoogleApiClient mGoogleApiClient;
    Geocoder geocoder;
    LocationRequest mLocationRequest;
    Circle circle;

    View mapview;
    ImageView iv_add_event, iv_profiles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        } else {
            //  Toast.makeText(getActivity(), "Please check Permission.", Toast.LENGTH_SHORT).show();
            //  lets_get_current_loc();
        }

        geocoder = new Geocoder(getActivity());

//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//         //   return TODO;
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new android.location.LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
//                Log.e("Act" , "location"+location.getLatitude());
//                }
//
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) {
//                    Log.e("Act" , "location");
//                }
//
//                @Override
//                public void onProviderEnabled(String provider) {
//
//                }
//
//                @Override
//                public void onProviderDisabled(String provider) {
//
//                }
//            });
//
//        }
        return inflater.inflate(R.layout.map_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //this.mapview=view;
        //you can set the title for your
        // toolbar here for different fragments different titles
        getActivity().setTitle("Miley");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapview = mapFragment.getView();
        mapFragment.getMapAsync(this);


        fav_list = (LinearLayout) view.findViewById(R.id.fav_list);
        add_event = (LinearLayout) view.findViewById(R.id.add_event);
        view_profile = (LinearLayout) view.findViewById(R.id.provile_view);
        btn_L_view = view.findViewById(R.id.l_view);
        btn_M_view = view.findViewById(R.id.m_view);

        iv_profiles = view.findViewById(R.id.iv_profiles);
        iv_add_event = view.findViewById(R.id.iv_add_event);


        //*****************************************************************************

        iv_profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment profile_List = new profile_fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, profile_List).addToBackStack(null);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
            }
        });


        iv_add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment add_event = new Create_Activity();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, add_event).addToBackStack(null);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);

            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);

                View locationButton = ((View) mapview.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
                // position on right bottom
                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                rlp.setMargins(0, 0, 30, 30);

                circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(22.6666, 75.8897))
                        .radius(3000)
                        .strokeColor(Color.LTGRAY)
                        .fillColor(Color.LTGRAY));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.6666, 75.8897), 15));
                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(25), 2000, null);
                //Place current location marker
                try {
                    List<Address> address = geocoder.getFromLocation(22.6666, 75.8897, 5);
                    addressstring = address.get(0).getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LatLng latLng1 = new LatLng(22.6666, 75.8897);
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(latLng1);
                markerOptions1.title(addressstring);
                markerOptions1.icon(BitmapDescriptorFactory.fromResource(R.drawable.pponee));
                mCurrLocationMarker1 = mMap.addMarker(markerOptions1);


                circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(22.72453, 75.88416))
                        .radius(3000)
                        .strokeColor(Color.LTGRAY)
                        .fillColor(Color.LTGRAY));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.72453, 75.88416), 15));
                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(25), 2000, null);
                //Place current location marker
                try {
                    List<Address> address = geocoder.getFromLocation(22.72453, 75.88416, 5);
                    addressstring = address.get(0).getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LatLng latLng2 = new LatLng(22.72453, 75.88416);
                MarkerOptions markerOptions2 = new MarkerOptions();
                markerOptions2.position(latLng2);
                markerOptions2.title(addressstring);
                markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.pptwo));
                mCurrLocationMarker2 = mMap.addMarker(markerOptions2);


            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

            View locationButton = ((View) mapview.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            // position on right bottom
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            rlp.setMargins(0, 0, 30, 30);

        }

    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.d("location", "jksfhdsd");
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(location.getLatitude(), location.getLongitude()))
                .radius(3000)
                .strokeColor(Color.LTGRAY)
                .fillColor(Color.LTGRAY));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(25), 2000, null);
        //Place current location marker
        try {
            List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
            addressstring = address.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(addressstring);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ppthree));
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        Log.e("location is", "" + location.getLatitude());
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));


        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


}
