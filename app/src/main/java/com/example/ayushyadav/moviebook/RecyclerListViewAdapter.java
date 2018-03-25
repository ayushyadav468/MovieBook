package com.example.ayushyadav.moviebook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ayushyadav on 20/03/18.
 */

public class RecyclerListViewAdapter extends RecyclerView.Adapter<RecyclerListViewAdapter.UserViewHolder>{

    Context context;
    ArrayList<imdbData> dataArraylist;

    public RecyclerListViewAdapter(Context context, ArrayList<imdbData> dataArraylist) {
        this.context = context;
        this.dataArraylist = dataArraylist;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.intheaters_layout, parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        imdbData data = dataArraylist.get(position);
        holder.title.setText(data.getTitle());
        holder.releasedDate.setText(data.getReleasedDate());
        holder.language.setText(data.getLanguage());
        Picasso.get().load(data.getPoster()).into(holder.poster);
        Picasso.get().load(data.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataArraylist.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView releasedDate;
        TextView language;
        ImageView image;
        ImageView poster;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.titleTextView);
            releasedDate = itemView.findViewById(R.id.yearReleasedTextView);
            language = itemView.findViewById(R.id.languageTextView);
            image = itemView.findViewById(R.id.movieImage);
            poster = itemView.findViewById(R.id.moviePoster);
        }
    }

}
