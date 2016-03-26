package mds.gpp.saudeemcasa.controller;

import android.content.Context;

import org.json.JSONException;

import android.location.Location;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import api.Dao.DrugStoreDao;
import api.Exception.ConnectionErrorException;
import api.Helper.JSONHelper;
import api.Request.HttpConnection;
import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.model.Stablishment;

import static java.util.Collections.sort;


public class DrugStoreController {

    private static DrugStoreController instance = null;
    private static DrugStore drugStore;
    private static List<DrugStore> drugStoreList = new ArrayList<DrugStore>();
    private static Context context;
    private static DrugStoreDao drugStoreDao;
    private String androidId;
    
    private DrugStoreController(Context context) {
        this.context = context;
        drugStoreDao = DrugStoreDao.getInstance(context);
    }
    /**
     * Return the unique instance of DrugstoreController active in the
     * project.
     *
     * @return The unique instance of DrugstoreController.
     */
    public static DrugStoreController getInstance(Context context) {
        if (instance == null) {
            instance = new DrugStoreController(context);
        } else {
			/* ! Nothing To Do. */
        }
        return instance;
    }
    /**
     * Set the selected drugstore
     *
     * @param drugStore
     *          the selected drugstore
     * */
    public void setDrugStore( DrugStore drugStore ) {
        DrugStoreController.drugStore = drugStore;
    }
    /**
     * Get the selected drugstore
     *
     * @return the class drugstore
     * */
    public DrugStore getDrugstore() {
        return drugStore;
    }

    /**
     * Get all the drugstores
     *
     * @return the list of drugstores
     * */

    public List<DrugStore> getAllDrugstores(){
        return drugStoreList;
    }
    /*
    * Starts the application being inside the if for the first usage
    * and the else for the times after that.
    * Receives the response from server, take objects out of json and add to database
    * */
    public void initControllerDrugstore() throws IOException, JSONException,ConnectionErrorException {

            if (drugStoreDao.isDbEmpty()) {

                HttpConnection httpConnection = new HttpConnection();

                String jsonPublic = httpConnection.newRequest("http://159.203.95.153:3000/farmacia_popular");

                HttpConnection httpConnectionPrivate = new HttpConnection();

                String jsonPrivate = httpConnectionPrivate.RequestAllDrugstoresByUF("http://159.203.95.153:3000/farmacia_popular_conveniada");

                if(jsonPublic != null && jsonPrivate !=null){

                    JSONHelper jsonParser = new JSONHelper(context);

                    if(jsonParser.drugstorePublicListFromJSON(jsonPublic) && jsonParser.drugstorePrivateListFromJSON(jsonPrivate)){
                        drugStoreList = drugStoreDao.getAllDrugStores();
                    }else{/*do nothing*/}
                }else {/*do nothing*/}

            } else {
                drugStoreList = drugStoreDao.getAllDrugStores();
            }
    }
    /**
     * set distance based on the coordenates for each drugstore
     * and then sort the list
     * @param context
     *           The activity where this is being executed.
     *
     * @param list
     *           the list of drugstores.
     *
     * @return a boolean indicator for testing
     *
     * */
    public static boolean setDistance(Context context,ArrayList<DrugStore> list) {
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
        * Request the rating for the 15 first drugstores so that it can be shown
        * at the HospitalList
        * */
    public void requestRating() throws ConnectionErrorException {
        HttpConnection httpConnection = new HttpConnection();
        for(int i = 0;i<15;i++){
            try {
                drugStoreList.get(i).setRate(httpConnection.getRating(drugStoreList.get(i).getId(),"http://159.203.95.153:3000/rate/gid/"));
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
     * @param drugstoreId
     *           int value that represents the stablishment unique id.
     *
     * @return response from http connection.
     *
     * @throws ConnectionErrorException
     */
    public String updateRate(int rate,String androidId,String drugstoreId ) throws ConnectionErrorException {
        HttpConnection connection = new HttpConnection();
        String response = null;

        response = connection.newRequest("http://159.203.95.153:3000"+"/"+"rate"+"/"+"gid"+"/"+drugstoreId+"/"+"aid"+"/"+androidId+"/"+"rating"+"/"+rate);

        return response;
    }

}
