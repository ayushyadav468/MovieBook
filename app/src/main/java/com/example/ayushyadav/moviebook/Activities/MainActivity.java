package com.example.ayushyadav.moviebook.Activities;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ayushyadav.moviebook.ApiClient;
import com.example.ayushyadav.moviebook.GenreArrayList;
import com.example.ayushyadav.moviebook.Genres;
import com.example.ayushyadav.moviebook.MoviesList;
import com.example.ayushyadav.moviebook.R;
import com.example.ayushyadav.moviebook.RecyclerListViewAdapter;
import com.example.ayushyadav.moviebook.imdbData;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    public static HashMap<Integer,String> mapGenre = new HashMap<>();

    ProgressBar progressBar;

    RecyclerView nowShowingRecyclerView;
    RecyclerListViewAdapter nowShowingRecyclerAdapter;
    ArrayList<imdbData.nowShowingData> nowShowingDataArrayList = new ArrayList<>();

    ArrayList<Genres> mainActivityGenresArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nowShowingRecyclerView = findViewById(R.id.nowShowingRecyclerView);
        nowShowingRecyclerAdapter = new RecyclerListViewAdapter(this, nowShowingDataArrayList);

        nowShowingRecyclerView.setAdapter(nowShowingRecyclerAdapter);
        nowShowingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        nowShowingRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        nowShowingRecyclerView.setItemAnimator(new DefaultItemAnimator());

        getGenres();

        fetchData();
    }

    private void getGenres() {

        Call<GenreArrayList> call = ApiClient.getInstance().getApiCallInterface().allGenreCall();
        call.enqueue(new Callback<GenreArrayList>() {
            @Override
            public void onResponse(Call<GenreArrayList> call, Response<GenreArrayList> response) {
                GenreArrayList genreArrayList = response.body();

                if(genreArrayList != null){

                    mainActivityGenresArrayList.clear();
                    mainActivityGenresArrayList.addAll(genreArrayList.allGenre);

                    mapGenre.clear();

                    for(Genres genre: genreArrayList.allGenre) {
                        mapGenre.put(genre.getId(),genre.getGenreName());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreArrayList> call, Throwable t) {

            }
        });

    }

    private void fetchData() {
        nowShowingRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Call<MoviesList> call = ApiClient.getInstance().getApiCallInterface().getData();

        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                Log.d("onRespose", "Function is working");

                MoviesList moviesList = response.body();

                if(moviesList != null){
                    nowShowingDataArrayList.clear();
                    nowShowingDataArrayList.addAll(moviesList.results);
                    nowShowingRecyclerAdapter.notifyDataSetChanged();
                }

                nowShowingRecyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.d("Throwable", t.getMessage());
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                nowShowingRecyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()){
            case R.id.menuSearch:
                Toast.makeText(MainActivity.this,"Search Icon Clicked", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
