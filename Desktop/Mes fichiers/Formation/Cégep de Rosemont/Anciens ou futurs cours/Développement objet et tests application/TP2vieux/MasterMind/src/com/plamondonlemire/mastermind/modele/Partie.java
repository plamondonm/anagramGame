
package com.plamondonlemire.mastermind.modele;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Partie implements Serializable {
    
    private Grille grilleSolution;
    private Grille grilleIndice;
    private Grille grilleSelection;
    private Grille grilleJeu;
    private Joueur unJoueur;
    private int nombreCouleurs;
    private boolean couleurIdentique;
    private int ligneActuelle;

    public Partie() {
       this(10, 4, new Joueur(), 4, false);
    }
    
    public Partie(int nbreMaxLignes) {
        this(nbreMaxLignes, 4, new Joueur(), 4, false);
    }
    
    public Partie(int nbreMaxLignes, int nbreMaxColonnes) {
        this(nbreMaxLignes, nbreMaxColonnes, new Joueur(), 4, false);
    }    
    
    public Partie(int nbreMaxLignes, int nbreMaxColonnes, int nbreMaxCouleurs) {
        this(nbreMaxLignes, nbreMaxColonnes, new Joueur(), nbreMaxCouleurs, false);
    }
    
    public Partie(int nbreMaxLignes, int nbreMaxColonnes, int nbreMaxCouleurs, 
                  boolean couleurPareil) {
        this(nbreMaxLignes, nbreMaxColonnes, new Joueur(),
             nbreMaxCouleurs, couleurPareil);
    }
    
    public Partie(int nbreMaxLignes, int nbreMaxColonnes, Joueur leJoueur,
                  int nbreCouleurs, boolean couleurPareil) {

        setJoueur(leJoueur);
        grilleJeu = new Grille(nbreMaxLignes, nbreMaxColonnes);
        grilleIndice = new Grille(nbreMaxLignes, nbreMaxColonnes);
        grilleSolution = new Grille(1, nbreMaxColonnes);
        setNombreCouleurs(nbreCouleurs);
        grilleSelection = new Grille(nombreCouleurs, nbreMaxColonnes);
        couleurIdentique = nbreMaxColonnes > nombreCouleurs ? true : couleurPareil;
        ligneActuelle = 0;
    }
    
     public void setNombreCouleurs(int nbreCouleurs) {
       nombreCouleurs = (nbreCouleurs >= 2) && 
                        (nbreCouleurs <= PaletteCouleurs.maxCouleurs()) ? nbreCouleurs : 4;  
    }
    
    public int getNombreCouleurs() {
        return nombreCouleurs;
    }   
    
    public void setGrilleSolution(Grille uneGrille) {
        grilleSolution = uneGrille;
    }
    
    public Grille getGrilleSolution() {
        return grilleSolution;
    }
    
    public void setGrilleIndice(Grille uneGrille) {
        
        grilleIndice = uneGrille;
    }
    
    public Grille getGrilleIndice() {
        
        return grilleIndice;
    }    
    
    public void setGrilleJeu(Grille uneGrille) {
        
        grilleJeu = uneGrille;
    }
    
    public Grille getGrilleJeu() {
        
        return grilleJeu;
    }    

    public void setGrilleSelection(Grille uneGrille) {
        
        grilleSelection = uneGrille;
    }
    
    public Grille getGrilleSelection() {
        
        return grilleSelection;
    }     
    
    public void setJoueur(Joueur leJoueur) {
        unJoueur = leJoueur;
    }
    
    public Joueur getJoueur() {
        return unJoueur;
    }
    
    public boolean getCouleurIdentique() {
        return couleurIdentique;
    }
    
    public int getLigneActuelle() {
        return ligneActuelle;
    }
    
    public void recommencerPartie() {
        
        for(int ligne = 0; ligne < grilleIndice.getMaxLignes(); ligne++) {
            grilleIndice.modifierLigneJetons(ligne, Color.GRAY);
            grilleJeu.modifierLigneJetons(ligne, Color.GRAY);
        }
        unJoueur.remettreAZero();
        ligneActuelle = 0;
    }
    
    public void nouvellePartie() {
        
        int compteurCouleurs = 1;
        Color tabCouleurs[] = new Color[nombreCouleurs];
        Color uneCouleur;
        int indiceAleatoire1 = 0;
        int indiceAleatoire2 = 0;
        Color couleurTampon;

        unJoueur.remettreAZero();
        
        grilleSolution.initialiserGrille();
        grilleIndice.initialiserGrille();
        grilleJeu.initialiserGrille();
        grilleSelection.initialiserGrille();
        
        uneCouleur = PaletteCouleurs.genererCouleurAleatoire();
        tabCouleurs[0] = uneCouleur;
        grilleSolution.modifierLigneJetons(0, uneCouleur);

        while(compteurCouleurs < nombreCouleurs) {
            uneCouleur = PaletteCouleurs.genererCouleurAleatoire();
            if(!verifierCouleurIdentique(tabCouleurs, uneCouleur, compteurCouleurs)) {
                tabCouleurs[compteurCouleurs] = uneCouleur;    
                if((compteurCouleurs < grilleSolution.getMaxColonnes()) && (!couleurIdentique)) {
                   grilleSolution.modifierJetonXY(0, compteurCouleurs, uneCouleur);
                }
                compteurCouleurs++;
            }
        }
        
        if(couleurIdentique) {
           for(int indice = 1; indice < grilleSolution.getMaxColonnes(); indice++) {
               indiceAleatoire1 = (int)(Math.random() * tabCouleurs.length);
               uneCouleur = tabCouleurs[indiceAleatoire1];
               grilleSolution.modifierJetonXY(0,indice, uneCouleur);
           }
        }

        // Brasser les couleurs
        for(int indice = 0; indice < 100; indice++) {
           indiceAleatoire1 = (int)(Math.random() * tabCouleurs.length);
           indiceAleatoire2 = (int)(Math.random() * tabCouleurs.length);
           couleurTampon = tabCouleurs[indiceAleatoire1];
           tabCouleurs[indiceAleatoire1] = tabCouleurs[indiceAleatoire2];
           tabCouleurs[indiceAleatoire2] = couleurTampon;
        }
  
        for(int ligne = 0; ligne < nombreCouleurs; ligne++) {
            uneCouleur = tabCouleurs[ligne];          
            for(int colonne = 0; colonne < grilleSelection.getMaxColonnes(); colonne++) {
                grilleSelection.modifierJetonXY(ligne, colonne, uneCouleur);
            }
        }
    }

    private boolean verifierCouleurIdentique(Color tabCouleurs[], 
                                             Color uneCouleur,
                                             int nombreIndices) {
        
        boolean couleurTrouve = false;
        int indice = 0;
        
        while((indice < nombreIndices) && (couleurTrouve == false)) {

            if(tabCouleurs[indice].equals(uneCouleur)) {
                couleurTrouve = true;
            }
            else {
                indice++;
            }
        }
        return couleurTrouve;    
    }
    
    private int verifierCouleurPresente(Grille uneLigne) {
        
        Color[] tabSolutions = new Color[grilleSolution.getMaxColonnes()];
        Color[] tabReponses = new Color[uneLigne.getMaxColonnes()];
        int nbreCouleursSolution = 1;
        int nbreCouleursReponse = 1;
        int nombreBlancs = 0;
        Color uneCouleurSolution;
        Color uneCouleurReponse;
        
        // Enlever les doublons pour faciliter le calcul des jetons blancs.
        tabSolutions[0] = grilleSolution.getJeton(0, 0).getCouleur();
        tabReponses[0] = uneLigne.getJeton(0, 0).getCouleur();
        for(int indice = 1; indice < tabSolutions.length; indice++) {
            uneCouleurSolution = grilleSolution.getJeton(0,indice).getCouleur();
            uneCouleurReponse = uneLigne.getJeton(0,indice).getCouleur();
            if(!verifierCouleurIdentique(tabSolutions,uneCouleurSolution,nbreCouleursSolution)) {
               tabSolutions[nbreCouleursSolution] = uneCouleurSolution;
               nbreCouleursSolution++;
            }
            if(!verifierCouleurIdentique(tabReponses,uneCouleurReponse,nbreCouleursReponse)) {
               tabReponses[nbreCouleursReponse] = uneCouleurReponse;
               nbreCouleursReponse++;
            }
        }
        
        // Rechercher les jetons présents dans la solution.
        for(int indice = 0; indice < nbreCouleursReponse; indice++) {
            if(verifierCouleurIdentique(tabSolutions,tabReponses[indice], nbreCouleursSolution)) {
               nombreBlancs++;
            }
        }
        
        return nombreBlancs;
    }
    
    private int verifierCouleurReussie(Grille uneLigne) {
       
        int nombreNoirs = 0;
        
        for(int indice = 0; indice < uneLigne.getMaxColonnes(); indice++) {
            if(uneLigne.getJeton(0, indice).toEquals(grilleSolution.getJeton(0, indice))) {
                nombreNoirs++;
            }  
        }
        return nombreNoirs;
    }    
    
    public boolean soumettreReponse(Grille uneLigneReponse) {
        
        int nombreLignesReussis = 0;
        int nombreJetonsNoirs = 0;
        int nombreJetonsBlancs = 0;
        int points;
       
        if(grilleSolution.toEquals(uneLigneReponse)) {
            for(int indice = 0; indice < grilleIndice.getMaxColonnes(); indice++) {
               grilleIndice.modifierJetonXY(ligneActuelle, indice, Color.BLACK);
            }
            nombreLignesReussis = grilleJeu.getMaxLignes() - ligneActuelle;
            unJoueur.setNombreLignesReussis(nombreLignesReussis);
            nombreJetonsNoirs = nombreLignesReussis * grilleJeu.getMaxColonnes();
            unJoueur.augmenterNombreCoupsReussis(nombreJetonsNoirs);
            points = Pointage.getPointageTotal(unJoueur.getNombreCoupsReussis(),
                                               unJoueur.getNombreCoupsBlancs(),
                                               unJoueur.getNombreLignesReussis());
            unJoueur.setPoints(points);
            return true;
        }
        nombreJetonsBlancs = verifierCouleurPresente(uneLigneReponse);
        for(int indice = 0; indice < nombreJetonsBlancs; indice++) {
            grilleIndice.modifierJetonXY(ligneActuelle, indice, Color.WHITE);
        }
       
        nombreJetonsNoirs = verifierCouleurReussie(uneLigneReponse);
        nombreJetonsBlancs -= nombreJetonsNoirs;
        unJoueur.augmenterNombreCoupsReussis(nombreJetonsNoirs);
        unJoueur.augmenterNombreCoupsBlancs(nombreJetonsBlancs);
        
        for(int indice = 0; indice < nombreJetonsNoirs; indice++) {
            grilleIndice.modifierJetonXY(ligneActuelle, indice, Color.BLACK);
        }
        ligneActuelle++;
        return false;
    }
    
    public String toString() {
        
        String chaine = "";
        
        chaine = grilleSolution + " " + grilleIndice + " " + grilleJeu + " "  + 
                " " + grilleSelection + " " + unJoueur + " " +
                " " + nombreCouleurs + " " + couleurIdentique;
        return chaine;        
    } 
}
