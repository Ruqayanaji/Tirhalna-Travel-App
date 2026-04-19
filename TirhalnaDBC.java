import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
public class Tirhalna {
	private static final Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO = new UserDAO();
    private static ActivityDAO activityDAO = new ActivityDAO();
    private static BookingDAO bookingDAO = new BookingDAO();
    private static HotelDAO hotelDAO = new HotelDAO();       
    private static RoomTypeDAO roomTypeDAO = new RoomTypeDAO(); 
    private static User currentUser = null; 
    
    public static void main (String[] args) {
    	System.out.println("=========================================");
        System.out.println("       🚀 نظام حجز رحلات ترحالنا 🚀");
        System.out.println("=========================================");
        
        mainMenu();
    }

    // ------------------ واجهة المستخدم الرئيسية ------------------
    private static void mainMenu() {
        while (true) {
            System.out.println("\n--- القائمة الرئيسية ---");
            System.out.println("1. تسجيل الدخول (Login)");
            System.out.println("2. إنشاء حساب جديد (Register)");
            System.out.println("3. الخروج (Exit)");
            System.out.print("الرجاء اختيار رقم: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    loginUser();
                    if (currentUser != null) {
                        userMenu(); // الانتقال لقائمة المستخدم بعد تسجيل الدخول بنجاح
                    }
                    break;
                case "2":
                    registerUser();
                    break;
                case "3":
                    System.out.println("شكراً لاستخدامك نظام ترحالنا. مع السلامة!");
                    scanner.close();
                    return;
                default:
                    System.out.println("⚠️ اختيار غير صالح. الرجاء المحاولة مجدداً.");
            }
        }
    }
    
    // ------------------ وظائف التسجيل والدخول ------------------

    private static void loginUser() {
        System.out.println("\n--- تسجيل الدخول ---");
        System.out.print("أدخل بريدك الإلكتروني (User Email): ");
        String email = scanner.nextLine();
        
        System.out.print("أدخل رقم الهاتف (Phone Number): ");
        // يجب أن نضمن إدخال رقم صحيح
        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("❌ إدخال غير صحيح. رقم الهاتف يجب أن يكون رقماً.");
            return;
        }

        try (Connection conn = TirhalnaDBC.getConnection()) {
            if (conn == null) return; 

            User user = userDAO.getUserByEmailAndPhone(conn, email, phoneNumber); 
            
            if (user != null) {
                currentUser = user;
                System.out.println("\n✅ مرحباً بك، " + user.getFirstName() + "! تم تسجيل الدخول بنجاح.");
            } else {
                System.out.println("❌ فشل تسجيل الدخول. البريد الإلكتروني أو رقم الهاتف غير صحيح.");
            }
        } catch (SQLException e) {
            System.err.println("❌ حدث خطأ أثناء الاتصال بقاعدة البيانات: " + e.getMessage());
        }
    }
    
    private static void registerUser() {
        System.out.println("\n--- إنشاء حساب جديد ---");
        System.out.print("أدخل اسمك الأول: ");
        String firstName = scanner.nextLine();
        
        System.out.print("أدخل اسم العائلة: ");
        String lastName = scanner.nextLine();
        
        System.out.print("أدخل بريدك الإلكتروني: ");
        String email = scanner.nextLine();
        
        System.out.print("أدخل رقم الهاتف: ");
        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("❌ إدخال غير صحيح. رقم الهاتف يجب أن يكون رقماً.");
            return;
        }

        User newUser = new User(firstName, lastName, email, phoneNumber);

        try (Connection conn = TirhalnaDBC.getConnection()) {
            if (conn == null) return;
            
            int newId = userDAO.addUser(conn, newUser); // استخدام الدالة المتاحة
            
            if (newId > 0) {
                System.out.println("\n✅ تم إنشاء حسابك بنجاح! رقم المستخدم الخاص بك هو: " + newId);
            } else {
                System.out.println("❌ فشل التسجيل. قد يكون البريد الإلكتروني أو رقم الهاتف مستخدماً مسبقاً.");
            }
        } catch (SQLException e) {
            System.err.println("❌ حدث خطأ أثناء التسجيل: " + e.getMessage());
        }
    }


    // ------------------ قائمة المستخدم (بعد الدخول) ------------------

    private static void userMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- قائمة المستخدم: " + currentUser.getFirstName() + " ---");
            System.out.println("1. عرض الأنشطة المتاحة");
            System.out.println("2. حجز نشاط (Activity)");
            System.out.println("3. عرض الفنادق المتاحة");
            System.out.println("4. حجز فندق (Hotel)");
            System.out.println("5. تسجيل الخروج");
            System.out.print("الرجاء اختيار رقم: ");

            String choice = scanner.nextLine();

            switch (choice) {
            case "1":
                displayActivities();
                break;
            case "2":
                bookActivity();
                break;
            case "3":
                displayHotels(); 
                break;
            case "4":
                bookHotel();    
                break;
            case "5":
                currentUser = null;
                running = false;
                System.out.println("✅ تم تسجيل الخروج.");
                break;
            default:
                System.out.println("⚠️ اختيار غير صالح. الرجاء المحاولة مجدداً.");
            }
        }
    }

    // ------------------ وظائف الحجز ------------------

    private static void displayActivities() {
        System.out.println("\n--- الأنشطة المتاحة حالياً ---");
        try (Connection conn = TirhalnaDBC.getConnection()) {
            if (conn == null) return;

            List<Activity> activities = activityDAO.getAllActivities(conn);
            
            if (activities.isEmpty()) {
                System.out.println("لا توجد أنشطة متاحة حالياً.");
                return;
            }

            // عرض الأنشطة في جدول منظم
            System.out.printf("| %-4s | %-25s | %-12s | %-6s | %-8s |\n", 
                              "ID", "الاسم", "الموقع", "السعر", "السعة");
            System.out.println("------------------------------------------------------------------");
            
            for (Activity act : activities) {
                System.out.printf("| %-4d | %-25s | %-12s | %-6.2f | %-8d |\n",
                                  act.getActivityId(),
                                  act.getActivityName(),
                                  act.getActivityLocation(),
                                  act.getActivityPrice(),
                                  act.getCapacity());
            }
            System.out.println("------------------------------------------------------------------");

        } catch (SQLException e) {
            System.err.println("❌ خطأ أثناء جلب الأنشطة: " + e.getMessage());
        }
    }

    private static void bookActivity() {
        displayActivities(); // عرض الأنشطة أولاً

        System.out.println("\n--- حجز نشاط ---");
        System.out.print("أدخل رقم النشاط (Activity ID) الذي تريد حجزه: ");
        int activityId;
        try {
            activityId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("❌ إدخال غير صحيح.");
            return;
        }
        
        System.out.print("أدخل عدد المشاركين: ");
        int numPeople;
        try {
            numPeople = Integer.parseInt(scanner.nextLine());
            if (numPeople <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("❌ عدد المشاركين يجب أن يكون رقماً صحيحاً وموجباً.");
            return;
        }

        // إنشاء كائن الحجز (Booking Object)
        Booking newBooking = new Booking();
        newBooking.setUserID(currentUser.getUserID());
        newBooking.setActivityID(activityId);
        newBooking.setNumOfPeopleActivity(numPeople);
        newBooking.setBookingDate(LocalDate.now()); // تاريخ الحجز هو اليوم

        newBooking.setCheck_in_activity(LocalDate.now());
        
        try {
            newBooking.validateBooking();
            
            try (Connection conn = TirhalnaDBC.getConnection()) {
                if (conn == null) return;
                
                int bookingId = bookingDAO.createBooking(conn, newBooking);
                
                if (bookingId > 0) {
                    System.out.println("\n=========================================");
                    System.out.println("✅ تم الحجز بنجاح!");
                    newBooking.setBookingID(bookingId); // تعيين الـ ID للحجز
                    System.out.println(newBooking.toString());
                    System.out.println("=========================================");
                } else {
                    System.out.println("❌ فشل الحجز. (ربما السعة غير كافية أو خطأ قاعدة بيانات).");
                }
            }
            
        } catch (IllegalArgumentException e) {
            System.err.println("❌ فشل التحقق من الحجز: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ خطأ في قاعدة البيانات أثناء الحجز: " + e.getMessage());
        }
        
    }
    private static void displayHotels() {
        System.out.println("\n--- الفنادق المتاحة حالياً ---");
        try (Connection conn = TirhalnaDBC.getConnection()) {
            if (conn == null) return;

            List<Hotel> hotels = hotelDAO.getAllHotels(conn);
            
            if (hotels.isEmpty()) {
                System.out.println("لا توجد فنادق متاحة حالياً.");
                return;
            }

            System.out.printf("| %-4s | %-30s | %-15s | %-6s |\n", 
                              "ID", "اسم الفندق", "الموقع", "النجوم");
            System.out.println("------------------------------------------------------------------");
            
            for (Hotel hotel : hotels) {
                System.out.printf("| %-4d | %-30s | %-15s | %-6d |\n",
                                  hotel.getHotelId(),
                                  hotel.getHotelName(),
                                  hotel.getHotelLocation(),
                                  hotel.getHotelStars());
            }
            System.out.println("------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("❌ خطأ أثناء جلب الفنادق: " + e.getMessage());
        }
    }

    private static void bookHotel() {
        displayHotels();
        System.out.println("\n--- حجز فندق ---");
        System.out.print("أدخل رقم الفندق (Hotel ID) الذي تريد حجزه: ");
        int hotelId;
        try {
            hotelId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("❌ إدخال غير صحيح.");
            return;
        }
        
        // التحقق من وجود الفندق
        try (Connection conn = TirhalnaDBC.getConnection()) {
            if (conn == null) return;
            Hotel selectedHotel = hotelDAO.getHotelById(conn, hotelId);
            if (selectedHotel == null) {
                System.err.println("❌ الفندق برقم " + hotelId + " غير موجود.");
                return;
            }
            
            System.out.println("✅ الفندق: " + selectedHotel.getHotelName() + " (" + selectedHotel.getHotelStars() + " نجوم).");
            
            // عرض الغرف المتاحة
            List<HotelRoom> rooms = hotelDAO.getAvailableRoomsByHotelId(conn, hotelId);
            if (rooms.isEmpty()) {
                System.out.println("❌ لا توجد غرف متاحة في هذا الفندق حالياً.");
                return;
            }
            
            System.out.println("\n--- الغرف المتاحة في " + selectedHotel.getHotelName() + " ---");
            System.out.printf("| %-4s | %-18s | %-8s | %-8s |\n", 
                              "ID", "نوع الغرفة", "السعر", "العدد");
            System.out.println("--------------------------------------------------");
            
            for (HotelRoom hr : rooms) {
                RoomType rt = roomTypeDAO.getRoomTypeById(conn, hr.getRoomTypeId()); 
                String roomName = rt != null ? rt.getRoomTypeName() : "غير معروف";

                System.out.printf("| %-4d | %-18s | %-8.2f | %-8d |\n",
                                  hr.getRoomTypeId(),
                                  roomName,
                                  hr.getRoomPrice(),
                                  hr.getRoomAvailable());
            }
            System.out.println("--------------------------------------------------");
            
            // اختيار الغرفة
            System.out.print("أدخل رقم نوع الغرفة (Room Type ID) الذي تريد حجزه: ");
            int roomTypeId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("أدخل تاريخ الوصول (YYYY-MM-DD): ");
            LocalDate checkIn = LocalDate.parse(scanner.nextLine());
            
            System.out.print("أدخل تاريخ المغادرة (YYYY-MM-DD): ");
            LocalDate checkOut = LocalDate.parse(scanner.nextLine());
            
            System.out.print("أدخل عدد الأشخاص في الغرفة: ");
            int numPeople = Integer.parseInt(scanner.nextLine());

            // إنشاء كائن الحجز
            Booking newBooking = new Booking();
            newBooking.setUserID(currentUser.getUserID());
            newBooking.setHotelID(hotelId);
            newBooking.setRoomTypeID(roomTypeId);
            newBooking.setNumOfPeopleHotel(numPeople);
            newBooking.setBookingDate(LocalDate.now());
            newBooking.setCheck_in_hotel(checkIn);
            newBooking.setCheck_out_hotel(checkOut);
            
            // محاولة الحجز
            try {
                newBooking.validateBooking(); // التحقق من الصلاحية
                int bookingId = bookingDAO.createBooking(conn, newBooking);
                
                if (bookingId > 0) {
                    System.out.println("\n=========================================");
                    System.out.println("✅ تم حجز الفندق بنجاح!");
                    newBooking.setBookingID(bookingId);
                    System.out.println(newBooking.toString());
                    System.out.println("=========================================");
                } else {
                    System.out.println("❌ فشل الحجز. (قد يكون خطأ في التواريخ أو السعة).");
                }
            } catch (IllegalArgumentException | SQLException e) {
                System.err.println("❌ خطأ في الحجز: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ حدث خطأ غير متوقع أثناء حجز الفندق: " + e.getMessage());
        }
    }

}