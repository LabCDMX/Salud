package mx.digitalcoaster.rzertuche.medicoencasa.models;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mx.digitalcoaster.rzertuche.medicoencasa.R;

public class VisitasAdapter extends BaseAdapter {

    private Context context;
    private List<Item> items;
    private int count=0;


    public VisitasAdapter(Context context, List<Item> items) {
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View rowView = view;

        if (view == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_visita, viewGroup, false);
        }

        TextView nombre  = (TextView) rowView.findViewById(R.id.tvNombre);
        TextView curp  = (TextView) rowView.findViewById(R.id.tvCurp);
        TextView direccion = (TextView) rowView.findViewById(R.id.tvDireccion);
        TextView tv_status = (TextView) rowView.findViewById(R.id.textView5);
        TextView status = (TextView) rowView.findViewById(R.id.textView3);
        View palomitaView = (View) rowView.findViewById(R.id.palomitaView);


        Item item = this.items.get(position);

        if(item.getStatus().equals("Sano")){
            tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorSano));
            status.setBackgroundColor(context.getResources().getColor(R.color.colorSano));
        }else if(item.getStatus().equals("Sobrepeso")){

            tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorAmbar));
            status.setBackgroundColor(context.getResources().getColor(R.color.colorAmbar));

        }else if(item.getStatus().equals("Obeso")){

            tv_status.setBackgroundColor(context.getResources().getColor(R.color.colorRojo));
            status.setBackgroundColor(context.getResources().getColor(R.color.colorRojo));
        }

        if(item.getMakeHojaDiaria()){
            palomitaView.setVisibility(View.VISIBLE);
        }else if(item.getCheckHojaDiaria()){
            palomitaView.setBackground(context.getResources().getDrawable(R.drawable.paloma2));
        }else{
            palomitaView.setVisibility(View.GONE);
        }




        nombre.setText(item.getNombre().toString());
        curp.setText(item.getCurp().toString());
        direccion.setText(item.getDireccion().toString());


        return rowView;
    }
}
