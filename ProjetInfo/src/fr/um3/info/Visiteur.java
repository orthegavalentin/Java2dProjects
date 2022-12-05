package fr.um3.info;

import fr.um3.info.enums.ActionPersonnageEnum;
import fr.um3.info.enums.DirectionEnum;
import fr.um3.info.enums.SecteurEnum;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


public class Visiteur extends Personnage {

    public static final int INTERVAL_CHANGEMENT_ACTION = 20;

    public boolean access=true;

    public Visiteur(int positionCourantX, int positionCourantY, int taille, Secteur depart,
                    Secteur destination, BufferedImage image) {
        super.positionCourantX = positionCourantX;
        super.positionCourantY = positionCourantY;
        super.secteurDepart = depart;
        super.secteurDestination = destination;
        this.actionEncours=ActionPersonnageEnum.CHANGER_SECTEUR;

        super.taille = taille;
        super.image = image;

        TimerTask task = new TimerTask() {
            public void run() {
                compteur++;
            }
        };
        Timer timer = new Timer();
        long delay = 1000L;
        timer.scheduleAtFixedRate(task, delay, delay);
    }


    @Override
    public void travailler() {

    }


    @Override
    public void bouger(Panel panel) {
       /* if (this.getCompteur() >= INTERVAL_CHANGEMENT_ACTION) {
            this.changerAction();
        }*/
        if(access) {
            access=false;
            switch (this.actionEncours) {

                case VISITER:
                    this.visiter();
                    break;

                case CHANGER_SECTEUR:
                    Random rand = new Random();
                    List<Secteur> secteursVisitable = this.secActivite
                            .stream()
                            .filter(secteur -> !this.secteurDepart.equals(secteur))
                            .collect(Collectors.toList());
                    this.secteurDestination = secteursVisitable.get(rand.nextInt(secteursVisitable.size()));
                    this.changerSecteur(panel);
                    break;

            }


        }
    }


    private void changerAction() {
        this.compteur = 0;
        if (!this.getActionEncours().equals(ActionPersonnageEnum.VISITER)) {
            this.setActionEncours(ActionPersonnageEnum.VISITER);
            return;

        }

        //se mettre à la sortie du secteur
        this.setPositionCourantX(this.getSecteurDepart().getPorteX());
        this.setPositionCourantY(this.getSecteurDepart().getPorteY());

        this.setActionEncours(ActionPersonnageEnum.CHANGER_SECTEUR);


    }

    @Override
    public void changerSecteur(Panel panel) {
        String sb = this.getSecteurDepart().getTypeSecteur().toString() +
                "->" +
                this.getSecteurDestination().getTypeSecteur().toString();
        List<DirectionEnum> directions = panel.listesChemin.get(sb);

        new Thread(() -> {
            for (DirectionEnum cDirection : directions) {
                //panel.cDetection.checkTile(this);

                switch (cDirection) {
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
          access=true;
        }).start();

        this.setSecteurDepart(this.getSecteurDestination());
        //this.setActionEncours(ActionPersonnageEnum.VISITER);

    }

    @Override
    public void visiter() {
        float posX = this.getPositionCourantX();
        float posY = this.getPositionCourantY();
        int vitY = this.getVitesseY();
        int vitX = this.getVitesseX();


        this.setPositionCourantX(posX + this.getVitesseX());
        this.setPositionCourantY(posY + this.getVitesseY());

        if (this.getPositionCourantX() < this.getSecteurDestination().getLocation().getMinX()) {
            this.setVitesseX(-vitX);
            this.setVitesseY(ThreadLocalRandom.current().nextInt(1, 4));
        }
        if (this.getPositionCourantY() < this.getSecteurDestination().getLocation().getMinY()) {
            this.setVitesseY(-vitY);
            this.setVitesseX((ThreadLocalRandom.current().nextInt(1, 4)));
        }
        if (this.getPositionCourantX() >= this.getSecteurDestination().getLocation().getMaxX() - this.taille) {
            this.setVitesseX(-vitX);

        }
        if (this.getPositionCourantY() >= this.getSecteurDestination().getLocation().getMaxY() - this.taille) {
            this.setVitesseY(-vitY);

        }
    }


}
