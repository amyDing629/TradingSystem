package User;

import Inventory.Inventory;
import Main.UI.UIcontoller;
import Trade.TradeManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReverseSystemGUI {
    UserManager um;
    TradeManager tm;
    ItemApprovalManager iam;
    UIcontoller uc;
    Inventory iv;
    AdminActivityManager aam;
    JFrame pFrame;
    JFrame frame;
    public ReverseSystemGUI(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam, AdminActivityManager aam,UIcontoller uc ,JFrame pFrame) {
        this.um = um;
        this.tm = tm;
        this.iam=iam;
        this.uc=uc;
        this.iv=iv;
        this.aam=aam;
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

        ClientUser b = um.getUser(name);

        placeComponents(frame, panel, b);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel, ClientUser b){

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(scrollPane, c);
        StringBuilder hi= new StringBuilder("Username:\n");
        ArrayList<ClientUser> name=um.getUserList();
        if(name.size()==0){
            hi.append("Currently there is no users\n");
        }
        for (ClientUser user : name) {
            hi.append(um.getUsername(user)).append("\n");
        }
        System.out.println(hi.toString());
        textArea.setText(hi.toString());
        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

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
            if(um.getUser(userInput.getText())!=null){
                createNext(frame,panel,um.getUser(userInput.getText()),textArea,exit);
            }
        });
    }

    public  void createNext(JFrame frame, JPanel panel,ClientUser a,JTextArea textArea,JButton exit){
        JTextArea textArea1 = new JTextArea(5, 20);
        textArea1.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridwidth = GridBagConstraints.REMAINDER;
        c1.fill = GridBagConstraints.HORIZONTAL;

        c1.fill = GridBagConstraints.BOTH;
        c1.weightx = 1.0;
        c1.weighty = 1.0;
        panel.add(scrollPane1, c1);
        panel.remove(exit);
        if(!textArea.getText().equals("Currently there is no users")){
            StringBuilder lol= new StringBuilder("Actions: ");
            ArrayList<ArrayList<String>> z=um.getActions(a);
            for (ArrayList<String> strings : z) {
                lol.append(strings.get(1)).append("\n");
            }
            textArea1.setText(lol.toString());
        }else{
            textArea1.setEnabled(false);
        }
        panel.add(textArea1);
        panel.add(exit);
    }
}
