package mds.gpp.saudeemcasa.model;

/**
 * Created by freemanpivo on 9/20/15.
 */
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
        this.postalCode = postalCode;
    }



   
}