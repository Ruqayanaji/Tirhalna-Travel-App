import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

public class UserDAO {

public int addUser(Connection dbConnection, User user) throws SQLException {

        String sql = "INSERT INTO Users (First_name, Last_name, User_Email,Phone_Number) VALUES (?, ?, ?, ?)";

        int generatedId = 0;

        

        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            

            pstmt.setString(1, user.getFirstName());

            pstmt.setString(2, user.getLastName());

            pstmt.setString(3, user.getUserEmail());

            pstmt.setInt(4, user.getPhoneNumber());

            

            pstmt.executeUpdate();

            

            try (ResultSet rs = pstmt.getGeneratedKeys()) {

                if (rs.next()) {

                    generatedId = rs.getInt(1);

                    user.setUserID(generatedId);

                }

            }

            

            dbConnection.commit();

            return generatedId;

        }

    }

    

    public User getUserById(Connection dbConnection, int userId) throws SQLException {

        String sql = "SELECT User_id, First_name, Last_name, User_Email, Phone_Number FROM User WHERE userID = ?";

        

        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    return new User(

                        rs.getInt("User_id"),

                        rs.getString("First_name"),

                        rs.getString("Last_name"),

                        rs.getString("User_Email"),

                        rs.getInt("Phone_Number")

                    );

                }

            }

        }

        return null;

    }
    /**
     * ⭐️ دالة جديدة: جلب المستخدم باستخدام البريد الإلكتروني ورقم الهاتف (لتسجيل الدخول). ⭐️
     */
    public User getUserByEmailAndPhone(Connection dbConnection, String userEmail, int phoneNumber) throws SQLException {
        // نستخدم أسماء الأعمدة الخاصة بك (User_Email, Phone_Number, إلخ)
        String sql = "SELECT User_id, First_name, Last_name, User_Email, Phone_Number " +
                     "FROM Users WHERE User_Email = ? AND Phone_Number = ?";
        
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, userEmail);
            pstmt.setInt(2, phoneNumber); 
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("User_id"),
                        rs.getString("First_name"),
                        rs.getString("Last_name"),
                        rs.getString("User_Email"),
                        rs.getInt("Phone_Number")
                    );
                }
            }
        }
        return null; // فشل تسجيل الدخول
    }
}