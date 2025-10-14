package model;

// SessionModel.java
public final class SessionModel {
    private static volatile UsuarioModel currentUser;

    private SessionModel() { }

    public static UsuarioModel getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UsuarioModel user) {
        currentUser = user;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void clear() {
        currentUser = null;
    }
}
