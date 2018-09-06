package com.katkov.training_starwars.ui.favorites;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.database.FavoritesTable;

import java.util.ArrayList;
import java.util.List;

public final class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<FavoritesTable> data = new ArrayList<>();

    public final void setFavorites(List<FavoritesTable> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoritesTable item = data.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView favoriteTextView;

        ViewHolder(View itemView) {
            super(itemView);

            favoriteTextView = itemView.findViewById(R.id.textView_favoriteName);
        }

        void bind(FavoritesTable table) {
            favoriteTextView.setText(table.getFavoriteName());
        }
    }
}
