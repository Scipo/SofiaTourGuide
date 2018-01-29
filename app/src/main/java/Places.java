import java.util.UUID;

public class Places {
    private UUID mId;
    private String mTitle;
    private String mCode;
    private String mDescription;
    private String mAddress;
    private String mSchedule;
    private String mPrice;
    private Double mLat;
    private Double mLon;

    public Places(){
        //Generate unique identifier
        mId = UUID.randomUUID();
    }

    public Places(UUID mId, String mTitle,
                  String mCode,
                  String mDescription,
                  String mAddress, String mSchedule,
                  String mPrice, Double mLat, Double mLon) {

        this.mId = mId;
        this.mTitle = mTitle;
        this.mCode = mCode;
        this.mDescription = mDescription;
        this.mAddress = mAddress;
        this.mSchedule = mSchedule;
        this.mPrice = mPrice;
        this.mLat = mLat;
        this.mLon = mLon;

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

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
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
}
