package map;

import inventory.Audio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;

public final class AudioMap extends HashMap<String, Audio> {
    private static final long serialVersionUID = 1L;
    private static final String SELECT_AUDIO = ("SELECT call_number, available_copies, " 
                                              + "GetDemandFactor(call_number) AS demand_factor, "
                                              + "num_copies, title, label, year, num_copies, storage_medium, description " 
                                              + "FROM audio;");
    
   
    
    public AudioMap(final Connection aConn)  {
        try(final PreparedStatement pstmt = aConn.prepareStatement(SELECT_AUDIO);
            final ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()) {
                    final Audio audio = new Audio();
                    audio.setCallNum(rs.getString("call_number"));
                    audio.setTitle(rs.getString("title"));
                    audio.setPublisher(rs.getString("label"));
                    audio.setYear(rs.getInt("year"));
                    audio.setNumCopies(rs.getInt("num_copies"));
                    audio.setAvailCopies(rs.getInt("available_copies"));
                    audio.setDemandFactor(rs.getFloat("demand_factor"));
                    audio.setType("Audio");
                    audio.setStorageMedium(rs.getString("storage_medium"));
                    audio.setDescription(rs.getString("description"));
                    put(audio.getCallNumber(), audio);
                }     
        } catch(final SQLException e) {
            Utility.showErrorMessage(null, "Error loading Audio Data!");
            e.printStackTrace();
        }
    }
}