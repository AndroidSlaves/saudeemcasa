/*****************************
 * Class name: DrugstoreScreen (.java)
 *
 * Purpose: Screen that shows the information of the selected drugstore in the previous list screen.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import api.Exception.ConnectionErrorException;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.model.DrugStore;

public class DrugstoreScreen extends Fragment {

    final String DRUSTORE_TYPE = "FARMACIAPOPULAR";
    final String MESSAGE_SAVE = "Sua avaliação foi salva!";
    final String MESSAGE_FAIL_CONECTION = "Houve um erro de conexão.\nverifique se  está " +
                                          "conectado a internet.";
    final String CEP = "CEP: ";
    final String TELEPHONE = "Tel: ";
    final String ONE_SPACE = " - ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaceState) {

        View drugStoreScreen = inflater.inflate(R.layout.drugstore_screen, null);

        final DrugStoreController drugStoreController = DrugStoreController.
                getInstance(this.getContext());

        final DrugStore drugStore = drugStoreController.getDrugstore();

        TextView nameTextView = (TextView) drugStoreScreen.findViewById(R.id.textViewDrugName);
        nameTextView.setText(drugStore.getName());

        TextView addressTextView = (TextView) drugStoreScreen.findViewById(R.id.textViewAddress);
        addressTextView.setText(Html.fromHtml(drugStore.getAddress() + ONE_SPACE +
                        drugStore.getCity() + ONE_SPACE + drugStore.getState()));

        TextView cepTextView = (TextView) drugStoreScreen.findViewById(R.id.textViewCep);
        cepTextView.setText(CEP + drugStore.getPostalCode());

        if (drugStore.getType().equals(DRUSTORE_TYPE)) {
            TextView telephoneTextView = (TextView) drugStoreScreen.
                                         findViewById(R.id.textViewDrugTel);
            telephoneTextView.setText("");
        } else {
            TextView telephoneTextView = (TextView) drugStoreScreen
                                         .findViewById(R.id.textViewDrugTel);
            telephoneTextView.setText(TELEPHONE + drugStore.getTelephone());

        }

        RatingBar ratingBarFinal = (RatingBar) drugStoreScreen.
                                   findViewById(R.id.ratingBarFinalDrugstore);
        ratingBarFinal.setRating(drugStore.getRate());
        TextView textViewRate = (TextView) drugStoreScreen.
                                findViewById(R.id.textViewRatingDrugstore);
        textViewRate.setText("" + drugStore.getRate());

        Button drugStoreButton = (Button)drugStoreScreen.findViewById(R.id.buttonSaveRateDrugstore);
        final RatingBar drugstoreStars = (RatingBar) drugStoreScreen.
                                         findViewById(R.id.ratingBarUserDrugstore);

        drugStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {

                    public void run() {
                        Looper.prepare();

                        try {

                            drugStoreController.updateRate((int) drugstoreStars.getRating(),
                            drugStoreController.getAndroidId(), drugStore.getId());
                            Toast.makeText(getContext(),MESSAGE_SAVE ,
                            Toast.LENGTH_LONG).show();

                        } catch (ConnectionErrorException e) {

                            Toast.makeText(getContext(),MESSAGE_FAIL_CONECTION ,
                            Toast.LENGTH_LONG).show();

                        }
                        Looper.loop();
                    }
                }.start();
            }

        });

        //Phone call button
        ImageButton phoneCallButton = (ImageButton) drugStoreScreen.findViewById(R.id.
                                          phonecallButtonDrugstore);

        phoneCallButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) throws SecurityException{

                    Intent phoneCall = new Intent(Intent.ACTION_CALL);
                    phoneCall.setData(Uri.parse(TELEPHONE + drugStore.getTelephone()));
                    startActivity(phoneCall);
        }
        });

        return drugStoreScreen;

    }
}
