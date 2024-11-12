package tugas1.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tugas1.kelas.OrderCart;
import tugas1.kelas.OrderRepository;

@WebServlet("/admin/placeOrder.do")
public class PlaceOrderServletAdmin extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderRepository orderRepository; // Assuming you have a repository class

	@Override
    public void init() throws ServletException {
        // Pass the ServletContext to the OrderRepository
        orderRepository = new OrderRepository(getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderCart shoppingCart = (OrderCart) session.getAttribute("shoppingCart");

        if (shoppingCart != null) {
            // Save the order data to the repository
            orderRepository.saveOrder(shoppingCart); // This will write to logOrder.txt

            // Clear the shopping cart after placing the order
            session.removeAttribute("shoppingCart");

            // Redirect to a confirmation page or order summary
            request.getRequestDispatcher("/WEB-INF/orderConfirmationAdmin.jsp").forward(request, response);
            
        } else {
            // Handle case where there is no shopping cart
           
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }
}