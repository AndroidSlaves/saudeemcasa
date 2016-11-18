package Model;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import mds.gpp.saudeemcasa.model.DrugStore;

public class TestModelDrugStore extends TestCase {

    @Test
    public void testGetSetPostalCodeValid() {
        DrugStore drugStore = new DrugStore();

        String postalCode = "72000000";

        drugStore.setPostalCode(postalCode);
        assertEquals(drugStore.getPostalCode(), postalCode);
    }
    @Test
    public void testGetSetPostalCodeValidWithSpecialCharacter() {
        DrugStore drugStore = new DrugStore();

        String postalCode = "72.000-000";

        drugStore.setPostalCode(postalCode);
        assertEquals(drugStore.getPostalCode(), postalCode);
    }
    @Test
    public void testGetSetPostalCodeInvalidTooSmall() {
        DrugStore drugStore = new DrugStore();

        String postalCode = "21";

        drugStore.setPostalCode(postalCode);
        assertEquals(drugStore.getPostalCode(), "");
    }
    @Test
    public void testGetSetPostalCodeInvalidTooBig() {
        DrugStore drugStore = new DrugStore();

        String postalCode = "21.000-00XXX";

        drugStore.setPostalCode(postalCode);
        assertEquals(drugStore.getPostalCode(), "");
    }
    @Test
    public void testGetSetPostalCodeInvalidNull() {
        DrugStore drugStore = new DrugStore();

        String postalCode = null;

        drugStore.setPostalCode(postalCode);
        assertEquals(drugStore.getPostalCode(), "");
    }


    @Test
    public void testDrugStoreEmptyConstructor(){
        DrugStore drugStore = new DrugStore();
        assertNotNull(drugStore);
    }
}
