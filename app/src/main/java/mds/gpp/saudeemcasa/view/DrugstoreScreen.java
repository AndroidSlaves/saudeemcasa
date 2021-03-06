/*****************************
 * Class name: DrugstoreScreen (.java)
 *
 * Purpose: Screen that shows the information of the selected drugstore in the previous list screen.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
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

import junit.framework.Assert;

import api.Exception.ConnectionErrorException;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.model.DrugStore;

public class DrugstoreScreen extends Fragment {

    // Government drugstore type.
    final String DRUGSTORE_TYPE = "FARMACIAPOPULAR";
    // Message showed to user if is defined some rate.
    final String MESSAGE_SAVE = "Sua avaliação foi salva!";
    // Connection error message.
    final String MESSAGE_FAIL_CONNECTION = "Houve um erro de conexão.\nverifique se  está " +
            "conectado a internet.";
    // Postal code info.
    final String CEP = "CEP: ";
    // Telephone info.
    final String TELEPHONE = "Tel: ";
    // String separator.
    final String ONE_SPACE = " - ";
    // Used to Log system.
    final String TAG = DrugstoreScreen.class.getSimpleName();

    /**
     * Defines the layout of Drugstore fragment.
     * @param inflater
     *              Which layout will be inflated.
     * @param container
     *              The type of view group.
     * @param savedInstanceState
     *              Get the instances of screen saved previously.
     * @return
     *              The layout of Drugstore Screen.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Defining the XML (design) of the screen.
        View drugStoreScreen = inflater.inflate(R.layout.drugstore_screen, null);
        // Used to control the rates stored by user.
        final DrugStoreController drugStoreController = DrugStoreController.
                getInstance(this.getContext());
        // Used to get information about drugstore.
        final DrugStore drugStore = drugStoreController.getDrugstore();


        TextView nameTextView = (TextView) drugStoreScreen.findViewById(R.id.textViewDrugName);
        nameTextView.setText(drugStore.getName());

        TextView addressTextView = (TextView) drugStoreScreen.findViewById(R.id.textViewAddress);
        addressTextView.setText(Html.fromHtml(drugStore.getAddress() + ONE_SPACE +
                        drugStore.getCity() + ONE_SPACE + drugStore.getState()));

        TextView cepTextView = (TextView) drugStoreScreen.findViewById(R.id.textViewCep);
        cepTextView.setText(CEP + drugStore.getPostalCode());

        Assert.assertEquals(nameTextView.getText(), drugStore.getName());
        Assert.assertEquals(cepTextView.getText(), CEP + drugStore.getPostalCode());

        if(drugStore.getType().equals(DRUGSTORE_TYPE)) {
            Log.i(TAG, "This type of drugstore dont have phone number.");

            TextView telephoneTextView = (TextView) drugStoreScreen.findViewById(R.id.textViewDrugTel);
            telephoneTextView.setText("");

            Assert.assertEquals(telephoneTextView.getText(), "");
        } else {
            Log.i(TAG, "This type of drugstore have phone number, setting it to layout.");

            TextView telephoneTextView = (TextView) drugStoreScreen.findViewById(R.id.textViewDrugTel);
            telephoneTextView.setText(TELEPHONE + drugStore.getTelephone());

            Assert.assertEquals(telephoneTextView.getText(), TELEPHONE + drugStore.getTelephone());
        }

        RatingBar ratingBarFinal = (RatingBar) drugStoreScreen.findViewById(R.id.ratingBarFinalDrugstore);
        ratingBarFinal.setRating(drugStore.getRate());
        Assert.assertEquals(ratingBarFinal.getRating(), drugStore.getRate());

        TextView textViewRate = (TextView) drugStoreScreen.findViewById(R.id.textViewRatingDrugstore);
        textViewRate.setText("" + drugStore.getRate());
        Assert.assertEquals(textViewRate.getText(), "" + drugStore.getRate());

        Button drugStoreButton = (Button)drugStoreScreen.findViewById(R.id.buttonSaveRateDrugstore);
        final RatingBar drugstoreStars = (RatingBar) drugStoreScreen.findViewById(R.id.ratingBarUserDrugstore);

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
