/*****************************
 * Class name: DrugStoreList (.java)
 *
 * Purpose: Fill the screen with the list of drugstores, putting the nearest drugstores on the list.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.adapter.DrugStoreAdapter;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.model.DrugStore;

public class DrugStoreList extends Activity {
    GPSTracker gps;
    ArrayList<DrugStore> list;
    ListView listView;

    /**
     * Create drugstores list from the user's location
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugstore_list_screen);

        // Set important variables for the list.
        gps = new GPSTracker(this);
        final DrugStoreController drugStoreController = DrugStoreController.getInstance(this);
        list = (ArrayList<DrugStore>) drugStoreController.getAllDrugstores();
        listView = (ListView) findViewById(R.id.listView);

        if(gps.canGetLocation() == true) {
            drugStoreController.setDistance(getApplicationContext(), list);
            final DrugStoreAdapter adapter = new DrugStoreAdapter(getApplicationContext(), list);
            listView.setAdapter(adapter);

        } else {
            final String CONNECT_ERROR_TEXT = "Voce nao esta conectado ao gps ou a internet!\n" +
                    " Conecte-se para prosseguir.";
            Toast.makeText(getApplicationContext(),CONNECT_ERROR_TEXT , Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                // Set controller drugstore when clicked then change screen
                DrugStore drugstoreFromPositionClicked = list.get(position);
                drugStoreController.setDrugStore(drugstoreFromPositionClicked);
                Intent intent = new Intent(getBaseContext(), GoogleMapDrugStore.class);
                startActivity(intent);
            }
        });

    }
}

