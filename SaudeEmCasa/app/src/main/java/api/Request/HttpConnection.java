/************************
 * Class name: HttpConnection (.java)
 *
 * Purpose: Connects with the server to get the data necessary for the application.
 ************************/

package api.Request;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import api.Exception.ConnectionErrorException;

public class HttpConnection {

    // List of states to be used in the requestAllDrugstoresByUF.
    private final static String states[] = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                                      "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                                      "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public HttpConnection() {
        /* Default constructor! */
    }

    /**
     * Create the connection with some url and return the response in string format.
     *
     * @param ipAddress
     *              Address to be accessed.
     *
     * @return response
     *              Response from http connection.
     *
     * @throws ConnectionErrorException
     *              There maybe a error connecting to server
     */
    public String newRequest(String ipAddress) throws ConnectionErrorException {
        assert (ipAddress != null) : "ipAddress must never be null";
        assert (ipAddress.length() >= 8) : "ipAddress must never be smaller then 8 characters";

        // Variable that will receive the data from the JSON.
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
            assert (response != null) : "response must never be null";

            System.out.println("Request complete " + ipAddress);

        } catch(ClientProtocolException e) {
            System.out.println("Request failed " + ipAddress);
            throw new ConnectionErrorException();

        } catch(IOException e) {
            System.out.println("Request failed " + ipAddress);
            throw new ConnectionErrorException();
        }
        return response;
    }

    /**
     * Save or update rate from user on server database.
     *
     * @param ipAddress
     *              Address to be accessed.
     *
     * @return
     *              Response from http connection.
     *
     * @throws ConnectionErrorException
     *              There maybe a error connecting to server
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
     *              Receive an id from a drugstore or a hospital.
     *
     * @param ipAddress
     *              Receive the address's ip.
     *
     * @return rating
     *              Rate evaluation.
     *
     * @throws ConnectionErrorException
     *              There maybe a error connecting to server.
     * @throws JSONException
     *              There maybe a error treating jason object.
     */
    public float getRating(String id,String ipAddress) throws ConnectionErrorException,
            JSONException {
        assert (ipAddress != null) : "ipAddress must never be null";
        assert (ipAddress.length() >= 8) : "ipAddress must never be smaller than 8 characters";
        assert (id != null) : "id must never be negative";

        // String that will store response in order to use it.
        String json = newRequest(ipAddress + id);
        assert (json !=  null) : "json String must never be null";

        JSONArray jsonArray = new JSONArray(json);
        float rating = 0.0f;

        if (jsonArray.length() == 0){
            rating = 0;

        } else {
            rating = (float) jsonArray.getJSONObject(0).getDouble("rate");

        }
        return rating;
    }

    /**
     * Method that uses the httpGet and the HttpCliente to create a execution from the client.
     *
     * @param httpGet
     *              Take the http
     * @param client
     *              Take the http of the client.
     *
     * @return requestResponse
     *              The string treated by the handler.
     *
     * @throws IOException
     */
    public String Request(HttpGet httpGet, HttpClient client) throws IOException {
        assert (httpGet != null) : "httpGet must never be null";
        assert (client != null) : "client must never be null";

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        String requestResponse = client.execute(httpGet, responseHandler);
        assert (requestResponse != null) : "requestResponse String must never be null";

        return requestResponse;
    }

    /**
     * @param inputStream
     *              It allows you to read JSON data one byte at a time.
     *
     * @return json
     *              Files loaded from json.
     */
    public String loadJSONFromAsset(InputStream inputStream) {
        String json = null;
        try {
            //InputStream is = getActivity().getAssets().open("yourfilename.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch(IOException ioExceptionJson) {
            ioExceptionJson.printStackTrace();
        }
        return json;
    }

}
