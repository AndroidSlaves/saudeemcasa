package mds.gpp.saudeemcasa.model;

public class DrugStore extends Stablishment {

    public DrugStore() {

    }

    public DrugStore(String nameDrugStore, String telephoneDrugStore) {
        super(nameDrugStore, telephoneDrugStore);
    }

    protected String postalCode = "";

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        assert (postalCode.length() == 8): "postalCode must never be smaller then 0 characters";
        assert (postalCode != "") : "postalCode must never be empty";
        assert (postalCode != null) : "postalCode must never be null";

        this.postalCode = postalCode;
    }
       
}