package com.example.freesmartgym.Controller;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.freesmartgym.CommWithDatabase;
import com.example.freesmartgym.FreeSmartGym;
import com.example.freesmartgym.Model.User;

public class DatabaseHelper extends SQLiteOpenHelper implements CommWithDatabase {
    public DatabaseHelper(){
        super(FreeSmartGym.getAppContext(),"smartgym.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + "user_table" + "(id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, height INTEGER, weight INTEGER, age INTEGER, fatpercent INTEGER, sex INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "user_table");
        onCreate(db);
    }

    @Override
    public void writeUserToDatabase(User theUser) {


        try{
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "INSERT INTO user_table (firstname, height, weight, age, fatpercent, sex) VALUES (?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, theUser.getFirstName());
            statement.bindLong(2, (int)theUser.getHeight());
            statement.bindLong(3,theUser.getWeight());
            statement.bindLong(4, theUser.getAge());
            statement.bindLong(5,theUser.getFatPercent());
            statement.bindLong(6, theUser.getSex());

            long rowID = statement.executeInsert();
            db.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getUserFromDatabase() {
        User theUser;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("SELECT firstname, height, weight, age, fatpercent, sex FROM user_table", null);
            res.moveToFirst();
            if(res.isFirst()) {
                theUser = new User(res.getString(0), res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4), res.getInt(5));
                db.close();
                return theUser;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
