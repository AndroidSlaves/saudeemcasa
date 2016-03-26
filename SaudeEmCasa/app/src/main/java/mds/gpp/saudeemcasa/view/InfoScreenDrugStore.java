package mds.gpp.saudeemcasa.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import mds.gpp.saudeemcasa.R;

public class InfoScreenDrugStore extends FragmentActivity {
    //View menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_screen_drugstore);

        //menu = findViewById(R.id.topbar_back);

        TextView nameTextView = (TextView) findViewById(R.id.text_info_drugstore);
        nameTextView.setText("O Programa Farmácia Popular do Brasil vem a ser uma iniciativa do Governo Federal que autoriza a Fundação Oswaldo Cruz (FIOCRUZ) a disponibilizar medicamentos mediante ressarcimento, e pelo Decreto nº 5.090, de 20 de maio de 2004, que regulamenta a Lei 10.858 e institui o Programa Farmácia Popular do Brasil.\n" +
                "As unidades próprias contam com um elenco de 112 itens, entre medicamentos e o preservativo masculino, os quais são dispensados pelo seu valor de custo, representando uma redução de até 90% do valor de mercado. A condição para a aquisição dos medicamentos disponíveis nas unidades, neste caso, é a apresentação de documento com foto, no qual conste seu CPF, juntamente com uma receita médica ou odontológica. Importante ressaltar que somente a Rede Própria aceita receitas prescritas por dentistas.\n");

       /* menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoScreenDrugStore.this, ChooseScreen.class); // essa é activity Inicial do app
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // adiciona a flag para a intent
                startActivity(intent);
            }
        });*/

    }

}
