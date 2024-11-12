package tugas1.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject; // Import the @Inject annotation
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tugas1.kelas.Product;
import tugas1.service.ProductService;

@WebServlet("/product.do")
public class ProductServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject // Inject the ProductService
    private ProductService productService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products;

        try {
            // Use ProductService to retrieve products
            products = productService.getAllProducts();

            // Set up session for Order Cart
            HttpSession session = request.getSession();
            OrderCartServ cart = (OrderCartServ) session.getAttribute("cart");
            if (cart == null) {
                cart = new OrderCartServ();
                session.setAttribute("cart", cart);
            }

            // Forward to JSP
            request.setAttribute("products", products);
            request.getRequestDispatcher("/WEB-INF/productList.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}