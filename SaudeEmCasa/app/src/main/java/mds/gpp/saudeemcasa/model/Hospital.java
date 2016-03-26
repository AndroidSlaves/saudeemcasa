package mds.gpp.saudeemcasa.model;


public class Hospital extends Stablishment {
    //empty contructor
    public Hospital() {
    }

    public Hospital(String name, String telephone) {
        super(name, telephone);
    }

    protected String number = "";
    protected String district = "";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

}