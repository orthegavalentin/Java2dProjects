package fr.um3.info;

import fr.um3.info.enums.ActionPersonnageEnum;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Tuile extends Entite {
    public Tuile(int positionCourantX, int positionCourantY, int taille, BufferedImage image) {

        super.positionCourantX = positionCourantX;
        super.positionCourantY = positionCourantY;
        super.taille = taille;
        super.image=image;

    }
}
