package com.example.ecommerceclonejava.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tvTotal;
    Button btnCheckout;

    PrefManager pref;
    List<CartItem> list;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recycler = findViewById(R.id.recyclerCart);
        tvTotal = findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        pref = new PrefManager(this);
        list = pref.getCartList();

        adapter = new CartAdapter(this, list, this::updateTotal);
        recycler.setAdapter(adapter);

        updateTotal();

        btnCheckout.setOnClickListener(v ->
                Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show()
        );
    }

    private void updateTotal() {
        int total = 0;

        for (CartItem item : list) {
            total += Integer.parseInt(item.price) * item.quantity;
        }

        tvTotal.setText("Total: ₹" + total);
    }
}