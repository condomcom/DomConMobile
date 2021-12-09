package com.example.condom;

public class DynamicPerformanceItem {
    private int id;
    private String itemTitle;
    private int itemImage;
    private String itemDescription;
    private String itemBeginning;
    //private String favoriteStatus;

    public DynamicPerformanceItem(int id, String itemTitle, int itemImage, String itemDescription, String itemBeginning) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.itemDescription = itemDescription;
        this.itemBeginning = itemBeginning;
        //this.favoriteStatus = favoriteStatus;
    }

    public int getId() {
        return id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public int getItemImage() {
        return itemImage;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemBeginning() {
        return itemBeginning;
    }

   /* public String getFavoriteStatus() {
        return favoriteStatus;
    }*/
}
