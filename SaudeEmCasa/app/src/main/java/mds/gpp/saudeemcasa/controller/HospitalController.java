/*****************************
 * Class name: HospitalController (.java)
 *
 * Purpose: Creates hospital, list of hospitals, arrange these hospitals into a list, and organize
 *          this list by distance between user, hospital localization, provided by latitude and
 *          longitude.
 ****************************/

package mds.gpp.saudeemcasa.controller;

import android.content.Context;
import android.location.Location;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import api.Dao.HospitalDao;
import api.Exception.ConnectionErrorException;
import api.Helper.JSONHelper;
import api.Request.HttpConnection;

import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.model.Hospital;
import mds.gpp.saudeemcasa.model.Stablishment;

import static java.util.Collections.sort;

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
     * @throws JSONHelper
     *              there maybe a failure in the JSON access.
     *
     * @throws ConnectionErrorException
     *              there maybe a failure communicating with the server.
     */
    public void initControllerHospital(InputStream inputStream) throws IOException, JSONException,
            ConnectionErrorException {
        final String IP_ADDRESS = "http://159.203.95.153:3000/habilitados";
        Boolean dbEmpty = hospitalDao.isDbEmpty();

        if(dbEmpty) {

            HttpConnection httpConnection = new HttpConnection();
            //String jsonHospital = httpConnection.newRequest(IP_ADDRESS);
            String jsonHospital = httpConnection.loadJSONFromAsset(inputStream);
            JSONHelper jsonParser = new JSONHelper(context);

            if(jsonHospital != null){
                Boolean jsonResult = jsonParser.hospitalListFromJSON(jsonHospital);

                if(jsonResult) {
                    hospitalList = hospitalDao.getAllHospitals();
                } else {/* Do nothing */}
            } else {/* Do nothing */}
        } else {
            // Just setting hospitals to local list
            hospitalList = hospitalDao.getAllHospitals();
        }
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
            savedDistance = canGetLocation;
        }

        return savedDistance;
    }

    /**
     * Request the rating for the 15 first hospitals so that it can be show at the HospitalList.
     *
     * @throws ConnectionErrorException
     *              there maybe a failure communicating with the server.
     */
    public void requestRating() throws ConnectionErrorException {
        HttpConnection httpConnection = new HttpConnection();

        final int NUMBER_OF_ITEMS_ON_THE_LIST = 15;
        final String IP_ADDRESS= "http://159.203.95.153:3000/rate/gid/";

        for(int i = 0;i < NUMBER_OF_ITEMS_ON_THE_LIST; i++) {
            String hospitalId = hospitalList.get(i).getId();

            // Get rate from server
            try {
                float rate = httpConnection.getRating(hospitalId,IP_ADDRESS);
                hospitalList.get(i).setRate(rate);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    /**
     * Save or update rate from user on server database.
     *
     * @param RATE
     *           float value received from user input.
     * @param ANDROID_ID
     *           string value that represents the unique android id.
     * @param HOSPITAL_ID
     *           int value that represents the stablishment unique id.
     *
     * @return response from http connection.
     *
     * @throws ConnectionErrorException
     */
    public String updateRate(final int RATE,final String ANDROID_ID,final String HOSPITAL_ID)
            throws ConnectionErrorException {
        assert (RATE >= 0) : "minimum rate value interval.";
        assert (RATE <= 5) : "maximum rate value interval.";
        assert (ANDROID_ID != null) : "Null androidId treatment.";
        assert (ANDROID_ID.length() > 2) : "Minor character androidId treatment.";
        assert (HOSPITAL_ID != null) : "Nothing stored on hospitalId.";
        assert (HOSPITAL_ID.length() >= 1) : "Verify hospitalId minor character.";

        // Define ip address string.
        HttpConnection connection = new HttpConnection();
        final String SERVER_IP_ADDRESS = "http://159.203.95.153:3000/rate/gid/";
        final String IP_ADDRESS_WITH_ANDROID_ID = SERVER_IP_ADDRESS + HOSPITAL_ID + "/aid/"
                + ANDROID_ID;
        final String IP_ADDRESS_WITH_RATING = IP_ADDRESS_WITH_ANDROID_ID + "/rating/" + RATE;

        // Make request.
        final String RESPONSE_WITH_RATES = connection.newRequest(IP_ADDRESS_WITH_RATING);

        assert (RESPONSE_WITH_RATES != null) : "Receive a null treatment";
        return RESPONSE_WITH_RATES;
    }

}
