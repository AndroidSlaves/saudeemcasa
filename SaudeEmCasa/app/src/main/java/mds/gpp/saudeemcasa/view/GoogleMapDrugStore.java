/*****************************
 * Class name: GoogleMap (.java)
 *
 * Purpose: This class create the view with google maps to show drugstore location.
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
import mds.gpp.saudeemcasa.controller.DrugStoreController;

public class GoogleMapDrugStore extends FragmentActivity{
    // Google map object that contains everything to be show to the user.
    private GoogleMap myGoogleMap = null;
    // Controller that contains the data for proper functioning.
    DrugStoreController drugStoreController = DrugStoreController.getInstance(this);
    /**
     * Method that is called when screen is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert (savedInstanceState != null) : "Receive a null tratment";

        setContentView(R.layout.activity_google_map_drugstore);
        setUpMap();
    }

    /**
     * Resume map when it is stopped by the user getting off the screen.
     */
    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    /**
     * Do a null check to confirm that we have not already instantiated the map. Try to obtain the
     * map from the SupportMapFragment. Check if we were successful in obtaining the map.
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
     */
    private void oneLocationMap() {
        String name = drugStoreController.getDrugstore().getName();
        String latitude = drugStoreController.getDrugstore().getLatitude();
        String longitude = drugStoreController.getDrugstore().getLongitude();
        LatLng drugstoreLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

        myGoogleMap.addMarker(new MarkerOptions().position(drugstoreLocation).title(name));
        myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(drugstoreLocation, 10));
    }
}
