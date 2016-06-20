/*****************************
 * Class name: LoadingScreen(.java)
 *
 * Purpose: Initial screen of application, download data from server and stores into database.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.widget.ImageView;
import android.os.Handler;

//import com.firebase.client.Firebase;

<<<<<<< HEAD
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONException;
import java.io.IOException;

import api.Dao.DrugStoreDao;
=======
import com.firebase.client.Firebase;

import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;

>>>>>>> 45bb9473b92202dc352ece0ce6cff56cad771997
import api.Exception.ConnectionErrorException;
import api.Helper.FirebaseHelper;
import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.controller.HospitalController;
import mds.gpp.saudeemcasa.model.DrugStore;


public class LoadingScreen extends Activity {

    HospitalController hospitalController;
    private Handler messageHandler = new Handler();

    /**
     *  @param base
     *                  Define the basic context for this Context Wrapper.
     */
    // (*) To solve ERROR: _non-zero exit value 2_
    @Override
    protected void attachBaseContext(Context base) {
        assert (base != null) : "Receive a null tratment";

        super.attachBaseContext(base);
        MultiDex.install(this);
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
<<<<<<< HEAD
        final DrugStoreDao drugStoreDao = DrugStoreDao.getInstance(this);
=======

>>>>>>> 45bb9473b92202dc352ece0ce6cff56cad771997
        MultiDex.install(this);
        setContentView(R.layout.loading_screen);
        final ImageView logoSaudeEmCasa = (ImageView) findViewById(R.id.saude_em_casa_logo);

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
                String postalcode = "";
                String id = "";
                String type = "AQUITEMFARMACIAPOPULAR";
                int count = 0;
                System.out.println(dataSnapshot.toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    for (DataSnapshot alldrugstore : snapshot.getChildren()) {

                        id = alldrugstore.getValue().toString();
                        for (DataSnapshot drugstoreValues : alldrugstore.getChildren()) {
                            //System.out.println(drugstoreValues.toString());

                            if (drugstoreValues.getKey().equalsIgnoreCase("lat")) {
                                latitude = drugstoreValues.getValue().toString();
                            }
                            if (drugstoreValues.getKey().equalsIgnoreCase("long")) {
                                longitude = drugstoreValues.getValue().toString();
                            }
                            if (drugstoreValues.getKey().equalsIgnoreCase("nu_telefone_farmacia")) {
                                telephone = drugstoreValues.getValue().toString();
                            }
                            if (drugstoreValues.getKey().equalsIgnoreCase("no_farmacia")) {
                                name = drugstoreValues.getValue().toString();
                            }
                            if (drugstoreValues.getKey().equalsIgnoreCase("no_cidade")) {
                                city = drugstoreValues.getValue().toString();
                            }
                            if (drugstoreValues.getKey().equalsIgnoreCase("ds_endereco_farmacia")) {
                                address = drugstoreValues.getValue().toString();
                            }
                            if (drugstoreValues.getKey().equalsIgnoreCase("uf")) {
                                state = drugstoreValues.getValue().toString();
                            }
                            if (drugstoreValues.getKey().equalsIgnoreCase("nu_cep_farmacia")) {
                                postalcode = drugstoreValues.getValue().toString();
                            }
                        }
                        System.out.println("TESTE "+latitude +","+ longitude);
                        DrugStore newDrugstore = new DrugStore(latitude,longitude,telephone,name,city,address,state,id,type,postalcode);
                        System.out.println(drugStoreDao.insertDrugstore(newDrugstore));

                        count++;

                    }
                    System.out.println("Percorri "+count+" farmacias.");
                }
                DrugStoreController drugStoreController = DrugStoreController.getInstance(getApplicationContext());
                try {
                    drugStoreController.initControllerDrugstore();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ConnectionErrorException e) {
                    e.printStackTrace();
                }
                toListScreen();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    //requestStablishment();


    /*
     * Responsible for requesting communication with the Stabliment. Building alert dialogs,
     * defined the connection failure.
	 * Messages dialogue progress and start communication with the hospital or drugstore controller.
	 */
    public void requestStablishment() {
        DrugStoreController drugStoreController = DrugStoreController.getInstance(this);


        /*AlertDialog.Builder messageNeutralBuilder = new AlertDialog.Builder( this );
        final String MESSAGE_CONECTION_FAILURE = "Falha na Conexão";
        final String MESSAGE_DOWNLOAD_DATA_FAILURE = "Falha ao baixar os dados.";
        final String MESSAGE_UPLOADING_DATA = "Carregando dados...";
        final String MESSAGE_UPLOAD_COMPLETED = "Dados carregados";
        final String MESSAGE_RETRY = "Retry";
        final String MESSAGE_CANCEL = "Cancel";

        messageNeutralBuilder.setTitle(MESSAGE_CONECTION_FAILURE)
                             .setMessage(MESSAGE_DOWNLOAD_DATA_FAILURE);
        messageNeutralBuilder.setPositiveButton(MESSAGE_RETRY, new RetryButtonListener());
        messageNeutralBuilder.setNegativeButton(MESSAGE_CANCEL, new CancelButtonListener());

        final AlertDialog messageFailedConnection = messageNeutralBuilder.create();
        assert(messageFailedConnection != null) : "message to not be shown must be null";

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage(MESSAGE_UPLOADING_DATA);
        progress.show();

        new Thread() {

            public void run() {
                Looper.prepare();

                final HospitalController hospitalController = HospitalController.getInstance(
                                                              getApplicationContext());
                assert(hospitalController != null) : "controller must not be null";

                final String androidId = "" + android.provider.Settings.Secure.getString(
                                         getContentResolver(), android.provider.Settings.Secure
                                         .ANDROID_ID);
                assert(androidId != null) : "id must not be null";
                hospitalController.setAndroidId(androidId);
                InputStream is = null;
                try {
                    is = getBaseContext().getAssets().open("json_test_data.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    hospitalController.initControllerHospital(is);
                } catch (IOException e) {
                    showMessageOnThread(messageFailedConnection, messageHandler);
                    e.printStackTrace();
                } catch (JSONException e) {
                    showMessageOnThread(messageFailedConnection, messageHandler);
                    e.printStackTrace();
                } catch (ConnectionErrorException cee) {
                    showMessageOnThread(messageFailedConnection, messageHandler);
                }

                System.out.println("ESTOU NA LOADING SCREEN");
                final DrugStoreController drugstoreController = DrugStoreController.getInstance(
                                                                getApplicationContext());
                drugstoreController.setAndroidId(androidId);
                try {
                    is = getBaseContext().getAssets().open("json_drugstore_test_data.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    drugstoreController.initControllerDrugstore(is);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (ConnectionErrorException cee){
                    showMessageOnThread(messageFailedConnection, messageHandler);
                }
                System.out.println("ESTOU NA LOADING SCREEN");
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        progress.setMessage(MESSAGE_UPLOAD_COMPLETED);
<<<<<<< HEAD
                        System.out.println("TO NA THREAD");
                        if (drugstoreController.getAllDrugstores().size()>0){// &&
                        //    hospitalController.getAllHospitals().size()>0) {
                            System.out.println(drugstoreController.getAllDrugstores().size());
                            System.out.println("NOME: "+drugstoreController.getAllDrugstores().get(0).getName());
                            toListScreen();
                        } else {/* Nothing To Do. }
=======

                        progress.dismiss();
                        //if (drugstoreController.getAllDrugstores().size()>0 &&
                          //  hospitalController.getAllHospitals().size()>0) {
                            toListScreen();
                        //} else {/* Nothing To Do. */}
>>>>>>> 45bb9473b92202dc352ece0ce6cff56cad771997

                        Looper.loop();
                    }
                });
            }
        }.start();*/
    }


    /**
     *
     * @param message
     *              Stores message for runtime error.
     * @param messageHandler
     *              Support the error message, show after thread run.
     */
    private void showMessageOnThread( final AlertDialog message,
                                      Handler messageHandler ) {
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
    }

    /*
     * Method responsible to retry the connection to the server, if failed once.
     */
    private class RetryButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick( DialogInterface dialog, int which ) {
                dialog.dismiss();

                Intent myAction = new Intent(LoadingScreen.this, LoadingScreen.class);

                LoadingScreen.this.startActivity(myAction);
                LoadingScreen.this.finish();
        }
    }

    /*
     * Method responsible to end or cancel the server request, if failed once. Close application.
     */
    private class CancelButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick( DialogInterface dialog, int which ) {
            dialog.dismiss();

            LoadingScreen.this.finish();
        }

    }
}
