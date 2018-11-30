package mx.digitalcoaster.rzertuche.medicoencasa.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DataBaseHelper extends SQLiteOpenHelper {

    private String TAG = getClass().getSimpleName();
    //DATABASE....
    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;

    String pregunta;
    String categoria;
    String hint;
    String id;

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
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_PACIENTES + " Creada");

        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
                DataBaseDB.PACIENTES_SINCRONIZAR_TIEMPO_ENCUESTA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_EDAD + " TEXT," +
                DataBaseDB.PACIENTES_SINCRONIZAR_ID + " TEXT); "

        );
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR + " Creada");



        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_VISITAS + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.PACIENTES_VISITA_NOMBRE + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CURP + " TEXT, " +

                DataBaseDB.PACIENTES_VISITA_DIRECCION + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_NUMERO + " TEXT," +
                DataBaseDB.PACIENTES_VISITA_STATUS + " TEXT, " + //5
                DataBaseDB.PACIENTES_VISITA_FECHA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_DIAGNOSTICO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_TRATAMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_EXPEDIENTE + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_HEMOTIPO + " TEXT, " +//10
                DataBaseDB.PACIENTES_VISITA_PESO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ESTATURA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_TENSION + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CARDIACA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_RESPIRATORIA + " TEXT, " +//15
                DataBaseDB.PACIENTES_VISITA_TALLA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_PULSO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GLUCEMIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_TEMPERATURA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_NOTAS_ENFERMERIA + " TEXT, " +//20
                DataBaseDB.PACIENTES_VISITA_GINECOOBSTERICOS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ACTUALES + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GENERALES + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_RESPIRATORIO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CARDIO + " TEXT, " +//25
                DataBaseDB.PACIENTES_VISITA_DIGESTIVO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_URINARIO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_REPRODUCTOR + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_HEMOLI + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ENDOCRINO + " TEXT, " +//30
                DataBaseDB.PACIENTES_VISITA_NERVIOSO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ESQUELETICO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_PIEL + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_HABITUS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CABEZA + " TEXT, " +//35
                DataBaseDB.PACIENTES_VISITA_CUELLO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_ABDOMEN + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GINECOLOGICA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_EXTREMIDADES + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_COLUMNA + " TEXT, " +//40
                DataBaseDB.PACIENTES_VISITA_NEUROLOGICA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_GENITALES + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_NOTAS_DOC + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_CHECK_HOJA_DIARIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_MAKE_HOJA_DIARIA + " TEXT, " +//45
                DataBaseDB.PACIENTES_VISITA_ELABORO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SIGUIENTE_VISITA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_PLANES + " TEXT); "
        );
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_PACIENTES_VISITAS + " Creada");


        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
                DataBaseDB.PACIENTES_TRATAMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_TORAX + " TEXT, " +
                DataBaseDB.PACIENTES_PERSONALES_HEREDO + " TEXT); "
        );
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE + " Creada");

        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FECHA + " TEXT, " +//5
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_EXPEDIENTE + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_STATUS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOTAS_ENFERMERIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SUBJETIVO + " TEXT, " +//10
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_OBJETIVO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ANALISIS + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PLAN + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_HEMOTIPO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PESO + " TEXT, " +//15
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ESTATURA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TENSION_ARTERIAL + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_CARDIACA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_RESPIRATORIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TALLA + " TEXT, " +//20
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PULSO + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_GLUCEMIA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TEMPERATURA + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ID + " TEXT, " +
                DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SYNC + " TEXT); "//25

        );
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + " Creada");


        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_CODIGOS_POSTALES + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.CODIGO_POSTAL + " TEXT, " +
                DataBaseDB.COLONIA + " TEXT, " +
                DataBaseDB.MUNICIPIO + " TEXT, " +
                DataBaseDB.ESTADO + " TEXT); "
        );
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_CODIGOS_POSTALES + " Creada");



        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_STATUS + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_EXPEDIENTE + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ELABORO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SIGUIENTE_VISITA + " TEXT," +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HEMOTIPO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PESO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ESTATURA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TENSION + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CARDIACA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_RESPIRATORIA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TALLA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PULSO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GLUCEMIA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TEMPERATURA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_HDA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_CARDIO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_DIABETES + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_DISLEP + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_OBESIDAD + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_CEREBRO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PADECIMIENTOS_ACTUALES + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_PATOLOGICOS + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_NO_PATOLOGICOS + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GINECOOBSTERICOS + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ACTUALES + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GENERALES + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_RESPIRATORIO + " TEXT, " + //29
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CARDIO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_DIGESTIVO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_URINARIO + " TEXT, " + //32
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_REPRODUCTOR + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HEMOLI + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ENDOCRINO + " TEXT, " + //35
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NERVIOSO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ESQUELETICO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PIEL + " TEXT, " + //38
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HABITUS + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CABEZA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CUELLO + " TEXT, " + //41
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ABDOMEN + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GINECOLOGICA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_EXTREMIDADES + " TEXT, " + //44
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_COLUMNA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NEUROLOGICA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GENITALES + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOTAS_ENFERMERIA + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOTAS_DOC + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PLANES + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_DIAGNOSTICO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TRATAMIENTO + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID + " TEXT, " + //53 -:::
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TORAX + " TEXT, " +
                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_HEREDO + " TEXT, " +

                DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS + " TEXT ); "

        );


        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC + " Creada");


        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_PREGUNTAS + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.PREGUNTAS_ID + " TEXT, " +
                DataBaseDB.PREGUNTAS_TIPO + " TEXT, " +
                DataBaseDB.PREGUNTAS_CATEGORIA + " TEXT, " +
                DataBaseDB.PREGUNTAS_TITULO + " TEXT, " +
                DataBaseDB.PREGUNTAS_DESCRIPTION + " TEXT, " +
                DataBaseDB.PREGUNTAS_HINT + " TEXT); "
        );
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_PREGUNTAS + " Creada");

        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_RESPUESTAS + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.RESPUESTAS_ID + " TEXT, " +
                DataBaseDB.RESPUESTAS_DESCRIPTION + " TEXT, " +
                DataBaseDB.RESPUESTAS_CATEGORIA + " TEXT); "
        );
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_RESPUESTAS + " Creada");

        /*---------------------------- Creación de la table de session ---------------------------*/
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataBaseDB.TABLE_NAME_RESPUESTAS_RADIO + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataBaseDB.RESPUESTAS_ID_RADIO + " TEXT, " +
                DataBaseDB.RESPUESTAS_DESCRIPTION_RADIO + " TEXT, " +
                DataBaseDB.RESPUESTAS_CATEGORIA_RADIO + " TEXT, " +
                DataBaseDB.RESPUESTAS_RADIO1 + " TEXT, " +
                DataBaseDB.RESPUESTAS_RADIO2 + " TEXT, " +
                DataBaseDB.RESPUESTAS_RADIO3 + " TEXT, " +
                DataBaseDB.RESPUESTAS_RADIO4 + " TEXT, " +
                DataBaseDB.RESPUESTAS_RADIO5 + " TEXT, " +
                DataBaseDB.RESPUESTAS_RADIO6 + " TEXT); "
        );
        Log.d("DB_CREATE",DataBaseDB.TABLE_NAME_RESPUESTAS_RADIO + " Creada");

    }

    public void addQuestionsDB(JsonObject respuestaJSON){

        db = this.getWritableDatabase();
        for (int i = 0; i < respuestaJSON.size(); i++) {

            JsonObject pregunta1 = respuestaJSON.getAsJsonObject("4");
            JsonObject preguntas = pregunta1.getAsJsonObject("pregunta");

            JsonObject preguntas2 = respuestaJSON.getAsJsonObject("5");
            JsonObject preguntas3 = respuestaJSON.getAsJsonObject("60");
            JsonObject preguntas4 = respuestaJSON.getAsJsonObject("61");
            JsonObject preguntas5 = respuestaJSON.getAsJsonObject("62");
            JsonObject preguntas6 = respuestaJSON.getAsJsonObject("63");
            JsonObject preguntas7 = respuestaJSON.getAsJsonObject("65");


            pregunta = preguntas.get("titulo").toString();
            categoria = preguntas.get("categoria_preguntas_id").toString();
            hint = preguntas.get("categoria_preguntas_id").toString();
            id = preguntas.get("id").toString();


            Log.d(TAG,"PREGUNTA: " + pregunta);
            Log.d(TAG,"CATEGORIA: " + categoria);
            Log.d(TAG,"HINT: " + hint);
            Log.d(TAG,"id: " + id);



            /*------------------------- Revisar si existe ------------------------*/
            Cursor c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PREGUNTAS, null);
            try {
                if (c.moveToFirst()) {

                    ContentValues update = new ContentValues();

                    update.put(DataBaseDB.PREGUNTAS_ID, id);
                    update.put(DataBaseDB.PREGUNTAS_TITULO, pregunta);
                    update.put(DataBaseDB.PREGUNTAS_CATEGORIA, categoria);
                    update.put(DataBaseDB.PREGUNTAS_HINT, hint);

                    db.update(DataBaseDB.TABLE_NAME_PREGUNTAS, update, DataBaseDB.PREGUNTAS_ID + "='" + id + "'", null);
                    Log.d("response_data","Codigo actualizado correctamente");

                } else {
                    ContentValues values = new ContentValues();

                    values.put(DataBaseDB.PREGUNTAS_ID, id);
                    values.put(DataBaseDB.PREGUNTAS_TITULO, pregunta);
                    values.put(DataBaseDB.PREGUNTAS_CATEGORIA, categoria);
                    values.put(DataBaseDB.PREGUNTAS_HINT, hint);


                    db.insert(DataBaseDB.TABLE_NAME_PREGUNTAS, null, values);
                    Log.d("response_data","Codigo postal insertado correctamente");
                    c.close();

                }
            } catch (SQLException ex) {
                Log.d("response_data_db","Error al insertar codigo postal: " + ex);
            }
        }
        db.close();
    }

    public void addCodePostalDB(JsonObject responseCP){
        try {
            db = this.getWritableDatabase();

            JsonArray parentesco = responseCP.getAsJsonArray("codigospostales");

            String codigo_postal;
            String colonia;
            String municipio;
            String estado;

            for (int i = 0; i < parentesco.size(); i++) {

                codigo_postal = parentesco.get(i).getAsJsonObject().get("CodigoPostal").getAsString();
                colonia       = parentesco.get(i).getAsJsonObject().get("Colonia").getAsString();
                municipio     = parentesco.get(i).getAsJsonObject().get("Municipio").getAsString();
                estado        = parentesco.get(i).getAsJsonObject().get("Estado").getAsString();


                Log.d(TAG,"CODIGO_POSTAL: " + codigo_postal);
                Log.d(TAG,"COLONIA: " + colonia);
                Log.d(TAG,"MUNICIPIO: " + municipio);
                Log.d(TAG,"ESTADO: " + estado);

                /*------------------------- Revisar si existe ------------------------*/
                Cursor c = db.rawQuery("SELECT " + DataBaseDB.CODIGO_POSTAL +
                        " FROM " + DataBaseDB.TABLE_NAME_CODIGOS_POSTALES +
                        " WHERE " + DataBaseDB.CODIGO_POSTAL + "='" + codigo_postal + "'", null);
                try {
                    if (c.moveToFirst()) {
                        Log.d("response_data_cp_db","Codigo existente: ");
                        ContentValues update = new ContentValues();

                        update.put(DataBaseDB.CODIGO_POSTAL, codigo_postal);
                        update.put(DataBaseDB.COLONIA, colonia);
                        update.put(DataBaseDB.MUNICIPIO, municipio);
                        update.put(DataBaseDB.ESTADO, estado);

                        db.update(DataBaseDB.TABLE_NAME_CODIGOS_POSTALES, update, DataBaseDB.CODIGO_POSTAL + "='" + codigo_postal + "'", null);
                        Log.d("response_data_cp_db","Codigo actualizado correctamente");

                    } else {
                        ContentValues values = new ContentValues();

                        values.put(DataBaseDB.CODIGO_POSTAL, codigo_postal);
                        values.put(DataBaseDB.COLONIA, colonia);
                        values.put(DataBaseDB.MUNICIPIO, municipio);
                        values.put(DataBaseDB.ESTADO, estado);

                        db.insert(DataBaseDB.TABLE_NAME_CODIGOS_POSTALES, null, values);
                        Log.d("response_data_cp_db","Codigo postal insertado correctamente");
                    }
                    c.close();
                } catch (SQLException ex) {
                    Log.d("response_data_cp_db","Error al insertar codigo postal: " + ex);
                }
            }
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPaicentesDB(JsonObject responsePacientes){
        try{
            db = this.getWritableDatabase();
            JsonArray pacientes = responsePacientes.getAsJsonArray("pacientes");

            String  no_expediente,
                    fecha,
                    curp,
                    apellidoPaterno,
                    apellidoMaterno,
                    nombre,
                    fechadeNacimiento,
                    estadodeNacimiento;

            Integer sexo;

            String nacionalidadOrigen,
                    estadoResidencia,
                    municipio,
                    cp,
                    visita,
                    pob_vul,
                    colonia,
                    nombreCalle,
                    estadoCivil,
                    ocupacion,
                    derechoHabiencia,
                    telFijo,
                    telCelular,
                    correoElectronico;

            Integer createdBy;

            String  created_at,
                    updated_at;

            String  hemo,
                    peso,
                    estatura,
                    tension, //el que tiene ' ' '
                    frecuencia_car,
                    frecuencia_resp,
                    talla,
                    pulso,
                    glucemia,
                    temp,
                    ant_personales,
                    ant_patologicos,
                    ant_obstericos,
                    padecimiento_actual,
                    sistemas_generales,
                    respiratorio,
                    cardio,
                    digestivo,
                    urinario,
                    repro,
                    hemolin,
                    endo,
                    s_nervioso,
                    esqueletico,
                    piel,
                    habitus,
                    cabeza,
                    cuello,
                    torax,
                    abdomen,
                    gine,
                    extremidades,
                    c_vertebral,
                    neuro,
                    genitales,
                    notas_doc,
                    plan,
                    impresion_diag,
                    tratamiento;

            for(int i = 0 ; i < pacientes.size() ; i++){
                no_expediente     = pacientes.get(i).getAsJsonObject().get("no_expediente").getAsString();
                fecha             = pacientes.get(i).getAsJsonObject().get("fecha").getAsString();
                curp              = pacientes.get(i).getAsJsonObject().get("curp").getAsString();
                apellidoPaterno   = pacientes.get(i).getAsJsonObject().get("apellidoPaterno").getAsString();
                apellidoMaterno   = pacientes.get(i).getAsJsonObject().get("apellidoMaterno").getAsString();
                nombre            = pacientes.get(i).getAsJsonObject().get("nombre").getAsString();
                fechadeNacimiento  = pacientes.get(i).getAsJsonObject().get("fechadeNacimiento").getAsString();
                estadodeNacimiento = pacientes.get(i).getAsJsonObject().get("estadodeNacimiento").getAsString();

                sexo = pacientes.get(i).getAsJsonObject().get("sexo").getAsInt();

                nacionalidadOrigen = pacientes.get(i).getAsJsonObject().get("nacionalidadOrigen").getAsString();
                estadoResidencia   = pacientes.get(i).getAsJsonObject().get("estadoResidencia").getAsString();
                municipio          = pacientes.get(i).getAsJsonObject().get("municipio").getAsString();
                cp                 = pacientes.get(i).getAsJsonObject().get("cp").getAsString();
                visita             = pacientes.get(i).getAsJsonObject().get("visita").getAsString();
                pob_vul            = pacientes.get(i).getAsJsonObject().get("pob_vul").getAsString();
                colonia            = pacientes.get(i).getAsJsonObject().get("colonia").getAsString();
                nombreCalle        = pacientes.get(i).getAsJsonObject().get("nombreCalle").getAsString();
                estadoCivil        = pacientes.get(i).getAsJsonObject().get("estadoCivil").getAsString();
                ocupacion          = pacientes.get(i).getAsJsonObject().get("ocupacion").getAsString();
                derechoHabiencia   = pacientes.get(i).getAsJsonObject().get("derechoHabiencia").getAsString();
                telFijo            = pacientes.get(i).getAsJsonObject().get("telFijo").getAsString();
                telCelular         = pacientes.get(i).getAsJsonObject().get("telCelular").getAsString();

                createdBy         = pacientes.get(i).getAsJsonObject().get("createdBy").getAsInt();

                created_at        = pacientes.get(i).getAsJsonObject().get("created_at").getAsString();
                updated_at        = pacientes.get(i).getAsJsonObject().get("updated_at").getAsString();

                hemo = pacientes.get(i).getAsJsonObject().get("hemo").getAsString();
                peso = pacientes.get(i).getAsJsonObject().get("peso").getAsString();
                estatura = pacientes.get(i).getAsJsonObject().get("estatura").getAsString();
                tension = pacientes.get(i).getAsJsonObject().get("tensión").getAsString();
                frecuencia_car = pacientes.get(i).getAsJsonObject().get("frecuencia_car").getAsString();
                frecuencia_resp = pacientes.get(i).getAsJsonObject().get("frecuencia_resp").getAsString();
                talla = pacientes.get(i).getAsJsonObject().get("talla").getAsString();
                pulso = pacientes.get(i).getAsJsonObject().get("pulso").getAsString();
                glucemia = pacientes.get(i).getAsJsonObject().get("glucemia").getAsString();
                temp = pacientes.get(i).getAsJsonObject().get("temp").getAsString();
                ant_personales = pacientes.get(i).getAsJsonObject().get("ant_personales").getAsString();
                ant_patologicos = pacientes.get(i).getAsJsonObject().get("ant_patologicos").getAsString();
                ant_obstericos = pacientes.get(i).getAsJsonObject().get("ant_obstericos").getAsString();
                padecimiento_actual = pacientes.get(i).getAsJsonObject().get("padecimiento_actual").getAsString();
                sistemas_generales = pacientes.get(i).getAsJsonObject().get("sistemas_generales").getAsString();
                respiratorio = pacientes.get(i).getAsJsonObject().get("respiratorio").getAsString();
                cardio = pacientes.get(i).getAsJsonObject().get("cardio").getAsString();
                digestivo = pacientes.get(i).getAsJsonObject().get("digestivo").getAsString();
                urinario = pacientes.get(i).getAsJsonObject().get("urinario").getAsString();
                repro = pacientes.get(i).getAsJsonObject().get("repro").getAsString();
                hemolin = pacientes.get(i).getAsJsonObject().get("hemolin").getAsString();
                endo = pacientes.get(i).getAsJsonObject().get("endo").getAsString();
                s_nervioso = pacientes.get(i).getAsJsonObject().get("s_nervioso").getAsString();
                esqueletico = pacientes.get(i).getAsJsonObject().get("esqueletico").getAsString();
                piel = pacientes.get(i).getAsJsonObject().get("piel").getAsString();
                habitus = pacientes.get(i).getAsJsonObject().get("habitus").getAsString();
                cabeza = pacientes.get(i).getAsJsonObject().get("cabeza").getAsString();
                cuello = pacientes.get(i).getAsJsonObject().get("cuello").getAsString();
                torax = pacientes.get(i).getAsJsonObject().get("torax").getAsString();
                abdomen = pacientes.get(i).getAsJsonObject().get("abdomen").getAsString();
                gine = pacientes.get(i).getAsJsonObject().get("gine").getAsString();
                extremidades = pacientes.get(i).getAsJsonObject().get("extremidades").getAsString();
                c_vertebral = pacientes.get(i).getAsJsonObject().get("c_vertebral").getAsString();
                neuro = pacientes.get(i).getAsJsonObject().get("neuro").getAsString();
                genitales = pacientes.get(i).getAsJsonObject().get("genitales").getAsString();
                notas_doc = pacientes.get(i).getAsJsonObject().get("notas_doc").getAsString();
                plan = pacientes.get(i).getAsJsonObject().get("plan").getAsString();
                impresion_diag = pacientes.get(i).getAsJsonObject().get("impresion_diag").getAsString();
                tratamiento= pacientes.get(i).getAsJsonObject().get("tratamiento").getAsString();



                Cursor c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC,null);

                if(c.moveToFirst()){
                    ContentValues update = new ContentValues();

                    update.put(DataBaseDB.PACIENTES_EXPEDIENTE   , no_expediente);
                    update.put(DataBaseDB.PACIENTES_VISITA_FECHA , fecha);
                    update.put(DataBaseDB.PACIENTES_CURP         , curp);
                    update.put(DataBaseDB.PACIENTES_AP_PATERNO   , apellidoPaterno);
                    update.put(DataBaseDB.PACIENTES_AP_MATERNO   , apellidoMaterno);
                    update.put(DataBaseDB.PACIENTES_NOMBRE       , nombre);
                    update.put(DataBaseDB.PACIENTES_FECHA_NACIMIENTO,fechadeNacimiento);
                    update.put(DataBaseDB.PACIENTES_ESTADO_NACIMIENTO,estadodeNacimiento);

                    update.put(DataBaseDB.PACIENTES_SEXO         , sexo);

                    update.put(DataBaseDB.PACIENTES_NACIONALIDAD ,nacionalidadOrigen);
                    //update.put(DataBaseDB.PACIENTES_ESTADORES);

                    //update.put(DataBaseDB.PACIENTES_EXPEDIENTE);
                }else{

                }
            }
            db.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void printAllDB(){

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
