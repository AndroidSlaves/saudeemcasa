/*****************************
 * Class name: GPSTracker (.java)
 *
 * Purpose: Implement GPS for mobile phone.
 ****************************/

package mds.gpp.saudeemcasa.helper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class GPSTracker extends Service implements LocationListener{

    private final Context context;

    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    boolean canGetLocation = false;

    double longitude;
    double latitude;
    Location location;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 50;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    protected LocationManager locationManager;

    /**
     *
     * @param context
     *              Define the context where GPS will be instantiated.
     */
    public GPSTracker(Context context) {
        this.context = context;
        getLocation();
    }

    /**
     *
     * @return
     *              Geolocation of user, contains longitude and latitude.
     *
     * Get the current location of the user, with latitude and longitude. Checks the GPS connection
     * and activation, possible paths and check location by GPS or WiFi.
     */
    public Location getLocation() {

        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!isGPSEnable && !isNetworkEnable) {
            }else {

                this.canGetLocation = true;
                if(isNetworkEnable) {

                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
                            this);
                    if(locationManager != null) {

                        location = locationManager.getLastKnownLocation(
                                LocationManager.NETWORK_PROVIDER);

                        if(location != null) {

                            longitude = location.getLongitude();
                            latitude = location.getLatitude();
                        }
                    }
                }
                if(isGPSEnable) {
                    if(location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                                this);
                        if (locationManager != null) {

                            location = locationManager.getLastKnownLocation(
                                    LocationManager.GPS_PROVIDER);
                            if (location != null) {

                                longitude = location.getLongitude();
                                latitude = location.getLatitude();
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        assert (location !=null) : "location must never be null.";
        return location;
    }

    /*
     * Method requesting the cancellation of GPS updates, or stop updating the user's location.
     */
    public void stopUsingGPS() {
        if(locationManager != null) {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    /*
     * Redeem user's position, returning only the latitude coordinate the user is located.
     */
    public double getLatitude() {
        if(location != null) {
            latitude = location.getLatitude();
        }

        assert (latitude < 90) : "the valid value for latitude must be smaller than 91 degress";
        assert (latitude > -90) : "the valid value for latitude must be bigger than negative 91 degress";
        return latitude;
    }

    /*
     * Redeem the user's position, returning only the longitude coordinate the user is located.
     */
    public double getLongitude() {
        if(location != null) {
            longitude = location.getLongitude();
        }

        assert (latitude < 90) : "the valid value for longitude must be smaller than 181 degress";
        assert (latitude > -90) : "the valid value for longitude must be bigger than negative 181 degress";
        return longitude;
    }

    /*
     * Check the variable canGetLocation and returns its result.
     */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
