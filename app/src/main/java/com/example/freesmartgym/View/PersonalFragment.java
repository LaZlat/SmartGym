package com.example.freesmartgym.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.freesmartgym.CommWithDatabase;
import com.example.freesmartgym.Controller.Calculation;
import com.example.freesmartgym.Controller.UserCommWithDatabase;
import com.example.freesmartgym.R;
import com.example.freesmartgym.Model.User;

public class PersonalFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "PersonalFragment";

    private User theUser;
    private Spinner personalSpinner;
    private SecActivity activity;
    private TextView personalNameEditText, calcPerDayTextView, bmiScore, calcPerWeekTextView, personalHeightEditText, personalWeightEditText, personalAgeEditText, personalFatPercentEditText, aboutTextView;
    private Switch personalSexSwitch;
    private ConstraintLayout calculationConst;
    private Button calcButton;

    private CommWithDatabase commWithDatabase;
    private UserCommWithDatabase userCommWithDatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sec_activity_personal,container,false);

        theUser = getArguments().getParcelable("User_Info");

        personalSpinner = view.findViewById(R.id.personalSpinner);
        populateSpinner();

        personalNameEditText = view.findViewById(R.id.personalNameEditText);
        personalHeightEditText = view.findViewById(R.id.personalHeightEditText);
        personalWeightEditText = view.findViewById(R.id.personalWeightEditText);
        personalAgeEditText = view.findViewById(R.id.personalAgeEditText);
        personalFatPercentEditText = view.findViewById(R.id.personalFatPercentEditText);
        aboutTextView = view.findViewById(R.id.aboutTextView);
        personalSexSwitch = view.findViewById(R.id.personalSexSwitch);
        calculationConst = view.findViewById(R.id.calculationConstraint);
        calcPerDayTextView = view.findViewById(R.id.calcPerDayTextView);
        calcButton = view.findViewById(R.id.calcButton);
        calcPerWeekTextView = view.findViewById(R.id.calcPerWeeTextView);
        bmiScore = view.findViewById(R.id.bmiScore);

        fillUserInformation();
        focusChange();
        calculate();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            personalNameEditText.setText(savedInstanceState.getString("name"));
            personalAgeEditText.setText(savedInstanceState.getString("age"));
            personalHeightEditText.setText(savedInstanceState.getString("height"));
            personalWeightEditText.setText(savedInstanceState.getString("weight"));
            personalFatPercentEditText.setText(savedInstanceState.getString("fat"));
        }
    }

    private boolean checkUserFields(){
        if(TextUtils.isEmpty(personalNameEditText.getText().toString())){
            personalNameEditText.setError("Field can't be empty");
            return false;
        }
        else if(TextUtils.isEmpty(personalHeightEditText.getText().toString())){
            personalHeightEditText.setError("Field can't be empty");
            return false;
        }
        else if(TextUtils.isEmpty(personalWeightEditText.getText().toString())){
            personalHeightEditText.setError("Field can't be empty");
            return false;
        }
        else if(TextUtils.isEmpty(personalAgeEditText.getText().toString())){
            personalAgeEditText.setError("Field can't be empty");
            return false;
         }
        else if(TextUtils.isEmpty(personalFatPercentEditText.getText().toString())){
            personalFatPercentEditText.setError("Field can't be empty");
            return false;
        }
        return true;

    }

    private void fillUserInformation(){
        personalNameEditText.setText(theUser.getFirstName());
        personalHeightEditText.setText(String.valueOf(theUser.getHeight()));
        personalWeightEditText.setText(String.valueOf(theUser.getWeight()));
        personalAgeEditText.setText(String.valueOf(theUser.getAge()));
        personalFatPercentEditText.setText(String.valueOf(theUser.getFatPercent()));
        aboutTextView.setText("About, " + theUser.getFirstName());
    }

    private void focusChange(){

        userCommWithDatabase = new UserCommWithDatabase();
        commWithDatabase = userCommWithDatabase;

        personalNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hidekeyboard(v);
                    if(checkUserFields()){
                        theUser = new User(personalNameEditText.getText().toString(),Integer.valueOf(personalHeightEditText.getText().toString()),Integer.valueOf(personalWeightEditText.getText().toString()),
                        Integer.valueOf(personalAgeEditText.getText().toString()),Integer.valueOf(personalFatPercentEditText.getText().toString()));
                        commWithDatabase.writeUserToDatabase(theUser);
                    }
                }
            }
        });
        personalWeightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hidekeyboard(v);
                    if(checkUserFields()){
                        theUser = new User(personalNameEditText.getText().toString(),Integer.valueOf(personalHeightEditText.getText().toString()),Integer.valueOf(personalWeightEditText.getText().toString()),
                                Integer.valueOf(personalAgeEditText.getText().toString()),Integer.valueOf(personalFatPercentEditText.getText().toString()));
                        commWithDatabase.writeUserToDatabase(theUser);
                    }
                }
            }
        });
        personalHeightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hidekeyboard(v);
                }
            }
        });
        personalAgeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hidekeyboard(v);
                    if(checkUserFields()){
                        theUser = new User(personalNameEditText.getText().toString(),Integer.valueOf(personalHeightEditText.getText().toString()),Integer.valueOf(personalWeightEditText.getText().toString()),
                                Integer.valueOf(personalAgeEditText.getText().toString()),Integer.valueOf(personalFatPercentEditText.getText().toString()));
                        commWithDatabase.writeUserToDatabase(theUser);
                    }
                }
            }
        });
        personalFatPercentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hidekeyboard(v);
                    if(checkUserFields()){
                        theUser = new User(personalNameEditText.getText().toString(),Integer.valueOf(personalHeightEditText.getText().toString()),Integer.valueOf(personalWeightEditText.getText().toString()),
                                Integer.valueOf(personalAgeEditText.getText().toString()),Integer.valueOf(personalFatPercentEditText.getText().toString()));
                        commWithDatabase.writeUserToDatabase(theUser);
                    }
                }
            }
        });
    }

    private void hidekeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (NullPointerException e){
            Log.e(TAG,e.toString());
        }
    }

    private void calculate(){
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculation calc = new Calculation();

                int spinnerSelectionNumber = personalSpinner.getSelectedItemPosition();
                int kcal = calc.neededKcal(theUser,spinnerSelectionNumber);
                calculationConst.setVisibility(View.VISIBLE);
                calcPerDayTextView.setText(String.valueOf(kcal));
                calcPerWeekTextView.setText(String.valueOf(calc.perWeek(kcal)));

                bmiScore.setText(String.valueOf(calc.basicBmi(theUser)));

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void populateSpinner(){
        try {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.activities, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            personalSpinner.setAdapter(adapter);
            personalSpinner.setOnItemSelectedListener(this);
        } catch (Exception e){
            Log.e(TAG,"Spinner population Error");
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            if(context instanceof Activity){
                activity = (SecActivity) context;
            }
        } catch (Exception e){
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", personalNameEditText.getText().toString());
        outState.putString("height", personalHeightEditText.getText().toString());
        outState.putString("weight", personalWeightEditText.getText().toString());
        outState.putString("age", personalAgeEditText.getText().toString());
        outState.putString("fat", personalFatPercentEditText.getText().toString());
    }

    //    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        personalSpinner = getView().findViewById(R.id.personalSpinner);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        personalSpinner = null;
//    }
}
