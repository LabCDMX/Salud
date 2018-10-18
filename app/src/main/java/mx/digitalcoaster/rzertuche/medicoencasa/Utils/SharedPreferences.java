package mx.digitalcoaster.rzertuche.medicoencasa.Utils;

import android.content.Context;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;

public class SharedPreferences {

    private android.content.SharedPreferences sharedPreferences = null;

    private static SharedPreferences theInstance = null;

    private SharedPreferences()
    {
        sharedPreferences =   MainActivity.appContext.getSharedPreferences("MedicoPreferences", Context.MODE_PRIVATE);
    }

    private synchronized static void createInstance() {
        if (theInstance == null) {
            theInstance = new SharedPreferences();
        }
    }

    public static SharedPreferences getInstance() {
        if (theInstance == null) createInstance();
        return theInstance;
    }


    public void setStringData(String value, String data){
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(value, data);
        editor.commit();
    }

    public void setBooleanData(String value, Boolean data){
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(value, data);
        editor.commit();
    }

    public boolean getBooleanData(String value){
        boolean resp = false;
        try {
            resp = sharedPreferences.getBoolean(value, false);
        } catch (Exception e) {
        }

        return resp;
    }

    public void setLongData(String value, long data){
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(value, data);
        editor.commit();
    }

    public long getLongData(String value){
        long resp = 0;

        try{
            resp = sharedPreferences.getLong(value,0);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return resp;
    }

    public String getStringData(String value){
        String resp = "";
        try {
            resp = sharedPreferences.getString(value, "");
        } catch (Exception e) {
        }

        return resp;
    }


    /*public void setCompany(String value, Company company) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Company companyFavorite = new Company();  //Instancia Gson.
        companyFavorite.
        editor.putString(value,favoriteCompany);
        editor.commit();
    }

    public Company getCompany(String value){
        Company companyFavorite = new Company();
        try {
            Gson gson = new Gson(); //Instancia Gson.
            companyFavorite = gson.fromJson(value, Company.class);

        } catch (Exception e) {
        }

        return companyFavorite;
    }*/

    public void setIntData(String value, int data) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(value, data);
        editor.commit();
    }

    public int getIntData(String value){
        int resp = 0;

        try {
            resp = sharedPreferences.getInt(value, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    public void setStringSet(String key, Set<String> value){
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }

    public Set<String> getStringSet(String key){
        Set<String> result = null;
        try {
            result = sharedPreferences.getStringSet(key, new HashSet<String>());
        } catch (Exception e) {
        }

        return result;
    }

    public boolean contains(String value){
        boolean contain = false;
        try {
            contain = sharedPreferences.contains(value);
        } catch (Exception e) {
        }
        return contain;
    }

    public void deleteData(String value) {
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(value);
        editor.commit();
    }

    public void clearPreferences(){
        Log.d("sp_", "CLEAR!!!");
        android.content.SharedPreferences settings =  MainActivity.appContext.getSharedPreferences("MedicoPreferences", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }

}
