package fr.um3.info;

import fr.um3.info.enums.SecteurEnum;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Equitation extends Secteur {

    public Equitation(Rectangle2D location, Color couleur, SecteurEnum typeSecteur) {
        super(location,  typeSecteur,0,0);
    }
}
