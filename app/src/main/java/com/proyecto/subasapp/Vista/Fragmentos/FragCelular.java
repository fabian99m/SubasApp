package com.proyecto.subasapp.Vista.Fragmentos;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.proyecto.subasapp.Bdatos.Consulta;
import com.proyecto.subasapp.R;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class FragCelular extends Fragment implements View.OnClickListener{


    private AutoCompleteTextView gama;
    private Button bt;
    private EditText id, marca, modelo, costo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag_celulares, container, false);

        gama = view.findViewById(R.id.sp1);
        bt = view.findViewById(R.id.bt2);
        id = view.findViewById(R.id.etID);
        marca = view.findViewById(R.id.etMarca);
        modelo = view.findViewById(R.id.etModelo);
        costo = view.findViewById(R.id.etCosto);


        String[] opc = new String[] {"Baja", "Media", "Alta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner, opc);
        gama.setAdapter(adapter);

        bt.setOnClickListener(this::onClick);
        costo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {EntradaText(this,editable);}
        });

        return view;
    }

    private void EntradaText(TextWatcher tw, Editable editable) {
        costo.removeTextChangedListener(tw);
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
            costo.setText(formattedString);
            costo.setSelection(costo.getText().length());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        costo.addTextChangedListener(tw);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt2) {
            GuardarCelular();
        }
    }

    private void GuardarCelular() {

        if (id.getText().toString().isEmpty()) {
            id.setError("Ingrese una id válida");
            id.requestFocus();
            if(modelo.getText().toString().isEmpty()) {modelo.setError("Ingrese un modelo válid0!");}
            if(marca.getText().toString().isEmpty()){ marca.setError("Ingrese una marca válida!");}
            if(costo.getText().toString().isEmpty()) { costo.setError("Ingrese un costo válid0!");}
            if(gama.getText().toString().isEmpty()){ gama.requestFocus();}
        } else if (marca.getText().toString().isEmpty() || marca.getText().toString().trim().equals("")) {
            Toast.makeText(this.getActivity(), "Ingrese una marca!!", Toast.LENGTH_SHORT).show();
            marca.requestFocus();
        } else if (modelo.getText().toString().isEmpty() || modelo.getText().toString().trim().equals("")) {
            Toast.makeText(this.getActivity(), "Ingrese modelo!!", Toast.LENGTH_SHORT).show();
            modelo.requestFocus();
        } else if (gama.getText().toString().isEmpty()) {
            Toast.makeText(this.getActivity(), "Seleccione una gama!!", Toast.LENGTH_SHORT).show();
            gama.requestFocus();
            gama.showDropDown();
        } else if (costo.getText().toString().isEmpty()) {
            Toast.makeText(this.getActivity(), "Ingrese el costo base!!", Toast.LENGTH_SHORT).show();
            costo.requestFocus();
        } else {
            try {
                int id2 = Integer.parseInt(id.getText().toString());
                String marca2 = marca.getText().toString();
                String modelo2 = modelo.getText().toString();
                String gama2 =  gama.getText().toString();
                float costo2 = Float.parseFloat(costo.getText().toString().replaceAll(",",""));
                Consulta.GuardarBD(getActivity(), id2, marca2, modelo2, gama2, costo2);
                Snackbar.make(this.getView(), "Celular guardado con éxito!", Snackbar.LENGTH_LONG).show();
                id.setText("");marca.setText("");modelo.setText("");
                costo.setText("");
                id.clearFocus();marca.clearFocus();modelo.clearFocus();
                costo.clearFocus();gama.clearFocus();
            } catch (Exception e) {
                Toast.makeText(getActivity(),"Error al guardar celular!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }


}
