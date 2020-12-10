package com.app.partner.clinica.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.partner.clinica.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapterPaciente extends RecyclerView.Adapter<RecyclerAdapterPaciente.ViewHolder> {

    private List<String> lsPaciente;
    private List<String> lsPacienteOriginal;
    private OnItemClickListener onItemClickListener;

    public RecyclerAdapterPaciente(List<String> lsPaciente, OnItemClickListener onItemClickListener) {
        this.lsPaciente = lsPaciente;
        this.onItemClickListener = onItemClickListener;
        this.lsPacienteOriginal = new ArrayList<>();
        lsPacienteOriginal.addAll(lsPaciente);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_paciente_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.bind(lsPaciente.get(position), onItemClickListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return lsPaciente.size();
    }

    public void filter(final String strSerch) {
        lsPaciente.clear();
        if (strSerch.length() == 0) {
            lsPaciente.addAll(lsPacienteOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<String> collect = lsPacienteOriginal.stream()
                        .filter(i -> i.toLowerCase().contains(strSerch.toLowerCase()))
                        .collect(Collectors.toList());
                lsPaciente.addAll(collect);
            } else {
                for (String p : lsPacienteOriginal) {
                    if (p.toLowerCase().contains(strSerch.toLowerCase())){
                        lsPaciente.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtPacienteNombre;
        ImageView imgCheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPacienteNombre = itemView.findViewById(R.id.txtPacienteNombre);
            imgCheck = itemView.findViewById(R.id.imgCheck);
        }

        public void bind(String paciente, OnItemClickListener onItemClickListener) throws IOException {

            txtPacienteNombre.setText(paciente);

            imgCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onCheckClick(paciente, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onCheckClick(String paciente, int position);
    }
}
