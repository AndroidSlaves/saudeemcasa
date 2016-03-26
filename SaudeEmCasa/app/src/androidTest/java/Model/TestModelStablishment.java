package Model;

import junit.framework.TestCase;

import org.junit.Test;

import mds.gpp.saudeemcasa.model.Stablishment;
/**
 * Created by freemanpivo on 10/16/15.
 */
public class TestModelStablishment extends TestCase{

    @Test
    public void testSetLatitude(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLatitude("10");
        assertEquals(stablishment.getLatitude(), "10");
    }

    @Test
    public void testGetLatitude(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLatitude("10");
        assertEquals("10", stablishment.getLatitude());
    }

    @Test
    public void testSetId(){
        Stablishment stablishment = new Stablishment();
        stablishment.setId(1);
        assertTrue(stablishment.getId() == 1);
    }

    @Test
    public void testGetId(){
        Stablishment stablishment = new Stablishment();
        stablishment.setId(1);
        assertEquals(stablishment.getId(), 1);
    }

    @Test
    public void testSetLongitude(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLongitude("20");
        assertTrue(stablishment.getLongitude() == "20");
    }

    @Test
    public void testGetLongitude(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLongitude("20");
        assertEquals(stablishment.getLongitude(), "20");
    }

    @Test
    public void testSetType(){
        Stablishment stablishment = new Stablishment();
        stablishment.setType("SUS");
        assertTrue(stablishment.getType() == "SUS");
    }

    @Test
    public void testGetType(){
        Stablishment stablishment = new Stablishment();
        stablishment.setType("SUS");
        assertEquals(stablishment.getType(), "SUS");
    }

    @Test
    public void testSetTelephone(){
        Stablishment stablishment = new Stablishment();
        stablishment.setTelephone("(11)1111-2222");
        assertTrue(stablishment.getTelephone() == "(11)1111-2222");
    }

    @Test
    public void testGetTelephone(){
        Stablishment stablishment = new Stablishment();
        stablishment.setTelephone("(11)1111-2222");
        assertEquals(stablishment.getTelephone(), "(11)1111-2222");
    }

    @Test
    public void testSetName(){
        Stablishment stablishment = new Stablishment();
        stablishment.setName("STABLISHMENT");
        assertTrue(stablishment.getName() == "STABLISHMENT");
    }

    @Test
    public void testGetName(){
        Stablishment stablishment = new Stablishment();
        stablishment.setName("STABLISHMENT");
        assertEquals(stablishment.getName(), "STABLISHMENT");
    }

    @Test
    public void testSetCity(){
        Stablishment stablishment = new Stablishment();
        stablishment.setCity("CITY");
        assertTrue(stablishment.getCity() == "CITY");
    }

    @Test
    public void testGetCity() {
        Stablishment stablishment = new Stablishment();
        stablishment.setCity("CITY");
        assertEquals(stablishment.getCity(), "CITY");
    }

    @Test
    public void testSetAddress(){
        Stablishment stablishment = new Stablishment();
        stablishment.setAddress("ADDRESS");
        assertTrue(stablishment.getAddress() == "ADDRESS");
    }

    @Test
    public void testGetAddress(){
        Stablishment stablishment = new Stablishment();
        stablishment.setAddress("ADDRESS");
        assertEquals(stablishment.getAddress(), "ADDRESS");
    }

    @Test
    public void testSetState(){
        Stablishment stablishment = new Stablishment();
        stablishment.setState("DF");
        assertTrue(stablishment.getState() == "DF");
    }

    @Test
    public void testGetState(){
        Stablishment stablishment = new Stablishment();
        stablishment.setState("DF");
        assertEquals(stablishment.getState(), "DF");
    }

    @Test
    public void testSetRate(){
        Stablishment stablishment = new Stablishment();
        stablishment.setRate(2.1f);
        assertTrue(stablishment.getRate() == 2.1f);
    }

    @Test
    public void testGetRate(){
        Stablishment stablishment = new Stablishment();
        stablishment.setRate(2.1f);
        assertEquals(stablishment.getRate(), 2.1f);
    }

    @Test
    public void testSetDistance(){
        Stablishment stablishment = new Stablishment();
        stablishment.setDistance(100.01f);
        assertTrue(stablishment.getDistance() == 100.01f);
    }

    @Test
    public void testGetDistance(){
        Stablishment stablishment = new Stablishment();
        stablishment.setDistance(100.01f);
        assertEquals(stablishment.getDistance(), 100.01f);
    }

    @Test
    public void testStablishment(){
        Stablishment stablishment = new Stablishment();
        if (stablishment == null)
            assertTrue(false);
        else
            assertTrue(true);

    }

}
