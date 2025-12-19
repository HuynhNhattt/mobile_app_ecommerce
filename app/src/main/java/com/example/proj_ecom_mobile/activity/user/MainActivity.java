package com.example.proj_ecom_mobile.activity.user;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.MenuItem;

import com.example.proj_ecom_mobile.R;
import com.example.proj_ecom_mobile.database.Seeder;
import com.example.proj_ecom_mobile.fragment.HomeFragment;
import com.example.proj_ecom_mobile.fragment.ProfileFragment;
import com.example.proj_ecom_mobile.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- QUAN TRỌNG: KÍCH HOẠT DỮ LIỆU ---
        // Dòng này sẽ nạp 20 sản phẩm mẫu vào Firebase ngay khi App chạy.
        Seeder.seedProductData();
        // -------------------------------------

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // Mặc định hiển thị trang chủ (HomeFragment) khi vừa mở App
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new HomeFragment())
                    .commit();
        }

        // Xử lý sự kiện khi bấm vào các nút dưới thanh Menu
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (id == R.id.nav_search) {
                    selectedFragment = new SearchFragment();
                } else if (id == R.id.nav_profile) {
                    selectedFragment = new ProfileFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, selectedFragment)
                            .commit();
                }
                return true;
            }
        });
    }
}