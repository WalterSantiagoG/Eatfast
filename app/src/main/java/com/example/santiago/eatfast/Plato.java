package com.example.santiago.eatfast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by programador on 21/11/2017.
 */

public class Plato {
    private String id;
    private String nombre;
    private ArrayList<String> ingredientes;
    private double precio;
    private String idRestaurante;

    public Plato(String idPlato,String idRestaurante,String nombre, ArrayList<String> ingredientes, double precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.idRestaurante=idRestaurante;
        this.id=idPlato;
    }

    public Plato(String idPlato,String idRestaurante,String nombre, String listaIngredientes, double precio) {
        this.nombre = nombre;
        this.añadirListaDeIngredientes(listaIngredientes);
        this.precio = precio;
        this.idRestaurante=idRestaurante;
        this.id=idPlato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(String idRestaurante) {
        this.idRestaurante = idRestaurante;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void añadirListaDeIngredientes(String ingredientes){
        String ingredientesPlato [] = ingredientes.split(",");
        for (int i = 0; i <ingredientesPlato.length ; i++) {
            this.añadirIngrediente(ingredientesPlato[i]);
        }
    }
    public void añadirIngrediente(String ingrediente){
        this.ingredientes.add(ingrediente);
    }
    public boolean eliminarIngrediente(String ingrediente){
        boolean r= false;
        for (int i = 0; i < this.ingredientes.size(); i++) {
            if(this.ingredientes.get(i).equalsIgnoreCase(ingrediente)){
                this.ingredientes.remove(i);
                r=true;
            }
        }
        return r;
    }
    public String getListaDeIngredientes(){
        String r ="";
        for (int i = 0; i < this.ingredientes.size(); i++) {
            if(i<this.ingredientes.size()-1) {
                r = r + this.ingredientes.get(i) + ",";
            }else{
                r = r + this.ingredientes.get(i);
            }
        }

        return r;
    }

    public void guardar(){
        Datos.guardarPlato(this);
    }

    public void modificar(){Datos.actualizarPlato(this);}

    public void eliminar(){Datos.eliminarPlato(this);}
}
