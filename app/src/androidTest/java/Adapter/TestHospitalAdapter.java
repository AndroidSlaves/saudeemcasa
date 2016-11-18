package Adapter;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Test;

import java.util.ArrayList;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.adapter.HospitalAdapter;
import mds.gpp.saudeemcasa.model.Hospital;
import mds.gpp.saudeemcasa.view.LoadingScreen;

public class TestHospitalAdapter extends ActivityInstrumentationTestCase2<LoadingScreen> {
    private LoadingScreen myActivity;
    private ArrayList<Hospital> list;

    private HospitalAdapter hospitalAdapter;
    private Context context;

    //List position used for testing
    static final int POSITION = 0;

    public TestHospitalAdapter() {

        super(LoadingScreen.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.myActivity = getActivity();
        context = myActivity.getApplicationContext();

        Hospital hospital1, hospital2;

        list = new ArrayList<Hospital>();
        hospital1 = new Hospital("Universitary Hospital", "3385-9790");
        hospital2 = new Hospital("Sarah Kubitschek Hospital ", "3556-9480");
        list.add(hospital1);
        list.add(hospital2);

        hospitalAdapter = new HospitalAdapter(context, list);
    }

    public void testHospitalNotNull() {
        Hospital hospital = new Hospital();
        assertNotNull(hospital);
    }

    public void testGetNumberOfHospitals() {
        final int NUMBER_OF_HOSPITALS = 2;
        assertEquals(hospitalAdapter.getCount(), NUMBER_OF_HOSPITALS);
    }

    public void testGetItemValidOne() {
        final int POSITION = 1;

        Hospital hospital = hospitalAdapter.getItem(POSITION);
        Hospital expected = list.get(POSITION);

        assertEquals(hospital, expected);
    }

    public void testGetItemValidZero() {
        final int POSITION = 0;

        Hospital hospital = hospitalAdapter.getItem(POSITION);
        Hospital expected = list.get(POSITION);

        assertEquals(hospital, expected);
    }

    @Test( expected = ArrayIndexOutOfBoundsException.class)
    public void testGetItemInvalidNegative() {
        final int POSITION = -1;

        hospitalAdapter.getItem(POSITION);
    }

    @Test( expected = ArrayIndexOutOfBoundsException.class)
    public void testGetItemInvalidTooBig() {
        final int POSITION = 10;

        hospitalAdapter.getItem(POSITION);
    }

    public void testPopulateAdapter() throws Exception {
        View view =  hospitalAdapter.populateAdapter(myActivity.findViewById(R.id.listView), 0);
        View  convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);
        assertTrue(view.isEnabled());
    }
    public void testPopulateSetDistance() throws Exception {
        hospitalAdapter.populateAdapter(myActivity.findViewById(R.id.listView), 0);
        list.get(POSITION).setDistance(3);
        View  convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);
        hospitalAdapter.setDistance(convertView, POSITION);
    }

    public void testGetView() {
        View view =  hospitalAdapter.getView(POSITION, myActivity.findViewById(R.id.listView), null);
        assertNotNull(view);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}

