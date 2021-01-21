package com.app.partner.clinica.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.partner.clinica.R;
import com.app.partner.clinica.activities.CitaActivity;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.common.SharedPreferencesManager;
import com.app.partner.clinica.models.request.AgendaClonada;
import com.app.partner.clinica.models.request.Empleado;
import com.app.partner.clinica.models.request.MesSpinner;
import com.app.partner.clinica.models.request.Terapiaindividual;
import com.app.partner.clinica.models.response.ResponseTerapiaEntrevista;
import com.app.partner.clinica.models.response.ResponseTerapiaindividual;
import com.app.partner.clinica.services.instance.ITerapiaIndividual;
import com.app.partner.clinica.services.service.TerapiaIndividualService;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClonarSemanaDialogFragment extends BottomSheetDialogFragment {

    private Empleado empleado;

    private Spinner spnnFechaIniClon, spnnAnnoClon, spnnMesClon, spnnFechaClon;
    private EditText edtFechaFinClon, edtFechaClon;
    private CheckBox ckLunes, ckMartes, ckMiercoles, ckJueves, ckViernes, ckSabado, ckDomingo;
    private Button btnClonarSem;

    private Calendar fechaCalendario;

    private Calendar fechaIniClon = Calendar.getInstance();
    private Calendar fechaFinClon = Calendar.getInstance();
    private Calendar fechaClon = Calendar.getInstance();
    ;
    private Calendar fhoy = Calendar.getInstance();
    private int anno, mes = 0;

    private List<String> lsFechaInicio = new ArrayList<>();
    private List<Integer> lsAnno = new ArrayList<>();
    private List<MesSpinner> lsMes = new ArrayList<>();
    private List<String> lsFechaClon = new ArrayList<>();

    ITerapiaIndividual iTerapiaIndividual;
    TerapiaIndividualService sTerapiaIndividual;

    public ClonarSemanaDialogFragment(Calendar fechaCalendario) {
        this.fechaCalendario = fechaCalendario;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        empleado = SharedPreferencesManager.getPrefEmpleado();
        retrofitInit();
        View view = inflater.inflate(R.layout.fragment_clonar_semana_dialog, container, false);
        obtenerViews(view);
        eventosViews();
        return view;
    }

    private void obtenerViews(View view) {
        spnnFechaIniClon = view.findViewById(R.id.spnnFechaIniClon);
        spnnAnnoClon = view.findViewById(R.id.spnnAnnoClon);
        spnnMesClon = view.findViewById(R.id.spnnMesClon);
        spnnFechaClon = view.findViewById(R.id.spnnFechaClon);
        edtFechaFinClon = view.findViewById(R.id.edtFechaFinClon);
        edtFechaClon = view.findViewById(R.id.edtFechaClon);
        ckLunes = view.findViewById(R.id.ckLunes);
        ckMartes = view.findViewById(R.id.ckMartes);
        ckMiercoles = view.findViewById(R.id.ckMiercoles);
        ckJueves = view.findViewById(R.id.ckJueves);
        ckViernes = view.findViewById(R.id.ckViernes);
        ckSabado = view.findViewById(R.id.ckSabado);
        ckDomingo = view.findViewById(R.id.ckDomingo);
        btnClonarSem = view.findViewById(R.id.btnClonarSem);
    }

    private void eventosViews() {
        semanaHaClonar();
        listarAnno();
        listarMes();

        btnClonarSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ckLunes.isChecked() || ckMartes.isChecked() || ckMiercoles.isChecked() || ckJueves.isChecked() || ckViernes.isChecked() || ckSabado.isChecked() || ckDomingo.isChecked()) {
                    new AlertDialog.Builder(getContext())
                            .setCancelable(false)
                            .setTitle(Constantes.CONFIRMAR)
                            .setMessage("¿Desea clonar la semana?")
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("Clonar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    clonarSemana();
                                }
                            })
                            .show();
                } else {
                    Constantes.alertWarning(Constantes.VALIDACION, "Debe escoger mínimo un día");
                }
            }
        });
    }

    private void clonarSemana() {
        AgendaClonada agendaClonada = new AgendaClonada();
        agendaClonada.setIiddoctor(empleado.getIidreferencia());
        agendaClonada.setTsemanainicio(fechaIniClon.getTimeInMillis());
        agendaClonada.setTsemanafin(fechaFinClon.getTimeInMillis());
        agendaClonada.setTsemanaclonada(fechaClon.getTimeInMillis());
        agendaClonada.setSdias(armarSdias());

        Call<ResponseTerapiaindividual> call = sTerapiaIndividual.clonarAgenda(agendaClonada);
        call.enqueue(new Callback<ResponseTerapiaindividual>() {
            @Override
            public void onResponse(Call<ResponseTerapiaindividual> call, Response<ResponseTerapiaindividual> response) {
                if (response.isSuccessful()) {
                    //TODO: mostrar alerta
                    List<Terapiaindividual> lsTerapiaIndiv = response.body().getAaData();
                    Constantes.alertSuccess(Constantes.NOTIFICACION, "Se clonó correctamente la agenda");
                    dismiss();
                    if (lsTerapiaIndiv.size()> 0){
                        mostrarMsjNoClonados(lsTerapiaIndiv);
                    }
                } else {
                    Constantes.alertWarning(Constantes.NOTIFICACION, "Error al clonar la agenda");
                }
            }

            @Override
            public void onFailure(Call<ResponseTerapiaindividual> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");

            }
        });
    }

    private void mostrarMsjNoClonados(List<Terapiaindividual> lsTerapiaIndiv) {
        String msj = "Uno o varios eventos no han podido ser clonados, debido a que el horario está ocupado:\n";
        SimpleDateFormat formatear = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (Terapiaindividual ti:lsTerapiaIndiv) {
            msj += "\n" + ti.getPacientes().getSnombre() + " - " + formatear.format(ti.getTfechaterapia());
        }
        new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setTitle(Constantes.INFORMACION)
                .setMessage(msj)
                .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private String armarSdias() {
        String sDias = "";
        if (ckLunes.isChecked()) sDias += "1";
        else sDias += "0";
        if (ckMartes.isChecked()) sDias += ",2";
        else sDias += ",0";
        if (ckMiercoles.isChecked()) sDias += ",3";
        else sDias += ",0";
        if (ckJueves.isChecked()) sDias += ",4";
        else sDias += ",0";
        if (ckViernes.isChecked()) sDias += ",5";
        else sDias += ",0";
        if (ckSabado.isChecked()) sDias += ",6";
        else sDias += ",0";
        if (ckDomingo.isChecked()) sDias += ",7";
        else sDias += ",0";

        return sDias;
    }

    private void listarAnno() {
        anno = fhoy.get(Calendar.YEAR);
        for (int i = 0; i < 8; i++) {
            lsAnno.add(fhoy.get(Calendar.YEAR) + i);
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, lsAnno);
        spnnAnnoClon.setAdapter(adapter);
        spnnAnnoClon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                anno = lsAnno.get(i);
                listarFechaClon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listarMes() {
        lsMes.add(new MesSpinner("ENERO", 0));
        lsMes.add(new MesSpinner("FEBRERO", 1));
        lsMes.add(new MesSpinner("MARZO", 2));
        lsMes.add(new MesSpinner("ABRIL", 3));
        lsMes.add(new MesSpinner("MAYO", 4));
        lsMes.add(new MesSpinner("JUNIO", 5));
        lsMes.add(new MesSpinner("JULIO", 6));
        lsMes.add(new MesSpinner("AGOSTO", 7));
        lsMes.add(new MesSpinner("SEPTIEMBRE", 8));
        lsMes.add(new MesSpinner("OCTUBRE", 9));
        lsMes.add(new MesSpinner("NOVIEMBRE", 10));
        lsMes.add(new MesSpinner("DICIEMBRE", 11));

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, lsMes);
        spnnMesClon.setAdapter(adapter);
        spnnMesClon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mes = lsMes.get(i).getNumero();
                listarFechaClon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listarFechaClon() {
        lsFechaClon.clear();
        Calendar fclonada = Calendar.getInstance();
        fclonada.set(Calendar.YEAR, anno);
        fclonada.set(Calendar.MONTH, mes);
        fclonada.set(Calendar.DAY_OF_MONTH, 1);

        int ultDia = retornarTotalDia(fclonada);
        SimpleDateFormat formatear = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < ultDia; i++) {
            int diaSem = fclonada.get(Calendar.DAY_OF_WEEK);
            if (diaSem == 2) {
                lsFechaClon.add(formatear.format(fclonada.getTime()));
            }
            fclonada.add(Calendar.DAY_OF_MONTH, 1);
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, lsFechaClon);
        spnnFechaClon.setAdapter(adapter);
        spnnFechaClon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] fecha = lsFechaClon.get(i).split("-");
                fechaClon.set(Integer.parseInt(fecha[0]), (Integer.parseInt(fecha[1]) - 1), Integer.parseInt(fecha[2]));
                Calendar fechaFinClon = Calendar.getInstance();
                fechaFinClon.set(Integer.parseInt(fecha[0]), (Integer.parseInt(fecha[1]) - 1), Integer.parseInt(fecha[2]));
                fechaFinClon.add(Calendar.DAY_OF_MONTH, 6);
                edtFechaClon.setText(formatear.format(fechaFinClon.getTime()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void semanaHaClonar() {
        Calendar fclonar = Calendar.getInstance();
        fclonar.setTime(fechaCalendario.getTime());
        fclonar.set(Calendar.DAY_OF_MONTH, 1);

        int ultDia = retornarTotalDia(fclonar);
        SimpleDateFormat formatear = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < ultDia; i++) {
            int diaSem = fclonar.get(Calendar.DAY_OF_WEEK);
            if (diaSem == 2) {
                lsFechaInicio.add(formatear.format(fclonar.getTime()));
            }
            fclonar.add(Calendar.DAY_OF_MONTH, 1);
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, lsFechaInicio);
        spnnFechaIniClon.setAdapter(adapter);
        spnnFechaIniClon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] fecha = lsFechaInicio.get(i).split("-");
                fechaIniClon.set(Integer.parseInt(fecha[0]), (Integer.parseInt(fecha[1]) - 1), Integer.parseInt(fecha[2]));
                fechaIniClon.set(Calendar.HOUR_OF_DAY, 0);
                fechaIniClon.set(Calendar.MINUTE, 0);
                fechaIniClon.set(Calendar.SECOND, 1);

                fechaFinClon.set(Integer.parseInt(fecha[0]), (Integer.parseInt(fecha[1]) - 1), Integer.parseInt(fecha[2]));
                fechaFinClon.add(Calendar.DAY_OF_MONTH, 6);
                fechaFinClon.set(Calendar.HOUR_OF_DAY, 23);
                fechaFinClon.set(Calendar.MINUTE, 59);
                fechaFinClon.set(Calendar.SECOND, 59);
                edtFechaFinClon.setText(formatear.format(fechaFinClon.getTime()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private int retornarTotalDia(Calendar calendar) {
        int mes = calendar.get(Calendar.MONTH);
        switch (mes) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return 31;
            case 3:
            case 5:
            case 8:
            case 10:
                return 30;
            case 1:
                if (esBisiesto(calendar.get(Calendar.YEAR))) {
                    return 29;
                } else {
                    return 28;
                }
        }
        return 0;
    }

    public static boolean esBisiesto(int anno) {
        GregorianCalendar calendar = new GregorianCalendar();
        boolean esBisiesto = false;
        if (calendar.isLeapYear(1900 + anno)) {
            esBisiesto = true;
        }
        return esBisiesto;
    }

    private void retrofitInit() {
        iTerapiaIndividual = ITerapiaIndividual.getInstance();
        sTerapiaIndividual = iTerapiaIndividual.getService();
    }
}