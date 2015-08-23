package main.java.supermarket.error;

/**
 * Created by grosati on 8/3/15.
 */

public class ShoppingCartError extends Exception {
    public ShoppingCartError (String message){
        super(message);
        System.err.println(message);
    }
}
