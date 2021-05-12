package com.tara.tarangmishra1105;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MovieViewHolder> {
    Context context;
    List<Datum> datumList;
    GridLayoutManager gridLayoutManager;
    public Adapter(Context context,List<Datum> datumList, GridLayoutManager gridLayoutManager){
     this.context = context;
     this.datumList = datumList;
     this.gridLayoutManager = gridLayoutManager;
    }
    @NonNull
    @Override
    public Adapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MovieViewHolder holder, int position) {
        Datum data = datumList.get(position);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int module = position %6;
                if(module == 0)
                {
                    return 2;
                }
                else{
                    return  1;
                }
            }
        });
        if(data != null){
          //  Picasso.get().load(data.getImage_url()).into(holder.imagview);
            Glide.with(context).load(data.getImage_url()).into(holder.imagview);
        }

    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagview;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imagview = (ImageView)itemView.findViewById(R.id.imagview);
        }
    }
}
