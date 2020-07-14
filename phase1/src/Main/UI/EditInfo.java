package Main.UI;

import Inventory.Inventory;
import Inventory.Item;
import Main.GateWay;
import User.AdministrativeUser;
import User.User;
import User.UserManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EditInfo {
    public Scanner sc;
    public UserManager a;
    public User user;
    public Inventory v;
    public GateWay gw;

    public EditInfo(User u, GateWay gw){
        user=u;
        sc = new Scanner(System.in);
        a=new UserManager(gw);
        v = new Inventory(gw);
        this.gw=gw;
    }
    public void run() throws IOException {
        Scanner sc=new Scanner(System.in);
        int exit=-1;
        while(exit!=0) {
            System.out.println("--------------------\nEdit user information");
            System.out.println("Hello,user," + user.getUsername());
            System.out.println("Admin:"+user.getIsAdmin());
            System.out.println("Actions:\n1.Change password");
            if (user.getIsAdmin()) {
                System.out.print("2.Freeze a user\n3.Change user's limit\n4.add new item into the system\n");
                if(user.getId()==null){
                    System.out.print("5.Set user into admin\n");
                }
            }
            System.out.println("0.exit");
            System.out.print(">");
            int input = sc.nextInt();
            sc.nextLine();
            System.out.println("-----------------------------");
            switch (input) {
                case 1:
                    System.out.println("Change password");
                    System.out.println("Type in the password you wanted to change, type 0 to quit.");
                    String input2=sc.nextLine();
                    if (!input2.equals("0")){
                        user.setPassword(input2);
                    }
                    break;
                case 2:
                    System.out.println("Freeze user ");
                    System.out.println("Menu\n1.Freeze user\n2.unfreeze user");
                    if(user.getIsFrozen()){
                        System.out.println("3.request to remove freeze");
                    }
                    int inputF=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Type in the username of user you want to freeze, type 0 to quit.");
                    if(inputF==1) {
                        String input3 = sc.nextLine();
                        if (!input3.equals("0")) {
                            User ha = a.getUser(input3);
                            if (ha == null) {
                                System.out.println("Sorry there is no such user, returning to main menu.");
                            } else {
                                ((AdministrativeUser)a.getUser("admin")).freeze(ha);
                                System.out.println("user.User:" + ha.getUsername() + " account has been frozen");
                                System.out.println("Username: " + ha.getUsername());
                                System.out.println("Username: " + ha.getPassword());
                            }
                        }
                    }
                    else if(inputF==2){
                       ApprovalDataAccess aa=new ApprovalDataAccess(gw);
                       ArrayList<ArrayList<String>> usa=gw.getApprovalUser();
                        for (int i=0;i<usa.size();i++) {
                            System.out.println("User" + i + ": " + usa.get(i).get(1));
                            System.out.println("Reason: " + usa.get(i).get(2));
                            System.out.println("**************************");
                            System.out.println("Enter the item number to approve,enter -1 to quit.");
                            String inputU = sc.nextLine();
                            int k = Integer.parseInt(inputU);
                            if(k<usa.size()&&k>-1){
                                a.getUser(usa.get(k).get(1)).setFrozen(false);
                            }
                        }
                    }
                    else if(inputF==3){
                        System.out.println("Please enter the reason why you should unfreeze...enter -1 to quit");
                        String des=sc.nextLine();
                        if(!des.equals("-1")){
                            try {
                                String data="2"+"/"+user.getUsername()+"/"+des+"\n";
                                File file = new File("phase1/src/ItemApproval.txt");
                                FileWriter fr = new FileWriter(file, true);
                                BufferedWriter br = new BufferedWriter(fr);
                                br.write(data);
                                br.close();
                                fr.close();
                                System.out.println("Request successfully");
                                System.out.println("Please wait for the administrator to approve");
                            } catch (IOException e) {
                                System.out.println("An error occurred.");
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Change user's limit");
                    break;
                case 5:
                    System.out.println("Set a user into admin");
                    System.out.println("Type in the user you want to set to admin, type 0 to quit.");
                    String input4=sc.nextLine();
                    if (!input4.equals("0")){
                        User ha=a.getUser(input4);
                        if (ha==null){
                            System.out.println("Sorry there is no such user, returning to main menu.");
                        }
                        else{((AdministrativeUser)user).addNewUser(ha.getUsername(),ha.getPassword());}
                    }
                    break;
                case 4:
                    ApprovalDataAccess aa=new ApprovalDataAccess(gw);
                    System.out.println("add new item into the system");
                    System.out.println("Menu:\n1.Add item for yourself.\n2.Approve request from users");
                    String inputA=sc.nextLine();
                    if(inputA.equals("1")) {
                        int exit1 = 0;
                        String name = "";
                        while (exit1 == 0) {
                            System.out.println("Type the name of the item");
                            name = sc.nextLine();
                            boolean t = false;
                            for (Item n : v.getLendingList()) {
                                if (n.getName().equals(name)) {
                                    t = true;
                                    break;
                                }
                            }
                            if (t) {
                                System.out.println("The item already exists, please enter the name again");
                            } else {
                                exit1 = 1;
                            }
                        }
                        System.out.println("Type the description of the item");
                        String des = sc.nextLine();
                        Item i = new Item(name, user.getUsername());
                        i.setDescription(des);
                        v.addItem(i);
                    }
                    else if(inputA.equals("2")){
                        try {
                            int x=0;
                            while(x==0) {
                                ArrayList<ArrayList<String>> hii=gw.getApprovalItem();
                                for (int i=0;i<hii.size();i++) {
                                    System.out.println("Item " + i + ": " + hii.get(i).get(1));
                                    System.out.println("Description: " + hii.get(i).get(2));
                                    System.out.println("Owner: " + hii.get(i).get(3));
                                    System.out.println("**************************");
                                }
                                if(hii.size()==0){
                                    System.out.println("There is no item currently");
                                }
                                System.out.println("Enter the item number to approve,enter -1 to quit.");
                                String inputs = sc.nextLine();
                                int k = Integer.parseInt(inputs);
                                System.out.println(k);
                                if (k > -1 && k < (hii.size())) {
                                    Item i = new Item(hii.get(k).get(1), hii.get(k).get(3));
                                    i.setDescription(hii.get(k).get(2));
                                    a.getUser(hii.get(k).get(3)).addWishes(hii.get(k).get(1));
                                    v.addItem(i);
                                    gw.getApprovalItem().remove(k);
                                    aa.updateFile();
                                    System.out.println("Approve successfully");
                                }else if(k==-1){
                                    x=1;
                                }
                                else {
                                    System.out.println("You enter the wrong number!");
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                case 0:
                    exit=0;
                    break;
            }
        }
    }
}