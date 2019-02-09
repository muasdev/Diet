package com.muasdiet.muasmakkode.diet.db;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muasdiet.muasmakkode.diet.R;
import com.muasdiet.muasmakkode.diet.db.model.ModelMakanan;

import java.util.ArrayList;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MakananViewHolder> {


    private ArrayList<ModelMakanan> modelMakananList;
    Context context;
    private RecyclerView mRecyclerV;
    private int position;
    DatabaseHandler db;

    public DataAdapter(ArrayList<ModelMakanan> modelMakananList, Context context) {
        this.modelMakananList = modelMakananList;
        this.context = context;

        if (context != null) {

            db = new DatabaseHandler(context);
        }
    }

    @Override
    public MakananViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_sarapan, parent, false);
        MakananViewHolder viewHolder = new MakananViewHolder(view);
        return viewHolder;

    }

    public class MakananViewHolder extends RecyclerView.ViewHolder {
        TextView namaMknAdapter, urtMknAdapter, kaloriMknAdapter, karboMknAdapter, proteinMknAdapter, lemakMknAdapter;
        ImageView imgHapus;


        public MakananViewHolder(View itemView) {
            super(itemView);
            namaMknAdapter = itemView.findViewById(R.id.txtNmMakanan);
            urtMknAdapter = itemView.findViewById(R.id.txtUrt);
            kaloriMknAdapter = itemView.findViewById(R.id.txtKalori);
            /*
            karboMknAdapter = (TextView) itemView.findViewById(R.id.txtKarbo);
            proteinMknAdapter = (TextView) itemView.findViewById(R.id.txtProtein);
            lemakMknAdapter = (TextView) itemView.findViewById(R.id.txtLemak);*/
            imgHapus = itemView.findViewById(R.id.imageView_hapus);

            imgHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Toast.makeText(context, " ok", Toast.LENGTH_SHORT).show();*/
                    /*db = new DatabaseHandler(context);*/
                    if (db.deleteFromDatabse(modelMakananList.get(getAdapterPosition()).get_id())>0) {
                        modelMakananList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        /*public void setListeners() {
            imgHapus.setOnClickListener(MakananViewHolder.this);
        }*/



        /*@Override
        public void onClick(View view) {
            DatabaseHandler dbHelper = new DatabaseHandler(context);
            dbHelper.deleteContact(modelMakananList.get(position).get_id(), context);

            modelMakananList.remove(position);
            mRecyclerV.removeViewAt(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, modelMakananList.size());
            notifyDataSetChanged();
        }*/


    }

    /*public void remove(int position) {
        modelMakananList.remove(position);
        notifyItemRemoved(position);
    }*/


    /*public void removeItem(int position){
        int arr = modelMakananList.get(position).get_id();
        db = new DatabaseHandler(context);
        boolean trm = db.deleteContact(arr);
        modelMakananList.remove(position);
        notifyItemRemoved(position);

        *//*modelMakananList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, modelMakananList.size());
        notifyDataSetChanged();*//*
    }*/


    @Override
    public void onBindViewHolder(MakananViewHolder holder, int position) {

        holder.namaMknAdapter.setText(modelMakananList.get(position).getNama_model());
        holder.urtMknAdapter.setText(modelMakananList.get(position).getUrt_model());
        holder.kaloriMknAdapter.setText(modelMakananList.get(position).getKalori_model());
        /*
        holder.karboMknAdapter.setText(modelMakananList.get(position).getKarbo_model());
        holder.proteinMknAdapter.setText(modelMakananList.get(position).getProtein_model());
        holder.lemakMknAdapter.setText(modelMakananList.get(position).getLemak_model());*/



        /*holder.setListeners();*/
    }


    @Override
    public int getItemCount() {
        return (modelMakananList != null) ? modelMakananList.size() : 0;
    }


}

