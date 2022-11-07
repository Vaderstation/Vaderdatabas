package Java;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.*; 

public class Interface {


    public Interface(Map map) {
        SwingUtilities.invokeLater(() -> createWindow(map));
    }

    JButton b1, b2, b3;


    public void createWindow(Map map){

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(map.entrySet());

        SortedListModel listModel = new SortedListModel(wordList);
        JList<SortedListModel> myList = new JList<SortedListModel>(listModel);




        JFrame frame = new JFrame("VÄDERSTATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        frame.setPreferredSize(new Dimension(700, 600));

        JPanel panel = new JPanel();
        JButton Exit = new JButton("Exit");
        Exit.setPreferredSize(new Dimension(50, 50));

        pane.add(panel, BorderLayout.SOUTH);



        b1 = new JButton("ESP_1");
        b1.setPreferredSize(new Dimension(200, 100));
        b1.setSelected(true);

        b2 = new JButton("ESP_2");
        b2.setPreferredSize(new Dimension(200, 100));

        b3 = new JButton("ESP_3");
        b3.setPreferredSize(new Dimension(200, 100));

        ButtonGroup group = new ButtonGroup();
        group.add(b1);
        group.add(b2);
        group.add(b3);
    
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(Exit);

        Exit.addActionListener((n) -> { System.exit(0); });

        frame.pack();
        frame.setVisible(true);
        
    }




}
