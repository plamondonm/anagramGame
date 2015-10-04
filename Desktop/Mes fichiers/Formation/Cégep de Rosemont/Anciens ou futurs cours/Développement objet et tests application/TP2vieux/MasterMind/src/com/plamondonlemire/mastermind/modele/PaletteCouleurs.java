
package com.plamondonlemire.mastermind.modele;

import java.awt.Color;

public class PaletteCouleurs {
    private static final Color TABCOULEURS[] = 
                         {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
                          Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.PINK};
    
    public static Color genererCouleurAleatoire() {
        
        int nombreAleatoire;
        
        nombreAleatoire = (int)(Math.random() * TABCOULEURS.length);
        
        return TABCOULEURS[nombreAleatoire];
    }
    
    public static int maxCouleurs() {
        return TABCOULEURS.length;
    }
    
    public static String nomCouleur(Color uneCouleur) {
        
        if(uneCouleur.equals(Color.RED)) {
            return "Rouge";
        }
         
        if(uneCouleur.equals(Color.BLUE)) {
            return "Bleu";       
        }
         
        if(uneCouleur.equals(Color.GREEN)) {
            return "Vert";       
        }
        
        if(uneCouleur.equals(Color.ORANGE)) {
            return "Orange";
        }
         
        if(uneCouleur.equals(Color.YELLOW)) {
            return "Jaune";       
        }
         
        if(uneCouleur.equals(Color.MAGENTA)) {
            return "Magenta";       
        }
        
        if(uneCouleur.equals(Color.CYAN)) {
            return "Cyan";       
        }
        
        if(uneCouleur.equals(Color.PINK)) {
            return "Rose";       
        }
        
        if(uneCouleur.equals(Color.GRAY)) {
            return "Gris";       
        }
        
        if(uneCouleur.equals(Color.BLACK)) {
            return "Noir";       
        }
        
        if(uneCouleur.equals(Color.WHITE)) {
            return "Blanc";       
        }        
        return "Couleur indéfinie";
    }

}
