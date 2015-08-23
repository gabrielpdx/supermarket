package test.java.supermarket;

import main.java.supermarket.*;
import org.junit.*;

/**
 * Created by grosati on 7/31/15.
 */
public class ItemPricerTest {

    ItemPricer testPricer;

    @Before
    public void initChecker() {

    }

    @Test
    public void testNoItems() {
        testPricer = new ItemPricer.Builder('A', 20).build();
        Assert.assertEquals(0, testPricer.getCheckoutPrice());
    }

    @Test
    public void getPriceFromChecker() {
        testPricer = new ItemPricer.Builder('A', 500).build();
        testPricer.addToQuantity(10);
        Assert.assertEquals("10 items * 500 per = 5,000", 5000, testPricer.getCheckoutPrice());
    }

    @Test
    public void negativePrice() {
        testPricer = new ItemPricer.Builder('A', -42).build();
        testPricer.addToQuantity(100);
        Assert.assertEquals("Negative price corrected to 0", 0, testPricer.getCheckoutPrice());
    }

    @Test
    public void straightCheckoutTest() {
        testPricer = new ItemPricer.Builder('A', 10).build();
        Assert.assertEquals(100, testPricer.checkout(10));
    }

    @Test
    public void specialCheckoutTest() {
        testPricer = new ItemPricer.Builder('A', 10).special(3, 20).build();
        Assert.assertEquals(20, testPricer.checkout(3));
    }
}
