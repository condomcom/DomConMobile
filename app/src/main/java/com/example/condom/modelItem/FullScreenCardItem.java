package com.example.condom.modelItem;

public class FullScreenCardItem {
    private String keyId;
    private String itemTitle;
    private int itemImage;
    private String itemDescription;
    private String itemDate;
    private String itemStartTime;
    private String itemEndTime;
    private String itemSpeaker;
    private String itemCompany;

    public FullScreenCardItem(String keyId, String itemTitle, int itemImage,
                              String itemDescription, String itemDate,
                              String itemStartTime, String itemEndTime,
                              String itemSpeaker, String itemCompany) {
        this.keyId = keyId;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.itemDescription = itemDescription;
        this.itemDate = itemDate;
        this.itemStartTime = itemStartTime;
        this.itemEndTime = itemEndTime;
        this.itemSpeaker = itemSpeaker;
        this.itemCompany = itemCompany;
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

    public String getItemDate() {
        return itemDate;
    }

    public String getItemStartTime() {
        return itemStartTime;
    }

    public String getItemEndTime() {
        return itemEndTime;
    }

    public String getItemSpeaker() {
        return itemSpeaker;
    }

    public String getItemCompany() {
        return itemCompany;
    }
}
