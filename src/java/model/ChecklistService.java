package model;

import java.sql.*;
import java.util.*;

/**
 *
 * @author purvi
 */
public class ChecklistService 
{
    private Connection con;

    public ChecklistService(Connection con) {
        this.con = con;
    }

    public List<String> getChecklistByRole(String roleName) throws SQLException {
        List<String> items = new ArrayList<>();
        String sql = "SELECT item_name FROM inspection_templates WHERE role_name = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, roleName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            items.add(rs.getString("item_name"));
        }
        return items;
    }
}
