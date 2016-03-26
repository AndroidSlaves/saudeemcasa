package mds.gpp.saudeemcasa.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import mds.gpp.saudeemcasa.R;

public class InfoScreenMelhorEmCasa extends FragmentActivity {

    //View menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_screen_melhor_em_casa);

        //menu = findViewById(R.id.topbar_back);

        TextView nameTextView = (TextView) findViewById(R.id.text_info_melhor_em_casa);
        nameTextView.setText("O programa Melhor em Casa, é um programa relacionado a área de saúde, criado pelo Governo Federal em 2013, com as seguintes propostas:\n" +
                "\n" +
                "Atendimento as pessoas com necessidade de reabilitação motora, idosos, pacientes crônicos sem agravamento ou em situação pós-cirúrgica\n" +
                "\n" +
                "Melhorar e ampliar a assistência do SUS a pacientes com agravos de saúde que possam receber atendimento em casa, e perto da família,\n" +
                "\n" +
                "Contribuir para a recuperação de doenças, visto que, carinho e atenção familiar aliados à adequada assistência em saúde se tornam elementos importantes para a recuperação do paciente.\n");


        /*menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoScreenMelhorEmCasa.this, ChooseScreen.class); // essa é activity Inicial do app
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // adiciona a flag para a intent
                startActivity(intent);
            }
        });*/
    }
}
