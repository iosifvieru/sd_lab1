import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));

        String facultate = request.getParameter("facultate");
        String specializare = request.getParameter("specializare");
        int an_universitar = Integer.parseInt(request.getParameter("an_universitar"));

        /*
        procesarea datelor - calcularea anului nasterii
         */
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;

        // initializare serializator Jackson
        XmlMapper mapper = new XmlMapper();

        // creare bean si populare cu date
        StudentBean bean = new StudentBean();
        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);

        bean.setFacultate(facultate);
        bean.setSpecializare(specializare);
        bean.setAn_universitar(an_universitar);

        // serializare bean sub forma de string XML
        //mapper.writeValue(new File("/home/student/1307A/Popescu Ion/student.xml"), bean);
        mapper.writeValue(new File("/home/tero/1310A/student.xml"), bean);

        // se trimit datele primite si anul nasterii catre o alta pagina JSP pentru afisare

        request.setAttribute("action", "create");

        request.setAttribute("nume", nume);
        request.setAttribute("prenume", prenume);
        request.setAttribute("varsta", varsta);
        request.setAttribute("anNastere", anNastere);

        request.setAttribute("facultate", facultate);
        request.setAttribute("specializare", specializare);
        request.setAttribute("an_universitar", an_universitar);
        //request.getRequestDispatcher("./info-student.jsp").forward(request, response);
        request.getRequestDispatcher("./db").forward(request, response);
    }
}