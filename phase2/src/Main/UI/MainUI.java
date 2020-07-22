package Main.UI;

import Inventory.Inventory;
import Main.DataAccessFull;
import Trade.TradeManager;
import User.AdministrativeUser;
import User.ItemApprovalManager;
import User.UserDataAccess;
import User.UserManager;
import com.sun.deploy.ui.DialogTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainUI {
    UserManager um;
    TradeManager tm;
    Inventory iv;
    ItemApprovalManager iam;

    public MainUI(UserManager um, TradeManager tm, Inventory iv,ItemApprovalManager iam) {
        this.um = um;
        this.tm = tm;
        this.iv = iv;
        this.iam=iam;
    }

    public void run() throws IOException {
        int a = -1;
        Inventory iv = new Inventory();
        ItemApprovalManager iam = new ItemApprovalManager();
        File file = new File("phase2/src/username.txt");

        if (file.length() == 0) {
            AdministrativeUser b = new AdministrativeUser("admin", "123", true, tm, um);
            um.addUser(b);
            new UserDataAccess(um).updateFile();
        }
        DataAccessFull w = new DataAccessFull(um,tm,iv,iam);
        w.readFile(tm, iv, um);
        while (a != 0) {
            //print out the list of current users-------------------------------
            //gw = new GateWay();
            //w = new DataAccessFull(gw);
            //w.readFile();
            System.out.println("Users:");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(
                        "phase2/src/username.txt"));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //------------------------------------------------------------------
            System.out.println("\nMenu:\n1.login\n2.register\n0.quit");
            System.out.println("Please enter the number only.");
            Scanner sc = new Scanner(System.in);
            System.out.print(">");
            a = sc.nextInt();
            sc.nextLine();
            if (a == 1) {
                //System.out.println(gw.getUsers().get(3).getIsFrozen());
                Login login = new Login(um, tm, iv, iam);
                login.run();
            } else if (a == 2) {
                Register reg = new Register(um);
                reg.run();
            }
            System.out.println("------------------------------");
            w = new DataAccessFull(um,tm,iv,iam);
            w.updateFile();
        }
    }
}