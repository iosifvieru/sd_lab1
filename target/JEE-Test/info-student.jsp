<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Informatii student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Informatii student</h3>

		<!-- populare bean cu informatii din cererea HTTP -->
		<jsp:useBean id="studentBean" class="beans.StudentBean" scope="request" />
		<jsp:setProperty name="studentBean" property="nume" value='<%= request.getAttribute("nume") %>'/>
		<jsp:setProperty name="studentBean" property="prenume" value='<%= request.getAttribute("prenume") %>'/>
		<jsp:setProperty name="studentBean" property="varsta" value='<%= request.getAttribute("varsta") %>'/>

        <jsp:setProperty name="studentBean" property="facultate" value='<%= request.getAttribute("facultate") %>'/>
        <jsp:setProperty name="studentBean" property="specializare" value='<%= request.getAttribute("specializare") %>'/>
        <jsp:setProperty name="studentBean" property="an_universitar" value='<%= request.getAttribute("an_universitar") %>'/>


		<!-- folosirea bean-ului pentru afisarea informatiilor -->
		<p>Urmatoarele informatii au fost introduse:</p>
		<ul type="bullet">
			<li>Nume: <jsp:getProperty name="studentBean" property="nume" /></li>
			<li>Prenume: <jsp:getProperty name="studentBean" property="prenume" /></li>
			<li>Varsta: <jsp:getProperty name="studentBean" property="varsta" /></li>

			<!-- anul nasterii nu face parte din bean, il afisam separat (daca exista) -->
			<li>Anul nasterii: <%
			    Object anNastere = request.getAttribute("anNastere");
			    if (anNastere != null) {
			        out.print(anNastere);
			    } else {
			        out.print("necunoscut");
			    }
			%></li>

			<li>Facultate: <jsp:getProperty name="studentBean" property="facultate" /></li>
			<li>Specializare: <jsp:getProperty name="studentBean" property="specializare" /></li>
			<li>An Universitar: <jsp:getProperty name="studentBean" property="an_universitar" /></li>
		</ul>
	</body>
</html>