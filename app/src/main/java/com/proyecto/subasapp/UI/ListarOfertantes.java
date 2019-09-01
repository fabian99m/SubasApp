package com.proyecto.subasapp.UI;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyecto.subasapp.R;

import static com.proyecto.subasapp.UI.MainActivity.subas;


public class ListarOfertantes extends Fragment {

    RecyclerView rp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_listar_ofertantes, container, false);


        if (subas.ofer.size() > 0) {
            rp = v.findViewById(R.id.listaofer);
            rp.setLayoutManager(new LinearLayoutManager(this.getActivity()));

            AdaptadorOfer adapter = new AdaptadorOfer(subas.ofer);
            rp.setAdapter(adapter);
        }
        return v;
    }


}
