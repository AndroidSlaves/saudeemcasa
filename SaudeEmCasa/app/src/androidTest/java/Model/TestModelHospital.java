package Model;

import junit.framework.TestCase;

import org.junit.Test;

import mds.gpp.saudeemcasa.model.Hospital;


public class TestModelHospital extends TestCase {

    @Test
    public void testSetNumber(){
        Hospital hospital = new Hospital();
        hospital.setNumber("2020");
        assertTrue(hospital.getNumber() == "2020");
    }

    @Test
    public void testGetNumber(){
        Hospital hospital = new Hospital();
        hospital.setNumber("2020");
        assertEquals("2020", hospital.getNumber());

    }

    @Test
    public void testSetDistrict(){
        Hospital hospital = new Hospital();
        hospital.setDistrict("9");
        assertTrue(hospital.getDistrict() == "9");
    }

    @Test
    public void testGetDistrict(){
        Hospital hospital = new Hospital();
        hospital.setDistrict("9");
        assertEquals("9", hospital.getDistrict());
    }

    @Test
    public void testHospital(){
        Hospital hospital = new Hospital();
        if(hospital == null){
            assertTrue(false);
        }else {
            assertTrue(true);
        }
    }


}
