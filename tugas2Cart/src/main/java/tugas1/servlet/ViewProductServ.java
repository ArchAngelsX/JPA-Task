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

@WebServlet("/admin/viewProduct.do")
public class ViewProductServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject // Use @Inject to inject the ProductService
    private ProductService productService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            request.setAttribute("error", "Product ID is required");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID format");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }

        try {
            // Use ProductService to find the product by ID
            Product product = productService.getProductById(id);
            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/viewProduct.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Product not found");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving product: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    
}