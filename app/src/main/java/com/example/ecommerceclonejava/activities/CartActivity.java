package com.example.ecommerceclonejava.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.adapter.CartAdapter;
import com.example.ecommerceclonejava.model.CartItem;
import com.example.ecommerceclonejava.utils.PrefManager;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recycler;
    CartAdapter adapter;
    PrefManager pref;
    List<CartItem> list;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recycler = findViewById(R.id.recyclerCart);
        tvTotal = findViewById(R.id.tvTotal);

        pref = new PrefManager(this);
        list = pref.getCartList();

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, list, this::updateTotal);
        recycler.setAdapter(adapter);
        Button btncheck = findViewById(R.id.btnCheckout);

        btncheck.setOnClickListener(v ->
                android.widget.Toast.makeText(this, "For Ordering Payment", android.widget.Toast.LENGTH_SHORT).show()
        );
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());
        updateTotal();
    }

    private void updateTotal() {
        int total = 0;
        for (CartItem item : list) {
            total += Integer.parseInt(item.price) * item.quantity;
        }
        tvTotal.setText("Total: ₹" + total);
    }
}