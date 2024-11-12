package tugas1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tugas1.infrastructure.DBUtils;

@WebServlet("/login.do")
public class LoginServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate user credentials
        String[] loginResult = validateUser (username, password);
        String loginMessage = loginResult[0]; // Message from validation
        String admin = loginResult[1]; // Admin status from validation

        // Set the response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if ("Login Successful".equals(loginMessage)) {
            // Redirect to a welcome page or dashboard based on admin status
            if ("Y".equals(admin)) {
            	response.sendRedirect(request.getContextPath() + "/admin/product.do");
            } else {
                response.sendRedirect("product.do");
            }
        } else {
            // If login fails, set an error message and forward back to the login page
            request.setAttribute("errorMessage", loginMessage);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    private String[] validateUser (String username, String password) {
        String message = "Invalid username or password.";
        String admin = "N"; // Default to non-admin
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get database connection
            connection = DBUtils.getConn();
            String query = "SELECT password, admin FROM users_tbl WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (password != null && !password.isEmpty()) {
                // Only query the database if the password is provided
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    admin = resultSet.getString("admin"); // Get admin status

                    // Check if the provided password matches the stored password
                    if (password.equals(storedPassword)) {
                        message = "Login Successful"; // Valid credentials
                    } else {
                        message = "Invalid password. Please try again."; // Wrong password
                    }
                } else {
                    message = "User  not registered. Please sign up."; // User not found
                }
            } else {
                message = null; // No message if the password field is empty
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "An error occurred. Please try again later."; // General error message
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new String[] { message, admin }; // Return both message and admin status
    }
}