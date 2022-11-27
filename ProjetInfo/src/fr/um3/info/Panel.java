package fr.um3.info;

import fr.um3.info.utils.FermeUtils;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel implements Runnable, MouseListener {
    private static final long serialVersionUID = 1L;
    private static final int LARGEUR=700;
    private static final int LONGUEUR=700;
    private static final int TAILLE_BLOC=20;
    public static final int NUMBER_COLS_ROWS=35;

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

    private static final int INITIAL_FERMIER_POSITION_X = 640;
    private static final int INITIAL_FERMIER_POSITION_Y = 640;


    int FPS=60;


    private Thread simulationThread;

    /**Un commentaire*/
    private Ferme ferme;
    List<String> map;
    List<String> decor;

    BufferedImage [][] tuilesMap;
    BufferedImage [][] tuilesMap2;
    BufferedImage [][] tuilesDecor;

    //Fermier

    Fermier fermier;

    Entite [][] entites;

    public Panel(){

        this.addMouseListener(this);

        tuilesMap=FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_MAP,LONGUEUR_JEU_DE_TUILE_MAP,LARGEUR_JEU_DE_TUILE_MAP,
                TAILLE_IMAGE,ESPACEMENT_JEU_DE_TUILE_MAP);

        tuilesMap2=FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_MAP2,LONGUEUR_JEU_DE_TUILE_MAP2,LARGEUR_JEU_DE_TUILE_MAP2,
                TAILLE_IMAGE,ESPACEMENT_JEU_DE_TUILE_MAP2);

        tuilesDecor=FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_DECOR,LONGUEUR_JEU_DE_TUILE_DECOR,LARGEUR_JEU_DE_TUILE_DECOR,
                TAILLE_IMAGE,ESPACEMENT_JEU_DE_TUILE_DECOR);

         map=new ArrayList<>();
         map= FermeUtils.generateMapFromTextFile("Map.txt");
         decor= FermeUtils.generateMapFromTextFile("decor.txt");
         entites=new Entite[35][35];

         fermier=new Fermier(INITIAL_FERMIER_POSITION_X,INITIAL_FERMIER_POSITION_Y,25,tuilesDecor[0][15],true);



    }



    public  void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0,0,LARGEUR,LONGUEUR );

        generateMapOrDecor(g2,map);

        generateMapOrDecor(g2,decor);
        fermier.dessiner(g2,this);






    }
    public void startSimulation(){
        simulationThread=new Thread(this);
        simulationThread.start();
    }


    @Override
    public void run() {
        double drawInterval=1000000000/FPS;
        double delta=0;
        double lastTime=System.nanoTime();
        double currentTime;


        while (simulationThread!=null){
            currentTime=System.nanoTime();

            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>1){
                repaint();
                update();
                delta--;


            }


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
                        Entite land=new Tuile(position_X,position_Y,20,tuilesMap2[0][0],true);
                        land.dessiner(g2,this);
                        break;

                    case '8':

                        Entite p1=new Tuile(position_X,position_Y,20,tuilesMap[3][21],true);
                        p1.dessiner(g2,this);


                        break;

                    case '7':
                        Entite grass=new Tuile(position_X,position_Y,20,tuilesMap[2][1],true);
                        grass.dessiner(g2,this);
                        break;

                    case '6':

                        Entite wood=new Tuile(position_X,position_Y,20,tuilesMap[3][8],true);
                        wood.dessiner(g2,this);
                        break;

                    case '5':
                        Entite water=new Tuile(position_X,position_Y,20,tuilesMap[21][5],true);
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
                        Entite flower=new Tuile(position_X,position_Y,25,tuilesMap[9][1],true);
                        flower.dessiner(g2,this);


                        break;

                    case 'x':
                        Entite poisson=new Tuile(position_X,position_Y,20,tuilesDecor[0][5],true);
                        poisson.dessiner(g2,this);

                        break;
                    case 't':
                        Entite arbre=new Tuile(position_X,position_Y,40,tuilesDecor[0][0],true);
                        arbre.dessiner(g2,this);

                        break;
                    case 'i':
                        Entite arbre1=new Tuile(position_X,position_Y,20,tuilesMap2[1][1],true);
                        arbre1.dessiner(g2,this);

                        break;
                    case 'o':
                        Entite arbre2=new Tuile(position_X,position_Y,20,tuilesMap2[1][2],true);
                        arbre2.dessiner(g2,this);

                        break;
                    case 'k':
                        Entite arbre3=new Tuile(position_X,position_Y,20,tuilesMap2[2][1],true);
                        arbre3.dessiner(g2,this);

                        break;
                    case 'l':
                        Entite arbre4=new Tuile(position_X,position_Y,20,tuilesMap2[2][2],true);
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

    public void update(){}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        System.out.println("pressed mouse");
        fermier.setPositionCourantX(fermier.getPositionCourantX()-fermier.getVitesseX());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
