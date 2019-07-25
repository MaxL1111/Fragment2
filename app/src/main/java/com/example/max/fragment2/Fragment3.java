package com.example.max.fragment2;

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
import android.widget.TextView;

import com.example.max.fragment2.adapter.NameFairytaleAdapter;
import com.example.max.fragment2.model.Model2;

import java.io.IOException;
import java.util.ArrayList;

public class Fragment3 extends Fragment {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    String column_textft = "textft";
    String tableName = "";

    TextView text1, text2;


    LinearLayoutManager layoutManager;
    ArrayList<Model2> models;
    NameFairytaleAdapter categoryAdapter;
    RecyclerView recyclerView;
    ArrayList<String> list_nameft;
    ArrayList<Integer> list_id;
    public static final String KEY1 = "NameFairytale";
    public static final String KEY2 = "TableDB";
    public static final String KEY3 = "id";

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_view_fairytale, container, false);


        String NameFairytale = getArguments().getString(KEY1);
        String TableDB = getArguments().getString(KEY2);
        int id = getArguments().getInt(KEY3);
        String str_id = String.valueOf(id);
        text1 = (TextView) view.findViewById(R.id.text1);
        text2 = (TextView) view.findViewById(R.id.text2);
        text1.setText(NameFairytale);


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
        String selection = null;
        String[] selectionArgs = null;
        tableName = TableDB;
        columns = new String[]{column_textft};
        selection = "_id = ?";
        selectionArgs = new String[]{str_id};
        cursor = db.query(tableName, columns, selection, selectionArgs, null, null,
                null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String tdescr = cursor.getString(cursor.getColumnIndexOrThrow(column_textft));
                text2.setText(tdescr);
            }
            cursor.close();
        } else {
            dbHelper.close();
        }

        return view;
    }


}
