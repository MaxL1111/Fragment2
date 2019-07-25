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

import com.example.max.fragment2.adapter.NameFairytaleAdapter;
import com.example.max.fragment2.adapter.NameFairytaleAdapter2;
import com.example.max.fragment2.model.ListDetails2;
import com.example.max.fragment2.model.ListDetails3;
import com.example.max.fragment2.model.Model2;
import com.example.max.fragment2.model.Model3;

import java.io.IOException;
import java.util.ArrayList;

public class FragmentFavorites4 extends Fragment {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    String column_nameft = "nameft";
    String column_id = "_id";
    String tableName2 = "favoriteft";
    String tableName = "";
    LinearLayoutManager layoutManager;
    ArrayList<Model3> models;
    NameFairytaleAdapter2 categoryAdapter;
    RecyclerView recyclerView;

    ArrayList<String> list_nameft;
    ArrayList<Integer> list_id;

    //массивы данных из таблицы favoriteft
    ArrayList<Integer> list_id_favor;
    ArrayList<String> list_name_table;
    ArrayList<String> list_tableNames;

    public static final String KEY = "nameTable";
    public OnFragment1DataListener mListener;


    public FragmentFavorites4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listfairy, container, false);

        //   String pos = getArguments().getString(KEY);


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


        // достаем все данные из таблицы favoriteft
        Cursor cursor1 = null;
        String column_id_favor = "id_ft";
        String column_name_table = "name_table";
        String[] columnsFav = null;
        columnsFav = new String[]{column_id_favor, column_name_table};
        String tableFav = "favoriteft";

        cursor1 = db.query(tableFav, columnsFav, null, null, null, null,
                null);

        list_id_favor = new ArrayList<Integer>();
        list_name_table = new ArrayList<String>();

        //Курсор - это набор строк в табличном виде
        if (cursor1 != null) {

            //moveToFirst() — перемещает курсор на первую строку в результате запроса
            if (cursor1.moveToFirst()) {

                list_id_favor.add(cursor1.getInt(cursor1.getColumnIndex(column_id_favor)));
                list_name_table.add(cursor1.getString(cursor1.getColumnIndex(column_name_table)));

                //moveToNext() — перемещает курсор на следующую строку
                while (cursor1.moveToNext()) {
                    list_id_favor.add(cursor1.getInt(cursor1.getColumnIndex(column_id_favor)));
                    list_name_table.add(cursor1.getString(cursor1.getColumnIndex(column_name_table)));
                }
            }
            cursor1.close();


            //Достаем названия сказок, если они избранные, по остальным таблицам
            Cursor cursor = null;
            String[] columns = null;

            list_nameft = new ArrayList<String>();
            list_id = new ArrayList<Integer>();
            list_tableNames = new ArrayList<String>();

            columns = new String[]{column_id, column_nameft};
            String selection = null;
            String[] selectionArgs = null;
            selection = "_id = ?";


            for (int i = 0; i < list_id_favor.size(); i++) {


                tableName = list_name_table.get(i);
                Integer fairyId = list_id_favor.get(i);
                //  columns = new String[]{column_id};


                selectionArgs = new String[]{String.valueOf(fairyId)};
                cursor = db.query(tableName, columns, selection, selectionArgs,
                        null, null, null);


                //Курсор - это набор строк в табличном виде
                if (cursor != null) {

                    //moveToFirst() — перемещает курсор на первую строку в результате запроса
                    if (cursor.moveToFirst()) {

                        list_id.add(cursor.getInt(cursor.getColumnIndex(column_id)));
                        list_nameft.add(cursor.getString(cursor.getColumnIndex(column_nameft)));
                        list_tableNames.add(tableName);

                        models = ListDetails3.getList(list_id, list_nameft, list_tableNames);

                    }
                    cursor.close();
                } else {
                    dbHelper.close();
                }
            }


        } else {

            dbHelper.close();
        }


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
        categoryAdapter = new NameFairytaleAdapter2(context, models, getContext());
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
