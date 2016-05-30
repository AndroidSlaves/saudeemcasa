/************************
 * Class name: HttpConnection (.java)
 *
 * Purpose: Connects with the server to get the data necessary for the application.
 ************************/

package api.Request;

import android.content.Entity;
import android.util.Log;

import org.apache.http.HttpResponse;
import android.util.Log;


import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import api.Exception.ConnectionErrorException;

public class HttpConnection {

    // List of states to be used in the requestAllDrugstoresByUF.
    private static String states[] = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                                      "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                                      "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public HttpConnection() {
        /* Default constructor! */
    }

    /**
     * Create the connection with some url and return the response in string format.
     *
     * @param ipAddress
     *              address to be accessed.
     *
     * @return
     *              response from http connection.
     *
     * @throws ConnectionErrorException
     *              there maybe a error connecting to server
     */
    public String newRequest(String ipAddress) throws ConnectionErrorException {
        assert (ipAddress != null) : "ipAddress must never be null";
        assert (ipAddress.length() >= 8) : "ipAddress must never be smaller then 8 characters";

        // variable that will receive the data from the JSON.
        String response = "";
        try {
            System.out.println("Starting connection with " + ipAddress);

            /*
             * Variable in the HttpGet format that will create a get request with the server in the
             * given IP address.
             */
            HttpGet httpGet = new HttpGet(ipAddress);

            // Variable in the DefaultHttpClient, which will get the data in the a String format.
            HttpClient client = new DefaultHttpClient();

            response = Request(httpGet, client);

            System.out.println("Resquest complete " + ipAddress);

        } catch (ClientProtocolException e) {
            System.out.println("Request failed " + ipAddress);
            throw new ConnectionErrorException();

        } catch (IOException e) {
            System.out.println("Request failed " + ipAddress);
            throw new ConnectionErrorException();
        }
        return response;
    }

    /**
     * Save or update rate from user on server database.
     *
     * @param ipAddress
     *              address to be accessed.
     * @return
     *              response from http connection.
     *
     * @throws ConnectionErrorException
     *              there maybe a error connecting to server
     */
    public String RequestAllDrugstoresByUF(String ipAddress) throws ConnectionErrorException {
        assert (ipAddress != null) : "ipAddress must never be null";
        assert (ipAddress.length() >= 8) : "ipAddress must never be smaller than 8 characters";

        // String where all the json will come together.
        String finalJson = "";

        for (int i = 0; i < states.length; i++) {
            String request = newRequest(ipAddress + "/uf/" + states[i]);

            finalJson = finalJson + "," + request.substring(1, request.length() - 1);
        }
        finalJson = finalJson.substring(1, finalJson.length());
        String drugStoresByUf = "[" + finalJson + "]";

        return drugStoresByUf;
    }

    /**
     * Request to get ratings of all the chosen stablishment. The ones chosen are the ones from
     * the list created from the adapter
     *
     * @param id
     *              receive an id from a drugstore or a hospital.
     * @param ipAddress
     *              receive the address's ip.
     *
     * @return
     *              rate evaluation.
     *
     * @throws ConnectionErrorException
     *              there maybe a error connecting to server.
     *
     * @throws JSONException
     *              there maybe a error treating jason object.
     */
    public float getRating(String id,String ipAddress) throws ConnectionErrorException, JSONException {
        assert (ipAddress != null) : "ipAddress must never be null";
        assert (ipAddress.length() >= 8) : "ipAddress must never be smaller than 8 characters";
        assert (id != null) : "id must never be negative";

        // String that will store response in order to use it.
        String json = newRequest(ipAddress+id);
        JSONArray jsonArray = new JSONArray(json);
        float rating = 0.0f;

        if (jsonArray.length() == 0){
            rating = 0;

        }else {
            rating = (float) jsonArray.getJSONObject(0).getDouble("rate");

        }
        return rating;
    }

    /**
     * Method that uses the httpGet and the HttpCliente to create a execution from the client.
     *
     * @param httpGet
     *        take the http
     * @param client
     *        take the http of the client.
     *
     * @return the string treated by the handler.
     *
     * @throws IOException
     */
    public String Request(HttpGet httpGet, HttpClient client) throws IOException {
        assert (httpGet != null) : "httpGet must never be null";
        assert (client != null) : "client must never be null";

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        String requestResponse = client.execute(httpGet, responseHandler);;

        return requestResponse;
    }

}
