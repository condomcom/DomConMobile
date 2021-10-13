package com.example.condom.favorites;

public class FavoritesItem {
    private String keyId;
    private String itemTitle;
    private int itemImage;
    private String itemDescription;
    private String itemBeginning;
    private String itemDuration;
    //private String favoriteStatus;

    public FavoritesItem() {
    }

    public FavoritesItem(String keyId, String itemTitle, int itemImage,
                                 String itemDescription, String itemBeginning,
                                 String itemDuration) {
        this.keyId = keyId;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.itemDescription = itemDescription;
        this.itemBeginning = itemBeginning;
        this.itemDuration = itemDuration;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemBeginning() {
        return itemBeginning;
    }

    public void setItemBeginning(String itemBeginning) {
        this.itemBeginning = itemBeginning;
    }

    public String getItemDuration() {
        return itemDuration;
    }

    public void setItemDuration(String itemDuration) {
        this.itemDuration = itemDuration;
    }

}
