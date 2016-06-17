/************************
 * Class name: HospitalAdapter (.java)
 *
 * Purpose: Class that create an item list for the user by inflating the items and putting them on
 *          the screen.
 ************************/

package mds.gpp.saudeemcasa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.model.Hospital;

public class HospitalAdapter extends ArrayAdapter<Hospital>   {

    //Represents the context where the application in taking place, the screen where the user is.
    private Context context;

    // represent the list of hospitals that will inflate the visual list for the user.
    private ArrayList<Hospital> list;

    // represent the number of items that the list will have.
    public int numberOfHospitals = 5;

    // Name of the class used for logs
    public static final String TAG = "HospitalAdapter";

    /**
     * Create a hospitalAdapter with the context where it will be used and the list of
     * hospitals that will populate the shown to the user
     *
     * @param context
     *        calls the context where the list will populate.
     *
     * @param list
     *        list of hospitals that will inflate the visual list for the user.
     *
     */
    @SuppressLint("Assert")
    public HospitalAdapter(Context context, ArrayList<Hospital> list){
        super(context, 0, list);
        assert (context != null) : "context must never be null";
        assert (list != null) : "list must never be null";
        assert (list.size() > 0) : "list must never be null";

        this.context = context;
        this.list = list;
    }

    /**
     * Method that returns the number of items in the list to be shown to the user.
     *
     * @return the number of items that the list will have.
     */
    @Override
    public int getCount() {
        return numberOfHospitals;
    }

    /**
     * Method that returns the object hospital in the position specified in the parameter.
     *
     * @param POSITION
     *              Used to take the item position.
     *
     * @return item
     *              The object hospital in the position specified in the parameter.
     */
    @Override
    public Hospital getItem(final int POSITION) {
        assert (POSITION >= 0) : "position must never be null";

        Hospital item = list.get(POSITION);

        return item;
    }

    /**
     * Method that returns an view  item from the view list.
     *
     * @param position
     *              Itens positions
     * @param convertView
     *              Container view
     * @param parent
     *              A group of views of the list.
     *
     * @return view
     *              Item from the view list.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        assert (position >= 0) : "position must never be negative";
        assert (convertView != null) : "convertView must never be null";
        assert (parent != null) : "parent must never be null";

        View view = populateAdapter(convertView, position);
        Log.i(TAG," Adapter has been populated");

        return view;
    }

    /**
     * Convert the distance value from meters to kilometers.
     *
     * @param DISTANCE
     *              Float value to be converted.
     *
     * @return distanceInKilometer
     *              Distance value in km.
     */
    private Float convertToKM(final Float DISTANCE){
        assert (DISTANCE > 0): "distance must never be negative";
        // Convert to kilometer
        final float KILO = 1000f;
        float distanceInKilometer = DISTANCE/KILO;
        // Make sure value is not something crazy
        final float MAXDISTANCE = 13000f;
        assert (distanceInKilometer < MAXDISTANCE) : "distance should never be bigger than " +
                "MAXDISTANCE";
        assert (distanceInKilometer >= 0) : "distance cannot be negative";

        return distanceInKilometer;
    }

    /**
     * Inflate the list view so that a list can be created with the specified elements.
     *
     * @param convertView
     *        view to inflated.
     * @param position
     *        index in the view list.
     *
     * @return inflated layout.
     */
    public View populateAdapter(View convertView, int position){
        assert (convertView != null) : "convertView must never be null";
        assert (position >= 0) : "position must never be negative";
        // Get hospital from the sorted list
        Hospital hospitalPosition = getItem(position);
        assert (hospitalPosition != null) : "hospital should not be null";
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);
        // Getting item text view to set distance
        TextView textView = (TextView) convertView.findViewById(R.id.textView2_item);
        assert (textView != null) : "item text view should not be null";
        textView.setText(hospitalPosition.getName());

        setDistance(convertView, position);

        Log.v(TAG, " End of populate adapter for item "+position);

        return convertView;
    }

    /**
     * Set distance value in the item layout
     *
     * @param convertView
     *        View to be accessed.
     * @param POSITION
     *        Position of the item layout to be accessed.
     */
    public void setDistance(View convertView, final int POSITION) {
        assert (POSITION >= 0) : "position must never be negative";
        assert (convertView != null) : "convertView must never be null";
        
        if(getItem(POSITION).getDistance() < 1f) {
            // Turn Distance into String
            final String DISTANCE =""+list.get(POSITION).getDistance();
            assert (DISTANCE != null) : "String distance should never be null";

            // Setting distance of drugstore on list item
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            assert(textViewDistance != null) : "text view should never be null";
            textViewDistance.setText(DISTANCE+R.string .meters);

            Log.i(TAG,"distance equals to " + DISTANCE + " sucessfuly set");
        } else {
            // Convert the given distance from meter to km and turn it to String.
            final String DISTANCE = convertToKM(list.get(POSITION).getDistance()).toString();
            assert (DISTANCE != null) : "String distance should never be null";

            // Setting distance of drugstore on list item.
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            assert (textViewDistance != null) : "text view should never be null";
            textViewDistance.setText(DISTANCE + R.string.km);

            Log.i(TAG, "distance equals to " + DISTANCE + " sucessfuly set");

        }
    }

}

