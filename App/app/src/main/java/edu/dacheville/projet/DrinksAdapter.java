package edu.dacheville.projet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {
    private final List<Drink> drinks;
    private OnClickListener onClickListener;

    // Constructor to initialize the list of drinks
    public DrinksAdapter(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a list item (drink_item.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the Drink object at the current position in the list
        Drink drink = drinks.get(position);
        // Set the name and price of the drink in the corresponding views
        holder.drinkName.setText(drink.getName());
        holder.drinkPrice.setText(String.format(Locale.US, "%.2f €", drink.getPrice()));
        // Set the image of the drink based on its position in the list
        int[] cocaSRC = {R.drawable.coca_classique, R.drawable.coca_zero, R.drawable.coca_light, R.drawable.coca_cherry, R.drawable.coca_vanilla, R.drawable.coca_life, R.drawable.coca_caffeine_free, R.drawable.coca_raspberry, R.drawable.coca_orange, R.drawable.coca_energy, R.drawable.coca_signature_mixers};
        int imageSrc = cocaSRC[position];
        holder.drinkImage.setImageResource(imageSrc);
        // Add a listener to listen for clicks on the item
        holder.itemView.setOnClickListener(v -> onClickListener.onClick(drink));
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    public interface OnClickListener {
        void onClick(Drink drink);
    }

    // Define ViewHolder class which holds the views for a list item
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

