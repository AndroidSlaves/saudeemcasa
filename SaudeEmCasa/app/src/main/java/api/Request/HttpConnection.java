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


/*
 * Created by lucas on 9/28/15.
 */
public class HttpConnection {
    private static String states[] = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public HttpConnection() {

    }
    /**
     * Create the connection with some url and return the response in string format.
     *
     * @param ipAddress
     *           address to be accessed.

     * @return response from http connection.
     *
     * @throws ConnectionErrorException
     */
    public String newRequest(String ipAddress) throws ConnectionErrorException {
        String response;
        try {
            System.out.println("Starting connection with " + ipAddress);
            HttpGet httpGet = new HttpGet(ipAddress);

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
     * @param ipAdress
     *          address to be accessed.
     * @return response from http connection.
     *
     * @throws ConnectionErrorException
     */
    public String RequestAllDrugstoresByUF(String ipAdress) throws ConnectionErrorException {
        String finalJson = "";

        for (int i = 0; i < states.length; i++) {
            String tmp;
            tmp = newRequest(ipAdress + "/uf/" + states[i]);

            finalJson = finalJson + "," + tmp.substring(1, tmp.length() - 1);
        }
        finalJson = finalJson.substring(1, finalJson.length());
        return "[" + finalJson + "]";
    }
    public float getRating(String id,String ipAddress) throws ConnectionErrorException, JSONException {
        String json = newRequest(ipAddress+id);
        JSONArray jsonArray = new JSONArray(json);
        if(jsonArray.length() == 0){
            return 0;
        }else {
            return (float) jsonArray.getJSONObject(0).getDouble("rate");
        }
    }
    public String Request(HttpGet httpGet, HttpClient client) throws IOException {

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        return client.execute(httpGet, responseHandler);
    }

}