package mds.gpp.saudeemcasa.model;

public class DrugStore extends Stablishment {

    public DrugStore() {

    }

    public DrugStore(String nameDrugStore, String telephoneDrugStore) {
        
        assert(nameDrugStore != "");
        assert(nameDrugStore != null);
        assert(nameDrugStore.length > 2);
        assert(telephoneDrugStore !="");
        assert(telephoneDrugStore.length >= 10);
        assert(telephoneDrugStore < );

        super(nameDrugStore, telephoneDrugStore);
    }

    protected String postalCode = "";

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        
        assert(postalCode.length == 8);
        assert(postalCode != "");
        assert(postalCode != null);

        this.postalCode = postalCode;
    }
       
}