package com.example.ecommerceclonejava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.model.CartItem;
import com.example.ecommerceclonejava.utils.PrefManager;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public interface OnCartChangeListener {
        void onCartChanged();
    }

    Context context;
    List<CartItem> list;
    PrefManager pref;
    OnCartChangeListener listener;

    public CartAdapter(Context context, List<CartItem> list, OnCartChangeListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
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
        h.price.setText("₹ " + item.price);
        h.image.setImageResource(item.image);
        h.qty.setText(String.valueOf(item.quantity));

        h.plus.setOnClickListener(v -> {
            item.quantity++;
            pref.saveCartList(list);
            notifyDataSetChanged();
            listener.onCartChanged();
        });

        h.minus.setOnClickListener(v -> {
            if (item.quantity > 1) {
                item.quantity--;
            } else {
                list.remove(position);
            }
            pref.saveCartList(list);
            notifyDataSetChanged();
            listener.onCartChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price, qty;
        Button plus, minus;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imgCart);
            name = itemView.findViewById(R.id.tvCartName);
            price = itemView.findViewById(R.id.tvCartPrice);
            qty = itemView.findViewById(R.id.tvQty);
            plus = itemView.findViewById(R.id.btnPlus);
            minus = itemView.findViewById(R.id.btnMinus);
        }
    }
}