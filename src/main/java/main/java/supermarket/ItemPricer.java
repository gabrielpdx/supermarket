package main.java.supermarket;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by grosati on 7/27/15.
 */
public class ItemPricer {

    private char key;
    private int price;
    private int quantity;
    private boolean onSpecial;
    private int specialQuantity;
    private int specialPrice;


    public static class Builder {
        private char key;
        private int price;
        private int quantity = 0;
        private boolean onSpecial = false;
        private int specialQuantity = -1;
        private int specialPrice = -1;

        public Builder (char key, int initPrice) {
            this.key = key;
            this.price = initPrice > 0 ? initPrice : 0;
        }

        public Builder special (int quantity, int price) {
            onSpecial = true;
            specialQuantity = quantity;
            specialPrice = price;
            return this;
        }

        public ItemPricer build() {
            return new ItemPricer(this);
        }
    }


    private ItemPricer(Builder builder) {
        key = builder.key;
        price = builder.price;
        quantity = builder.quantity;
        onSpecial = builder.onSpecial;

        specialQuantity = builder.specialQuantity;
        specialPrice = builder.specialPrice;
    }


    public void incrementQuantity() {
        ++quantity;
    }

    public void addToQuantity(int quantityToAdd) {
        quantity += quantityToAdd;
    }

    // Delete quantity member and wire getCheckoutPrice here
    @JsonIgnore
    public int checkout(int quantity) {
        this.quantity = quantity;
        return getCheckoutPrice();
    }

    @JsonIgnore
    public int getCheckoutPrice() {
        int totalPrice = 0;

        if (quantity > 0) {
            int runningQuantity = quantity;

            if (onSpecial && ((runningQuantity / specialQuantity) > 0)) {
                int specialGroups = runningQuantity / specialQuantity;
                totalPrice += specialGroups * specialPrice;
                runningQuantity = runningQuantity % specialQuantity;
            }

            totalPrice += runningQuantity * price;
        }

        return totalPrice;
    }

    public char getKey() {
        return this.key;
    }

    public int getPrice() {
        return this.price;
    }

    public int getSpecialQuantity() {
        return this.specialQuantity;
    }

    public int getSpecialPrice() {
        return this.specialPrice;
    }

    @JsonIgnore
    public String getSignature() {
        String signature = new String();
        signature += this.key;
        signature += this.price;
        signature += this.specialQuantity;
        signature += this.specialPrice;
        return signature;
    }
}
