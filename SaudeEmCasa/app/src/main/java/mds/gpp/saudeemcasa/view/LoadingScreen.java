/*****************************
 * Class name: LoadingScreen(.java)
 *
 * Purpose: Initial screen of application, download data from server and stores into database.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.firebase.client.Firebase;

import api.Helper.FirebaseHelper;
import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.controller.HospitalController;

public class LoadingScreen extends Activity {
    //Tag is used in log system.
    private final String TAG = LoadingScreen.class.getSimpleName();
    /**
     *  @param base
     *                  Define the basic context for this Context Wrapper.
     */
    @Override
    protected void attachBaseContext(Context base) {
        assert (base != null) : "Receive a null tratment";

        super.attachBaseContext(base);
        // (*) To solve ERROR: _non-zero exit value 2_
        MultiDex.install(this);
        Log.i(TAG, "Instalação MultiDex.");
    }

    /**
     *
     * @param savedInstanceState
     *              Save the previous instance of LoadingScreen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        MultiDex.install(this);
        setContentView(R.layout.loading_screen);

        FirebaseHelper firebaseHelper = new FirebaseHelper();

        firebaseHelper.getDrugstoreInfo(getApplicationContext());
        firebaseHelper.getHospitalInfo(getApplicationContext());

        DrugStoreController drugStoreController = DrugStoreController.getInstance(getApplicationContext());
        drugStoreController.initControllerDrugstore();

        HospitalController hospitalController = HospitalController.getInstance(getApplicationContext());
        hospitalController.initControllerHospital();
        toListScreen();

    }

    /*
     * Method to end the LoadingScreen activity. After the activity close, call the next screen
     * for user choose hospital list or drugstore list.
     */
    private void toListScreen() {
        finish();

        Intent nextScreen = new Intent(getBaseContext(), ChooseScreen.class);
        startActivity(nextScreen);
        Log.i(TAG, "ToList Screen - Next initialized screen.");
    }
}
