package fr.um3.info;

import fr.um3.info.enums.DirectionEnum;
import fr.um3.info.enums.SecteurEnum;
import fr.um3.info.utils.FermeUtils;
import fr.um3.info.utils.KeyHandler;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Panel extends JPanel implements Runnable, MouseListener {
    private static final long serialVersionUID = 1L;
    private static final int LARGEUR = 700;
    private static final int LONGUEUR = 700;
    public static final int TAILLE_BLOC = 20;
    public static final int NUMBER_COLS_ROWS = 35;
    public static final int NB_VISITEUR = 34;

    private static final int TAILLE_IMAGE = 32;
    private static final String CHEMIN_IMAGE_TUILES_MAP = "map_spritesheet.png";
    private static final int LONGUEUR_JEU_DE_TUILE_MAP = 958;
    private static final int LARGEUR_JEU_DE_TUILE_MAP = 760;
    private static final int ESPACEMENT_JEU_DE_TUILE_MAP = 1;

    private static final String CHEMIN_IMAGE_TUILES_DECOR = "decor_spritesheet.png";
    private static final int LONGUEUR_JEU_DE_TUILE_DECOR = 32;
    private static final int LARGEUR_JEU_DE_TUILE_DECOR = 608;
    private static final int ESPACEMENT_JEU_DE_TUILE_DECOR = 0;

    private static final String CHEMIN_IMAGE_TUILES_MAP2 = "land_spritesheet.png";
    private static final int LONGUEUR_JEU_DE_TUILE_MAP2 = 224;
    private static final int LARGEUR_JEU_DE_TUILE_MAP2 = 320;
    private static final int ESPACEMENT_JEU_DE_TUILE_MAP2 = 0;

    private static final String CHEMIN_IMAGE_TUILE_ENTITES_MOBILES = "entites_mobiles_spritesheet.png";
    private static final int LONGUEUR_JEU_DE_TUILE_ENTITES_MOBILES = 32;
    private static final int LARGEUR_JEU_DE_TUILE_ENTITES_MOBILES = 864;
    private static final int ESPACEMENT_JEU_DE_TUILE_ENTITES_MOBILES = 0;

    private static final int INITIAL_FERMIER_POSITION_X = 10;
    private static final int INITIAL_FERMIER_POSITION_Y = 10;

    private static final int HAUTEUR_ACCUEIL = 220;
    private static final int LARGEUR_ACCUEIL = 220;
    private static final int HAUTEUR_POULLAILLER = 160;
    private static final int LARGEUR_POULLAILLER = 220;
    private static final int HAUTEUR_EQUITATION = 160;
    private static final int LARGEUR_EQUITATION = 220;
    private static final int HAUTEUR_ETABLE = 160;
    private static final int LARGEUR_ETABLE = 220;
    private static final int HAUTEUR_PLANTATION = 160;
    private static final int LARGEUR_PLANTATION = 220;

    private static final int SECTEUR_ACCUEIL_POSITION_X = 20;
    private static final int SECTEUR_ACCUEIL_POSITION_Y = 20;
    private static final int SECTEUR_POULLAILLER_POSITION_X = 20;
    private static final int SECTEUR_POULLAILLER_POSITION_Y = 520;
    private static final int SECTEUR_EQUITATION_POSITION_X = 20;
    private static final int SECTEUR_EQUITATION_POSITION_Y = 520;
    private static final int SECTEUR_ETABLE_POSITION_X = 20;
    private static final int SECTEUR_ETABLE_POSITION_Y = 520;
    private static final int SECTEUR_PLANTATION_POSITION_X = 20;
    private static final int SECTEUR_PLANTATION_POSITION_Y = 520;

    private static final int PORTE_ACCUEIL_X = 100;
    private static final int PORTE_ACCUEIL_Y = 220;
    private static final int PORTE_POULLAILLER_X = 220;
    private static final int PORTE_POULLAILLER_Y = 220;
    private static final int PORTE_EQUITATION_X = 220;
    private static final int PORTE_EQUITATION_Y = 220;
    private static final int PORTE_ETABLE_X = 220;
    private static final int PORTE_ETABLE_Y = 220;
    private static final int PORTE_PLANTATION_X = 220;
    private static final int PORTE_PLANTATION_Y = 220;


    int FPS = 60;//Frame Per Second
    public DetectionCollision cDetection = new DetectionCollision(this);
    public PathFinder pathFinder = new PathFinder(this);
    public KeyHandler keyHandler = new KeyHandler();

    private Thread simulationThread;

    private Ferme ferme;
    List<String> map;
    List<String> decor;
Map<String, List<DirectionEnum>> listesChemin;//hashMap
    BufferedImage[][] tuilesMap;
    BufferedImage[][] tuilesMap2;
    BufferedImage[][] tuilesDecor;
    BufferedImage[][] tuilesEntitesMobiles;

    //Fermier

    Fermier fermier;

    Visiteur visiteurH;
    Visiteur visiteurF;

    Secteur accueil;
    Secteur poullailler;
    Secteur equitation;
    Secteur etable;
    Secteur plantation;

    Tuile[][] entites;

    List<Secteur> secteurList;
    List<Visiteur> visiteurList;

    public Panel() {

        this.addMouseListener(this); //Detection de la souris
        this.addKeyListener(keyHandler); //Detection du clavier
        this.setFocusable(true); //Permet d'avoir le choix entre les deux detections

        tuilesMap = FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_MAP, LONGUEUR_JEU_DE_TUILE_MAP, LARGEUR_JEU_DE_TUILE_MAP,
                TAILLE_IMAGE, ESPACEMENT_JEU_DE_TUILE_MAP);

        tuilesMap2 = FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_MAP2, LONGUEUR_JEU_DE_TUILE_MAP2, LARGEUR_JEU_DE_TUILE_MAP2,
                TAILLE_IMAGE, ESPACEMENT_JEU_DE_TUILE_MAP2);

        tuilesDecor = FermeUtils.loadTiles(CHEMIN_IMAGE_TUILES_DECOR, LONGUEUR_JEU_DE_TUILE_DECOR, LARGEUR_JEU_DE_TUILE_DECOR,
                TAILLE_IMAGE, ESPACEMENT_JEU_DE_TUILE_DECOR);

        tuilesEntitesMobiles = FermeUtils.loadTiles(CHEMIN_IMAGE_TUILE_ENTITES_MOBILES, LONGUEUR_JEU_DE_TUILE_ENTITES_MOBILES, LARGEUR_JEU_DE_TUILE_ENTITES_MOBILES,
                TAILLE_IMAGE, ESPACEMENT_JEU_DE_TUILE_ENTITES_MOBILES);

        map = FermeUtils.generateMapFromTextFile("Map.txt");
        decor = FermeUtils.generateMapFromTextFile("decor.txt");
        listesChemin = FermeUtils.readFarmPathFromFile("paths.txt");
        entites = new Tuile[35][35];


        fermier = new Fermier(INITIAL_FERMIER_POSITION_X, INITIAL_FERMIER_POSITION_Y, 20, tuilesEntitesMobiles[0][15]);
        Rectangle2D location1 = new Rectangle2D.Double();
        location1.setRect(SECTEUR_ACCUEIL_POSITION_X, SECTEUR_ACCUEIL_POSITION_Y, LARGEUR_ACCUEIL, HAUTEUR_ACCUEIL);
        accueil = new Secteur(location1, SecteurEnum.ACCUEIL,PORTE_ACCUEIL_X,PORTE_ACCUEIL_Y);
        Rectangle2D location2 = new Rectangle2D.Double();
        location2.setRect(SECTEUR_POULLAILLER_POSITION_X, SECTEUR_POULLAILLER_POSITION_Y, LARGEUR_POULLAILLER, HAUTEUR_POULLAILLER);
        poullailler = new Secteur(location2, SecteurEnum.POULAILLER,PORTE_POULLAILLER_X,PORTE_POULLAILLER_Y);
        Rectangle2D location3 = new Rectangle2D.Double();
        location3.setRect(SECTEUR_EQUITATION_POSITION_X, SECTEUR_EQUITATION_POSITION_Y, LARGEUR_EQUITATION, HAUTEUR_EQUITATION);
        equitation = new Secteur(location3, SecteurEnum.EQUITATION,PORTE_EQUITATION_X,PORTE_EQUITATION_Y);
        Rectangle2D location4 = new Rectangle2D.Double();
        location4.setRect(SECTEUR_ETABLE_POSITION_X, SECTEUR_ETABLE_POSITION_Y, LARGEUR_ETABLE, HAUTEUR_ETABLE);
        etable = new Secteur(location4, SecteurEnum.ETABLE,PORTE_ETABLE_X,PORTE_ETABLE_Y);
        Rectangle2D location5 = new Rectangle2D.Double();
        location5.setRect(SECTEUR_PLANTATION_POSITION_X, SECTEUR_PLANTATION_POSITION_Y, LARGEUR_PLANTATION, HAUTEUR_PLANTATION);
        plantation= new Secteur(location5, SecteurEnum.PLANTATION,PORTE_PLANTATION_X,PORTE_PLANTATION_Y);
        secteurList=new ArrayList<>();
        secteurList.add(accueil);
        secteurList.add(poullailler);
        secteurList.add(equitation);
        secteurList.add(etable);
        secteurList.add(plantation);

        visiteurList = new ArrayList<>();

        for (int i= 0; i<(NB_VISITEUR/2); i++) {
            visiteurH = new Visiteur((int) accueil.getLocation().getMaxX(), (int) (accueil.getLocation().getMaxY() / 2), 20,
                    accueil, equitation, tuilesEntitesMobiles[0][15]);
            visiteurF = new Visiteur((int) accueil.getLocation().getMaxX(), (int) (accueil.getLocation().getMaxY() / 2), 20,
                    accueil, equitation, tuilesEntitesMobiles[0][25]);
            visiteurH.setSecActivite(secteurList);
            visiteurF.setSecActivite(secteurList);
            visiteurList.add(visiteurF);
            visiteurList.add(visiteurH);

        }

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0, 0, LARGEUR, LONGUEUR);

        generateMapOrDecor(g2, map);

        generateMapOrDecor(g2, decor);
        fermier.dessiner(g2, this);

        for(Visiteur visiteur: visiteurList) {
            visiteur.dessiner(g2, this);
        }
    }

    public void startSimulation() {
        simulationThread = new Thread(this);
        simulationThread.start();
    }


    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;

        //Game loop
        while (simulationThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta > 1) {
                repaint();
                update();
                delta--;


            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    // Dessiner notre monde en 2D
    private void generateMapOrDecor(Graphics2D g2, List<String> map) {
        int position_X = 0;
        int position_Y = 0;
        Tuile entite = null;
        int j = 0;

        for (String ligne : map) {

            for (int i = 0; i < ligne.length(); i++) {

                switch (ligne.charAt(i)) {

                    case '9':
                        //la terre
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap2[0][0], false);
                        entite.dessiner(g2, this);
                        break;

                    case '8':

                        //cloture
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap[3][21], true);
                        entite.dessiner(g2, this);

                        break;

                    case '7':
                        //herbe
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap[2][1], false);
                        entite.dessiner(g2, this);
                        break;

                    case '6':

                        //chemin
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap[3][15], false);
                        entite.dessiner(g2, this);


                        break;

                    case '5':
                        //water
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap[21][5], true);
                        entite.dessiner(g2, this);

                        break;

                    case '4':
                        //ecurie
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap[13][12], false);
                        entite.dessiner(g2, this);

                        break;

                    case '3':
                        //hangar
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap2[1][8], false);
                        entite.dessiner(g2, this);

                        break;

                    case '2':
                        //entree
                        entite = new Tuile(position_X, position_Y, 20, tuilesDecor[0][8], false);
                        entite.dessiner(g2, this);

                        break;

                    case '1':
                        //enclos
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap[6][4], false);
                        entite.dessiner(g2, this);

                        break;

                    case 'a':
                        //arbre
                        entite = new Tuile(position_X, position_Y, 30, tuilesDecor[0][1], true);
                        entite.dessiner(g2, this);

                        break;

                    case 'b':
                        //blé
                        entite = new Tuile(position_X, position_Y, 20, tuilesDecor[0][18], true);
                        entite.dessiner(g2, this);

                        break;

                    case 'c':
                        //champignon
                        entite = new Tuile(position_X, position_Y, 20, tuilesDecor[0][17], true);
                        entite.dessiner(g2, this);

                        break;
                    case 'e':
                        //abriAnimaux
                        entite = new Tuile(position_X, position_Y, 40, tuilesDecor[0][4], true);
                        entite.dessiner(g2, this);

                        break;


                    case 'f':
                        //fleur
                        entite = new Tuile(position_X, position_Y, 25, tuilesMap[9][1], true);
                        entite.dessiner(g2, this);


                        break;


                    case 'h':
                        //horseshoe
                        entite = new Tuile(position_X, position_Y, 20, tuilesDecor[0][10], true);
                        entite.dessiner(g2, this);

                        break;

                    case 'i':
                        //herbe
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap2[1][1], false);
                        entite.dessiner(g2, this);

                        break;

                    case 'j':
                        //herbe 1
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap2[2][2], false);
                        entite.dessiner(g2, this);

                        break;

                    case 'k':
                        //herbe 2
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap2[2][1], false);
                        entite.dessiner(g2, this);

                        break;

                    case 'l':
                        //legumes
                        entite = new Tuile(position_X, position_Y, 20, tuilesDecor[0][16], true);
                        entite.dessiner(g2, this);

                        break;

                    case 'm':
                        //maison
                        entite = new Tuile(position_X, position_Y, 70, tuilesDecor[0][9], true);
                        entite.dessiner(g2, this);

                        break;

                    case 'o':
                        //herbe 3
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap2[1][2], false);
                        entite.dessiner(g2, this);

                        break;

                    case 'p':
                        //pommier
                        entite = new Tuile(position_X, position_Y, 40, tuilesDecor[0][0], true);
                        entites[j][i] = entite;
                        entites[j][i + 1] = entite;
                        entites[j + 1][i] = entite;
                        entites[j + 1][i + 1] = entite;
                        entite.dessiner(g2, this);

                        break;

                    case 's':
                        //pierre
                        entite = new Tuile(position_X, position_Y, 20, tuilesMap[14][0], true);
                        entite.dessiner(g2, this);

                        break;

                    case 't':
                        //outil 1
                        entite = new Tuile(position_X, position_Y, 20, tuilesDecor[0][12], true);
                        entite.dessiner(g2, this);

                        break;
                    case 'v':
                        //outil 2
                        entite = new Tuile(position_X, position_Y, 20, tuilesDecor[0][14], true);
                        entite.dessiner(g2, this);

                        break;

                    case 'w':
                        //outil3
                        entite = new Tuile(position_X, position_Y, 20, tuilesDecor[0][15], true);
                        entite.dessiner(g2, this);

                        break;

                    case 'x':
                        //entrepot
                        entite = new Tuile(position_X, position_Y, 40, tuilesDecor[0][13], true);
                        entite.dessiner(g2, this);

                        break;

                    default:


                        break;


                }
                position_X += TAILLE_BLOC;
                if (null != entite) {
                    entites[j][i] = entite;
                    //System.out.println("j "+j+", "+entite.positionCourantY+" i "+i+"  "+entite.positionCourantX);
                }

                entite = null;

            }
            j++;
            position_X = 0;
            position_Y += TAILLE_BLOC;
        }

    }

    public void update() {

        if (keyHandler.upPressed) {
            keyHandler.upPressed = false;
            fermier.setDirection(DirectionEnum.UP);
            this.cDetection.checkTile(fermier);
            if (!fermier.collisionOn)
                fermier.setPositionCourantY(fermier.getPositionCourantY() - fermier.getVitesseY());
        }
        if (keyHandler.downPressed) {
            keyHandler.downPressed = false;
            fermier.setDirection(DirectionEnum.DOWN);
            this.cDetection.checkTile(fermier);
            if (!fermier.collisionOn)
                fermier.setPositionCourantY(fermier.getPositionCourantY() + fermier.getVitesseY());
        }
        if (keyHandler.leftPressed) {
            keyHandler.leftPressed = false;
            fermier.setDirection(DirectionEnum.LEFT);
            this.cDetection.checkTile(fermier);
            if (!fermier.collisionOn)
                fermier.setPositionCourantX(fermier.getPositionCourantX() - fermier.getVitesseX());
        }
        if (keyHandler.rightPressed) {
            keyHandler.rightPressed = false;
            fermier.setDirection(DirectionEnum.RIGHT);
            this.cDetection.checkTile(fermier);
            if (!fermier.collisionOn)
                fermier.setPositionCourantX(fermier.getPositionCourantX() + fermier.getVitesseX());
        }
        if (keyHandler.bPressed) {
            keyHandler.bPressed = false;


        }
        for(Visiteur visiteur: visiteurList) {
            visiteur.bouger(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());

    }

    @Override
    public void mousePressed(MouseEvent e) {

        //fermier.allerDestination(e, this);
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
