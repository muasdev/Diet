package com.muasdiet.muasmakkode.diet.db.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muasdiet.muasmakkode.diet.R;
import com.muasdiet.muasmakkode.diet.db.helper.DatabaseHandlerOlahraga;
import com.muasdiet.muasmakkode.diet.db.model.ModelOlahraga;

import java.util.ArrayList;

/**
 * Created by muas on 16/03/2018.
 */

public class DataOlahragaAdapter extends RecyclerView.Adapter<DataOlahragaAdapter.OlahragaViewHolder> {

    private ArrayList<ModelOlahraga> modelOlahragaArrayList;
    Context context;
    DatabaseHandlerOlahraga db;

    public DataOlahragaAdapter(ArrayList<ModelOlahraga> modelOlahragaArrayList, Context context) {
        this.modelOlahragaArrayList = modelOlahragaArrayList;
        this.context = context;
        if (context != null) {
            db = new DatabaseHandlerOlahraga(context);
        }
    }

    @Override
    public OlahragaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_olahraga, parent, false);
        OlahragaViewHolder viewHolder = new OlahragaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataOlahragaAdapter.OlahragaViewHolder holder, int position) {

        holder.namaOlahraga.setText(modelOlahragaArrayList.get(position).getNama_olahraga_model());
        holder.kaloriOlahraga.setText(modelOlahragaArrayList.get(position).getKalori_olahraga_model() + " kkal");
    }

    @Override
    public int getItemCount() {
        return (modelOlahragaArrayList != null) ? modelOlahragaArrayList.size() : 0;
    }

    public class OlahragaViewHolder extends RecyclerView.ViewHolder {
        TextView namaOlahraga, kaloriOlahraga;
        ImageView imgDeleteOlahraga;

        public OlahragaViewHolder(View itemView) {
            super(itemView);
            namaOlahraga = itemView.findViewById(R.id.txtNmOlahraga);
            kaloriOlahraga = itemView.findViewById(R.id.txtKaloriOlahraga);
            imgDeleteOlahraga = itemView.findViewById(R.id.imgDeleteOlahraga);
            imgDeleteOlahraga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (db.deleteFromDbOlahraga(modelOlahragaArrayList.get(getAdapterPosition()).get_id()) > 0) {
                        modelOlahragaArrayList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
