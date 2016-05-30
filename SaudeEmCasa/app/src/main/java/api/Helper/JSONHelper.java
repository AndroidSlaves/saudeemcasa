/************************
 * Class name: JSONHelper (.java)
 *
 * Purpose: Class that helps to treat the data received in the JSON format from the server.
 ************************/

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

    /**
     * @param hospitalJsonList
     *        It is the list of hospitals in the JSON format
     *
     * @return List of hospital object
     *
     * @throws JSONException
     */
    public boolean hospitalListFromJSON(String hospitalJsonList) throws JSONException {
        assert (hospitalJsonList != null) : "hospitalJsonList must never be null";
        assert (hospitalJsonList != "") : "hospitalJsonList must never be empty";

        // jArray Structure from the JSON received ready to extract data.
        JSONArray jsonArray = new JSONArray(hospitalJsonList);
        boolean canSetHospital = false;

        try {
            Hospital hospital = new Hospital();

            for(int index = 0; index < jsonArray.length(); index++) {
                hospital.setLatitude(Double.toString(jsonArray.getJSONObject(index).getDouble("lon")));
                hospital.setLongitude(Double.toString(jsonArray.getJSONObject(index)
                        .getDouble("lat")));
                hospital.setType(jsonArray.getJSONObject(index).getString("tipo_sus"));
                hospital.setState(jsonArray.getJSONObject(index).getString("uf"));
                hospital.setCity(jsonArray.getJSONObject(index).getString("cidade"));
                hospital.setAddress(jsonArray.getJSONObject(index).getString("no_logradouro"));
                hospital.setNumber(jsonArray.getJSONObject(index).getString("nu_endereco"));
                hospital.setDistrict(jsonArray.getJSONObject(index).getString("no_bairro"));
                hospital.setTelephone(jsonArray.getJSONObject(index).getString("nu_telefone"));
                hospital.setName(jsonArray.getJSONObject(index).getString("no_fantasia"));
                hospital.setId(jsonArray.getJSONObject(index).getJSONObject("_id").getString("_str"));

                hospitalDao.insertHospital(hospital);
                canSetHospital = true;
            }

        } catch(NullPointerException failedHospitalNull) {
            canSetHospital = false;
        }

        return canSetHospital;
    }

    /**
     * Access the drugstore data, from "FARMACIA POPULAR", in server and index it in drugstores's
     * table."
     *
     * @param drugstoreJsonList
     *        It is the list of hospitals in the JSON format
     *
     * @return List of drugstore object
     *
     * @throws JSONException
     */
    public boolean drugstorePublicListFromJSON(String drugstoreJsonList) throws JSONException {
        assert (drugstoreJsonList != null) : "drugstoreJsonList must never be null";
        assert (drugstoreJsonList!= "") : "drugstoreJsonList must never be empty";

        // jsonArray Structure from the JSON received ready to extract data.
        JSONArray jsonArray = new JSONArray(drugstoreJsonList);
        boolean canSetDrugStore = false;

        try {
            DrugStore drugStore = new DrugStore();;

            for(int index = 0; index < jsonArray.length(); index++) {
                drugStore.setLongitude(jsonArray.getJSONObject(index).getString("long"));
                drugStore.setLatitude(jsonArray.getJSONObject(index).getString("lat"));
                drugStore.setAddress(jsonArray.getJSONObject(index).getString("ds_endereco_farmacia"));
                drugStore.setPostalCode(jsonArray.getJSONObject(index).getString("nu_cep_farmacia"));
                drugStore.setState(jsonArray.getJSONObject(index).getString("uf"));
                drugStore.setCity(jsonArray.getJSONObject(index).getString("cidade"));
                drugStore.setTelephone("");
                drugStore.setName("Farmácia popular do Brasil");
                drugStore.setType("FARMACIAPOPULAR");
                drugStore.setId(jsonArray.getJSONObject(index).getJSONObject("_id").getString("_str"));

                drugStoreDao.insertDrugstore(drugStore);
                canSetDrugStore = true;
            }

        } catch(NullPointerException failedDrugstorePublicNull) {
            canSetDrugStore = false;
        }

        return canSetDrugStore;
    }

    /**
     * Access the drugstore data, from "AQUI TEM FARMACIA POPULAR", in server and index it in
     * drugstores's table."
     *
     * @param drugstoreJsonList
     *         It is the list of hospitals in the JSON format
     *
     * @return List of drugstore object
     *
     * @throws JSONException
     */
    public boolean drugstorePrivateListFromJSON(String drugstoreJsonList) throws JSONException {
        assert (drugstoreJsonList != null) : "drugstoreJsonList must never be null";
        assert (drugstoreJsonList!= "") : "drugstoreJsonList must never be empty";

        // jsonArray Structure from the JSON received ready to extract data.
        JSONArray jsonArray = new JSONArray(drugstoreJsonList);
        boolean canSetDrugStore = false;

        try {
            DrugStore drugStore = new DrugStore();

            for(int index = 0; index < jsonArray.length(); index++) {
                drugStore.setLongitude(jsonArray.getJSONObject(index).getString("long"));
                drugStore.setLatitude(jsonArray.getJSONObject(index).getString("lat"));
                drugStore.setTelephone("(" + jsonArray.getJSONObject(index)
                        .getString("nu_ddd_farmacia")
                        + ")" + jsonArray.getJSONObject(index)
                        .getString("nu_telefone_farmacia"));
                drugStore.setPostalCode(jsonArray.getJSONObject(index).getString("nu_cep_farmacia"));
                drugStore.setAddress(jsonArray.getJSONObject(index).getString("ds_endereco_farmacia"));
                drugStore.setName(jsonArray.getJSONObject(index).getString("no_farmacia"));
                drugStore.setCity(jsonArray.getJSONObject(index).getString("no_cidade"));
                drugStore.setState(jsonArray.getJSONObject(index).getString("uf"));
                drugStore.setType("AQUITEMFARMACIAPOPULAR");
                drugStore.setId(jsonArray.getJSONObject(index).getJSONObject("_id").getString("_str"));

                drugStoreDao.insertDrugstore(drugStore);
                canSetDrugStore = true;
            }

        } catch (NullPointerException failedDrugstorePrivateNull) {
            canSetDrugStore = false;
        }

        return canSetDrugStore;
    }
}
