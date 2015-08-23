package main.java.supermarket.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by grosati on 8/3/15.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such cart")
public class ShoppingCartNotFoundError extends ShoppingCartError{
    public ShoppingCartNotFoundError(Integer requestedCartID) {
            super("Shopping cart ID " +
                    ((requestedCartID==null) ? "null" : requestedCartID.toString())+
                    " does not exist");
    }
}
