package main.java.supermarket;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by grosati on 8/7/15.
 */
public class Item {

    protected char key;
    protected int price;
    protected int quantity;
    protected boolean onSpecial;
    protected int specialQuantity;
    protected int specialPrice;

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

        public Item build() {
            return new Item(this);
        }
    }


    protected Item(Builder builder) {
        key = builder.key;
        price = builder.price;
        quantity = builder.quantity;
        onSpecial = builder.onSpecial;

        specialQuantity = builder.specialQuantity;
        specialPrice = builder.specialPrice;
    }

    public Item () {}

    public void incrementQuantity() {
        ++quantity;
    }

    public void addToQuantity(int quantityToAdd) {
        quantity += quantityToAdd;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public char getKey() {
        return this.key;
    }

    public int getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public boolean isOnSpecial() { return this.onSpecial; }

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
