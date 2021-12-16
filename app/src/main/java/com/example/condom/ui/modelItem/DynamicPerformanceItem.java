package com.example.condom.ui.modelItem;

public class DynamicPerformanceItem {
    private String keyId;
    private String itemTitle;
    private int itemImage;
    private String itemDescription;
    private String itemBeginning;
    private String favoriteStatus;
    private String itemSpeaker;
    private String itemEnd;
    private String itemDirection;
    private String itemPlace;
    private String itemDate;

    public DynamicPerformanceItem(String id, String itemTitle,
                                  int itemImage, String itemDescription,
                                  String itemBeginning, String favoriteStatus,
                                  String itemSpeaker, String itemEnd,
                                  String itemDirection, String itemPlace, String itemDate) {
        this.keyId = id;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.itemDescription = itemDescription;
        this.itemBeginning = itemBeginning;
        this.favoriteStatus = favoriteStatus;
        this.itemSpeaker = itemSpeaker;
        this.itemEnd = itemEnd;
        this.itemDirection = itemDirection;
        this.itemPlace = itemPlace;
        this.itemDate = itemDate;
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

    public String getItemSpeaker() {
        return itemSpeaker;
    }

    public String getItemEnd() {
        return itemEnd;
    }

    public String getItemDirection() {
        return itemDirection;
    }

    public String getItemPlace() {
        return itemPlace;
    }

    public String getItemDate() {
        return itemDate;
    }
}
