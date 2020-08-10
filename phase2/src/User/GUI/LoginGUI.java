package User.GUI;

import Inventory.MarketBuilder;
import Main.DataAccessFull;
import Trade.BorderGUIBuilder;
import Trade.TradeGUIEngineer;
import Trade.TradeGUIPlan;
import User.Adapter.ClientUserController;
import User.Adapter.ClientUserPresenter;
import User.Adapter.IUserPresenter;
import User.Adapter.LoginSystemPresenter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.UUID;

public class LoginGUI{

    JFrame frame;
    LoginSystemPresenter lsp;

    // presenter

    public void run() {
        lsp = new LoginSystemPresenter(frame);
        frame = new JFrame("Login/Register");
        frame.setSize(330, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(frame, panel);
        frame.setVisible(true);
    }

    private void placeComponents(JFrame frame, JPanel panel){
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField nameInput = new JTextField(20);
        nameInput.setBounds(100,20,80,25);
        panel.add(nameInput);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordInput = new JPasswordField(20);
        passwordInput.setBounds(100,50,80,25);
        panel.add(passwordInput);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(190,80,80,25);
        panel.add(registerButton);

        JButton logInButton = new JButton("login");
        logInButton.setBounds(100,80,80,25);
        panel.add(logInButton);

        JButton exploreButton=new JButton("explore");
        JButton exitButton = new JButton("quit");
        exitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(exploreButton);
        panel.add(exitButton);


        logInButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = Arrays.toString(passwordInput.getPassword());
            boolean response = lsp.login(name, password);
            //boolean response = lsp.getUserModel().verifyUser(name, password);
            if (!response)
                JOptionPane.showMessageDialog(null, "invalid user");
            else {
                frame.setVisible(false);

                ClientUserController controller = new ClientUserController();
                UUID uuid = controller.getIDbyName(name);
                ClientUserPresenter clientUserPresenter = new ClientUserPresenter(uuid, frame);
                clientUserPresenter.run();

                ClientUserGUI a = new ClientUserGUI(frame, name);
                a.run();
            }
        });

        registerButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = Arrays.toString(passwordInput.getPassword());
            lsp.register(name, password);
            //lsp.getUserModel().createClientUser(name, password, false);
            JOptionPane.showMessageDialog(null, "success");
        });


        exploreButton.addActionListener(e -> {
            frame.setVisible(false);
            BorderGUIBuilder builder = new MarketBuilder(frame);
            TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
            engineer.constructGUI();
            TradeGUIPlan tg = engineer.getGUI();
            tg.run();
        });

        exitButton.addActionListener(e -> {

            // TODO: only access controller or presenter, allow use case to use gateway
//
//            try {
//
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
            frame.setVisible(false);
            DataAccessFull adf = new DataAccessFull();
            adf.updateFile();

        });



    }

}
