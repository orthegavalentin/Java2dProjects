package fr.um3.info;

import fr.um3.info.utils.FermeUtils;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel implements Runnable{
    private static final long serialVersionUID = 1L;
    private static final int LARGEUR=700;
    private static final int LONGUEUR=700;
    private static final int TAILLE_BLOC=20;

    private static final int TAILLE_IMAGE=32;
    private static final String CHEMIN_IMAGE_TUILES_MAP="map_spritesheet.png";
    private static final int LONGUEUR_JEU_DE_TUILE_MAP = 958;
    private static final int LARGEUR_JEU_DE_TUILE_MAP = 760;
    private static final int ESPACEMENT_JEU_DE_TUILE_MAP = 1;

    private static final String CHEMIN_IMAGE_TUILES_DECOR="decor_spritesheet.png";
    private static final int LONGUEUR_JEU_DE_TUILE_DECOR = 32;
    private static final int LARGEUR_JEU_DE_TUILE_DECOR =864 ;
    private static final int ESPACEMENT_JEU_DE_TUILE_DECOR = 0;

    private static final String CHEMIN_IMAGE_TUILES_MAP2="land_spritesheet.png";
    private static final int LONGUEUR_JEU_DE_TUILE_MAP2 = 224;
    private static final int LARGEUR_JEU_DE_TUILE_MAP2 =320 ;
    private static final int ESPACEMENT_JEU_DE_TUILE_MAP2 = 0;



    private Thread simulationThread;

    /**Un commentaire*/
    private Ferme ferme;
    List<String> map;
    List<String> decor;

    BufferedImage [][] tuilesMap;
    BufferedImage [][] tuilesMap2;
    BufferedImage [][] tuilesDecor;

    public Panel(){

        tuilesMap=FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_MAP,LONGUEUR_JEU_DE_TUILE_MAP,LARGEUR_JEU_DE_TUILE_MAP,
                TAILLE_IMAGE,ESPACEMENT_JEU_DE_TUILE_MAP);

        tuilesMap2=FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_MAP2,LONGUEUR_JEU_DE_TUILE_MAP2,LARGEUR_JEU_DE_TUILE_MAP2,
                TAILLE_IMAGE,ESPACEMENT_JEU_DE_TUILE_MAP2);

        tuilesDecor=FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_DECOR,LONGUEUR_JEU_DE_TUILE_DECOR,LARGEUR_JEU_DE_TUILE_DECOR,
                TAILLE_IMAGE,ESPACEMENT_JEU_DE_TUILE_DECOR);

         map=new ArrayList<>();
         map= FermeUtils.generateMapFromTextFile("Map.txt");
         decor= FermeUtils.generateMapFromTextFile("decor.txt");



    }



    public  void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0,0,LARGEUR,LONGUEUR );

        generateMapOrDecor(g2,map);

        generateMapOrDecor(g2,decor);






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
    // Dessiner notre monde en 2D
    private void generateMapOrDecor(Graphics2D g2,List<String> map){
        int position_X=0;
        int position_Y=0;

        for(String ligne:map){

            for(int i=0;i<ligne.length();i++){

                switch(ligne.charAt(i)){

                    case '9':
                        Entite land=new Tuile(position_X,position_Y,20,tuilesMap2[0][0]);
                        land.dessiner(g2,this);
                        break;

                    case '8':

                        Entite p1=new Tuile(position_X,position_Y,20,tuilesMap[3][21]);
                        p1.dessiner(g2,this);


                        break;

                    case '7':
                        Entite grass=new Tuile(position_X,position_Y,20,tuilesMap[2][1]);
                        grass.dessiner(g2,this);
                        break;

                    case '6':

                        Entite wood=new Tuile(position_X,position_Y,20,tuilesMap[3][8]);
                        wood.dessiner(g2,this);
                        break;

                    case '5':
                        Entite water=new Tuile(position_X,position_Y,20,tuilesMap[21][5]);
                        water.dessiner(g2,this);

                        break;

                    case '4':
                        g2.setColor(Color.ORANGE);
                        g2.fillRect(position_X,position_Y,TAILLE_BLOC,TAILLE_BLOC );

                        break;
                    case '3':
                        g2.setColor(Color.BLUE);
                        g2.fillRect(position_X,position_Y,TAILLE_BLOC,TAILLE_BLOC );

                        break;
                    case '2':
                        g2.setColor(Color.RED);
                        g2.fillRect(position_X,position_Y,TAILLE_BLOC,TAILLE_BLOC );

                        break;
                    case '1':
                        g2.setColor(Color.PINK);
                        g2.fillRect(position_X,position_Y,TAILLE_BLOC,TAILLE_BLOC );

                        break;


                    case 'f':
                        Entite flower=new Tuile(position_X,position_Y,25,tuilesMap[9][1]);
                        flower.dessiner(g2,this);


                        break;

                    case 'x':
                        Entite poisson=new Tuile(position_X,position_Y,20,tuilesDecor[0][5]);
                        poisson.dessiner(g2,this);

                        break;
                    case 't':
                        Entite arbre=new Tuile(position_X,position_Y,40,tuilesDecor[0][0]);
                        arbre.dessiner(g2,this);

                        break;
                    case 'i':
                        Entite arbre1=new Tuile(position_X,position_Y,20,tuilesMap2[1][1]);
                        arbre1.dessiner(g2,this);

                        break;
                    case 'o':
                        Entite arbre2=new Tuile(position_X,position_Y,20,tuilesMap2[1][2]);
                        arbre2.dessiner(g2,this);

                        break;
                    case 'k':
                        Entite arbre3=new Tuile(position_X,position_Y,20,tuilesMap2[2][1]);
                        arbre3.dessiner(g2,this);

                        break;
                    case 'l':
                        Entite arbre4=new Tuile(position_X,position_Y,20,tuilesMap2[2][2]);
                        arbre4.dessiner(g2,this);

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
