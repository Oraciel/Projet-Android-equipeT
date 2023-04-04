package edu.dacheville.projet;

import static edu.dacheville.projet.Application.DRINK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DrinkActivity extends AppCompatActivity {
    private Drink drink;
    private int count = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //recovers the drink selected in the previous activity
        drink = getIntent().getParcelableExtra(DRINK);

        //update the information of the drink
        ((TextView)findViewById(R.id.drinkPrice)).setText(drink.getPrice() +"0 €");
        ((TextView)findViewById(R.id.drinkName)).setText(drink.getName());
        ((TextView)findViewById(R.id.drinkDescription)).setText(drink.getDescription());
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(getImageName(drink));

        //button and text to choose the quantity of drink
        Button minusBtn = findViewById(R.id.ButtonMinus);
        Button addBtn = findViewById(R.id.ButtonAdd);
        final TextView display = findViewById(R.id.Cpt);
        final TextView price = findViewById(R.id.drinkPrice);

        display.setText(Integer.toString(count));
        price.setText((float) (drink.getPrice() * count) +"0 €");

        //increases the quantity and the price according to the chosen quantity
        addBtn.setOnClickListener(view -> {
            display.setText(Integer.toString(++count));
            price.setText((float) (drink.getPrice() * count) +"0 €");
        });

        //decreases the quantity and the price according to the chosen quantity
        minusBtn.setOnClickListener(view -> {
            if (count > 1) {
                display.setText(Integer.toString(--count));
                price.setText((float) (drink.getPrice() * count) + "0 €");
            }
        });



        //creation of an AlertDialog when clicking on buttonBuy
        findViewById(R.id.buttonBuy).setOnClickListener(click -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("BOISSON");
            //short summary of the order
            builder.setMessage("Vous voulez commandé la boisson : " + "\n" + count + " X " + drink.getName() + "\nPour un total de : " + (float) (drink.getPrice() * count) +"0 €");
            //button on the AlertDialog then Parcelab which sends the important information and goes to the DrinkActivity
            builder.setNeutralButton("Passez au récapitulatif", (dialogInterface, i) -> {
                Intent intent = new Intent(DrinkActivity.this, OrderActivity.class);
                intent.putExtra("boisson", drink.getName());
                intent.putExtra("quantite", count);
                intent.putExtra("prixTotal", drink.getPrice()*count);
                int imageSrc = getImageName(drink);
                drink.setImage(imageSrc);
                intent.putExtra(DRINK, drink);
                startActivity(intent);
            });
            builder.show();
        });

    }

    //function that retrieves the image corresponding to the product
    //input the selected drink
    //the image of the drink in int
    private int getImageName(Drink drink) {
        int imageSrc;
        switch (drink.getName()) {
            case "Coca-Cola Zero":
                imageSrc = R.drawable.coca_zero;
                break;
            case "Coca-Cola Light":
                imageSrc = R.drawable.coca_light;
                break;
            case "Coca-Cola Cherry":
                imageSrc = R.drawable.coca_cherry;
                break;
            case "Coca-Cola Vanilla":
                imageSrc = R.drawable.coca_vanilla;
                break;
            case "Coca-Cola Life":
                imageSrc = R.drawable.coca_life;
                break;
            case "Coca-Cola Caffeine Free":
                imageSrc = R.drawable.coca_caffeine_free;
                break;
            case "Coca-Cola Raspberry":
                imageSrc = R.drawable.coca_raspberry;
                break;
            case "Coca-Cola Orange":
                imageSrc = R.drawable.coca_orange;
                break;
            case "Coca-Cola Energy":
                imageSrc = R.drawable.coca_energy;
                break;
            case "Coca-Cola Signature Mixers":
                imageSrc = R.drawable.coca_signature_mixers;
                break;
            default:
                imageSrc = R.drawable.coca_classique;
        }
        return imageSrc;
    }

}