package com.example.santiago.eatfast;


import java.util.ArrayList;

/**
 * Created by android on 17/10/2017.
 */

public class Restaurante {
    private String id;
    private String foto;
    private String nit;
    private String nombre;
    private String correo;
    private int sexo;
    private String contrasena;
   // private ArrayList <Str>

    public Restaurante(){

    }

    public Restaurante(String foto, String nit, String nombre, String correo, int sexo) {
        this.foto = foto;
        this.nit = nit;
        this.nombre = nombre;
        this.correo = correo;
        this.sexo = sexo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Restaurante(String foto, String nit, String nombre, String correo) {
        this.foto = foto;
        this.nit = nit;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Restaurante(String id, String foto, String nit, String nombre, String correo,String contrasena) {
        this.id = id;
        this.foto = foto;
        this.nit = nit;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena=contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Restaurante (String id){
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String cedula) {
        this.nit = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public void guardar(){
        Datos.guardarPersona(this);
    }

    public void modificar(){Datos.actualizar(this);}

    public void eliminar(){Datos.eliminar(this);}
}
