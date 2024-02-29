<html>
	<head>
		<title>Update student</title>
		<meta charset="UTF-8" />
	</head>
<body>
    <h2>Update student</h2>
    <form action="./update" method="POST">
        <input type="hidden" name="id" value='<%= request.getParameter("id") %>'>
		Nume: <input type="text" name="nume" />
		<br />
		Prenume: <input type="text" name="prenume" />
		<br />
		Varsta: <input type="number" name="varsta" />
		<br />
		Facultate: <input type="text" name="facultate" />
		<br />
		<label for="specializare">Specializare: </label>
		<select name="specializare">
		    <option value="licenta">Licenta</option>
		    <option value="masterat">Masterat</option>
		    <option value="doctorat">Doctorat</option>
		</select>
		<br />
		An universitar: <input type="number" name="an_universitar"/>
		<button type="submit" name="action" value="create">Trimite</button>
	</form>

</body>
</html>