package com.proyecto.subasapp.Vista.Fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.google.android.material.snackbar.Snackbar;
import com.proyecto.subasapp.Bdatos.Consulta;
import com.proyecto.subasapp.R;



public class FragOfertante extends Fragment implements View.OnClickListener{


    private EditText nombre, cedula, deposito;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag_ofertante, container, false);

        nombre = view.findViewById(R.id.etMarca);
        cedula = view.findViewById(R.id.etCedula);
        deposito = view.findViewById(R.id.etDeposito);

        Button button = view.findViewById(R.id.bt2);
        button.setOnClickListener(this::onClick);

        deposito.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {EntradaText(this,editable);}
         });

        return view;
    }


    public void EntradaText(TextWatcher tw , Editable editable){
        deposito.removeTextChangedListener(tw);
        try {
            String originalString = editable.toString();
            Long longval;
            if (originalString.contains(",")) {
                originalString = originalString.replaceAll(",", "");
            }
            longval = Long.parseLong(originalString);
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###,###,###");
            String formattedString = formatter.format(longval);
            deposito.setText(formattedString);
            deposito.setSelection(deposito.getText().length());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        deposito.addTextChangedListener(tw);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt2) {
            GuardarOfertante();
        }
    }

    public void GuardarOfertante() {

        if (nombre.getText().toString().isEmpty() || nombre.getText().toString().trim().equals("")) {
            nombre.setError("Ingrese un nombre válido!");
            nombre.requestFocus();
            if(cedula.getText().toString().isEmpty()) { cedula.setError("Ingrese una cédula válida!");}
            if(deposito.getText().toString().isEmpty()){ deposito.setError("Ingrese un deposito válido!");}
        } else if (cedula.getText().toString().isEmpty()) {
            cedula.setError("Ingrese una cédula válida!");
            cedula.requestFocus();
            if(nombre.getText().toString().isEmpty()) { nombre.setError("Ingrese una cédula válida!");}
            if(deposito.getText().toString().isEmpty()){ deposito.setError("Ingrese un deposito válido!");}
        } else if (deposito.getText().toString().isEmpty()) {
            deposito.setError("Ingrese un deposito válido!");
            deposito.requestFocus();
            if(cedula.getText().toString().isEmpty()) { cedula.setError("Ingrese una cédula válida!");}
            if(nombre.getText().toString().isEmpty()){ nombre.setError("Ingrese un deposito válido!");}
        } else {
            try {
                Consulta.GuardarBD(getActivity(), nombre.getText().toString(),Long.parseLong(cedula.getText().toString()), Integer.parseInt(deposito.getText().toString().replaceAll(",","")));
                nombre.setText("");cedula.setText("");deposito.setText("");
                nombre.clearFocus();cedula.clearFocus();deposito.clearFocus();
                Snackbar.make(this.getView(), "Ofertante guardado con éxito!", Snackbar.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error al guardar ofertante!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
