package com.example.ayushyadav.moviebook.RecylerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayushyadav.moviebook.Constants.Key;
import com.example.ayushyadav.moviebook.R;
import com.example.ayushyadav.moviebook.ImdbData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.ayushyadav.moviebook.Activities.MainActivity.mapGenre;

/**
 * Created by ayushyadav on 20/03/18.
 */

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.UserViewHolder>{

    Context context;
    ArrayList<ImdbData> DataArrayList;

    public MoviesRecyclerViewAdapter(Context context, ArrayList<ImdbData> dataArrayList) {
        this.context = context;
        this.DataArrayList = dataArrayList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.movies_layout, parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        ImdbData data = DataArrayList.get(position);
        holder.title.setText(data.getTitle());
        holder.stars.setText(data.getStars());

        //Convert Genre id to Text
        String genreText = "";
        for(int gen : data.getGenre()){
            genreText = genreText + " " + mapGenre.get(gen);
        }
        holder.genre.setText(genreText);

        Picasso.get().load(Key.posterSizeURL + data.getPoster()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return DataArrayList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView stars;
        TextView genre;
        ImageView poster;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.titleTextView);
            stars = itemView.findViewById(R.id.starsTextView);
            genre = itemView.findViewById(R.id.genreTextView);
            poster = itemView.findViewById(R.id.moviePoster);
        }
    }

}
