<%-- 
    Document   : dashboard
    Author     : purvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard | Inspection System</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/dashboard.css">
    </head>
    <body>
        <%
            String username = (String)session.getAttribute("username");
            if(username == null)
            {
                response.sendRedirect("login.html");
            }
        %>
        <div class="container">
            <!-- Sidebar -->
            <div class="sidebar">
                <h2>Dashboard</h2>
                <ul>
                    <li><a href="dashboard.jsp" class="<%= (request.getRequestURI().endsWith("dashboard.jsp") ? "active" : "") %>">Home</a></li>
                    <li><a href="inspectionForm.jsp">New Inspection</a></li>
                    <li><a href="viewReports.jsp">Reports</a></li>
                    <li><a href="logout.jsp">Logout</a></li>
                </ul>
            </div>
            <!-- Main Content -->
            <div class="main-content">
                <div class="topbar">
                    <h1>Welcome, <%=username%>...!</h1>
                    <p>Select an option from the menu to get started.</p>
                </div>

                <div class="cards">
                    <div class="card card-blue">
                        <h3>Total Inspections</h3>
                        <p>120</p>
                    </div>
                    <div class="card card-orange">
                        <h3>Pending Approvals</h3>
                        <p>8</p>
                    </div>
                    <div class="card card-green">
                        <h3>Reports Generated</h3>
                        <p>45</p>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
