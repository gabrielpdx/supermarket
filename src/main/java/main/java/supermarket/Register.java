package main.java.supermarket;


import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grosati on 7/27/15.
 */
public class Register {

    private ConcurrentHashMap<Character, ItemPricer> itemPricers = new ConcurrentHashMap();
    private boolean checkedCodeWithoutPricer = false;
    public String stringRep = new String();

    public Register() {}

    public void addPricer(char pricerKey, int pricerPrice) {
        itemPricers.put(pricerKey, new ItemPricer.Builder(pricerKey, pricerPrice).build());
    }

    public void addPricer(char pricerKey, int pricerPrice,
                          int pricerSpecialQuantity, int pricerSpecialPrice) {
        itemPricers.put(pricerKey, new ItemPricer.Builder(
                pricerKey, pricerPrice).special(pricerSpecialQuantity, pricerSpecialPrice).build());
    }


    public int checkout (String itemsToCheckout) {
        int totalPrice = 0;
        char [] keysToCheckout = itemsToCheckout.toCharArray();
        Collection<ItemPricer> allItemPricers = itemPricers.values();

        for (char key : keysToCheckout) {
            if (itemPricers.containsKey(key)) {
                itemPricers.get(key).incrementQuantity();
            }
            else checkedCodeWithoutPricer = true;
        }

        for (ItemPricer itemPricer : allItemPricers) {
            totalPrice += itemPricer.getCheckoutPrice();
        }

        return totalPrice;
    }


    public boolean invalidCheckoutOccurred() {
        return checkedCodeWithoutPricer;
    }

    public Collection<ItemPricer> getAllPricers() {
        return itemPricers.values();
    }

    public String getSignature() {
        String signature = new String();
        Collection<ItemPricer> allPricers = itemPricers.values();
        for (ItemPricer pricer : allPricers) {
           signature = signature.concat(pricer.getSignature());
        }
        return signature;
    }

}
