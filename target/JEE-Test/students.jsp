<%@ page import="java.util.List" %>
<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Informatii student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Informatii student</h3>

		<!-- populare bean cu informatii din cererea HTTP -->
		<% List<beans.StudentBean> studentBeans = (List<beans.StudentBean>) request.getAttribute("studentBeans"); %>


        <table border='1'>
        <tr>
            <th>ID</th>
            <th>Nume</th>
            <th>Prenume</th>
            <th>Varsta</th>
            <th>Facultate</th>
            <th>Specializare</th>
            <th>An Universitar</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        <% for (beans.StudentBean studentBean : studentBeans) { %>
        <tr>
            <td><% out.print(studentBean.getId()); %></td>
            <td><% out.print(studentBean.getNume()); %></td>
            <td><% out.print(studentBean.getPrenume()); %></td>
            <td><% out.print(studentBean.getVarsta()); %></td>
            <td><% out.print(studentBean.getFacultate()); %></td>
            <td><% out.print(studentBean.getSpecializare()); %></td>
            <td><% out.print(studentBean.getAn_universitar()); %></td>

            <td>
                <form action="./delete" method="post">
                    <input type="hidden" name ="id" value='<%= studentBean.getId() %>'>
                    <input type="submit" name="action" value="delete">
                </form>
            </td>
            <td>
                <form action="updateStudent.jsp" method="post">
                    <input type="hidden" name ="id" value='<%= studentBean.getId() %>'>
                    <input type="submit" name="action" value="update">
                </form>
            </td>

        </tr>
        <% } %>
        </table>
	</body>
</html>