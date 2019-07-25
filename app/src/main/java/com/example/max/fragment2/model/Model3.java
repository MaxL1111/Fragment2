package com.example.max.fragment2.model;

public class Model3 {

    private String nameFairytale;
    private String TableDB;
    private int id;



    public Model3(int id, String nameFairytale, String TableDB) {
        this.TableDB = TableDB;
        this.nameFairytale = nameFairytale;
        this.id = id;
    }

    public Model3(String TableDB) {

        this.TableDB = TableDB;
    }

    public String getTableDB() {
        return TableDB;
    }

    public void setTableDB(String tableDB) {
        TableDB = tableDB;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNameFairytale() {
        return nameFairytale;
    }

    public void setNameFairytale(String nameFairytale) {
        this.nameFairytale = nameFairytale;
    }


}
