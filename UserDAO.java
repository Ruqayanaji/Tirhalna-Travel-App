
public class User {
	private int userID;
    private String firstName;
    private String lastName;
    private String userEmail;
    private int phoneNumber;

    public User() {
    }
 // Constructor لتسجيل مستخدم جديد (بدون ID)
    public User(String firstName, String lastName, String userEmail, int phoneNumber) {
        this(0, firstName, lastName, userEmail, phoneNumber);
    }
    public User(int userID, String firstName, String lastName,
    		String userEmail, int phoneNumber) {

        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Simple print method
    public void printInfo() {
        System.out.println("User ID: " + userID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + userEmail);
        System.out.println("Phone: " + phoneNumber);
    }
}
