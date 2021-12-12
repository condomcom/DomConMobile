package com.example.condom.ui.modelItem;

public class DynamicPerformanceItem {
    private String keyId;
    private String itemTitle;
    private int itemImage;
    private String itemDescription;
    private String itemBeginning;
    private String favoriteStatus;

    public DynamicPerformanceItem(String id, String itemTitle, int itemImage, String itemDescription, String itemBeginning, String favoriteStatus) {
        this.keyId = id;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.itemDescription = itemDescription;
        this.itemBeginning = itemBeginning;
        this.favoriteStatus = favoriteStatus;
    }

    public String getKeyId() {
        return keyId;
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

    public String getFavoriteStatus() {
       return favoriteStatus;
   }

    public void setFavoriteStatus(String favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }
}
