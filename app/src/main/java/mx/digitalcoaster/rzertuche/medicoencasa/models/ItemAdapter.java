package mx.digitalcoaster.rzertuche.medicoencasa.models;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mx.digitalcoaster.rzertuche.medicoencasa.R;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> items;
    private int count=0;


    public ItemAdapter(Context context, List<Item> items) {
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
            rowView = inflater.inflate(R.layout.item_pacientes, viewGroup, false);
        }

        TextView nombre  = (TextView) rowView.findViewById(R.id.tvNombre);
        TextView curp  = (TextView) rowView.findViewById(R.id.tv_curp);
        TextView direccion = (TextView) rowView.findViewById(R.id.tv_direccion);

        Item item = this.items.get(position);


        nombre.setText(item.getNombre().toString());
        curp.setText(item.getCurp().toString());
        direccion.setText(item.getDireccion().toString());


        return rowView;
    }
}
