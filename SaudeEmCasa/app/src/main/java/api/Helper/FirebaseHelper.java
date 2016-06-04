package api.Helper;

import android.content.Context;
import api.Dao.DrugStoreDao;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import mds.gpp.saudeemcasa.model.DrugStore;

public class FirebaseHelper {
    public void  getAllDrugstore(Context context){
        System.out.println("ESTOU NO FIREBASE");

        final Firebase drugstoreFirebase = new Firebase("https://farmpopularconv.firebaseio.com/");
        System.out.println("CONSEGUI CONECTAR");

        final DrugStoreDao drugStoreDao = DrugStoreDao.getInstance(context);

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
                System.out.println(dataSnapshot.toString());

                int count = 0;
                for (DataSnapshot alldrugstore : dataSnapshot.getChildren()) {
                    id = alldrugstore.getValue().toString();
                    for (DataSnapshot drugstoreValues : alldrugstore.getChildren()) {
                        if (drugstoreValues.getKey().equalsIgnoreCase("lat")) {
                            latitude = drugstoreValues.getValue().toString();
                        }
                        if (drugstoreValues.getKey().equalsIgnoreCase("lon")) {
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
                        DrugStore newDrugstore = new DrugStore(latitude,longitude,telephone,name,city,address,state,id,type,postalcode);
                        count++;
                        drugStoreDao.insertDrugstore(newDrugstore);

                    }

                }
                System.out.println("Percorri "+count+" farmacias.");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
