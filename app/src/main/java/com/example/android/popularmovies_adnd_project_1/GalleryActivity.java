package com.example.android.popularmovies_adnd_project_1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies_adnd_project_1.utils.MovieJsonUtils;
import com.example.android.popularmovies_adnd_project_1.utils.NetworkUtils;
import com.example.android.popularmovies_adnd_project_1.MovieAdapter.MovieAdapterOnClickHandler;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.android.popularmovies_adnd_project_1.utils.NetworkUtils.getResponseFromHttpUrl;

public class GalleryActivity extends AppCompatActivity implements MovieAdapterOnClickHandler{

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //connection to the recyclerview layout
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_view);

        //connection to the empty list Textview
        mErrorMessageDisplay = (TextView)findViewById(R.id.empty_list);

        //connection to the progress bar
        mLoadingIndicator = (ProgressBar)findViewById(R.id.pb_indicator);

        //set up the layout for the recyclerview
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        //connect layoutmanager to recylcerview
        mRecyclerView.setLayoutManager(layoutManager);

        //set for stability
        mRecyclerView.setHasFixedSize(true);

        //create adapter
        mMovieAdapter = new MovieAdapter(this,this);

        //connect recycleview to adapter
        mRecyclerView.setAdapter(mMovieAdapter);

        new FetchMoviesTask().execute("blah");

    }

    @Override
    public void onClick(Movie movie) {

        String id = movie.getid();
        String title = movie.getTitle();
        String releaseDate = movie.getReleaseDate();
        String voteAverage = movie.getVoteAverage();
        String plot = movie.getPlot();
        String posterResourceId = movie.getPosterResourceId();
        Context context = this;
        Class destinationClass = DetailsActivity.class;
        Intent movieDetailsIntent = new Intent(context, destinationClass);
        movieDetailsIntent.putExtra("id",id);
        movieDetailsIntent.putExtra("title",title);
        movieDetailsIntent.putExtra("releaseDate",releaseDate);
        movieDetailsIntent.putExtra("voteAverage", voteAverage);
        movieDetailsIntent.putExtra("plot", plot);
        movieDetailsIntent.putExtra("posterResourceId", posterResourceId);
        startActivity(movieDetailsIntent);

    }

    //show movies
    private void showMovies(){
        mLoadingIndicator.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    //show error message
    private void showErrorMessage(){
        mRecyclerView.setVisibility(View.GONE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<Movie>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            String url = "http://api.themoviedb.org/3/discover/movie";

            try {
                 String results = NetworkUtils.getResponseFromHttpUrl(url);

                try{
                    return MovieJsonUtils.getMovieStringsFromJson(results);
                }catch (JSONException e){
                    e.printStackTrace();
                    return null;
                }

            } catch (IOException e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Movie> Movies) {
            showMovies();
            mMovieAdapter.setmMovieData(Movies);
        }
    }

}
