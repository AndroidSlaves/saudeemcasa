package Model;

import junit.framework.TestCase;

import org.junit.Test;

import mds.gpp.saudeemcasa.model.DrugStore;

public class TestModelDrugStore extends TestCase {

    @Test
    public void testSetPostalCode() {
        DrugStore drugStore = new DrugStore();
        drugStore.setPostalCode("2035");
        assertTrue(drugStore.getPostalCode() == "2035");
    }

    @Test
    public void testGetPostalCode() {
        DrugStore drugStore = new DrugStore();
        drugStore.setPostalCode("2034");
        assertEquals("2034", drugStore.getPostalCode());
    }

    @Test
    public void testDrugStore(){
        DrugStore drugStore = new DrugStore();
        if(drugStore == null){
            assertTrue(false);
        }else {
            assertTrue(true);
        }
    }
}
