import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class TirhalnaDBC {
    
    public static final String DB_NAME = "tirhalna";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "mypassword";
    
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    public static final String URL = "jdbc:mysql://localhost:3307/" + DB_NAME;

    

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER); 
            
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false); 
            
        } catch (ClassNotFoundException e) {
            System.err.println("خطأ: لم يتم العثور على سائق MySQL. تأكد من ملف JAR.");
        } catch (SQLException e) { 
            System.err.println("خطأ في الاتصال بقاعدة البيانات: " + e.getMessage());
        }
        return connection;
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("فشل إغلاق الاتصال: " + e.getMessage());
            }
        }
    }
}
