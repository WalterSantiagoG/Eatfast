package com.example.santiago.eatfast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class CrearRestaurante extends AppCompatActivity {

    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtContrasena;
    private ArrayList<Integer>fotos;
    private Resources res;
    private Uri uri;
    ImageView foto;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_restaurante);

        foto = (ImageView)findViewById(R.id.fotoInicial);
        txtCedula = (EditText)findViewById(R.id.txtCedula);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);


        res =this.getResources();

        inicializar_fotos();
        storageReference = FirebaseStorage.getInstance().getReference();

    }

    public void inicializar_fotos(){
        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
    }

    public boolean validar(){
        String aux = res.getString(R.string.mensaje_error_vacio);
        if (Metodos.validar_aux(txtCedula,aux))return false;
        else if (Metodos.validar_aux(txtNombre,aux))return false;
        else if (Metodos.validar_aux(txtApellido,aux))return false;
        else if (Metodos.validar_aux(txtContrasena,aux))return false;
        return true;
    }

    public void agregar (View v){
        if (validar()){

            String id = Datos.getId();
            String foto = id+".jpg";
            Restaurante p = new Restaurante(id,foto,
                    txtCedula.getText().toString(),
                    txtNombre.getText().toString(),
                    txtApellido.getText().toString(),
                    txtContrasena.getText().toString());

            p.guardar();
            subir_foto(foto);
            Snackbar.make(v,res.getString(R.string.mensaje_persona_guardada),Snackbar.LENGTH_LONG).setAction("Action",null).show();
            limpiar();
        }
    }

    public void seleccionar_foto (View v){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, getString(R.string.mensaje_seleccion_imagen)),1);
    }

    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtContrasena.setText("");
        txtCedula.requestFocus();
        foto.setImageDrawable(ResourcesCompat.getDrawable(res,android.R.drawable.ic_menu_gallery,null));
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(CrearRestaurante.this,Principal.class);
        startActivity(i);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==1){
            uri = data.getData();
            if (uri != null) {
                foto.setImageURI(uri);
            }
        }
    }

    public void subir_foto(String foto){
        StorageReference childRef = storageReference.child(foto);
        UploadTask uploadTask = childRef.putFile(uri);
    }

}