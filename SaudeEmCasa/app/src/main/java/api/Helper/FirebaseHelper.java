package api.Helper;

import android.content.Context;
import android.util.Log;

import api.Dao.DrugStoreDao;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONException;

import java.io.IOException;

import api.Dao.HospitalDao;
import api.Exception.ConnectionErrorException;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.controller.HospitalController;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.model.Hospital;

public class FirebaseHelper {
    private final String TAG = "Firebase branch";
    // Access the database class
    DrugStoreDao drugStoreDao;
    // Access the database class
    HospitalDao hospitalDao;

    /**
     * Access data stored in Firebase server.
     *
     * @param context
     *              Screen where this action is happening.
     */
    public void getDrugstoreInfo(final Context context){
         drugStoreDao = DrugStoreDao.getInstance(context);

        if(drugStoreDao.isDatabaseEmpty()) {
            final Firebase drugstoreFirebase = new Firebase("https://farmpopularconv.firebaseio.com/");

            drugstoreFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    treatDrugstoresSnapshot(context,dataSnapshot);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.e(TAG," There was a problem accessing firebase");
                }
            });
        }else{/*Nothing to do*/}
    }

    private void treatDrugstoresSnapshot(Context context, DataSnapshot dataSnapshot){

        String latitude = "";
        String longitude = "";
        String telephone = "";
        String name = "";
        String city = "";
        String address = "";
        String state = "";
        String postalcode = "";
        String id = "";
        String type = "AQUITEMFARMACIAPOPULAR";
        int drugstoreCount = 0;

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            for (DataSnapshot alldrugstore : snapshot.getChildren()) {

                id = alldrugstore.getValue().toString();
                for (DataSnapshot drugstoreValues : alldrugstore.getChildren()) {

                    if (drugstoreValues.getKey().equalsIgnoreCase("lat")) {
                        latitude = drugstoreValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (drugstoreValues.getKey().equalsIgnoreCase("long")) {
                        longitude = drugstoreValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (drugstoreValues.getKey().equalsIgnoreCase("nu_telefone_farmacia")) {
                        telephone = drugstoreValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (drugstoreValues.getKey().equalsIgnoreCase("no_farmacia")) {
                        name = drugstoreValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (drugstoreValues.getKey().equalsIgnoreCase("no_cidade")) {
                        city = drugstoreValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (drugstoreValues.getKey().equalsIgnoreCase("ds_endereco_farmacia")) {
                        address = drugstoreValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (drugstoreValues.getKey().equalsIgnoreCase("uf")) {
                        state = drugstoreValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (drugstoreValues.getKey().equalsIgnoreCase("nu_cep_farmacia")) {
                        postalcode = drugstoreValues.getValue().toString();
                    }else{/* Nothing to do */}
                }
                Log.v(TAG,"TESTE " + latitude + "," + longitude);
                DrugStore newDrugstore = new DrugStore(latitude, longitude, telephone, name, city,
                        address, state, id, type, postalcode);
                Log.v(TAG,drugStoreDao.insertDrugstore(newDrugstore) + "");

                drugstoreCount++;

            }
            Log.v(TAG, drugstoreCount + " farmacias foram inseridas");

            DrugStoreController drugStoreController = DrugStoreController.getInstance(context);
            drugStoreController.initControllerDrugstore();
        }
    }

    public void getHospitalInfo(final Context context){
        hospitalDao = hospitalDao.getInstance(context);
        Log.i(TAG, "Waiting to get in");
        if(hospitalDao.isDbEmpty()) {
            final Firebase hospitalFirebase = new Firebase("https://melhoremcasa.firebaseio.com/");

            hospitalFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    treatHospitalSnapshot(context, dataSnapshot);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else{/*Nothing to do*/}
    }

    private void treatHospitalSnapshot(Context context, DataSnapshot dataSnapshot){

        String latitude = "";
        String longitude = "";
        String telephone = "";
        String name = "";
        String city = "";
        String address = "";
        String state = "";
        String district = "";
        String id = "";
        String type = "";
        int hospitalCount = 0;

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            for (DataSnapshot allHospital : snapshot.getChildren()) {

                id = allHospital.getValue().toString();
                for (DataSnapshot hospitalValues : allHospital.getChildren()) {

                    if (hospitalValues.getKey().equalsIgnoreCase("lat")) {
                        latitude = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (hospitalValues.getKey().equalsIgnoreCase("long")) {
                        longitude = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (hospitalValues.getKey().equalsIgnoreCase("nu_telefone")) {
                        telephone = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (hospitalValues.getKey().equalsIgnoreCase("no_fantasia")) {
                        name = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (hospitalValues.getKey().equalsIgnoreCase("cidade")) {
                        city = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (hospitalValues.getKey().equalsIgnoreCase("no_logradouro")) {
                        address = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (hospitalValues.getKey().equalsIgnoreCase("uf")) {
                        state = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (hospitalValues.getKey().equalsIgnoreCase("no_bairro")) {
                        district = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                    if (hospitalValues.getKey().equalsIgnoreCase("tipo_sus")) {
                        type = hospitalValues.getValue().toString();
                    }else{/* Nothing to do */}
                }
                Log.v(TAG,"TESTE " + latitude + "," + longitude);
                Hospital newHospital = new Hospital(latitude, longitude, telephone, name, city,
                        address, state, id, type, district);
                boolean result = hospitalDao.insertHospital(newHospital);

                Log.v(TAG, result + "");

                hospitalCount++;

            }
            Log.v(TAG, hospitalCount + " hospitais foram inseridas");

            HospitalController hospitalController = HospitalController.getInstance(context);
            hospitalController.initControllerHospital();
        }
    }
}
