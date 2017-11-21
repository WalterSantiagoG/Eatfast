package com.example.santiago.eatfast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.location.places.PlaceReport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listarPlatos extends AppCompatActivity implements AdaptadorPlato.OnPlatoClickListener{

    private RecyclerView listado;
    private ArrayList<Plato> platos;
    private Resources res;
    private AdaptadorPlato adapter;
    private LinearLayoutManager llm = new LinearLayoutManager(this);
    private DatabaseReference databaseReference;
    private final String BD= "Platos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listado = (RecyclerView)findViewById(R.id.lstPlatos);
        res = this.getResources();
        platos = new ArrayList<>();


        adapter = new AdaptadorPlato(this.getApplicationContext(),platos,this);
        llm = new LinearLayoutManager(this);
        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(BD).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                platos.clear();
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Plato p = snapshot.getValue(Plato.class);
                        platos.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setPlatos(platos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    public void agregar(View v){
       // Intent i = new Intent(Principal.this,CrearRestaurante.class);
      //  startActivity(i);
    }



    @Override
    public void onPlatoClick(Plato p) {

    }
}
