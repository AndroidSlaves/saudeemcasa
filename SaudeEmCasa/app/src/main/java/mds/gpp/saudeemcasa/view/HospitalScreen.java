package mds.gpp.saudeemcasa.view;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.text.Html;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.RatingBar;

import android.widget.TextView;
import android.widget.Toast;

import api.Exception.ConnectionErrorException;
import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.HospitalController;
import mds.gpp.saudeemcasa.model.Hospital;

/**
 * Created by freemanpivo on 11/14/15.
 */
public class HospitalScreen extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {

        View view = inflater.inflate(R.layout.hospital_screen, null);

        final HospitalController controller = HospitalController.getInstance(this.getContext());

        final Hospital hospital = controller.getHospital();
        // setting name
        final TextView nameTextView = (TextView) view.findViewById(R.id.textViewHospName);
        nameTextView.setText(hospital.getName());
        // Address
        TextView addressTextView = (TextView) view.findViewById(R.id.textViewAddressHosp);
        addressTextView.setText(Html.fromHtml(hospital.getAddress() + " - " + hospital.getCity() + " - " + hospital.getState()));
        // setting telephone
        TextView telephoneTextView = (TextView) view.findViewById(R.id.textViewHospTel);
        telephoneTextView.setText("Tel: " + hospital.getTelephone());

        //set ratting for drugstore

        final RatingBar ratingBarFinal = (RatingBar) view.findViewById(R.id.ratingBarFinalHospital);

        ratingBarFinal.setRating(hospital.getRate());

        TextView textViewRate = (TextView) view.findViewById(R.id.textViewRatingHospital);
        textViewRate.setText("" + hospital.getRate());


        Button hospitalButton = (Button) view.findViewById(R.id.buttonSaveRateHostpital);
        final RatingBar hospitalStars = (RatingBar) view.findViewById(R.id.ratingBarUserHospital);

        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {

                    public void run() {
                        Looper.prepare();
                        try {
                            controller.updateRate((int) hospitalStars.getRating(), controller.getAndroidId(), hospital.getId());
                            Toast.makeText(getContext(),"Sua avaliação foi salva!",Toast.LENGTH_LONG).show();

                        } catch (ConnectionErrorException e) {

                            Toast.makeText(getContext(),"Houve um error de conexão.\nverifique se está conectado a internet.",Toast.LENGTH_LONG).show();

                        }

                        Looper.loop();
                    }
                }.start();
            }

        });

        ImageButton phoneCallButton = (ImageButton) view.findViewById(R.id.phonecallButtonHospital);
        phoneCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws SecurityException {

                Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + hospital.getTelephone()));


                startActivity(phoneCall);

            }

        });






        /*Button hospitalMapButton = (Button) findViewById(R.id.button_hospital_map);

        hospitalMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GoogleMapHospital.class);
                startActivity(intent);
            }
        });*/

    return view;
}

}


