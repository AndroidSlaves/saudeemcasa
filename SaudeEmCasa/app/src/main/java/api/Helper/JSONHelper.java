package api.Helper;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import api.Dao.DrugStoreDao;
import api.Dao.HospitalDao;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.model.Hospital;

/**
 * Created by lucas on 9/27/15.
 */
public class JSONHelper {




    public JSONHelper(Context context){
        drugStoreDao = DrugStoreDao.getInstance(context);
        hospitalDao = HospitalDao.getInstance(context);
    }
    //list of hospitals to be populated
    HospitalDao hospitalDao;

    /*
    * @param hospitalJsonList
    *               It is the list of hospitals in the JSON format
    *
    * @return List of hospital object
    *
    * @throws JSONException
    * */
    public boolean hospitalListFromJSON(String hospitalJsonList )throws JSONException {
        JSONArray jArray = new JSONArray(hospitalJsonList);

        try {
            Hospital hospital = new Hospital();

            for( int index = 0; index < jArray.length(); index++ ) {

                hospital.setLatitude(Double.toString(jArray.getJSONObject(index).getDouble("lon")));

                hospital.setLongitude(Double.toString(jArray.getJSONObject(index).getDouble("lat")));

                hospital.setType(jArray.getJSONObject(index).getString("tipo_sus"));

                hospital.setState(jArray.getJSONObject(index).getString("uf"));

                hospital.setCity(jArray.getJSONObject(index).getString("cidade"));

                hospital.setAddress(jArray.getJSONObject(index).getString("no_logradouro"));

                hospital.setNumber(jArray.getJSONObject(index).getString("nu_endereco"));

                hospital.setDistrict(jArray.getJSONObject(index).getString("no_bairro"));

                hospital.setTelephone(jArray.getJSONObject(index).getString("nu_telefone"));

                hospital.setName(jArray.getJSONObject(index).getString("no_fantasia"));

                hospital.setId(jArray.getJSONObject(index).getJSONObject("_id").getString("_str"));

                hospitalDao.insertHospital(hospital);
                
            }

        } catch( NullPointerException npe ) {return false;}

        return true;
    }

    //list of hospitals to be populated
    DrugStoreDao drugStoreDao;

    /*
    * @param drugstoreList
    *               It is the list of hospitals in the JSON format
    *
    * @return List of drugstore object
    *
    * @throws JSONException
    * */
    public boolean drugstorePublicListFromJSON(String drugstoreJsonList )throws JSONException {

        JSONArray jArray = new JSONArray(drugstoreJsonList);

        try {
            DrugStore drugStore = new DrugStore();;

            for( int index = 0; index < jArray.length(); index++ ) {

                drugStore.setLongitude(jArray.getJSONObject(index).getString("long"));

                drugStore.setLatitude(jArray.getJSONObject(index).getString("lat"));

                drugStore.setAddress(jArray.getJSONObject(index).getString("ds_endereco_farmacia"));

                drugStore.setPostalCode(jArray.getJSONObject(index).getString("nu_cep_farmacia"));

                drugStore.setState(jArray.getJSONObject(index).getString("uf"));

                drugStore.setCity(jArray.getJSONObject(index).getString("cidade"));

                drugStore.setTelephone("");

                drugStore.setName("Farmácia popular do Brasil");

                drugStore.setType("FARMACIAPOPULAR");

                drugStore.setId(jArray.getJSONObject(index).getJSONObject("_id").getString("_str"));

                drugStoreDao.insertDrugstore(drugStore);


            }

        } catch( NullPointerException npe ) {
            return false;
        }

        return true;
    }

    /*
    * @param drugstoreList
    *               It is the list of hospitals in the JSON format
    *
    * @return List of drugstore object
    *
    * @throws JSONException
    * */
    public boolean drugstorePrivateListFromJSON(String drugstoreJsonList )throws JSONException {

        JSONArray jArray = new JSONArray(drugstoreJsonList);

        try {
            DrugStore drugStore = new DrugStore();;

            for( int index = 0; index < jArray.length(); index++ ) {

                drugStore.setLongitude(jArray.getJSONObject(index).getString("long"));

                drugStore.setLatitude(jArray.getJSONObject(index).getString("lat"));

                drugStore.setTelephone("(" + jArray.getJSONObject(index).getString("nu_ddd_farmacia") + ")"
                        + jArray.getJSONObject(index).getString("nu_telefone_farmacia"));

                drugStore.setPostalCode(jArray.getJSONObject(index).getString("nu_cep_farmacia"));

                drugStore.setAddress(jArray.getJSONObject(index).getString("ds_endereco_farmacia"));

                drugStore.setName(jArray.getJSONObject(index).getString("no_farmacia"));

                drugStore.setCity(jArray.getJSONObject(index).getString("no_cidade"));

                drugStore.setState(jArray.getJSONObject(index).getString("uf"));

                drugStore.setType("AQUITEMFARMACIAPOPULAR");

                drugStore.setId(jArray.getJSONObject(index).getJSONObject("_id").getString("_str"));

                drugStoreDao.insertDrugstore(drugStore);

            }

        } catch (NullPointerException npe ) {
            return false;
        }

        return true;
    }
}
