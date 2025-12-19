package com.example.proj_ecom_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.proj_ecom_mobile.R;
import com.example.proj_ecom_mobile.model.Product;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ArrayList<Product> productList;

    // Constructor để nhận dữ liệu từ bên ngoài truyền vào
    public ProductAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gọi layout item_product.xml đã tạo ở bước Res
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // 1. Gán tên sản phẩm
        holder.txtName.setText(product.getName());

        // 2. Format giá tiền kiểu Việt Nam (ví dụ: 1,000,000đ)
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtPrice.setText(formatter.format(product.getPrice()) + "đ");

        // 3. Load ảnh từ Internet bằng Glide
        // Nếu ảnh bị lỗi hoặc chưa load xong, nó sẽ hiện ảnh mặc định (placeholder)
        Glide.with(context)
                .load(product.getImageUrl())
                .placeholder(R.drawable.ic_home) // Hình chờ
                .error(R.drawable.ic_home)       // Hình lỗi
                .into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Class nắm giữ các thành phần giao diện (Ánh xạ ID)
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice;
        ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.item_name);
            txtPrice = itemView.findViewById(R.id.item_price);
            imgProduct = itemView.findViewById(R.id.item_image);
        }
    }
}