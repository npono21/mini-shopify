package SYSC4806Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerMerchant")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (user_email, user_password) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password); // Use hashing for passwords!
                stmt.executeUpdate();
            }
            response.getWriter().write("User registered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().write("Error during registration.");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
