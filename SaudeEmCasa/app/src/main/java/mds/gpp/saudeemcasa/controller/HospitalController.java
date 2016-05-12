/*****************************
 * Class name: HospitalController (.java)
 *
 * Purpose: Creates hospital, list of hospitals, arrange these hospitals into a list, and organize
 * this list by distance between user, hospital localization, provided by latitude and longitude.
 ****************************/

package mds.gpp.saudeemcasa.controller;

import android.content.Context;
import android.location.Location;

import org.json.JSONException;

import java.io.IOException;
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
    private static Hospital hospital;
    private static List<Hospital> hospitalList = new ArrayList<Hospital>();
    private static HospitalDao hospitalDao;
    private static Context context;
    private String androidId;

    /**
     * Constructor of hospital controller
     *
     * @param context:Context
     */
    private HospitalController(Context context) {
        this.context = context;
        hospitalDao = HospitalDao.getInstance(context);
    }

    /**
     * Return the unique instance of DrugstoreController active in the
     * project.
     *
     * @return The unique instance of DrugstoreController.
     */
    public static HospitalController getInstance(Context context) {
        if (instance == null) {
            instance = new HospitalController(context);
        } else {
			/* ! Nothing To Do. */
        }
        return instance;
    }

    /**
     * Set the selected hospital
     *
     * @param hospital
     *          the selected hospital
     * */
    public void setHospital( Hospital hospital ) {
        assert (hospital != null) : "Null object treatment";

        HospitalController.hospital = hospital;
    }

    /**
     * Get the selected hospital
     *
     * @return the class hospital
     * */
    public Hospital getHospital() {
        return hospital;
    }

    /**
     * Get hospital list
     *
     * @return the hospitalList
     * */
    public static List<Hospital> getAllHospitals(){
        return hospitalList;
    }

    /*
    * Starts the application being inside the if for the first usage and the else for the times
    * after that. Receives the response from server, take objects out of json and add to database.
    * */
    public void initControllerHospital() throws IOException, JSONException, ConnectionErrorException {
        String ipAddress = "http://159.203.95.153:3000/habilitados";
        Boolean dbEmpty = hospitalDao.isDbEmpty();

        if(dbEmpty) {

            HttpConnection httpConnection = new HttpConnection();
            String jsonHospital = httpConnection.newRequest(ipAddress);
            JSONHelper jsonParser = new JSONHelper(context);

            if(jsonHospital !=null){
                Boolean jsonResult = jsonParser.hospitalListFromJSON(jsonHospital);

                if(jsonResult) {
                    hospitalList = hospitalDao.getAllHospitals();
                } else {
                    /* Do nothing */
                }
            } else {
                /* Do nothing */
            }

        } else {
            // Just setting hospitals to local list
            hospitalList = hospitalDao.getAllHospitals();
        }
    }

    /**
     * Set distance based on the coordenates for each hospitals and then sort the list.
     *
     * @param context
     *           The activity where this is being executed.
     *
     * @param list
     *           The list of hospitals.
     *
     * @return a boolean indicator for testing
     *
     * */
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
     * Request the rating for the 15 first hospitals so that it can be show at the HospitalList
     *
     * @throws ConnectionErrorException
     * */
    public void requestRating() throws ConnectionErrorException {
        HttpConnection httpConnection = new HttpConnection();
        final int numberOfItemsOnTheList = 15;
        String ipAddress = "http://159.203.95.153:3000/rate/gid/";

        for(int i = 0;i < numberOfItemsOnTheList; i++) {
            String hospitalId = hospitalList.get(i).getId();
            try {
                float rate = httpConnection.getRating(hospitalId,ipAddress);
                hospitalList.get(i).setRate(rate);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAndroidId(String androidId) {
        assert (androidId != null) : "Null androidId treatment.";
        assert (androidId.length() > 2) : "Minor character androidId treatment.";

        this.androidId = androidId;
    }

    public String getAndroidId() {
        return androidId;
    }

    /*
    * Creates object that will determine how the comparation is done for
    * setDistante function sort.
    * */
    public static class DistanceComparator implements Comparator<Stablishment> {

        /**
         * Use responseHandler created to request the requested through a URL.
         *
         * @param stablishment1
         *          A stablishment to be compared.
         *
         * @param stablishment2
         *          A stablishment to be compared.
         *
         * @return which stablishment has the gratter distance.
         */
        public int compare(Stablishment stablishment1, Stablishment stablishment2) {
            assert (stablishment1 != null) : "stablishment1 null object treatment.";
            assert (stablishment2 != null) : "stablishment2 null object treatment.";
            // Needs reduce in the complexity
            return stablishment1.getDistance()<(stablishment2.getDistance())? -1 : 1;
        }
    }

    /**
     * Save or update rate from user on server database.
     *
     * @param rate
     *           float value received from user input.
     *
     * @param androidId
     *           string value that represents the unique android id.
     * @param hospitalId
     *           int value that represents the stablishment unique id.
     *
     * @return response from http connection.
     *
     * @throws ConnectionErrorException
     */
    public String updateRate(int rate, String androidId, String hospitalId)
            throws ConnectionErrorException {
        assert (rate >= 0 && rate <= 5) : "Minimum and maximum rate value interval.";
        assert (androidId != null) : "Null androidId treatment.";
        assert (androidId.length() > 2) : "Minor character androidId treatment.";
        assert (hospitalId != null) : "Nothing stored on hospitalId.";
        assert (hospitalId.length() >= 1) : "Verify hospitalId minor character.";

        HttpConnection connection = new HttpConnection();

        String ipAddress = "http://159.203.95.153:3000/rate/gid/" + hospitalId + "/aid/" + androidId
                + "/rating/" + rate;
        String response = connection.newRequest(ipAddress);

        return response;
    }

}
