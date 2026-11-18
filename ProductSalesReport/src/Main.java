import java.util.Scanner;

public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] productNames = {
                "Cozy Nights Monarch mattress",
                "Restonic Barbados mattress",
                "Sealy Phuket mattress"
        };
        final int MONTHS = 3;
        int[][] sales = new int[productNames.length][MONTHS];

        // Capture sales for every product for every month
        for (int p = 0; p < productNames.length; p++) {
            for (int m = 0; m < MONTHS; m++) {
                sales[p][m] = readInt(input,
                        "Enter number of sales for month " + (m + 1) + " for " + productNames[p] + ": ");
            }
            System.out.println();
        }

        Product product = new Product(productNames, sales);
        PrintProduct printer = new PrintProduct(product);

        System.out.println();
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println();
        System.out.println("**********************************************");
        printer.PrintTotalProductSales();
        printer.PrintAverageProductSales();
        printer.PrintMostPopularProduct();
    }

    /** Keeps asking until a valid non-negative whole number is entered. */
    private static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= 0) {
                    return value;
                }
                System.out.println("Please enter a number of 0 or more.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

