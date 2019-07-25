package com.example.max.fragment2.adapter;

import android.content.Context;
import android.database.SQLException;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.max.fragment2.DBHelper;
import com.example.max.fragment2.OnFragment1DataListener;
import com.example.max.fragment2.R;
import com.example.max.fragment2.model.Model2;
import com.example.max.fragment2.model.Model3;

import java.io.IOException;
import java.util.ArrayList;

public class NameFairytaleAdapter2 extends RecyclerView.Adapter<NameFairytaleAdapter2.MyHolder> {

    private Context context;
    private Context context2;
    private ArrayList<Model3> models;
    private OnFragment1DataListener mListener;
    private DBHelper localDB;


    public NameFairytaleAdapter2(Context context, ArrayList<Model3> models, Context context2) {
        this.context = context;
        this.models = models;
        this.context2 = context2;
    }

    public void DataListener(OnFragment1DataListener mListener) {
        this.mListener = mListener;
    }

    //переопределяем метод onCreateViewHolder.
    // т.е. сначала RecyclerView создаст небольшое число вьюхолдеров для конкретной вьюшки viewType
    // Мы указываем макет для каждого элемента
    // RecyclerView. Затем LayoutInflater заполняет макет, и передает его в конструктор ViewHolder.
    @Override
    public NameFairytaleAdapter2.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items2, null);
        NameFairytaleAdapter2.MyHolder myHolder = new NameFairytaleAdapter2.MyHolder(view);
        return myHolder;
    }


    // Переопределим onBindViewHolder и определим содержание каждого элемента из RecyclerView.
    // Этот метод очень похож на метод getView элемента адаптера ListView для отображения
    // В нашем примере, здесь вы должны установить значения полей имя
    @Override
    public void onBindViewHolder(final NameFairytaleAdapter2.MyHolder holder, int position) {

        holder.nameFairytaleTV.setText(models.get(position).getNameFairytale());
        // holder.tableDB.setText(models.get(position).getTableDB());
        final String NameFairytale = models.get(position).getNameFairytale();
        final String TableDB = models.get(position).getTableDB();
        final Integer id = models.get(position).getId();

        //Local DB for Favorite
        localDB = new DBHelper(context2);
        try {
            localDB.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("Unable To Update Database");
        }

        try {
            // подключаемся к базе
            //SQLiteDatabase - класс работы с базой (query, delete..)
            localDB.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        if (localDB.isFavorite(id, TableDB)) {
            holder.fav_image.setImageResource(R.drawable.ic_favorite_black_24dp);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onOpenFragment4(NameFairytale, TableDB, id);
            }
        });
        holder.fav_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!localDB.isFavorite(id, TableDB)) {
                    localDB.addToFavorites(id, TableDB);
                    holder.fav_image.setImageResource(R.drawable.ic_favorite_black_24dp);

                } else {
                    localDB.removeFromFavorites(id, TableDB);
                    holder.fav_image.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }
            }
        });
    }


    // метод возвращает число элементов
    @Override
    public int getItemCount() {
        //тернарная операция: if(null!=models){models.size()} else{return 0}
        return null != models ? models.size() : 0;

    }

    // в классе ViewHolder мы храним ссылки на поля во вьюшке, чтобы можно было
    // переиспользовать старые вьюшки
    // конструктор получает на вход корневую вьюшку, т.е. которая отображается на экране
    public static class MyHolder extends RecyclerView.ViewHolder {
        CardView card_view2;
        TextView nameFairytaleTV;
        ImageView fav_image;

        //добавляем конструктор в пользовательский адаптер,
        //чтобы он имел доступ к данным, отображаемым RecyclerView
        MyHolder(View itemView) {
            super(itemView);
            card_view2 = (CardView) itemView.findViewById(R.id.card_view2);
            nameFairytaleTV = (TextView) itemView.findViewById(R.id.nameFairytaleTV);
            fav_image = (ImageView) itemView.findViewById(R.id.fav_image);

        }
    }

}