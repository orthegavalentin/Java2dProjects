package fr.um3.info;

import java.util.List;

public class Ferme {
    private List<Personnage> personnages;
    private List<Secteur> secteurs;

    public Ferme(List<Personnage> personages, List<Secteur> secteurs) {
        this.personnages = personages;
        this.secteurs = secteurs;
    }

    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public List<Personnage> getPersonnages() {
        return personnages;
    }

    public void setPersonnages(List<Personnage> personnages) {
        this.personnages = personnages;
    }
}
