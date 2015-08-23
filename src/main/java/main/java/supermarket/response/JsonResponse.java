package main.java.supermarket.response;

/**
 * Created by grosati on 8/6/15.
 */
public class JsonResponse {
    public String response;
    public int cart_id;

    public JsonResponse(String response, Integer cart_id){
        this.response = response;
        this.cart_id = cart_id;
    }
}
