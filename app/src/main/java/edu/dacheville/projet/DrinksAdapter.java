package edu.dacheville.projet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {
    private List<Drink> drinks;

    public DrinksAdapter(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Drink drink = drinks.get(position);
        holder.drinkName.setText(drink.getName());
        holder.drinkPrice.setText(String.format(Locale.US, "%.2f €", drink.getPrice()));
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView drinkName;
        TextView drinkPrice;

        ViewHolder(View itemView) {
            super(itemView);
            drinkName = itemView.findViewById(R.id.drink_name);
            drinkPrice = itemView.findViewById(R.id.drink_price);
        }
    }
}

