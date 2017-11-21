package com.example.santiago.eatfast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by android on 17/10/2017.
 */

public class Datos {

    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static String bd = "Restaurantes";

    private static ArrayList<Restaurante> restaurantes = new ArrayList<>();

    public static void guardarPersona(Restaurante p){
        databaseReference.child(bd).child(p.getId()).setValue(p);
    }

    public static ArrayList<Restaurante> obtenerPersonas(){
        return restaurantes;
    }

    public static void setPersonas(ArrayList<Restaurante>per){
        restaurantes=per;
    }

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void actualizar(Restaurante p){
        databaseReference.child(bd).child(p.getId()).setValue(p);
    }

    public static void eliminar(Restaurante p){
        databaseReference.child(bd).child(p.getId()).removeValue();
    }
}
