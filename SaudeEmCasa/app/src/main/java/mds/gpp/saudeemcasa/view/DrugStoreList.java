package mds.gpp.saudeemcasa.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;

import api.Exception.ConnectionErrorException;
import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.adapter.DrugStoreAdapter;
import mds.gpp.saudeemcasa.adapter.HospitalAdapter;
import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.model.Hospital;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.model.DrugStore;


public class DrugStoreList extends Activity {

    ListView listView;
    ArrayList<DrugStore> list;
    GPSTracker gps;
    int drugstore = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugstore_list_screen);

        // Initializing list view
        listView = (ListView) findViewById(R.id.listView);
        gps = new GPSTracker(this);

        // Instancing controller
        final DrugStoreController drugStoreController = DrugStoreController.getInstance(this);

        // Initialize and fill list of drugstore
        list = (ArrayList<DrugStore>) drugStoreController.getAllDrugstores();

        if (gps.canGetLocation()) {
            drugStoreController.setDistance(getApplicationContext(), list);
            // Initializing new DrugStoreAdapter with list of drugstore
            final DrugStoreAdapter adapter = new DrugStoreAdapter(getApplicationContext(), list);

            // Setting adapter to listView
            listView.setAdapter(adapter);

        } else {
            Toast.makeText(getApplicationContext(), "Voce nao esta conectado ao gps ou a internet!\n Conecte-se para prosseguir.", Toast.LENGTH_LONG).show();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
        //                list.get(position).setRate((float) 3.3 );//this should be set as the httprequest
                drugStoreController.setDrugStore(list.get(position));
                //request from server the rate and set to the drugstore
                Intent intent = new Intent(getBaseContext(), GoogleMapDrugStore.class);

                startActivity(intent);
            }
        });

    }
}

