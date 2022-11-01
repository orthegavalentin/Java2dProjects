package fr.um3.info;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Fermier extends Personnage implements Serializable, Action {
    public static final int INTERVAL_CHANGEMENT_ACTION = 20;
    Thread compteurThread;


    public Fermier(int positionCourantX, int positionCourantY, Color couleur, int taille, String cheminImage,
                   int vitesseX, int vitesseY, TypePersonnageEnum typePersonnage, Secteur secteurActuel) {

        super.sectActuelle = secteurActuel;
        super.positionCourantX = (int) this.sectActuelle.getLocation().getMinX() + positionCourantX;
        super.positionCourantY = (int) this.sectActuelle.getLocation().getMinY() + positionCourantY;
        super.couleur = couleur;
        super.taille = taille;
        super.cheminImage = cheminImage;
        super.vitesseX = vitesseX;
        super.vitesseY = vitesseY;
        super.typePersonnage = typePersonnage;
        super.compteur = 0;
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
    public void dessiner(Graphics2D g2, Panel panel) {
        BufferedImage image;
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir"));
        sb.append("\\");
        sb.append(this.cheminImage);
        try {
            image = FermeUtils.resize(ImageIO.read(new File(sb.toString())), this.taille, this.taille);
            g2.drawImage(image, (int) this.positionCourantX, (int) this.positionCourantY, panel);

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        // methode statique c une methode de la classe
        // methode ou variable non static c pour l'objet donc faut instancier l'objet avant de l'appeler
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
                ", cheminImage='" + cheminImage + '\'' +
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
        System.out.println("---Travailler--- " + " x =" + this.positionCourantX + " y= " + this.positionCourantY);
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
            System.out.println("---Changer Secteur--- " + " x =" + this.positionCourantX + " y= " + this.positionCourantY);
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


}
