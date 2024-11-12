package tugas1.servlet;

import java.io.IOException;
import javax.inject.Inject; // Import the @Inject annotation
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tugas1.service.ProductService;

@WebServlet("/admin/deleteProduct.do")
public class DeleteProductServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject // Use @Inject to inject the ProductService
    private ProductService productService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            request.setAttribute("error", "Product ID is required");
            request.getRequestDispatcher("/admin/product.do").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            productService.deleteProduct(id); // Use the injected ProductService
            response.sendRedirect(request.getContextPath() + "/admin/product.do"); // Redirect back to product list
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Product ID format");
            request.getRequestDispatcher("/admin/product.do").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework
            request.setAttribute("error", "An error occurred while deleting the product");
            request.getRequestDispatcher("/admin/product.do").forward(request, response);
        }
    }
}