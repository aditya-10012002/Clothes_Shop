package com.example.noobie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView quantityText;
    TextView priceNumberText;
    ImageView sampleImage;
    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName, userName;
    double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityText = findViewById(R.id.quantityText);
        priceNumberText=findViewById(R.id.priceNumberText);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        makeArray();

        spinnerAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        createHashMap();



    }

    public void makeArray()
    {
        spinnerArrayList= new ArrayList();
        spinnerArrayList.add("T-shirts");
        spinnerArrayList.add("Trousers");
        spinnerArrayList.add("Shirts");
        spinnerArrayList.add("Jeans");
    }

    public void createHashMap()
    {
        goodsMap = new HashMap();
        goodsMap.put("T-shirts", 150.0);
        goodsMap.put("Trousers", 100.0);
        goodsMap.put("Shirts", 200.0);
        goodsMap.put("Jeans", 120.0);

    }

    public void decreaseQuantity(View view) {
        quantity--;
        if(quantity < 0)
            quantity = 0;
        quantityText.setText("" + quantity);
        priceNumberText.setText("" + (quantity * price));
    }

    public void increaseQuantity(View view) {
        quantity++;
        quantityText.setText("" + quantity);
        priceNumberText.setText("" + (quantity * price));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        priceNumberText.setText("" + (quantity * price));
        sampleImage = findViewById(R.id.sampleImage);
        switch(goodsName)
        {
            case "T-shirts" :
                sampleImage.setImageResource(R.drawable.sample);
                break;
            case "Trousers" :
                sampleImage.setImageResource(R.drawable.trousers);
                break;
            case "Shirts" :
                sampleImage.setImageResource(R.drawable.shirts);
                break;
            case "Jeans" :
                sampleImage.setImageResource(R.drawable.jeans);
                break;
            default:
                sampleImage.setImageResource(R.drawable.sample);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addtocart(View view) {

        Order order = new Order();
        EditText editText = findViewById(R.id.editText);
        userName = editText.getText().toString();
        order.userName = userName;
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, orderActivity.class);
        orderIntent.putExtra("userNameForIntent", userName);
        orderIntent.putExtra("goodsNameForIntent", goodsName);
        orderIntent.putExtra("quantityForIntent", quantity);
        orderIntent.putExtra("orderPriceForIntent", quantity * price);
        startActivity(orderIntent);

    }
}
