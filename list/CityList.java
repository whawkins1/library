package list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import utility.Utility;

import locations.City;

public final class CityList extends ArrayList<City> {
   private static final long serialVersionUID = 1L;
   private static String STATE_QUERY = "SELECT * FROM cities;";
    
   public CityList(final Connection aConn) {
       try(final PreparedStatement pstmt = aConn.prepareStatement(STATE_QUERY);
           final ResultSet rs = pstmt.executeQuery()) {
               while (rs.next()) {
                   final City c = new City();
                   c.setName(rs.getString("city_name"));
                   c.setID(rs.getInt("city_id"));
                   c.setParent(rs.getInt("state_id"));
                   add(c);
               }
       } catch (final SQLException e) {
           Utility.showErrorMessage(null, "Error Loading Cities!");
           e.printStackTrace();
       }
   }
}