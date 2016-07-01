package Helper;

import android.test.ActivityInstrumentationTestCase2;

import api.Dao.DrugStoreDao;
import api.Helper.FirebaseHelper;
import mds.gpp.saudeemcasa.view.LoadingScreen;

public class TestFirebaseHelper extends ActivityInstrumentationTestCase2<LoadingScreen> {
    private LoadingScreen myActivity;
    private FirebaseHelper firebaseHelper;

    public TestFirebaseHelper() {

        super(LoadingScreen.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.myActivity = getActivity();
        this.firebaseHelper = new FirebaseHelper();
    }

    public void testGetDrugstoreInfo(){
        firebaseHelper.getDrugstoreInfo(myActivity);

        assertTrue(DrugStoreDao.getInstance(myActivity).isDatabaseEmpty());
    }
}
