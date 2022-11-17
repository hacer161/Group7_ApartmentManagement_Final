package vn.edu.spx.group7_apartmentmanagement_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import vn.edu.spx.group7_apartmentmanagement_final.Fragment.BillFragment;
import vn.edu.spx.group7_apartmentmanagement_final.Fragment.HomeFragment;
import vn.edu.spx.group7_apartmentmanagement_final.Fragment.RoomFragment;

public class MainActivity extends AppCompatActivity {
    BillFragment billFragment=new BillFragment();
    RoomFragment roomFragment=new RoomFragment();
    HomeFragment homeFragment=new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.rooms:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,roomFragment).commit();
                        return true;
                    case R.id.bills:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,billFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

}