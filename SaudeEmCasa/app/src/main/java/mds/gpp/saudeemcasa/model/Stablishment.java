/*****************************
 * Class name: Stablishment (.java)
 *
 * Purpose: Represents a generic Stablishment entity. Stores the data
 * that characterize the Stablishment organization.
 ****************************/

package mds.gpp.saudeemcasa.model;

public class Stablishment {
    //Attributes of the stablishment.
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

    /**
     * Default constructor.
     *
     */
    public Stablishment() {
    }
    /**
     * Constructor with name and telephone for initialization
     *
     * @param name
     * Stablishment name that will be set on initialization
     * @param telephone
     * Stablishment telephone that will be set on initialization
     */
    public Stablishment(String name, String telephone) {
        assert (name != null) : "Receive a null tratment";
        assert (name.length() > 1) : "Tratment to minor of character in a name ";
        assert (name != "") : "Tratment empty name";
        assert (telephone.length() >= 10) : "Tratment to minor of character in telephone";

        this.name = name;
        this.telephone = telephone;
    }

    /**
     * Returns the identification number value stored for stablishment
     *
     * @return Stablishment identification number.
     */
    public String getId() {
        return id;
    }
    /**
     * Used to access the variable id and stored given data in this Stablishment
     *
     * @param id
     * Identification number to be stored.
     */
    public void setId(String id) {
        assert (id != null) : "Receive a null tratment";
        assert (id != "") : "Receive a empty tratment";
        this.id = id;
    }
    /**
     * Returns the latitude position value stored for stablishment
     *
     * @return Stablishment latitude.
     */
    public String getLatitude() {
        return latitude;
    }
    /**
     * Used to access the variable latitude and stored given data in this Stablishment
     *
     * @param latitude
     * Latitude number to be stored.
     */
    public void setLatitude(String latitude) {
        assert (latitude != null) : "Receive a null tratment";
        assert (latitude != "") : "Receive a empty tratment";
        this.latitude = latitude;
    }
    /**
     * Returns the longitude number value stored for stablishment
     *
     * @return Stablishment longitude number.
     */
    public String getLongitude() {
        return longitude;
    }
    /**
     * Used to access the variable longitude address and stored given data in this Stablishment
     *
     * @param longitude
     * Longitude number to be stored.
     */
    public void setLongitude(String longitude) {
        assert (longitude != null) : "Receive a null tratment";
        assert (longitude != "") : "Receive a empty tratment";
        this.longitude = longitude;
    }
    /**
     * Returns the city name where the stablishment is located
     *
     * @return Stablishment city name.
     */
    public String getCity() {
        return city;
    }
    /**
     * Used to access the variable city address and stored given data in this Stablishment
     *
     * @param city
     * City name that will be stored.
     */
    public void setCity(String city) {
        assert (city != null) : "Receive a null tratment";
        assert (city != "") : "Receive a empty tratment";
        this.city = city;
    }
    /**
     * Returns the address that points where the stablishment is located
     *
     * @return Stablishment address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Used to access the variable address and stored given data in this Stablishment
     *
     * @param address
     * Address text that will be stored.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Returns the state name where the stablishment is located
     *
     * @return Stablishment state name.
     */
    public String getState() {
        return state;
    }
    /**
     * Used to access the variable state and stored given data in this Stablishment
     *
     * @param state
     * State name that will be stored.
     */
    public void setState(String state) {
        assert (state != null) : "Receive a null tratment";
        assert (state != "") : "Receive a empty tratment";
        this.state = state;
    }
    /**
     * Returns the rate given to  this stablishment
     *
     * @return Stablishment rate number. (Integer between 1 and 5)
     */
    public float getRate() {
        return rate;
    }
    /**
     * Used to access the variable rate and stored given data in this Stablishment
     *
     * @param rate
     * Rate number attributed to this stablishment that will be stored.
     */
    public void setRate(float rate) {
        assert (rate >= 0) : "Receive a negative tratment";
        this.rate = rate;
    }
    /**
     * Returns the stablishment telephone
     *
     * @return the stablishment telephone.
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * Used to access the variable telephone and stored given data in this Stablishment
     *
     * @param telephone
     * Telephone number attributed to this stablishment that will be stored.
     */
    public void setTelephone(String telephone) {
        assert (telephone != null) : "Receive a null tratment";
        assert (telephone != "") : "Receive a empty tratment";
        assert (telephone.length() >= 10) : "Tratment to minor of character in telephone";
        this.telephone = telephone;
    }
    /**
     * Returns The stablishment market name.
     *
     * @return The stablishment market name.
     */
    public String getName() {
        return name;
    }
    /**
     * Used to access the variable name and stored given data in this Stablishment
     *
     * @param name
     * Name number attributed to this stablishment that will be stored.
     */
    public void setName(String name) {
        assert (name != null) : "Receive a null tratment";
        assert (name != "") : "Receive a empty tratment";
        this.name = name;
    }
    /**
     * Returns The type of stablishment this is.
     *
     * @return The type of the stablishment. (private or public)
     */
    public String getType() {
        return type;
    }
    /**
     * Used to access the variable type and stored given data in this Stablishment
     *
     * @param type
     * Type attributed to this stablishment that will be stored.
     */
    public void setType(String type) {
        assert (type != null) : "Receive a null tratment";
        assert (type != "") : "Receive a empty tratment";
        this.type = type;
    }
    /**
     * Returns the distance between this stablishment and the user's location.
     *
     * @return the distance between this stablishment and the user's location (In meters).
     */
    public float getDistance() {
        return distance;
    }
    /**
     * Used to access the variable distance and stored given data in this Stablishment
     *
     * @param distance
     * the distance between this stablishment and the user's location (In meters) that will be stored.
     */
    public void setDistance(float distance) {
        assert (distance >= 0) : "Receive a negative tratment";
        this.distance = distance;
    }
}
