package map;

import inventory.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import utility.Utility;

public final class VideoMap extends HashMap<String, Video>{
    private static final long serialVersionUID = 1L;
    private static final String SELECT_VIDEO = ("SELECT call_number, available_copies, " 
                                              + "GetDemandFactor(call_number) AS demand_factor, " 
                                              + "title, studio, year, "
                                              + "number_copies, storage_medium, description "
                                              + "FROM video v;");
    
    public VideoMap(final Connection aConn) {
        try(final PreparedStatement pstmt = aConn.prepareStatement(SELECT_VIDEO);
            final ResultSet rs = pstmt.executeQuery() ) {
                while(rs.next()) {
                    final Video video = new Video();
                    video.setCallNum(rs.getString("call_number"));
                    video.setTitle(rs.getString("title"));
                    video.setPublisher(rs.getString("studio"));
                    video.setYear((rs.getInt("year")));
                    video.setNumCopies(rs.getInt("number_copies"));
                    video.setDescription(rs.getString("description"));
                    video.setAvailCopies(rs.getInt("available_copies"));
                    video.setDemandFactor(rs.getFloat("demand_factor"));
                    video.setType("Video");
                    video.setStorageMedium(rs.getString("storage_medium"));
                    put(video.getCallNumber(), video);
               }
        } catch(final SQLException e) {
            Utility.showErrorMessage(null, "Error Loading Video Data!");
            e.printStackTrace();
        }
   }
}