package com.example.ayushyadav.moviebook.Activities;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
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
import com.example.ayushyadav.moviebook.RecylerAdapter.MoviesRecyclerViewAdapter;
import com.example.ayushyadav.moviebook.ImdbData;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ProgressBar progressBar;
    ArrayList<Genres> mainActivityGenresArrayList = new ArrayList<>();
    public static HashMap<Integer,String> mapGenre = new HashMap<>();

    RecyclerView nowShowingRecyclerView;
    MoviesRecyclerViewAdapter nowShowingRecyclerAdapter;
    ArrayList<ImdbData> nowShowingMoviesDataArrayList = new ArrayList<>();

    RecyclerView topRatedRecyclerView;
    MoviesRecyclerViewAdapter topRatedMoviesRecyclerAdapter;
    ArrayList<ImdbData> topRatedMoviesDataArrayList = new ArrayList<>();

    RecyclerView upComingRecyclerView;
    MoviesRecyclerViewAdapter upComingRecyclerAdapter;
    ArrayList<ImdbData> upComingMoviesDataArrayList = new ArrayList<>();

    RecyclerView popularRecyclerView;
    MoviesRecyclerViewAdapter popularRecyclerAdapter;
    ArrayList<ImdbData> popularMoviesDataArrayList = new ArrayList<>();

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

        //NOW SHOWING MOVIES
        SnapHelper snapHelper1 = new LinearSnapHelper();
        nowShowingRecyclerView = findViewById(R.id.nowShowingRecyclerView);
        nowShowingRecyclerAdapter = new MoviesRecyclerViewAdapter(this, nowShowingMoviesDataArrayList);
        nowShowingRecyclerView.setAdapter(nowShowingRecyclerAdapter);
        nowShowingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        nowShowingRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        nowShowingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        snapHelper1.attachToRecyclerView(nowShowingRecyclerView);

        //TOP RATED MOVIES
        SnapHelper snapHelper2 = new LinearSnapHelper();
        topRatedRecyclerView = findViewById(R.id.topRatedRecyclerView);
        topRatedMoviesRecyclerAdapter = new MoviesRecyclerViewAdapter(this,topRatedMoviesDataArrayList);
        topRatedRecyclerView.setAdapter(topRatedMoviesRecyclerAdapter);
        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topRatedRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        topRatedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        snapHelper2.attachToRecyclerView(topRatedRecyclerView);

        //UP COMING MOVIES
        SnapHelper snapHelper3 = new LinearSnapHelper();
        upComingRecyclerView = findViewById(R.id.upComingRecyclerView);
        upComingRecyclerAdapter = new MoviesRecyclerViewAdapter(this, upComingMoviesDataArrayList);
        upComingRecyclerView.setAdapter(upComingRecyclerAdapter);
        upComingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        upComingRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        upComingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        snapHelper3.attachToRecyclerView(upComingRecyclerView);

        //POPULAR MOVIES
        SnapHelper snapHelper4 = new LinearSnapHelper();
        popularRecyclerView = findViewById(R.id.popularRecyclerView);
        popularRecyclerAdapter = new MoviesRecyclerViewAdapter(this, popularMoviesDataArrayList);
        popularRecyclerView.setAdapter(popularRecyclerAdapter);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        popularRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        popularRecyclerView.setItemAnimator(new DefaultItemAnimator());
        snapHelper4.attachToRecyclerView(popularRecyclerView);

        nowShowingRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        getGenres();
        fetchData();

        nowShowingRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
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
                Toast.makeText(MainActivity.this,"Genres not found on Internet",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchData() {

        Call<MoviesList> nowPlayingMoviesDataCall = ApiClient.getInstance().getApiCallInterface().nowPlayingMoviesData();
        Call<MoviesList> topRatedMoviesDataCall = ApiClient.getInstance().getApiCallInterface().topRatedMoviesData();
        Call<MoviesList> upComingMoviesDataCall = ApiClient.getInstance().getApiCallInterface().upComingMoviesData();
        Call<MoviesList> popularMoviesDataCall = ApiClient.getInstance().getApiCallInterface().popularMoviesData();

        nowPlayingMoviesDataCall.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                Log.d("onRespose", "Function is working");
                MoviesList moviesList = response.body();
                if(moviesList != null){
                    nowShowingMoviesDataArrayList.clear();
                    nowShowingMoviesDataArrayList.addAll(moviesList.results);
                    nowShowingRecyclerAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Latest movies can't fetched rom Internet",Toast.LENGTH_LONG).show();
            }
        });
        topRatedMoviesDataCall.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                if(moviesList != null){
                    topRatedMoviesDataArrayList.clear();
                    topRatedMoviesDataArrayList.addAll(moviesList.results);
                    topRatedMoviesRecyclerAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Popular movies can't fetched rom Internet",Toast.LENGTH_LONG).show();
            }
        });
        upComingMoviesDataCall.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                if(moviesList != null){
                    upComingMoviesDataArrayList.clear();
                    upComingMoviesDataArrayList.addAll(moviesList.results);
                    upComingRecyclerAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Upcoming movies can't fetched rom Internet",Toast.LENGTH_LONG).show();
            }
        });
        popularMoviesDataCall.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                if(moviesList != null){
                    popularMoviesDataArrayList.clear();
                    popularMoviesDataArrayList.addAll(moviesList.results);
                    popularRecyclerAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Popular movies can't fetched rom Internet",Toast.LENGTH_LONG).show();
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
