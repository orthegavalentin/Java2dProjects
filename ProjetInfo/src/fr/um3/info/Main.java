package fr.um3.info;

import fr.um3.info.utils.FermeUtils;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main  {

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
        FermeUtils.initSound("animalsSound.wav");
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("p")){
                    FermeUtils.playSound();
                }

                if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("q")){
                    FermeUtils.stopSound();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


    }



}
//git add pour metrre dans le staging area
// git commit pour mettre dans la commit area
// git status pour connaitre l'etat
// git branch pour connaitre sur quelle branche on est
// git checkout pour changer de branche
// git pull recuperer les changements
// git merge recuperer les changements d'un ô branche
// git log : historique des commits
// coding game et algo expert pour les exos en progra
// synchronize dans threads
//moquito ou JUnit
