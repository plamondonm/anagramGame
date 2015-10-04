
package com.plamondonlemire.mastermind.modele;

import java.awt.Color;
import java.io.Serializable;

public class Jeton implements Serializable {

    private Color couleur;

    public Jeton() {
       this(Color.GRAY);
    }
    
    public Jeton(Color uneCouleur) {
        setCouleur(uneCouleur);
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color uneCouleur) {
        this.couleur = uneCouleur;
    }
    
    public boolean toEquals(Jeton unJeton) {
        
    	return this.couleur.equals(unJeton.couleur);
    }
    
    public String getCouleurChaine() {
        return PaletteCouleurs.nomCouleur(couleur);  
    }
            
    public String toString() { 
        return couleur.toString();	
    }
}
