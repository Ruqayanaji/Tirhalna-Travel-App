import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class RoomTypeDAO {
	
	public RoomType getRoomTypeById(Connection dbConnection, int roomTypeId) throws SQLException {
	    String sql = "SELECT room_type_id, room_type_name, capacity FROM Room_Type WHERE room_type_id = ?";
	    
	    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
	        pstmt.setInt(1, roomTypeId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new RoomType(
	                    rs.getInt("room_type_id"),
	                    rs.getString("room_type_name"),
	                    rs.getInt("capacity")
	                );
	            }
	        }
	    }
	    return null;
	}
}
