package com.proyecto.subasapp.UI;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


        public void Opciones(final Context a){
            final AlertDialog.Builder builder = new AlertDialog.Builder(a);
            builder.setTitle("Opciones "+" "+ofertante.get(getAdapterPosition()).getNombre())
                    .setItems(R.array.opc, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(which==0){
                                Querys.ElimnarOferBD(a,ofertante.get(getAdapterPosition()).getCedula());
                                ofertante.remove(getAdapterPosition());
                                notifyDataSetChanged();
                                Toast.makeText(a,"Eliminado con éxito!", Toast.LENGTH_SHORT).show();
                            }
                            else if(which==1){
                                Toast.makeText(a,"Editar" +ofertante.get(getAdapterPosition()).getNombre(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
          builder.create();
          builder.show();

        }


    }


}


