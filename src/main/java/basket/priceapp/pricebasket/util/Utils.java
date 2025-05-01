package basket.priceapp.pricebasket.util;

import basket.priceapp.pricebasket.model.Basket;

import java.util.Locale;

public class Utils {

    private static final Locale LOCALE = Locale.UK;
    private static final String CURRENCY_SYMBOL = "£";

    /**
     * calculate the subtotal of basket
     * @param args String[]
     * @param basket Basket
     * @return double
     */
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

    /**
     *  display the details of promotion if existe
     * @param totalDiscount double
     * @param offerDetails StringBuilder
     */
    public static void displayOfferDetails(double totalDiscount, StringBuilder offerDetails) {
        if (totalDiscount > 0) {
            System.out.println(offerDetails);
        } else {
            System.out.println("(No offers available)");
        }
    }

    /**
     * display the amount with the currency
     * @param amount double
     * @return String
     */
    public static String formatCurrency(double amount) {
        return CURRENCY_SYMBOL + String.format(LOCALE, "%.2f", amount);
    }
}
