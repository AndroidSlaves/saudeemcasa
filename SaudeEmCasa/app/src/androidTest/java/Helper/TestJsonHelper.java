package Helper;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import api.Dao.DrugStoreDao;
import api.Dao.HospitalDao;
import api.Helper.JSONHelper;
import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.view.LoadingScreen;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by lucas on 10/29/15.
 */
public class TestJsonHelper extends ActivityInstrumentationTestCase2<LoadingScreen> {

    private LoadingScreen myActivity;
    private Instrumentation myInstrumentation;
    JSONHelper jsonHelper;
    public TestJsonHelper() {
        super(LoadingScreen.class);
    }
    protected void setUp() throws Exception {
        super.setUp();
        this.myActivity = getActivity();
        this.myInstrumentation = getInstrumentation();
        jsonHelper = new JSONHelper(myActivity.getApplicationContext());

    }

    public void testHospitalListFromJSON(){

        HospitalDao hospitalDao = HospitalDao.getInstance(myActivity.getApplicationContext());

        try {
            jsonHelper.hospitalListFromJSON(loadJSONFromAsset("json_test_data.json"));
        } catch (JSONException e) {
            assertTrue(false);
        }
        if(hospitalDao.getAllHospitals().size()>0){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
        hospitalDao.deleteAllHospitals();

    }
    public void testDrugstorePublicListFromJSON(){
        DrugStoreDao drugStoreDao = DrugStoreDao.getInstance(myActivity.getApplicationContext());

        try {
            jsonHelper.drugstorePublicListFromJSON(loadJSONFromAsset("json_drugstore_test_data.json"));
        } catch (JSONException e) {
            assertTrue(false);
        }
        if(drugStoreDao.getAllDrugStores().size()>0){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
        drugStoreDao.deleteAllDrugStores();

    }
    public void testDrugstorePrivateListFromJSON(){
        DrugStoreDao drugStoreDao = DrugStoreDao.getInstance(myActivity.getApplicationContext());

        try {
            jsonHelper.drugstorePrivateListFromJSON(loadJSONFromAsset("json_drugstore_test_data_2.json"));
        } catch (JSONException e) {
            assertTrue(false);
        }
        if(drugStoreDao.getAllDrugStores().size()>0){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
        drugStoreDao.deleteAllDrugStores();

    }

    public String loadJSONFromAsset(String jsonfile) {
        String json = null;

        try {
            InputStream is = getActivity().getAssets().open(jsonfile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}

