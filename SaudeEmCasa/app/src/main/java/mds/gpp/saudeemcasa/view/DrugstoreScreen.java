package mds.gpp.saudeemcasa.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import android.Manifest;
import android.app.Activity;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import android.text.Html;
import android.view.View;
import android.widget.ImageButton;

import android.widget.TextView;


import java.util.ArrayList;

import java.util.UUID;

import api.Exception.ConnectionErrorException;
import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.model.DrugStore;

import android.provider.Settings.Secure;
import android.widget.Toast;

import static java.security.AccessController.getContext;

/**
 * Created by lucas on 10/21/15.
 */


public class DrugstoreScreen extends Fragment {

    ArrayList<DrugStore> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {

        View view = inflater.inflate(R.layout.drugstore_screen, null);

        final DrugStoreController controller = DrugStoreController.getInstance(this.getContext());

        final DrugStore drugStore = controller.getDrugstore();

        // setting name

        TextView nameTextView = (TextView) view.findViewById(R.id.textViewDrugName);
        nameTextView.setText(drugStore.getName());
        // Address
        TextView addressTextView = (TextView) view.findViewById(R.id.textViewAddress);
        addressTextView.setText(Html.fromHtml(drugStore.getAddress() + " - " + drugStore.getCity() + " - " + drugStore.getState()));
        //CEP
        TextView cepTextView = (TextView) view.findViewById(R.id.textViewCep);
        cepTextView.setText("Cep: " + drugStore.getPostalCode());
        // setting telephone
        if (drugStore.getType().equals("FARMACIAPOPULAR")) {
            TextView telephoneTextView = (TextView) view.findViewById(R.id.textViewDrugTel);
            telephoneTextView.setText("");
        } else {
            TextView telephoneTextView = (TextView) view.findViewById(R.id.textViewDrugTel);
            telephoneTextView.setText("Tel: " + drugStore.getTelephone());

        }


        //set ratting for drugstore

        RatingBar ratingBarFinal = (RatingBar) view.findViewById(R.id.ratingBarFinalDrugstore);
        ratingBarFinal.setRating(drugStore.getRate());

        TextView textViewRate = (TextView) view.findViewById(R.id.textViewRatingDrugstore);

        textViewRate.setText("" + drugStore.getRate());

        Button drugStoreButton = (Button) view.findViewById(R.id.buttonSaveRateDrugstore);
        final RatingBar drugstoreStars = (RatingBar) view.findViewById(R.id.ratingBarUserDrugstore);
        drugStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {

                    public void run() {
                        Looper.prepare();

                        try {

                            controller.updateRate((int) drugstoreStars.getRating(), controller.getAndroidId(), drugStore.getId());
                            Toast.makeText(getContext(),"Sua avaliação foi salva!",Toast.LENGTH_LONG).show();
                        } catch (ConnectionErrorException e) {

                            Toast.makeText(getContext(),"Houve um error de conexão.\nverifique se está conectado a internet.",Toast.LENGTH_LONG).show();

                        }


                        Looper.loop();
                    }
                }.start();
            }

        });

            ImageButton phoneCallButton = (ImageButton) view.findViewById(R.id.phonecallButtonDrugstore);
            phoneCallButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) throws SecurityException{


                    Intent phoneCall = new Intent(Intent.ACTION_CALL);
                    phoneCall.setData(Uri.parse("tel:"+drugStore.getTelephone()));
                    startActivity(phoneCall);

                }
            });


        return (view);

    }


}

