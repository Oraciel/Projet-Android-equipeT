package edu.dacheville.projet;

import static android.content.ContentValues.TAG;
import static edu.dacheville.projet.Application.DRINK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class OrderActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        interstitalAd();

        Drink drink = getIntent().getParcelableExtra(DRINK);
        int count = getIntent().getIntExtra("quantite", 0);
        double prixTotal = getIntent().getDoubleExtra("prixTotal", 0);

        ((TextView)findViewById(R.id.order)).setText(drink.getName()+" X "+ count);
        ((TextView)findViewById(R.id.total)).setText("Total : "+ prixTotal +"0 €");

        ImageView imageView = findViewById(R.id.imgV);
        imageView.setImageResource(drink.getImage());

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> Toast.makeText(getApplicationContext(), "Votre note est : " + rating + "\nMerci d'avoir donné votre avis", Toast.LENGTH_SHORT).show());


        Button confirmBtn = findViewById(R.id.confirm_order);
        confirmBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, PaymentActivity.class);
            startActivity(intent);
        });
    }

    public void interstitalAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Set the ad reference to null so you don't show the ad a second time.
                        Log.d(TAG, "Ad dismissed fullscreen content.");
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        // Called when ad fails to show.
                        Log.e(TAG, "Ad failed to show fullscreen content.");
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(TAG, "Ad showed fullscreen content.");
                    }
                });
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(OrderActivity.this);
                } else {
                    Log.d(TAG, "The interstitial ad wasn't ready yet.");
                }
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d(TAG, loadAdError.toString());
                mInterstitialAd = null;
            }
        });
    }
}