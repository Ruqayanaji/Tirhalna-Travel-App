import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
public class Activity implements Serializable{
	 private static final long serialVersionUID = 1L;
	    
	    private int activityId;
	    private String activityName;
	    private String activityType;
	    private String activityLocation;
	    private LocalTime startTime;
	    private LocalTime endTime;
	    private int capacity;
	    private double activityPrice;
	    private int destinationId;
	    
	    public Activity() {
	    }
	    
	    public Activity(int activityId, String activityName, String activityType,
	                   String activityLocation, LocalTime startTime, LocalTime endTime,
	                   int capacity, double activityPrice, int destinationId) {
	        this.activityId = activityId;
	        this.activityName = activityName;
	        this.activityType = activityType;
	        this.activityLocation = activityLocation;
	        this.startTime = startTime;
	        this.endTime = endTime;
	        this.capacity = capacity;
	        this.activityPrice = activityPrice;
	        this.destinationId = destinationId;
	    }
	    
	    public Activity(String activityName, String activityType, String activityLocation,
	                   LocalTime startTime, LocalTime endTime, int capacity,
	                   double activityPrice, int destinationId) {
	        this.activityName = activityName;
	        this.activityType = activityType;
	        this.activityLocation = activityLocation;
	        this.startTime = startTime;
	        this.endTime = endTime;
	        this.capacity = capacity;
	        this.activityPrice = activityPrice;
	        this.destinationId = destinationId;
	    }
	    
	    public int getActivityId() {
	        return activityId;
	    }
	    
	    public String getActivityName() {
	        return activityName;
	    }
	    
	    public String getActivityType() {
	        return activityType;
	    }
	    
	    public String getActivityLocation() {
	        return activityLocation;
	    }
	    
	    public LocalTime getStartTime() {
	        return startTime;
	    }
	    
	    public LocalTime getEndTime() {
	        return endTime;
	    }
	    
	    public int getCapacity() {
	        return capacity;
	    }
	    
	    public double getActivityPrice() {
	        return activityPrice;
	    }
	    
	    public int getDestinationId() {
	        return destinationId;
	    }
	    
	    public void setActivityId(int activityId) {
	        this.activityId = activityId;
	    }
	    
	    public void setActivityName(String activityName) {
	        this.activityName = activityName;
	    }
	    
	    public void setActivityType(String activityType) {
	        this.activityType = activityType;
	    }
	    
	    public void setActivityLocation(String activityLocation) {
	        this.activityLocation = activityLocation;
	    }
	    
	    public void setStartTime(LocalTime startTime) {
	        this.startTime = startTime;
	    }
	    
	    public void setEndTime(LocalTime endTime) {
	        this.endTime = endTime;
	    }
	    
	    public void setCapacity(int capacity) {
	        this.capacity = capacity;
	    }
	    
	    public void setActivityPrice(double activityPrice) {
	        this.activityPrice = activityPrice;
	    }
	    
	    public void setDestinationId(int destinationId) {
	        this.destinationId = destinationId;
	    }
	    
	    public long getDurationInMinutes() {
	        if (startTime != null && endTime != null) {
	            return java.time.temporal.ChronoUnit.MINUTES.between(startTime, endTime);
	        }
	        return 0;
	    }
	    
	    public boolean isValid() {
	        return activityName != null && !activityName.trim().isEmpty() &&
	               activityType != null && !activityType.trim().isEmpty() &&
	               activityLocation != null && !activityLocation.trim().isEmpty() &&
	               startTime != null && endTime != null &&
	               startTime.isBefore(endTime) &&
	               capacity > 0 &&
	               activityPrice >= 0 &&
	               destinationId > 0;
	    }
	    
	    @Override
	    public String toString() {
	        return "Activity{" +
	                "activityId=" + activityId +
	                ", activityName='" + activityName + '\'' +
	                ", activityType='" + activityType + '\'' +
	                ", activityLocation='" + activityLocation + '\'' +
	                ", startTime=" + startTime +
	                ", endTime=" + endTime +
	                ", capacity=" + capacity +
	                ", activityPrice=" + activityPrice +
	                ", destinationId=" + destinationId +
	                '}';
	    }
	    
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Activity activity = (Activity) o;
	        return activityId == activity.activityId &&
	               Objects.equals(activityName, activity.activityName);
	    }
	    
	    @Override
	    public int hashCode() {
	        return Objects.hash(activityId, activityName);
	    }
}
