import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class HotelDAO {
	public List<Hotel> getAllHotels(Connection dbConnection) throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT Hotel_ID, Hotel_Name, Contact_Number, Hotel_Location, Hotel_Stars, Destination_ID FROM Hotel";
        
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Hotel hotel = new Hotel(
                    rs.getInt("Hotel_ID"),
                    rs.getString("Hotel_Name"),
                    rs.getString("Contact_Number"),
                    rs.getString("Hotel_Location"),
                    rs.getInt("Hotel_Stars"),
                    rs.getInt("Destination_ID")
                );
                hotels.add(hotel);
            }
        }
        return hotels;
    }

   
    public Hotel getHotelById(Connection dbConnection, int hotelId) throws SQLException {
        String sql = "SELECT Hotel_ID, Hotel_Name, Contact_Number, Hotel_Location, Hotel_Stars, Destination_ID FROM Hotel WHERE Hotel_ID = ?";
        
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setInt(1, hotelId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Hotel(
                        rs.getInt("Hotel_ID"),
                        rs.getString("Hotel_Name"),
                        rs.getString("Contact_Number"),
                        rs.getString("Hotel_Location"),
                        rs.getInt("Hotel_Stars"),
                        rs.getInt("Destination_ID")
                    );
                }
            }
        }
        return null;
    }

    public List<HotelRoom> getAvailableRoomsByHotelId(Connection dbConnection, int hotelId) throws SQLException {
        List<HotelRoom> availableRooms = new ArrayList<>();
        String sql = "SELECT Hotel_ID, Room_Type_ID, Room_Price, Room_Available FROM Hotel_Room WHERE Hotel_ID = ? AND Room_Available > 0";

        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setInt(1, hotelId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    availableRooms.add(new HotelRoom(
                        rs.getInt("Hotel_ID"),
                        rs.getInt("Room_Type_ID"),
                        rs.getDouble("Room_Price"),
                        rs.getInt("Room_Available")
                    ));
                }
            }
        }
        return availableRooms;
}}
