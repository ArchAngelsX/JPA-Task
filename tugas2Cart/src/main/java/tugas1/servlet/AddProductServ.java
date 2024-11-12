package tugas1.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tugas1.kelas.Product;
import tugas1.service.ProductService;

@WebServlet("/admin/addProduct.do")
public class AddProductServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    private ProductService productService;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String priceStr = request.getParameter("price");
        String brand = request.getParameter("brand");
        String description = request.getParameter("description");

        // Input validation
        if (name == null || name.isEmpty() ||
            category == null || category.isEmpty() ||
            brand == null || brand.isEmpty() ||
            description == null || description.isEmpty() ||
            priceStr == null || priceStr.isEmpty()) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid price format");
            request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
            return;
        }

        // Create a new product entity
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setBrand(brand);
        product.setDescription(description);
        product.setPrice(price);

        try {
            // Use ProductService to add the product
            productService.addProduct(product);
            response.sendRedirect(request.getContextPath() + "/admin/product.do"); // Redirect back to product list
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred");
            request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
        }
    }


    
}