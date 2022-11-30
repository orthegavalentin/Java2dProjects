package fr.um3.info;

import fr.um3.info.utils.FermeUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Entite {
    protected float positionCourantX;
    protected float positionCourantY;
    protected int taille;
    protected BufferedImage image;
    protected boolean onPath;
    public Rectangle solidArea;
    public boolean collisionOn=false;


    public float getPositionCourantX() {
        return positionCourantX;
    }

    public float getPositionCourantY() {
        return positionCourantY;
    }

    public int getTaille() {
        return taille;
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

    public void dessiner(Graphics2D g2, Panel panel) {
        BufferedImage image;

        image = FermeUtils.resize(this.image, this.taille, this.taille);
        g2.drawImage(image, (int) this.positionCourantX, (int) this.positionCourantY, panel);


    }


}
