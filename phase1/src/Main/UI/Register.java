package Main.UI;

import Main.GateWay;
import User.AdministrativeUser;
import User.FileEditor;
import User.User;
import User.UserManager;

import java.io.IOException;
import java.util.Scanner;

public class Register {
    public Scanner sc;
    public UserManager a;
    public FileEditor fe;

    public Register(GateWay gw){
        sc = new Scanner(System.in);
        a=new UserManager(gw);
        fe=new FileEditor(gw);
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        int input=0;
        while(input!=1) {
            System.out.println("--------------------\nRegister");
            System.out.println("Please enter your username!");
            System.out.print(">");
            String username = sc.nextLine();
            System.out.println("Please enter your password!");
            System.out.print(">");
            String password = sc.nextLine();
            if (a.getUser(username) == null) {
                User user1 = new User(username, password, false);
                fe.addToUsers(user1);
                System.out.println("Your account has been successfully created!");
                System.out.println("Your id: " + user1.getId());
                System.out.println("Your username: " + user1.getUsername());
                System.out.println("Your password: " + user1.getPassword());
                input=1;
            }
            else{
                System.out.println("The username already exists, please try to register again, enter any number to continue. Enter 1 to exit.");
                input=sc.nextInt();
                sc.nextLine();
            }
        }
    }
}