package mx.digitalcoaster.rzertuche.medicoencasa.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mx.digitalcoaster.rzertuche.medicoencasa.R;

public class ItemVisitaAdapter extends BaseAdapter {

    private Context context;
    private List<ItemVisita> items;
    private int count=0;


    public ItemVisitaAdapter(Context context, List<ItemVisita> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
            return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View rowView = view;

        if (view == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_seguimiento, viewGroup, false);
        }

        TextView visita  = (TextView) rowView.findViewById(R.id.tv_visita);
        TextView fecha  = (TextView) rowView.findViewById(R.id.tv_fecha);

        ItemVisita item = this.items.get(position);


        visita.setText("VISITA "+ item.getNumero_visita().toString());
        fecha.setText(item.getFecha_visita().toString());


        return rowView;
    }
}
