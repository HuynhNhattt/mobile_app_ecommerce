package com.example.proj_ecom_mobile.database;

import android.util.Log;
import com.example.proj_ecom_mobile.model.Product;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class Seeder {


    public static void seedProductData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Product> products = new ArrayList<>();



        // NHÓM ÁO
        products.add(new Product("p001", "Áo Sơ Mi Poplin Tay Ngắn", 650000, "Chất liệu vải Poplin thoáng mát, phom dáng rộng rãi thoải mái cho ngày hè.", "https://via.placeholder.com/300?text=Ao+So+Mi+Trang", "Ao"));
        products.add(new Product("p002", "Áo Sơ Mi Đỏ Thẫm Relaxed", 650000, "Thiết kế tối giản với tông màu đỏ thẫm sang trọng, phù hợp đi làm và đi chơi.", "https://via.placeholder.com/300?text=Ao+So+Mi+Do", "Ao"));
        products.add(new Product("p017", "Áo Len Dệt Kim Khóa Kéo", 950000, "Áo khoác len nhẹ có khóa kéo tiện lợi, phong cách hiện đại.", "https://via.placeholder.com/300?text=Ao+Len+Zip", "Ao"));
        products.add(new Product("p019", "Áo Thun Cổ Lọ Cotton", 890000, "Chất liệu Cotton mềm mại, giữ ấm tốt, dễ phối với áo khoác.", "https://via.placeholder.com/300?text=Ao+Co+Lo", "Ao"));
        products.add(new Product("p021", "Áo Polo Dệt Kim", 790000, "Áo Polo phom dáng suông, chất liệu dệt kim cao cấp thoáng khí.", "https://via.placeholder.com/300?text=Ao+Polo", "Ao"));

        // NHÓM QUẦN
        products.add(new Product("p003", "Quần Tây Ống Suông Nâu", 850000, "Quần âu ống suông lịch lãm, chất liệu vải pha len mềm rủ.", "https://cdn.hstatic.net/products/200000911315/27_aa8f079a0ee34c04b3f287f0fb9ebc55.jpg", "Quan"));
        products.add(new Product("p004", "Quần Xếp Ly Ống Rộng", 890000, "Thiết kế xếp ly tinh tế, tạo độ phồng tự nhiên và thoải mái khi di chuyển.", "https://via.placeholder.com/300?text=Quan+Xep+Ly", "Quan"));
        products.add(new Product("p006", "Quần Jeans Ống Đứng", 890000, "Quần Jeans màu xanh cổ điển, phom đứng tôn dáng.", "https://via.placeholder.com/300?text=Quan+Jeans", "Quan"));
        products.add(new Product("p010", "Quần Short Bermuda", 750000, "Quần short dài ngang gối, phong cách trẻ trung năng động.", "https://via.placeholder.com/300?text=Quan+Short", "Quan"));
        products.add(new Product("p015", "Quần Jeans Màu Kem", 890000, "Màu kem nhã nhặn, dễ dàng phối với các loại áo tối màu.", "https://via.placeholder.com/300?text=Jeans+Kem", "Quan"));

        // NHÓM ÁO KHOÁC
        products.add(new Product("p005", "Áo Khoác Dạ Dáng Rộng", 980000, "Áo khoác dạ phom rộng thời thượng, giữ ấm tuyệt đối.", "https://via.placeholder.com/300?text=Ao+Khoac+Da", "AoKhoac"));
        products.add(new Product("p007", "Áo Mang-to Hai Hàng Khuy", 3890000, "Áo choàng dài cổ điển, điểm nhấn sang trọng cho mùa đông.", "https://via.placeholder.com/300?text=Mang+To", "AoKhoac"));
        products.add(new Product("p009", "Áo Khoác An Nam", 1090000, "Lấy cảm hứng từ trang phục truyền thống, cách tân hiện đại.", "https://via.placeholder.com/300?text=Ao+Khoac+An+Nam", "AoKhoac"));
        products.add(new Product("p013", "Áo Khoác Lửng Cổ Da Lộn", 980000, "Dáng áo ngắn (cropped) giúp 'hack' chiều cao hiệu quả.", "https://via.placeholder.com/300?text=Ao+Khoac+Lung", "AoKhoac"));
        products.add(new Product("p014", "Áo Khoác Denim Workwear", 980000, "Chất liệu Denim bền bỉ, màu xanh Cerulean lạ mắt.", "https://via.placeholder.com/300?text=Ao+Khoac+Jean", "AoKhoac"));
        products.add(new Product("p018", "Áo Blazer Oversized Nâu", 3490000, "Blazer phom rộng phóng khoáng, phong cách Hàn Quốc.", "https://via.placeholder.com/300?text=Blazer+Nau", "AoKhoac"));
        products.add(new Product("p020", "Áo Khoác Dù Có Mũ", 2450000, "Chất liệu Nylon chống thấm nước nhẹ, tiện lợi khi đi mưa phùn.", "https://via.placeholder.com/300?text=Ao+Khoac+Du", "AoKhoac"));

        // NHÓM PHỤ KIỆN
        products.add(new Product("p011", "Túi Tote Da Lộn", 890000, "Túi xách cỡ lớn đựng vừa laptop, chất liệu da lộn mềm.", "https://via.placeholder.com/300?text=Tui+Tote", "PhuKien"));
        products.add(new Product("p012", "Ví Đựng Thẻ Da Bò", 250000, "Nhỏ gọn, tiện lợi, làm từ da bò thật màu đỏ rượu.", "https://via.placeholder.com/300?text=Vi+Da", "PhuKien"));
        products.add(new Product("p016", "Thắt Lưng Da Cổ Điển", 650000, "Phụ kiện không thể thiếu cho quý ông, khóa kim loại không gỉ.", "https://via.placeholder.com/300?text=That+Lung", "PhuKien"));

        // Vòng lặp đẩy lên Firebase
        for (Product p : products) {
            db.collection("products").document(p.getId()).set(p)
                    .addOnSuccessListener(aVoid -> Log.d("Seeder", "Đã thêm: " + p.getName()))
                    .addOnFailureListener(e -> Log.e("Seeder", "Lỗi: " + e.getMessage()));
        }
    }
}