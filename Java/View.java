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

    JButton b1, b2, b3, b4;

    private static JLabel mT;
    private static JLabel mH;
    private static JLabel mG;

    private static int clickedId;

    private static SortedListModel<Map.Entry<String, Integer>> listModel;
    private static JScrollPane scrollpane;

    public View(Map map){
        SwingUtilities.invokeLater(() -> createWindow(map));
    }

    public void createWindow(Map<String, Integer> map){
        Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(1, 1));
        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
        listModel = new SortedListModel<Map.Entry<String, Integer>>(wordList);
        listModel.sort((s1, s2) -> s2.getKey().compareTo(s1.getKey()));
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

        panel1.setBackground(Color.GRAY);
        panel1.setLayout(new FlowLayout());
        panel2.setBackground(Color.lightGray); //knappar
        panel3.setBackground(Color.white); //Scrollpane
        panel3.setLayout(new FlowLayout());
        panel4.setBackground(Color.GRAY);
        panel5.setBackground(Color.white); //Measure

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

        b4 = new JButton("Update");
        b4.setPreferredSize(new Dimension(220, 100));
        
        
        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b3);
        panel2.add(b4);

        //Rubrik
        JLabel t1 = new JLabel("V??derstation_v1");

        t1.setPreferredSize(new Dimension(400,120));
        t1.setFont(new Font("Verdana", Font.PLAIN, 32));
        panel1.add(t1, BorderLayout.CENTER);

        //esp measurements setup and label for panel 3
                 mT = new JLabel("Null");
                 mH = new JLabel("Null");
                 mG = new JLabel("Null");
                 JLabel lV = new JLabel("Listed Values: ");

                mT.setPreferredSize(new Dimension(420,100));
                mH.setPreferredSize(new Dimension(420,100));
                mG.setPreferredSize(new Dimension(420,100));
                lV.setPreferredSize(new Dimension(420,100));

                mT.setFont(new Font("Verdana", Font.PLAIN, 18));
                mH.setFont(new Font("Verdana", Font.PLAIN, 18));
                mG.setFont(new Font("Verdana", Font.PLAIN, 18));
                lV.setFont(new Font("Verdana", Font.PLAIN, 24));


                panel5.add(mT);
                panel5.add(mH);
                panel5.add(mG);
                panel3.add(lV);

        //Live values setup

        View.scrollpane.setPreferredSize(new Dimension(420,300));
        View.scrollpane.setMinimumSize(new Dimension(420,300));
        View.scrollpane.setMaximumSize(new Dimension(420,300));
        panel3.add(View.scrollpane);
        

        //checkIfOk

        Measure m = new Measure(); 
        //update

        //esp1
        b1.addActionListener((ActionListener) new ActionListener(){ //S??TT IN V??RDEN FR??N DATABAS. 
            @Override
            public void actionPerformed(ActionEvent e) {
                mT.setText("Mean Temprature: " + Main.db.resultSetToDouble(Main.db.getMeanTemp(1)) + " ??C");
                mH.setText("Mean Humidity: " + Main.db.resultSetToDouble(Main.db.getMeanHumidity(1)) + " %");
                mG.setText("Mean Gas: " + Main.db.resultSetToDouble(Main.db.getMeanGas(1)) + " ppm CO2");

                if(m.checkTemp(Main.db.resultSetToDouble(Main.db.getMeanTemp(1))) == 1){
                    mT.setForeground(Color.RED);
                }else{
                    mT.setForeground(Color.black);
                }

                if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanHumidity(1))) == 1){
                    mH.setForeground(Color.RED);
                }else{
                    mH.setForeground(Color.black);
                }

                if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanGas(1))) == 1){
                    mG.setForeground(Color.RED);
                }else{
                    mG.setForeground(Color.black);
                }



                Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(1, 1));
                List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
                View.listModel.updateAA(wordList);
                View.listModel.sort((s1, s2) -> s2.getKey().compareTo(s1.getKey()));
                panel3.remove(View.scrollpane);
                panel3.updateUI();
                View.scrollpane.revalidate();
                View.scrollpane.repaint();
                panel3.add(View.scrollpane);
                panel3.updateUI();

                b1.setSelected(true);
                b1.setText("ESP_1 (SELECTED)");
    
                b2.setSelected(false);
                b2.setText("ESP_2");
                
                b3.setSelected(false);  
                b3.setText("ESP_3");

                View.clickedId = 1; 
                
            }
        });

        //esp 2
        b2.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mT.setText("Mean Temprature: " + Main.db.resultSetToDouble(Main.db.getMeanTemp(2)) + " ??C");
                mH.setText("Mean Humidity: " + Main.db.resultSetToDouble(Main.db.getMeanHumidity(2)) + " %");
                mG.setText("Mean Gas: " + Main.db.resultSetToDouble(Main.db.getMeanGas(2)) + " ppm CO2");

                if(m.checkTemp(Main.db.resultSetToDouble(Main.db.getMeanTemp(2))) == 1){
                    mT.setForeground(Color.RED);
                }else{
                    mT.setForeground(Color.black);
                }

                if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanHumidity(2))) == 1){
                    mH.setForeground(Color.RED);
                }else{
                    mH.setForeground(Color.black);
                }

                if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanGas(2))) == 1){
                    mG.setForeground(Color.RED);
                }else{
                    mG.setForeground(Color.black);
                }

                Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(2, 2));
                List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
                View.listModel.updateAA(wordList);
                View.listModel.sort((s1, s2) -> s2.getKey().compareTo(s1.getKey()));
                panel3.remove(View.scrollpane);
                panel3.updateUI();
                View.scrollpane.revalidate();
                View.scrollpane.repaint();
                panel3.add(View.scrollpane);
                panel3.updateUI();

                b2.setSelected(true);
                b2.setText("ESP_2 (SELECTED)");
    
                b1.setSelected(false);
                b1.setText("ESP_1");
                
                b3.setSelected(false);  
                b3.setText("ESP_3");
                
                View.clickedId = 2;
            }
        });
        //esp 3
        b3.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mT.setText("Mean Temprature: " + Main.db.resultSetToDouble(Main.db.getMeanTemp(3)) + " ??C");
                mH.setText("Mean Humidity: " + Main.db.resultSetToDouble(Main.db.getMeanHumidity(3)) + " %");
                mG.setText("Mean Gas: " + Main.db.resultSetToDouble(Main.db.getMeanGas(3)) + " ppm CO2");

                if(m.checkTemp(Main.db.resultSetToDouble(Main.db.getMeanTemp(3))) == 1){
                    mT.setForeground(Color.RED);
                }else{
                    mT.setForeground(Color.black);
                }

                if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanHumidity(3))) == 1){
                    mH.setForeground(Color.RED);
                }else{
                    mH.setForeground(Color.black);
                }

                if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanGas(3))) == 1){
                    mG.setForeground(Color.RED);
                }else{
                    mG.setForeground(Color.black);
                }
                
                Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(3, 3));
                List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
                View.listModel.updateAA(wordList);
                View.listModel.sort((s1, s2) -> s2.getKey().compareTo(s1.getKey()));
                panel3.remove(View.scrollpane);
                panel3.updateUI();
                View.scrollpane.revalidate();
                View.scrollpane.repaint();
                panel3.add(View.scrollpane);
                panel3.updateUI();

                b3.setSelected(true);
                b3.setText("ESP_3 (SELECTED)");
    
                b2.setSelected(false);
                b2.setText("ESP_2");
                
                b1.setSelected(false);  
                b1.setText("ESP_1");

                View.clickedId = 3;
            }
        });

        b4.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                if(View.clickedId == 1){
                    System.out.println("Uppdaterar ESP_1");
                    mT.setText("Mean Temprature: " + Main.db.resultSetToDouble(Main.db.getMeanTemp(1)) + " ??C");
                    mH.setText("Mean Humidity: " + Main.db.resultSetToDouble(Main.db.getMeanHumidity(1)) + " %");
                    mG.setText("Mean Gas: " + Main.db.resultSetToDouble(Main.db.getMeanGas(1)) + " ppm CO2");

                        if(m.checkTemp(Main.db.resultSetToDouble(Main.db.getMeanTemp(1))) == 1){
                            mT.setForeground(Color.RED);
                        }else{
                            mT.setForeground(Color.black);
                        }
        
                        if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanHumidity(1))) == 1){
                            mH.setForeground(Color.RED);
                        }else{
                            mH.setForeground(Color.black);
                        }
        
                        if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanGas(1))) == 1){
                            mG.setForeground(Color.RED);
                        }else{
                            mG.setForeground(Color.black);
                        }

                        Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(1, 1));
                        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
                        View.listModel.updateAA(wordList);
                        View.listModel.sort((s1, s2) -> s2.getKey().compareTo(s1.getKey()));
                        panel3.remove(View.scrollpane);
                        panel3.updateUI();
                        View.scrollpane.revalidate();
                        View.scrollpane.repaint();
                        panel3.add(View.scrollpane);
                        panel3.updateUI();
                }
            
                if(View.clickedId == 2){
                    System.out.println("Uppdaterar ESP_2");
                    mT.setText("Mean Temprature: " + Main.db.resultSetToDouble(Main.db.getMeanTemp(2)) + " ??C");
                    mH.setText("Mean Humidity: " + Main.db.resultSetToDouble(Main.db.getMeanHumidity(2)) + " %");
                    mG.setText("Mean Gas: " + Main.db.resultSetToDouble(Main.db.getMeanGas(2)) + " ppm CO2");

                        if(m.checkTemp(Main.db.resultSetToDouble(Main.db.getMeanTemp(2))) == 1){
                            mT.setForeground(Color.RED);
                        }else{
                            mT.setForeground(Color.black);
                        }
        
                        if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanHumidity(2))) == 1){
                            mH.setForeground(Color.RED);
                        }else{
                            mH.setForeground(Color.black);
                        }
        
                        if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanGas(2))) == 1){
                            mG.setForeground(Color.RED);
                        }else{
                            mG.setForeground(Color.black);
                        }
    
                        Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(2, 2));
                        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
                        View.listModel.updateAA(wordList);
                        View.listModel.sort((s1, s2) -> s2.getKey().compareTo(s1.getKey()));
                        panel3.remove(View.scrollpane);
                        panel3.updateUI();
                        View.scrollpane.revalidate();
                        View.scrollpane.repaint();
                        panel3.add(View.scrollpane);
                        panel3.updateUI();
                        
                }

                if(View.clickedId == 3){
                    System.out.println("Uppdaterar ESP_3");
                    mT.setText("Mean Temprature: " + Main.db.resultSetToDouble(Main.db.getMeanTemp(3)) + " ??C");
                    mH.setText("Mean Humidity: " + Main.db.resultSetToDouble(Main.db.getMeanHumidity(3)) + " %");
                    mG.setText("Mean Gas: " + Main.db.resultSetToDouble(Main.db.getMeanGas(3)) + " ppm CO2");

                        if(m.checkTemp(Main.db.resultSetToDouble(Main.db.getMeanTemp(3))) == 1){
                            mT.setForeground(Color.RED);
                        }else{
                            mT.setForeground(Color.black);
                        }
        
                        if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanHumidity(3))) == 1){
                            mH.setForeground(Color.RED);
                        }else{
                            mH.setForeground(Color.black);
                        }
        
                        if(m.checkHumidity(Main.db.resultSetToDouble(Main.db.getMeanGas(3))) == 1){
                            mG.setForeground(Color.RED);
                        }else{
                            mG.setForeground(Color.black);
                        }
                
                        Map<String, Integer> dataSet = Main.db.resultSetToMap(Main.db.getMeasureValue(3, 3));
                        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(dataSet.entrySet());
                        View.listModel.updateAA(wordList);
                        View.listModel.sort((s1, s2) -> s2.getKey().compareTo(s1.getKey()));
                        panel3.remove(View.scrollpane);
                        panel3.updateUI();
                        View.scrollpane.revalidate();
                        View.scrollpane.repaint();
                        panel3.add(View.scrollpane);
                        panel3.updateUI();
                }

                
                
            }
        });

    }
}