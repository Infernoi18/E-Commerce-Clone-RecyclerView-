package com.example.ecommerceclonejava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.model.CartItem;
import com.example.ecommerceclonejava.utils.PrefManager;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<CartItem> list;
    PrefManager pref;
    Runnable updateTotal;

    public CartAdapter(Context context, List<CartItem> list, Runnable updateTotal) {
        this.context = context;
        this.list = list;
        this.updateTotal = updateTotal;
        pref = new PrefManager(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int position) {

        CartItem item = list.get(position);

        h.name.setText(item.name);
        h.price.setText("Rs. " + item.price);
        h.qty.setText(String.valueOf(item.quantity));
        h.image.setImageResource(item.image);

        h.plus.setOnClickListener(v -> {
            item.quantity++;
            pref.saveCartList(list);
            notifyDataSetChanged();
            updateTotal.run();
        });

        h.minus.setOnClickListener(v -> {
            if (item.quantity > 1) {
                item.quantity--;
            } else {
                list.remove(position);
            }
            pref.saveCartList(list);
            notifyDataSetChanged();
            updateTotal.run();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price, qty, plus, minus;

        public ViewHolder(View v) {
            super(v);

            image = v.findViewById(R.id.imgCart);
            name = v.findViewById(R.id.tvCartName);
            price = v.findViewById(R.id.tvCartPrice);
            qty = v.findViewById(R.id.tvQty);
            plus = v.findViewById(R.id.btnPlus);
            minus = v.findViewById(R.id.btnMinus);
        }
    }
}