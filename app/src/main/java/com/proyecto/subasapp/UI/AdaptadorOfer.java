package com.proyecto.subasapp.UI;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proyecto.subasapp.Bdatos.Querys;
import com.proyecto.subasapp.Modelo.Ofertante;
import com.proyecto.subasapp.R;

import java.util.ArrayList;

public class AdaptadorOfer extends RecyclerView.Adapter<AdaptadorOfer.ViewHolderOfertantes> {

    ArrayList<Ofertante> ofertante;

    public AdaptadorOfer(ArrayList<Ofertante> ofertante) {
        this.ofertante = ofertante;
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
            itemView.setOnClickListener(this);
            nombre = itemView.findViewById(R.id.idNombre);
            cedula = itemView.findViewById(R.id.idCedula);
            deposito = itemView.findViewById(R.id.idDeposito);

        }

        @Override
        public void onClick(View view) {
            Opciones(view.getContext());

        }


        public void Opciones(final Context c){

            final AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("Opciones"+" : "+ofertante.get(getAdapterPosition()).getNombre())
                    .setItems(R.array.opc, (dialog, which) -> {
                        if(which==0){
                            try {
                                Querys.ElimnarOferBD(c,ofertante.get(getAdapterPosition()).getCedula());
                                ofertante.remove(getAdapterPosition());
                                notifyDataSetChanged();
                                Toast.makeText(c,"Eliminado con éxito!", Toast.LENGTH_SHORT).show();
                            }catch (Exception e) {
                                Toast.makeText(c,"Error al eliminar ofertante!", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                        else if(which==1){
                            Editar(c);
                        }
                    });
          builder.create();
          builder.show();

        }


        public void Editar(final Context c) {
            final Activity ac= (Activity)c;
            final AlertDialog.Builder builder = new AlertDialog.Builder(c);
            final LayoutInflater inflater = ac.getLayoutInflater();
            final View v = inflater.inflate(R.layout.editar_ofertante, null);
            builder.setView(v);
            final EditText nombre = v.findViewById(R.id.enombre);
            final EditText cedula = v.findViewById(R.id.ecedula);
            final EditText deposito = v.findViewById(R.id.edeposito);
            nombre.setText(ofertante.get(getAdapterPosition()).getNombre());
            cedula.setText(String.valueOf(ofertante.get(getAdapterPosition()).getCedula()));
            deposito.setText(String.valueOf(ofertante.get(getAdapterPosition()).getDeposito()));

            builder.setPositiveButton("Guardar", (dialog,id) -> {
                    int i=getAdapterPosition();
                    if (nombre.getText().toString().isEmpty() || nombre.getText().toString().trim().equals("")) {
                        Toast.makeText(ac, "Ingrese un nombre de ofertante!!", Toast.LENGTH_SHORT).show();
                    } else if (cedula.getText().toString().isEmpty()) {
                        Toast.makeText(ac, "Ingrese un número de cédula!!", Toast.LENGTH_SHORT).show();
                    } else if (deposito.getText().toString().isEmpty()) {
                        Toast.makeText(ac, "Ingrese deposito!!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            Querys.EditarOferBD(c,ofertante.get(i).getCedula(),nombre.getText().toString(),Integer.parseInt(cedula.getText().toString()), Float.parseFloat(deposito.getText().toString()));
                            ofertante.get(i).setNombre(nombre.getText().toString());
                            ofertante.get(i).setCedula(Integer.parseInt(cedula.getText().toString()));
                            ofertante.get(i).setDeposito(Float.parseFloat(deposito.getText().toString()));
                            notifyDataSetChanged();
                            Toast.makeText(c, "Modificado con éxito!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(c, "Error al modificar ofertante!", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
            }).setNegativeButton("Cerrar", (dialog,id) -> dialog.cancel());
            builder.create();
            builder.show();
        }

    }

}


