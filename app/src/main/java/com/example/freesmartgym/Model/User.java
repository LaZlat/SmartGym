package com.example.freesmartgym.Model;

import android.os.Parcel;
import android.os.Parcelable;

public  class User implements Parcelable {
    private String firstName;
    private int height;
    private int weight;
    private int age;
    private int fatPercent = 0;

    public User(String firstName, int height, int weight, int age, int fatPercent) {
        this.firstName = firstName;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.fatPercent = fatPercent;
    }

    public User(String firstName, int height, int weight, int age) {
        this.firstName = firstName;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public User() {
    }

    protected User(Parcel in) {
        firstName = in.readString();
        height = in.readInt();
        weight = in.readInt();
        age = in.readInt();
        fatPercent = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(int fatPercent) {
        this.fatPercent = fatPercent;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeInt(height);
        dest.writeInt(weight);
        dest.writeInt(age);
        dest.writeInt(fatPercent);
    }
}
