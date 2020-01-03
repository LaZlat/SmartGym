package com.example.freesmartgym.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.freesmartgym.R;
import com.example.freesmartgym.Model.User;
import com.example.freesmartgym.View.HomeFragment;
import com.example.freesmartgym.View.PersonalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecActivity extends AppCompatActivity {

    private User theUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

//        Intent intent = new Intent();
        theUser = getIntent().getParcelableExtra("User_Info");


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListiner);

        getSupportFragmentManager().beginTransaction().replace(R.id.secActivityFragmentContainer, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListiner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    Bundle bundle = new Bundle();

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_personal:
                            bundle.putParcelable("User_Info",theUser);
                            selectedFragment = new PersonalFragment();
                            selectedFragment.setArguments(bundle);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.secActivityFragmentContainer,
                            selectedFragment).commit();
                    return true;
                }
            };

}
