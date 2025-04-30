package basket.priceapp.pricebasket.util;

import basket.priceapp.pricebasket.model.Basket;

public class Utils {

    public static double calculateSubtotal(String[] args, Basket basket) {
        double subtotal = 0.0;
        for (String arg : args) {
            if (!Catalog.exists(arg)) {
                throw new IllegalArgumentException("Unknown product : " + arg);
            }
            basket.addProduct(arg);
            subtotal += Catalog.getProduct(arg).getPrice();
        }
        return subtotal;
    }
}
