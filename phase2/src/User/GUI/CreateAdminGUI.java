package User.GUI;

import Inventory.Inventory;
import Trade.TradeManager;
import User.Adapter.UIController;
import User.UseCase.AdminActivityManager;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;

public class CreateAdminGUI {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIController uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame pFrame;
    JFrame frame;
    public CreateAdminGUI(UIController uc ,JFrame pFrame) {
        this.um = new UserManager();
        this.tm = new TradeManager();
        this.iam= new ItemApprovalManager();
        this.uc=uc;
        this.iv= new Inventory();
        this.aam=new AdminActivityManager();
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Freeze User");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);


        placeComponents(frame, panel, name);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, String b){

        JLabel textLabel = new JLabel("Please enter the user's username below");
        textLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(textLabel);


        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

        JTextField userInput1 = new JTextField(30);
        userInput1.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput1);


        JButton submitButton = new JButton("submit");
        submitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(submitButton);

        JButton exit = new JButton("exit");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);
        exit.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        submitButton.addActionListener(e -> {
            frame.setVisible(false);
            aam.addNewAdmin(userInput.getText(),userInput1.getText());
            JOptionPane.showMessageDialog(null, "successfully created");
            UserFreezeSystem d = new UserFreezeSystem(uc,frame);
            d.run(b);
        });
    }
}
