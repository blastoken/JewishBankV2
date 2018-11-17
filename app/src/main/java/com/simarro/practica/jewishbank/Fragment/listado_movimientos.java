package com.simarro.practica.jewishbank.Fragment;


import android.accounts.Account;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.simarro.practica.aplicacionbancoanna2018.bd.MiBancoOperacional;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cliente;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Cuenta;
import com.simarro.practica.aplicacionbancoanna2018.pojo.Movimiento;
import com.simarro.practica.jewishbank.Activity.TransferenciasActivity;
import com.simarro.practica.jewishbank.Adapters.AccountAdapter;
import com.simarro.practica.jewishbank.Adapters.TransferAdapter;
import com.simarro.practica.jewishbank.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class listado_movimientos extends Fragment {


    ArrayList<Movimiento> listamovimientos=null;
    TransferAdapter adaptador=null;
    MiBancoOperacional mbo=null;
    ListView lista=null;

    public listado_movimientos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listado_movimientos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         lista=getView().findViewById(R.id.listamovimientos);
        mbo=MiBancoOperacional.getInstance(this.getActivity());

        if(this.getActivity().getClass().equals(TransferenciasActivity.class)) {
            cargarDatosCuenta();
        }


    }


    public void cargarDatosCuenta(){
        Cuenta aux=new Cuenta();
        aux.setId(Integer.parseInt(getActivity().getIntent().getStringExtra("id")));
        this.listamovimientos=this.mbo.getMovimientos(aux);
        inicializarMovil();
    }
    public void inicializarMovil(){

        this.adaptador = new TransferAdapter(this.getActivity(), R.layout.elemento_lista, this.listamovimientos);
        lista.setAdapter(this.adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), adaptador.getItem(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void cargarAPartirDeCuenta(Cuenta c){
        this.listamovimientos=this.mbo.getMovimientos(c);
        this.inicializarMovil();
    }
}