/*****************************
 * Class name: Hospital (.java)
 *
 * Purpose: Represents a generic hospital entity. Stores the data
 * that characterize the hospital organization.
 ****************************/

package mds.gpp.saudeemcasa.model;

public class Hospital extends Stablishment {
    private String number = "";
    private String district = "";

    /**
     * Empty contructor.
     */
    public Hospital() {
    }

    /**
     * Constructor of hospital
     *
     * @param name:String
     * @param telephone:String
     *
     */
    public Hospital(String name, String telephone) {
        super(name, telephone);

    }

    /**
     * Contructor
     * @param latitude
     * @param longitude
     * @param telephone
     * @param name
     * @param city
     * @param address
     * @param state
     * @param id
     * @param type
     */
    public Hospital(String latitude, String longitude, String telephone, String name, String city,
                    String address, String state, String id, String type, String district) {
        super(latitude,longitude,telephone,name,city,address,state,id,type);
        this.district = district;
    }

    /**
     * Get the value of attribute number.
     *
     * @return number:String
     *
     */
    public String getNumber() {
        assert (number != null) : "Null attribute treatment.";
        return number;
    }

    /**
     * Set the value of attribute number.
     *
     * @param number:String
     *
     */
    public void setNumber(String number) {
        assert (number != null) : "Null attribute treatment.";

        this.number = number;
    }

    /**
     * Get the string of attribute district.
     *
     * @return district:String
     *
     */
    public String getDistrict() {
        assert (district != null) : "Null attribute treatment.";
        assert (district != "") : "Empty district treatment.";
        assert (district.length() > 2) : "Minor character treatment.";
        return district;
    }

    /**
     * Set the string of attribute district.
     *
     * @param district:String
     *
     */
    public void setDistrict(String district) {
        assert (district != null) : "Null attribute treatment.";
        assert (district != "") : "Empty district treatment.";
        assert (district.length() > 2) : "Minor character treatment.";

        this.district = district;
    }

}