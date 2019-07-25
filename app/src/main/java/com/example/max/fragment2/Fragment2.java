package com.example.max.fragment2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.max.fragment2.R;
import com.example.max.fragment2.adapter.NameFairytaleAdapter;
import com.example.max.fragment2.model.ListDetails2;
import com.example.max.fragment2.model.Model2;

import java.io.IOException;
import java.util.ArrayList;

public class Fragment2 extends Fragment {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    String column_nameft = "nameft";
    String column_id = "_id";
    String tableName = "";

    LinearLayoutManager layoutManager;
    ArrayList<Model2> models;
    NameFairytaleAdapter categoryAdapter;
    RecyclerView recyclerView;
    ArrayList<String> list_nameft;
    ArrayList<Integer> list_id;
    public static final String KEY = "nameTable";
    public OnFragment1DataListener mListener;


    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listfairy, container, false);

        String pos = getArguments().getString(KEY);


        dbHelper = new DBHelper(getContext());
        try {
            dbHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("Unable To Update Database");
        }

        try {
            // подключаемся к базе
            //SQLiteDatabase - класс работы с базой (query, delete..)
            db = dbHelper.getWritableDatabase();

        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        Cursor cursor = null;
        String[] columns = null;
        columns = new String[]{column_id, column_nameft};
        tableName = pos;
        cursor = db.query(tableName, columns, null, null, null, null,
                null);

        //Курсор - это набор строк в табличном виде
        if (cursor != null) {

            //moveToFirst() — перемещает курсор на первую строку в результате запроса
            if (cursor.moveToFirst()) {
                list_nameft = new ArrayList<String>();
                list_id = new ArrayList<Integer>();
                list_id.add(cursor.getInt(cursor.getColumnIndex(column_id)));
                list_nameft.add(cursor.getString(cursor.getColumnIndex(column_nameft)));

                //favorite
                //  if(localDB.isFavorite(column_id,tableName))

                //moveToNext() — перемещает курсор на следующую строку
                while (cursor.moveToNext()) {
                    list_id.add(cursor.getInt(cursor.getColumnIndex(column_id)));
                    list_nameft.add(cursor.getString(cursor.getColumnIndex(column_nameft)));
                    models = ListDetails2.getList(list_id, list_nameft, tableName);
                }
            }
            cursor.close();
        } else {
            dbHelper.close();
        }

        //Favorite


        // в Activity получаем доступ к RecyclerView, находим по id
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);


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
        categoryAdapter = new NameFairytaleAdapter(context, models, getContext());
        categoryAdapter.DataListener(mListener);
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
