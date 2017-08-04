package com.example.android.popularmovies_adnd_project_1;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView mIdView;
    TextView mTitleView;
    TextView mReleaseDataView;
    TextView mVoteAverageView;
    TextView mPlotView;
    ImageView mPosterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String mId = getIntent().getStringExtra("id").toString();
        String mTitle = getIntent().getStringExtra("title").toString();
        String mReleaseDate = getIntent().getStringExtra("releaseDate").toString();
        String mVoteAverage = getIntent().getStringExtra("voteAverage").toString();
        String mPlot = getIntent().getStringExtra("plot").toString();
        String mPosterResourceId = getIntent().getStringExtra("posterResourceId").toString();

        mIdView = (TextView)findViewById(R.id.tv_movie_id);
        mTitleView  = (TextView)findViewById(R.id.tv_movie_title);
        mReleaseDataView  = (TextView)findViewById(R.id.tv_movie_release_date);
        mVoteAverageView = (TextView)findViewById(R.id.tv_movie_vote_average);
        mPlotView  = (TextView)findViewById(R.id.tv_movie_plot);
        mPosterView = (ImageView)findViewById(R.id.iv_movie_poster);

        mIdView.setText(mId);
        mTitleView.setText(mTitle);
        mReleaseDataView.setText(mReleaseDate);
        mVoteAverageView.setText(mVoteAverage);
        mPlotView.setText(mPlot);
        Picasso.with(this).load(mPosterResourceId).into(mPosterView);



    }
}
