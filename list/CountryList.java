package list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import utility.Utility;
import locations.Country;


public final class CountryList extends ArrayList<Country> {
   private static final long serialVersionUID = 1L;
   private static String COUNTRY_QUERY = "SELECT * FROM countries;";
    
   public CountryList(final Connection aConn) {
       try(final PreparedStatement pstmt = aConn.prepareStatement(COUNTRY_QUERY);
           final ResultSet rs = pstmt.executeQuery()) {
               while (rs.next()) {
                   final Country c = new Country();
                   c.setName(rs.getString("country_name"));
                   c.setID(rs.getInt("country_id"));
                   add(c);
               }
       } catch (final SQLException e) {
           Utility.showErrorMessage(null, "Error Loading Country Data!");
           e.printStackTrace();
       }
   }
}