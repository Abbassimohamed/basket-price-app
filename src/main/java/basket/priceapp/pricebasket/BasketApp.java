package basket.priceapp.pricebasket;

import basket.priceapp.pricebasket.model.Basket;
import basket.priceapp.pricebasket.model.Promotion;
import basket.priceapp.pricebasket.service.OfferService;
import basket.priceapp.pricebasket.service.impl.OfferServiceImpl;
import basket.priceapp.pricebasket.util.Catalog;
import basket.priceapp.pricebasket.util.Utils;
import java.util.logging.Logger;

import java.util.List;

public class BasketApp {
    static Logger logger = Logger.getLogger(BasketApp.class.getName());

    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            logger.info("No items provided.");
            System.exit(1);
        }

        Basket basket = new Basket();
        double subtotal = Utils.calculateSubtotal(args, basket);

        System.out.println("Subtotal: " + Utils.formatCurrency(subtotal));

        OfferService offer = new OfferServiceImpl();
        StringBuilder offerDetails = new StringBuilder();
        List<Promotion> appliedPromos = Catalog.getPromotions();
        double totalDiscount = offer.apply(basket, appliedPromos, offerDetails);

        Utils.displayOfferDetails(totalDiscount, offerDetails);
        System.out.println("Total: " + Utils.formatCurrency(subtotal - totalDiscount) + "\n");
    }



}
