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

import api.Exception.ConnectionErrorException;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.model.DrugStore;

public class FirebaseHelper {
    private final String TAG = "Firebase branch";

    public void getDrugstoreInfo(final Context context){
        final DrugStoreDao drugStoreDao = DrugStoreDao.getInstance(context);
        if(drugStoreDao.isDatabaseEmpty()) {
            final Firebase drugstoreFirebase = new Firebase("https://farmpopularconv.firebaseio.com/");
            drugstoreFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
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
                                }
                                if (drugstoreValues.getKey().equalsIgnoreCase("long")) {
                                    longitude = drugstoreValues.getValue().toString();
                                }
                                if (drugstoreValues.getKey().equalsIgnoreCase("nu_telefone_farmacia")) {
                                    telephone = drugstoreValues.getValue().toString();
                                }
                                if (drugstoreValues.getKey().equalsIgnoreCase("no_farmacia")) {
                                    name = drugstoreValues.getValue().toString();
                                }
                                if (drugstoreValues.getKey().equalsIgnoreCase("no_cidade")) {
                                    city = drugstoreValues.getValue().toString();
                                }
                                if (drugstoreValues.getKey().equalsIgnoreCase("ds_endereco_farmacia")) {
                                    address = drugstoreValues.getValue().toString();
                                }
                                if (drugstoreValues.getKey().equalsIgnoreCase("uf")) {
                                    state = drugstoreValues.getValue().toString();
                                }
                                if (drugstoreValues.getKey().equalsIgnoreCase("nu_cep_farmacia")) {
                                    postalcode = drugstoreValues.getValue().toString();
                                }
                            }
                            Log.v(TAG,"TESTE " + latitude + "," + longitude);
                            DrugStore newDrugstore = new DrugStore(latitude, longitude, telephone, name, city, address, state, id, type, postalcode);
                            Log.v(TAG,drugStoreDao.insertDrugstore(newDrugstore) + "");

                            drugstoreCount++;

                        }
                        Log.v(TAG,"Percorri " + drugstoreCount + " farmacias.");

                        DrugStoreController drugStoreController = DrugStoreController.getInstance(context);
                        drugStoreController.initControllerDrugstore();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else{/*Nothing to do*/}


    }
}
