import java.text.DecimalFormat;

/** Responsible for printing the report data. */
public class PrintProduct {

    private final IProduct product;

    public PrintProduct(IProduct product) {
        this.product = product;
    }

    public void PrintTotalProductSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void PrintAverageProductSales() {
        DecimalFormat df = new DecimalFormat("0.##");
        System.out.println("AVERAGE PRODUCT SALES: " + df.format(product.GetAverageProductSold()));
    }

    public void PrintMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }
}
