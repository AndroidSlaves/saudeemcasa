/************************
 * Class name: DrugStore (.java)
 *
 * Purpose: Represents a generic drugstore with its attributes.
 ************************/

package mds.gpp.saudeemcasa.model;

public class DrugStore extends Stablishment {

    // Represents the postal code which complements the adress of a drugstore.
    private String postalCode = "";

    // Method that creates an empty drugstore (constructor)
    public DrugStore() {

    }

    public DrugStore(String latitude, String longitude, String telephone, String name, String city, String address,
                     String state, String id, float distance, String type, String postalCode) {
        super(latitude, longitude, telephone, name, city, address, state, id, distance, type);
        this.postalCode = postalCode;
    }

    /**
     * Method that creates a constructor with the drugstore basic information, name and telephone.
     *
     * @param nameDrugStore
     * @param telephoneDrugStore
     */
    public DrugStore(String nameDrugStore, String telephoneDrugStore) {
        super(nameDrugStore, telephoneDrugStore);

        assert(nameDrugStore != "") : "nameDrugStore must never be empty";
        assert(nameDrugStore != null) : "nameDrugStore must never be null";
        assert(nameDrugStore.length() > 1) : "nameDrugStore must never be one letter";
        assert(telephoneDrugStore != "") : "telephoneDrugStore must never be empty";
        assert(telephoneDrugStore.length() >= 10) : "telephoneDrugStore must never be smaller"
                                                    +" than 10 characters"; 

    }

    /**
     * @return postalCode attribute
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode set attribute.
     */
    public void setPostalCode(String postalCode) {
        assert (postalCode.length() == 8): "postalCode must never be smaller then 0 characters";
        assert (postalCode != "") : "postalCode must never be empty";
        assert (postalCode != null) : "postalCode must never be null";

        this.postalCode = postalCode;
    }
       
}
