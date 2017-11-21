package com.example.santiago.eatfast;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements AdaptadorRestaurante.OnPersonaClickListener{

    private RecyclerView listado;
    private ArrayList<Restaurante> personas;
    private Resources res;
    private AdaptadorRestaurante adapter;
    private LinearLayoutManager llm;
    private DatabaseReference databaseReference;
    private final String BD= "Restaurantes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listado = (RecyclerView)findViewById(R.id.lstPersonas);
        res = this.getResources();
        personas = new ArrayList<>();

        adapter = new AdaptadorRestaurante(this.getApplicationContext(),personas,this);
        llm = new LinearLayoutManager(this);
        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(BD).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                personas.clear();
                if (dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Restaurante p = snapshot.getValue(Restaurante.class);
                        personas.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setPersonas(personas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    public void agregar(View v){
        Intent i = new Intent(Principal.this,CrearRestaurante.class);
        startActivity(i);
    }

    @Override
    public void onPersonaClick(Restaurante p) {
       /* Intent i = new Intent(Principal.this,ModificarPersona.class);
        Bundle b = new Bundle();
        b.putString("id",p.getId());
        b.putString("cedula",p.getCedula());
        b.putString("nombre",p.getNombre());
        b.putString("apellido",p.getApellido());
        b.putString("foto",p.getFoto());

        i.putExtra("datos",b);
        startActivity(i);*/
    }
}