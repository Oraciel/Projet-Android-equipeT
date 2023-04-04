package edu.dacheville.projet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.stripe.android.ApiResultCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;


public class PaymentActivity extends AppCompatActivity {

    private Stripe mStripe;

    //Dans ce cas j'utilise Stripe pour simuler un paiement avec une fausse carte bancaire
    //La requete est envoye lors du payement dans mon dashboard developer stripe

    //Fausse carte bancaire pour tester l'api de payement VISA, 4485808782119269, 8/2025, 243
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Initialize the Stripe API with your test public key
        mStripe = new Stripe(this, "pk_test_51MsoHyFtIWoaGqMDUsbUlHFPlp86jxFM6aABtu0TmaUs0h7oIRUjWV89bJ1RsEnFenBZ0EvxlVK5hiWTjfflh93D00TPjxm39o");

        // Set up the CardInputWidget and attach it to the layout
        CardInputWidget cardInputWidget = findViewById(R.id.card_input_widget);
        Button payButton = findViewById(R.id.pay_button);
        payButton.setOnClickListener(v -> {
            // Create a PaymentMethod using the CardInputWidget
            PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
            if (params == null) {
                Toast.makeText(this, "Invalid card details", Toast.LENGTH_SHORT).show();
                return;
            }

            // Use ApiResultCallback
            mStripe.createPaymentMethod(params, new ApiResultCallback<PaymentMethod>() {
                @Override
                public void onSuccess(@NonNull PaymentMethod paymentMethod) {
                    // Payment method created successfully, proceed to charge the card
                    // Call your server-side API to charge the card with the payment method ID
                    Toast.makeText(PaymentActivity.this, "Payment succeeded", Toast.LENGTH_SHORT).show();

                    // Start the new activity
                    Intent intent = new Intent(PaymentActivity.this, SuccessfulActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onError(@NonNull Exception e) {
                    Toast.makeText(PaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}