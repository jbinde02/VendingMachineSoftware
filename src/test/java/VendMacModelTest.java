import junit.framework.TestCase;

public class VendMacModelTest extends TestCase {
    VendMacModel vendMacModel = new VendMacModel();

    public void testLoadNewJSON() {
        vendMacModel.loadNewJSON("src/main/resources/inputTest.json");
        VendMacModel vendMacModel = new VendMacModel();
        vendMacModel.loadNewJSON("src/main/resources/inputTest.json");
        assertEquals(vendMacModel.getItem("A0").toString(), "Name: Snickers | Amount: 10 | Price: $1.35");
        assertNull(vendMacModel.getItem("C0"));
    }

    public void testGetItem() {
        assertNotNull(vendMacModel.getItem("A0"));
        assertNull(vendMacModel.getItem("0ABC"));
    }

    public void testIsItemAvailable() {
        assertTrue(vendMacModel.isItemAvailable("A0"));
        assertFalse(vendMacModel.isItemAvailable("0ABC"));
    }

    public void testAttemptPurchase() {
        assertEquals(vendMacModel.attemptPurchase("A0", "1.35"),
                "Purchase successful. 0.00 in change. Enjoy your Snickers!");
        assertEquals(vendMacModel.attemptPurchase("A0", "1.45"),
                "Purchase successful. 0.10 in change. Enjoy your Snickers!");
        assertEquals(vendMacModel.attemptPurchase("A0", "1.00"),
                "This item cost 0.35 more: Invalid purchase amount. Purchase failed!");
    }
}