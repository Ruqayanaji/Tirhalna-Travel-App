import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class BookingDAO {
	public int createBooking(Connection dbConnection, Booking booking) throws SQLException {
	    String sql = "INSERT INTO Booking ("
	               + "user_id, hotel_id, activity_id, room_type_id, "
	               + "number_of_people_for_hotel, number_of_people_for_activity, "
	               + "booking_date, check_in_hotel, check_out_hotel, check_in_activity, check_out_activity"
	               + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    int generatedId = 0;
	    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        
	        pstmt.setInt(1, booking.getUserID()); 
	        pstmt.setObject(2, booking.getHotelID());
	        pstmt.setObject(3, booking.getActivityID());
	        pstmt.setObject(4, booking.getRoomTypeID());
	        pstmt.setInt(5, booking.getNumOfPeopleHotel());
	        pstmt.setInt(6, booking.getNumOfPeopleActivity());
	        pstmt.setDate(7, java.sql.Date.valueOf(booking.getBookingDate()));
	        pstmt.setObject(8, booking.getCheck_in_hotel() != null ? java.sql.Date.valueOf(booking.getCheck_in_hotel()) : null);
	        pstmt.setObject(9, booking.getCheck_out_hotel() != null ? java.sql.Date.valueOf(booking.getCheck_out_hotel()) : null);
	        pstmt.setObject(10, booking.getCheck_in_activity() != null ? java.sql.Date.valueOf(booking.getCheck_in_activity()) : null);
	        pstmt.setObject(11, booking.getCheck_out_activity() != null ? java.sql.Date.valueOf(booking.getCheck_out_activity()) : null);
	        pstmt.executeUpdate();
	        
	        try (ResultSet rs = pstmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                generatedId = rs.getInt(1);
	            }
	        }
	        if (generatedId > 0) {
	             dbConnection.commit();  
	        }
	        
	        return generatedId;
	    }
	}
}
