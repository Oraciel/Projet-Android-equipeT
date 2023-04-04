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
        int[] cocaSRC = {R.drawable.coca_classique, R.drawable.coca_zero, R.drawable.coca_light, R.drawable.coca_cherry, R.drawable.coca_vanilla, R.drawable.coca_life, R.drawable.coca_caffeine_free, R.drawable.coca_raspberry, R.drawable.coca_orange, R.drawable.coca_energy, R.drawable.coca_signature_mixers};
        int imageSrc = cocaSRC[position];
        holder.drinkImage.setImageResource(imageSrc);
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

