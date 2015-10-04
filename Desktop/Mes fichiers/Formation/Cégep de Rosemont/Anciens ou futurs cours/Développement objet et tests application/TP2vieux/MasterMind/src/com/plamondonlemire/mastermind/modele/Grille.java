
package com.plamondonlemire.mastermind.modele;

import java.awt.Color;
import java.io.Serializable;

public class Grille implements Serializable {
    
    private Jeton[][] tabJetons;
    private int maximumLignes;
    private int maximumColonnes;
   
    public Grille() {
       this(10, 4);
    }
   
    public Grille(int maxLignes, int maxColonnes) {
       setMaxGrille(maxLignes, maxColonnes);
    }

    public void setMaxGrille(int maxLignes, int maxColonnes) {
       
        maximumColonnes = maxColonnes > 2 ? maxColonnes : 4;
        maximumLignes = maxLignes > 0 ? maxLignes : 4;
        tabJetons = new Jeton[maximumLignes][maximumColonnes];
        initialiserGrille();
    }  
    
    public int getMaxLignes( ) {
        return maximumLignes;
    }
    
    public int getMaxColonnes() {
        return maximumColonnes;
    }
    
    public void initialiserGrille() {
        
        for(int ligne = 0; ligne < maximumLignes; ligne++) {
            for(int colonne = 0; colonne < maximumColonnes; colonne++) {
               tabJetons[ligne][colonne] = new Jeton();
            }
        }
    }
    
    public void modifierLigneJetons(int numeroLigne, Color uneCouleur) {
        if((numeroLigne >= 0) && (numeroLigne < maximumLignes)) {
            for(int indice = 0; indice < maximumColonnes; indice++) {
                tabJetons[numeroLigne][indice].setCouleur(uneCouleur);
            }
        }
    }
    
    public void modifierJetonXY(int noLigne, int noColonne, Color uneCouleur) {
       if((noLigne >= 0) && (noLigne < maximumLignes) && 
          (noColonne >= 0) && (noColonne < maximumColonnes)) {
            tabJetons[noLigne][noColonne].setCouleur(uneCouleur);
       }
    }
    
    public Jeton getJeton(int noLigne, int noColonne) {
         if((noLigne >= 0) && (noLigne < maximumLignes) && 
            (noColonne >= 0) && (noColonne < maximumColonnes)) {
             return tabJetons[noLigne][noColonne];
       }
       return null;
    }
    
    public boolean toEquals(Grille uneGrille) {
        
        boolean identique = true;
        int compteur = 0;
        
        if(maximumColonnes == uneGrille.getMaxColonnes()) {
            while(identique && (compteur < maximumColonnes)) {
                if(!tabJetons[0][compteur].toEquals(uneGrille.getJeton(0, compteur))) {
                    identique = false;
                }
                else {
                    compteur++;
                }
            }
        } 
        else {
            identique = false;
        }
 
        return identique;
    }
    
    public String getGrilleChaine() {
               String chaine = "";
        
        for(int ligne = 0; ligne < maximumLignes; ligne++) {
            for(int colonne = 0; colonne < maximumColonnes; colonne++) {
                chaine += tabJetons[ligne][colonne].getCouleurChaine() + " ";
            }
            chaine += " ** ";
        }
        return chaine;  
    }
    
    public String toString() {
        
        String chaine = "";
        
        for(int ligne = 0; ligne < maximumLignes; ligne++) {
            for(int colonne = 0; colonne < maximumColonnes; colonne++) {
                chaine += tabJetons[ligne][colonne].toString() + " ";
            }
        }
        return chaine;
    } 
}
