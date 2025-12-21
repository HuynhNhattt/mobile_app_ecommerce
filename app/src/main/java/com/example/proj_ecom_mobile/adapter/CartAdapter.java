package com.example.proj_ecom_mobile.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.proj_ecom_mobile.R;
import com.example.proj_ecom_mobile.activity.user.CartActivity;
import com.example.proj_ecom_mobile.database.SQLHelper;
import com.example.proj_ecom_mobile.model.CartItem;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private ArrayList<CartItem> cartList;
    private SQLHelper sqlHelper;

    public CartAdapter(Context context, ArrayList<CartItem> cartList) {
        this.context = context;
        this.cartList = cartList;
        this.sqlHelper = new SQLHelper(context);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartList.get(position);

        holder.txtName.setText(item.getProductName());
        holder.txtSize.setText("Size: " + item.getSize());
        holder.txtQty.setText(String.valueOf(item.getQuantity()));

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtPrice.setText(formatter.format(item.getProductPrice()) + "đ");

        Glide.with(context)
                .load(item.getProductImage())
                .placeholder(R.drawable.ic_home)
                .into(holder.imgProduct);

        holder.btnMinus.setOnClickListener(v -> {
            int currentQty = item.getQuantity();
            if (currentQty > 1) {
                int newQty = currentQty - 1;
                item.setQuantity(newQty);
                holder.txtQty.setText(String.valueOf(newQty));
                sqlHelper.updateQuantity(item.getProductId(), item.getSize(), newQty);

                if (context instanceof CartActivity) {
                    ((CartActivity) context).updateTotalPrice();
                }
            } else {
                showDeleteDialog(position, item);
            }
        });

        holder.btnPlus.setOnClickListener(v -> {
            // Kiểm tra tồn kho của chính size này (item.getStock() đã lưu max stock của size đó)
            if (item.getQuantity() < item.getStock()) {
                int newQty = item.getQuantity() + 1;
                item.setQuantity(newQty);
                holder.txtQty.setText(String.valueOf(newQty));
                sqlHelper.updateQuantity(item.getProductId(), item.getSize(), newQty);

                if (context instanceof CartActivity) {
                    ((CartActivity) context).updateTotalPrice();
                }
            } else {
                Toast.makeText(context, "Quá số lượng tồn kho của size " + item.getSize() + "! (Max: " + item.getStock() + ")", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnDelete.setOnClickListener(v -> showDeleteDialog(position, item));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    private void showDeleteDialog(int position, CartItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Xóa sản phẩm")
                .setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    sqlHelper.deleteCartItem(item.getProductId(), item.getSize());
                    cartList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartList.size());

                    if (context instanceof CartActivity) {
                        ((CartActivity) context).updateTotalPrice();
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtQty, txtSize, btnMinus, btnPlus;
        ImageView imgProduct, btnDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.cart_item_name);
            txtPrice = itemView.findViewById(R.id.cart_item_price);
            txtQty = itemView.findViewById(R.id.cart_item_qty);
            txtSize = itemView.findViewById(R.id.cart_item_size);
            imgProduct = itemView.findViewById(R.id.cart_item_img);
            btnDelete = itemView.findViewById(R.id.btn_delete_item);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);
        }
    }
}