package com.app.partner.clinica.common;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

import com.app.partner.clinica.models.request.Empleado;
import com.app.partner.clinica.models.request.Pagina;

public class Constantes {

    public static final String URL_BACK = "http://64.227.109.195:7201/";
//    public static final String URL_BACK = "http://localhost:8083/";
    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN";
    public static final String KEY_USER = "KEY_USER";
    public static final String KEY_PASSWORD = "KEY_PASSWORD";
    public static final String KEY_RECORDAR = "KEY_RECORDAR";
    public static final String KEY_NOMBRE = "KEY_NOMBRE";
    public static final String KEY_APELLIDOS = "KEY_APELLIDOS";
    public static final String KEY_ID_PERFIL = "KEY_ID_PERFIL";
    public static final String KEY_ID_USUARIO = "KEY_ID_USUARIO";

    //Objetos
    public static final String KEY_EMPLEADO = "KEY_EMPLEADO";
    public static final String KEY_PAGINA = "KEY_PAGINA";
    public static final String KEY_RESPONSE_TOKEN = "KEY_RESPONSE_TOKEN";

    //URL_PAGINAS
    public static final String URL_CITAS = "/citas";
    public static final String URL_ASISTENCIA = "/asistencia";

    public static void expandir(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void colapsar(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void rotar180(View view) {
        RotateAnimation animation = new RotateAnimation(0, 180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(250);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    public static void rotar360(View view) {
        RotateAnimation animation = new RotateAnimation(180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(250);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    public static void limpiarSharedPreferenes() {
//        SharedPreferencesManager.setPreferences(KEY_USER, null);
//        SharedPreferencesManager.setPreferences(KEY_PASSWORD, null);
//        SharedPreferencesManager.setPreferences(KEY_TOKEN, null);
//        SharedPreferencesManager.setPreferences(KEY_REFRESH_TOKEN, null);
//        SharedPreferencesManager.setPreferences(KEY_RECORDAR, false);
//        SharedPreferencesManager.setPreferences((Empleado) null);
//        SharedPreferencesManager.setPreferences((Pagina) null);
    }

    public static String retornarMes(int i) {
        switch (i) {
            case 0:
                return "ENE";
            case 1:
                return "FEB";
            case 2:
                return "MAR";
            case 3:
                return "ABR";
            case 4:
                return "MAY";
            case 5:
                return "JUN";
            case 6:
                return "JUL";
            case 7:
                return "AGO";
            case 8:
                return "SEP";
            case 9:
                return "OCT";
            case 10:
                return "NOV";
            default:
                return "DIC";
        }
    }
}
