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

        final String INFO_TEXT_SAUDE_EM_CASA = "O aplicativo Saude em Casa, visa, facilitar ao " +
                                               "usuário do Programa Melhor em Casa, formas de " +
                                               "encontrar Hospitais, Clínicas ou estabelecimentos" +
                                               " mais próximos ao paciente, atendendo à questao " +
                                               "de dificuldade de deslocamento dos mesmos. Além" +
                                               " disso, o aplicativo disponibiliza também as " +
                                               "localizações de farmácias populares do Brasil, " +
                                               "para que os usuários possam localizar e ter " +
                                               "acesso ao possíveis medicamentos de seus " +
                                               "tratamentos de saude.\n";

        TextView nameTextView = (TextView)findViewById(R.id.text_info_saude_em_casa);
        nameTextView.setText(INFO_TEXT_SAUDE_EM_CASA);

    }

}
