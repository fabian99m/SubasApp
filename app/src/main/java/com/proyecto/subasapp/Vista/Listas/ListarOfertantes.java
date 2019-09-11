package com.proyecto.subasapp.Vista.Listas;



import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.snackbar.Snackbar;
import com.proyecto.subasapp.Bdatos.Consulta;
import com.proyecto.subasapp.R;
import com.proyecto.subasapp.Vista.Adaptadores.AdaptadorOfer;
import com.proyecto.subasapp.Vista.Fragmentos.FragOfertante;
import java.util.Objects;


public class ListarOfertantes extends Fragment {


    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listar_ofertantes, container, false);

        if (Consulta.CargarOfertanteBD(this.getActivity()).size() > 0) {
            recyclerView = view.findViewById(R.id.listaofer);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            AdaptadorOfer adapter = new AdaptadorOfer(Consulta.CargarOfertanteBD(this.getActivity()));
            recyclerView.setAdapter(adapter);
        } else {
            Snackbar.make(view, "Ingrese al menos un ofertante!", Snackbar.LENGTH_LONG)
                    .setActionTextColor(getResources().getColor(R.color.snackbar_action))
                    .setAction("Ir!", view1 -> {
                        FragOfertante fragOfer = new FragOfertante();
                        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.test1, fragOfer).commit();
                        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
                        toolbar.setTitle("Ofertante");

                    }).show();

        }
        return view;
    }

}
