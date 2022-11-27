package fr.um3.info;


import fr.um3.info.enums.SecteurEnum;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Objects;

public class Secteur implements Serializable {
    private Rectangle2D location;
    private Color couleur;
    private SecteurEnum typeSecteur;

    public Secteur(Rectangle2D location,Color couleur,SecteurEnum typeSecteur){
        this.location=location;
        this. couleur=couleur;
        this.typeSecteur=typeSecteur;

    }

    public Rectangle2D getLocation() {
        return location;
    }

    public void setLocation(Rectangle2D _location) {
        this.location = _location;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public SecteurEnum getTypeSecteur() {
        return typeSecteur;
    }

    public void setTypeSecteur(SecteurEnum typeSecteur) {
        this.typeSecteur = typeSecteur;
    }

    public void dessiner(Graphics2D g2){
        g2.setColor(this.couleur);

        g2.fillRect((int)this.location.getMinX(),(int)this.location.getMinY(),(int)this.location.getWidth(),(int)this.location.getHeight());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secteur secteur = (Secteur) o;
        return typeSecteur == secteur.typeSecteur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeSecteur);
    }

    @Override
    public String toString() {
        return "Secteur{" +
                "location=" + location +
                ", couleur=" + couleur +
                ", typeSecteur=" + typeSecteur +
                '}';
    }
}
