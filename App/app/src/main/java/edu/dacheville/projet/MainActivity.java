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

        // Create a RecyclerView to display the list of drinks
        RecyclerView drinksRecyclerView = findViewById(R.id.drinks_recyclerview);
        drinksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Drink> drinks = loadDrinksFromJson();

        // Create an adapter to display the list items in the RecyclerView
        DrinksAdapter drinksAdapter = new DrinksAdapter(drinks);
        drinksRecyclerView.setAdapter(drinksAdapter);

        drinksAdapter.setOnClickListener(this::onClickDrink);
    }

    public void onClickDrink(Drink item){
        // Create a new activity to display details of the selected drink
        Intent intent = new Intent(getApplicationContext(), DrinkActivity.class);
        intent.putExtra(DRINK, item);
        startActivity(intent);
    }

    // This method loads the list of drinks from a JSON file
    private List<Drink> loadDrinksFromJson() {
        try {
            // Open the JSON file as an input stream
            InputStream inputStream = getAssets().open("drinks.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            // Convert the data to a string
            String json = new String(buffer, StandardCharsets.UTF_8);
            // Use the Gson library to convert the JSON data to Java objects
            Gson gson = new Gson();
            Type drinksType = new TypeToken<List<Drink>>() {}.getType();
            return gson.fromJson(json, drinksType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
