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

import com.proyecto.subasapp.Bdatos.Consulta;
import com.proyecto.subasapp.R;



public class FragOfertante extends Fragment implements View.OnClickListener{

    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public String mParam1;
    public String mParam2;


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

    public static FragOfertante newInstance(String param1, String param2) {
        FragOfertante fragment = new FragOfertante();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                Consulta.GuardarBD(getActivity(), nombre.getText().toString(),Long.parseLong(cedula.getText().toString()), Integer.parseInt(deposito.getText().toString().replaceAll(",","")));
                nombre.setText("");cedula.setText("");deposito.setText("");
                Toast.makeText(getActivity(), "Ofertante guardado con éxito!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error al guardar ofertante!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
