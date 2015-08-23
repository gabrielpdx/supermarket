package main.java.supermarket;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;


/**
 * Created by grosati on 8/7/15.
 */
public class ShoppingCart extends ItemTracker{

    private int ID;

    @JsonIgnore
    public int checkout() {
        int totalPrice = 0;
        Collection<Item> allItems = getAllItems();

        for (Item item : allItems) {
            if (item.getQuantity() > 0) {
                int runningQuantity = item.getQuantity();
                int specialQuantity = item.getSpecialQuantity();

                if (item.isOnSpecial() && ((runningQuantity / specialQuantity) > 0)) {
                    int specialGroups = runningQuantity / specialQuantity;
                    totalPrice += specialGroups * item.getSpecialPrice();
                    runningQuantity = runningQuantity % specialQuantity;
                }

                totalPrice += runningQuantity * item.getPrice();
            }
        }

        return totalPrice;
    }
}
