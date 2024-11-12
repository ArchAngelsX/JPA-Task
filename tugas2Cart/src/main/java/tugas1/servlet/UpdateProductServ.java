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

@WebServlet("/admin/updateProduct.do")
public class UpdateProductServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    private ProductService productService;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String priceStr = request.getParameter("price");
        String brand = request.getParameter("brand");
        String description = request.getParameter("description");

        // Input validation
        if (idStr == null || idStr.isEmpty() ||
            name == null || name.isEmpty() ||
            category == null || category.isEmpty() ||
            brand == null || brand.isEmpty() ||
            description == null || description.isEmpty() ||
            priceStr == null || priceStr.isEmpty()) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("/WEB-INF/updateProduct.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID");
            request.getRequestDispatcher("/WEB-INF/updateProduct.jsp").forward(request, response);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid price format");
            request.getRequestDispatcher("/WEB-INF/updateProduct.jsp").forward(request, response);
            return;
        }

        // Create a Product object and set its properties
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCategory(category);
        product.setBrand(brand);
        product.setDescription(description);
        product.setPrice(price);

        // Update the product using ProductService
        try {
            productService.updateProduct(product);
            response.sendRedirect(request.getContextPath() + "/admin/product.do"); // Redirect back to product list
        } catch (Exception e) {
            request.setAttribute("error", "Error updating product: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/updateProduct.jsp").forward(request, response);
        }
    }

    
}