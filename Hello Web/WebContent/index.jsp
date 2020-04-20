<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Circuit Calculator</title>
</head>

<body>
    <h1>This will calculate amperage given voltage and resistance are
        known</h1>
    <form>
        <label for=txtVoltage>Voltage:</label>
        <input name=txtVoltage type=number min=0.01 step=0.01 required><br>

        <label for=txtResistance>Resistance:</label>
        <input name=txtResistance type=number min=0.01 step=0.01 required><br><br>

        <input type=submit value="Calculate Amperage" name=cmdSubmit>

        <%
        	//only show this if user has submitted the form rather than initially getting it.
        	if (request.getParameter("cmdSubmit")!= null) {
            	double voltage = Double.parseDouble(request.getParameter("txtVoltage"));
            	double resistance = Double.parseDouble(request.getParameter("txtResistance"));
            
            	double amperage = voltage / resistance;
            
            	out.println("The amperage is " + amperage);
        	}
        %>
    </form>
</body>

</html>