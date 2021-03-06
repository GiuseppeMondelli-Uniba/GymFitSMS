package com.example.gymfit.system.conf.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymfit.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ListTurnPickerAdapter extends RecyclerView.Adapter<ListTurnPickerAdapter.MyViewHolder> {
    private final List<String> turns;
    private final Context context;

    private final OnItemClickListener listener;

    public ListTurnPickerAdapter(@NonNull final Context context, @NonNull final List<String> turns, final OnItemClickListener listener) {
        this.turns = turns;
        this.context = context;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final MaterialTextView title;
        private final LinearLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.title);
            this.container = itemView.findViewById(R.id.turn_row);
        }

        public void bind(int position, OnItemClickListener listener) {
            this.container.setOnClickListener(v -> listener.onItemClick(this, position));
        }
    }

    @NonNull
    @Override
    public ListTurnPickerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.layout_recycleview_turn_picker, parent, false);
        return new ListTurnPickerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTurnPickerAdapter.MyViewHolder holder, int position) {
        holder.bind(position, this.listener);

        holder.title.setText(this.turns.get(position));
    }

    @Override
    public int getItemCount() {
        return this.turns.size();
    }

    public void removeItem(int position) {
        this.turns.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(@NonNull String item, int position) {
        this.turns.add(position, item);
        notifyItemInserted(position);
    }

    public void replaceItems(@NonNull List<String> items) {
        this.turns.clear();
        this.turns.addAll(items);
        notifyDataSetChanged();
    }

}
