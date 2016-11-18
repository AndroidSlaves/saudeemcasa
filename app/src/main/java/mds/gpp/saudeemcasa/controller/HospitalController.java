/*****************************
 * Class name: HospitalController (.java)
 *
 * Purpose: Creates hospital, list of hospitals, arrange these hospitals into a list, and organize
 *          this list by distance between user, hospital localization, provided by latitude and
 *          longitude.
 ****************************/

package mds.gpp.saudeemcasa.controller;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import api.Dao.HospitalDao;
import api.Exception.ConnectionErrorException;

import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.model.Hospital;
import mds.gpp.saudeemcasa.model.Stablishment;
import mds.gpp.saudeemcasa.view.HospitalList;

import static java.util.Collections.sort;
import static junit.framework.Assert.assertNotNull;

public class HospitalController {


    private static HospitalController instance = null;

    // Selected hospital from the list presented to the user.
    private static Hospital hospital;

    // Creates an arraylist to populate this list with a collection of hospitals
    private static List<Hospital> hospitalList = new ArrayList<Hospital>();

    // Attribute hospitalDao used to get hospitals objects in database
    private static HospitalDao hospitalDao;

    // Attribute context is related to the screen that user should be.
    private static Context context;

    // Attribute for logging class name.
    final String TAG = HospitalController.class.getSimpleName();

    /*
     * androidId is an string information unique for each mobile phone. We get this information
     * directly from mobile phone.
     */
    private String androidId;

    /**
     * Constructor of hospital controller.
     *
     * @param context
     *              The screen where this is being called.
     */
    private HospitalController(Context context) {
        this.context = context;
        hospitalDao = HospitalDao.getInstance(context);
    }

    /**
     * Return the unique instance of DrugstoreController active in the project.
     *
     * @return
     *              The unique instance of DrugstoreController.
     */
    public static HospitalController getInstance(Context context) {
        if (instance == null) {
            instance = new HospitalController(context);
        } else {/* ! Nothing To Do. */}

        assert (instance != null) : "Receive a null treatment";
        return instance;
    }

    /**
     * Set the selected hospital.
     *
     * @param hospital
     *              the selected hospital.
     */
    public void setHospital( Hospital hospital ) {
        assert (hospital != null) : "Null object treatment";

        HospitalController.hospital = hospital;
    }

    /**
     * Get the selected hospital.
     *
     * @return
     *              the selected hospital from previous HospitalList click.
     */
    public Hospital getHospital() {
        assert (hospital != null) : "Null object treatment";
        return hospital;
    }

    /**
     * Get hospital list
     *
     * @return
     *              All the hospitals stored in this instance.
     */
    public static List<Hospital> getAllHospitals(){
        assert (hospitalList != null) :"Receive a null treatment";
        return hospitalList;
    }

    /**
     * Starts the application being inside the if for the first usage and the else for the times
     * after that. Receives the response from server, take objects out of json and add to database.
     *
     * @throws IOException
     *              there maybe a failure in the conversion on the treatment of the response
     *              from the server.
     *
     * @throws ConnectionErrorException
     *              there maybe a failure communicating with the server.
     */
    public void initControllerHospital() {
        hospitalList = hospitalDao.getAllHospitals();
        assertNotNull("Received null treatment drugstoreList", hospitalList);
    }

    /**
     * Set distance based on the coordenates for each hospitals and then sort the list.
     *
     * @param context
     *              The activity where this is being executed.
     * @param list
     *              The list of hospitals.
     *
     * @return
     *              a boolean indicator for testing
     *
     */
    public boolean setDistance(Context context, ArrayList<Hospital> list) {
        assert (list.isEmpty() == true) : "Empty list treatment";
        assert (list != null) : "Null list treatment";

        GPSTracker gps = new GPSTracker(context);
        boolean canGetLocation = gps.canGetLocation();
        boolean savedDistance = false;

        if(canGetLocation) {
            double userLongitude = gps.getLongitude();
            double userLatitude = gps.getLatitude();

            Log.i(TAG, "Phone can get user location! Evaluating distance...");

            for (int i = 0; i < list.size(); i++) {
                float resultsAdapter[] = new float[1];
                // REVIEW FUNCTION
                Location.distanceBetween(Double.parseDouble(list.get(i).getLatitude()),
                        Double.parseDouble(list.get(i).getLongitude()),
                        userLatitude, userLongitude, resultsAdapter);
                list.get(i).setDistance(resultsAdapter[0]);
            }

            sort(list, new DistanceComparator());
            savedDistance = true;

        } else {
            Log.d(TAG, "Can't get user location, verify if GPS is enabled.");
            savedDistance = canGetLocation;
        }

        return savedDistance;
    }

    /**
     * Saves the unique identifier of the android user.
     *
     * @param ANDROID_ID
     *              Identifier of the android user of this session.
     */
    public void setAndroidId(final String ANDROID_ID) {
        assert (ANDROID_ID != null) : "Null androidId treatment.";
        assert (ANDROID_ID.length() > 2) : "Minor character androidId treatment.";

        this.androidId = ANDROID_ID;
    }

    /**
     * Gets the unique identifier of the android user.
     *
     * @returns
     *              the Identifier of the android user of this session.
     */
    public String getAndroidId() {
        assert (androidId != null) : "Receive a null treatment";
        assert (androidId != "") : "Receive a empty treatment";
        return androidId;
    }

    /**
     * Creates object that will determine how the comparation is done for setDistante function sort.
     */
    public static class DistanceComparator implements Comparator<Stablishment> {

        /**
         * Use responseHandler created to request the requested through a URL.
         *
         * @param STABLISHMENT_1
         *              A stablishment to be compared.
         *
         * @param STABLISHMENT_2
         *              A stablishment to be compared.
         *
         * @return
         *              which stablishment has the gratter distance.
         */
        public int compare(final Stablishment STABLISHMENT_1, final Stablishment STABLISHMENT_2) {
            assert (STABLISHMENT_1 != null) : "stablishment1 null object treatment.";
            assert (STABLISHMENT_2 != null) : "stablishment2 null object treatment.";

            final float DISTANCE1 = STABLISHMENT_1.getDistance();
            final float DISTANCE2 = STABLISHMENT_2.getDistance();

            int comparatorResult = 0;
            if(DISTANCE1<DISTANCE2){
                comparatorResult = -1;
            }else{
                comparatorResult = 1;
            }

            return comparatorResult;
        }
    }
}
