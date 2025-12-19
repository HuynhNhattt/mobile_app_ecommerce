package com.example.proj_ecom_mobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proj_ecom_mobile.R;
import com.example.proj_ecom_mobile.adapter.ProductAdapter;
import com.example.proj_ecom_mobile.model.Product;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> productList;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 1. Ánh xạ RecyclerView
        recyclerView = view.findViewById(R.id.recycler_products);

        // 2. Cấu hình hiển thị dạng lưới (Grid) 2 cột
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // 3. Khởi tạo danh sách và Adapter
        productList = new ArrayList<>();
        adapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(adapter);

        // 4. Gọi hàm tải dữ liệu
        loadProductsFromFirebase();

        return view;
    }

    private void loadProductsFromFirebase() {
        db = FirebaseFirestore.getInstance();

        // Lấy toàn bộ dữ liệu trong collection "products"
        db.collection("products")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        productList.clear(); // Xóa list cũ để tránh trùng lặp
                        for (DocumentSnapshot d : queryDocumentSnapshots) {
                            // Chuyển đổi dữ liệu JSON từ Firebase thành Object Product
                            Product p = d.toObject(Product.class);
                            if (p != null) {
                                p.setId(d.getId()); // Lưu lại ID của document
                                productList.add(p);
                            }
                        }
                        // Báo cho Adapter biết dữ liệu đã thay đổi để vẽ lại màn hình
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Chưa có sản phẩm nào!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("HomeFragment", "Lỗi tải dữ liệu: " + e.getMessage());
                    Toast.makeText(getContext(), "Lỗi kết nối Server!", Toast.LENGTH_SHORT).show();
                });
    }
}