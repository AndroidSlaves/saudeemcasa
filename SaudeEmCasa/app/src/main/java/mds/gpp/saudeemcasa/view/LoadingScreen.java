/*****************************
 * Class name: LoadingScreen(.java)
 *
 * Purpose: Initial screen of application, download data from server and stores into database.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONException;

import java.io.IOException;

import api.Dao.DrugStoreDao;
import api.Exception.ConnectionErrorException;
import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.model.DrugStore;

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
        Log.d(TAG, "Android Context sitting on Firebase.");

        final DrugStoreDao drugStoreDao = DrugStoreDao.getInstance(this);

        MultiDex.install(this);
        setContentView(R.layout.loading_screen);

        final Firebase drugstoreFirebase = new Firebase("https://farmpopularconv.firebaseio.com/");
        drugstoreFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String latitude = "";
                String longitude = "";
                String telephone = "";
                String name = "";
                String city = "";
                String address = "";
                String state = "";
                String postalCode = "";
                String id = "";
                String type = "AQUITEMFARMACIAPOPULAR";
                int count = 0;

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for(DataSnapshot allDrugstore : snapshot.getChildren()) {

                        id = allDrugstore.getValue().toString();
                        for (DataSnapshot drugstoreValues : allDrugstore.getChildren()) {

                            if (drugstoreValues.getKey().equalsIgnoreCase("lat")) {
                                latitude = drugstoreValues.getValue().toString();
                            } else if (drugstoreValues.getKey().equalsIgnoreCase("long")) {
                                longitude = drugstoreValues.getValue().toString();
                            } else if (drugstoreValues.getKey().equalsIgnoreCase("nu_telefone_farmacia")) {
                                telephone = drugstoreValues.getValue().toString();
                            } else if (drugstoreValues.getKey().equalsIgnoreCase("no_farmacia")) {
                                name = drugstoreValues.getValue().toString();
                            } else if (drugstoreValues.getKey().equalsIgnoreCase("no_cidade")) {
                                city = drugstoreValues.getValue().toString();
                            } else if (drugstoreValues.getKey().equalsIgnoreCase("ds_endereco_farmacia")) {
                                address = drugstoreValues.getValue().toString();
                            } else if (drugstoreValues.getKey().equalsIgnoreCase("uf")) {
                                state = drugstoreValues.getValue().toString();
                            } else if (drugstoreValues.getKey().equalsIgnoreCase("nu_cep_farmacia")) {
                                postalCode = drugstoreValues.getValue().toString();
                            } else {
                                /* Nothing to do! */
                            }
                        }

                        Log.d(TAG, "Latitude check: " + latitude);
                        Log.d(TAG, "Longitude check: " + longitude);

                        DrugStore newDrugstore = new DrugStore(latitude,longitude,telephone,name,city,
                                address,state,id,type,postalCode);

                        if(drugStoreDao.insertDrugstore(newDrugstore) == 1){
                            Log.i(TAG, "Insert Drugstore is ok!");
                        } else {
                            Log.i(TAG, "Insert Drugstore is failure!");
                        }

                        count++;

                    }
                    Log.d(TAG, "Check amount of drugstores: " + count);
                }

                DrugStoreController drugStoreController = DrugStoreController.getInstance(getApplicationContext());

                try {
                    drugStoreController.initControllerDrugstore();
                } catch (IOException exceptionIOInitController) {
                    Log.e(TAG, "Input error and output data to start pharmacy controller.");
                    exceptionIOInitController.printStackTrace();
                } catch (JSONException exceptionJSONInitController) {
                    Log.e(TAG, "JSON error starting pharmacy controller.");
                    exceptionJSONInitController.printStackTrace();
                } catch (ConnectionErrorException exceptionConnectionInitController) {
                    Log.e(TAG, "Error connecting to the database to start pharmacy controller.");
                    exceptionConnectionInitController.printStackTrace();
                }

                toListScreen();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    /**
     *
     * @param message
     *              Stores message for runtime error.
     * @param messageHandler
     *              Support the error message, show after thread run.
     */
    private void showMessageOnThread(final AlertDialog message, Handler messageHandler) {
        assert(messageHandler != null) : "messageHandler must never be null";
        assert(message != null) : "message must never be null";

        messageHandler.post(new Runnable() {
            public void run() {
                message.show();
            }
        });
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

    /*
     * Method responsible to retry the connection to the server, if failed once.
     */
    private class RetryButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();

            Intent myAction = new Intent(LoadingScreen.this, LoadingScreen.class);

            LoadingScreen.this.startActivity(myAction);
            Log.i(TAG, "Activity started with my intention Action in return button.");
            LoadingScreen.this.finish();
            Log.i(TAG, "Screen Loading Screen finalized by clicking button to return.");
        }
    }

    /*
     * Method responsible to end or cancel the server request, if failed once. Close application.
     */
    private class CancelButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();

            LoadingScreen.this.finish();
            Log.i(TAG, "Screen Loading Screen finalized by clicking the cancel button.");
        }

    }
}
