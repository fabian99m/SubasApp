package com.proyecto.subasapp.UI;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.proyecto.subasapp.Bdatos.Querys;
import com.proyecto.subasapp.R;


public class FragCelular extends Fragment implements View.OnClickListener {


    private Spinner sp;
    private Button bt;
    private EditText id, marca, modelo, costo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frag_celulares, container, false);

        sp = v.findViewById(R.id.sp1);
        bt = v.findViewById(R.id.bt2);
        id = v.findViewById(R.id.etID);
        marca = v.findViewById(R.id.etMarca);
        modelo = v.findViewById(R.id.etModelo);
        costo = v.findViewById(R.id.edCosto);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.gamas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        bt.setOnClickListener(this::onClick);

        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt2) {
            GuardarCelular();
        }
    }

    private void GuardarCelular() {
        if (id.getText().toString().isEmpty()) {
            Toast.makeText(this.getActivity(), "Ingrese un ID de celular!!", Toast.LENGTH_SHORT).show();
        } else if (marca.getText().toString().isEmpty() || marca.getText().toString().trim().equals("")) {
            Toast.makeText(this.getActivity(), "Ingrese una marca!!", Toast.LENGTH_SHORT).show();
        } else if (modelo.getText().toString().isEmpty() || modelo.getText().toString().trim().equals("")) {
            Toast.makeText(this.getActivity(), "Ingrese modelo!!", Toast.LENGTH_SHORT).show();
        } else if (sp.getSelectedItemPosition() == 0) {
            Toast.makeText(this.getActivity(), "Seleccione una gama!!", Toast.LENGTH_SHORT).show();
        } else if (costo.getText().toString().isEmpty()) {
            Toast.makeText(this.getActivity(), "Ingrese el costo base!!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                int id2 = Integer.parseInt(id.getText().toString());
                String marca2 = marca.getText().toString();
                String modelo2 = modelo.getText().toString();
                String gama2 = sp.getSelectedItem().toString();
                float costo2 = Float.parseFloat(costo.getText().toString());
                Querys.GuardarBD(getActivity(), id2, marca2, modelo2, gama2, costo2);
                Toast.makeText(getActivity(), "Celular guardado con éxito!", Toast.LENGTH_SHORT).show();

                id.setText("");marca.setText("");modelo.setText("");sp.setSelection(0);costo.setText("");
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error al guardar celular!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }
        }
    }
}
