package com.example.condom;

public class newSpeakerRVItem {
    private int keyId;
    private String speakerName;
    private int speakerImage;
    private String speakerProfession;

    public newSpeakerRVItem(int keyId, String speakerName, int speakerImage, String speakerProfession) {
        this.keyId = keyId;
        this.speakerName = speakerName;
        this.speakerImage = speakerImage;
        this.speakerProfession = speakerProfession;
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
}
