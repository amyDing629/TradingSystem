package Main.UI;
import User.ClientUser;
import User.UserManager;

import java.util.Scanner;
//check
public class ChangePass {
    Scanner sc;
    ClientUser user;
    UserManager um;
    UIcontoller uc;
    public ChangePass(ClientUser user, UserManager um,UIcontoller uc){
        this.um=um;
        sc=new Scanner(System.in);
        this.user=user;
        this.uc=uc;
    }
    public void run(){
        System.out.println("Change password");
        String input2=uc.getString("Type in the password you wanted to change, type 0 to quit.");
        if (!input2.equals("0")){
            um.set(user,input2);
            System.out.println("Changed password successfully!");
        }
        else{
            System.out.println("returning....");
        }
    }
}