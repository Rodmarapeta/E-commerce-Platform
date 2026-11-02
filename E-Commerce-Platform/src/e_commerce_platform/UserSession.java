package e_commerce_platform;

public class UserSession {
    
    private static int userId;
    private static String username;
    private static String email;
    
    public static void setUser(int id, String name, String userEmail) {
        userId = id;
        username = name;
        email = userEmail;
    }
    public static int getUserId() {
        return userId;
    }
    public static String getUsername() {
        return username;
    }
    public static String getEmail() {
        return email;
    }
    public static void clearSession() {
        userId = 0;
        username = null;
        email = null;
    }
}
