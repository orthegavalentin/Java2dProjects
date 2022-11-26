package fr.um3.info;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    private static final int LARGEUR=720;
    private static final int LONGUEUR=740;

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
//git add pour metrre dans le staging area
// git commit pour mettre dans la commit area
// git status pour connaitre l'etat
// git branch pour connaitre sur quelle branche on est
// git checkout pour changer de branche
// git pull recuperer les changements
