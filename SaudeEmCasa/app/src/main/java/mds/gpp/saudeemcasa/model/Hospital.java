package mds.gpp.saudeemcasa.model;


public class Hospital extends Stablishment {
    //empty contructor
    public Hospital() {
    }

    public Hospital(String name, String telephone) {
        super(name, telephone);

        assert (name != null) : "Null attribute treatment.";
        assert (name.length() > 1) : "Treatment to minor of character in a name ";
        assert (name != "") : "Empty name treatment";
        assert (telephone.length() >= 10) : "Treatment to minor of character in telephone";

    }

    protected String number = "";
    protected String district = "";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        assert (number != null) : "Null attribute treatment.";

        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        assert (district != null) : "Null attribute treatment.";
        assert (district != "") : "Empty district treatment.";
        assert (district.length() > 2) : "Minor character treatment.";

        this.district = district;
    }

}