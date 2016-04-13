package mds.gpp.saudeemcasa.model;

public class DrugStore extends Stablishment {

    public DrugStore() {

    }

    public DrugStore(String nameDrugStore, String telephoneDrugStore) {
        
        Assert(nameDrugStore != "");
        Assert(nameDrugStore != null);
        Assert(nameDrugStore.length() > 2);
        Assert(telephoneDrugStore !="");
        Assert(telephoneDrugStore.length() >= 10);
        Assert(telephoneDrugStore < );

        super(nameDrugStore, telephoneDrugStore);
    }

    protected String postalCode = "";

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        
        Assert(postalCode.length() == 8);
        Assert(postalCode != "");
        Assert(postalCode != null);

        this.postalCode = postalCode;
    }
       
}