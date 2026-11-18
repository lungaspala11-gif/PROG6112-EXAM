/**
 * Holds the product names (single-dimensional array) and the monthly
 * sales figures (two-dimensional array: rows = products, columns = months)
 * and calculates the report values.
 */
public class Product implements IProduct {

    private final String[] productNames;   // single-dimensional array
    private final int[][] sales;           // two-dimensional array [product][month]

    public Product(String[] productNames, int[][] sales) {
        this.productNames = productNames;
        this.sales = sales;
    }

    /** Total number of products sold across all products and months. */
    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int p = 0; p < sales.length; p++) {
            for (int m = 0; m < sales[p].length; m++) {
                total += sales[p][m];
            }
        }
        return total;
    }

    /** Average number of products sold per product per month entry. */
    @Override
    public double GetAverageProductSold() {
        int count = 0;
        for (int p = 0; p < sales.length; p++) {
            count += sales[p].length;
        }
        if (count == 0) {
            return 0;
        }
        return (double) GetTotalProductsSold() / count;
    }

    /** Name of the most popular product, with its highest sales figure. */
    @Override
    public String GetMostPopularProduct() {
        int highest = Integer.MIN_VALUE;
        int bestProduct = 0;
        for (int p = 0; p < sales.length; p++) {
            for (int m = 0; m < sales[p].length; m++) {
                if (sales[p][m] > highest) {
                    highest = sales[p][m];
                    bestProduct = p;
                }
            }
        }
        return productNames[bestProduct] + " (" + highest + ")";
    }
}