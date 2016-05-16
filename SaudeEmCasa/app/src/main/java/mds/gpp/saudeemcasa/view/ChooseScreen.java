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
    public final String connectionErrorText = "Houve um erro de conexão.\nVerifique " +
            "sua conexão com a internet.";
    public final String rateFetchText = "Requerindo avaliações...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_screen);

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        gps = new GPSTracker(this);
        Button hospitalButton = (Button) findViewById(R.id.melhor_em_casa_button);
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospitalListThread();
            }
        });

        Button drugStoreButton = (Button) findViewById(R.id.farm_popular_button);
        drugStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drugstoreListThread();
            }
        });

        ImageButton infoSaudeEmCasaButton = (ImageButton) findViewById(R.id.infoButton);
        infoSaudeEmCasaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getBaseContext(), InfoScreenSaudeEmCasa.class);
                startActivity(nextScreen);
            }
        });

        ImageButton infoMelhorEmCasaButton = (ImageButton) findViewById(R.id.melhorEmCasaInfoButton);
        infoMelhorEmCasaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getBaseContext(), InfoScreenMelhorEmCasa.class);
                startActivity(nextScreen);
            }
        });

        ImageButton infoDrugStoreButton = (ImageButton) findViewById(R.id.farmPopularInfoButton);
        infoDrugStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getBaseContext(), InfoScreenDrugStore.class);
                startActivity(nextScreen);
            }
        });

    }

    /**
     * This thread is used to get the ratings of each hospital from several.
     * */
    public void hospitalListThread() {
        final ProgressDialog progress = new ProgressDialog(this);
        showProgress(progress, rateFetchText);

        new Thread() {
            public void run() {
                Looper.prepare();

                try {
                    HospitalController.getInstance(getApplicationContext()).requestRating();
                    Intent nextScreen = new Intent(getBaseContext(), HospitalList.class);
                    startActivity(nextScreen);

                } catch (ConnectionErrorException e) {
                    Toast.makeText(getApplicationContext(), connectionErrorText,
                            Toast.LENGTH_LONG).show();
                }
                progress.dismiss();
                Looper.loop();
            }
        }.start();
    }

    /**
     * This thread is used to get the ratings of each drugstore from server.
     * */
    public void drugstoreListThread() {
        final ProgressDialog progress = new ProgressDialog(this);
        showProgress(progress,rateFetchText);

        new Thread() {
            public void run() {
                Looper.prepare();

                try {
                    DrugStoreController.getInstance(getApplicationContext()).requestRating();
                    Intent nextScreen = new Intent(getBaseContext(), DrugStoreList.class);
                    startActivity(nextScreen);

                } catch (ConnectionErrorException connectionError) {
                    Toast.makeText(getApplicationContext(),connectionErrorText,
                            Toast.LENGTH_LONG).show();
                }

                progress.dismiss();
                Looper.loop();
            }
        }.start();
    }

    /*
    * Show dialog box with progress and message.
    *
    * @param progress
    *           Dialog box that will show the specified message.
    * @param message
    *           Text message to be shown in the dialog box.
    *
    * @returns progress in order to test it.
    * */
    private ProgressDialog showProgress(ProgressDialog progress, String message){
        assert (message != null): "Message must never be null";
        assert (progress !=null): "Progress must never be null";

        progress.setMessage(message);
        progress.show();

        return progress;
    }
}
