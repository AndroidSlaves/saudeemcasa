/*****************************
 * Class name: HospitalScreen (.java)
 *
 * Purpose: Screen that shows the hospital full information for the user.
 ****************************/
package mds.gpp.saudeemcasa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import android.net.Uri;
import android.os.Looper;

import api.Exception.ConnectionErrorException;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.HospitalController;
import mds.gpp.saudeemcasa.model.Hospital;

public class HospitalScreen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaceState) {

        View hospitalScreen = inflater.inflate(R.layout.hospital_screen, null);

        final HospitalController hospitalController = HospitalController.
                getInstance(this.getContext());

        final Hospital hospital = hospitalController.getHospital();

        // setting name
        final TextView nameTextView = (TextView) hospitalScreen.findViewById(R.id.textViewHospName);
        nameTextView.setText(hospital.getName());

        // Address
        TextView addressTextView = (TextView) hospitalScreen.findViewById(R.id.textViewAddressHosp);
        addressTextView.setText(Html.fromHtml(hospital.getAddress() + " - " + hospital.getCity() +
                " - " + hospital.getState()));

        // setting telephone
        TextView telephoneTextView = (TextView) hospitalScreen.findViewById(R.id.textViewHospTel);
        telephoneTextView.setText("Tel: " + hospital.getTelephone());

        //set ratting for drugstore
        final RatingBar ratingBarFinal = (RatingBar) hospitalScreen.
                findViewById(R.id.ratingBarFinalHospital);
        ratingBarFinal.setRating(hospital.getRate());
        TextView textViewRate = (TextView) hospitalScreen.findViewById(R.id.textViewRatingHospital);
        textViewRate.setText("" + hospital.getRate());

        Button hospitalButton = (Button) hospitalScreen.findViewById(R.id.buttonSaveRateHostpital);
        final RatingBar hospitalStars = (RatingBar) hospitalScreen.
                findViewById(R.id.ratingBarUserHospital);

        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {

                    public void run() {
                        Looper.prepare();
                        try {
                            hospitalController.updateRate((int) hospitalStars.getRating(),
                                    hospitalController.getAndroidId(), hospital.getId());
                            Toast.makeText(getContext(), "Sua avaliação foi salva!",
                                    Toast.LENGTH_LONG).show();

                        } catch (ConnectionErrorException e) {

                            Toast.makeText(getContext(),"Houve um erro de conexão.\nverifique" +
                                    "se está conectado a internet.",Toast.LENGTH_LONG).show();

                        }

                        Looper.loop();
                    }
                }.start();
            }

        });

        ImageButton phoneCallButton = (ImageButton) hospitalScreen.
                findViewById(R.id.phonecallButtonHospital);

        phoneCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws SecurityException {

                Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse("Tel: " + hospital.getTelephone()));
                startActivity(phoneCall);

            }

        });

    return hospitalScreen;

    }

}