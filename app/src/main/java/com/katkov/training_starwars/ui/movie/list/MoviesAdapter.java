package com.katkov.training_starwars.ui.movie.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.entities.MovieSummary;

import java.util.ArrayList;
import java.util.List;

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<MovieSummary> data = new ArrayList<>();
    private ClickMovieListCallback callback;

    MoviesAdapter(ClickMovieListCallback callback) {
        this.callback = callback;
    }

    public final void setMovies(List<MovieSummary> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieSummary item = data.get(position);
        holder.bind(item, callback);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ClickMovieListCallback {
        void onClickSuccess(int id);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView movieId;
        private TextView movieName;
        private TextView movieDescription;

        ViewHolder(View itemView) {
            super(itemView);
            movieId = itemView.findViewById(R.id.textView_episode);
            movieName = itemView.findViewById(R.id.textView_title);
            movieDescription = itemView.findViewById(R.id.textView_description);
        }

        void bind(final MovieSummary movieSummary, final ClickMovieListCallback callback) {
            movieId.setText(Integer.toString(movieSummary.getEpisode()));
            movieName.setText(movieSummary.getName());
            movieDescription.setText(movieSummary.getDescription());

            final int movieId = movieSummary.getMovieID();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickSuccess(movieId);
                }
            });
        }
    }
}
