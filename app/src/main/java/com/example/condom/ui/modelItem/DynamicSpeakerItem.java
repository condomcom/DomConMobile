package com.example.condom.ui.modelItem;

public class DynamicSpeakerItem {
    private int keyId;
    private String speakerName;
    private int speakerImage;
    private String speakerProfession;
    private String speakerTitle;
    private String speakerPlace;
    private String speakerTime;

    public DynamicSpeakerItem(int keyId, String speakerName, int speakerImage, String speakerProfession,
                              String speakerTitle, String speakerPlace, String speakerTime) {
        this.keyId = keyId;
        this.speakerName = speakerName;
        this.speakerImage = speakerImage;
        this.speakerProfession = speakerProfession;
        this.speakerTitle = speakerTitle;
        this.speakerPlace = speakerPlace;
        this.speakerTime = speakerTime;
    }

    public int getKeyId() {
        return keyId;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public int getSpeakerImage() {
        return speakerImage;
    }

    public String getSpeakerProfession() {
        return speakerProfession;
    }

    public String getSpeakerTitle() {
        return speakerTitle;
    }

    public String getSpeakerPlace() {
        return speakerPlace;
    }

    public String getSpeakerTime() {
        return speakerTime;
    }
}
