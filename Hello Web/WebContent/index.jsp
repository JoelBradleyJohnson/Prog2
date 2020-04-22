<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.Circuit"%>
<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" type="text/css" href="style.css">
	<meta charset="UTF-8">
	<title>Circuit Calculator</title>
</head>

<body>
	<%
		Circuit myCircuit = (Circuit) request.getAttribute("myCircuit");
	%>
	<div id="wrapper">
		<header>
			<h1>Amperage Calculator</h1>
		</header>
		<main>
			<form method=post>
				<label for=txtVoltage>Voltage:</label><br>
				<input name=txtVoltage type=number min=0.01 step=0.01 required><br>
				<label for=txtResistance>Resistance:</label><br>
				<input name=txtResistance type=number min=0.01 step=0.01 required><br><br>
				<input type=submit value="Calculate Amperage" name=cmdSubmit>
				<%
					out.println("<br><br>The amperage is " + myCircuit.getAmperage());
				%>
			</form>
		</main>
	</div>
</body>

</html>