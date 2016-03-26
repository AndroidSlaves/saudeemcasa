package Request;

import junit.framework.TestCase;

import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

import api.Exception.ConnectionErrorException;
import api.Request.HttpConnection;

/**
 * Created by lucas on 10/27/15.
 */
public class TestHttpConnection extends TestCase {


    @Test
    public void testNewRequest() {
        HttpConnection connection = new HttpConnection();
        try {
            String responseString = null;
            responseString = connection.newRequest("http://74.125.141.106");
            System.out.println(responseString);
            assertNotNull(responseString);
        } catch (ConnectionErrorException e) {
            assertTrue(false);
        } finally {
            assertTrue(true);
        }

    }

}