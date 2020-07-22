package Trade;

import Inventory.Inventory;
import Inventory.Item;
import User.ClientUser;
import User.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RTradeGUI {
    /**
     * current user that use this system
     */
    private final ClientUser currUser;
    /**
     * the item selected that is used to request trade
     */
    private final Item item;
    /**
     * owner of the selected item
     */
    private final TradeController trc;
    private final TradePresenter tp;

    /**
     * [constructor]
     * @param currUser current user
     * @param item item selected by the current user
     */
    public RTradeGUI(ClientUser currUser, Item item, TradeManager tm, UserManager um, Inventory iv){
        trc = new TradeController(currUser, tm, um, iv);
        this.currUser = currUser;
        this.item = item;
        tp = new TradePresenter(currUser);
        trc.getTarUser(item);
    }

    public void run(){
        JFrame frame = new JFrame("Request Trade Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        JTextArea jtz = new JTextArea();
        frame.getContentPane().add(BorderLayout.CENTER, jtz);
        frame.setVisible(true);
        if (!trc.checkInput(item).equals("true")){
            jtz.setText(trc.checkInput(item));
        }else{
            JPanel panel = new JPanel();
            jtz.setText("Current User: " + currUser.getUsername() + "\n" + "Item to request the trade: " + item.getName()
                    + "\n" + "Suggest item to lend if make a two way trade: " + trc.getSuggestedItemName());
            JButton onewayTemp = new JButton("One way-Temporary");
            JButton onewayPer = new JButton("One way-Permanent");
            JButton twoway = new JButton("Two way");
            panel.add(onewayTemp);
            panel.add(onewayPer);
            panel.add(twoway);
            frame.getContentPane().add(BorderLayout.SOUTH, panel);
            onewayTemp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (item.getIsInTrade()){
                        jtz.setText("the item is already in trade");
                    }else{
                        trc.createTrade("1", item);
                        jtz.setText("the trade(One way-Temporary) has been created");
                    }
                }
            });
            onewayPer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (item.getIsInTrade()){
                        jtz.setText("the item is already in trade");
                    }else{
                        trc.createTrade("2", item);
                        jtz.setText("the trade(One way-Temporary) has been created");
                    }
                }
            });
            twoway.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (item.getIsInTrade()){
                        jtz.setText("the item is already in trade");
                    }else{
                        getItem();
                    }
                }
            });

        }

    }

    private void getItem(){
        JFrame frame = new JFrame("Select Second Item for two way Trade");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        JTextArea jtz = new JTextArea();
        jtz.setText("item in your lending list: " + tp.selectSecondItem() + "\n" +
                "suggested items: " + trc.getSuggestedItemName() + "\n" );
        JTextArea ta = new JTextArea("type item name here");
        JButton twowayTemp = new JButton("Two way-Temporary");
        JButton twowayPer = new JButton("Two way-Permanent");
        JPanel panel = new JPanel();
        panel.add(ta);
        panel.add(twowayTemp);
        panel.add(twowayPer);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, jtz);
        frame.setVisible(true);

        twowayTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = ta.getText();
                ta.setText("type item name here");
                if (!currUser.getWishLend().contains(itemName)){
                    jtz.setText("wrong input");
                }
                else{
                    Item it = trc.getItem(itemName);
                    if (it.getIsInTrade()){
                        jtz.setText("the item is already in the trade");
                    }else{
                        trc.createTrade("3", item, it);
                        jtz.setText("the trade(two way-temporary) has been created, please wait for another user to confirm");
                    }
                }
            }
        });

        twowayPer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = ta.getText();
                ta.setText("type item name here");
                if (!currUser.getWishLend().contains(itemName)){
                    jtz.setText("wrong input");
                }
                else{
                    Item it = trc.getItem(itemName);
                    if (it.getIsInTrade()){
                        jtz.setText("the item is already in the trade");
                    }else{
                        trc.createTrade("4", item, it);
                        jtz.setText("the trade(two way-permanent) has been created, please wait for another user to confirm");
                    }
                }
            }
        });

    }



}
