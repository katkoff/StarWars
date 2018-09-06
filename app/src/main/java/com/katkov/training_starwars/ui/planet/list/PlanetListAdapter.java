package com.katkov.training_starwars.ui.planet.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.entities.PlanetSummary;

import java.util.ArrayList;
import java.util.List;

public final class PlanetListAdapter extends RecyclerView.Adapter<PlanetListAdapter.ViewHolder> {

    private List<PlanetSummary> data = new ArrayList<>();
    private ClickPlanetListCallback callback;

    PlanetListAdapter(ClickPlanetListCallback callback) {
        this.callback = callback;
    }

    public final void setPlanets(List<PlanetSummary> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), callback);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    interface ClickPlanetListCallback {
        void onClickSuccess(int planetId);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView_name);
        }

        void bind(final PlanetSummary planetSummary, final ClickPlanetListCallback callback) {
            nameTextView.setText(planetSummary.getName());

            final int planetId = planetSummary.getPlanetID();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickSuccess(planetId);
                }
            });
        }
    }
}
