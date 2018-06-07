package mx.digitalcoaster.rzertuche.medicoencasa.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.models.User;

/**
 * Created by zertuche on 4/3/18.
 */

public class UsersAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<User> users;

    public UsersAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.item, null);

            // set image based on selected text
            TextView nombre = (TextView) gridView
                    .findViewById(R.id.nombre);

            TextView curp = (TextView) gridView
                    .findViewById(R.id.curp);

            TextView direccion = (TextView) gridView
                    .findViewById(R.id.direccion);


            User user = users.get(position);

            nombre.setText("Nombre: "+user.getNombre()+" "+user.getApellido_Paterno()+" "+user.getApellido_Materno());
            Log.d("Adapter", nombre.getText().toString());
            curp.setText("CURP: "+user.getcURP());
            direccion.setText("Dirección: "+user.getLocalidad_colonia()+", "+user.getEstado());

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}