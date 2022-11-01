package fr.um3.info;
// joue le role de telecommande
public interface Action {
     void travailler();

     void bouger(Panel panel);

     void changerSecteur(Secteur nouveauSec,Panel panel);


}