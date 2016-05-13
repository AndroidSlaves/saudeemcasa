/*****************************
 * Class name: DrugStoreAdapter (.java)
 *
 * Purpose: Class used to allow the drugstore object is used by a different contract.
 ****************************/

package mds.gpp.saudeemcasa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.model.Hospital;

public class DrugStoreAdapter extends ArrayAdapter<DrugStore>   {
    private Context context;
    private ArrayList<DrugStore> lista;
    public static final int COUNT = 15;

    /**
     *
     * @param context
     *              Defines the context where Adapter is instantiated.
     * @param lista
     *              List of DrugStores to populate adapter view.
     */
    public DrugStoreAdapter(Context context, ArrayList<DrugStore> lista){
        super(context,0,lista);

        assert (context != null) : "context must never be null";
        assert (lista != null) : "lista must never be null";
        assert (lista.size() > 0) : "lista must never be empty";

        this.context = context;
        this.lista = lista;
    }

    /**
     *
     * @return
     *              Return the quantity of drugstores on list.
     */
    @Override
    public int getCount() {
        return COUNT;
    }

    /**
     *
     * @param position
     *              Represents the array index.
     * @return
     *              Return DrugStore object from array.
     */
    @Override
    public DrugStore getItem(int position) {
        assert (position >= 0) : "position must never be negative";

        return lista.get(position);
    }

    /**
     *
     * @param position
     *              Arraylist index, populates respective adapter item.
     * @param convertView
     *              The old view to reuse.
     * @param parent
     *              Represents the style of adapter.
     * @return
     *              Return a new view with adapter settings.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        assert (position >= 0) : "position must never be null";
        assert (convertView != null) : "convertView must never be null";
        assert (parent != null) : "parent must never be null";

        return populateAdapter(convertView,position);
    }

    /**
     * convert the distance value from meters to kilometers.
     *
     * @param distance
     *           float value to be converted.
     *
     * @return distance value in km.
     */
    private Float convertToKM(Float distance){
        assert (distance > 0): "distance must never be negative";

        return distance/1000;

    }

    /**
     * Inflate the list view so that a list can be created with the specified elements.
     *
     * @param convertView
     *           View to inflated.
     *
     * @param position
     *           index in the view list.
     *@return inflated layout.
     */
    public View populateAdapter(View convertView, int position){
        assert (convertView != null) : "convertView must never be null";
        assert (position >= 0) : "position must never be null";
        // Override method to get view
        DrugStore drugStorePosition = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);

        // Setting name of drugstore on list item
        TextView textView = (TextView) convertView.findViewById(R.id.textView2_item);
        textView.setText((CharSequence) drugStorePosition.getName());


        setDistance(convertView, position);

        return convertView;
    }

    /**
     * set distance value in the item layout
     *
     * @param convertView
     *           View to be accessed.
     *
     * @param position
     *           position of the item layout to be accessed.
     */
    public void setDistance(View convertView, int position) {
        assert (position >= 0) : "position must never be null";
        assert (convertView != null) : "convertView must never be null";

        if (this.lista.get(position).getDistance() < 1f) {
            // Setting distance of drugstore on list item
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(this.lista.get(position).getDistance() + " m");
        }else {
            // Setting distance of drugstore on list item
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(convertToKM(this.lista.get(position).getDistance()).toString() + " Km");
        }

    }

}
