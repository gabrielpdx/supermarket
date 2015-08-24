package test.java.supermarket;
import main.java.supermarket.*;
import org.junit.*;

/**
 * Created by grosati on 7/31/15.
 */
public class RegisterTest {

    private Register register;

    @Test
    public void testCheckoutWithNoPricer() {
        register = new Register();
        Assert.assertEquals("No pricers = 0", 0,
                register.checkout("ABBACBBAB"));
    }

    @Test
    public void testBasicPricerCreation() {
        register = new Register();
        register.addPricer('A', 10);
        Assert.assertEquals("5 for 50 = 50", 50, register.checkout("AAAAA"));
    }

    @Test
    public void testSpecialPricerCreation() {
        register = new Register();
        register.addPricer('A', 10, 3, 25);
        Assert.assertEquals("3 for 25 + 2 for 20 = 45", 45, register.checkout("AAAAA"));
    }

    @Test
    public void testCodesMissingPricers() {
        register = new Register();
        register.addPricer('A', 10);
        Assert.assertEquals("No rules installed for 'XYZ' = 0", 0, register.checkout("XYZ"));
        Assert.assertTrue("XYZ toggles Register.itemNotFound", register.invalidCheckoutOccurred());
    }
}
