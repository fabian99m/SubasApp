package com.proyecto.subasapp.UI.Fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.subasapp.Bdatos.Consulta;
import com.proyecto.subasapp.R;



public class FragOfertante extends Fragment implements View.OnClickListener {


    private EditText nombre, cedula, deposito;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag_ofertante, container, false);

        nombre = view.findViewById(R.id.etNombre);
        cedula = view.findViewById(R.id.etCedula);
        deposito = view.findViewById(R.id.etDeposito);

        Button button = view.findViewById(R.id.bt1);
        button.setOnClickListener(this::onClick);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt1) {
            GuardarOfertante();
        }
    }

    public void GuardarOfertante() {

        if (nombre.getText().toString().isEmpty() || nombre.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Ingrese un nombre de ofertante!!", Toast.LENGTH_SHORT).show();
            nombre.requestFocus();
        } else if (cedula.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Ingrese un número de cédula!!", Toast.LENGTH_SHORT).show();
            cedula.requestFocus();
        } else if (deposito.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Ingrese deposito!!", Toast.LENGTH_SHORT).show();
            deposito.requestFocus();
        } else {
            try {
                Consulta.GuardarBD(getActivity(), nombre.getText().toString(),Integer.parseInt(cedula.getText().toString()), Float.parseFloat(deposito.getText().toString()));
                nombre.setText("");cedula.setText("");deposito.setText("");
                Toast.makeText(getActivity(), "Ofertante guardado con éxito!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error al guardar ofertante!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
