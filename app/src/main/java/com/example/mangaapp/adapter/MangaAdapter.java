package com.example.mangaapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.ViewHolder> implements Filterable {
    //khai bao bien
    private List<Manga> mangaList;
    private List<Manga> mangaListOld;

    public MangaAdapter(List<Manga> mangaList) {
        this.mangaList = mangaList;
        this.mangaListOld = mangaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truyen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Manga mg = mangaList.get(position);


            holder.imgTruyen.setImageResource(mg.getImgtruyen());
            holder.txtChap.setText(mg.getTenchap());
            holder.txtTenTruyen.setText(mg.getTentruyen());

    }

    @Override
    public int getItemCount() {
        if(mangaList != null)
        {
            return mangaList.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    mangaList = mangaListOld;
                }else {
                    List<Manga> list = new ArrayList<>();
                    for(Manga manga : mangaListOld){
                        if(manga.getTentruyen().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(manga);
                        }
                    }

                    mangaList = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mangaList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mangaList = (List<Manga>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTruyen;
        TextView txtChap, txtTenTruyen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTruyen = itemView.findViewById(R.id.imgTruyen);
            txtChap = itemView.findViewById(R.id.txtChap);
            txtTenTruyen = itemView.findViewById(R.id.txtTenTruyen);
        }
    }
}
