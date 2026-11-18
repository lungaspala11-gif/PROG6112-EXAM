import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class ProductApp extends JFrame {

    private static final String FILE_NAME = "data.txt";
    private static final String STARS = "**********************************";

    private final JTextField txtCustomer = new JTextField();
    private final JComboBox<String> cmbProduct = new JComboBox<>(new String[]{
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    });
    private final JTextField txtPrice = new JTextField();
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildMenu();
        buildForm();
        setSize(480, 280);
        setLocationRelativeTo(null);
    }

    private void buildMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);

        JMenu tools = new JMenu("Tools");
        JMenuItem process = new JMenuItem("Process");
        JMenuItem clear = new JMenuItem("Clear");
        process.addActionListener(e -> processSale());   // same logic as the button
        clear.addActionListener(e -> clearForm());
        tools.add(process);
        tools.add(clear);

        bar.add(file);
        bar.add(tools);
        setJMenuBar(bar);
    }

    private void buildForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Customer Name:"));
        panel.add(txtCustomer);
        panel.add(new JLabel("Select Product:"));
        panel.add(cmbProduct);
        panel.add(new JLabel("Enter Price:"));
        panel.add(txtPrice);
        panel.add(new JLabel(""));
        panel.add(btnProcess);
        btnProcess.addActionListener(e -> processSale());
        add(panel);
    }

    private void processSale() {
        String customer = txtCustomer.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();

        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer's name.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
            if (price <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price greater than 0.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        IProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        String details = "Customer Name: " + customer + "\n"
                + "Product: " + product + "\n"
                + "VAT: R " + vat + "\n"
                + "COMMISSION: R " + commission + "\n"
                + "TOTAL: R " + total;

        JOptionPane.showMessageDialog(this, details, "Message", JOptionPane.INFORMATION_MESSAGE);
        saveToFile(details);
    }

    private void saveToFile(String details) {
        File file = new File(FILE_NAME);
        boolean isNew = !file.exists() || file.length() == 0;
        try (PrintWriter out = new PrintWriter(new FileWriter(file, true))) {
            if (isNew) {
                out.println("DATA LOG");
            }
            out.println(STARS);
            out.println(details);
            out.println(STARS);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save to " + FILE_NAME + ": " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCustomer.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
