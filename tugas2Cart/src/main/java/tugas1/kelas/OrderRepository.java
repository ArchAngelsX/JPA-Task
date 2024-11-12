package tugas1.kelas;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderRepository {

    private String filePath;

    public OrderRepository(ServletContext context) {
        // Get the real path of the logOrder.txt file in the WEB-INF folder
        this.filePath = context.getRealPath("/WEB-INF/logOrder.txt");
        System.out.println("Log file path: " + filePath);
        
        // Check if the file path is null
        if (this.filePath == null) {
            System.err.println("Error: filePath is null. Check if logOrder.txt exists in the WEB-INF directory.");
        }
    }

    public void saveOrder(OrderCart orderCart) {
        if (filePath == null) {
            System.err.println("Cannot save order: filePath is null.");
            return; // Exit the method if the file path is invalid
        }

        File logFile = new File(filePath);

        // Check if the file exists, and create it if it does not
        try {
            if (logFile.createNewFile()) {
                System.out.println("Log file created: " + logFile.getName());
            } else {
                System.out.println("Log file already exists.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating the log file.");
            e.printStackTrace();
            return; // Exit the method if file creation fails
        }

        // Now proceed to write to the file
        try (FileWriter fileWriter = new FileWriter(logFile, true); // Append mode
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Write order details to the file
            printWriter.println("Order Details:");
            for (OrderItem item : orderCart.getItems()) {
                printWriter.printf("Product Name: %s, Price: %.2f, Quantity: %d, Subtotal: %.2f%n",
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        item.getProduct().getPrice() * item.getQuantity());
            }
            printWriter.printf("Total: %.2f%n", calculateTotal(orderCart));
            printWriter.println("--------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace(); // Log the error (you may want to handle this differently)
        }
    }

    private double calculateTotal(OrderCart orderCart) {
        double total = 0;
        for (OrderItem item : orderCart.getItems()) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
}