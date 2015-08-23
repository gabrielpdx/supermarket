package test.java.supermarket;
import main.java.supermarket.*;
import org.junit.*;

/**
 * Created by grosati on 7/31/15.
 */
public class RegisterTest {

    private Register testMarket;

    @Test
    public void testCheckoutWithNoPricer() {
        testMarket = new Register();
        Assert.assertEquals("No pricers = 0", 0,
                testMarket.checkout("ABBACBBAB"));
    }

    @Test
    public void testBasicPricerCreation() {
        testMarket = new Register();
        testMarket.addPricer('A', 10);
        Assert.assertEquals("5 for 50 = 50", 50, testMarket.checkout("AAAAA"));
    }

    @Test
    public void testSpecialPricerCreation() {
        testMarket = new Register();
        testMarket.addPricer('A', 10, 3, 25);
        Assert.assertEquals("3 for 25 + 2 for 20 = 45", 45, testMarket.checkout("AAAAA"));
    }

    @Test
    public void testCodesMissingPricers() {
        testMarket = new Register();
        testMarket.addPricer('A', 10);
        Assert.assertEquals("No rules installed for 'XYZ' = 0", 0, testMarket.checkout("XYZ"));
        Assert.assertTrue("XYZ toggles Register.itemNotFound", testMarket.invalidCheckoutOccurred());
    }
}
