package User;

import Inventory.Inventory;
import Main.UI.UIcontoller;
import Trade.TradeManager;

import javax.swing.*;
import java.awt.*;

public class DiffLimitGUI {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIcontoller uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame pFrame;
    JFrame frame;
    public DiffLimitGUI(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, AdminActivityManager aam,UIcontoller uc ,JFrame pFrame) {
        this.um = um;
        this.tm = tm;
        this.iam=iam;
        this.uc=uc;
        this.iv=iv;
        this.aam=aam;
        this.pFrame=pFrame;
    }
    public void run(String name){
        frame = new JFrame("Trade limit");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        ClientUser b =um.getUser(name);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){

        JLabel textLabel = new JLabel("Please enter the user's username and the limit below");
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
            aam.setDiff(um.getUser(userInput.getText()),Integer.parseInt(userInput1.getText()));
            JOptionPane.showMessageDialog(null, "success changed limit");
            UserFreezeSystem d = new UserFreezeSystem(um,tm,iv,iam,aam,uc,frame);
            d.run(um.getUsername(b));
        });
    }
}
