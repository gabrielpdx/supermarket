package main.java.supermarket.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.supermarket.Item;
import main.java.supermarket.ItemPricer;

import java.util.Collection;

/**
 * Created by grosati on 8/6/15.
 */
public class CartResponse extends JsonResponse {

    public Collection<ItemPricer> items;

    public CartResponse(String initText, Integer initId, Collection<ItemPricer> items) {
        super(initText, initId);
        this.items = items;

        if (items.isEmpty()) {
            response += " (empty cart)";
        }
    }

    @JsonIgnore
    public String getSignature() {
        String output = new String();
        output += cart_id;
        output += response;
        for (ItemPricer item : items){
            output += item.getSignature();
        }
        return output;
    }
}
