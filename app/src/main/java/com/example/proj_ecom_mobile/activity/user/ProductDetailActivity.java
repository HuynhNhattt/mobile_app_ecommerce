package com.example.proj_ecom_mobile.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.proj_ecom_mobile.R;
import com.example.proj_ecom_mobile.database.SQLHelper;
import com.example.proj_ecom_mobile.model.CartItem;
import com.example.proj_ecom_mobile.model.Product;
import java.text.DecimalFormat;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView imgDetail, btnBack;
    private TextView txtName, txtPrice, txtDesc, txtStock;
    private TextView btnS, btnM, btnL, btnXL;
    private Button btnAddToCart;
    private Product product;
    private SQLHelper sqlHelper;
    private String selectedSize = null;
    private int currentStockForSize = 0; // Biến lưu tồn kho của size đang chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        sqlHelper = new SQLHelper(this);
        initView();

        btnS.setOnClickListener(v -> selectSize("S"));
        btnM.setOnClickListener(v -> selectSize("M"));
        btnL.setOnClickListener(v -> selectSize("L"));
        btnXL.setOnClickListener(v -> selectSize("XL"));

        if (getIntent().hasExtra("product_item")) {
            product = (Product) getIntent().getSerializableExtra("product_item");
            if (product != null) {
                displayProductInfo();
            }
        }

        btnBack.setOnClickListener(v -> finish());

        btnAddToCart.setOnClickListener(v -> {
            if (selectedSize == null) {
                Toast.makeText(this, "Vui lòng chọn Size!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (currentStockForSize <= 0) {
                Toast.makeText(this, "Size " + selectedSize + " đã hết hàng!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra số lượng đã có trong giỏ của size cụ thể này
            int currentInCart = sqlHelper.getTotalQuantityForProductAndSize(product.getId(), selectedSize);
            if (currentInCart + 1 > currentStockForSize) {
                Toast.makeText(this, "Quá số lượng tồn kho (Max: " + currentStockForSize + ")", Toast.LENGTH_SHORT).show();
                return;
            }

            CartItem item = new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl(),
                    1,
                    selectedSize,
                    currentStockForSize // Lưu max stock của size này vào item để validate trong giỏ
            );
            sqlHelper.addToCart(item);
            Toast.makeText(this, "Đã thêm vào giỏ: Size " + selectedSize, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        imgDetail = findViewById(R.id.img_detail);
        btnBack = findViewById(R.id.btn_back);
        txtName = findViewById(R.id.txt_detail_name);
        txtPrice = findViewById(R.id.txt_detail_price);
        txtDesc = findViewById(R.id.txt_detail_desc);
        txtStock = findViewById(R.id.txt_detail_stock);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);

        btnS = findViewById(R.id.size_s);
        btnM = findViewById(R.id.size_m);
        btnL = findViewById(R.id.size_l);
        btnXL = findViewById(R.id.size_xl);
    }

    private void displayProductInfo() {
        txtName.setText(product.getName());
        txtDesc.setText(product.getDescription());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        txtPrice.setText(formatter.format(product.getPrice()) + "đ");
        Glide.with(this).load(product.getImageUrl()).into(imgDetail);

        txtStock.setText("Vui lòng chọn size để xem tồn kho");
    }

    private void selectSize(String size) {
        selectedSize = size;
        resetSizeButton(btnS);
        resetSizeButton(btnM);
        resetSizeButton(btnL);
        resetSizeButton(btnXL);

        TextView selectedBtn = null;

        // Cập nhật tồn kho theo size
        if (size.equals("S")) {
            selectedBtn = btnS;
            currentStockForSize = product.getStockS();
        } else if (size.equals("M")) {
            selectedBtn = btnM;
            currentStockForSize = product.getStockM();
        } else if (size.equals("L")) {
            selectedBtn = btnL;
            currentStockForSize = product.getStockL();
        } else if (size.equals("XL")) {
            selectedBtn = btnXL;
            currentStockForSize = product.getStockXL();
        }

        if (selectedBtn != null) {
            selectedBtn.setBackgroundResource(R.drawable.bg_size_selected);
            selectedBtn.setTextColor(Color.WHITE);
        }

        // Cập nhật giao diện
        if (currentStockForSize > 0) {
            txtStock.setText("Kho: " + currentStockForSize);
            txtStock.setTextColor(Color.DKGRAY);
            btnAddToCart.setEnabled(true);
            btnAddToCart.setText("Thêm vào giỏ hàng");
            btnAddToCart.setBackgroundTintList(getResources().getColorStateList(R.color.black));
        } else {
            txtStock.setText("Hết hàng");
            txtStock.setTextColor(Color.RED);
            btnAddToCart.setEnabled(false);
            btnAddToCart.setText("Hết hàng");
            btnAddToCart.setBackgroundTintList(getResources().getColorStateList(android.R.color.darker_gray));
        }
    }

    private void resetSizeButton(TextView btn) {
        btn.setBackgroundResource(R.drawable.bg_size_unselected);
        btn.setTextColor(Color.BLACK);
    }
}