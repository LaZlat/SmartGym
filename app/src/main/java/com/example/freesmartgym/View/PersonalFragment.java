package com.example.freesmartgym.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freesmartgym.R;
import com.example.freesmartgym.Model.User;

public class PersonalFragment extends Fragment {

    private User theUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        theUser = getArguments().getParcelable("User_Info");
        return inflater.inflate(R.layout.fragment_sec_activity_personal,container,false);
    }
}
