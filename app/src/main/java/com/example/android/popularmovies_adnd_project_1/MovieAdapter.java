package com.example.android.popularmovies_adnd_project_1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.onClick;
import static java.lang.System.load;

/**
 * Created by mikem on 8/3/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    Context mContext;

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final ImageView poster_view;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            poster_view = (ImageView)itemView.findViewById(R.id.iv_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //get the position
            int adapterPostion = getAdapterPosition();
            //get the movie that was clicked on from arraylist
            Movie movie = mMovieData.get(adapterPostion);
            //pass to onClick
            mClickHandler.onClick(movie);


        }
    }

    //implement interface to handle onclick actions
    public interface MovieAdapterOnClickHandler{
        void onClick(Movie movie);
    }

    private final MovieAdapterOnClickHandler mClickHandler;

    public MovieAdapter(Context context, MovieAdapterOnClickHandler clickHandler){
        mContext = context;
        mClickHandler = clickHandler;
    }

    // stores the movie data
    private ArrayList<Movie> mMovieData;

    @Override
    public int getItemCount() {
        if (mMovieData == null) {
            return 0;
        }
        return mMovieData.size();
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder movieHolder, int position) {
        Movie currentMovie = mMovieData.get(position);
        String posterResourceId = currentMovie.getPosterResourceId();
        Picasso.with(mContext).load(posterResourceId).into(movieHolder.poster_view);

    }

    public void setmMovieData(ArrayList<Movie> movieData){
        mMovieData = movieData;
        notifyDataSetChanged();
    }
}
