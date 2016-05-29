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
    public static final int COUNT = 15;

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
    public int getCount() { return COUNT; }

    /**
     * @param POSITION
     *        used to take the item position.
     *
     * @return the object hospital in the position specified in the parameter.
     */
    @Override
    public Hospital getItem(final int POSITION) {
        
        assert (POSITION >= 0) : "position must never be null";
        
        return list.get(POSITION);
    }
    //fix this function
    @Override
    public long getItemId(final int POSITION) {
        return POSITION;
    }

    /**
     * @param position
     *        itens positions
     * @param convertView
     *        container view
     * @param parent
     *        a group of views of the list.
     *
     * @return a view  item from the view list.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        assert (position >= 0) : "position must never be negative";
        assert (convertView != null) : "convertView must never be null";
        assert (parent != null) : "parent must never be null";

        return populateAdapter(convertView,position);
    }

    /**
     * convert the distance value from meters to kilometers.
     *
     * @param DISTANCE
     *           float value to be converted.
     *
     * @return distance value in km.
     */
    private Float convertToKM(final Float DISTANCE){
        assert (DISTANCE > 0): "distance must never be negative";

        final float KILO = 1000f;
        float distanceInKilometer = DISTANCE/KILO;

        final float MAXDISTANCE = 13000f;
        assert (distanceInKilometer < MAXDISTANCE) : "distance should never be bigger than MAXDISTANCE";

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

        Hospital hospitalPosition = this.list.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);

        TextView textView = (TextView) convertView.findViewById(R.id.textView2_item);
        textView.setText((CharSequence) hospitalPosition.getName());

        setDistance(convertView, position);

        return convertView;
    }

    /**
     * set distance value in the item layout
     *
     * @param convertView
     *        view to be accessed.
     * @param POSITION
     *        position of the item layout to be accessed.
     */
    public void setDistance(View convertView, final int POSITION) {
        assert (POSITION >= 0) : "position must never be negative";
        assert (convertView != null) : "convertView must never be null";
        
        if (this.list.get(POSITION).getDistance() < 1f) {
            // Setting distance of drugstore on list item
            final String METERS_POS_FIX = " m";
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(this.list.get(POSITION).getDistance() + METERS_POS_FIX);
        } else {
            // Setting distance of drugstore on list item
            final String KM_POS_FIX = " km";
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(convertToKM(this.list.get(POSITION).getDistance()).toString() + KM_POS_FIX);
        }
    }

}

