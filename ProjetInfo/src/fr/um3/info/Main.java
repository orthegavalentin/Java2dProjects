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
// git branch permet de savoir sur quelle branche on est
// gitlog permet de créer des init
// git statut pour savoir l'etat actuel de ton projet
// git add ajouter dans le staging area
// git add> git commit -m "message"> git push leNomDuRepertoire