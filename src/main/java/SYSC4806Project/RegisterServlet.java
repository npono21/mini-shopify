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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String confirmTsAndCs = request.getParameter("confirm_ts_and_cs");

        try {
            // Validate input
            if (email == null || name == null || password == null || confirmPassword == null || confirmTsAndCs == null ||
                email.isEmpty() || name.isEmpty() || password.isEmpty() || !password.equals(confirmPassword) ||
                !"true".equals(confirmTsAndCs)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid input. Please check your details and try again.");
                return;
            }

            // Database insertion
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO users (user_email, user_name, user_password) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, email);
                    stmt.setString(2, name);
                    stmt.setString(3, password); // Replace with hashed password in production
                    stmt.executeUpdate();
                }
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("User registered successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("An error occurred during registration.");
            } catch (IOException ignored) {
            }
        }
    }
}
