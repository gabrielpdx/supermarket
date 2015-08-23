package test.java.supermarket;

import main.java.supermarket.ItemTracker;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by grosati on 8/7/15.
 */
public class ItemTrackerTest {
    @Test
    public void testAddItem () {
        ItemTracker itemTracker = new ItemTracker();
        itemTracker.addItem('A',10);
        itemTracker.addToItem('A', 10);
        Assert.assertEquals(10, itemTracker.countItem('A'));
    }

    @Test
    public void testRemoveItem () {
        ItemTracker itemTracker = new ItemTracker();
        itemTracker.addItem('A',10);
        itemTracker.addToItem('A', 10);
        itemTracker.removeFromItem('A', 5);
        Assert.assertEquals(5, itemTracker.countItem('A'));
    }
}
