package test.java.supermarket;

import main.java.supermarket.*;
import main.java.supermarket.error.ShoppingCartCannotBeCreatedError;
import main.java.supermarket.error.ShoppingCartError;
import main.java.supermarket.error.ShoppingCartNotFoundError;
import main.java.supermarket.response.CheckoutResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by grosati on 8/3/15.
 */
public class RegisterControllerTest {

    public Boolean cartsAreEqual(Register expected, Register toVerify) {
        Collection<ItemPricer> fromExpected = expected.getAllPricers();
        Collection<ItemPricer> fromToVerify = toVerify.getAllPricers();

        return (fromToVerify.containsAll(fromExpected) && fromExpected.containsAll(fromToVerify));
    }

    @Test (expected = ShoppingCartError.class)
    public void testCartPutNullID () throws ShoppingCartCannotBeCreatedError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.addShoppingCart(null);
    }

    @Test
    public void testCartPut () throws ShoppingCartNotFoundError, ShoppingCartCannotBeCreatedError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.addShoppingCart(42);
        Assert.assertFalse(shoppingCartController.getShoppingCart(42) == null);
    }

    @Test (expected = ShoppingCartError.class)
    public void testNullIdGet () throws ShoppingCartNotFoundError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        Integer cartID = null;
        Register register = shoppingCartController.getShoppingCart(cartID);
    }

    @Test (expected = ShoppingCartError.class)
    public void testDNEGet () throws ShoppingCartNotFoundError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        Register register = shoppingCartController.getShoppingCart(42);
    }

    @Test
    public void testGet () throws ShoppingCartNotFoundError, ShoppingCartCannotBeCreatedError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.addShoppingCart(42);
        shoppingCartController.addPricerToCart(42, 'A', 20);

        Register protoCart = new Register();
        protoCart.addPricer('A', 20);

        Assert.assertTrue(protoCart.stringRep.equals(shoppingCartController.getShoppingCart(42).stringRep));
    }

    @Test (expected = ShoppingCartError.class)
    public void testAddPricerToNullCart() throws ShoppingCartNotFoundError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.addPricerToCart(null, 'A', 10);

    }

    @Test (expected = ShoppingCartError.class)
    public void testAddPricerToDNECart() throws ShoppingCartNotFoundError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.addPricerToCart(42,'A',10);
    }

    @Test
    public void testAddPricer() throws ShoppingCartNotFoundError, ShoppingCartCannotBeCreatedError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.addShoppingCart(42);
        shoppingCartController.addPricerToCart(42, 'A', 10);

        Register protoCart = new Register();
        protoCart.addPricer('A', 10);

        Assert.assertEquals(protoCart.getSignature(), shoppingCartController.getShoppingCart(42).getSignature());
    }

    @Test
    public void testAddPricerWithSpecial() throws ShoppingCartNotFoundError, ShoppingCartCannotBeCreatedError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.addShoppingCart(42);
        shoppingCartController.addPricerToCart(42, 'A', 10, 6, 50);

        Register protoCart = new Register();
        protoCart.addPricer('A', 10, 6, 50);

        Assert.assertEquals(protoCart.getSignature(), shoppingCartController.getShoppingCart(42).getSignature());
    }

    @Test
    public void testCheckoutResponseCreation() throws ShoppingCartNotFoundError, ShoppingCartCannotBeCreatedError {
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        shoppingCartController.addShoppingCart(42);
        shoppingCartController.addPricerToCart(42, 'B', 10, 6, 50);
        shoppingCartController.addPricerToCart(42, 'A', 10);
        CheckoutResponse checkoutToVerify = shoppingCartController.checkout(42, "ABBBBBB");

        Register protoCart = new Register();
        protoCart.addPricer('B', 10, 6, 50);
        protoCart.addPricer('A', 10);
        CheckoutResponse goodCheckout =
                new CheckoutResponse
                        ("Checkout result", 42, protoCart.getAllPricers(), protoCart.checkout("ABBBBBB"));

        Assert.assertEquals(goodCheckout.getSignature(), checkoutToVerify.getSignature());
    }
}
