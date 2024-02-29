import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DatabaseServlet.establishConnection();

            if(connection == null){
                return;
            }

            int id = Integer.parseInt(request.getParameter("id"));

            String updateQuery = "UPDATE studenti SET nume= ?, prenume = ?, varsta = ?, facultate = ?, specializare = ?, an_universitar = ?" +
                    "WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, request.getParameter("nume"));
            statement.setString(2, request.getParameter("prenume"));
            statement.setInt(3, Integer.parseInt(request.getParameter("varsta")));
            statement.setString(4, request.getParameter("facultate"));
            statement.setString(5, request.getParameter("specializare"));
            statement.setString(6, request.getParameter("an_universitar"));
            statement.setInt(7, id);

            statement.executeUpdate();

            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            response.sendRedirect("./read-student");
        }
    }
}
