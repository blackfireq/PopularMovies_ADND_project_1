package com.example.android.popularmovies_adnd_project_1.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by mikem on 8/3/2017.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie";

    private static final String MOVIE_POPULAR = "popular";

    private static final String MOVIE_TOP_RATED = "top_rated";

    private static final String MOVIE_IMG_BASE_URL = "http://image.tmdb.org/t/p";

    private static final String MOVIE_IMG_SIZE = "w185";

    private static final String MOVIE_API_KEY = "";

    private static final String API_PARAM = "api_key";

    //build movie image url
    public static URL buildURL(String imageLocation){
        Uri builtUrl = Uri.parse(MOVIE_IMG_BASE_URL).buildUpon()
                .appendPath(MOVIE_IMG_SIZE)
                .appendPath(imageLocation)
                .build();

        URL url = null;

        try{
            url = new URL(builtUrl.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl (String movieUrl) throws IOException {
        //URL url = buildURL(movieUrl);
        URL url = new URL(MOVIE_BASE_URL + "?api_key=09c9e331efb5e1fb13ac90d94582b869");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }


}
