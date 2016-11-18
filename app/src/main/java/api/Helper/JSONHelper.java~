/*****************************
 * Class name: JSONHelper (.java)
 *
 * Purpose: Class that helps to treat the data received in the JSON format from the server.
 *****************************/

package api.Helper;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import api.Dao.DrugStoreDao;
import api.Dao.HospitalDao;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.model.Hospital;

public class JSONHelper {

    // List of hospitals to be populated
    HospitalDao hospitalDao = null;
    // List of hospitals to be populated
    DrugStoreDao drugStoreDao = null;

    public JSONHelper(Context context) {
        assert (context != null) : "context must never be null";

        drugStoreDao = DrugStoreDao.getInstance(context);
        hospitalDao = HospitalDao.getInstance(context);
    }

    /*
    * @param hospitalJsonList
    *        It is the list of hospitals in the JSON format
    *
    * @return List of hospital object
    *
    * @throws JSONException
    * */
    public boolean hospitalListFromJSON(String hospitalJsonList) throws JSONException {
        assert (hospitalJsonList != null) : "hospitalJsonList must never be null";
        assert (hospitalJsonList != "") : "hospitalJsonList must never be empty";

        JSONArray jArray = new JSONArray(hospitalJsonList);

        try {
            Hospital hospital = new Hospital();

            for(int index = 0; index < jArray.length(); index++) {
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

        } catch(NullPointerException failedHospitalNull) {
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
    public boolean drugstorePublicListFromJSON(String drugstoreJsonList) throws JSONException {
        assert (drugstoreJsonList != null) : "drugstoreJsonList must never be null";
        assert (drugstoreJsonList!= "") : "drugstoreJsonList must never be empty";

        JSONArray jArray = new JSONArray(drugstoreJsonList);

        try {
            DrugStore drugStore = new DrugStore();;

            for(int index = 0; index < jArray.length(); index++) {
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

        } catch(NullPointerException failedDrugstorePublicNull) {
            return false;
        }

        return true;
    }

    /*
    * @param drugstoreList
    *        It is the list of hospitals in the JSON format
    *
    * @return List of drugstore object
    *
    * @throws JSONException
    * */
    public boolean drugstorePrivateListFromJSON(String drugstoreJsonList) throws JSONException {
        assert (drugstoreJsonList != null) : "drugstoreJsonList must never be null";
        assert (drugstoreJsonList!= "") : "drugstoreJsonList must never be empty";

        JSONArray jArray = new JSONArray(drugstoreJsonList);

        try {
            DrugStore drugStore = new DrugStore();

            for(int index = 0; index < jArray.length(); index++) {
                drugStore.setLongitude(jArray.getJSONObject(index).getString("long"));
                drugStore.setLatitude(jArray.getJSONObject(index).getString("lat"));
                drugStore.setTelephone("(" + jArray.getJSONObject(index).getString("nu_ddd_farmacia")
                        + ")" + jArray.getJSONObject(index).getString("nu_telefone_farmacia"));
                drugStore.setPostalCode(jArray.getJSONObject(index).getString("nu_cep_farmacia"));
                drugStore.setAddress(jArray.getJSONObject(index).getString("ds_endereco_farmacia"));
                drugStore.setName(jArray.getJSONObject(index).getString("no_farmacia"));
                drugStore.setCity(jArray.getJSONObject(index).getString("no_cidade"));
                drugStore.setState(jArray.getJSONObject(index).getString("uf"));
                drugStore.setType("AQUITEMFARMACIAPOPULAR");
                drugStore.setId(jArray.getJSONObject(index).getJSONObject("_id").getString("_str"));

                drugStoreDao.insertDrugstore(drugStore);
            }

        } catch (NullPointerException failedDrugstorePrivateNull) {
            return false;
        }

        return true;
    }
}
