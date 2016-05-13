/*****************************
 * Class name: HospitalList (.java)
 *
 * Purpose: An activity in order to create a list of hospitals with the distance between the user
 * and the respective hospitals.
 ****************************/

package mds.gpp.saudeemcasa.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.adapter.HospitalAdapter;
import mds.gpp.saudeemcasa.controller.HospitalController;
import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.model.Hospital;

public class HospitalList extends Activity {

    ListView listView;
    ArrayList<Hospital> list;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_list_screen);

        // Initializing list view
        listView = (ListView) findViewById(R.id.listView);

        gps = new GPSTracker(this);

        // Instancing controller
        final HospitalController hospitalController = HospitalController.getInstance(
                getApplicationContext());
        // Initialize and fill list of hospital
        list = (ArrayList<Hospital>) HospitalController.getAllHospitals();

        if(gps.canGetLocation()) {
            hospitalController.setDistance(getApplicationContext(), list);
            // Initializing new HospitalAdapter with list of hospitals
            HospitalAdapter adapter = new HospitalAdapter(getApplicationContext(), list);
            // Setting adapter to listView
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(), "Voce nao esta conectado ao gps ou a internet!" +
                    "\n Conecte-se para prosseguir.",Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                //list.get(position).setRate((float) 4.1 );//this should be set as the httpRequest
                hospitalController.setHospital(list.get(position));
                Intent intent = new Intent(getBaseContext(), GoogleMapHospital.class);

                startActivity(intent);
            }
        });

    }
 }

