package main.java.supermarket.response;

/**
 * Created by grosati on 8/6/15.
 */
public class PricerResponse extends JsonResponse {

    public Character key;
    public Integer price, specialQuantity, specialPrice;

    public PricerResponse(String response, Integer cart_id, Character key, Integer price) {
        super(response, cart_id);
        this.key = key;
        this.price = price;
    }

    public PricerResponse(String response, Integer cart_id, Character key, Integer price,
                          Integer specialQuantity, Integer specialPrice) {
        super(response, cart_id);
        this.key = key;
        this.price = price;
        this.specialQuantity = specialQuantity;
        this.specialPrice = specialPrice;
    }
}
