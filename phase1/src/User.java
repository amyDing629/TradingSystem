import java.util.*;
abstract class User {
    private int id;
    private String password;
    private String username;
    private List<String> notification;

    public User(String username, String password, int id){
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public abstract String getPassword();
    public abstract String getUsername();
    public abstract int getId();

    public abstract List<String> getNotification();
    public abstract void addNotification(String no);
    public abstract void changePassword(String password);
    public abstract void changeUsername(String username);

}