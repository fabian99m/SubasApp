package com.proyecto.subasapp.Vista.Listas;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.snackbar.Snackbar;
import com.proyecto.subasapp.Bdatos.Consulta;
import com.proyecto.subasapp.R;
import com.proyecto.subasapp.Vista.Adaptadores.AdaptadorCel;


public class ListarCelulares extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_listar_celulares, container, false);

        if(Consulta.CargarCelularBD(getActivity()).size()>0) {
            recyclerView = view.findViewById(R.id.listarcel);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            AdaptadorCel adapter = new AdaptadorCel(Consulta.CargarCelularBD(getActivity()));
            recyclerView.setAdapter(adapter);
        } else{
            Snackbar.make(view, "Ingrese al menos un celular!", Snackbar.LENGTH_LONG)
                    .setActionTextColor(getResources().getColor(R.color.snackbar_action))
                    .setAction("Aceptar", view1 -> {}).show();
        }
        return  view;
    }

}
