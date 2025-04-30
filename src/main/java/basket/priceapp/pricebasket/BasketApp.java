package basket.priceapp.pricebasket;

import basket.priceapp.pricebasket.model.Basket;
import basket.priceapp.pricebasket.model.Promotion;
import basket.priceapp.pricebasket.service.OfferService;
import basket.priceapp.pricebasket.service.impl.OfferServiceImpl;
import basket.priceapp.pricebasket.util.Catalog;
import basket.priceapp.pricebasket.util.Utils;

import java.util.List;
import java.util.Locale;

public class BasketApp {

    public static void main(String[] args) {
        if (args.length < 1) {
            printUsageAndExit();
        }

        Basket basket = new Basket();
        double subtotal = Utils.calculateSubtotal(args, basket);

        System.out.println("Subtotal: £" + String.format(Locale.US, "%.2f", subtotal));

        OfferService offer = new OfferServiceImpl();
        StringBuilder offerDetails = new StringBuilder();
        List<Promotion> appliedPromos = Catalog.getPromotions();
        double totalDiscount = offer.apply(basket, appliedPromos, offerDetails);

        displayOfferDetails(totalDiscount, offerDetails);
        System.out.println("Total: £" + String.format(Locale.US, "%.2f", (subtotal - totalDiscount)) + "\n");
    }

    private static void printUsageAndExit() {
        System.err.println(" item1 item2 item3 ...");
        System.exit(1);
    }

    private static void displayOfferDetails(double totalDiscount, StringBuilder offerDetails) {
        if (totalDiscount > 0) {
            System.out.println(offerDetails);
        } else {
            System.out.println("(No offers available)");
        }
    }


}
