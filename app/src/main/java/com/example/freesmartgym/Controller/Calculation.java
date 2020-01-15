package com.example.freesmartgym.Controller;

import com.example.freesmartgym.Model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calculation {

    public int neededKcal (User theUser, int spinnerSelectedPosition){
        int kcal;

        if(theUser.getSex() == 0){
            kcal = (int) ((10 * theUser.getWeight() + 6.25 * theUser.getHeight() - 5 * theUser.getAge() + 5));
        } else {
            kcal = (int) ((10 * theUser.getWeight() + 6.25 * theUser.getHeight() - 5 * theUser.getAge() - 161));
        }


        if(spinnerSelectedPosition == 0) {
            return kcal;
        }
        else if(spinnerSelectedPosition == 1){
            return (int)(kcal*1.2);
        }
        else if(spinnerSelectedPosition == 2){
            return (int)(kcal*1.375);
        }
        else if(spinnerSelectedPosition == 3){
            return (int)(kcal*1.55);
        }
        else if(spinnerSelectedPosition == 4){
            return (int)(kcal*1.725);
        }
        else{
            return (int)(kcal*1.9);
        }
    }

    public int perWeek(int kcal){
        return kcal*7;
    }

    public double basicBmi(User theUser){
        double bmi = theUser.getWeight() / ((theUser.getHeight()/100)*(theUser.getHeight()/100));

        BigDecimal bd = new BigDecimal(bmi).setScale(2, RoundingMode.HALF_UP);

        double finalBbmi = bd.doubleValue();

        return finalBbmi;
    }
}
