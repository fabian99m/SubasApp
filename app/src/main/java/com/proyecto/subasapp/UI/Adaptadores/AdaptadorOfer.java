package com.proyecto.subasapp.UI.Adaptadores;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.subasapp.Bdatos.Consulta;
import com.proyecto.subasapp.Modelo.Ofertante;
import com.proyecto.subasapp.R;

import java.util.ArrayList;

public class AdaptadorOfer extends RecyclerView.Adapter<AdaptadorOfer.ViewHolderOfertantes> {

     private ArrayList<Ofertante> ofertante;


    public AdaptadorOfer(ArrayList<Ofertante> ofer) {
     this.ofertante=ofer;
    }

    @NonNull
    @Override
    public ViewHolderOfertantes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listar_ofertantes, null, false);

        return new ViewHolderOfertantes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOfertantes holder, int position) {
        holder.nombre.setText(ofertante.get(position).getNombre());
        holder.cedula.setText(String.valueOf(ofertante.get(position).getCedula()));
        holder.deposito.setText((String.valueOf(ofertante.get(position).getDeposito())));

    }

    @Override
    public int getItemCount() {
        return ofertante.size();
    }

    public class ViewHolderOfertantes extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre, cedula, deposito;

        public ViewHolderOfertantes(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.idNombre);
            cedula = itemView.findViewById(R.id.idCedula);
            deposito = itemView.findViewById(R.id.idDeposito);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            Opciones(view.getContext());

        }

        public void Opciones(final Context context) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Opciones" + " : " + ofertante.get(getAdapterPosition()).getNombre())
                    .setItems(R.array.opc, (dialog, which) -> {
                        if (which == 0) {
                            try {
                                Consulta.ElimnarBD(context, ofertante.get(getAdapterPosition()).getCedula(),Consulta.TABLA_OFERTANTE,Consulta.CAMPO_cedula);
                                ofertante.remove(getAdapterPosition());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Eliminado con éxito!", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(context, "Error al eliminar ofertante!", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        } else if (which == 1) {
                            Editar(context);
                          }
                    });
            builder.create();
            builder.show();
        }


        public void Editar(final Context context) {
            final Activity activity = (Activity) context;
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            final LayoutInflater inflater = activity.getLayoutInflater();
            final View view = inflater.inflate(R.layout.editar_ofertante, null);
            builder.setView(view);

            final EditText nombre =  view.findViewById(R.id.eid);
            final EditText cedula = view.findViewById(R.id.emarca);
            final EditText deposito = view.findViewById(R.id.emodelo);

            nombre.setText(ofertante.get(getAdapterPosition()).getNombre());
            cedula.setText(String.valueOf(ofertante.get(getAdapterPosition()).getCedula()));
            deposito.setText(String.valueOf(ofertante.get(getAdapterPosition()).getDeposito()));

            builder.setPositiveButton("Guardar", (dialog, id) -> {
                int i = getAdapterPosition();
                if (nombre.getText().toString().isEmpty() || nombre.getText().toString().trim().equals("")) {
                    Toast.makeText(activity, "Ingrese un nombre de ofertante!!", Toast.LENGTH_LONG).show();
                    Editar(context);
                } else if (cedula.getText().toString().isEmpty()) {
                    Toast.makeText(activity, "Ingrese un número de cédula!!", Toast.LENGTH_LONG).show();
                    Editar(context);
                } else if (deposito.getText().toString().isEmpty()) {
                    Toast.makeText(activity, "Ingrese deposito!!", Toast.LENGTH_LONG).show();
                    Editar(context);
                } else {
                    try {
                        Consulta.ModificarBD(context, ofertante.get(i).getCedula(), nombre.getText().toString(), Integer.parseInt(cedula.getText().toString()), Float.parseFloat(deposito.getText().toString()));
                        ofertante.get(i).setNombre(nombre.getText().toString());
                        ofertante.get(i).setCedula(Integer.parseInt(cedula.getText().toString()));
                        ofertante.get(i).setDeposito(Float.parseFloat(deposito.getText().toString()));
                        notifyDataSetChanged();
                        Toast.makeText(context, "Modificado con éxito!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(context, "Error al modificar ofertante!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }).setNegativeButton("Cerrar", (dialog, id) -> dialog.cancel());
            builder.create();
            builder.show();
        }

    }

}


