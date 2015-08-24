package main.java.supermarket;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grosati on 8/7/15.
 */
public class ItemTracker {
    
    protected ConcurrentHashMap<Character, Item> items = new ConcurrentHashMap();

    public ItemTracker() {}

    public void add (char key, int quantity) {
        Item item = items.get(key);
        if (item == null) {
            items.put(key, new Item(key, quantity));
        }
        else {
            item.addToQuantity(quantity);
        }
    }

    public void addItem(char itemKey, int quantity) {
        items.put(itemKey, new Item(itemKey, quantity));
    }

    public void addToItem (Character key, int addQuantity) {
        Item additionItem = items.get(key);
        if (additionItem != null) {
            additionItem.addToQuantity(addQuantity);
        }
    }

    public void removeFromItem (Character key, Integer removeQuantity) {
        Item removalItem = items.get(key);
        if (removalItem != null) {
            int newQuantity = removalItem.getQuantity() - removeQuantity;
            newQuantity = newQuantity < 0 ? 0 : newQuantity;
            removalItem.setQuantity(newQuantity);
        }
    }

    public int countItem (Character key) {
        return items.get(key).getQuantity();
    }

    @JsonIgnore
    public Collection<Item> getAllItems() {
        return items.values();
    }

    @JsonIgnore
    public String getItemsAsString() {
        String string = new String();
        Collection<Item> allItems = items.values();
        for (Item item : allItems) {
            string = string.concat(item.getString());
        }
        return string;
    }
}
