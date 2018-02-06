package model;

import java.util.UUID;

public class Places {
    private UUID mId;
    private String mTitle;
    private String mCode;
    private String mDescription;
    private String mAddress;
    private String mSchedule;
    private Double mPrice;
    private Double mLat;
    private Double mLon;
    private String mImageResourceId;

    public Places(){
        //Generate unique identifier
        mId = UUID.randomUUID();
    }

    public Places(String mTitle,
                  String mCode,
                  String mDescription,
                  String mAddress, String mSchedule,
                  Double mPrice, Double mLat, Double mLon, String mImageResourceId) {

        this();
        this.mTitle = mTitle;
        this.mCode = mCode;
        this.mDescription = mDescription;
        this.mAddress = mAddress;
        this.mSchedule = mSchedule;
        this.mPrice = mPrice;
        this.mLat = mLat;
        this.mLon = mLon;
        this.mImageResourceId = mImageResourceId;

    }

   public Places(UUID mId, String mTitle,
                  String mCode,
                  String mDescription,
                  String mAddress, String mSchedule,
                  Double mPrice, Double mLat, Double mLon, String mImageResourceId) {

        this.mId = mId;
        this.mTitle = mTitle;
        this.mCode = mCode;
        this.mDescription = mDescription;
        this.mAddress = mAddress;
        this.mSchedule = mSchedule;
        this.mPrice = mPrice;
        this.mLat = mLat;
        this.mLon = mLon;
        this.mImageResourceId = mImageResourceId;

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String mCode) {
        this.mCode = mCode;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getSchedule() {
        return mSchedule;
    }

    public void setSchedule(String mSchedule) {
        this.mSchedule = mSchedule;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double mPrice) {
        this.mPrice = mPrice;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double mLat) {
        this.mLat = mLat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double mLon) {
        this.mLon = mLon;
    }

    public String getmImageResourceId() {
        return mImageResourceId;
    }

    public void setmImageResourceId(String mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }

}
