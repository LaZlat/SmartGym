package com.example.freesmartgym.Controller;


import com.example.freesmartgym.CommWithDatabase;
import com.example.freesmartgym.Model.User;

public class UserCommWithDatabase implements CommWithDatabase {
    private DatabaseHelper myDb = new DatabaseHelper();

    @Override
    public void writeUserToDatabase(User theUser) {
        myDb.writeUserToDatabase(theUser);
    }

    @Override
    public User getUserFromDatabase() {
        return myDb.getUserFromDatabase();
    }
}
