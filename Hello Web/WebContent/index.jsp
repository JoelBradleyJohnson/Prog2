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
	<div id="wrapper">
		<header>
			<h1>This will calculate amperage given voltage and resistance
				are known</h1>
		</header>
		<main>
			<form>
				<label for=txtVoltage>Voltage:</label> <input name=txtVoltage type=number min=0.01 step=0.01
					required><br> <label for=txtResistance>Resistance:</label> <input name=txtResistance type=number
					min=0.01 step=0.01 required><br>
				<br> <input type=submit value="Calculate Amperage" name=cmdSubmit>

				<%
						//only show this if user has submitted the form rather than initially getting it.
						if (request.getParameter("cmdSubmit") != null) {
							Circuit myCircuit = new Circuit();

							myCircuit.setVoltage(Double.parseDouble(request.getParameter("txtVoltage")));
							myCircuit.setResistance(Double.parseDouble(request.getParameter("txtResistance")));

							out.println("<br><br>The amperage is " + myCircuit.getAmperage());
						}
					%>
			</form>
		</main>
	</div>
</body>

</html>