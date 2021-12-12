package com.example.condom.ui.modelItem;

public class DynamicFavoritesItem {
    private String keyId;
    private String itemTitle;
    private int itemImage;
    private String itemDescription;
    private String itemBeginning;

    public DynamicFavoritesItem(String keyId, String itemTitle, int itemImage, String itemDescription, String itemBeginning) {
        this.keyId = keyId;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.itemDescription = itemDescription;
        this.itemBeginning = itemBeginning;
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
}
