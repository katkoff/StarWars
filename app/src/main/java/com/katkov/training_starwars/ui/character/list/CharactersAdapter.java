package com.katkov.training_starwars.ui.character.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.entities.CharacterSummary;

import java.util.ArrayList;
import java.util.List;

public final class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private List<CharacterSummary> data = new ArrayList<>();
    private ClickCharacterListCallback callback;

    CharactersAdapter(ClickCharacterListCallback callback) {
        this.callback = callback;
    }

    public final void setCharacters(List<CharacterSummary> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CharacterSummary item = data.get(position);
        holder.bind(item, callback);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ClickCharacterListCallback {
        void onClickSuccess(int characterId);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView birthyearTextView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView_name);
            birthyearTextView = itemView.findViewById(R.id.textView_birthyear);
        }

        void bind(CharacterSummary characterSummary, final ClickCharacterListCallback callback) {
            nameTextView.setText(characterSummary.getName());
            birthyearTextView.setText(characterSummary.getBirthyear());

            final int characterId = characterSummary.getCharacterID();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickSuccess(characterId);
                }
            });
        }
    }
}
