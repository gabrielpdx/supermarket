package main.java.supermarket;

/**
 * Created by grosati on 8/22/15.
 */
public class InventoryManager extends ItemTracker {

    public InventoryManager () {
        super();
    }

    public Integer getItem (Character itemKey, Integer quantity) {
        if (items.containsKey(itemKey)){
            if (countItem(itemKey) >= quantity){
                removeFromItem(itemKey, quantity);
                return quantity;
            }
            else {
                return 0;
            }
        }
        else return null;
    }
}