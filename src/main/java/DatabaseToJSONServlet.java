import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseToJSONServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DatabaseServlet.establishConnection();
            if (connection == null) {
                return;
            }

            String query = "SELECT * FROM studenti";

            StringBuilder responseText = new StringBuilder();
            responseText.append("[\n");

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while(results.next()) {
                responseText.append("\t{ \"id\": \"").append(results.getInt("id")).append("\"")
                        .append(", \"nume\": \"").append(results.getString("nume")).append("\"")
                        .append(", \"prenume\": \"").append(results.getString("prenume")).append("\"")
                        .append(", \"varsta\": \"").append(results.getInt("varsta")).append("\"")
                        .append(", \"facultate\": \"").append(results.getString("facultate")).append("\"")
                        .append(", \"specializare\": \"").append(results.getString("specializare")).append("\"")
                        .append(", \"an_universitar\": \"").append(results.getString("an_universitar")).append("\"")
                        .append("}, \n");
            }

            responseText.deleteCharAt(responseText.length() - 3);
            responseText.append("]");

            response.setContentType("application/json");
            response.getWriter().write(responseText.toString());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
