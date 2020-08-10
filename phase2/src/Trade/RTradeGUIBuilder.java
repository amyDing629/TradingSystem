package Trade;

import Inventory.Inventory;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.UUID;

public class RTradeGUIBuilder implements BorderGUIBuilder {


    private final RTradeController trc;
    private final BorderGUIWithThreeTextArea tg;
    private final JFrame fr;

    /**
     * [constructor]
     *
     * @param currUser current user
     * @param item     item selected by the current user
     */
    public RTradeGUIBuilder(UUID currUser, String item, JFrame fr) {


        tg = new BorderGUIWithThreeTextArea();
        trc = new RTradeController(currUser, tg, item);
        trc.getTarUser(item);
        this.fr = fr;
    }

    @Override
    public void buildFrame() {
        tg.setFrame(800, 200, "Request Trade Session");
    }

    @Override
    public void buildPanelN() {
        JPanel msg = new JPanel();
        JLabel msgl = new JLabel("message:", SwingConstants.LEFT);
        msg.add(msgl);
        JTextArea jtz = new JTextArea();
        msg.add(jtz);
        tg.setNorth(msg);
        tg.initializeMsg(jtz);
    }

    @Override
    public void buildPanelE() {
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("input second item name here " +
                "for a two way trade", SwingConstants.LEFT);
        JTextArea ta = new JTextArea("type item name here");
        panelRight.add(label);
        panelRight.add(ta);
        tg.setEast(panelRight);
        tg.addInput("input", ta);
    }

    @Override
    public void buildPanelW() {
    }

    @Override
    public void buildPanelS() {
        JPanel panelS =  new JPanel();
        JButton onewayTemp = new JButton("One way-Temporary");
        JButton onewayPer = new JButton("One way-Permanent");
        JButton twowayTemp = new JButton("Two way-Temporary");
        JButton twowayPer = new JButton("Two way-Permanent");
        JButton back = new JButton("return");
        panelS.add(onewayTemp);
        panelS.add(onewayPer);
        panelS.add(twowayTemp);
        panelS.add(twowayPer);
        panelS.add(back);
        tg.setSouth(panelS);
        onewayTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    trc.onewayBut("temp");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        onewayPer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    trc.onewayBut("per");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        twowayTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    trc.twowayBut("temp");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        twowayPer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    trc.twowayBut("per");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trc.backBut(fr);
            }
        });




    }

    @Override
    public void buildPanelC() {
        JTextArea tradeInfo = new JTextArea();
        JPanel panel = new JPanel();
        panel.add(tradeInfo);
        tradeInfo.setPreferredSize(new Dimension(400, 170));
        tg.setCenter(panel);
        tg.initializeCurr(tradeInfo);
        trc.presentTradeInfo();
    }

    @Override
    public BorderGUINoTextArea getTradeGUI() {
        return tg;
    }
}
