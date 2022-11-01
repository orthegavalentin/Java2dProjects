package fr.um3.info;

import java.awt.*;

public class Visiteur extends Personnage{
    public Visiteur(int positionCourantX, int positionCourantY, Color couleur, int taille) {
        super.positionCourantX=positionCourantX;
        super.positionCourantY=positionCourantY;
        super.couleur=couleur;
        super.taille= taille;
    }

    @Override
    public void dessiner(Graphics2D g2, Panel panel) {

    }

    @Override
    public void travailler() {

    }

    @Override
    public void bouger(Panel panel) {

    }

    @Override
    public void changerSecteur(Secteur nouveauSec,Panel panel) {

    }
}
