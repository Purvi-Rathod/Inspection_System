<%-- 
    Document   : inspectionForm
    Author     : purvi
--%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String inspectionType = request.getParameter("inspectionType");
    String inspectorRole = request.getParameter("inspectorRole");
    String inspectionLevel = request.getParameter("inspectionLevel");

    if (inspectionType != null) session.setAttribute("inspectionType", inspectionType);
    if (inspectorRole != null) session.setAttribute("inspectorRole", inspectorRole);
    if (inspectionLevel != null) session.setAttribute("inspectionLevel", inspectionLevel);

    String currentStep = "type";
    if (session.getAttribute("inspectionType") != null) currentStep = "role";
    if (session.getAttribute("inspectorRole") != null) currentStep = "level";
    if (session.getAttribute("inspectionLevel") != null) currentStep = "checklist";

    Map<String, String[]> roleChecklist = new HashMap<>();
    roleChecklist.put("Infrastructure Inspector", new String[]{"Building Condition", "Classrooms", "Laboratories", "Libraries", "Hostel Rooms"});
    roleChecklist.put("Hygiene & Cleanliness Inspector", new String[]{"Restrooms", "Dining Areas", "Water Supply", "Waste Management", "Overall Cleanliness"});
    roleChecklist.put("Safety Inspector", new String[]{"Fire Safety", "Emergency Exits", "Security Personnel", "Safety Protocols"});
    roleChecklist.put("Academic Inspector", new String[]{"Teaching Aids", "Faculty Availability", "Student Attendance", "Classroom Environment"});
    roleChecklist.put("Hostel Inspector", new String[]{"Hostel Rooms", "Common Areas", "Mess Services", "Security Arrangements"});
%>
<!DOCTYPE html>
<html>
<head>
    <title>Inspection Form</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/form.css">
</head>
<body>
<div class="form-container">
    <h2>Institution Inspection Form</h2>

    <% if ("type".equals(currentStep)) { %>
        <form method="post">
            <h3>Select Inspection Type</h3>
            <label><input type="radio" name="inspectionType" value="Internal" required> Internal</label><br>
            <label><input type="radio" name="inspectionType" value="External"> External</label><br>
            <button type="submit">Next</button>
        </form>

    <% } else if ("role".equals(currentStep)) { %>
        <form method="post">
            <h3>Select Inspector Role</h3>
            <label><input type="radio" name="inspectorRole" value="Infrastructure Inspector" required> Infrastructure Inspector</label><br>
            <label><input type="radio" name="inspectorRole" value="Hygiene & Cleanliness Inspector"> Hygiene & Cleanliness Inspector</label><br>
            <label><input type="radio" name="inspectorRole" value="Safety Inspector"> Safety Inspector</label><br>
            <label><input type="radio" name="inspectorRole" value="Academic Inspector"> Academic Inspector</label><br>
            <label><input type="radio" name="inspectorRole" value="Hostel Inspector"> Hostel Inspector</label><br>
            <button type="submit">Next</button>
        </form>

    <% } else if ("level".equals(currentStep)) { %>
        <form method="post">
            <h3>Select Level</h3>
            <label><input type="radio" name="inspectionLevel" value="Primary" required> Primary</label><br>
            <label><input type="radio" name="inspectionLevel" value="Secondary"> Secondary</label><br>
            <label><input type="radio" name="inspectionLevel" value="Higher Education"> Higher Education</label><br>
            <button type="submit">Next</button>
        </form>

    <% } else if ("checklist".equals(currentStep)) {
        String role = (String) session.getAttribute("inspectorRole");
        String[] items = roleChecklist.get(role);
    %>
        <form method="post" action="SubmitInspectionController" enctype="multipart/form-data">
            <h3>Inspection Details</h3>
            Inspector Name: <input type="text" name="inspectorName" required><br>
            Inspection Date: <input type="date" name="inspectionDate" required><br>
            Location: <input type="text" name="location" required><br>
            Remarks:<br>
            <textarea name="remarks" rows="3"></textarea><br><br>

            <h3>Checklist for <%= role %></h3>
            <% for (int i = 0; i < items.length; i++) { %>
                <label><%= items[i] %>:</label><br>
                <input type="hidden" name="item<%= i %>_name" value="<%= items[i] %>">
                <textarea name="item<%= i %>_comment" rows="3" required placeholder="Enter observation for <%= items[i] %>"></textarea><br>
                <input type="file" name="item<%= i %>_image" accept="image/*" required><br><br>
            <% } %>
            <input type="hidden" name="inspectionType" value="<%= session.getAttribute("inspectionType") %>">
            <input type="hidden" name="inspectionLevel" value="<%= session.getAttribute("inspectionLevel") %>">
            <input type="hidden" name="totalItems" value="<%= items.length %>">
            <button type="submit">Submit Inspection</button>
        </form>
    <% } %>
</div>
</body>
</html>
