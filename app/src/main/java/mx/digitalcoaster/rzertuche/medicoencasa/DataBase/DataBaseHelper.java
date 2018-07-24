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
                DataBaseDB.PACIENTES_CALLE + " TEXT, " +
                DataBaseDB.PACIENTES_AP_PATERNO + " TEXT, " +
                DataBaseDB.PACIENTES_AP_MATERNO + " TEXT, " +
                DataBaseDB.PACIENTES_FECHA_NACIMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_ESTADO_NACIMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_SEXO + " TEXT, " +
                DataBaseDB.PACIENTES_NACIONALIDAD + " TEXT, " +
                DataBaseDB.PACIENTES_ESTADO + " TEXT, " +
                DataBaseDB.PACIENTES_MUNICIPIO + " TEXT, " +
                DataBaseDB.PACIENTES_COLONIA + " TEXT, " +
                DataBaseDB.PACIENTES_ESTADO_CIVIL + " TEXT, " +
                DataBaseDB.PACIENTES_OCUPACION + " TEXT, " +
                DataBaseDB.PACIENTES_DERECHO + " TEXT, " +
                DataBaseDB.PACIENTES_FOLIO_DERECHO + " TEXT, " +
                DataBaseDB.PACIENTES_TEL_FIJO + " TEXT, " +
                DataBaseDB.PACIENTES_CEL + " TEXT, " +
                DataBaseDB.PACIENTES_EMAIL + " TEXT, " +
                DataBaseDB.PACIENTES_CREATED_BY + " TEXT, " +
                DataBaseDB.PACIENTES_CODIGO + " TEXT, " +
                DataBaseDB.PACIENTES_POBLACION + " TEXT, " +
                DataBaseDB.PACIENTES_TIEMPO_ENCUESTA + " TEXT); "
        );
        System.out.println(DataBaseDB.TABLE_NAME_PACIENTES + " Creada");

        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_NOMBRE + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_CURP + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_CALLE + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_AP_PATERNO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_AP_MATERNO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_FECHA_NACIMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO_NACIMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_SEXO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_NACIONALIDAD + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_MUNICIPIO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_COLONIA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO_CIVIL + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_OCUPACION + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_DERECHO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_FOLIO_DERECHO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_TEL_FIJO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_CEL + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_EMAIL + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_CREATED_BY + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_CODIGO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_POBLACION + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_TIEMPO_ENCUESTA + " TEXT); "
        );
        System.out.println(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR + " Creada");



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
                DataBaseDB.PACIENTES_VISITA_TRATAMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_EXPEDIENTE + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_HEMOTIPO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_PESO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ESTATURA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_TENSION + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CARDIACA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_RESPIRATORIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_TALLA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_PULSO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GLUCEMIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_TEMPERATURA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_NOTAS_ENFERMERIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GINECOOBSTERICOS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ACTUALES + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GENERALES + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_RESPIRATORIO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CARDIO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_DIGESTIVO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_URINARIO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_REPRODUCTOR + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_HEMOLI + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ENDOCRINO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_NERVIOSO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ESQUELETICO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_PIEL + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_HABITUS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CABEZA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CUELLO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ABDOMEN + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GINECOLOGICA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_EXTREMIDADES + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_COLUMNA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_NEUROLOGICA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GENITALES + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_NOTAS_DOC + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CHECK_HOJA_DIARIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_MAKE_HOJA_DIARIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ELABORO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SIGUIENTE_VISITA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_PLANES + " TEXT); "
        );
        System.out.println(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS + " Creada");


        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.PACIENTES_EXPEDIENTE_NOMBRE + " TEXT, " +
                DataBaseDB.PACIENTES_EXPEDIENTE_CURP + " TEXT, " +
                DataBaseDB.PACIENTES_STATUS + " TEXT, " +
                DataBaseDB.PACIENTES_EXPEDIENTE + " TEXT, " +
                DataBaseDB.PACIENTES_ELABORO + " TEXT, " +
                DataBaseDB.PACIENTES_SIGUIENTE_VISITA + " TEXT," +
                DataBaseDB.PACIENTES_HEMOTIPO + " TEXT, " +
                DataBaseDB.PACIENTES_PESO + " TEXT, " +
                DataBaseDB.PACIENTES_ESTATURA + " TEXT, " +
                DataBaseDB.PACIENTES_TENSION + " TEXT, " +
                DataBaseDB.PACIENTES_CARDIACA + " TEXT, " +
                DataBaseDB.PACIENTES_RESPIRATORIA + " TEXT, " +
                DataBaseDB.PACIENTES_TALLA + " TEXT, " +
                DataBaseDB.PACIENTES_PULSO + " TEXT, " +
                DataBaseDB.PACIENTES_GLUCEMIA + " TEXT, " +
                DataBaseDB.PACIENTES_TEMPERATURA + " TEXT, " +
                DataBaseDB.PACIENTES_NOTAS_ENFERMERIA + " TEXT, " +
                DataBaseDB.PACIENTES_ANTECEDENTES_HDA + " TEXT, " +
                DataBaseDB.PACIENTES_ANTECEDENTES_CARDIO + " TEXT, " +
                DataBaseDB.PACIENTES_ANTECEDENTES_DIABETES + " TEXT, " +
                DataBaseDB.PACIENTES_ANTECEDENTES_DISLEP + " TEXT, " +
                DataBaseDB.PACIENTES_ANTECEDENTES_OBESIDAD + " TEXT, " +
                DataBaseDB.PACIENTES_ANTECEDENTES_CEREBRO + " TEXT, " +
                DataBaseDB.PACIENTES_PADECIMIENTOS_ACTUALES + " TEXT, " +
                DataBaseDB.PACIENTES_PERSONALES_PATOLOGICOS + " TEXT, " +
                DataBaseDB.PACIENTES_PERSONALES_NO_PATOLOGICOS + " TEXT, " +
                DataBaseDB.PACIENTES_GINECOOBSTERICOS + " TEXT, " +
                DataBaseDB.PACIENTES_ACTUALES + " TEXT, " +
                DataBaseDB.PACIENTES_GENERALES + " TEXT, " +
                DataBaseDB.PACIENTES_RESPIRATORIO + " TEXT, " +
                DataBaseDB.PACIENTES_CARDIO + " TEXT, " +
                DataBaseDB.PACIENTES_DIGESTIVO + " TEXT, " +
                DataBaseDB.PACIENTES_URINARIO + " TEXT, " +
                DataBaseDB.PACIENTES_REPRODUCTOR + " TEXT, " +
                DataBaseDB.PACIENTES_HEMOLI + " TEXT, " +
                DataBaseDB.PACIENTES_ENDOCRINO + " TEXT, " +
                DataBaseDB.PACIENTES_NERVIOSO + " TEXT, " +
                DataBaseDB.PACIENTES_ESQUELETICO + " TEXT, " +
                DataBaseDB.PACIENTES_PIEL + " TEXT, " +
                DataBaseDB.PACIENTES_HABITUS + " TEXT, " +
                DataBaseDB.PACIENTES_CABEZA + " TEXT, " +
                DataBaseDB.PACIENTES_CUELLO + " TEXT, " +
                DataBaseDB.PACIENTES_ABDOMEN + " TEXT, " +
                DataBaseDB.PACIENTES_GINECOLOGICA + " TEXT, " +
                DataBaseDB.PACIENTES_EXTREMIDADES + " TEXT, " +
                DataBaseDB.PACIENTES_COLUMNA + " TEXT, " +
                DataBaseDB.PACIENTES_NEUROLOGICA + " TEXT, " +
                DataBaseDB.PACIENTES_GENITALES + " TEXT, " +
                DataBaseDB.PACIENTES_NOTAS_DOC + " TEXT, " +
                DataBaseDB.PACIENTES_PLANES + " TEXT, " +
                DataBaseDB.PACIENTES_IMPRESION_DIAGNOSTICA + " TEXT, " +
                DataBaseDB.PACIENTES_TRATAMIENTO + " TEXT); "
        );
        System.out.println(DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE + " Creada");



        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FECHA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_EXPEDIENTE + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_STATUS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOTAS_ENFERMERIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SUBJETIVO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_OBJETIVO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ANALISIS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PLAN + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_HEMOTIPO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PESO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ESTATURA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TENSION_ARTERIAL + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_CARDIACA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_RESPIRATORIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TALLA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PULSO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_GLUCEMIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TEMPERATURA + " TEXT); "

        );
        System.out.println(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + " Creada");


        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_CODIGOS_POSTALES + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.CODIGO_POSTAL + " TEXT, " +
                DataBaseDB.COLONIA + " TEXT, " +
                DataBaseDB.MUNICIPIO + " TEXT, " +
                DataBaseDB.ESTADO + " TEXT); "
        );
        System.out.println(DataBaseDB.TABLE_NAME_CODIGOS_POSTALES + " Creada");





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
