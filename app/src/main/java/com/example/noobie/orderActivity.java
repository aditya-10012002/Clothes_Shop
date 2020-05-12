package com.example.noobie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class orderActivity extends AppCompatActivity {

    String[] addresses = {"adityakr1001@gmail.com"};
    String subject = "Placing order from clothes shop";
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        
        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntent", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntent", 0);
        TextView orderTextView = findViewById(R.id.orderTextView);
        text = "UserName - "+ userName + "\n"
                + "Name of Product - " + goodsName + "\n"
                + "Quantity of Product - " + quantity + "\n"
                + "Total Order Price - "+ orderPrice+" $";
        orderTextView.setText(text);
    }

    public void placeOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
