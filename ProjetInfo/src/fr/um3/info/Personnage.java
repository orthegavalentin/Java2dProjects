package fr.um3.info;

import fr.um3.info.enums.ActionPersonnageEnum;
import fr.um3.info.enums.DirectionEnum;
import fr.um3.info.enums.TypePersonnageEnum;

import java.awt.*;
import java.util.List;


public abstract class Personnage extends Entite implements Action {
    protected Secteur secteurDepart;
    protected Secteur secteurDestination;
    protected TypePersonnageEnum typePersonnage;
    protected List<Secteur> secActivite;
    protected Color couleur;
    protected int vitesseX=20;
    protected int vitesseY=20;
    protected DirectionEnum direction= DirectionEnum.UP;

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

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

    public Secteur getSecteurDepart() {
        return secteurDepart;
    }

    public void setSecteurDepart(Secteur secAncien) {
        this.secteurDepart = secAncien;
    }

    public int getVitesseX() {
        return vitesseX;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public Secteur getSecteurDestination() {
        return this.secteurDestination;
    }

    public void setSecteurDestination(Secteur sectActuelle) {
        this.secteurDestination = sectActuelle;
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






