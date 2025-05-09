package controller;

import db.DBConnection;
import dto.InspectionDTO;
import dto.InspectionItemDTO;
import model.InspectionService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;

@WebServlet("/SubmitInspectionController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class SubmitInspectionController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            // 1. Collect form fields
            String inspectionType = request.getParameter("inspectionType");
            String inspectionLevel = request.getParameter("inspectionLevel");
            String inspectorName = request.getParameter("inspectorName");
            String location = request.getParameter("location");
            String remarks = request.getParameter("remarks");
            Date inspectionDate = Date.valueOf(request.getParameter("inspectionDate"));
            int totalItems = Integer.parseInt(request.getParameter("totalItems"));

            // 2. DB connection and service
            Connection con = DBConnection.getConnection();
            InspectionService service = new InspectionService(con);
            int inspectorId = service.getUserIdByName(inspectorName);

            // 3. Save inspection
            InspectionDTO dto = new InspectionDTO();
            dto.setInspectorId(inspectorId);
            dto.setInspectionType(inspectionType);
            dto.setInspectionLevel(inspectionLevel);
            dto.setLocationName(location);
            dto.setInspectionDate(inspectionDate);
            dto.setRemarks(remarks);

            int inspectionId = service.saveInspection(dto);

            // 4. Prepare upload directory
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // 5. Process checklist items
            List<InspectionItemDTO> items = new ArrayList<>();
            for (int i = 0; i < totalItems; i++) {
                String name = request.getParameter("item" + i + "_name");
                String comment = request.getParameter("item" + i + "_comment");

                Part filePart = request.getPart("item" + i + "_image");
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

                String filePath = uploadPath + File.separator + uniqueFileName;
                filePart.write(filePath);
                String relativePath = "uploads/" + uniqueFileName;

                InspectionItemDTO item = new InspectionItemDTO();
                item.setInspectionId(inspectionId);
                item.setItemName(name);
                item.setItemStatus("Completed"); // Default value
                item.setComment(comment);
                item.setImagePath(relativePath);

                items.add(item);
            }

            // 6. Save items
            service.saveInspectionItems(items);

            // 7. Forward to success.jsp with data
            request.setAttribute("inspectorName", inspectorName);
            request.setAttribute("inspectionDate", inspectionDate.toString());
            request.setAttribute("inspectionType", inspectionType);
            request.setAttribute("inspectionLevel", inspectionLevel);
            request.setAttribute("location", location);
            request.setAttribute("remarks", remarks);

            RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error submitting inspection:</h3>");
            response.getWriter().println("<pre>" + e.getMessage() + "</pre>");
        }
    }
}
