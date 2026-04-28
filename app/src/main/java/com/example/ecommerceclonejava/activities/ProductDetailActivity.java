package com.example.ecommerceclonejava.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.model.CartItem;
import com.example.ecommerceclonejava.utils.PrefManager;

import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView img;
    TextView name, price, desc;
    Button addCart;

    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());
        img = findViewById(R.id.imgDetail);
        name = findViewById(R.id.tvName);
        price = findViewById(R.id.tvPrice);
        desc = findViewById(R.id.tvDesc);
        addCart = findViewById(R.id.btnAddCart);

        pref = new PrefManager(this);

        String n = getIntent().getStringExtra("name");
        String p = getIntent().getStringExtra("price");
        String d = getIntent().getStringExtra("desc");
        int i = getIntent().getIntExtra("image", 0);

        name.setText(n);
        price.setText("₹ " + p);
        desc.setText(d);
        img.setImageResource(i);

        addCart.setOnClickListener(v -> {

            List<CartItem> cart = pref.getCartList();
            boolean found = false;

            for (CartItem item : cart) {
                if (item.name.equals(n)) {
                    item.quantity++;
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new CartItem(n, p, i, 1));
            }

            pref.saveCartList(cart);

            Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show();
        });
    }
}