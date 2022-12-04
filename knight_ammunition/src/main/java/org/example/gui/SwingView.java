/*
 * Created by JFormDesigner on Fri Dec 02 01:18:46 EET 2022
 */

package org.example.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.example.controller.AmmunitionController;
import org.example.entity.AmmunitionFactory;
import org.example.entity.AmmunitionFactoryImpl;

/**
 * @author Artem
 */
public class SwingView extends JPanel {
    AmmunitionController ammunitionController;
    public SwingView(AmmunitionController ammunitionController) {
        this.ammunitionController = ammunitionController;
        initComponents();
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea1));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private void show_all(ActionEvent e) {
        // TODO add your code here
        ammunitionController.findAll();
    }

    private void show_by_id(ActionEvent e) {
        // TODO add your code here
        String id = JOptionPane.showInputDialog("Enter id to be shown");
        ammunitionController.findEntityById(Integer.parseInt(id));
    }

    private void delete_by_id(ActionEvent e) {
        // TODO add your code here
        String id = JOptionPane.showInputDialog("Enter id to be shown");
        try {
            ammunitionController.delete(Integer.parseInt( id));
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect input");
            throw new RuntimeException(ex);
        }
    }

    private void craft(ActionEvent e) {
        // TODO add your code here
        AmmunitionFactory ammunitionFactory = new AmmunitionFactoryImpl();
        try {
            ammunitionController.create(ammunitionFactory.createRandomAmmunition());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void print_total_cost(ActionEvent e) {
        // TODO add your code here
        double cost = ammunitionController.computeTotalEquippedAmmunitionCost();
        JOptionPane.showMessageDialog(this, "Total cost is " + Math.floor(cost * 100) / 100 + '!');
    }

    private void print_total_in_cost_range(ActionEvent e) {
        // TODO add your code here
        String s = JOptionPane.showInputDialog(new JOptionPane(),"Enter lower and upper cost bounds (num num)" ,"10 30");
        String[] split = new String[0];
        try {
            split = s.split(" ");
        } catch (Exception ex) {
            System.out.println("Incorrect input");
            throw new RuntimeException(ex);
        }
        ammunitionController.getAmmunitionListInCostRange(Integer.parseInt( split[0]),Integer.parseInt( split[1]));
    }

    private void print_sorted_by_weight(ActionEvent e) {
        // TODO add your code here
        ammunitionController.getAmmunitionListSortedByWeight();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        show_all = new JButton();
        show_by_id = new JButton();
        delete_by_id = new JButton();
        craft = new JButton();
        print_total_cost = new JButton();
        print_total_in_cost_range = new JButton();
        print_sorted_by_weight = new JButton();

        //======== this ========
        setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        setMinimumSize(new Dimension(425, 500));
        setPreferredSize(new Dimension(795, 500));
        setRequestFocusEnabled(false);
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
        . EmptyBorder( 0, 0, 0, 0) , "", javax. swing. border. TitledBorder. CENTER, javax
        . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,
        12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans
        . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .
        getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new MigLayout(
            "hidemode 3,align center center,gap 10 10",
            // columns
            "[grow,fill]",
            // rows
            "[18]" +
            "[]"));

        //======== scrollPane1 ========
        {

            //---- textArea1 ----
            textArea1.setWrapStyleWord(true);
            scrollPane1.setViewportView(textArea1);
        }
        add(scrollPane1, "pad 0,cell 0 0,dock center,wmin 200,hmin 200");

        //---- show_all ----
        show_all.setText("Show All");
        show_all.addActionListener(e -> {
            textArea1.setText("");
			show_all(e);
		});
        add(show_all, "cell 0 1,height 40:40");

        //---- show_by_id ----
        show_by_id.setText("Show by ID");
        show_by_id.addActionListener(e -> {
            textArea1.setText("");
            show_by_id(e);
        });
        add(show_by_id, "cell 0 1,height 40:40");

        //---- delete_by_id ----
        delete_by_id.setText("Delete by ID");
        delete_by_id.addActionListener(e -> {
            textArea1.setText("");
            delete_by_id(e);
        });
        add(delete_by_id, "cell 0 2,height 40:40");

        //---- craft ----
        craft.setText("Craft");
        craft.addActionListener(e -> {
            textArea1.setText("");
            craft(e);
        });
        add(craft, "cell 0 2,height 40:40");

        //---- print_total_cost ----
        print_total_cost.setText("Print Total Cost");
        print_total_cost.addActionListener(e -> {
            textArea1.setText("");
            print_total_cost(e);
        });
        add(print_total_cost, "cell 0 2,height 40:40");

        //---- print_total_in_cost_range ----
        print_total_in_cost_range.setText("Print Total In Cost Range");
        print_total_in_cost_range.addActionListener(e -> {
            textArea1.setText("");
            print_total_in_cost_range(e);
        });
        add(print_total_in_cost_range, "cell 0 3,height 40:40");

        //---- print_sorted_by_weight ----
        print_sorted_by_weight.setText("Print Sorted by Weight");
        print_sorted_by_weight.addActionListener(e -> {
            textArea1.setText("");
            print_sorted_by_weight(e);
        });
        add(print_sorted_by_weight, "cell 0 3,height 40:40");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton show_all;
    private JButton show_by_id;
    private JButton delete_by_id;
    private JButton craft;
    private JButton print_total_cost;
    private JButton print_total_in_cost_range;
    private JButton print_sorted_by_weight;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(new String(new byte[] { (byte) b }));
//        // redirects data to the text area
//        textArea.append(String.valueOf((char)b));
//        // scrolls the text area to the end of data
//        textArea.setCaretPosition(textArea.getDocument().getLength());
//        // keeps the textArea up to date
//        textArea.update(textArea.getGraphics());
    }
}