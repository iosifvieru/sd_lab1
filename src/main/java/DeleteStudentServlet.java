import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DatabaseServlet.establishConnection();
            if(connection == null){
                return;
            }

            log(request.toString());

            int id = Integer.parseInt(request.getParameter("id"));

            log(" " + id);
            StudentBean studentBean = new StudentBean();
            studentBean.setId(id);

            deleteStudent(studentBean, connection);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect("./read-student");
        }
    }
    public void deleteStudent(StudentBean student, Connection connection){
        if(student == null){
            return;
        }
        if(connection == null){
            return;
        }

        try {
            String query = "DELETE FROM studenti WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            log(statement.toString());

            statement.setInt(1, student.getId());
            statement.executeUpdate();

        } catch (Exception e ){
            e.printStackTrace();
        }
    }

}
