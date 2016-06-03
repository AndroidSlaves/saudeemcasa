/************************
 * Class name: DrugStore (.java)
 *
 * Purpose: Represents a generic drugstore with its attributes.
 ************************/

package mds.gpp.saudeemcasa.model;

public class DrugStore extends Stablishment {

    // Represents the postal code which complements the address of a drugstore.
    private String postalCode = "";

    // Method that creates an empty drugstore (constructor)
    public DrugStore() {
        // Empty constructor
    }

    /**
     * Method that creates a constructor with the drugstore basic information, name and telephone.
     *
     * @param nameDrugStore
     *              Pharmaceutical establishment name to be created.
     * @param telephoneDrugStore
     *              Telephone contact pharmacy which will be created.
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
     * Recover storage string postcode.
     *
     * @return postalCode
     *              Code developed by the postal administrations used as guidance location.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Assign a postal code with size eight characters.
     *
     * @param postalCode
     *              Set the code used as guidance location.
     */
    public void setPostalCode(String postalCode) {
        assert (postalCode.length() == 8): "postalCode must never be smaller then 0 characters";
        assert (postalCode != "") : "postalCode must never be empty";
        assert (postalCode != null) : "postalCode must never be null";

        this.postalCode = postalCode;
    }
       
}
