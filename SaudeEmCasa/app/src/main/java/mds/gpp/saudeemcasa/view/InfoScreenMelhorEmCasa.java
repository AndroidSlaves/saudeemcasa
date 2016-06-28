/*****************************
 * Class name: InfoScreenMelhorEmCasa (.java)
 *
 * Purpose: Show information about "Melhor Em Casa" social program.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import mds.gpp.saudeemcasa.R;

public class InfoScreenMelhorEmCasa extends FragmentActivity {

    /**
     *
     * @param savedInstanceState
     *              Save the previous screen intented.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_screen_melhor_em_casa);

        TextView nameTextView = (TextView) findViewById(R.id.text_info_melhor_em_casa);
        assert (!(nameTextView == null)) : "text view should not bee null";

        nameTextView.setText(R.string.infoScreenMelhorEmCasa);
    }
}
