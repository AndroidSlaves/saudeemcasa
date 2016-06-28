/*****************************
 * Class name: InfoScreenSaudeEmCasa (.java)
 *
 * Purpose: Show information about SaudeEmCasa Application.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import mds.gpp.saudeemcasa.R;

public class InfoScreenSaudeEmCasa extends FragmentActivity{

    /**
     *
     * @param savedInstanceState
     *              Save the previous screen intented.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_screen_saude_em_casa);

        TextView nameTextView = (TextView)findViewById(R.id.text_info_saude_em_casa);
        assert (!(nameTextView == null)) : "text view should not bee null";
        nameTextView.setText(R.string.infoScreenSaudeEmCasa);

    }

}
