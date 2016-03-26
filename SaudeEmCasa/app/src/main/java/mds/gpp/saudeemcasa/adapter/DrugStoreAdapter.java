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

    // Setting DrugStoreAdapter constructor
    public DrugStoreAdapter(Context context, ArrayList<DrugStore> lista){
        super(context,0,lista);
        this.context = context;
        this.lista = lista;
    }

    //Set the number of objects to show on list
    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public DrugStore getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return populateAdapter(convertView,position);
    }
    /**
     * convert the distance value from meters to kilometers.
     *
     * @param distance
     *           float value to be converted.
     *
     * @return distance value in km.
     *
     */
    private Float convertToKM(Float distance){
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
     * */
    public View populateAdapter(View convertView, int position){
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
     * */
    public void setDistance(View convertView, int position) {
        if (this.lista.get(position).getDistance() < 1f) {
            // Setting distance of drugstore on list item
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(this.lista.get(position).getDistance() + " m");
        } else {
            // Setting distance of drugstore on list item
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(convertToKM(this.lista.get(position).getDistance()).toString() + " Km");
        }

        //convertView.setOnClickListener(new OnItemClickListener(position));
        //return convertView;
    }

}
