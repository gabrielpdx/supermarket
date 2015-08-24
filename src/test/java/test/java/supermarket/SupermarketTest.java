//package test.java.supermarket;
//
//import main.java.supermarket.ShoppingCart;
//import main.java.supermarket.Supermarket;
//import main.java.supermarket.error.ShoppingCartError;
//import org.junit.Assert;
//import org.junit.Test;
//
///**
// * Created by grosati on 8/10/15.
// */
//public class SupermarketTest {
//
//    @Test
//    public void testSupermarketItemAdd() throws ShoppingCartError {
//        Supermarket supermarket = new Supermarket();
//        supermarket.addShoppingCart(42);
//        supermarket.addItemToCart(42, 'A', 10);
//        supermarket.addItemToCart(42, 'B', 10, 3, 25);
//
//        ShoppingCart shoppingCart = new ShoppingCart(id);
//        shoppingCart.addItem('A', 10);
//        shoppingCart.addItem('A', 10, 3,25);
//
//        Assert.assertEquals(
//                supermarket.getShoppingCartForClient(42).getSignature(), shoppingCart.getSignature());
//    }
//
//    @Test
//    public void testSupermarketCheckout() throws ShoppingCartError {
//        Supermarket supermarket = new Supermarket();
//        supermarket.addShoppingCart(42);
//        supermarket.addItemToCart(42, 'A', 10);
//        supermarket.addItemToCart(42, 'B', 10, 3, 25);
//
//        ShoppingCart shoppingCart = new ShoppingCart(id);
//        shoppingCart.addItem('A', 10);
//        shoppingCart.addItem('A', 10, 3,25);
//
//        Assert.assertEquals(supermarket.getShoppingCartForClient(42).checkout(),
//                shoppingCart.checkout());
//    }
//}
