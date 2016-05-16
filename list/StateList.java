package list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import utility.Utility;

import locations.State;


public final class StateList extends ArrayList<State> {
    private static final long serialVersionUID = 1L;
    private static String STATE_QUERY = "SELECT * FROM states;";
   
    public StateList(final Connection aConn) {
       try(final PreparedStatement pstmt = aConn.prepareStatement(STATE_QUERY);
           final ResultSet rs = pstmt.executeQuery()) {
               while (rs.next()) {
                   final State s = new State();
                   s.setName(rs.getString("state_name"));
                   s.setID(rs.getInt("state_id"));
                   s.setParentID(rs.getInt("country_id"));
                   add(s);
               }
       } catch (final SQLException e) {
           Utility.showErrorMessage(null, "Error Loading State Data!");
           e.printStackTrace();
       }
   }
}