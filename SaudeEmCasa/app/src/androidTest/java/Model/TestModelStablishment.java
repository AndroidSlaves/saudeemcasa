package Model;

import android.test.AndroidTestCase;
import mds.gpp.saudeemcasa.model.Stablishment;

public class TestModelStablishment extends AndroidTestCase{


    public void testSetLatitude(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLatitude("10");
        assertEquals(stablishment.getLatitude(), "10");
    }

    public void testSetLatitudeNotNull(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLatitude("10");
        assertNotNull(stablishment.getLatitude());
    }

    public void testGetLatitude(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLatitude("10");
        assertEquals("10", stablishment.getLatitude());
    }

    public void testSetId(){
        Stablishment stablishment = new Stablishment();
        stablishment.setId("1");
        assertTrue(stablishment.getId() == "1");
    }

    public void testGetId(){
        Stablishment stablishment = new Stablishment();
        stablishment.setId("1");
        assertEquals(stablishment.getId(), "1");
    }

    public void testSetLongitude(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLongitude("20");
        assertTrue(stablishment.getLongitude() == "20");
    }

    public void testSetLongitudeNotNull(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLongitude("20");
        assertNotNull(stablishment.getLongitude());
    }

    public void testGetLongitude(){
        Stablishment stablishment = new Stablishment();
        stablishment.setLongitude("20");
        assertEquals(stablishment.getLongitude(), "20");
    }

    public void testSetType(){
        Stablishment stablishment = new Stablishment();
        stablishment.setType("SUS");
        assertTrue(stablishment.getType() == "SUS");
    }

    public void testGetType(){
        Stablishment stablishment = new Stablishment();
        stablishment.setType("SUS");
        assertEquals(stablishment.getType(), "SUS");
    }

    public void testSetTelephone(){
        Stablishment stablishment = new Stablishment();
        stablishment.setTelephone("(11)1111-2222");
        assertTrue(stablishment.getTelephone() == "(11)1111-2222");
    }

    public void testSetTelephoneNotNull(){
        Stablishment stablishment = new Stablishment();
        stablishment.setTelephone("(11)1111-2222");
        assertNotNull(stablishment.getTelephone());
    }

    public void testGetTelephone(){
        Stablishment stablishment = new Stablishment();
        stablishment.setTelephone("(11)1111-2222");
        assertEquals(stablishment.getTelephone(), "(11)1111-2222");
    }

    public void testSetName(){
        Stablishment stablishment = new Stablishment();
        stablishment.setName("STABLISHMENT");
        assertTrue(stablishment.getName() == "STABLISHMENT");
    }

    public void testGetName(){
        Stablishment stablishment = new Stablishment();
        stablishment.setName("STABLISHMENT");
        assertEquals(stablishment.getName(), "STABLISHMENT");
    }

    public void testSetCity(){
        Stablishment stablishment = new Stablishment();
        stablishment.setCity("CITY");
        assertTrue(stablishment.getCity() == "CITY");
    }

    public void testSetCityNotNull(){
        Stablishment stablishment = new Stablishment();
        stablishment.setCity("CITY");
        assertNotNull(stablishment.getCity());
    }

    public void testGetCity() {
        Stablishment stablishment = new Stablishment();
        stablishment.setCity("CITY");
        assertEquals(stablishment.getCity(), "CITY");
    }

    public void testSetAddress(){
        Stablishment stablishment = new Stablishment();
        stablishment.setAddress("ADDRESS");
        assertTrue(stablishment.getAddress() == "ADDRESS");
    }

    public void testGetAddress(){
        Stablishment stablishment = new Stablishment();
        stablishment.setAddress("ADDRESS");
        assertEquals(stablishment.getAddress(), "ADDRESS");
    }

    public void testSetState(){
        Stablishment stablishment = new Stablishment();
        stablishment.setState("DF");
        assertTrue(stablishment.getState() == "DF");
    }

    public void testGetState(){
        Stablishment stablishment = new Stablishment();
        stablishment.setState("DF");
        assertEquals(stablishment.getState(), "DF");
    }

    public void testSetRate(){
        Stablishment stablishment = new Stablishment();
        stablishment.setRate(2.1f);
        assertTrue(stablishment.getRate() == 2.1f);
    }

    public void testGetRate(){
        Stablishment stablishment = new Stablishment();
        stablishment.setRate(2.1f);
        assertEquals(stablishment.getRate(), 2.1f);
    }

    public void testSetDistance(){
        Stablishment stablishment = new Stablishment();
        stablishment.setDistance(100.01f);
        assertTrue(stablishment.getDistance() == 100.01f);
    }

    public void testGetDistance(){
        Stablishment stablishment = new Stablishment();
        stablishment.setDistance(100.01f);
        assertEquals(stablishment.getDistance(), 100.01f);
    }

    public void testStablishment(){
        Stablishment stablishment = new Stablishment();
        if (stablishment == null)
            assertTrue(false);
        else
            assertTrue(true);

    }

}
