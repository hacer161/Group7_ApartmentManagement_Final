package vn.edu.spx.group7_apartmentmanagement_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import vn.edu.spx.group7_apartmentmanagement_final.Fragment.BillFragment;
import vn.edu.spx.group7_apartmentmanagement_final.Fragment.ContractFragment;
import vn.edu.spx.group7_apartmentmanagement_final.Fragment.HomeFragment;
import vn.edu.spx.group7_apartmentmanagement_final.Fragment.RoomFragment;
import vn.edu.spx.group7_apartmentmanagement_final.Fragment.TenantFragment;

public class MainActivity extends AppCompatActivity {
    BillFragment billFragment=new BillFragment();
    RoomFragment roomFragment=new RoomFragment();
    HomeFragment homeFragment=new HomeFragment();
    TenantFragment tenantFragment=new TenantFragment();
    ContractFragment contractFragment=new ContractFragment();

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
                    case R.id.contracts:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,contractFragment).commit();
                        return true;
                    case R.id.tenants:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,tenantFragment).commit();
                        return true;
                    case R.id.rooms:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,roomFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

}