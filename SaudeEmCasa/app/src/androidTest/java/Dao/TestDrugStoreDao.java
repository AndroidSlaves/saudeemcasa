package Dao;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.List;

import api.Dao.DrugStoreDao;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.view.LoadingScreen;

/**
 * Created by vinisilvacar on 02/11/15.
 */


public class TestDrugStoreDao extends ActivityInstrumentationTestCase2<LoadingScreen> {
    private LoadingScreen myActivity;
    DrugStoreDao drugStoreDao;

    public TestDrugStoreDao() {

        super(LoadingScreen.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.myActivity = getActivity();
    }

    //Test to check instance of the class
    public void testGetInstance() throws Exception {
        drugStoreDao = DrugStoreDao.getInstance(myActivity.getApplicationContext());
        assertNotNull(drugStoreDao);
    }

    //Test method to insert a drugstore list
    public void testInsertAllDrugStore() throws Exception {

        drugStoreDao = DrugStoreDao.getInstance(myActivity.getApplicationContext());

        int beforeInsert = drugStoreDao.getAllDrugStores().size();

        List<DrugStore> drugStoreList = new ArrayList<DrugStore>();

        DrugStore drugStore1 = new DrugStore("Rosario", "3385-9790");
        DrugStore drugStore2= new DrugStore("Pague Menos", "3332-1232");
        DrugStore drugStore3 = new DrugStore("Pacheco", "35556767");
        DrugStore drugStore4 = new DrugStore("Posto nº 06", "3234-0001");
        DrugStore drugStore5 = new DrugStore("Drogasil", "35569480");

        drugStoreList.add(drugStore1);
        drugStoreList.add(drugStore2);
        drugStoreList.add(drugStore3);
        drugStoreList.add(drugStore4);
        drugStoreList.add(drugStore5);

        drugStoreDao.insertAllDrugStores(drugStoreList);

        assertEquals("Rosario", drugStore1.getName());
        assertEquals(drugStoreList.size(), 5);

        assertEquals(beforeInsert + 5, drugStoreDao.getAllDrugStores().size());

        //Method call to delete the existent local bank
        drugStoreDao.deleteAllDrugStores();
    }

    //Test method to verify that the local bank is empty.
    public void testIsDbEmpty() throws Exception {
        drugStoreDao = DrugStoreDao.getInstance(myActivity.getApplicationContext());
        assertTrue(drugStoreDao.isDbEmpty());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
