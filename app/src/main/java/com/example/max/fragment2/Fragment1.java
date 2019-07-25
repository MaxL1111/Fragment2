package com.example.max.fragment2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.max.fragment2.adapter.CategoryAdapter;
import com.example.max.fragment2.model.ListDetails;
import com.example.max.fragment2.model.Model;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    LinearLayoutManager layoutManager;
    ArrayList<Model> models;
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerView;

    public OnFragment1DataListener getmListener() {
        return mListener;
    }

    public OnFragment1DataListener mListener;
    public static final String KEY = "xxx";

    public Fragment1() {
        // Required empty public constructor
    }

    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        // в Activity получаем доступ к RecyclerView, находим по id
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


        models = ListDetails.getList();

        //В отличие от ListView,для управления позициями пунктов RecyclerView треб-ся LayoutManager
        //используем LinearLayoutManager. LayoutManager подкласс, по умолчанию, сделает
        // ваш RecyclerView похожим на ListView
        Context context = null;
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //Если размер RecyclerView не изменится, то для повышения производительности добавим метод
        recyclerView.setHasFixedSize(true);


        //Теперь, когда адаптер готов, добавьте следующий код в Activity
        //чтобы инициализировать и использовать адаптер,
        // вызывая конструктор адаптера и метод setAdapter в RecyclerView:
        categoryAdapter = new CategoryAdapter(context, models);
        categoryAdapter.DataListenerr(mListener);
        //  mListener.onOpenFragment2("belft");


        recyclerView.setAdapter(categoryAdapter);


        return view;
    }


    //У фрагмента есть метод onAttach(), который вызывается при присоединении фрагмента к активности
    //В нем и проверим есть ли у активности наш интерфейс.Если нет, то вызываем исключение
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment1DataListener) {
            //мы из активности вытащили объект нашего интерфейса.
            //То есть этот интерфейс принадлежит активности, а не фрагменту
            mListener = (OnFragment1DataListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }
    }

}