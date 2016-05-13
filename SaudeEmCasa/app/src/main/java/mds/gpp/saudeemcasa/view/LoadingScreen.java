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

import org.json.JSONException;
import java.io.IOException;
import api.Exception.ConnectionErrorException;
import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.controller.HospitalController;


public class LoadingScreen extends Activity {

    HospitalController hospitalController;
    private Handler messageHandler = new Handler();

    /**
      *
      * @param base
      *             Define the basic context for this Context Wrapper.
      */

    // to solve ERRO _non-zero exit value 2_
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

        MultiDex.install(this);
        setContentView(R.layout.loading_screen);
        final ImageView logoSaudeEmCasa = (ImageView) findViewById(R.id.saude_em_casa_logo);

        requestStablishment();
    }


    public void requestStablishment() {
        final AlertDialog.Builder msgNeutralBuilder = new AlertDialog.Builder( this );

        msgNeutralBuilder.setTitle("Falha na Conexão").setMessage("Não foi possível baixar os dados do servidor.");
        msgNeutralBuilder.setPositiveButton("Retry", new RetryButtonListener());
        msgNeutralBuilder.setNegativeButton("Cancel", new CancelButtonListener());

        final AlertDialog messageFailedConnection = msgNeutralBuilder.create();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Carregando dados...");
        progress.show();

        new Thread() {

            public void run() {
                Looper.prepare();

                final HospitalController hospitalController = HospitalController.getInstance(getApplicationContext());
                final String androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                hospitalController.setAndroidId(androidId);
                try {
                    hospitalController.initControllerHospital();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ConnectionErrorException cee) {
                    showMessageOnThread(messageFailedConnection, messageHandler);
                }

                final DrugStoreController drugstoreController = DrugStoreController.getInstance(getApplicationContext());
                drugstoreController.setAndroidId(androidId);
                try {
                    drugstoreController.initControllerDrugstore();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (ConnectionErrorException cee){
                    showMessageOnThread(messageFailedConnection, messageHandler);
                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        progress.setMessage("Dados carregados");

                        if (drugstoreController.getAllDrugstores().size()>0 && hospitalController.getAllHospitals().size()>0) {
                            toListScreen();
                        } else {
                            /* ! Nothing To Do. */
                        }


                        progress.dismiss();
                        Looper.loop();
                    }
                });
            }
        }.start();
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

    private void toListScreen() {
        finish();
        Intent nextScreen = new Intent(getBaseContext(), ChooseScreen.class);
        startActivity(nextScreen);
    }

    private class RetryButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick( DialogInterface dialog, int which ) {
                dialog.dismiss();

                Intent myAction = new Intent(LoadingScreen.this, LoadingScreen.class);

                LoadingScreen.this.startActivity(myAction);
                LoadingScreen.this.finish();
        }
    }

    private class CancelButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick( DialogInterface dialog, int which ) {
            dialog.dismiss();

            LoadingScreen.this.finish();
        }

    }
}
