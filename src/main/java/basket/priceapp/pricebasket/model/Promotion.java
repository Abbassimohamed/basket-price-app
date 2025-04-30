package basket.priceapp.pricebasket.model;

public class Promotion {

    /** The product that triggered the promotion */
    private String productNameSource;
    /** The product receiving the discount  */
    private String productNameTarget;
    /** The amount of trigger product required to activate the promotion */
    private int quantity;
    /** The percentage of discount applied to the target product*/
    private double discount;

    public Promotion(String productSource, String productTarget, int quantity, double discount) {
        this.productNameSource = productSource;
        this.productNameTarget = productTarget;
        this.quantity = quantity;
        this.discount = discount;
    }

    public String getProductNameSource() {
        return productNameSource;
    }

    public void setProductNameSource(String productNameSource) {
        this.productNameSource = productNameSource;
    }

    public String getProductNameTarget() {
        return productNameTarget;
    }

    public void setProductNameTarget(String productNameTarget) {
        this.productNameTarget = productNameTarget;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
