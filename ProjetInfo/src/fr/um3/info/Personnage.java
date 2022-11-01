package fr.um3.info;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;


public abstract class Personnage implements Action{
    protected float positionCourantX;
    protected float positionCourantY;
    protected int taille;
    protected Secteur secAncien;
    protected Secteur sectActuelle;
    protected TypePersonnageEnum typePersonnage;

    protected List<Secteur> secActivite;
    protected Color couleur;

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    protected int compteur;

    public ActionPersonnageEnum getActionEncours() {
        return actionEncours;
    }

    public void setActionEncours(ActionPersonnageEnum actionEncours) {
        this.actionEncours = actionEncours;
    }

    protected ActionPersonnageEnum actionEncours;

    public TypePersonnageEnum getTypePersonnage() {
        return typePersonnage;
    }

    public void setTypePersonnage(TypePersonnageEnum typePersonnage) {
        this.typePersonnage = typePersonnage;
    }

    protected int vitesseX;
    protected int vitesseY;

    protected String cheminImage;




    public float getPositionCourantX() {
        return positionCourantX;
    }

    public float getPositionCourantY() {
        return positionCourantY;
    }

    public int getTaille() {
        return taille;
    }

    public Color getCouleur() {
        return couleur;
    }

    public List<Secteur> getSecActivite() {
        return secActivite;
    }

    public Secteur getSecAncien() {
        return secAncien;
    }

    public void setSecAncien(Secteur secAncien) {
        this.secAncien = secAncien;
    }

    public int getVitesseX() {
        return vitesseX;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public Secteur getSectActuelle() {
        return sectActuelle;
    }

    public void setSectActuelle(Secteur sectActuelle) {
        this.sectActuelle = sectActuelle;
    }

    public void setPositionCourantX(float positionCourantX) {
        this.positionCourantX = positionCourantX;
    }

    public void setPositionCourantY(float positionCourantY) {
        this.positionCourantY = positionCourantY;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setSecActivite(List<Secteur> secActivite) {
        this.secActivite = secActivite;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }


    public void dessiner(Graphics2D g2){
        g2.setColor(this.couleur);

        g2.fillRect((int)this.positionCourantX,(int)this.positionCourantY,this.taille,this.taille );

    }
    //surcharge
    public abstract void dessiner(Graphics2D g2, Panel panel);


}






