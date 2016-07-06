package com.cblue.processservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pavel on 2016/6/23.
 */
public class Person implements Parcelable {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //对当前内容的描述（不用）
    @Override
    public int describeContents() {
        return 0;
    }

    //把java对象写成parcel
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeInt(age);

    }

    public Person() {
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public static  final Creator<Person> CREATOR  = new Creator<Person>() {
       //从parcle转化成java对象
        @Override
        public Person createFromParcel(Parcel parcel) {
           String  name =  parcel.readString();
            int age = parcel.readInt();
            return new Person(name,age);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[0];
        }
    };
}
