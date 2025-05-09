package model;

import dto.InspectionDTO;
import dto.InspectionDTO;
import dto.InspectionItemDTO;
import dto.InspectionItemDTO;

import java.sql.*;
import java.util.List;
/**
 *
 * @author purvi
 */
public class InspectionService 
{
    private Connection con;

    public InspectionService(Connection con) {
        this.con = con;
    }

    public int getUserIdByName(String fullName) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE full_name = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, fullName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("user_id");
        throw new SQLException("Inspector not found");
    }

    public int saveInspection(InspectionDTO dto) throws SQLException {
        String sql = "INSERT INTO inspections (inspector_id, location_name, inspection_date, remarks, inspection_type, inspection_level) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, dto.getInspectorId());
        ps.setString(2, dto.getLocationName());
        ps.setDate(3, dto.getInspectionDate());
        ps.setString(4, dto.getRemarks());
        ps.setString(5, dto.getInspectionType());
        ps.setString(6, dto.getInspectionLevel());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);
        return -1;
    }

    public void saveInspectionItems(List<InspectionItemDTO> items) throws SQLException {
        String sql = "INSERT INTO inspection_items (inspection_id, item_name, item_status, comment) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        for (InspectionItemDTO item : items) {
            ps.setInt(1, item.getInspectionId());
            ps.setString(2, item.getItemName());
            ps.setString(3, item.getItemStatus());
            ps.setString(4, item.getComment());
            ps.addBatch();
        }
        ps.executeBatch();
    }
}
