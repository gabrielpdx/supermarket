package main.java.supermarket;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grosati on 8/7/15.
 */
public class ItemTracker {
    
    protected ConcurrentHashMap<Character, Item> items = new ConcurrentHashMap();
    public String stringRep = new String();

    public ItemTracker() {}

    public void addItem(char ItemKey, int ItemPrice) {
        items.put(ItemKey, new Item.Builder(ItemKey, ItemPrice).build());
    }

    public void addItem(char ItemKey, int ItemPrice,
                          Integer ItemSpecialQuantity, Integer ItemSpecialPrice) {
        if (ItemSpecialQuantity == null || ItemSpecialPrice == null) {
            items.put(ItemKey, new Item.Builder(ItemKey, ItemPrice).build());
        }
        else {
            items.put(ItemKey,
                    new Item.Builder(ItemKey,
                            ItemPrice).special(ItemSpecialQuantity, ItemSpecialPrice).build());
        }
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

    public Collection<Item> getAllItems() {
        return items.values();
    }

    public String getSignature() {
        String signature = new String();
        Collection<Item> allItems = items.values();
        for (Item Item : allItems) {
            signature = signature.concat(Item.getSignature());
        }
        return signature;
    }

//    protected ConcurrentHashMap<Character, Item>
//            items = new ConcurrentHashMap();
//
//    public ItemTracker() {
//    }
//
//    public void addItem (Character key, Item item) {
//        items.put(key, item);
//    }
//
//    public void addToItem (Character key, int quantity) {
//        Integer currentQuantity = items.get(key).getQuantity();
//        items.put(key, (currentQuantity == null ? addQuantity : currentQuantity + addQuantity));
//    }
//
//    public int countItem (Character key) {
//        return items.get(key);
//    }
//
//    public void removeFromItem (Character key, Integer removeQuantity) {
//        Integer currentQuantity = items.get(key);
//        if (currentQuantity != null) {
//            currentQuantity -= removeQuantity;
//            items.put(key, (currentQuantity < 0 ? 0 : currentQuantity));
//        }
//    }
}
