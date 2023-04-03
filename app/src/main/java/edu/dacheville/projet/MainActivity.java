package edu.dacheville.projet;

import static edu.dacheville.projet.Application.DRINK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView drinksRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //load for adds
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        drinksRecyclerView = findViewById(R.id.drinks_recyclerview);
        drinksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Drink> drinks = loadDrinksFromJson();
        DrinksAdapter drinksAdapter = new DrinksAdapter(drinks);
        drinksRecyclerView.setAdapter(drinksAdapter);

        drinksAdapter.setOnClickListener(new DrinksAdapter.OnClickListener() {
            @Override
            public void onClick(Drink drink) {
                onClickDrink(drink);
            }
        });
    }

    public void onClickDrink(Drink item){
        Intent intent = new Intent(getApplicationContext(), DrinkActivity.class);
        intent.putExtra(DRINK,(Parcelable) item);
        startActivity(intent);
    }

    private List<Drink> loadDrinksFromJson() {
        try {
            InputStream inputStream = getAssets().open("drinks.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            Type drinksType = new TypeToken<List<Drink>>() {}.getType();
            return gson.fromJson(json, drinksType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
