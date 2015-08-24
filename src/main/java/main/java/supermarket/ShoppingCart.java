package main.java.supermarket;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;


/**
 * Created by grosati on 8/7/15.
 */
public class ShoppingCart extends ItemTracker{

    private int ID;

    public ShoppingCart(int ID) {
        this.ID = ID;
    }
}
