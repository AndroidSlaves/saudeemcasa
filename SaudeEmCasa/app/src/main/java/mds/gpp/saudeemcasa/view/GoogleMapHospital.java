package mds.gpp.saudeemcasa.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.HospitalController;

/**
 * Created by iolane on 25/11/15.
 */
public class GoogleMapHospital extends FragmentActivity {

    private GoogleMap mMap;
    HospitalController controller = HospitalController.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_google_maps_hospital);
        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    private void setUpMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap != null) {
            oneLocationMap();
            return;
        }
        // Try to obtain the map from the SupportMapFragment.
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        // Check if we were successful in obtaining the map.
        if (mMap == null) {
            return;
        }
        mMap.setTrafficEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    //Start the map on the right position
    private void oneLocationMap() {
        String nome = controller.getHospital().getName();
        String latitude = controller.getHospital().getLatitude();
        String longitude = controller.getHospital().getLongitude();
        LatLng hospitalLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

        mMap.addMarker(new MarkerOptions().position(hospitalLocation).title(nome));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hospitalLocation, 10));
    }
}
