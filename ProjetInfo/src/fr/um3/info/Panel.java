package fr.um3.info;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel implements Runnable{
    private static final long serialVersionUID = 1L;
    private static final int TAILLEPIX= 4;
    private static final int LARGEUR=800;
    private static final int LONGUEUR=800;
    private static final int TAILLE_BLOC=20;
    private static final String CHEMIN_IMAGE_FERMIER="wooden.png";
    private static final String CHEMIN_IMAGE_FERMIER_2="guy2.png";// !!!!!regarder une video sur ca!!!!!
    private Thread simulationThread;
    private final double HAUTEUR_SEC=200;
    private final double LARGEUR_SEC= 200;
    private final double SECTEUR_A_POSITION_X=0;
    private final double SECTEUR_A_POSITION_Y=0;
    private final double SECTEUR_B_POSITION_X=600;
    private final double SECTEUR_B_POSITION_Y=400;
    private final double SECTEUR_C_POSITION_X=0;
    private final double SECTEUR_C_POSITION_Y=500;

    /**Un commentaire*/
    private Ferme ferme;
    List<String> map;

    public Panel(){
        List<Personnage> personnageList=new ArrayList<>();
        List<Secteur> secteurList=new ArrayList<>();
         map=new ArrayList<>();
         map=FermeUtils.generateMapFromTextFile("Map.txt");



    }



    public  void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,LARGEUR,LONGUEUR );

        generateMap(g2,map);




    }
    public void startSimulation(){
        simulationThread=new Thread(this);
        simulationThread.start();
    }


    @Override
    public void run() {

        while (simulationThread!=null){


        }
    }

    private void generateMap(Graphics2D g2,List<String> mapString){
        int position_X=0;
        int position_Y=0;

        for(String ligne:mapString){

            for(int i=0;i<ligne.length();i++){

                switch(ligne.charAt(i)){

                    case 'x':
                        g2.setColor(Color.BLUE);
                        g2.fillRect(position_X,position_Y,TAILLE_BLOC,TAILLE_BLOC );

                        break;

                    case 'o':
                        g2.setColor(Color.RED);
                        g2.fillRect(position_X,position_Y,TAILLE_BLOC,TAILLE_BLOC );

                        break;

                    case 'c':
                        g2.setColor(Color.GREEN);
                        g2.fillRect(position_X,position_Y,TAILLE_BLOC,TAILLE_BLOC );

                        break;



                    case 'p':
                        Personnage p1=new Fermier(position_X,position_Y,Color.black,20,CHEMIN_IMAGE_FERMIER);
                        p1.dessiner(g2,this);

                        break;


                    default:
                        break;



                }
                position_X+=TAILLE_BLOC;

            }
            position_X=0;
            position_Y+=TAILLE_BLOC;




        }

    }
}
