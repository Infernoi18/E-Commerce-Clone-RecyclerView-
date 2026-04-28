package com.example.ecommerceclonejava.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.activities.ProductDetailActivity;
import com.example.ecommerceclonejava.model.Product;
import com.example.ecommerceclonejava.model.CartItem;
import com.example.ecommerceclonejava.utils.PrefManager;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<Product> list;
    PrefManager pref;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
        pref = new PrefManager(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Product p = list.get(position);

        holder.name.setText(p.name);
        holder.desc.setText(p.desc);
        holder.price.setText("Rs. " + p.price);
        holder.image.setImageResource(p.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("name", p.name);
            intent.putExtra("price", p.price);
            intent.putExtra("desc", p.desc);
            intent.putExtra("image", p.image);
            context.startActivity(intent);
        });

        holder.btn.setOnClickListener(v -> {

            List<CartItem> cart = pref.getCartList();
            boolean found = false;

            for (CartItem item : cart) {
                if (item.name.equals(p.name)) {
                    item.quantity++;
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new CartItem(p.name, p.price, p.image, 1));
            }

            pref.saveCartList(cart);

            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price, desc;
        Button btn;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imgProduct);
            name = itemView.findViewById(R.id.tvName);
            desc = itemView.findViewById(R.id.tvDesc);
            price = itemView.findViewById(R.id.tvPrice);
            btn = itemView.findViewById(R.id.btnAddCart);
        }
    }
}