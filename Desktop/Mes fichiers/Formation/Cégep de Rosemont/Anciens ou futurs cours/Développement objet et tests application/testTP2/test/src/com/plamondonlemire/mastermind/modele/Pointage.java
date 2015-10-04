
package com.plamondonlemire.mastermind.modele;

public class Pointage {
    
    private static final int POINTSCOUPSREUSSIS = 10;
    private static final int POINTSCOUPSBLANCS = 5;
    private static final int POINTPARLIGNEREUSSIE = 20;
    
    public static int getPointageTotal(int nombreCoupsReussis, 
                                       int nombreCoupsBlancs,
                                       int nombreLignesReussies) {
        
        return (POINTSCOUPSREUSSIS * nombreCoupsReussis) +
               (POINTSCOUPSBLANCS * nombreCoupsBlancs) +
               (POINTPARLIGNEREUSSIE * nombreLignesReussies); 
    }
}
