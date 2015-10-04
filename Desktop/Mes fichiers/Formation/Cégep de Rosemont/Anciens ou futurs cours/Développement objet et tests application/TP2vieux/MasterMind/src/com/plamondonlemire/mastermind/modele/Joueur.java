
package com.plamondonlemire.mastermind.modele;

import java.io.Serializable;

public class Joueur implements Serializable {

    private int nombreCoupsReussis;
    private int nombreCoupsBlancs;
    private int nombreLignesReussis;
    private int points;

    public Joueur() {
        
        setNombreCoupsReussis(0);
        setNombreCoupsBlancs(0);
        setNombreLignesReussis(0);
        setPoints(0);
    }

    public int getNombreCoupsReussis() {
        return nombreCoupsReussis;
    }

    public void setNombreCoupsReussis(int nbreCoupsReussis) {
       
        nombreCoupsReussis = nbreCoupsReussis >= 0 ? nbreCoupsReussis : 0;
    }

    public int getNombreCoupsBlancs() {

        return nombreCoupsBlancs;
    }

    public void setNombreCoupsBlancs(int nbreCoupsBlancs) {
  
        nombreCoupsBlancs = nbreCoupsBlancs >= 0 ? nbreCoupsBlancs : 0;
    }

    public int getNombreLignesReussis() {
        return nombreLignesReussis;
    }

    public void setNombreLignesReussis(int nombreLignesReussis) {
        this.nombreLignesReussis = nombreLignesReussis;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int lesPoints) {
        
        points = lesPoints >= 0 ? lesPoints : 0;
    }
    
    public void augmenterNombreCoupsReussis(int nombreCoups) {
        
        if(nombreCoups > 0) {
            nombreCoupsReussis += nombreCoups;
        }
    }
 
    public void augmenterNombreCoupsBlancs(int nombreCoups) {
        
        if(nombreCoups > 0) {
            nombreCoupsBlancs += nombreCoups;
        }
    }
    
    public void augmenterPoints(int pointsSupplementaires) {
        if(pointsSupplementaires > 0) {
            points += Pointage.getPointageTotal(nombreCoupsReussis, 
                               nombreCoupsBlancs, nombreLignesReussis);
        }       
    }
    
    public void remettreAZero() {
        setNombreCoupsBlancs(0);
        setNombreCoupsReussis(0);
        setNombreLignesReussis(0);
        points = 0;
    }
    
    public String toString() {
        return nombreCoupsReussis + " " + nombreCoupsBlancs + " " + points;	
    }  
}
