package com.example.max.fragment2;

//определим интерфейс слушателя команд от нашего фрагмента.
public interface OnFragment1DataListener {
    //Если фрагмент может отправлять несколько сигналов, то в этом интерфейсе будет несколько методов
  //  void onOpenFragment1();
    void onOpenFragment2(String string);
    void onOpenFragment3(String NameFairytale,String TableDB, int id);
    void onOpenFragment4(String NameFairytale,String TableDB, int id);
}
