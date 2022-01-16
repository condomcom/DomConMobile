package com.example.condom.ui.modelItem;

public class DynamicSpeakerItem {
    private int keyId;
    private String name;
    private String surname;
    private String image;
    private String profession;
    private String title;
    private String place;
    private String time;

    public DynamicSpeakerItem(int keyId, String speakerName, String speakerSurname, String speakerImage, String speakerProfession,
                              String speakerTitle, String speakerPlace, String speakerTime) {
        this.keyId = keyId;
        this.name = speakerName;
        this.surname = speakerSurname;
        this.image = speakerImage;
        this.profession = speakerProfession;
        this.title = speakerTitle;
        this.place = speakerPlace;
        this.time = speakerTime;
    }

    public int getKeyId() {
        return keyId;
    }

    public String getSpeakerName() {
        return name;
    }

    public String getSpeakerSurname() {
        return surname;
    }

    public String getImage() {
        return image;
    }

    public String getProfession() {
        return profession;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }
}
