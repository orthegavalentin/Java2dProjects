package fr.um3.info;

import fr.um3.info.Panel;
import fr.um3.info.Personnage;
import fr.um3.info.enums.ActionPersonnageEnum;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Animal extends Personnage {
    Secteur secteur;

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Animal(int positionCourantX, int positionCourantY, int taille, BufferedImage image,
                  Secteur secteur) {


        super.positionCourantX = positionCourantX;
        super.positionCourantY = positionCourantY;
        super.taille = taille;
        super.image = image;
        super.images=images;
        this.secteur=secteur;
        super.vitesseX=3;
        super.vitesseY=3;

        super.actionEncours = ActionPersonnageEnum.CHANGER_SECTEUR;


    }
    @Override
    public void travailler() {
        //not implemented
    }

    @Override
    public void visiter() {
        //not implemented

    }

    @Override
    public void bouger(Panel panel) {
        float posX = this.getPositionCourantX();
        float posY = this.getPositionCourantY();
        int vitY = this.getVitesseY();
        int vitX = this.getVitesseX();


        this.setPositionCourantX(posX + this.getVitesseX());
        this.setPositionCourantY(posY + this.getVitesseY());

        if (this.getPositionCourantX() < this.getSecteur().getLocation().getMinX()) {
            this.setVitesseX(-vitX);
            this.setVitesseY(ThreadLocalRandom.current().nextInt(1, 3));
        }
        if (this.getPositionCourantY() < this.getSecteur().getLocation().getMinY()) {
            this.setVitesseY(-vitY);
            this.setVitesseX((ThreadLocalRandom.current().nextInt(1, 3)));
        }
        if (this.getPositionCourantX() >= this.getSecteur().getLocation().getMaxX() - this.taille) {
            this.setVitesseX(-vitX);


        }
        if (this.getPositionCourantY() >= this.getSecteur().getLocation().getMaxY() - this.taille) {
            this.setVitesseY(-vitY);


        }

    }

    @Override
    public void changerSecteur(Panel panel) {

    }
}
