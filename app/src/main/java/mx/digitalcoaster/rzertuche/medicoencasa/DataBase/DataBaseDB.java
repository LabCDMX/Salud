package mx.digitalcoaster.rzertuche.medicoencasa.DataBase;

public class DataBaseDB {

    /*-------------------------------- Datos de la base de datos ---------------------------------*/
    public static final String DB_NAME = "database_medicoentucasa";    // Nombre de la base de datos

    public static final String TABLE_NAME_PACIENTES = "table_name_pacientes";
    public static final String TABLE_NAME_PACIENTES_VISITAS = "table_name_pacientes_visitas";
    public static final String TABLE_NAME_PACIENTES_SIN_EXPEDIENTE = "table_name_pacientes_expediente";
    public static final String TABLE_NAME_PACIENTES_SEGUIMIENTO = "table_name_pacientes_seguimiento";
    public static final String TABLE_NAME_CODIGOS_POSTALES = "table_name_codigos_postales";




    public static int VERSION = 1;


    public static final String CODIGO_POSTAL = "codigo_postal";
    public static final String COLONIA = "colonia";
    public static final String MUNICIPIO = "municipio";
    public static final String ESTADO = "estado";

    public static final String PACIENTES_NOMBRE = "pacientes_nombre";
    public static final String PACIENTES_CURP = "pacientes_curp";
    public static final String PACIENTES_CALLE = "pacientes_calle";
    public static final String PACIENTES_AP_PATERNO = "pacientes_ap_paterno";
    public static final String PACIENTES_AP_MATERNO = "pacientes_ap_materno";
    public static final String PACIENTES_FECHA_NACIMIENTO = "pacientes_fecha_nacimiento";
    public static final String PACIENTES_ESTADO_NACIMIENTO = "pacientes_estado_nacimiento";
    public static final String PACIENTES_SEXO = "pacientes_sexo";
    public static final String PACIENTES_NACIONALIDAD = "pacientes_nacionalidad";
    public static final String PACIENTES_ESTADO = "pacientes_estado";
    public static final String PACIENTES_MUNICIPIO = "pacientes_municipio";
    public static final String PACIENTES_COLONIA = "pacientes_colonia";
    public static final String PACIENTES_ESTADO_CIVIL = "pacientes_estado_civil";
    public static final String PACIENTES_OCUPACION = "pacientes_ocupacion";
    public static final String PACIENTES_DERECHO = "pacientes_derecho";
    public static final String PACIENTES_FOLIO_DERECHO = "pacientes_folio_derecho";
    public static final String PACIENTES_TEL_FIJO = "pacientes_tel_fijo";
    public static final String PACIENTES_CEL = "pacientes_cel";
    public static final String PACIENTES_EMAIL = "pacientes_email";
    public static final String PACIENTES_CREATED_BY = "pacientes_created_by";





    //EXPEDIENTE, HISTORIA CLINICA, SIGUIENTE VISITA
    public static final String PACIENTES_EXPEDIENTE_NOMBRE = "pacientes_expediente_nombre";
    public static final String PACIENTES_EXPEDIENTE_CURP = "pacientes_expediente_curp";
    public static final String PACIENTES_STATUS = "pacientes_status";
    public static final String PACIENTES_EXPEDIENTE = "pacientes_expediente";
    public static final String PACIENTES_ELABORO = "pacientes_elaboro";
    public static final String PACIENTES_SIGUIENTE_VISITA = "pacientes_siguiente_visita";
    public static final String PACIENTES_HEMOTIPO = "pacientes_hemotipo";
    public static final String PACIENTES_PESO = "pacientes_peso";
    public static final String PACIENTES_ESTATURA = "pacientes_estatura";
    public static final String PACIENTES_TENSION = "pacientes_tension";
    public static final String PACIENTES_CARDIACA = "pacientes_cardiaca";
    public static final String PACIENTES_RESPIRATORIA = "pacientes_respiratoria";
    public static final String PACIENTES_TALLA = "pacientes_talla";
    public static final String PACIENTES_PULSO = "pacientes_pulso";
    public static final String PACIENTES_GLUCEMIA = "pacientes_glucemia";
    public static final String PACIENTES_TEMPERATURA = "pacientes_temperatura";
    public static final String PACIENTES_NOTAS_ENFERMERIA = "pacientes_notas_enfermeria";
    public static final String PACIENTES_ANTECEDENTES_HDA = "pacientes_antecedentes_hda";
    public static final String PACIENTES_ANTECEDENTES_CARDIO = "pacientes_andecedentes_cardio";
    public static final String PACIENTES_ANTECEDENTES_DIABETES = "pacientes_antecedentes_diabetes";
    public static final String PACIENTES_ANTECEDENTES_DISLEP = "pacientes_antecedentes_dislep";
    public static final String PACIENTES_ANTECEDENTES_OBESIDAD = "pacientes_antecedentes_obesidad";
    public static final String PACIENTES_ANTECEDENTES_CEREBRO = "pacientes_antecedentes_cerebro";
    public static final String PACIENTES_PADECIMIENTOS_ACTUALES = "pacientes_padecimientos_actuales";
    public static final String PACIENTES_PERSONALES_PATOLOGICOS = "pacientes_personales_patologicos";
    public static final String PACIENTES_PERSONALES_NO_PATOLOGICOS = "pacientes_personales_no_patologicos";
    public static final String PACIENTES_GINECOOBSTERICOS = "pacientes_ginecoobstericos";
    public static final String PACIENTES_ACTUALES = "pacientes_actuales";
    public static final String PACIENTES_GENERALES = "pacientes_generales";
    public static final String PACIENTES_RESPIRATORIO = "pacientes_respiratorio";
    public static final String PACIENTES_CARDIO = "pacientes_cardio";
    public static final String PACIENTES_DIGESTIVO = "pacientes_digestivo";
    public static final String PACIENTES_URINARIO = "pacientes_urinario";
    public static final String PACIENTES_REPRODUCTOR = "pacientes_reproductor";
    public static final String PACIENTES_HEMOLI = "pacientes_hemoli";
    public static final String PACIENTES_ENDOCRINO = "pacientes_endocrino";
    public static final String PACIENTES_NERVIOSO = "pacientes_nervioso";
    public static final String PACIENTES_ESQUELETICO = "pacientes_esqueletico";
    public static final String PACIENTES_PIEL = "pacientes_piel";
    public static final String PACIENTES_HABITUS = "pacientes_habitus";
    public static final String PACIENTES_CABEZA = "pacientes_cabeza";
    public static final String PACIENTES_CUELLO = "pacientes_cuello";
    public static final String PACIENTES_ABDOMEN = "pacientes_abdomen";
    public static final String PACIENTES_GINECOLOGICA = "pacientes_ginecologica";
    public static final String PACIENTES_EXTREMIDADES = "pacientes_extremidades";
    public static final String PACIENTES_COLUMNA = "pacientes_columna";
    public static final String PACIENTES_NEUROLOGICA = "pacientes_neurologica";
    public static final String PACIENTES_GENITALES = "pacientes_genitales";
    public static final String PACIENTES_NOTAS_DOC = "pacientes_notas_doc";
    public static final String PACIENTES_PLANES = "pacientes_planes";
    public static final String PACIENTES_IMPRESION_DIAGNOSTICA = "pacientes_impresion_diagnostica";
    public static final String PACIENTES_TRATAMIENTO = "pacientes_tratamiento";




    public static final String PACIENTES_VISITA_NOMBRE = "pacientes_visita_nombre";
    public static final String PACIENTES_VISITA_CURP = "pacientes_visita_curp";
    public static final String PACIENTES_VISITA_DIRECCION = "pacientes_visita_direccion";
    public static final String PACIENTES_VISITA_NUMERO = "pacientes_visita_numero";
    public static final String PACIENTES_VISITA_STATUS = "pacientes_visita_status";
    public static final String PACIENTES_VISITA_DIAGNOSTICO = "pacientes_visita_diagnostico";
    public static final String PACIENTES_VISITA_TRATAMIENTO = "pacientes_visita_tratamiento";
    public static final String PACIENTES_VISITA_FECHA= "pacientes_visita_fecha";
    public static final String PACIENTES_VISITA_EXPEDIENTE = "pacientes_visita_expediente";
    public static final String PACIENTES_VISITA_HEMOTIPO = "pacientes_visita_hemotipo";
    public static final String PACIENTES_VISITA_PESO = "pacientes_visita_peso";
    public static final String PACIENTES_VISITA_ESTATURA = "pacientes_visita_estatura";
    public static final String PACIENTES_VISITA_TENSION = "pacientes_visita_tension";
    public static final String PACIENTES_VISITA_CARDIACA = "pacientes_visita_cardiaca";
    public static final String PACIENTES_VISITA_RESPIRATORIA = "pacientes_visita_respiratoria";
    public static final String PACIENTES_VISITA_TALLA = "pacientes_visita_talla";
    public static final String PACIENTES_VISITA_PULSO = "pacientes_visita_pulso";
    public static final String PACIENTES_VISITA_GLUCEMIA = "pacientes_visita_glucemia";
    public static final String PACIENTES_VISITA_TEMPERATURA = "pacientes_visita_temperatura";
    public static final String PACIENTES_VISITA_NOTAS_ENFERMERIA = "pacientes_visita_enfermeria";
    public static final String PACIENTES_VISITA_GINECOOBSTERICOS = "pacientes_visita_ginecoobstericos";
    public static final String PACIENTES_VISITA_ACTUALES = "pacientes_visita_actuales";
    public static final String PACIENTES_VISITA_GENERALES = "pacientes_visita_generales";
    public static final String PACIENTES_VISITA_RESPIRATORIO = "pacientes_visita_respiratorio";
    public static final String PACIENTES_VISITA_CARDIO = "pacientes_visita_cardio";
    public static final String PACIENTES_VISITA_DIGESTIVO = "pacientes_visita_digestivo";
    public static final String PACIENTES_VISITA_URINARIO = "pacientes_visita_urinario";
    public static final String PACIENTES_VISITA_REPRODUCTOR = "pacientes_visita_reproductor";
    public static final String PACIENTES_VISITA_HEMOLI = "pacientes_visita_hemoli";
    public static final String PACIENTES_VISITA_ENDOCRINO = "pacientes_visita_endocrino";
    public static final String PACIENTES_VISITA_NERVIOSO = "pacientes_visita_nervioso";
    public static final String PACIENTES_VISITA_ESQUELETICO = "pacientes_visita_esqueletico";
    public static final String PACIENTES_VISITA_PIEL = "pacientes_visita_piel";
    public static final String PACIENTES_VISITA_HABITUS = "pacientes_visita_habitus";
    public static final String PACIENTES_VISITA_CABEZA = "pacientes_visita_cabeza";
    public static final String PACIENTES_VISITA_CUELLO = "pacientes_visita_cuello";
    public static final String PACIENTES_VISITA_ABDOMEN = "pacientes_visita_abdomen";
    public static final String PACIENTES_VISITA_GINECOLOGICA = "pacientes_visita_ginecologica";
    public static final String PACIENTES_VISITA_EXTREMIDADES = "pacientes_visita_extremidades";
    public static final String PACIENTES_VISITA_COLUMNA = "pacientes_visita_columna";
    public static final String PACIENTES_VISITA_NEUROLOGICA = "pacientes_visita_neurologica";
    public static final String PACIENTES_VISITA_GENITALES = "pacientes_visita_genitales";
    public static final String PACIENTES_VISITA_NOTAS_DOC = "pacientes_visita_notas_doc";
    public static final String PACIENTES_VISITA_PLANES = "pacientes_visita_planes";
    public static final String PACIENTES_VISITA_CHECK_HOJA_DIARIA = "pacientes_visita_check_hoja_diaria";
    public static final String PACIENTES_VISITA_MAKE_HOJA_DIARIA = "pacientes_visita_make_hoja_diaria";


    public static final String PACIENTES_VISITA_SEGUIMIENTO_NOMBRE = "pacientes_visita_seguimiento_nombre";
    public static final String PACIENTES_VISITA_SEGUIMIENTO_CURP = "pacientes_visita_seguimiento_curp";
    public static final String PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO = "pacientes_visita_seguimiento_diagnostico";
    public static final String PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO = "pacientes_visita_seguimiento_tratamiento";
    public static final String PACIENTES_VISITA_SEGUIMIENTO_FECHA= "pacientes_visita_seguimiento_fecha";
    public static final String PACIENTES_VISITA_SEGUIMIENTO_NUMERO = "pacientes_visita_seguimiento_expediente";
    public static final String PACIENTES_VISITA_SEGUIMIENTO_STATUS = "pacientes_visita_seguimiento_status";



}
