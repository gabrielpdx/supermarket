package main.java.supermarket;


import main.java.supermarket.error.ShoppingCartCannotBeCreatedError;
import main.java.supermarket.error.ShoppingCartNotFoundError;
import main.java.supermarket.response.CartResponse;
import main.java.supermarket.response.CheckoutResponse;
import main.java.supermarket.response.JsonResponse;
import main.java.supermarket.response.PricerResponse;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grosati on 7/30/15.
 */

@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {

    //ObjectMapper mapper = new ObjectMapper();

    private ConcurrentHashMap<Integer, Register>
            carts = new ConcurrentHashMap();

    public Register getShoppingCart (Integer cartID)
            throws ShoppingCartNotFoundError {
        if (cartID != null) {
            Register returnCart = carts.get(cartID);
            if (returnCart != null) {
                    return returnCart;
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        throw new ShoppingCartNotFoundError(cartID);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public JsonResponse addShoppingCart (
            @RequestParam(value="newCartID", defaultValue="null")
            Integer ID) throws ShoppingCartCannotBeCreatedError {
        if (ID != null) {
            carts.put(ID, new Register());
            return new JsonResponse("Succesfully added cart", ID);
        }
        else {
            throw new ShoppingCartCannotBeCreatedError();
        }
    }


    @RequestMapping(value = "/{id}/**", method = RequestMethod.GET)
    public CartResponse getShoppingCartForClient(
            @PathVariable("id") Integer cartID) throws ShoppingCartNotFoundError {
        if (cartID != null) {
            Register returnCart = carts.get(cartID);
            if (returnCart != null) {
                    return new CartResponse("Cart found", cartID, returnCart.getAllPricers());
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        throw new ShoppingCartNotFoundError(cartID);
    }


    @RequestMapping(method = RequestMethod.POST)
    public PricerResponse addPricerToCart (
            @RequestParam(value="cartID", defaultValue="null") Integer cartID,
            @RequestParam(value="pricerKey", defaultValue = "null") Character pricerKey,
            @RequestParam(value="pricerPrice", defaultValue="null") Integer pricerPrice)
            throws ShoppingCartNotFoundError {

        if (cartID != null) {
            Register cart = carts.get(cartID);
            if (cart != null) {
                cart.addPricer(pricerKey, pricerPrice);
                return new PricerResponse("Successfully added Pricer", cartID, pricerKey, pricerPrice);
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        else {
            throw new ShoppingCartNotFoundError(cartID);
        }
    }


    @RequestMapping(value = "/special", method = RequestMethod.POST)
    public PricerResponse addPricerToCart (
            @RequestParam(value="cartID", defaultValue="null") Integer cartID,
            @RequestParam(value="pricerKey", defaultValue = "null") Character pricerKey,
            @RequestParam(value="pricerPrice", defaultValue="null") Integer pricerPrice,
            @RequestParam(value="pricerSpecialQuantity", defaultValue="null") Integer pricerSpecialQuantity,
            @RequestParam(value="pricerSpecialPrice", defaultValue="null") Integer pricerSpecialPrice)
            throws ShoppingCartNotFoundError {

        if (cartID != null) {
            Register cart = carts.get(cartID);
            if (cart != null) {
                cart.addPricer(pricerKey, pricerPrice, pricerSpecialQuantity, pricerSpecialPrice);
                return new PricerResponse("Successfully added Pricer", cartID, pricerKey,
                        pricerPrice, pricerSpecialQuantity, pricerSpecialPrice);
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        else {
            throw new ShoppingCartNotFoundError(cartID);
        }
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public CheckoutResponse checkout (
            @RequestParam(value="cartID", defaultValue="null") Integer cartID,
            @RequestParam(value="items", defaultValue="null") String itemsString) throws ShoppingCartNotFoundError {
        if (cartID != null) {
            Register cart = carts.get(cartID);
            if (cart != null) {
                return new CheckoutResponse("Checkout result", cartID, carts.get(cartID).getAllPricers(), carts.get(cartID).checkout(itemsString));
            }
            else throw new ShoppingCartNotFoundError(cartID);
        }
        else {
            throw new ShoppingCartNotFoundError(cartID);
        }
    }
}