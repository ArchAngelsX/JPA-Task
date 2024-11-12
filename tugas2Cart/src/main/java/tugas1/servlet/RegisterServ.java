package tugas1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tugas1.infrastructure.DBUtils;

@WebServlet("/register") // Ensure this matches the action in your form
public class RegisterServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       

        // Store user in the database
        String registrationMessage = registerUser (username, password);

        // Set the response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + registrationMessage + "</h1>");
    }

    private String registerUser (String username, String password) {
        String message = "Registration failed. Please try again.";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get database connection
            connection = DBUtils.getConn(); // Implement this method to get your DB connection
            String query = "INSERT INTO users_tbl (username, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                message = "Registration successful. You can now login.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
				DBUtils.closeConn(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return message;
    }
}