package main.java.supermarket;

import main.java.supermarket.error.ShoppingCartError;
import main.java.supermarket.error.ShoppingCartNotFoundError;
import main.java.supermarket.error.inventoryError;
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
@RequestMapping(value = "/")
public class Supermarket {
    public static void main (String[] args) {
        SpringApplication.run(Supermarket.class, args);
    }

    private InventoryManager inventory;
    private ConcurrentHashMap<Integer, ShoppingCart>
            carts = new ConcurrentHashMap<>();
    private Register register = new Register();

    @RequestMapping(value="/inventory", method = RequestMethod.GET)
    public ItemTracker getInventory () throws inventoryError {
        if (inventory != null) {
            return inventory;
        }
        else {
            throw new inventoryError("Inventory is empty.");
        }
    }

    @RequestMapping(value="/inventory/{key}**", method = RequestMethod.PUT)
    public String addItemToSystem (
            @PathVariable(value="key") Character key,
            @RequestParam(value="quantity", defaultValue="null") Integer quantity,
            @RequestParam(value="price", defaultValue="null") Integer price,
            @RequestParam(value="specialQuantity", defaultValue="null") Integer specialQuantity,
            @RequestParam(value="specialPrice", defaultValue="null") Integer specialPrice)
        throws inventoryError {
        if (key != null && price != null && price >= 0 && quantity != null && quantity >= 0) {
            if (inventory == null) {
                inventory = new InventoryManager();
            }

            if (specialPrice != null && specialPrice >=0
                    && specialQuantity != null && specialQuantity >= 0) {
                register.addPricer(key, price, specialQuantity, specialPrice);
            }
            else {
                register.addPricer(key, price);
            }
            inventory.addItem(key, quantity);
            return "Successfully added item " + key + "x" + quantity + " to inventory system.";
        }
        else throw new inventoryError("Error adding item to inventory system - check parameters.");
    }

    @RequestMapping(value="/inventory/{key}/", method = RequestMethod.POST)
    public String modifyItemQuantity (
            @PathVariable(value="key") Character key,
            @RequestParam(value="operation", defaultValue="add") String operation,
            @RequestParam(value="amount", defaultValue="null") Integer amount)
        throws inventoryError {
        if (key != null && operation != null && (operation == "add" || operation == "subtract")
                && amount != null && amount >= 0) {
            if (operation == "add") {
                inventory.addToItem(key, amount);
            } else if (operation == "subtract") {
                inventory.removeFromItem(key, amount);
            }
            return "Successfully added " + amount + " to item " + key + " inventory";
        }
        else throw new inventoryError("Error adding to inventory item - check parameters.");
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.PUT)
    public String addShoppingCart(
            @PathVariable("id") Integer cartID) throws ShoppingCartError {
        carts.put(cartID, new ShoppingCart(cartID));
        return new String("Cart created");
    }

    @RequestMapping(value = "/carts/{id}/items", method = RequestMethod.POST)
    public String addItemToCart (
            @PathVariable("id") Integer cartID,
            @RequestParam(value="key", defaultValue = "null") Character key,
            @RequestParam(value="quantity", defaultValue="null") Integer quantity)
            throws ShoppingCartNotFoundError {

        if (cartID != null) {
            ShoppingCart cart = carts.get(cartID);
            if (cart != null) {
                cart.add(key, inventory.getItem(key, quantity));
                return new String("Successfully added item");
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        else {
            throw new ShoppingCartNotFoundError(cartID);
        }
    }

    @RequestMapping(value = "/carts/{id}/checkout", method = RequestMethod.GET)
    public Integer getCheckoutPrice (
            @PathVariable("id") Integer cartID) throws ShoppingCartNotFoundError {
        ShoppingCart cart = carts.get(cartID);
        if (cart != null) {
            return register.checkout(cart.getItemsAsString());
        }
        else {
            throw new ShoppingCartNotFoundError(cartID);
        }

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
