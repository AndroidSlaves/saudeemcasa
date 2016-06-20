/*****************************
 * Class name: ChooseScreen (.java)
 *
 * Purpose: Screen that allows the user's choice for application information and government
 * programs, and the lists of hospitals and pharmacies.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import api.Exception.ConnectionErrorException;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.controller.HospitalController;
import mds.gpp.saudeemcasa.helper.GPSTracker;

public class ChooseScreen extends Activity {

    public GPSTracker gps;
    public final String CONNECTION_ERROR_TEXT = "Houve um erro de conexão.\nVerifique sua " +
                                                "conexão com a internet.";
    public final String FETCH_RATE_TEXT = "Requerindo avaliações...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_screen);
        assert (savedInstanceState != null) : "Receive a null treatment";

        // Connect the the scroller the screen.
        final int LEFT_SLIDE_ID = android.R.anim.slide_in_left;
        final int RIGHT_SLIDE_ID = android.R.anim.slide_out_right;
        overridePendingTransition(RIGHT_SLIDE_ID,LEFT_SLIDE_ID);

        GPSTracker gps = new GPSTracker(this);

        // Setting event click for hospital list.
        final int HOSPITAL_BUTTON_ID = R.id.melhor_em_casa_button;
        Button hospitalButton = (Button) findViewById(HOSPITAL_BUTTON_ID);
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospitalListThread();
                assert (v != null) : "Receive a null treatment";
            }
        });

        // Setting event click for drugstore list.
        final int POPULAR_DRUGSTORE_BUTTON_ID =  R.id.farm_popular_button;
        Button drugStoreButton = (Button) findViewById(POPULAR_DRUGSTORE_BUTTON_ID);
        drugStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drugstoreListThread();
                assert (v != null) : "Receive a null treatment";
            }
        });

        // Setting event click on information about app button.
        final int APP_INFO_BUTTON_ID = R.id.infoButton;
        ImageButton infoSaudeEmCasaButton = (ImageButton) findViewById(APP_INFO_BUTTON_ID);
        infoSaudeEmCasaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getBaseContext(), InfoScreenSaudeEmCasa.class);
                assert (nextScreen != null) : "Receive a null treatment";
                startActivity(nextScreen);
                assert (v != null) : "Receive a null treatment";
            }
        });

        // Setting event click on information about hospital button.
        final int HOSPITAL_INFO_BUTTON_ID = R.id.melhorEmCasaInfoButton;
        ImageButton infoMelhorEmCasaButton = (ImageButton) findViewById(HOSPITAL_INFO_BUTTON_ID);
        infoMelhorEmCasaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getBaseContext(), InfoScreenMelhorEmCasa.class);
                assert (nextScreen != null) : "Receive a null treatment";
                startActivity(nextScreen);
                assert (v != null) : "Receive a null treatment";
            }
        });

        // Setting event click on information about drugstore button.
        final int DRUGSTORE_INFO_BUTTON_ID = R.id.farmPopularInfoButton;
        ImageButton infoDrugStoreButton = (ImageButton) findViewById(DRUGSTORE_INFO_BUTTON_ID );
        infoDrugStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getBaseContext(), InfoScreenDrugStore.class);
                assert (nextScreen != null) : "Receive a null treatment";
                startActivity(nextScreen);
                assert (v != null) : "Receive a null treatment";
            }
        });
    }

    /**
     * This thread is used to get the ratings of each hospital from several.
     */
    public void hospitalListThread() {
        /*final ProgressDialog PROGRESS_DIALOG = new ProgressDialog(this);
        showProgress(PROGRESS_DIALOG, FETCH_RATE_TEXT);

        new Thread() {
            public void run() {
                Looper.prepare();

                // Request ratings for hospital and go to hospital list.
                //try {*/
                    //HospitalController.getInstance(getApplicationContext()).requestRating();

                    Intent nextScreen = new Intent(getBaseContext(), HospitalList.class);
                    assert (nextScreen != null) : "Receive a null treatment";
                    startActivity(nextScreen);


                //} catch (ConnectionErrorException e) {
                //    Toast.makeText(getApplicationContext(), CONNECTION_ERROR_TEXT,
                //            Toast.LENGTH_LONG).show();
                //}
/*
                PROGRESS_DIALOG.dismiss();
                Looper.loop();
            }
        }.start();*/

    }

    /**
     * This thread is used to get the ratings of each drugstore from server.
     * */
    public void drugstoreListThread() {
        /*final ProgressDialog PROGRESS_DIALOG = new ProgressDialog(this);
        showProgress(PROGRESS_DIALOG, FETCH_RATE_TEXT);

        new Thread() {
            public void run() {
                Looper.prepare();
*/
                // Request ratings for drugstore and go to drugstore list.
                //try {
                    //DrugStoreController.getInstance(getApplicationContext()).requestRating();

                    Intent nextScreen = new Intent(getBaseContext(), DrugStoreList.class);
                    assert (nextScreen != null) : "Receive a null treatment";
                    startActivity(nextScreen);

                //} catch (ConnectionErrorException connectionError) {
                //    Toast.makeText(getApplicationContext(), CONNECTION_ERROR_TEXT,
                 //           Toast.LENGTH_LONG).show();
                //}
/*

                PROGRESS_DIALOG.dismiss();
                Looper.loop();
            }
        }.start();*/
    }

    /**
     * Show dialog box with progress and message.
     *
     * @param PROGRESS_DIALOG
     *              Dialog box that will show the specified message.
     * @param MESSAGE
     *              Text message to be shown in the dialog box.
     *
     * @returns
     *              progress in order to test it.
     */
    private ProgressDialog showProgress(final ProgressDialog PROGRESS_DIALOG, final String MESSAGE){
        assert (MESSAGE != null): "Message must never be null";
        assert (PROGRESS_DIALOG !=null): "Progress must never be null";

        PROGRESS_DIALOG.setMessage(MESSAGE);
        PROGRESS_DIALOG.show();

        return PROGRESS_DIALOG;
    }
}
