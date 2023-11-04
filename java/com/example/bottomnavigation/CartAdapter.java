package com.example.bottomnavigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {


    private List<CartItemModel> cartItemModelList ;
    private TextView cartTotalAmount;
    public CartAdapter(List<CartItemModel> cartItemModelList,TextView cartTotalAmount) {
        this.cartItemModelList = cartItemModelList;
        this.cartTotalAmount=cartTotalAmount;

    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){
            case 0:
                 return  CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout,viewGroup,false);
                return new cartItemViewholder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_total_amount_layout,viewGroup,false);
                return new cartTotalAmountViewholder(cartTotalView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
         switch (cartItemModelList.get(position).getType()){
             case  CartItemModel.CART_ITEM:
                 String productID=cartItemModelList.get(position).getProductID();
                 String resource =cartItemModelList.get(position).getProductImage();
                 String title =cartItemModelList.get(position).getProductTitle();
                 String productPrice =cartItemModelList.get(position).getProductPrice();
                 String cuttedPrice =cartItemModelList.get(position).getCuttedPrice();
                 ((cartItemViewholder)viewHolder ).setItemDetails(productID,resource,title,productPrice,cuttedPrice,position);
                 break;
             case CartItemModel.TOTAL_AMOUNT:
                 int totalItems=0;
                 int totalItemPrice=0;
                 String deliveryPrice ;
                 int savedAmount=0;
                 int totalAmount;

                 for(int x=0;x<cartItemModelList.size();x++){
                     if(cartItemModelList.get(x).getType()==CartItemModel.CART_ITEM){
                         totalItems++;
                         totalItemPrice=totalItemPrice +Integer.parseInt(cartItemModelList.get(x).getProductPrice());
                     }
                 }

                 if(totalItemPrice > 500){
                     deliveryPrice="FREE";
                     totalAmount=totalItemPrice;
                 }else {
                     deliveryPrice="60";
                     totalAmount=totalItemPrice+60;
                 }



                 ((cartTotalAmountViewholder)viewHolder).setTotalAmount(totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount);

                 break;
             default:
                 return;
         }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class cartItemViewholder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView productQuantity;
        private LinearLayout deleteBtn;
        public cartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage =itemView.findViewById(R.id.product_image1);
            productTitle =itemView.findViewById(R.id.product_title1);
            productPrice=itemView.findViewById(R.id.product_price);
            cuttedPrice=itemView.findViewById(R.id.cutted_price1);
            productQuantity =itemView.findViewById(R.id.product_quantity);
            deleteBtn=itemView.findViewById(R.id.remove_item_btn);

        }
        private void setItemDetails(String productID,String resource,String title,String productPriceText,String cuttedPriceText,int position){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)).into(productImage);
            productTitle.setText(title);
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);

             deleteBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if(!productDetailsActivity.running_cart_query){
                         productDetailsActivity.running_cart_query=true;

                         EQuires.removeFromCart(position, itemView.getContext());
                     }
                 }
             });
        }


    }

      class cartTotalAmountViewholder extends RecyclerView.ViewHolder{
           private TextView totalItems;
           private TextView totalItemPrice;
           private TextView deliveryPrice;
           private TextView totalAmount;
           private TextView savedAmount;


          public cartTotalAmountViewholder(@NonNull View itemView) {
              super(itemView);

              totalItems =itemView.findViewById(R.id.total_items);
              totalItemPrice=itemView.findViewById(R.id.total_items_price);
              deliveryPrice=itemView.findViewById(R.id.delivery_price);
              totalAmount =itemView.findViewById(R.id.total_price);
              savedAmount =itemView.findViewById(R.id.saved_amount);
          }
          private void setTotalAmount(int totalItemText,int totalItemPriceText,String deliveryPriceText,int totalAmountText,int savedAmountText){
              totalItems.setText("Price("+totalItemText+" items");
              totalItemPrice.setText("Rs."+totalItemPriceText+"/-");
              if(deliveryPriceText.equals("FREE")){
                  deliveryPrice.setText(deliveryPriceText);
              }else {
                  deliveryPrice.setText("Rs."+deliveryPriceText+"/-");
              }
              totalAmount.setText("Rs."+totalAmountText+"/-");
              cartTotalAmount.setText("Rs."+totalAmountText+"/-");
              savedAmount.setText(" You saved Rs."+savedAmountText+"/- on this order");
          }
      }
}
