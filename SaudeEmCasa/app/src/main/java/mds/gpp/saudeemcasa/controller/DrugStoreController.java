/*****************************
 * Class name: DrugStoreController (.java)
 *
 * Purpose: Entity that communicates with the backend functions. Contains the logic steps of the
 * application.
 ****************************/

package mds.gpp.saudeemcasa.controller;

import android.content.Context;
import android.location.Location;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import api.Dao.DrugStoreDao;

import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.model.Stablishment;

import static java.util.Collections.sort;
import static junit.framework.Assert.assertNotNull;

public class DrugStoreController {

    /* A unique instance of the DrugstoreController running on the software. There must not be more
    than one instance running. */
    private static DrugStoreController instance = null;
    // A selected drugstore on the list of drugstores by the user.
    private static DrugStore drugStore;
    // The list of drugstores to be shown to the user.
    private static List<DrugStore> drugStoreList = new ArrayList<DrugStore>();
    // Context of the screen where the user is.
    private static Context context;
    // Object that makes connection with the database.
    private static DrugStoreDao drugStoreDao;
    // Unique identification number of the user android.
    private String androidId;

    /**
     * Constructor for the DrugstoreController.
     *
     * @param context
     *              activity context where this method was called.
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
     *              activity context where this method was called.
     *
     * @return
     *              The unique instance of DrugstoreController.
     */
    public static DrugStoreController getInstance(Context context) {
        assert (context != null) : "Receive a null treatment";
        if(instance == null) {
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
     *              the selected drugstore on the list of drugstores.
     */
    public void setDrugStore(DrugStore drugStore) {
        assert (drugStore != null) : "Receive a null treatment";
        DrugStoreController.drugStore = drugStore;
    }

    /**
     * Get store drugstore
     *
     * @return drugStore
     *              the previously selected and stored drugstore.
     **/
    public DrugStore getDrugstore() {
        return drugStore;
    }

    /**
     * Give the list of the nearest drugstores to be show to the user in a list.
     *
     * @return drugStoreList
     *              the list of the nearest drugstores.
     *
     */
    public List<DrugStore> getAllDrugstores(){
        return drugStoreList;
    }

    /**
     * Method that is first initiated when the application is opened. It connects to the server and
     * the database on first use or just get the data from the database for usage.  
     */
    public void initControllerDrugstore(){

        drugStoreList = drugStoreDao.getAllDrugStores();
        assertNotNull("Received null treatment drugstoreList", drugStoreList);
    }

    /**
     * Set distance based on the coordenates for each drugstore and then sort the list.
     *
     * @param context
     *              the activity where this is being called.
     * @param list
     *              the list of drugstores that need the distance to be set.
     *
     * @return canSetDistance
     *              a boolean indicator for testing
     */
    public static boolean setDistance(Context context, ArrayList<DrugStore> list) {
        assert (context != null) : "Receive a null treatment";
        assert (list != null) : "Receive a null treatment";
        assert (list.size() > 0) : "Receive a empty treatment";

        GPSTracker gps = new GPSTracker(context);
        boolean canSetDistance = false;

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

            canSetDistance = true;
        }else {
            canSetDistance = false;
        }

        return canSetDistance;

    }

    /**
     * Saves the unique identifier of the android user.
     *
     * @param androidId
     *              Identifier of the android user of this session.
     */
    public void setAndroidId(String androidId) {
        assert (androidId != null) : "Receive a null treatment";
        assert (androidId != "") : "Receive a empty treatment";
        this.androidId = androidId;
    }

    /**
     * Gets the unique identifier of the android user.
     *
     * @returns
     *              the Identifier of the android user of this session.
     */
    public String getAndroidId() {
        return androidId;
    }

    /**
     * Creates object that will determine how the comparation is done for setDistante function sort.
     */
    public static class DistanceComparator implements Comparator<Stablishment> {

        /**
         * Use responseHandler created to request the requested through a URL.
         *
         * @param stablishment1
         *              A stablishment to be compared.
         * @param stablishment2
         *              A stablishment to be compared.
         *
         * @return
         *              which stablishment has the grater distance.
         */
        public int compare(Stablishment stablishment1, Stablishment stablishment2) {
            assert (stablishment1 != null) : "Receive a null treatment";
            assert (stablishment2 != null) : "Receive a null treatment";

            int firstStablishmentIsGreater = -1;
            if(stablishment1.getDistance() < (stablishment2.getDistance())){
                firstStablishmentIsGreater = 1;
            } else {
                firstStablishmentIsGreater = -1;
            }
            return firstStablishmentIsGreater;
        }

    }
}