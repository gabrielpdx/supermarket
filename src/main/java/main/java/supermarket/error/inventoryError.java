package main.java.supermarket.error;

/**
 * Created by gaber on 8/24/15.
 */
public class inventoryError extends Exception {
    public inventoryError (String message){
        super(message);
        System.err.println(message);
    }
}
