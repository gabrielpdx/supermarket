package main.java.supermarket.error;

import main.java.supermarket.error.ShoppingCartError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by grosati on 8/3/15.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Null ID")
public class ShoppingCartCannotBeCreatedError extends ShoppingCartError {
    public ShoppingCartCannotBeCreatedError() {
        super("Cannot create a shopping cart with null ID");
    }
}
