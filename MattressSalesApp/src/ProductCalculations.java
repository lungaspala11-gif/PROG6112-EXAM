public class ProductCalculations implements IProductCalculations {

    private static final double VAT_RATE = 0.15;
    private static final double COMMISSION_RATE = 0.05;

    private final double price;

    public ProductCalculations(double price) {
        this.price = price;
    }

    /** VAT: 15% of the product price. */
    @Override
    public double GetVat() {
        return price * VAT_RATE;
    }

    /** Commission: 5% of the product price. */
    @Override
    public double GetCommission() {
        return price * COMMISSION_RATE;
    }

    /** Total sale amount: price + VAT + commission. */
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}
