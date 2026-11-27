package e_commerce_platform;

import e_commerce_platform.ui.userProfilePage;

public class UserSession {
    
    private static int userId;
    private static String username;
    private static String email;
    private static String role;
    
    public static void setUser(int id, String name, String userEmail, String role) {
        userId = id;
        username = name;
        email = userEmail;
        role = role;
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
    public static String getRole() {
        return role;
    }
    public static boolean isAdmin() {
        return role != null && role.equalsIgnoreCase("ADMIN");
    }
    public static void clearSession() {
        userId = 0;
        username = null;
        email = null;
        role = null;
    }
}
