package fr.um3.info;
// joue le role de telecommande
public interface Action {
     void travailler();
     void visiter();

     void bouger(Panel panel);

     void changerSecteur(Panel panel);


}