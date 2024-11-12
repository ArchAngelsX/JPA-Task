package tugas1.servlet;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tugas1.kelas.OrderCart;
import tugas1.kelas.Product;

import java.io.IOException;

@WebServlet("/admin/addToCart.do")
@Stateless // This makes the servlet a stateless EJB, which can be injected
public class OrderCartServAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION_KEY = "shoppingCart";

    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager entityManager;

    public OrderCartServAdmin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve or create a new OrderCart
        OrderCart cart = (OrderCart) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new OrderCart(); // Create a new OrderCart instance
            session.setAttribute(CART_SESSION_KEY, cart); // Save it in the session
        }

        // Get product ID and quantity from the request
        int productId = Integer.parseInt(request.getParameter("productId")); // Ensure this parameter exists
        int quantity = Integer.parseInt(request.getParameter("quantity")); // Ensure this parameter exists

        // Retrieve the product using JPA
        Product product = getProductById(productId);

        // Add item to the cart if the product is found
        if (product != null) {
            cart.addItem(product, quantity);
        }

        // Save the updated cart back to the session
        session.setAttribute(CART_SESSION_KEY, cart);

        // Debugging logs
        System.out.println("Adding item to cart: " + product.getName() + ", Quantity: " + quantity);
        System.out.println("Total items in cart: " + cart.getItems().size());

        // Forward to the cart page instead of redirecting
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/orderCartAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private Product getProductById(int productId) {
        return entityManager.find(Product.class, productId); // Use JPA to find the product by ID
    }
}