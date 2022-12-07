package Java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class View {

    JButton b1, b2, b3;

    private static JLabel mT;
    private static JLabel mH;
    private static JLabel mG;
    private static JScrollPane scrollpane;
    public View(Map map){
        SwingUtilities.invokeLater(() -> createWindow(map));
    }

    public void createWindow(Map<String, Integer> map){
        Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(1, 1));
        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
        SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(wordList);
        JList<Map.Entry<String, Integer>> jlist = new JList<Map.Entry<String, Integer>>(listModel);
        View.scrollpane = new JScrollPane(jlist);

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080,720);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel1.setLayout(new FlowLayout());
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.yellow);
        panel3.setLayout(new FlowLayout());
        panel4.setBackground(Color.magenta);
        panel5.setBackground(Color.blue);

        panel1.setPreferredSize(new Dimension(840,120));
        panel2.setPreferredSize(new Dimension(220,100));
        panel3.setPreferredSize(new Dimension(420,100));
        panel3.setMinimumSize(new Dimension(420,100));
        panel3.setMaximumSize(new Dimension(420,100));
        panel4.setPreferredSize(new Dimension(100,30));
        panel5.setPreferredSize(new Dimension(420,100));

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);

        //Buttons
        b1 = new JButton("ESP_1");
        b1.setPreferredSize(new Dimension(220, 100));

        b2 = new JButton("ESP_2");
        b2.setPreferredSize(new Dimension(220, 100));

        b3 = new JButton("ESP_3");
        b3.setPreferredSize(new Dimension(220, 100));

        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b3);

        //Rubrik
        JLabel t1 = new JLabel("Väderstation_v1");

        t1.setPreferredSize(new Dimension(400,120));
        t1.setFont(new Font("Verdana", Font.PLAIN, 32));
        panel1.add(t1, BorderLayout.CENTER);

        //Medelvärden
        Measure esp1 = new Measure();
        Measure esp2 = new Measure();
        Measure esp3 = new Measure();

        //esp measurements setup
                 mT = new JLabel("Null");
                 mH = new JLabel("Null");
                 mG = new JLabel("null");

                mT.setPreferredSize(new Dimension(420,100));
                mH.setPreferredSize(new Dimension(420,100));
                mG.setPreferredSize(new Dimension(420,100));

                mT.setFont(new Font("Verdana", Font.PLAIN, 18));
                mH.setFont(new Font("Verdana", Font.PLAIN, 18));
                mG.setFont(new Font("Verdana", Font.PLAIN, 18));

                panel5.add(mT);
                panel5.add(mH);
                panel5.add(mG);

        //Live values setup

        View.scrollpane.setPreferredSize(new Dimension(420,100));
        View.scrollpane.setMinimumSize(new Dimension(420,100));
        View.scrollpane.setMaximumSize(new Dimension(420,100));
        panel3.add(View.scrollpane);

        b1.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mT.setText("Mean Temprature: " + String.valueOf(esp1.getMeanTemp(34, 10)));
                mH.setText("Mean Humidity: " + String.valueOf(esp1.getMeanHumidity(90, 23)));
                mG.setText("Mean Gas: " + String.valueOf(esp1.getMeanGas(23, 2)));

                Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(2, 2));
                List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
                SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(wordList);
                JList<Map.Entry<String, Integer>> jlist = new JList<Map.Entry<String, Integer>>(listModel);
                View.scrollpane = new JScrollPane(jlist);
                View.scrollpane.revalidate();
                View.scrollpane.repaint();
                
            }
        });

        //esp 2
        b2.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mT.setText("Mean Temprature: " + String.valueOf(esp2.getMeanTemp(343, 10)));
                mH.setText("Mean Humidity: " + String.valueOf(esp2.getMeanHumidity(920, 23)));
                mG.setText("Mean Gas: " + String.valueOf(esp2.getMeanGas(43, 2)));
                
            }
        });
        //esp 3
        b3.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mT.setText("Mean Temprature: " + String.valueOf(esp3.getMeanTemp(342, 10)));
                mH.setText("Mean Humidity: " + String.valueOf(esp3.getMeanHumidity(910, 23)));
                mG.setText("Mean Gas: " + String.valueOf(esp3.getMeanGas(213, 2)));
                
            }
        });


    }
}