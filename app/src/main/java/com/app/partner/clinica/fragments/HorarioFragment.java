package com.app.partner.clinica.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.activities.LoginActivity;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.models.request.TerapiaEntrevista;
import com.app.partner.clinica.models.request.Terapiaindividual;
import com.app.partner.clinica.models.response.ResponseTerapiaIndividual;
import com.app.partner.clinica.services.instance.ITerapiaIndividual;
import com.app.partner.clinica.services.service.TerapiaIndividualService;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HorarioFragment extends Fragment {

    CalendarView cldHorario;
    TextView txtDia, txtMes, txtAnno;
    ImageView imgCerrarSesion;
    List<EventDay> lsEventos = new ArrayList<>();

    ITerapiaIndividual iTerapiaIndividual;
    TerapiaIndividualService sTerapiaIndividual;

    public HorarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        retrofitInit();
        View view = inflater.inflate(R.layout.fragment_horario, container, false);
        obtenerViews(view);
        eventosViews();
        Calendar calendar = Calendar.getInstance();
        settearFecha(calendar);

        agregarEventos(calendar);
        return view;
    }

    private void eventosViews() {

        imgCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constantes.limpiarSharedPreferenes();
                Intent login = new Intent(getContext(), LoginActivity.class);
                startActivity(login);
            }
        });

        cldHorario.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                HorarioDialogFragment dialogFragment = new HorarioDialogFragment(eventDay.getCalendar(), existeEvento(eventDay.getCalendar()));
                dialogFragment.show(getActivity().getSupportFragmentManager(), "horarioDialog");
                settearFecha(eventDay.getCalendar());
            }
        });

        cldHorario.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                Calendar fecha = cldHorario.getCurrentPageDate();
                agregarEventos(fecha);
                settearFecha(fecha);
            }
        });

        cldHorario.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                Calendar fecha = cldHorario.getCurrentPageDate();
                agregarEventos(fecha);
                settearFecha(fecha);
            }
        });
    }

    private void agregarEventos(Calendar fecha) {
        Terapiaindividual terapiaIndividual = new Terapiaindividual();
        terapiaIndividual.setIiddoctor(9);
        terapiaIndividual.setTfechaterapia(fecha.getTime().getTime());

        Call<ResponseTerapiaIndividual> call = sTerapiaIndividual.retornarFechas(terapiaIndividual);
        call.enqueue(new Callback<ResponseTerapiaIndividual>() {
            @Override
            public void onResponse(Call<ResponseTerapiaIndividual> call, Response<ResponseTerapiaIndividual> response) {
                if (response.isSuccessful()) {
                    ResponseTerapiaIndividual resp = response.body();
                    if (resp.getEstado() == 1) {
                        List<TerapiaEntrevista> lsTerapiaEntrevista = resp.getAaData();
                        for (TerapiaEntrevista te : lsTerapiaEntrevista) {
                            Calendar calendar = Calendar.getInstance();
                            if (te.getTfechaterapia() != null && te.getTfechaentrevista() != null) {
                                calendar.setTimeInMillis(te.getTfechaterapia());
                                calendar.add(Calendar.HOUR_OF_DAY, 23);
                                if (!existeEvento(calendar))
                                lsEventos.add(new EventDay(calendar, R.drawable.circulobicolor));
                            } else if (te.getTfechaterapia() != null) {
                                calendar.setTimeInMillis(te.getTfechaterapia());
                                calendar.add(Calendar.HOUR_OF_DAY, 23);
                                if (!existeEvento(calendar))
                                lsEventos.add(new EventDay(calendar, R.drawable.circuloverde));
                            } else {
                                calendar.setTimeInMillis(te.getTfechaentrevista());
                                calendar.add(Calendar.HOUR_OF_DAY, 23);
                                if (!existeEvento(calendar))
                                lsEventos.add(new EventDay(calendar, R.drawable.circuloazul));
                            }
                        }
                        cldHorario.setEvents(lsEventos);
                    } else {
                        Toast.makeText(getContext(), "NO HAY EVENTOS ESTE MES", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getContext(), "ERROR INTERNO", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTerapiaIndividual> call, Throwable t) {
                Toast.makeText(getContext(), "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void obtenerViews(View view) {
        txtDia = view.findViewById(R.id.txtDia);
        txtMes = view.findViewById(R.id.txtMes);
        txtAnno = view.findViewById(R.id.txtAnno);
        imgCerrarSesion = view.findViewById(R.id.imgCerrarSesion);
        cldHorario = (CalendarView) view.findViewById(R.id.cldHorario);
    }

    private boolean existeEvento(Calendar calendar) {
        for (EventDay evento : lsEventos) {
            if (evento.getCalendar().equals(calendar)) {
                return true;
            }
        }
        return false;
    }

    private void settearFecha(Calendar fecha) {
        txtMes.setText(Constantes.retornarMes(fecha.get(Calendar.MONTH)));
        txtDia.setText(retornarDia(fecha.get(Calendar.DAY_OF_MONTH)));
        txtAnno.setText(String.valueOf(fecha.get(Calendar.YEAR)));
    }

    private String retornarDia(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return "" + i;
    }

    private void retrofitInit() {
        iTerapiaIndividual = ITerapiaIndividual.getInstance();
        sTerapiaIndividual = iTerapiaIndividual.getService();
    }

}