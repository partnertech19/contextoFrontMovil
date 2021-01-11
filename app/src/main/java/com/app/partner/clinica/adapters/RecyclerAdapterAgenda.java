package com.app.partner.clinica.adapters;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.partner.clinica.R;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.models.request.Entrevistas;
import com.app.partner.clinica.models.request.Pacientes;
import com.app.partner.clinica.models.request.TerapiaEntrevista;
import com.app.partner.clinica.models.request.Terapiaindividual;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapterAgenda extends RecyclerView.Adapter<RecyclerAdapterAgenda.ViewHolder> {

    private List<TerapiaEntrevista> lsAgenda;
    private OnItemClickListener onItemClickListener;

    public RecyclerAdapterAgenda(List<TerapiaEntrevista> lsAgenda, OnItemClickListener onItemClickListener) {
        this.lsAgenda = lsAgenda;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_agenda_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.bind(lsAgenda.get(position), onItemClickListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return lsAgenda.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtPaciente, txtCitaDescripcion, txtCitaEstado, txtCitaHora;
        ImageView imgRotar;
        ConstraintLayout ctlAgendaColor;

        boolean expand = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPaciente = itemView.findViewById(R.id.txtPaciente);
            txtCitaDescripcion = itemView.findViewById(R.id.txtCitaDescripcion);
            imgRotar = itemView.findViewById(R.id.imgRotar);
            txtCitaEstado = itemView.findViewById(R.id.txtCitaEstado);
            txtCitaHora = itemView.findViewById(R.id.txtCitaHora);
            ctlAgendaColor = itemView.findViewById(R.id.ctlAgendaColor);
        }

        public void bind(TerapiaEntrevista agenda, OnItemClickListener onItemClickListener) throws IOException {

            Calendar calendar = Calendar.getInstance();
            if (agenda.getTerapiaindividual() != null) {
                Terapiaindividual ti = agenda.getTerapiaindividual();
                Pacientes pc = ti.getPacientes();
                txtPaciente.setText(pc.getSnombre());
                txtCitaEstado.setText(ti.getIestado() == 0 ? "Eliminado" : ti.getIestado() == 1 ? "Programado" : "Completado");
                calendar.setTimeInMillis(ti.getTfechaterapia());
                txtCitaHora.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                ctlAgendaColor.setBackgroundColor(Color.rgb(52,180,85));
            } else if (agenda.getEntrevistas() != null) {
                Entrevistas en = agenda.getEntrevistas();
                txtPaciente.setText(en.getSnombresolicitante());
                txtCitaEstado.setText(en.getIestado() == 0 ? "Eliminado" : en.getIestado() == 1 ? "Programado" : "Completado");
                calendar.setTimeInMillis(en.getTfechaentrevista());
                txtCitaHora.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
                ctlAgendaColor.setBackgroundColor(Color.rgb(83, 187, 251));
            }

            imgRotar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expand = !expand;
                    if (expand) {
                        Constantes.rotar180(imgRotar);
                        Constantes.expandir(txtCitaDescripcion);
                    } else {
                        Constantes.rotar360(imgRotar);
                        Constantes.colapsar(txtCitaDescripcion);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(agenda, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TerapiaEntrevista agenda, int position);
    }
}
