import java.util.*;
public class AdministrativeUser extends User {
    private String password;
    private String username;
    private int id;

    private List<String> notification;

    public AdministrativeUser(String username, String password, int id){
        super(username, password, id);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<String> getNotification() {
        return notification;
    }

    @Override
    public void addNotification(String no) {
        notification.add(no);
    }

    @Override
    public void changePassword(String password) {
        this.password=password;
    }

    @Override
    public void changeUsername(String username) {
        this.username=username;
    }

    public void freeze(ClientUser a){a.setIsFrozen(false);}

    public void unfreeze(ClientUser a){a.setIsFrozen(true);}

    public AdministrativeUser addNewUser(String username, String password, int id){
        AdministrativeUser a = new AdministrativeUser(username, password, id);
        return a;
    }

    public void confirmItem(ClientUser a, Item b){
        a.addWishLend(b.getName());
    }

    public void changeValue(int a){
        
    }

}