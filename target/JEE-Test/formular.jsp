<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular student</h3>
		Introduceti datele despre student:
		<form action="./db" method="post">
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
			<button type="submit" name="submit">Trimite</button>

		</form>
	</body>
</html>