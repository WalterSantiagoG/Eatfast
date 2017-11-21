package com.example.santiago.eatfast;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class AdaptadorPlato extends RecyclerView.Adapter<AdaptadorPlato.platoViewHolder> {


    private ArrayList<Plato> platos;
    private Resources res;
    private Context contexto;
    private OnPlatoClickListener clickListener;



    public AdaptadorPlato(Context contexto, ArrayList<Plato> platos, OnPlatoClickListener clickListener){
        this.platos=platos;
        this.res=contexto.getResources();
        this.contexto=contexto;
        this.clickListener=clickListener;
    }

    @Override
    public platoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plato,parent,false);
        return new platoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final platoViewHolder holder, int position) {
        final Plato p = platos.get(position);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(p.getFoto()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(contexto).load(uri).into(holder.foto);
            }
        });

        holder.nombrePlato.setText(p.getNombre());
        holder.precioPlato.setText(""+p.getPrecio());
//        holder.apellido.setText(p.getContrasena());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onPlatoClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public static class platoViewHolder extends RecyclerView.ViewHolder {

        private ImageView foto;
        private TextView nombrePlato;
        private TextView precioPlato;


        public platoViewHolder(View item){
            super(item);
            foto = (ImageView)item.findViewById(R.id.imgFoto);
            nombrePlato = (TextView)item.findViewById(R.id.lblNombrePlato);
            precioPlato = (TextView)item.findViewById(R.id.lblPrecioPlato);

        }
    }

    public interface OnPlatoClickListener{
        void onPlatoClick(Plato p);
    }

}
