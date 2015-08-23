package main.java.supermarket.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.supermarket.ItemPricer;

import java.util.Collection;

/**
 * Created by grosati on 8/6/15.
 */
public class CheckoutResponse extends CartResponse {

    public Integer checkoutPrice;

    public CheckoutResponse(String initText, Integer initId, Collection<ItemPricer> items, Integer checkoutPrice) {
        super(initText, initId, items);
        this.checkoutPrice = checkoutPrice;
    }

    @JsonIgnore
    public String getSignature() {
        String output = new String();
        output += cart_id;
        output += response;
        for (ItemPricer item : items){
            output += item.getSignature();
        }
        output += checkoutPrice;
        return output;
    }
}
