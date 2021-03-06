package com.example.condom.modelItem;

public class PerformancesCardsItem {
    private String keyId;
    private String itemTitle;
    private String itemImage;
    private String itemDescription;
    private String itemBeginning;
    private String itemDuration;
    private String favoriteStatus;

    public PerformancesCardsItem(String keyId, String itemTitle, String itemImage,
                                 String itemDescription, String itemBeginning,
                                 String itemDuration, String favoriteStatus) {
        this.keyId = keyId;
        this.itemTitle = itemTitle;
        this.itemImage = itemImage;
        this.itemDescription = itemDescription;
        this.itemBeginning = itemBeginning;
        this.itemDuration = itemDuration;
        this.favoriteStatus = favoriteStatus;
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

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
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

    public String getFavoriteStatus() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(String favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }
}
