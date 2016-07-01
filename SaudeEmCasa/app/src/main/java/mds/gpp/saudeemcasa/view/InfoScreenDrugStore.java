/*****************************
 * Class name: InfoScreenDrugStore (.java)
 *
 * Purpose: Show information about "Farmacia popular" social program.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import mds.gpp.saudeemcasa.R;

public class InfoScreenDrugStore extends FragmentActivity {

    /**
     *
     * @param savedInstanceState
     *              Save the previous screen intented.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_screen_drugstore);

        final String TAG = InfoScreenDrugStore.class.getSimpleName();

        TextView nameTextView = (TextView) findViewById(R.id.text_info_drugstore);
        assert (!(nameTextView == null)) : "text view should not bee null";

        nameTextView.setText(R.string.infoScreenDrugstore);
        Log.i(TAG, "Information screen Drugstore presented!");
    }
}
