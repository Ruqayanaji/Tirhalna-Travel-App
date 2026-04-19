import java.io.Serializable;
import java.util.Objects;
public class Hotel implements Serializable{
	 private static final long serialVersionUID = 1L;
	    
	    private int hotelId;
	    private String hotelName;
	    private String contactNumber;
	    private String hotelLocation;
	    private int hotelStars;
	    //زياده
	    private int destinationId;

	    public Hotel() {
	    }

	    public Hotel(int hotelId, String hotelName, String contactNumber,
	                 String hotelLocation, int hotelStars,int destinationId) {
	        this.hotelId = hotelId;
	        this.hotelName = hotelName;
	        this.contactNumber = contactNumber;
	        this.hotelLocation = hotelLocation;
	        this.hotelStars = hotelStars;
	        this.destinationId = destinationId;
	    }

	    public Hotel(String hotelName, String contactNumber,
	                 String hotelLocation, int hotelStars,int destinationId) {
	        this.hotelName = hotelName;
	        this.contactNumber = contactNumber;
	        this.hotelLocation = hotelLocation;
	        this.hotelStars = hotelStars;
	        this.destinationId = destinationId;
	    }

	    public int getHotelId() {
	        return hotelId;
	    }

	    public String getHotelName() {
	        return hotelName;
	    }

	    public String getContactNumber() {
	        return contactNumber;
	    }

	    public String getHotelLocation() {
	        return hotelLocation;
	    }

	    public int getHotelStars() {
	        return hotelStars;
	    }
	    
	    public int getDestinationId() {
	        return destinationId;
	    }

	    public void setHotelId(int hotelId) {
	        this.hotelId = hotelId;
	    }

	    public void setHotelName(String hotelName) {
	        this.hotelName = hotelName;
	    }

	    public void setContactNumber(String contactNumber) {
	        this.contactNumber = contactNumber;
	    }

	    public void setHotelLocation(String hotelLocation) {
	        this.hotelLocation = hotelLocation;
	    }

	    public void setHotelStars(int hotelStars) {
	        this.hotelStars = hotelStars;
	    }
	    
	    public void setDestinationId(int destinationId) {
	        this.destinationId = destinationId;
	    }

	    // Validation (مثل Activity)
	    public boolean isValid() {
	        return hotelName != null && !hotelName.trim().isEmpty() &&
	               contactNumber != null && !contactNumber.trim().isEmpty() &&
	               hotelLocation != null && !hotelLocation.trim().isEmpty() &&
	               hotelStars > 0 && hotelStars <= 5;
	    }

	    @Override
	    public String toString() {
	        return "Hotel{" +
	                "hotelId=" + hotelId +
	                ", hotelName='" + hotelName + '\'' +
	                ", contactNumber='" + contactNumber + '\'' +
	                ", hotelLocation='" + hotelLocation + '\'' +
	                ", hotelStars=" + hotelStars +
	                ", destinationId=" + destinationId +
	                '}';
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Hotel hotel = (Hotel) o;
	        return hotelId == hotel.hotelId &&
	               Objects.equals(hotelName, hotel.hotelName);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(hotelId, hotelName);
	    }
}
