import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DestinationDAO {
	
    public List<Destination> getAllDestinations(Connection dbConnection) throws SQLException {
        List<Destination> destinations = new ArrayList<>();
        String sql = "SELECT Destination_ID, Destination_Name, Destination_Type FROM Destination";
        
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Destination destination = new Destination(
                    rs.getInt("id"),
                    rs.getString("destination_name"),
                    rs.getString("destination_type")
                );
                destinations.add(destination);
            }
        }
        return destinations;
    }
}
