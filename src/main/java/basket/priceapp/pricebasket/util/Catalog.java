package basket.priceapp.pricebasket.util;

import basket.priceapp.pricebasket.model.Product;
import basket.priceapp.pricebasket.model.Promotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {

    private static final Map<String, Product> products = new HashMap<>();

    private static final List<Promotion> promotions = new ArrayList<>();

    static {
        products.put("soup", new Product("soup", 0.65));
        products.put("bread", new Product("bread", 0.80));
        products.put("milk", new Product("milk", 1.30));
        products.put("apples", new Product("apples", 1.00));
    }

    static {
        promotions.add(new Promotion("Apples", "Apples", 1, 10));
        promotions.add(new Promotion("Soup", "Bread", 2, 50));
    }

    public static Product getProduct(String name) {
        return products.get(name.toLowerCase());
    }

    public static boolean exists(String name) {
        return products.containsKey(name.toLowerCase());
    }

    public static List<Promotion> getPromotions() {
        return promotions;
    }


}
