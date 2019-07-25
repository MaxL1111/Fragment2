package com.example.max.fragment2.model;

public class Model {

    private int categoryImageId;
    private String categoryTitle;
    private String tableDB;

    public Model(int categoryImageId, String categoryTitle, String tableDB) {
        this.categoryImageId = categoryImageId;
        this.categoryTitle = categoryTitle;
        this.tableDB = tableDB;
    }

    public String getTableDB() {
        return tableDB;
    }

    public void setTableDB(String tableDB) {
        this.tableDB = tableDB;
    }

    public int getCategoryImageId() {
        return categoryImageId;
    }

    public void setCategotyImageId(int categoryImageId) {
        this.categoryImageId = categoryImageId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
