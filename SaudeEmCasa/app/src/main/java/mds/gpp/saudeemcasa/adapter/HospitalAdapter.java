package mds.gpp.saudeemcasa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.model.Hospital;

public class HospitalAdapter extends ArrayAdapter<Hospital>   {

    private Context context;
    private ArrayList<Hospital> lista;
    public static final int COUNT = 15;

    public HospitalAdapter(Context context, ArrayList<Hospital> lista){
        super(context, 0, lista);

        Assert(context != null);
        Assert(lista != null);
        Assert(lista.length() > 0);
        
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() { return COUNT; }

    @Override
    public Hospital getItem(int position) {
        
        Assert(position >= 0);
        Assert(position < 32000); //Int size
        
        return lista.get(position);
    }
    //fix this function
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        Assert(position >= 0);
        Assert(convertView != null);
        Assert(parent != null);

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
        Assert(distance > 0);

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
        Assert(convertView != null);
        Assert(position >= 0);

        Hospital hospitalPosition = this.lista.get(position);
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
     *           View to be accessed.
     *
     * @param position
     *           position of the item layout to be accessed.
     * */
    public void setDistance(View convertView, int position) {
        Assert(position >= 0);
        Assert(convertView != null);
        
        if (this.lista.get(position).getDistance() < 1f) {
            // Setting distance of drugstore on list item
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(this.lista.get(position).getDistance() + " m");
        } else {
            // Setting distance of drugstore on list item
            TextView textViewDistance = (TextView) convertView.findViewById(R.id.textView4_item);
            textViewDistance.setText(convertToKM(this.lista.get(position).getDistance()).toString() + " Km");
        }
    }

}
