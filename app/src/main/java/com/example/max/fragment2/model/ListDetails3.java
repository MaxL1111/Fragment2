package com.example.max.fragment2.model;


import android.app.Application;

import java.util.ArrayList;


public class ListDetails3 extends Application {

    //static String tableName;

    public static ArrayList<Model3> getList(ArrayList<Integer> list_id , ArrayList<String> list_nameft, ArrayList<String> list_tableNames) {
        ArrayList<Model3> categoriesList = new ArrayList<>();

        for (int i=0; i<list_id.size(); i++){
            categoriesList.add(new Model3(list_id.get(i),list_nameft.get(i), list_tableNames.get(i)));
        }

        return categoriesList;
    }
}