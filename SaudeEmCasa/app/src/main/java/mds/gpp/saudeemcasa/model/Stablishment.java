package mds.gpp.saudeemcasa.model;

/**
 * Class name: Stablishment (.java)
 *
 * Purpose: This class modeling stablishments like the Hospitals and the Drugstores
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
        assert (name != null) : "Receive a null tratment";
        assert (name.length() > 1) : "Tratment to minor of character in a name ";
        assert (name != "") : "Tratment empty name";
        assert (telephone.length() >= 10) : "Tratment to minor of character in telephone";

        this.name = name;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        assert (id != null) : "Receive a null tratment";
        assert (id != "") : "Receive a empty tratment";
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        assert (latitude != null) : "Receive a null tratment";
        assert (latitude != "") : "Receive a empty tratment";
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        assert (longitude != null) : "Receive a null tratment";
        assert (longitude != "") : "Receive a empty tratment";
        this.longitude = longitude;
    }

    public void setCity(String city) {
        assert (city != null) : "Receive a null tratment";
        assert (city != "") : "Receive a empty tratment";
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        assert (state != null) : "Receive a null tratment";
        assert (state != "") : "Receive a empty tratment";
        this.state = state;
    }

    public void setRate(float rate) {
        assert (rate >= 0) : "Receive a negative tratment";
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
        assert (telephone != null) : "Receive a null tratment";
        assert (telephone != "") : "Receive a empty tratment";
        assert (telephone.length() >= 10) : "Tratment to minor of character in telephone";
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        assert (name != null) : "Receive a null tratment";
        assert (name != "") : "Receive a empty tratment";
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        assert (type != null) : "Receive a null tratment";
        assert (type != "") : "Receive a empty tratment";
        this.type = type;
    }
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        assert (distance >= 0) : "Receive a negative tratment";
        this.distance = distance;
    }

}
