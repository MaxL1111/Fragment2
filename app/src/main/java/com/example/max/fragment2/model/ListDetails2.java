package com.example.max.fragment2.model;


import android.app.Application;

import java.util.ArrayList;


public class ListDetails2 extends Application {

    //static String tableName;

    public static ArrayList<Model2> getList(ArrayList<Integer> list_id , ArrayList<String> list_nameft, String tableName) {
        ArrayList<Model2> categoriesList = new ArrayList<>();

        for (int i=0; i<list_id.size(); i++){
            categoriesList.add(new Model2(list_id.get(i),list_nameft.get(i), tableName));
        }

        return categoriesList;
    }
}