package com.app.partner.clinica.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.partner.clinica.models.request.Empleado;
import com.app.partner.clinica.models.response.ResponseToken;
import com.google.gson.Gson;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS_FILE = "APP_SETTINGS";

    public SharedPreferencesManager() {
    }

    private static SharedPreferences getSharedPreferences() {
        return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    public static void setPreferences(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value).commit();
    }

    public static void setPreferences(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value).commit();
    }


    public static void setPreferences(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value).commit();
    }

    public static String getPrefString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    public static int getPrefInt(String key) {
        return getSharedPreferences().getInt(key, 0);
    }

    public static boolean getPrefBoolean(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    public static void setPreferences(ResponseToken responseToken) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        String json = new Gson().toJson(responseToken);
        edit.putString(Constantes.KEY_RESPONSE_TOKEN, json).commit();
    }

    public static ResponseToken getPrefResponseToken() {
        String json = getSharedPreferences().getString(Constantes.KEY_RESPONSE_TOKEN, null);
        return new Gson().fromJson(json, ResponseToken.class);
    }

    public static void setPreferences(Empleado empleado) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        String json = new Gson().toJson(empleado);
        edit.putString(Constantes.KEY_EMPLEADO, json).commit();
    }

    public static Empleado getPrefEmpleado() {
        String json = getSharedPreferences().getString(Constantes.KEY_EMPLEADO, null);
        return new Gson().fromJson(json, Empleado.class);
    }
}
