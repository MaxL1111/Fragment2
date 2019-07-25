package com.example.max.fragment2.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.max.fragment2.OnFragment1DataListener;
import com.example.max.fragment2.R;
import com.example.max.fragment2.model.Model;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

    private Context context;
    private ArrayList<Model> models;
    private OnFragment1DataListener mListener;
    private Context context2;

    public CategoryAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;

    }

    public void DataListenerr(OnFragment1DataListener mListener) {
        this.mListener = mListener;
    }


    //переопределяем метод onCreateViewHolder.
    // т.е. сначала RecyclerView создаст небольшое число вьюхолдеров для конкретной вьюшки viewType
    // Мы указываем макет для каждого элемента
    // RecyclerView. Затем LayoutInflater заполняет макет, и передает его в конструктор ViewHolder.
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    // Переопределим onBindViewHolder и определим содержание каждого элемента из RecyclerView.
    // Этот метод очень похож на метод getView элемента адаптера ListView для отображения
    // В нашем примере, здесь вы должны установить значения полей имя
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.categoryImages.setImageResource(models.get(position).getCategoryImageId());
        holder.categoryTitle.setText(models.get(position).getCategoryTitle());

        final String nameTable = models.get(position).getTableDB();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();

                /**
                 мы в фрагменте можем обращаться к нашему объекту mListener и и вызывать его метод
                 И этот вызов будет означать, что мы вызываем метод активности.
                 Мы хотим послать сигнал активности в клике
                 **/

                mListener.onOpenFragment2(nameTable);
            }
        });


    }


    // метод возвращает число элементов
    @Override
    public int getItemCount() {
        return models.size();
    }


    // в классе ViewHolder мы храним ссылки на поля во вьюшке, чтобы можно было
    // переиспользовать старые вьюшки
    // конструктор получает на вход корневую вьюшку, т.е. которая отображается на экране
    public static class MyHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        TextView categoryTitle;
        TextView tableDB;
        ImageView categoryImages;

        //добавляем конструктор в пользовательский адаптер,
        //чтобы он имел доступ к данным, отображаемым RecyclerView
        MyHolder(View itemView) {
            super(itemView);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            categoryTitle = (TextView) itemView.findViewById(R.id.categorynameTV);
            categoryImages = (ImageView) itemView.findViewById(R.id.categoryimageView);
        }
    }


}
