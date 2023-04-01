package edu.dacheville.projet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {
    private final List<Drink> drinks;

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
        holder.drinkDescription.setText(drink.getDescription());
        holder.drinkPrice.setText(String.format(Locale.US, "%.2f €", drink.getPrice()));
        Picasso.get().load(drink.getImageUrl()).into(holder.drinkImage);
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView drinkName;
        TextView drinkDescription;
        TextView drinkPrice;
        ImageView drinkImage;

        ViewHolder(View itemView) {
            super(itemView);
            drinkName = itemView.findViewById(R.id.drink_name);
            drinkDescription = itemView.findViewById(R.id.drink_description);
            drinkPrice = itemView.findViewById(R.id.drink_price);
            drinkImage = itemView.findViewById(R.id.drink_image);
        }
    }
}
