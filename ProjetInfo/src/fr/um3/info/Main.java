package fr.um3.info;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    private static final int LARGEUR=800;
    private static final int LONGUEUR=800;

    public static void main(String [] args){
        JFrame frame = new JFrame();
        Panel panel= new Panel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(LARGEUR,LONGUEUR);
        frame.setLocationRelativeTo(null);
        frame.setTitle("La ferme!");
        frame.setContentPane(panel);
        frame.setVisible(true);
        panel.startSimulation();
    }

}
