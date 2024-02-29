import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReadStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din fisierul XML de pe disc
        // inlocuita cu db.
        //File file = new File("/home/tero/1310A/student.xml");

        Connection connection = DatabaseServlet.establishConnection();
        Statement statement = null;
        ResultSet results = null;
        List<StudentBean> studentBeans = new ArrayList<>();

        if(connection == null){
            log("CONNECTION IS NULL.");
            response.sendError(404, "CONNECTION IS NULL.");
            return;
        }

        // conn is not null
        String readSQL = "SELECT * FROM studenti";

        /*
        StringBuilder responseText = new StringBuilder();
        responseText.append("<h2>Studenti</h2>");
        responseText.append("<table border='1'>" +
                "<thead><tr> <th>ID</th> <th>Nume</th> <th>Prenume</th> <th>Varsta</th> <th>Facultate</th> <th>Specializare</th> <th>An universitar</th></tr></thead>" +
                "<tbody>");

        try {
            statement = connection.createStatement();
            results = statement.executeQuery(readSQL);

            while(results.next()){
                int id = results.getInt("id");
                String nume = results.getString("nume");
                String prenume = results.getString("prenume");
                int varsta = results.getInt("varsta");
                String facultate = results.getString("facultate");
                String specializare = results.getString("specializare");
                int anUniversitar = results.getInt("an_universitar");

                responseText.append("<tr><td>" + id + "</td><td> " + nume + " </td><td> " + prenume +
                        " </td><td>" + varsta + "</td><td> " + facultate + "</td><td> " + specializare + "</td><td> " + anUniversitar + "</td></tr>" );

            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        responseText.append("</tbody></table><br/ >");
         */

        try {
            statement = connection.createStatement();
            results = statement.executeQuery(readSQL);

            while(results.next()) {

                int id = results.getInt("id");
                String nume = results.getString("nume");
                String prenume = results.getString("prenume");
                int varsta = results.getInt("varsta");
                String facultate = results.getString("facultate");
                String specializare = results.getString("specializare");
                int anUniversitar = results.getInt("an_universitar");

                StudentBean temp = new StudentBean();
                temp.setId(id);
                temp.setNume(nume);
                temp.setPrenume(prenume);
                temp.setVarsta(varsta);
                temp.setFacultate(facultate);
                temp.setSpecializare(specializare);
                temp.setAn_universitar(anUniversitar);

                studentBeans.add(temp);
            }
            results.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("studentBeans", studentBeans);
        request.getRequestDispatcher("./students.jsp").forward(request, response);
    }
}
