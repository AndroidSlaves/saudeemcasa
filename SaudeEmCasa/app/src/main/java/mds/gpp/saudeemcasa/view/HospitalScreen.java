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

import junit.framework.Assert;

import api.Exception.ConnectionErrorException;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.HospitalController;
import mds.gpp.saudeemcasa.model.Hospital;

public class HospitalScreen extends Fragment {

    final String MESSAGE_FAIL_CONNECTION = "Houve um erro de conexão.\n verifique se está " +
                                           "conectado a internet.";
    final String MESSAGE_SAVE = "Sua avaliação foi salva!";
    final String TELEPHONE = "Tel: ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View hospitalScreen = inflater.inflate(R.layout.hospital_screen, null);
        final HospitalController hospitalController = HospitalController.getInstance(this.getContext());
        final Hospital hospital = hospitalController.getHospital();

        // Setting name
        final TextView nameTextView = (TextView) hospitalScreen.findViewById(R.id.textViewHospName);
        nameTextView.setText(hospital.getName());

        // Address
        TextView addressTextView = (TextView) hospitalScreen.findViewById(R.id.textViewAddressHosp);
        addressTextView.setText(Html.fromHtml(hospital.getAddress() + " - " + hospital.getCity() +
                " - " + hospital.getState()));

        // Setting telephone
        TextView telephoneTextView = (TextView) hospitalScreen.findViewById(R.id.textViewHospTel);
        telephoneTextView.setText("Tel: " + hospital.getTelephone());

        Assert.assertEquals(nameTextView.getText(), hospital.getName());
        Assert.assertEquals(telephoneTextView.getText(), "Tel: " + hospital.getTelephone());

        //set ratting for drugstore
        final RatingBar ratingBarFinal = (RatingBar) hospitalScreen.findViewById(R.id.ratingBarFinalHospital);
        ratingBarFinal.setRating(hospital.getRate());
        Assert.assertEquals(ratingBarFinal.getRating(), hospital.getRate());

        TextView textViewRate = (TextView) hospitalScreen.findViewById(R.id.textViewRatingHospital);
        textViewRate.setText("" + hospital.getRate());
        Assert.assertEquals(textViewRate.getText(), "" + hospital.getRate());

        Button hospitalButton = (Button) hospitalScreen.findViewById(R.id.buttonSaveRateHostpital);
        final RatingBar hospitalStars = (RatingBar) hospitalScreen.
                findViewById(R.id.ratingBarUserHospital);

        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {

                    public void run() {
                        Looper.prepare();
                        try {
                            hospitalController.updateRate((int) hospitalStars.getRating(),
                                    hospitalController.getAndroidId(), hospital.getId());
                                    Toast.makeText(getContext(),MESSAGE_SAVE ,
                                    Toast.LENGTH_LONG).show();

                        } catch(ConnectionErrorException exceptionConnectionError) {
                            Toast.makeText(getContext(), MESSAGE_FAIL_CONNECTION,Toast.LENGTH_LONG).show();
                        }
                        Looper.loop();
                    }
                }.start();
            }

        });

        ImageButton phoneCallButton = (ImageButton) hospitalScreen.findViewById(R.id.phonecallButtonHospital);

        phoneCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws SecurityException {
                Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse(TELEPHONE
                        + hospital.getTelephone()));
                startActivity(phoneCall);
            }
        });

        return hospitalScreen;

    }

}