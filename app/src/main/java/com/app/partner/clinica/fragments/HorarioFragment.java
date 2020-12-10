package com.app.partner.clinica.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partner.clinica.R;
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
    List<EventDay> lsEventos = new ArrayList<>();

    public HorarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horario, container, false);
        obtenerViews(view);
        Calendar calendar = Calendar.getInstance();
        settearFecha(calendar);

        agregarEventos(calendar);
        return view;
    }

    private void agregarEventos(Calendar fecha) {
        for (int i = 1; i < 10; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), i);
            lsEventos.add(new EventDay(calendar, R.drawable.btn_redondeado));
        }
        cldHorario.setEvents(lsEventos);
    }

    private void obtenerViews(View view) {
        txtDia = view.findViewById(R.id.txtDia);
        txtMes = view.findViewById(R.id.txtMes);
        txtAnno = view.findViewById(R.id.txtAnno);
        cldHorario = (CalendarView) view.findViewById(R.id.cldHorario);

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
                Toast.makeText(getContext(), "Atras " + fecha.getTime().toString(), Toast.LENGTH_LONG).show();
                settearFecha(fecha);
                agregarEventos(fecha);
            }
        });

        cldHorario.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                Calendar fecha = cldHorario.getCurrentPageDate();
                Toast.makeText(getContext(), "Adelante " + fecha.getTime().toString(), Toast.LENGTH_LONG).show();
                settearFecha(fecha);
                agregarEventos(fecha);
            }
        });
    }

    private boolean existeEvento(Calendar calendar) {
        for (EventDay evento : lsEventos) {
            if (evento.getCalendar() == calendar) {
                return true;
            }
        }
        return false;
    }

    private void settearFecha(Calendar fecha) {
        txtMes.setText(retornarMes(fecha.get(Calendar.MONTH)));
        txtDia.setText(retornarDia(fecha.get(Calendar.DAY_OF_MONTH)));
        txtAnno.setText(String.valueOf(fecha.get(Calendar.YEAR)));
    }

    private String retornarDia(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return "" + i;
    }

    private String retornarMes(int i) {
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