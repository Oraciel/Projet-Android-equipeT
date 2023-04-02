package edu.dacheville.projet;

import static edu.dacheville.projet.Application.DRINK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    private Drink drink;
    private int count;
    private double prixTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        drink = getIntent().getParcelableExtra(DRINK);
        count = getIntent().getIntExtra("quantite", 0);
        prixTotal = getIntent().getDoubleExtra("prixTotal", 0);

        ((TextView)findViewById(R.id.order)).setText(drink.getName()+" X "+count);
        ((TextView)findViewById(R.id.total)).setText("Total : "+prixTotal+"0 €");

        ImageView imageView = findViewById(R.id.imgV);
        imageView.setImageResource(drink.getImage());

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), "Votre note est : " + rating + "\nMerci d'avoir donné votre avis", Toast.LENGTH_SHORT).show();
            }
        });


        Button confirmBtn = findViewById(R.id.confirm_order);
        confirmBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, PaymentActivity.class);
            startActivity(intent);
        });
    }
}