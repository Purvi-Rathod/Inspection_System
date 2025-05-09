package controller;

import db.DBConnection;
import model.ChecklistService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author purvi
 */
public class LoadChecklistController extends HttpServlet 
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("inspectorRole");

        try {
            Connection con = db.DBConnection.getConnection();
            ChecklistService service = new ChecklistService(con);
            List<String> items = service.getChecklistByRole(role);
            request.setAttribute("checklistItems", items);
            request.setAttribute("selectedRole", role);

            RequestDispatcher rd = request.getRequestDispatcher("inspectionForm.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error loading checklist: " + e.getMessage());
        }
    }
}
