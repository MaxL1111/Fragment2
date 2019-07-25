package com.example.max.fragment2.model;


import android.app.Application;

import com.example.max.fragment2.R;

import java.util.ArrayList;


public class ListDetails extends Application {



    public static ArrayList<Model> getList() {
        ArrayList<Model> categoriesList = new ArrayList<>();

        categoriesList.add(new Model(R.drawable.rus, "Русские сказки", "rusft"));
        categoriesList.add(new Model(R.drawable.ukr, "Украинские сказки","ukrft"));
        categoriesList.add(new Model(R.drawable.mircastle, "Белорусские сказки","belft"));
        categoriesList.add(new Model(R.drawable.tree, "Басни", "basnift"));
        categoriesList.add(new Model(R.drawable.zeus, "Мифы Древней Греции","greciaft"));
        categoriesList.add(new Model(R.drawable.thor, "Скандинавские мифы","scandft"));
        categoriesList.add(new Model(R.drawable.oak, "Страшные истории", "horrorft"));
        return categoriesList;


    }

}
