package fr.um3.info;

import javax.swing.*;
import java.awt.event.*;

public class InterfaceGraphique extends JFrame {

    public InterfaceGraphique() {

        super("titre de l'application");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };

        addWindowListener(l);

        ImageIcon img = new ImageIcon("vache.gif");
        JButton bouton = new JButton("vache",img);

        JPanel panneau = new JPanel();
        panneau.add(bouton);
        setContentPane(panneau);
        setSize(200,100);
        setVisible(true);
    }

    public static void main(String [] args){
        JFrame frame = new InterfaceGraphique();
    }
}
