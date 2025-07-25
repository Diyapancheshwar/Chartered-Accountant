import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/ContactServlet") 
public class ContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        String url = "jdbc:mysql://localhost:3306/ca"; 
        String user = "root"; 
        String pass = "root"; 

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
         
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql:///abhidb?useSSL=false","root","root");

            String sql = "INSERT INTO contact (name, email, subject, message) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, subject);
            ps.setString(4, message);
            ps.executeUpdate();

            out.println("<script type='text/javascript'>");
            out.println("alert('Message sent successfully!');");
            out.println("window.location = 'contact.html';");
            out.println("</script>");
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Error: Unable to send message.</h3>");
        }
    }
}
