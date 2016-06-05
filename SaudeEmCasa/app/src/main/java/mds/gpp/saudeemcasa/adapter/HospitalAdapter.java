/************************
 * Class name: HospitalAdapter (.java)
 *
 * Purpose: Class that create an item list for the user by inflating the items and putting them on
 *          the screen.
 ************************/

package mds.gpp.saudeemcasa.adapter;

import android.content.Context;
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
    public static final int COUNT = 5;

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
        return COUNT;
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
     * Method that returns the identification number of the element specified in the parameter.
     *
     * @param POSITION
     *              Used to take the item position.
     * @return POSITION
     *              Used to take the item position.
     */
    @Override
    public long getItemId(final int POSITION) {
        return POSITION;
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
        assert (view != null) : "view context must never be null";

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

        final float KILO = 1000f;
        float distanceInKilometer = DISTANCE/KILO;

        final float MAXDISTANCE = 13000f;
        assert (distanceInKilometer < MAXDISTANCE) : "distance should never be bigger than " +
                "MAXDISTANCE";

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

        Hospital hospitalPosition = getItem(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);

        TextView textView = (TextView) convertView.findViewById(R.id.textView2_item);
        textView.setText(hospitalPosition.getName());

        setDistance(convertView, position);

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

            // Setting distance of drugstore on list item
            final String METERS_POS_FIX = " m";
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(this.list.get(POSITION).getDistance() + METERS_POS_FIX);

        } else {

            // Setting distance of drugstore on list item
            final String KM_POS_FIX = " km";
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(convertToKM(this.list.get(POSITION).getDistance()).toString()
                    + KM_POS_FIX);
        }
    }

}

