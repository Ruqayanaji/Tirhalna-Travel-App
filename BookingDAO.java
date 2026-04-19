import java.time.LocalDate;
public class Booking {
		
		private int bookingID;
		private int userID;
		private int numOfPeopleHotel;
		private int numOfPeopleActivity;
		
		private Integer hotelID = null;
		private Integer activityID = null;
		private Integer roomTypeID = null;

		private LocalDate bookingDate = null;
		private LocalDate check_in_hotel = null;
		private LocalDate check_out_hotel = null;
		private LocalDate check_in_activity = null;
		private LocalDate check_out_activity = null;

		public Booking() {}

		public Booking(int bookingID,int userID,Integer hotelID, Integer activityID,Integer roomTypeID,
				int numOfPeopleHotel,int numOfPeopleActivity,LocalDate bookingDate,LocalDate check_in_hotel,LocalDate check_out_hotel,LocalDate check_in_activity,LocalDate check_out_activity) {
			
			this.bookingID = bookingID; // هنا يتم التعيين الأولي والوحيد
			this.userID = userID;
			this.hotelID = hotelID;
			this.activityID = activityID;
			this.roomTypeID = roomTypeID;
			this.numOfPeopleHotel = numOfPeopleHotel;
			this.numOfPeopleActivity = numOfPeopleActivity;
			this.bookingDate = bookingDate;
			this.check_in_hotel = check_in_hotel;
			this.check_out_hotel = check_out_hotel;
			this.check_in_activity = check_in_activity;
			this.check_out_activity = check_out_activity;
			
			validateBooking();
		}
		//Getters للوصول إلى البيانات

	    public int getBookingID() { //له قيتر بس ماله سيتر لانه ما يصير اغير رقم الحجز
	        return bookingID; 
	    }

	    public int getUserID() {
	        return userID;
	    }

	    public Integer getHotelID() {
	    	if(hotelID == null) {
				System.out.println("You have not booked hotel yet.");
			}
	        return hotelID;
	    }

	    public Integer getActivityID() {
			if(activityID == null) {
				System.out.println("You have not booked any activities yet.");
			}
	    	return activityID;
	    }

	    public Integer getRoomTypeID() {
	        return roomTypeID;
	    }

	    public int getNumOfPeopleHotel() {
	        return numOfPeopleHotel;
	    }
	    
	    public int getNumOfPeopleActivity() {
	        return numOfPeopleActivity;
	    }

	    public LocalDate getBookingDate() {
	        return bookingDate;
	    }

	    public LocalDate getCheck_in_hotel() {
	        return check_in_hotel;
	    }

	    public LocalDate getCheck_out_hotel() {
	        return check_out_hotel;
	    }

	    public LocalDate getCheck_in_activity() {
	        return check_in_activity;
	    }

	    public LocalDate getCheck_out_activity() {
	        return check_out_activity;
	    }
	    

	   //Setters لتعديل البيانات
	    
	    void setBookingID(int bookingID) {
	        this.bookingID = bookingID;
	    }
	    public void setUserID(int userID) {
	        this.userID = userID;
	    }

	    public void setHotelID(Integer hotelID) {
	        this.hotelID = hotelID;
	    }
	    
	    public void setActivityID(Integer activityID) {
	        this.activityID = activityID;
	    }

	    public void setRoomTypeID(Integer roomTypeID) {
	        this.roomTypeID = roomTypeID;
	    }

	    public void setNumOfPeopleHotel(int numOfPeopleHotel) {
	    	if(numOfPeopleHotel > 0)
	    		this.numOfPeopleHotel = numOfPeopleHotel;
	    	else 
	    		throw new IllegalArgumentException("Number of people for Hotel must be at least one.");	    
	    }
	    
	    public void setNumOfPeopleActivity(int numOfPeopleActivity) {
	    	if(numOfPeopleActivity > 0)
	    		this.numOfPeopleActivity = numOfPeopleActivity;
	    	else 
	    		throw new IllegalArgumentException("Number of people for Activity must be at least one.");	    
	    }

	    public void setBookingDate(LocalDate bookingDate) {
	        this.bookingDate = bookingDate;
	    }

	    public void setCheck_in_hotel(LocalDate check_in_hotel) {
	        this.check_in_hotel = check_in_hotel;
	    }

	    public void setCheck_out_hotel(LocalDate check_out_hotel) {
	        this.check_out_hotel = check_out_hotel;
	    }
	    
	    public void setCheck_in_activity(LocalDate check_in_activity) {
	        this.check_in_activity = check_in_activity;
	    }
	    
	    public void setCheck_out_activity(LocalDate check_out_activity) {
	        this.check_out_activity = check_out_activity;
	    }
	    public void validateBooking() {

	    	if (hotelID == null && activityID == null) { //على الاقل واحد منهم يكون بالحجز
			    throw new IllegalArgumentException("Booking must include either a Hotel or an Activity.");
			}
			if(hotelID != null && (check_in_hotel == null || check_out_hotel == null)) {//ما يصير حجز فندق بدون تشيك ان وتشيك اوت
			    throw new IllegalArgumentException("Booking must include a Hotel with Chick In and Check Out.");
			}
			if(hotelID != null && roomTypeID == null) {
			    throw new IllegalArgumentException("Booking with Hotel must include Room type.");
			}
			if(activityID != null && check_in_activity == null) {
			    throw new IllegalArgumentException("Booking with Activity must include Check In.");
			}				
				
			}
			
	    
	    public String toString() {

	    	StringBuilder bookingDetails = new StringBuilder();
	        bookingDetails.append("\n==============================================");
	        bookingDetails.append("\nBooking Confirmation (ID: ").append(bookingID).append(")");
	        bookingDetails.append("\n==============================================");
	        bookingDetails.append("\nUser ID: ").append(userID);
	        bookingDetails.append("\nBooking Date: ").append(bookingDate);
	        
	        if (hotelID != null) {
	            bookingDetails.append("\nHotel Details:");
	            bookingDetails.append("\nHotel ID: ").append(hotelID);
	            bookingDetails.append("\nRoom Type ID: ").append(roomTypeID);
	            bookingDetails.append("\nGuests (Hotel): ").append(numOfPeopleHotel);
	            bookingDetails.append("\nCheck-in Date: ").append(check_in_hotel);
	            bookingDetails.append("\nCheck-out Date: ").append(check_out_hotel);
	        }
	        
	        if (activityID != null) {
	            bookingDetails.append("\nActivity Details:");
	            bookingDetails.append("\nActivity ID: ").append(activityID);
	            bookingDetails.append("\nParticipants (Activity): ").append(numOfPeopleActivity);
	            bookingDetails.append("\nActivity Check-in Date: ").append(check_in_activity);
	            
	            if (check_out_activity != null) {
	                bookingDetails.append("\nActivity Check-out Date: ").append(check_out_activity);
	            }
	        }

	        bookingDetails.append("\n==============================================");
	        
	        return bookingDetails.toString();
	    }

}
