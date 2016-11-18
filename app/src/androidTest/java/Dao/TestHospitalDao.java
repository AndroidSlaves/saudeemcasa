package Dao;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.List;

import api.Dao.HospitalDao;
import mds.gpp.saudeemcasa.model.Hospital;
import mds.gpp.saudeemcasa.view.LoadingScreen;

/**
 * Created by vinisilvacar on 03/11/15.
 */

public class TestHospitalDao extends ActivityInstrumentationTestCase2<LoadingScreen> {
    private LoadingScreen myActivity;
    HospitalDao hospitalDao;

    public TestHospitalDao() {

        super(LoadingScreen.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.myActivity = getActivity();
    }

    //Test to check instance of the class
    public void testGetInstance() throws Exception {
        hospitalDao = hospitalDao.getInstance(myActivity.getApplicationContext());
        assertNotNull(hospitalDao);
    }

    //Test method to insert a hospital list
    public void testInsertAllHospitals() throws Exception {

        hospitalDao = hospitalDao.getInstance(myActivity.getApplicationContext());

        int beforeInsert = hospitalDao.getAllHospitals().size();

        List<Hospital> hospitalList = new ArrayList<Hospital>();

        Hospital hospital1 = new Hospital("Hospital Regional do Gama", "3385-9700");
        Hospital hospital2 = new Hospital("Maria Auxialiadora", "35569843");
        Hospital hospital3 = new Hospital("Santa Luzia", "9555-0002");
        Hospital hospital4 = new Hospital("Posto nº 01 do Gama", "39656560");
        Hospital hospital5 = new Hospital("Hospital Anchieta", "5343-7634");

        hospitalList.add(hospital1);
        hospitalList.add(hospital2);
        hospitalList.add(hospital3);
        hospitalList.add(hospital4);
        hospitalList.add(hospital5);

        hospitalDao.insertAllHospitals(hospitalList);

        assertEquals("Santa Luzia", hospital3.getName());
        assertEquals(hospitalList.size(), 5);

        assertEquals(beforeInsert + 5, hospitalDao.getAllHospitals().size());

        //Method call to delete the existent local bank
        hospitalDao.deleteAllHospitals();

    }

    //Test method to verify that the local bank is empty.
    public void testIsDbEmpty() throws Exception {
        hospitalDao = HospitalDao.getInstance(myActivity.getApplicationContext());
        assertTrue(hospitalDao.isDbEmpty());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
