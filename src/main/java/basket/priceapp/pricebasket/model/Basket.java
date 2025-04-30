package basket.priceapp.pricebasket.model;

import java.util.*;

public class Basket {

    private final Map<String, Integer> items = new HashMap<>();

    public void addProduct(String name) {
        name = name.toLowerCase();
        items.put(name, items.getOrDefault(name, 0) + 1);
    }

    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
