package basket.priceapp.pricebasket.service.impl;

import basket.priceapp.pricebasket.model.Basket;
import basket.priceapp.pricebasket.model.Product;
import basket.priceapp.pricebasket.model.Promotion;
import basket.priceapp.pricebasket.service.OfferService;
import basket.priceapp.pricebasket.util.Catalog;

import java.util.List;
import java.util.Map;

public class OfferServiceImpl implements OfferService {

    @Override
    public double apply(Basket basket, List<Promotion> promoList, StringBuilder sb) {

        if (promoList == null || promoList.isEmpty()) {
            return 0.0;
        }

        double totalDiscount = 0.0;
        Map<String, Integer> items = basket.getItems();

        // loop products in cart
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String productName = entry.getKey().toLowerCase();  // Product name in lower case
            int productCount = entry.getValue();  // Number of occurrences of the product in the cart

            Promotion promotion = promoList.stream().filter(pr -> pr.getProductNameSource()
                            .equalsIgnoreCase(productName))
                    .findFirst().orElse(null);
            if (promotion != null) {
                String targetProduct = promotion.getProductNameTarget();
                int targetCount = items.getOrDefault(targetProduct.toLowerCase(), 0);  // Number of target products in the basket

                // Verification of the number of eligible reductions
                int eligibleDiscounts = Math.min(productCount / promotion.getQuantity(), targetCount);

                if (eligibleDiscounts > 0) {
                    Product target = Catalog.getProduct(targetProduct);
                    if (target == null) continue;

                    // calculation of the amount of reduction per item
                    double discountPerItem = target.getPrice() * (promotion.getDiscount() / 100.0);
                    double totalPromoDiscount = eligibleDiscounts * discountPerItem;
                    totalDiscount += totalPromoDiscount;

                    sb.append(String.format(
                            "%s %.0f%% off: -%.0fp%n",
                            targetProduct,
                            promotion.getDiscount(),
                            totalPromoDiscount * 100));
                }
            }
        }

        return totalDiscount;
    }
}
