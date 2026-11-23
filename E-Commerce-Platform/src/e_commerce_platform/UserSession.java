package e_commerce_platform;

import java.util.HashMap;

public class UserSession {

    private static HashMap<String, Object> session =
            new HashMap<>();

    public static void setUser(int id, String name, String userEmail) {
        session.put("userId", id);
        session.put("username", name);
        session.put("email", userEmail);
    }

    public static int getUserId() {
        return (int) session.getOrDefault("userId", 0);
    }

    public static String getUsername() {
        return (String) session.get("username");
    }

    public static String getEmail() {
        return (String) session.get("email");
    }

    public static void clearSession() {
        session.clear();
    }
}
