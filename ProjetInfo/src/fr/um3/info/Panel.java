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
    private static final String CHEMIN_IMAGE_FERMIER="guy1.png";
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

    public Panel(){
        List<Personnage> personnageList=new ArrayList<>();
        List<Secteur> secteurList=new ArrayList<>();

        Rectangle2D location1= new Rectangle2D.Double();
        location1.setRect(SECTEUR_A_POSITION_X,SECTEUR_A_POSITION_Y,LARGEUR_SEC,HAUTEUR_SEC);
        Secteur secteur=new Secteur(location1,Color.CYAN,SecteurEnum.EQUITATION);
        Rectangle2D location2= new Rectangle2D.Double();
        location2.setRect(SECTEUR_B_POSITION_X,SECTEUR_B_POSITION_Y,LARGEUR_SEC,HAUTEUR_SEC);
        Secteur secteur2=new Secteur(location2,Color.PINK,SecteurEnum.POULAILLER);
        Rectangle2D location3= new Rectangle2D.Double();
        location3.setRect(SECTEUR_C_POSITION_X,SECTEUR_C_POSITION_Y,LARGEUR_SEC,HAUTEUR_SEC);
        Secteur secteur3=new Secteur(location3,Color.YELLOW,SecteurEnum.ETABLE);
        secteurList.add(secteur);
        secteurList.add(secteur2);
        secteurList.add(secteur3);

       Personnage alvinFermier= new Fermier(20,20,Color.GREEN,40,CHEMIN_IMAGE_FERMIER,
               1,1,TypePersonnageEnum.FERMIER,secteur);
        Personnage alvinFermier2= new Fermier(20,20,Color.GREEN,40,CHEMIN_IMAGE_FERMIER_2,
                1,1,TypePersonnageEnum.FERMIER,secteur);
        Personnage alvinFermier3= new Fermier(20,20,Color.GREEN,40,CHEMIN_IMAGE_FERMIER_2,
                1,1,TypePersonnageEnum.FERMIER,secteur2);
        Personnage alvinFermier4= new Fermier(20,20,Color.GREEN,40,CHEMIN_IMAGE_FERMIER_2,
                1,1,TypePersonnageEnum.FERMIER,secteur2);

        alvinFermier.setSecActivite(secteurList);
        alvinFermier2.setSecActivite(secteurList);
        alvinFermier3.setSecActivite(secteurList);
        alvinFermier4.setSecActivite(secteurList);

       personnageList.add(alvinFermier);
       //personnageList.add(alvinFermier2);
      /* personnageList.add(alvinFermier3);
       personnageList.add(alvinFermier4);*/
       this.ferme=new Ferme(personnageList,secteurList);



    }



    public  void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,LARGEUR,LONGUEUR );


        for(Secteur secteur:this.ferme.getSecteurs()){
            secteur.dessiner(g2);
        }
        for(Personnage personnage:this.ferme.getPersonnages()){
            personnage.dessiner(g2,this);
        }


    }
    public void startSimulation(){
        simulationThread=new Thread(this);
        simulationThread.start();
    }


    @Override
    public void run() {
        while (simulationThread!=null){

            for(Personnage personnage:this.ferme.getPersonnages()){
                personnage.bouger(this);
                this.repaint();
            }


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
