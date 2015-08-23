package main.java.supermarket;

import main.java.supermarket.error.ShoppingCartError;
import main.java.supermarket.error.ShoppingCartNotFoundError;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grosati on 7/27/15.
 */

@SpringBootApplication
@RestController
@RequestMapping(value = "/supermarket")
public class Supermarket {
    public static void main (String[] args) {
        SpringApplication.run(Supermarket.class, args);
    }

    private InventoryManager inventory = new InventoryManager();
    private ConcurrentHashMap<Integer, ShoppingCart>
            carts = new ConcurrentHashMap<>();
    private Register register;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ItemTracker getInventory () {
        return inventory;
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.PUT)
    public String addShoppingCart(
            @PathVariable("id") Integer cartID) throws ShoppingCartError {
        carts.put(cartID, new ShoppingCart());
        return new String("Cart created");
    }

    @RequestMapping(value = "/carts/{id}/items", method = RequestMethod.POST)
    public String addItemToCart (
            @PathVariable("id") Integer cartID,
            @RequestParam(value="itemKey", defaultValue = "null") Character itemKey,
            @RequestParam(value="itemPrice", defaultValue="null") Integer itemPrice)
            throws ShoppingCartNotFoundError {

        if (cartID != null) {
            ShoppingCart cart = carts.get(cartID);
            if (cart != null) {
                cart.addItem(itemKey, itemPrice);
                return new String("Successfully added item");
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        else {
            throw new ShoppingCartNotFoundError(cartID);
        }
    }

    @RequestMapping(value = "/carts/{id}/items/specials", method = RequestMethod.POST)
    public String addItemToCart (
            @PathVariable("id") Integer cartID,
            @RequestParam(value="itemKey", defaultValue = "null") Character itemKey,
            @RequestParam(value="itemPrice", defaultValue="null") Integer itemPrice,
            @RequestParam(value="itemSpecialQuantity", defaultValue="null") Integer itemSpecialQuantity,
            @RequestParam(value="itemSpecialPrice", defaultValue="null") Integer itemSpecialPrice)
            throws ShoppingCartNotFoundError {

        if (cartID != null) {
            ShoppingCart cart = carts.get(cartID);
            if (cart != null) {
                cart.addItem(itemKey, itemPrice, itemSpecialQuantity, itemSpecialPrice);
                return new String("Successfully added item");
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        else {
            throw new ShoppingCartNotFoundError(cartID);
        }
    }

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public Set<Map.Entry<Integer, ShoppingCart>> getShoppingCartForClient () throws ShoppingCartError {
        if (carts != null) {
            return carts.entrySet();
        }
        throw new ShoppingCartError("No carts to display");
    }

    @RequestMapping(value = "/carts/{id}/**", method = RequestMethod.GET)
    public ShoppingCart getShoppingCartForClient(
            @PathVariable("id") Integer cartID) throws ShoppingCartNotFoundError {
        if (cartID != null) {
            ShoppingCart returnCart = carts.get(cartID);
            if (returnCart != null) {
                return returnCart;
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        throw new ShoppingCartNotFoundError(cartID);
    }

}
