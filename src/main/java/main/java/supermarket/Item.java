package main.java.supermarket;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by grosati on 8/7/15.
 */
public class Item {

    protected char key;
    protected int quantity;

    public Item () {}

    public Item (char key, int quantity) {
        this.key = key;
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        ++quantity;
    }

    public void addToQuantity(int quantityToAdd) {
        quantity += quantityToAdd;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public char getKey() { return this.key; }

    public int getQuantity() { return this.quantity; }

    @JsonIgnore
    public String getString() {
        String string = new String();
        for (int i = 0; i < this.quantity; ++i) {
            string += key;
        }
        return string;
    }
}
