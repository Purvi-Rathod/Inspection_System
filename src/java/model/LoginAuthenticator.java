package model;

import db.DBConnection;
import dto.UserDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author purvi
 */
public class LoginAuthenticator 
{
    public boolean isLogin(UserDTO user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        
        Statement st = DBConnection.getStatement();
        String tablePassword="";
        try
        {
            String query = "SELECT password from users WHERE username='"+username+"'";
            System.out.println("Query = "+query);
            ResultSet rs = st.executeQuery(query);
            
            if(rs.next())
            {
                tablePassword = rs.getString(1);
            }
            else
            {
                return false;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        if(username !=null && password!=null && !username.trim().equals("") && password.equals(tablePassword))
        {
            return true;
        }
        return false;
    }
}
