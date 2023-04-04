package edu.dacheville.projet;

import static edu.dacheville.projet.Application.DRINK;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView drinksRecyclerView = findViewById(R.id.drinks_recyclerview);
        drinksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Drink> drinks = loadDrinksFromJson();
        DrinksAdapter drinksAdapter = new DrinksAdapter(drinks);
        drinksRecyclerView.setAdapter(drinksAdapter);

        drinksAdapter.setOnClickListener(this::onClickDrink);
    }

    public void onClickDrink(Drink item){
        Intent intent = new Intent(getApplicationContext(), DrinkActivity.class);
        intent.putExtra(DRINK, item);
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
