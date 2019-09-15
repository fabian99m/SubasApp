package com.proyecto.subasapp.Vista.Adaptadores;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.subasapp.Bdatos.Consulta;
import com.proyecto.subasapp.Modelo.Celular;
import com.proyecto.subasapp.R;

import java.util.ArrayList;

public class AdaptadorCel extends RecyclerView.Adapter<AdaptadorCel.ViewHolderCelulares> {

    private ArrayList<Celular> celulares;

    public AdaptadorCel(ArrayList<Celular> cel) {
        this.celulares = cel;
    }

    @NonNull
    @Override
    public ViewHolderCelulares onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listar_celulares,null , false);
        return  new ViewHolderCelulares(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCelulares holder, int position) {
    holder.id.setText(String.valueOf(celulares.get(position).getId()));
    holder.marca.setText(celulares.get(position).getMarca());
    holder.modelo.setText(celulares.get(position).getModelo());
    holder.gama.setText(celulares.get(position).getGama());
    holder.costo.setText(String.valueOf(celulares.get(position).getCosto()));
    }

    @Override
    public int getItemCount() {
        return celulares.size();
    }

    public class ViewHolderCelulares extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id,gama,modelo,costo,marca;

        public ViewHolderCelulares(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv11);
            marca = itemView.findViewById(R.id.etMarca);
            modelo = itemView.findViewById(R.id.etModelo);
            gama = itemView.findViewById(R.id.gama);
            costo = itemView.findViewById(R.id.etCosto);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            Opciones(view.getContext());
        }

        private void Opciones(Context context) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Opciones" + " : " + celulares.get(getAdapterPosition()).getMarca()+" "+ celulares.get(getAdapterPosition()).getModelo())
                    .setItems(R.array.opc2, (dialog, which) -> {
                        if (which == 0) {
                            try {
                                Consulta.ElimnarBD(context, celulares.get(getAdapterPosition()).getId(),Consulta.TABLA_CELULAR,Consulta.CAMPO_id);
                                celulares.remove(getAdapterPosition());
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
                final View view = inflater.inflate(R.layout.editar_celular, null);
                builder.setView(view);

                final EditText id =  view.findViewById(R.id.eid);
                final EditText marca = view.findViewById(R.id.emarca);
                final EditText modelo = view.findViewById(R.id.emodelo);
                final Spinner  gama = view.findViewById(R.id.spgama);
                final EditText costo = view.findViewById(R.id.ecosto);


                id.setText(String.valueOf(celulares.get(getAdapterPosition()).getId()));
                marca.setText(celulares.get(getAdapterPosition()).getMarca());
                modelo.setText(celulares.get(getAdapterPosition()).getModelo());
                costo.setText(String.valueOf(celulares.get(getAdapterPosition()).getCosto()));
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.gamas, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                gama.setAdapter(adapter);

                if(celulares.get(getAdapterPosition()).getGama().equals("Baja")) { gama.setSelection(1);
                } else if (celulares.get(getAdapterPosition()).getGama().equals("Media")) { gama.setSelection(2); }
                else { gama.setSelection(3); }

                builder.setPositiveButton("Guardar", (dialog, ID) -> {
                    int i = getAdapterPosition();
                    if (id.getText().toString().isEmpty() ) {
                        Toast.makeText(activity, "Ingrese una ID!!", Toast.LENGTH_LONG).show();
                        Editar(context);
                    } else if (marca.getText().toString().isEmpty() || marca.getText().toString().trim().equals("")) {
                        Toast.makeText(activity, "Ingrese una marca!!", Toast.LENGTH_LONG).show();
                        Editar(context);
                    } else if (modelo.getText().toString().isEmpty() || modelo.getText().toString().trim().equals("")) {
                        Toast.makeText(activity, "Ingrese un modelo!!", Toast.LENGTH_LONG).show();
                        Editar(context);
                    }
                    else if (gama.getSelectedItemPosition()==0) {
                        Toast.makeText(activity, "Seleccione una gama!!", Toast.LENGTH_LONG).show();
                        Editar(context);
                    }
                    else if (costo.getText().toString().isEmpty()) {
                        Toast.makeText(activity, "Ingrese un costo!!", Toast.LENGTH_LONG).show();
                        Editar(context);
                    }
                    else {
                        try {
                            Consulta.ModificarBD(activity,celulares.get(i).getId(),Integer.parseInt(id.getText().toString()),marca.getText().toString(),
                                    modelo.getText().toString(), gama.getSelectedItem().toString() , Float.parseFloat(costo.getText().toString()));
                            celulares.get(i).setId(Integer.parseInt(id.getText().toString()));
                            celulares.get(i).setMarca(marca.getText().toString());
                            celulares.get(i).setModelo(modelo.getText().toString());
                            celulares.get(i).setGama(gama.getSelectedItem().toString());
                            celulares.get(i).setCosto(Float.parseFloat(costo.getText().toString()));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Modificado con éxito!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Error al modificar celular!", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("Cerrar", (dialog, ID) -> dialog.cancel());
                builder.create();
                builder.show();
            }


    }
}
