package basket.priceapp.pricebasket.service;

import basket.priceapp.pricebasket.model.Basket;
import basket.priceapp.pricebasket.model.Promotion;

import java.util.List;

public interface OfferService {

    /**
     * Applies available promotions to a basket and calculates the total discount.
     * @param basket Basket
     * @param promos List<Promotion>
     * @param discountDetails StringBuilder
     * @return double
     */
    double apply(Basket basket, List<Promotion> promos, StringBuilder discountDetails);
}
