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

public class ChooseScreen extends Activity{

    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_screen);

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        Button hospitalButton = (Button) findViewById(R.id.melhor_em_casa_button);
        Button drugStoreButton = (Button) findViewById(R.id.farm_popular_button);
        ImageButton infoSaudeEmCasaButton = (ImageButton) findViewById(R.id.infoButton);
        ImageButton infoMelhorEmCasaButton = (ImageButton) findViewById(R.id.melhorEmCasaInfoButton);
        ImageButton infoDrugStoreButton = (ImageButton) findViewById(R.id.farmPopularInfoButton);

        gps = new GPSTracker(this);

        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospitalListThread();
            }
        });

        drugStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drugstoreListThread();
            }
        });

        infoSaudeEmCasaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextScreen = new Intent(getBaseContext(), InfoScreenSaudeEmCasa.class);
                startActivity(nextScreen);

            }
        });

        infoMelhorEmCasaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getBaseContext(), InfoScreenMelhorEmCasa.class);
                startActivity(nextScreen);

            }
        });

        infoDrugStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getBaseContext(), InfoScreenDrugStore.class);
                startActivity(nextScreen);

            }
        });

    }

    public void hospitalListThread(){
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Requerindo avaliações...");
        progress.show();
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    HospitalController.getInstance(getApplicationContext()).requestRating();
                    Intent nextScreen = new Intent(getBaseContext(), HospitalList.class);
                    startActivity(nextScreen);
                } catch (ConnectionErrorException e) {
                    Toast.makeText(getApplicationContext(),"Houve um error de conexão.\nverifique sua conexão com a internet. ",Toast.LENGTH_LONG).show();
                }
                progress.dismiss();
                Looper.loop();
            }
        }.start();
    }
    public void drugstoreListThread(){
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Requerindo avaliações...");
        progress.show();
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    DrugStoreController.getInstance(getApplicationContext()).requestRating();
                    Intent nextScreen = new Intent(getBaseContext(), DrugStoreList.class);
                    startActivity(nextScreen);
                } catch (ConnectionErrorException e) {
                    Toast.makeText(getApplicationContext(), "Houve um error de conexão.\nverifique sua conexão com a internet. ", Toast.LENGTH_LONG).show();
                }

                progress.dismiss();
                Looper.loop();
            }
        }.start();

    }


}