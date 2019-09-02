package com.proyecto.subasapp.UI;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyecto.subasapp.Bdatos.Querys;
import com.proyecto.subasapp.R;


public class ListarOfertantes extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listar_ofertantes, container, false);
        recyclerView = view.findViewById(R.id.listaofer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        AdaptadorOfer adapter = new AdaptadorOfer(Querys.CargarOferBD(this.getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }


}
