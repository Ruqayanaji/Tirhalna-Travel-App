
public class RoomType {
	    
	    private int roomTypeId; 
	    private String roomTypeName; 
	    private int capacity; 

	    public RoomType(int roomTypeId, String roomTypeName, int capacity) {
	        this.roomTypeId = roomTypeId;
	        this.roomTypeName = roomTypeName;
	        this.capacity = capacity;
	    }

	    public int getRoomTypeId() {
	        return roomTypeId;
	    }

	    public String getRoomTypeName() {
	        return roomTypeName;
	    }

	    public int getCapacity() {
	        return capacity;
	    }

	    public void setRoomTypeId(int roomTypeId) {
	        this.roomTypeId = roomTypeId;
	    }

	    public void setRoomTypeName(String roomTypeName) {
	        this.roomTypeName = roomTypeName;
	    }

	    public void setCapacity(int capacity) {
	        this.capacity = capacity;
	    }

	    public String toString() {
	        return "RoomType{" +
	                "id=" + roomTypeId +
	                ", name='" + roomTypeName + '\'' +
	                ", capacity=" + capacity +
	                '}';
	    }
	}