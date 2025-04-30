package basket.priceapp.pricebasket.util;

import basket.priceapp.pricebasket.model.Basket;

import java.util.Locale;

public class Utils {

    private static final Locale LOCALE = Locale.UK;
    private static final String CURRENCY_SYMBOL = "£";

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

    public static void displayOfferDetails(double totalDiscount, StringBuilder offerDetails) {
        if (totalDiscount > 0) {
            System.out.println(offerDetails);
        } else {
            System.out.println("(No offers available)");
        }
    }

    public static String formatCurrency(double amount) {
        return CURRENCY_SYMBOL + String.format(LOCALE, "%.2f", amount);
    }
}
