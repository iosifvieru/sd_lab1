import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class DatabaseServlet extends HttpServlet {
    static String db_url = "jdbc:sqlite:/home/tero/Desktop/SD/SD_Laborator_01/JEE-Test/studenti.db";
    String user = "";
    String password ="";
    static Connection connection = null;
    public static Connection establishConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(db_url);

            return connection;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            connection = establishConnection();
            if(connection == null){
                return;
            }

            //log("DATE PRIMITE: ACTION: " + action + " NUME: " + nume + " PRENUME: " + prenume + " VARSTA: " + varsta);

            Statement statement = connection.createStatement();

            String createTable = "CREATE TABLE IF NOT EXISTS \"studenti\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL UNIQUE,\n" +
                    "\t\"nume\"\tTEXT NOT NULL,\n" +
                    "\t\"prenume\"\tTEXT,\n" +
                    "\t\"varsta\"\tINTEGER,\n" +
                    "\t\"facultate\"\tTEXT,\n" +
                    "\t\"specializare\"\tTEXT,\n" +
                    "\t\"an_universitar\"\tINTEGER,\n" +
                    "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                    ");";

            statement.execute(createTable);

            StudentBean bean = new StudentBean();
            bean.setNume(request.getParameter("nume"));
            bean.setPrenume(request.getParameter("prenume"));
            bean.setVarsta(Integer.parseInt(request.getParameter("varsta")));
            bean.setFacultate(request.getParameter("facultate"));
            bean.setSpecializare(request.getParameter("specializare"));
            bean.setAn_universitar(Integer.parseInt(request.getParameter("an_universitar")));

            createStudent(bean, connection);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            response.sendRedirect("./read-student");

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void createStudent(StudentBean student, Connection connection){
        if(student == null){
            return;
        }

        if(connection == null){
            return;
        }

        try {
            String query = "INSERT INTO studenti(nume, prenume, varsta, facultate, specializare, an_universitar) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getNume());
            statement.setString(2, student.getPrenume());
            statement.setInt(3, student.getVarsta());
            statement.setString(4, student.getFacultate());
            statement.setString(5, student.getSpecializare());
            statement.setInt(6, student.getAn_universitar());

            statement.executeUpdate();
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
