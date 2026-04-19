import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
public class ActivityDAO {
	public List<Activity> getAllActivities(Connection dbConnection) throws SQLException {
        List<Activity> activities = new ArrayList<>();
        String sql = "SELECT Activity_ID, Activity_Name, Activity_Type, Activity_Location, Start_Time, End_Time, Capacity, Activity_Price, Destination_ID FROM Activity";
        
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Activity activity = new Activity(
                    rs.getInt("Activity_ID"),
                    rs.getString("Activity_Name"),
                    rs.getString("Activity_Type"),
                    rs.getString("Activity_Location"),
                    rs.getTime("Start_Time") != null ? rs.getTime("Start_Time").toLocalTime() : null,
                    rs.getTime("End_Time") != null ? rs.getTime("End_Time").toLocalTime() : null,
                    rs.getInt("Capacity"),
                    rs.getDouble("Activity_Price"),
                    rs.getInt("Destination_ID")
                );
                activities.add(activity);
            }
        }
        return activities;
    }

    public int addActivity(Connection dbConnection, Activity activity) throws SQLException {
        if (!activity.isValid()) {
            System.err.println("Error: Activity object validation failed.");
            return 0;
        }
        
        String sql = "INSERT INTO Activity (Activity_Name, Activity_Type, Activity_Location, Start_Time, End_Time, Capacity, Activity_Price, Destination_ID) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int generatedId = 0;
        
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, activity.getActivityName());
            pstmt.setString(2, activity.getActivityType());
            pstmt.setString(3, activity.getActivityLocation());
            pstmt.setTime(4, java.sql.Time.valueOf(activity.getStartTime())); 
            pstmt.setTime(5, java.sql.Time.valueOf(activity.getEndTime())); 
            pstmt.setInt(6, activity.getCapacity());
            pstmt.setDouble(7, activity.getActivityPrice());
            pstmt.setInt(8, activity.getDestinationId());
            
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    activity.setActivityId(generatedId);
                }
            }
            
            dbConnection.commit();
            return generatedId;
        }
    }
}
