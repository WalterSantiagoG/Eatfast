package com.example.santiago.eatfast;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class CrearPlatos extends AppCompatActivity {

    private EditText txtNombrePlato;
    private EditText txtPrecio;
    private EditText txtIngredientes;
    private ArrayList<Integer> fotos;
    private Resources res;
    private Uri uri;
    ImageView foto;
    private StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_platos);

        foto = (ImageView)findViewById(R.id.fotoInicial);
        txtNombrePlato = (EditText)findViewById(R.id.txtNombrePlato);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);
        txtIngredientes = (EditText)findViewById(R.id.txtIngredientes);


        res =this.getResources();

        // inicializar_fotos();
        storageReference = FirebaseStorage.getInstance().getReference();

    }

    public boolean validar(){
        String aux = res.getString(R.string.mensaje_error_vacio);
        if (Metodos.validar_aux(txtNombrePlato,aux))return false;
        else if (Metodos.validar_aux(txtPrecio,aux))return false;
        else if (Metodos.validar_aux(txtIngredientes,aux))return false;

        return true;
    }

    public void agregar (View v){
        if (validar()){


            String id = Datos.getId();
            String foto = id+".jpg";
            Plato p = new Plato(id,"114065564",txtNombrePlato.getText().toString(),txtIngredientes.getText().toString(),Double.parseDouble(txtPrecio.getText().toString()),foto);

            p.guardar();
            subir_foto(foto);
            Snackbar.make(v,res.getString(R.string.mensaje_plato_guardado),Snackbar.LENGTH_LONG).setAction("Action",null).show();
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
        txtNombrePlato.setText("");
        txtPrecio.setText("");
        txtIngredientes.setText("");
        txtNombrePlato.requestFocus();
        foto.setImageDrawable(ResourcesCompat.getDrawable(res,android.R.drawable.ic_menu_gallery,null));
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(CrearPlatos.this,Principal.class);
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
