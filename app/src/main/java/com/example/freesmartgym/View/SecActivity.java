package com.example.freesmartgym.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.freesmartgym.R;
import com.example.freesmartgym.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecActivity extends AppCompatActivity {

    private User theUser;
    private Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        theUser = getIntent().getParcelableExtra("User_Info");
        selectedFragment = new HomeFragment();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListiner);


        if(savedInstanceState != null){
            selectedFragment = getSupportFragmentManager().getFragment(savedInstanceState,"myFragment");
            getSupportFragmentManager().beginTransaction().replace(R.id.secActivityFragmentContainer,selectedFragment).commit();
        } else{
            getSupportFragmentManager().beginTransaction().replace(R.id.secActivityFragmentContainer, selectedFragment).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListiner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,"myFragment", selectedFragment);
    }
}
