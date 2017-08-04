package com.example.android.popularmovies_adnd_project_1;

import com.example.android.popularmovies_adnd_project_1.utils.NetworkUtils;

/**
 * Created by mikem on 8/3/2017.
 */

public class Movie {
    private String mId;
    private String mTitle;
    private String mReleaseDate;
    private String mVoteAverage;
    private String mPlot;
    private String mPosterResourceId;

    public Movie(String id, String title, String releaseDate, String voteAverage, String plot, String posterResourceId){
        mId = id;
        mTitle = title;
        mReleaseDate = releaseDate;
        mVoteAverage = voteAverage;
        mPlot = plot;
        mPosterResourceId = posterResourceId;
    }

    public String getid(){
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public String getPlot() {
        return mPlot;
    }

    public String getPosterResourceId() {
        return "http://image.tmdb.org/t/p/w780" + mPosterResourceId;
    }

}
