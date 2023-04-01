package edu.dacheville.projet;

import static edu.dacheville.projet.Application.DRINK;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {
    private Drink drink;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        drink = getIntent().getParcelableExtra(DRINK);
        ((TextView)findViewById(R.id.drinkPrice)).setText(Double.toString( drink.getPrice())+"0 €");
        ((TextView)findViewById(R.id.drinkName)).setText(drink.getName());
        ((TextView)findViewById(R.id.drinkDescription)).setText(drink.getDescription());

        ImageView imageView = findViewById(R.id.imageView);

        int imageSrc;
        switch(drink.getName()) {
            case "Coca-Cola Classic":
                imageSrc = R.drawable.coca_classique;
                break;
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

        imageView.setImageResource(imageSrc);

        Button minusBtn = (Button) findViewById(R.id.ButtonMinus);
        Button addBtn = (Button) findViewById(R.id.ButtonAdd);
        final TextView display = findViewById(R.id.Cpt);
        final TextView price = findViewById(R.id.drinkPrice);

        display.setText(Integer.toString(count));
        price.setText(Float.toString((float) (drink.getPrice() * count))+"0 €");

        addBtn.setOnClickListener(view -> {
            display.setText(Integer.toString(++count));
            price.setText(Float.toString((float) (drink.getPrice()*count))+"0 €");
        });

        minusBtn.setOnClickListener(view -> {
            display.setText(Integer.toString(--count));
            price.setText(Float.toString((float) (drink.getPrice()*count))+"0 €");
        });

        ((Button)findViewById(R.id.buttonBuy)).setOnClickListener(click -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("BOISSON");
            builder.setMessage("Vous avez bien commandé la boisson : " + "\n" + count + " X " + drink.getName() + "\nPour un total de : " + Float.toString((float) (drink.getPrice()*count))+"0 €");
            builder.setNeutralButton("OK", null);
            builder.show();
        });
    }
}