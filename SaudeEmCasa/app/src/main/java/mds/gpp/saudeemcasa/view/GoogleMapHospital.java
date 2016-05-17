/*****************************
 * Class name: GoogleMap (.java)
 *
 * Purpose: This class create the view with google maps to show hospital location
 ****************************/

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

public class GoogleMapHospital extends FragmentActivity {

    private GoogleMap myGoogleMap = null;
    HospitalController hospitalController = HospitalController.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert (savedInstanceState != null) : "Receive a null treatment";
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_google_maps_hospital);
        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }
    /**
     * Do a null check to confirm that we have not already instantiated the map. Try to obtain the
     * map from the SupportMapFragment. Check if we were successful in obtaining the map.
     *
     */
    private void setUpMap() {

        if (myGoogleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            myGoogleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            myGoogleMap.setTrafficEnabled(true);
            myGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        } else {
            oneLocationMap();
        }

    }

    /**
     * This functions get the latitude and longitude from the drugstore in the context and put a
     * marker point there with zoom 10 (10 shows elegance).
     *
     */
    private void oneLocationMap() {
        String nome = hospitalController.getHospital().getName();
        String latitude = hospitalController.getHospital().getLatitude();
        String longitude = hospitalController.getHospital().getLongitude();
        LatLng hospitalLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

        myGoogleMap.addMarker(new MarkerOptions().position(hospitalLocation).title(nome));
        myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hospitalLocation, 10));
    }
}
