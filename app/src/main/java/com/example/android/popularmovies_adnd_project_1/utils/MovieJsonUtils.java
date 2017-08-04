package com.example.android.popularmovies_adnd_project_1.utils;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.example.android.popularmovies_adnd_project_1.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mikem on 8/3/2017.
 */

public class MovieJsonUtils {

    // Tag for the log messages
    public static final String LOG_TAG = MovieJsonUtils.class.getSimpleName();

    public static ArrayList<Movie> getMovieStringsFromJson(String movieJson)
            throws JSONException{
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try {
            //inital JSON object
            JSONObject jsonRoot = new JSONObject(movieJson);
            //get results array
            JSONArray movies = jsonRoot.getJSONArray("results");
            //get each of the movie details and pack into arrayList
            if(movies.length() > 0){
                for (int i=0;i<movies.length();i++) {
                    JSONObject movie = movies.getJSONObject(i);

                    //get the id
                    String id = movie.getString("id");
                    //get the title
                    String title = movie.getString("title");
                    //get release data
                    String releaseDate = movie.getString("release_date");
                    //get the vote_average
                    String vote_average = movie.getString("vote_average");
                    //get the overview
                    String overview = movie.getString("overview");
                    //get the poster_path
                    String poster_path = movie.getString("poster_path");

                    movieList.add(new Movie(id,title,releaseDate,vote_average,overview,poster_path));
                }

            }

        } catch (JSONException e){
            Log.e(LOG_TAG, "there is a pronlem parsing the Movie JSON results");
        }
        return movieList;
    }
}
