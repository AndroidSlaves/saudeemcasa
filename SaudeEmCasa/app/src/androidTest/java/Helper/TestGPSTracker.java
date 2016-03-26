//Test to see whether GPS is picking up the user's location
package Helper;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.test.ActivityInstrumentationTestCase2;

import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.view.LoadingScreen;

/**
 * Created by vinisilvacar on 26/10/15.
 */

public class TestGPSTracker extends ActivityInstrumentationTestCase2<LoadingScreen> {
    protected LocationManager locationManager;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 50;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    Location location;
    Location locationTest;

    private LoadingScreen myActivity;

    public TestGPSTracker() {
        super(LoadingScreen.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.myActivity = getActivity();
        GPSTracker gpsTracker = new GPSTracker(myActivity.getApplicationContext());
        locationManager = (LocationManager) myActivity.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, gpsTracker);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public void testShouldPass() {
        GPSTracker gpsTracker = new GPSTracker(myActivity.getApplicationContext());
        assertNotNull(gpsTracker);
    }

    public void testGetLocation() throws Exception{
        GPSTracker gpsTracker = new GPSTracker(myActivity.getApplicationContext());
        assertNotNull(locationManager);

        assertNotNull(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
        assertNotNull(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));

        locationTest = gpsTracker.getLocation();

        assertNotNull(location);
        assertNotNull(locationTest);


        assertEquals(location.getLatitude(), locationTest.getLatitude());
        assertEquals(location.getLongitude(), locationTest.getLongitude());

        assertEquals(gpsTracker.getLatitude(), locationTest.getLatitude());
        assertEquals(gpsTracker.getLongitude(), locationTest.getLongitude());

    }

    /*public void testGetLatitude() throws Exception {
        assertEquals(location.getLatitude(), locationTest.getLatitude());
    }

    public void testGetLongitude() throws Exception {
        GPSTracker gpsTracker = new GPSTracker(myActivity.getApplicationContext());
        assertNotNull(gpsTracker);
        assertEquals(location.getLongitude(), gpsTracker.getLongitude());
    } */

    public void testCanGetLocation() throws Exception {
        GPSTracker gpsTracker = new GPSTracker(myActivity.getApplicationContext());
        assertTrue(gpsTracker.canGetLocation());
    }
}
