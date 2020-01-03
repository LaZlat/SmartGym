package com.example.freesmartgym.View;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.freesmartgym.R;
import com.example.freesmartgym.Model.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainActivityLogInFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainActivityLogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainActivityLogInFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private EditText nameEditText, heightEditText, weightEditText, ageEditText, fatPercentEditText;
    private Button nextButton;
    private User theUser;

    public MainActivityLogInFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MainActivityLogInFragment newInstance() {
        MainActivityLogInFragment fragment = new MainActivityLogInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            nameEditText.setText(savedInstanceState.getString("name"));
            heightEditText.setText(savedInstanceState.getString("height"));
            weightEditText.setText(savedInstanceState.getString("weight"));
            ageEditText.setText(savedInstanceState.getString("age"));
            fatPercentEditText.setText(savedInstanceState.getString("fat"));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity_log_in, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        heightEditText = view.findViewById(R.id.heightEditText);
        weightEditText = view.findViewById(R.id.weightEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        fatPercentEditText = view.findViewById(R.id.fatPercentEditText);
        nextButton = view.findViewById(R.id.nextButton);
        nameEditText.requestFocus();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(nameEditText.getText())) {
                    nameEditText.setError("There was an error with your input");
                } else if (TextUtils.isEmpty(heightEditText.getText())) {
                    heightEditText.setError("There was an error with your input");
                } else if (TextUtils.isEmpty(weightEditText.getText())) {
                    weightEditText.setError("There was an error with your input");
                } else if(TextUtils.isEmpty(nameEditText.getText())){
                    ageEditText.setError("There was an error with your input");
                } else{
                    if(fatPercentEditText.getText().toString().trim().length() == 0){
                        theUser = new User(nameEditText.getText().toString().trim(), Integer.valueOf(heightEditText.getText().toString()),Integer.valueOf(weightEditText.getText().toString()), Integer.valueOf(ageEditText.getText().toString()));
                        sendBack(theUser);
                    } else{
                        theUser = new User(nameEditText.getText().toString().trim(), Integer.parseInt(heightEditText.getText().toString().trim()),Integer.parseInt(weightEditText.getText().toString().trim()), Integer.valueOf(ageEditText.getText().toString()), Integer.parseInt(fatPercentEditText.getText().toString().trim()));
                        sendBack(theUser);
                    }
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void sendBack(User theUser) {
        if (mListener != null) {
            mListener.onFragmentInteraction(theUser);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(User theUser);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", nameEditText.getText().toString());
        outState.putString("height", heightEditText.getText().toString());
        outState.putString("weight", weightEditText.getText().toString());
        outState.putString("age", ageEditText.getText().toString());
        outState.putString("fat", fatPercentEditText.getText().toString());
    }
}
