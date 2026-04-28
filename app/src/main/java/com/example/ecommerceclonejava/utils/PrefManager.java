package com.example.ecommerceclonejava.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.ecommerceclonejava.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class PrefManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public PrefManager(Context context) {
        prefs = context.getSharedPreferences("APP", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // 🔹 USER
    public void saveUser(String email, String password) {
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    public boolean checkUser(String email, String password) {
        return email.equals(prefs.getString("email", "")) &&
                password.equals(prefs.getString("password", ""));
    }

    public void setLoggedIn(boolean value) {
        editor.putBoolean("isLoggedIn", value);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean("isLoggedIn", false);
    }


    public void saveCartList(List<CartItem> list) {
        JSONArray array = new JSONArray();

        try {
            for (CartItem item : list) {
                JSONObject obj = new JSONObject();
                obj.put("name", item.name);
                obj.put("price", item.price);
                obj.put("image", item.image);
                obj.put("quantity", item.quantity);
                array.put(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        editor.putString("cart_json", array.toString());
        editor.apply();
    }


    public List<CartItem> getCartList() {
        List<CartItem> list = new ArrayList<>();
        String json = prefs.getString("cart_json", "");

        try {
            JSONArray array = new JSONArray(json);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                list.add(new CartItem(
                        obj.getString("name"),
                        obj.getString("price"),
                        obj.getInt("image"),
                        obj.getInt("quantity")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}