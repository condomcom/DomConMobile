package com.example.condom.old_ui.navigation.speakers;

public class SpeakersCardsItem {
    private String keyId;
    private String speakerName;
    private String speakerImage;
    private String speakerProfession;
    private String speakerDescription;
    private String speakerPerfTime;
    private String speakerPlace;
    private String speakerPerf;

    public SpeakersCardsItem(String keyId, String speakerName, String speakerImage,
                                 String speakerProfession, String speakerDescription,
                                 String speakerPerfTime, String speakerPlace, String speakerPerf) {
        this.keyId = keyId;
        this.speakerName = speakerName;
        this.speakerImage = speakerImage;
        this.speakerProfession = speakerProfession;
        this.speakerDescription = speakerDescription;
        this.speakerPerfTime = speakerPerfTime;
        this.speakerPlace = speakerPlace;
        this.speakerPerf = speakerPerf;

    }


    public String getKeyId() { return keyId;}

    public void setKeyId(String keyId) { this.keyId = keyId;}

    public String getSpeakerName() { return speakerName;}

    public void setSpeakerName(String speakerName) { this.speakerName = speakerName;}

    public String getSpeakerImage() { return speakerImage;}

    public void setSpeakerImage(String speakerImage) { this.speakerImage = speakerImage;}

    public String getSpeakerDescription() { return speakerDescription;}

    public void setSpeakerDescription(String speakerDescription) { this.speakerDescription = speakerDescription;}

    public String getSpeakerProfession() { return speakerProfession;}

    public void setSpeakerProfession(String speakerProfession) { this.speakerProfession = speakerProfession;}

    public String getSpeakerPerfTime() { return speakerPerfTime;}

    public void setSpeakerPerfTime(String speakerPerfTime) { this.speakerPerfTime = speakerPerfTime;}

    public String getSpeakerPlace(){ return speakerPlace;}

    public void setSpeakerPlace(String speakerPlace) { this.speakerPlace = speakerPlace;}

    public void setSpeakerPerf(String speakerPerf){ this.speakerPerf = speakerPerf;}

    public String getSpeakerPerf(){ return speakerPerf;}

}
