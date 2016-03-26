package Adapter;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.adapter.HospitalAdapter;
import mds.gpp.saudeemcasa.model.Hospital;
import mds.gpp.saudeemcasa.view.LoadingScreen;

/**
 * Created by vinisilvacar on 12/11/15.
 */
public class TestHospitalAdapter extends ActivityInstrumentationTestCase2<LoadingScreen> {
    private LoadingScreen myActivity;
    private ArrayList<Hospital> lista;

    private HospitalAdapter hospitalAdapter;
    private Context context;
    Hospital hospital1, hospital2;

    //List position used for testing
    static final int POSITION = 0;

    public TestHospitalAdapter() {

        super(LoadingScreen.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.myActivity = getActivity();
        context = myActivity.getApplicationContext();

        lista = new ArrayList<Hospital>();
        hospital1 = new Hospital("Universitary Hospital", "3385-9790");
        hospital2 = new Hospital("Sarah Kubitschek Hospital ", "3556-9480");
        lista.add(hospital1);
        lista.add(hospital2);

        hospitalAdapter = new HospitalAdapter(context,lista);
    }

    public void testGetCount() {
        assertNotNull(hospitalAdapter);
        assertEquals(hospitalAdapter.getCount(), hospitalAdapter.COUNT);
    }

    public void testGetItem() {
        assertEquals(hospitalAdapter.getItem(POSITION), lista.get(POSITION));
    }

    public void testGetItemId() {

        assertEquals(hospitalAdapter.getItemId(POSITION), POSITION);
    }

    //Testing method that populates the layout
    public void testPopulateAdapter() throws Exception {
        View view =  hospitalAdapter.populateAdapter(myActivity.findViewById(R.id.listView), 0);
        View  convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);
        assertTrue(view.isEnabled());

        lista.get(POSITION).setDistance(3);
        hospitalAdapter.setDistance(convertView, POSITION);
    }

    //Test method that transforms a View in the xml containing the list item layout
    public void testGetView() throws Exception {
        View view =  hospitalAdapter.getView(POSITION, myActivity.findViewById(R.id.listView), null);
        assertNotNull(view);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}

