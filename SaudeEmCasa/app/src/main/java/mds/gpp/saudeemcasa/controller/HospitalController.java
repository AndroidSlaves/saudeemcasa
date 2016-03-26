package mds.gpp.saudeemcasa.controller;

import android.content.Context;
import android.location.Location;

import org.json.JSONException;
import org.json.JSONObject;

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


/**
 * Created by freemanpivo on 9/20/15.
 */
public class HospitalController {

    private static HospitalController instance = null;
    private static Hospital hospital;
    private static List<Hospital> hospitalList = new ArrayList<Hospital>();
    private static HospitalDao hospitalDao;
    private static Context context;
    private String androidId;

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
        * Starts the application being inside the if for the first usage
        * and the else for the times after that.
        * Receives the response from server, take objects out of json and add to database
        * */
    public void initControllerHospital() throws IOException, JSONException, ConnectionErrorException {

            if (hospitalDao.isDbEmpty()) {
                HttpConnection httpConnection = new HttpConnection();

                String jsonHospital = httpConnection.newRequest("http://159.203.95.153:3000/habilitados");
                JSONHelper jsonParser = new JSONHelper(context);

                if(jsonHospital !=null){
                    if(jsonParser.hospitalListFromJSON(jsonHospital)){
                        hospitalList = hospitalDao.getAllHospitals();
                    }else{/*do nothing*/}
                }else {/*do nothing*/}


            } else {
                //just setting hospitals to local list
                hospitalList = hospitalDao.getAllHospitals();

            }
    }
    /**
     * set distance based on the coordenates for each hospitals
     * and then sort the list
     * @param context
     *           The activity where this is being executed.
     *
     * @param list
     *           the list of hospitals.
     *
     * @return a boolean indicator for testing
     *
     * */
    public boolean setDistance(Context context, ArrayList<Hospital> list) {
        GPSTracker gps = new GPSTracker(context);
        if(gps.canGetLocation()) {
            double userLongitude = gps.getLongitude();
            double userLatitude = gps.getLatitude();
            for (int i = 0; i < list.size(); i++) {
                float resultsadapter[] = new float[1];

                Location.distanceBetween(Double.parseDouble(list.get(i).getLatitude()),
                        Double.parseDouble(list.get(i).getLongitude()),
                        userLatitude, userLongitude, resultsadapter);

                list.get(i).setDistance(resultsadapter[0]);
            }
            sort(list, new DistanceComparator());
            return true;
        }else {
            return false;
        }

    }
    /*
    * Request the rating for the 15 first hospitals so that it can be shown
    * at the HospitalList
    * */
    public void requestRating() throws ConnectionErrorException {
        HttpConnection httpConnection = new HttpConnection();
        for(int i = 0;i<15;i++){
            try {
                hospitalList.get(i).setRate(httpConnection.getRating(hospitalList.get(i).getId(),"http://159.203.95.153:3000/rate/gid/"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAndroidId() {
        return androidId;
    }

    /*
    * Creates object that will determine how the comparation is done for
    * setDistante function sort.
    * */
    public static class DistanceComparator implements Comparator<Stablishment>
    {

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
    public String updateRate(int rate,String androidId,String hospitalId ) throws ConnectionErrorException {
        HttpConnection connection = new HttpConnection();
        String response;
        response = connection.newRequest("http://159.203.95.153:3000"+"/"+"rate"+"/"+"gid"+"/"+hospitalId+"/"+"aid"+"/"+androidId+"/"+"rating"+"/"+rate);

        return response;
    }

}
