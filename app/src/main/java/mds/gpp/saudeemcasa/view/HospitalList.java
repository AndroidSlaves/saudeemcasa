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
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.*;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.adapter.HospitalAdapter;
import mds.gpp.saudeemcasa.controller.HospitalController;
import mds.gpp.saudeemcasa.helper.GPSTracker;
import mds.gpp.saudeemcasa.model.Hospital;

public class HospitalList extends Activity {
    //listView is a layout that show objects organized in a list.
    private ListView listView;
    //setup hospital list to populate adapter view.
    private ArrayList<Hospital> list;
    //gps to evaluate distance between user and hospital.
    private GPSTracker gps;
    //message shwo to user if gps is not enabled
    private final String CONNECTION_ERROR_TEXT = "Voce nao esta conectado ao gps ou a internet!" +
            "\n Conecte-se para prosseguir.";
    //Name of class, used to Log system.
    private final String TAG = HospitalList.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_list_screen);

        // Initializing listView
        final int LIST_VIEW_ID = R.id.listView;
        listView = (ListView) findViewById(LIST_VIEW_ID);

        // Setting up important components.
        gps = new GPSTracker(this);
        final HospitalController hospitalController = HospitalController.getInstance(getApplicationContext());
        list = (ArrayList<Hospital>) HospitalController.getAllHospitals();

        Log.d(TAG, "Number of hospitals stored on list: " + list.size());

        // Set the distance for hospitals.
        if(gps.canGetLocation()) {
            Log.i(TAG, "GPS is enabled. Setting up adapter list.");

            hospitalController.setDistance(getApplicationContext(), list);
            HospitalAdapter adapter = new HospitalAdapter(getApplicationContext(), list);
            listView.setAdapter(adapter);
        } else {
            Log.d(TAG, "GPS not enabled. Asking user to turn it on.");
            Toast.makeText(getApplicationContext(),CONNECTION_ERROR_TEXT ,Toast.LENGTH_LONG).show();
        }

        // Set listener for when the user click on a list element.
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

