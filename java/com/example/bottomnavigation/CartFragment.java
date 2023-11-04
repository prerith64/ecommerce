package com.example.bottomnavigation;

import static android.content.Intent.getIntent;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {


    private RecyclerView cartItemRecyclerView;
    Button order;

    private TextView totalAmount;
    public static Dialog loadingDialog;

    public static CartAdapter cartAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart,container,false);

          order=view.findViewById(R.id.cart_continue_btn);
          order.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  // Create and configure the AlertDialog
                  AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                  builder.setTitle("Order History");
                  builder.setMessage("Order placed");

                  // Add a positive button and define its action
                  builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          // Perform an action when the "OK" button is clicked
                          // For example, you can close the dialog or do nothing.
                          dialog.dismiss();
                      }
                  });

                  // Add a negative button (optional)
                  builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          // Perform an action when the "Cancel" button is clicked
                          // For example, you can close the dialog or do nothing.
                          dialog.dismiss();
                      }
                  });

                  // Create and show the AlertDialog
                  AlertDialog dialog = builder.create();
                  dialog.show();
              }
          });

        // ... (the rest of your code)




        loadingDialog=new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        loadingDialog.show();





        cartItemRecyclerView=view.findViewById(R.id.cart_items_recyclerView);
        totalAmount=view.findViewById(R.id.total_cart_amount);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerView.setLayoutManager(layoutManager);

        if(EQuires.cartItemModelList.size()==0){
            EQuires.cartList.clear();
            EQuires.loadCartList(getContext(),loadingDialog,true);
        }else {
            loadingDialog.dismiss();
        }





        cartAdapter=new CartAdapter(EQuires.cartItemModelList,totalAmount);     //4:50
         cartItemRecyclerView.setAdapter(cartAdapter);
         cartAdapter.notifyDataSetChanged();
//        CartAdapter cartAdapter =new CartAdapter(cartItemModelList);
//        cartItemRecyclerView.setAdapter(cartAdapter);
//        cartAdapter.notifyDataSetChanged();
        return view;
    }

}