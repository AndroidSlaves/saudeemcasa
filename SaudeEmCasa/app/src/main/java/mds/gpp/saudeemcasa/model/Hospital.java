/*****************************
 * Class name: Hospital (.java)
 *
 * Purpose: Represents a generic hospital entity. Stores the data
 * that characterize the hospital organization.
 ****************************/

package mds.gpp.saudeemcasa.model;

public class Hospital extends Stablishment {
    // Empty constructor
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

        assert (name != null) : "Null attribute treatment.";
        assert (name.length() > 1) : "Treatment to minor of character in a name ";
        assert (name != "") : "Empty name treatment";
        assert (telephone.length() >= 10) : "Treatment to minor of character in telephone";

    }

    private String number = "";
    private String district = "";

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