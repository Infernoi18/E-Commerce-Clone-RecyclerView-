package com.example.ecommerceclonejava.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.adapter.ProductAdapter;
import com.example.ecommerceclonejava.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recycler;
    ImageView cartIcon;
    List<Product> list;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recycler = findViewById(R.id.recyclerProducts);
        cartIcon = findViewById(R.id.cartIcon);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        list.add(new Product(
                "iPhone 14",
                "79999",
                "Apple smartphone with A15 chip",
                R.drawable.phone
        ));

        list.add(new Product(
                "Dell Laptop",
                "55999",
                "Intel i5, 16GB RAM, SSD",
                R.drawable.laptop
        ));

        list.add(new Product(
                "Running Shoes",
                "2499",
                "Comfortable sports shoes",
                R.drawable.shoes
        ));

        findViewById(R.id.backBtn).setOnClickListener(v -> finish());

        adapter = new ProductAdapter(this, list);
        recycler.setAdapter(adapter);

        cartIcon.setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));
    }
}