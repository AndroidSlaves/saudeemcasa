package Adapter;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.adapter.DrugStoreAdapter;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.view.LoadingScreen;

/**
 * Created by vinisilvacar on 10/11/15.
 */
public class TestDrugStoreAdapter extends ActivityInstrumentationTestCase2<LoadingScreen> {
    private LoadingScreen myActivity;
    private ArrayList<DrugStore> lista;

    private DrugStoreAdapter drugStoreAdapter;
    private Context context;
    DrugStore drugStore1, drugStore2;

    //List position used for testing
    static final int POSITION = 0;

    public TestDrugStoreAdapter() {

            super(LoadingScreen.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.myActivity = getActivity();
        context = myActivity.getApplicationContext();

        lista = new ArrayList<DrugStore>();
        drugStore1 = new DrugStore("DrugStore Plus", "3385-9790");
        drugStore2 = new DrugStore("DrugStore Healthy", "3332-1232");
        lista.add(drugStore1);
        lista.add(drugStore2);

        drugStoreAdapter = new DrugStoreAdapter(context,lista);
    }

    public void testGetCount() {
        assertNotNull(drugStoreAdapter);
        assertEquals(drugStoreAdapter.getCount(), drugStoreAdapter.COUNT);
    }

    public void testGetItem() {
        assertEquals(drugStoreAdapter.getItem(POSITION), lista.get(POSITION));
    }

    public void testGetItemId() {
        assertEquals(drugStoreAdapter.getItemId(POSITION), POSITION);
    }

    //Testing method that populates the layout
    public void testPopulateAdapter() throws Exception {
        View view =  drugStoreAdapter.populateAdapter(myActivity.findViewById(R.id.listView), 0);
        View  convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);
        assertTrue(view.isEnabled());

        lista.get(POSITION).setDistance(3);
        drugStoreAdapter.setDistance(convertView, POSITION);
    }

    //Test method that transforms a View in the xml containing the list item layout
    public void testGetView() throws Exception {
        View view =  drugStoreAdapter.getView(POSITION, myActivity.findViewById(R.id.listView), null);
        assertNotNull(view);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

    }
}
