package mds.gpp.saudeemcasa.model;

/**
 * Created by freemanpivo on 9/20/15.
 */



    public class Stablishment {

    protected String latitude = "";
    protected String longitude = "";
    protected String type = "";
    protected String telephone = "";
    protected String name = "";
    protected String city = "";
    protected String address = "";
    protected String state = "";
    protected float rate = 0;
    protected String id;
    protected float distance = 0;



    public Stablishment() {
    }

    public Stablishment(String name, String telephone) {
        this.name = name;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public float getRate() {
        return rate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

}
