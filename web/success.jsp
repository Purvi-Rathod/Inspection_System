<%-- 
    Document   : success
    Author     : purvi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inspection Submitted</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            max-width: 600px;
        }
        th, td {
            text-align: left;
            padding: 8px;
            border-bottom: 1px solid #ccc;
        }
        .button {
            padding: 10px 20px;
            background-color: #004080;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>✅ Inspection Submitted Successfully!</h2>
        <p>Your inspection has been recorded with the following details:</p>

        <table>
            <tr><th>Inspector Name:</th><td><%= request.getAttribute("inspectorName") %></td></tr>
            <tr><th>Inspection Date:</th><td><%= request.getAttribute("inspectionDate") %></td></tr>
            <tr><th>Type:</th><td><%= request.getAttribute("inspectionType") %></td></tr>
            <tr><th>Level:</th><td><%= request.getAttribute("inspectionLevel") %></td></tr>
            <tr><th>Location:</th><td><%= request.getAttribute("location") %></td></tr>
            <tr><th>Remarks:</th><td><%= request.getAttribute("remarks") %></td></tr>
        </table>

        <br>
        <a href="inspectionForm.jsp" class="button">Submit Another Inspection</a>
        <a href="logout.jsp" class="button" style="background-color: #a00;">Logout</a>
    </div>
</body>
</html>
