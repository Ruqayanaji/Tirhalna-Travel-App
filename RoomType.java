
public class HotelRoom {
	    
	    private int hotelId;      
	    private int roomTypeId;   
	    private double roomPrice; 
	    private int roomAvailable; 
	    
	    public HotelRoom(int hotelId, int roomTypeId, double roomPrice, int roomAvailable) {
	        this.hotelId = hotelId;
	        this.roomTypeId = roomTypeId;
	        this.roomPrice = roomPrice;
	        this.roomAvailable = roomAvailable;
	    }

	    public int getHotelId() {
	        return hotelId;
	    }

	    public int getRoomTypeId() {
	        return roomTypeId;
	    }

	    public double getRoomPrice() {
	        return roomPrice;
	    }

	    public int getRoomAvailable() {
	        return roomAvailable;
	    }

	    public void setHotelId(int hotelId) {
	        this.hotelId = hotelId;
	    }

	    public void setRoomTypeId(int roomTypeId) {
	        this.roomTypeId = roomTypeId;
	    }

	    public void setRoomPrice(double roomPrice) {
	        this.roomPrice = roomPrice;
	    }

	    public void setRoomAvailable(int roomAvailable) {
	        this.roomAvailable = roomAvailable;
	    }

	    public String toString() {
	        return "HotelRoom{" +
	                "HotelID=" + hotelId +
	                ", RoomTypeID=" + roomTypeId +
	                ", Price=" + roomPrice +
	                ", Available=" + roomAvailable +
	                '}';
	    }
	}

