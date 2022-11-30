package fr.um3.info;

import fr.um3.info.enums.ActionPersonnageEnum;
import fr.um3.info.enums.DirectionEnum;
import fr.um3.info.enums.TypePersonnageEnum;
import fr.um3.info.utils.FermeUtils;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Fermier extends Personnage implements Serializable, Action {
    public static final int INTERVAL_CHANGEMENT_ACTION = 20;
    Thread compteurThread;


    public Fermier(int positionCourantX, int positionCourantY, int taille, BufferedImage image) {


        super.positionCourantX = positionCourantX;
        super.positionCourantY = positionCourantY;
        super.taille = taille;
        super.image = image;
        super.solidArea = new Rectangle();
        super.solidArea.x = 4;
        super.solidArea.y = 4;
        super.solidArea.height = 16;
        super.solidArea.width = 16;

        super.actionEncours = ActionPersonnageEnum.CHANGER_SECTEUR;

        TimerTask task = new TimerTask() {
            public void run() {
                Fermier.super.compteur++;
            }
        };
        java.util.Timer timer = new Timer("Timer");
        long delay = 1000L;
        timer.scheduleAtFixedRate(task, delay, delay);

    }


    @Override
    public String toString() {
        return "Fermier{" +
                "positionCourantX=" + positionCourantX +
                ", positionCourantY=" + positionCourantY +
                ", taille=" + taille +
                ", secAncien=" + secAncien +
                ", sectActuelle=" + sectActuelle +
                ", secActivite=" + secActivite +
                ", couleur=" + couleur +
                ", vitesseX=" + vitesseX +
                ", vitesseY=" + vitesseY +
                '}';
    }

    @Override
    public void travailler() {
        float posX = this.getPositionCourantX();
        float posY = this.getPositionCourantY();
        int vitY = this.getVitesseY();
        int vitX = this.getVitesseX();


        this.setPositionCourantX(posX + this.getVitesseX());
        this.setPositionCourantY(posY + this.getVitesseY());

        if (this.getPositionCourantX() < this.getSectActuelle().getLocation().getMinX()) {
            this.setVitesseX(-vitX);
            this.setVitesseY(ThreadLocalRandom.current().nextInt(1, 4));
        }
        if (this.getPositionCourantY() < this.getSectActuelle().getLocation().getMinY()) {
            this.setVitesseY(-vitY);
            this.setVitesseX((ThreadLocalRandom.current().nextInt(1, 4)));
        }
        if (this.getPositionCourantX() >= this.getSectActuelle().getLocation().getMaxX() - this.taille) {
            this.setVitesseX(-vitX);

        }
        if (this.getPositionCourantY() >= this.getSectActuelle().getLocation().getMaxY() - this.taille) {
            this.setVitesseY(-vitY);

        }

    }

    @Override
    public void bouger(Panel panel) {
        if (this.getCompteur() >= INTERVAL_CHANGEMENT_ACTION) {
            this.changerAction();
        }
        switch (this.actionEncours) {

            case TRAVAILLER:
                this.travailler();
                break;

            case CHANGER_SECTEUR:
                Random rand = new Random();
                List<Secteur> secteursVisitable = this.secActivite
                        .stream()
                        .filter(secteur -> !this.sectActuelle.equals(secteur))
                        .collect(Collectors.toList());
                this.changerSecteur(secteursVisitable.get(rand.nextInt(secteursVisitable.size())), panel);
                break;

        }
    }

    private void changerAction() {
        this.compteur = 0;
        if (!this.getActionEncours().equals(ActionPersonnageEnum.TRAVAILLER)) {
            this.setActionEncours(ActionPersonnageEnum.TRAVAILLER);
            return;

        }
        this.setPositionCourantX((int) this.getSectActuelle().getLocation().getCenterX());
        this.setPositionCourantY((int) this.getSectActuelle().getLocation().getCenterY());
        this.setActionEncours(ActionPersonnageEnum.CHANGER_SECTEUR);


    }

    // TODO reflechir à un meilleur moyen de changer de secteur
    public void changerSecteur(Secteur nouveauSec, Panel panel) {
        if (!this.getSectActuelle().equals(nouveauSec)) {
            this.setSecAncien(this.getSectActuelle());
            this.setSectActuelle(nouveauSec);
            if (nouveauSec.getLocation().getMinX() > this.getPositionCourantX()) {
                if (this.getVitesseX() < 0) {
                    this.setVitesseX(this.getVitesseX() * -1);
                }
            }
            if (nouveauSec.getLocation().getMinY() > this.getPositionCourantY()) {
                if (this.getVitesseY() < 0) {
                    this.setVitesseY(this.getVitesseY() * -1);
                }
            }
            if (nouveauSec.getLocation().getMaxX() < this.getPositionCourantX()) {
                if (this.getVitesseX() > 0) {
                    this.setVitesseX(this.getVitesseX() * -1);
                }
            }
            if (nouveauSec.getLocation().getMaxY() < this.getPositionCourantY()) {
                if (this.getVitesseY() > 0) {
                    this.setVitesseY(this.getVitesseY() * -1);
                }
            }
            try {
                changerPosition(this, panel);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void changerPosition(Personnage personnage, Panel panel) throws InterruptedException {


        float vx = (int) personnage.getSectActuelle().getLocation().getCenterX() - personnage.getPositionCourantX();
        float vy = (int) personnage.getSectActuelle().getLocation().getCenterY() - personnage.getPositionCourantY();
        for (float t = 0.0f; t < 1.0; t += 0.0001) {

            personnage.setPositionCourantX(personnage.getPositionCourantX() + vx * t);
            personnage.setPositionCourantY(personnage.getPositionCourantY() + vy * t);
            panel.repaint();
            Thread.sleep(10);

            if (personnage.getPositionCourantX() >= personnage.getSectActuelle().getLocation().getMinX()
                    &&
                    personnage.getPositionCourantX() >= personnage.getSectActuelle().getLocation().getMinX()
                    &&
                    personnage.getPositionCourantX() < (personnage.getSectActuelle().getLocation().getMinX() +
                            personnage.getSectActuelle().getLocation().getWidth() - this.getTaille())
                    &&
                    personnage.getPositionCourantY() < (personnage.getSectActuelle().getLocation().getMinY() +
                            personnage.getSectActuelle().getLocation().getHeight() - this.getTaille())) {
                this.setActionEncours(ActionPersonnageEnum.TRAVAILLER);
                return;
            }


        }
    }

    public void searchPath(int destinationCol, int destinationRow, Panel panel) {
        int startX = (int) this.positionCourantX / Panel.TAILLE_BLOC;
        int startY = (int) this.positionCourantY / Panel.TAILLE_BLOC;
        panel.pathFinder.setNodes(startX, startY, destinationCol, destinationRow);

        if (panel.pathFinder.search()) {
            int nextX = panel.pathFinder.pathList.get(0).col * Panel.TAILLE_BLOC;
            int nextY = panel.pathFinder.pathList.get(0).row * Panel.TAILLE_BLOC;

            if ((int) this.positionCourantY > nextY ) {
                this.direction = DirectionEnum.UP;

            } else if ((int) this.positionCourantY < nextY ) {

                this.direction = DirectionEnum.DOWN;
            } else if ((int) this.positionCourantX > nextX ) {
                this.direction = DirectionEnum.LEFT;

            } else if ((int) this.positionCourantX < nextX ) {
                this.direction = DirectionEnum.RIGHT;
            }
            int nextCol = panel.pathFinder.pathList.get(0).col ;
            int nextRow = panel.pathFinder.pathList.get(0).row ;
            if(nextCol==destinationCol&&nextRow==destinationRow){
                onPath=false;


            }

        }
    }


    public void allerDestination(MouseEvent e, Panel panel) {
        this.collisionOn = false;
        onPath=true;
        int destinationX=e.getX()/Panel.TAILLE_BLOC;
        int destinationY=e.getY()/Panel.TAILLE_BLOC;


        new Thread(() -> {
               while(onPath) {
                   searchPath(destinationX,destinationY,panel);
                   panel.cDetection.checkTile(this);
                   if (!collisionOn) {
                       switch (direction) {
                           case UP:
                               positionCourantY -= vitesseY;
                               break;
                           case DOWN:
                               positionCourantY += vitesseY;
                               break;
                           case LEFT:
                               positionCourantX -= vitesseX;
                               break;
                           case RIGHT:
                               positionCourantX += vitesseX;
                               break;

                       }
                       long startTime = System.currentTimeMillis();
                       long currentTime = startTime;
                       while (currentTime < startTime + 100) {
                           try {
                               Thread.sleep(10);
                           } catch (InterruptedException interruptedException) {
                               interruptedException.printStackTrace();
                           }
                           Thread.yield();
                           currentTime = System.currentTimeMillis();
                       }
                       SwingUtilities.invokeLater(panel::repaint);

                   }
               }


        }).start();


    }

    public List<DirectionEnum> getPath() {

        return new ArrayList<>(Arrays.asList(
                DirectionEnum.DOWN,
                DirectionEnum.LEFT,
                DirectionEnum.LEFT,
                DirectionEnum.LEFT,
                DirectionEnum.LEFT));
    }
}
