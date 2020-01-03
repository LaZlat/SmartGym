package com.example.freesmartgym;

import com.example.freesmartgym.Model.User;

public interface CommWithDatabase {
    void writeUserToDatabase(User theUser);
    User getUserFromDatabase();
}
