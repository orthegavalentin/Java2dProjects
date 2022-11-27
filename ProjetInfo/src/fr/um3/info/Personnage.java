package fr.um3.info;

import fr.um3.info.enums.ActionPersonnageEnum;
import fr.um3.info.enums.TypePersonnageEnum;

import java.awt.*;
import java.util.List;


public abstract class Personnage extends Entite implements Action {
    protected Secteur secAncien;
    protected Secteur sectActuelle;
    protected TypePersonnageEnum typePersonnage;
    protected List<Secteur> secActivite;
    protected Color couleur;
    protected int vitesseX;
    protected int vitesseY;


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


}






