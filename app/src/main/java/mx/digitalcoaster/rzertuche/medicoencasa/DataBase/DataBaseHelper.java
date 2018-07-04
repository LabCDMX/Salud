package mx.digitalcoaster.rzertuche.medicoencasa.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.PACIENTES_NOMBRE + " TEXT, " +
                DataBaseDB.PACIENTES_CURP + " TEXT, " +
                DataBaseDB.PACIENTES_DIRECCION + " TEXT); "
        );
        System.out.println(DataBaseDB.TABLE_NAME_PACIENTES + " Creada");


           /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_VISITAS + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.PACIENTES_VISITA_NOMBRE + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CURP + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_DIRECCION + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_NUMERO + " TEXT," +
                DataBaseDB.PACIENTES_VISITA_STATUS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_FECHA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_DIAGNOSTICO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_TRATAMIENTO + " TEXT); "
        );
        System.out.println(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS + " Creada");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
