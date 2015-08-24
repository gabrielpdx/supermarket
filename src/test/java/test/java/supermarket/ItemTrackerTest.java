package test.java.supermarket;

import main.java.supermarket.ItemTracker;
import main.java.supermarket.Register;
import main.java.supermarket.ShoppingCart;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by grosati on 8/7/15.
 */
public class ItemTrackerTest {
    @Test
    public void testAddItem () {
        ItemTracker itemTracker = new ItemTracker();
        itemTracker.addItem('A', 10);
        Assert.assertEquals(10, itemTracker.countItem('A'));
    }

    @Test
    public void testRemoveItem () {
        ItemTracker itemTracker = new ItemTracker();
        itemTracker.addItem('A', 10);
        itemTracker.removeFromItem('A', 5);
        Assert.assertEquals(5, itemTracker.countItem('A'));
    }

    @Test
    public void testCheckout () {
        ShoppingCart shoppingCart = new ShoppingCart(42);
        Register register = new Register();
        register.addPricer('a', 50);
        register.addPricer('b', 100, 2, 75);
        shoppingCart.addItem('a',5); // price is 250
        shoppingCart.addItem('b', 3); // price is 75 + 100 = 175
        Assert.assertEquals(425, register.checkout(shoppingCart.getItemsAsString()));
    }
}
