package com.example.freesmartgym.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.freesmartgym.CommWithDatabase;
import com.example.freesmartgym.R;
import com.example.freesmartgym.Model.User;
import com.example.freesmartgym.Controller.UserCommWithDatabase;

import java.util.logging.Logger;




public class MainActivity extends AppCompatActivity implements MainActivityLogInFragment.OnFragmentInteractionListener {

    private static final Logger LOGGER = Logger.getLogger( MainActivity.class.getName() );
    SharedPreferences prefs = null;

    private TextView diveInTextView;
    private FrameLayout fragmentContainer;
    private User theUser;
    private CommWithDatabase commWithDatabase;
    private UserCommWithDatabase userCommWithDatabase;


    private boolean firstScreenTouch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        diveInTextView = (TextView) findViewById(R.id.diveInTextView);

        prefs = getSharedPreferences("com.example.smartgym", MODE_PRIVATE);

        if(savedInstanceState != null){
            firstScreenTouch = savedInstanceState.getBoolean("first");
        }

    }



    public void toTheNextActivity(){
        Intent intent = new Intent(this, SecActivity.class);
        intent.putExtra("User_Info",theUser);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (prefs.getBoolean("first_run", true)) {
            prefs.edit().putBoolean("first_run",false).commit();
            firstScreenTouch = false;
            diveInTextView.setVisibility(View.INVISIBLE);

            MainActivityLogInFragment fragment = MainActivityLogInFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_container,fragment, "LOG_IN_FRAGMENT").commit();

        }
        else if(firstScreenTouch == true){
            userCommWithDatabase = new UserCommWithDatabase();
            commWithDatabase = userCommWithDatabase;
            theUser = commWithDatabase.getUserFromDatabase();
            toTheNextActivity();
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onFragmentInteraction(User theUser) {
        this.theUser = theUser;
        onBackPressed();
        diveInTextView.setVisibility(View.VISIBLE);

        firstScreenTouch = true;

        userCommWithDatabase = new UserCommWithDatabase();
        commWithDatabase = userCommWithDatabase;
        commWithDatabase.writeUserToDatabase(theUser);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
         outState.putBoolean("first", firstScreenTouch);
    }
}
