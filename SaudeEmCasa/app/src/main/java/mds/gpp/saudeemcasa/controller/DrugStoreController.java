/*****************************
 * Class name: DrugStoreController (.java)
 *
 * Purpose: Entity that communicates with the backend functions. Contains the logic steps of the
 * application.
 ****************************/

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

    /**
     * Constructor for the DrugstoreController.
     *
     * @param context
     * activity context where this method was called.
     *
     */
    private DrugStoreController(Context context) {
        assert (context != null) : "Receive a null treatment";
        this.context = context;
        drugStoreDao = DrugStoreDao.getInstance(context);
    }

    /**
     * Return the unique instance of DrugstoreController active in the project.
     *
     * @param context
     * activity context where this method was called.
     *
     * @return The unique instance of DrugstoreController.
     *
     */
    public static DrugStoreController getInstance(Context context) {
        assert (context != null) : "Receive a null treatment";
        if (instance == null) {
            instance = new DrugStoreController(context);
        } else {
			/* ! Nothing To Do. */
        }
        return instance;
    }

    /**
     * Stores the given drugstore as the active (clicked) drugstore.
     *
     * @param drugStore
     * the selected drugstore on the list of drugstores.
     *
     */
    public void setDrugStore( DrugStore drugStore ) {
        assert (drugStore != null) : "Receive a null treatment";
        DrugStoreController.drugStore = drugStore;
    }

    /**
     * Get store  drugstore
     *
     * @return the previously selected and stored drugstore.
     *
     **/
    public DrugStore getDrugstore() {
        return drugStore;
    }

    /**
     * Give the list of the nearest drugstores to be show to the user in a list.
     *
     * @return the list of the nearest drugstores.
     *
     */
    public List<DrugStore> getAllDrugstores(){
        return drugStoreList;
    }

    /**
     * Method that is first initiated when the application is opened. It connects to the server and
     * the database on first use or just get the data from the database for usage.
     *
     * @throws IOException there maybe a failure in the conversion on the treatment of the response
     * from the server.
     *
     * @throws JSONHelper there maybe a failure in the JSON access.
     *
     * @throws ConnectionErrorException there maybe a failure communicating with the server.
     *
     */
    public void initControllerDrugstore() throws IOException, JSONException,ConnectionErrorException {

            if (drugStoreDao.isDatabaseEmpty()) {

                HttpConnection httpConnection = new HttpConnection();

                String jsonPublic = httpConnection.newRequest("http://159.203.95.153:3000/farmacia_popular");

                HttpConnection httpConnectionPrivate = new HttpConnection();

                String jsonPrivate = httpConnectionPrivate.RequestAllDrugstoresByUF("http://159.203.95.153:3000/farmacia_popular_conveniada");
                /*This check happens because there may be failure during the requisition which would
                continue the steps with information missing. This maybe replaced by and exception.*/
                if(jsonPublic != null && jsonPrivate != null){

                    JSONHelper jsonParser = new JSONHelper(context);

                    if(jsonParser.drugstorePublicListFromJSON(jsonPublic) &&
                            jsonParser.drugstorePrivateListFromJSON(jsonPrivate)){
                        drugStoreList = drugStoreDao.getAllDrugStores();
                    }else{/*Nothing to do*/}
                }else {/*Nothing to do*/}

            } else {
                drugStoreList = drugStoreDao.getAllDrugStores();
            }
    }

    /**
     * Set distance based on the coordenates for each drugstore and then sort the list.
     *
     * @param context
     *           the activity where this is being called.
     *
     * @param list
     *           the list of drugstores that need the distance to be set.
     *
     * @return a boolean indicator for testing
     *
     */
    public static boolean setDistance(Context context,ArrayList<DrugStore> list) {
        assert (context != null) : "Receive a null treatment";
        assert (list != null) : "Receive a null treatment";
        assert (list.size() > 0) : "Receive a empty treatment";

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

    /**
     * Request the rating for the 15 first drugstores so that it can be shown at the HospitalList.
     *
     * @throws ConnectionErrorException The request may fail to get the ratings.
     *
     */
    public void requestRating() throws ConnectionErrorException {
        HttpConnection httpConnection = new HttpConnection();
        for(int i = 0;i<15;i++){
            try {
                drugStoreList.get(i).setRate(httpConnection.getRating(drugStoreList.get(i).
                        getId(), "http://159.203.95.153:3000/rate/gid/"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the unique identifier of the android user.
     *
     * @param androidId
     * Identifier of the android user of this session.
     *
     */
    public void setAndroidId(String androidId) {
        assert (androidId != null) : "Receive a null treatment";
        assert (androidId != "") : "Receive a empty treatment";
        this.androidId = androidId;
    }

    /**
     * Gets the unique identifier of the android user.
     *
     * @returns the Identifier of the android user of this session.
     *
     */
    public String getAndroidId() {
        return androidId;
    }

    /**
     * Creates object that will determine how the comparation is done for setDistante function sort.
     *
     */
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
         * @return which stablishment has the grater distance.
         *
         */
        public int compare(Stablishment stablishment1, Stablishment stablishment2) {
            assert (stablishment1 != null) : "Receive a null treatment";
            assert (stablishment2 != null) : "Receive a null treatment";
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
     * @return response from server.
     *
     * @throws ConnectionErrorException there maybe a failure when connecting to the server.
     *
     */
    public String updateRate(int rate,String androidId,String drugstoreId )
            throws ConnectionErrorException {
        assert (rate >= 0) : "Receive a negative tratment";
        assert (androidId != null) : "Receive a null tratment";
        assert (androidId != "") : "Receive a empty tratment";
        assert (drugstoreId != null) : "Receive a null tratment";
        assert (drugstoreId != "") : "Receive a empty tratment";

        HttpConnection connection = new HttpConnection();
        String response = "";

        response = connection.newRequest("http://159.203.95.153:3000" + "/" + "rate" + "/" + "gid" +
                "/" + drugstoreId + "/" + "aid" + "/" + androidId + "/" + "rating" + "/" + rate);

        return response;
    }

}
