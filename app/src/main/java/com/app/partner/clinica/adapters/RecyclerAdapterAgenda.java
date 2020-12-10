package com.app.partner.clinica.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.partner.clinica.R;
import com.app.partner.clinica.common.Constantes;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapterAgenda extends RecyclerView.Adapter<RecyclerAdapterAgenda.ViewHolder> {

    private List<String> lsAgenda;
    private OnItemClickListener onItemClickListener;

    public RecyclerAdapterAgenda(List<String> lsAgenda, OnItemClickListener onItemClickListener) {
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

        TextView txtPaciente, txtCitaDescripcion;
        ImageView imgRotar;

        boolean expand = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPaciente = itemView.findViewById(R.id.txtPaciente);
            txtCitaDescripcion = itemView.findViewById(R.id.txtCitaDescripcion);
            imgRotar = itemView.findViewById(R.id.imgRotar);
        }

        public void bind(String agenda, OnItemClickListener onItemClickListener) throws IOException {

            txtPaciente.setText(agenda);

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
        void onItemClick(String agenda, int position);
    }
}
