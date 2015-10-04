
package com.plamondonlemire.mastermind.controleur;

import com.plamondonlemire.mastermind.modele.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import com.plamondonlemire.mastermind.vues;

public class MasterMindGraphique extends JFrame implements ActionListener
{ 
    private JMenuBar menu;
    private JMenu menuFichier;
    private JMenu niveau;
    private JMenuItem menuItemNouveau;
    private JMenuItem menuItemOuvrir;
    private JMenuItem menuEnregistrer;
    private JMenuItem menuEnregistrerSous;
    private JMenuItem menuFermer;
    private JMenuItem menuQuitter; 
    private JLabel nordOuestVide = new JLabel();
    private JLabel labelTitreNordCentre = new JLabel("Evoluer votre esprit!");
    private JLabel labelNordEstVide = new JLabel();
    private JLabel score = new JLabel("Score: ");
    private JTextField textScore = new JTextField("",10);
    private JButton labelNordOuest = new JButton("Grille des indices");
    private JButton labelNordCentre = new JButton("Grille de jeu");
    private JButton labelNordEst = new JButton("Selecteur de couleur");
    private JLabel labelSolution = new JLabel("Solution: ");
    private JButton boutonSolution1 = new JButton();
    private JButton boutonSolution2 = new JButton();
    private JButton boutonSolution3 = new JButton();
    private JButton boutonSolution4 = new JButton();
    private JButton recommencer = new JButton("Recommencer");
    private JButton voirSolution = new JButton("Voir solution");
    private JButton soumettreRep = new JButton("Soumettre reponse");
    private JButton boutonsSelection[];
    private Partie unePartie = new Partie();
    private Grille ligneReponse;
//    private static final MouseAdapter ecouteur;
    
    public static void main(String[] args) {
        
        new MasterMindGraphique("MasterMind");
    }
    
    public MasterMindGraphique(String titre)
    {
        super(titre);
        JLabel whiteSpace = new JLabel("      ");
        JLabel whiteSpaceOuestEst = new JLabel("  ");
        JLabel whiteSpaceEstOuest = new JLabel("  ");
        JLabel whiteSpaceOuestOuest = new JLabel("      ");
        
        JPanel panneauNord = new JPanel();
        
        menu = new JMenuBar();
        menuFichier = new JMenu("Fichier");
            JMenuItem menuItemNouveau = new JMenuItem("Nouveau");
            JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir");
            JMenuItem menuEnregistrer = new JMenuItem("Enregistrer");
            JMenuItem menuEnregistrerSous = new JMenuItem("Enregistrer sous");
            JMenuItem menuFermer = new JMenuItem("Fermer");
            JMenuItem menuQuitter = new JMenuItem("Quitter"); 
                
            panneauNord.add(menu);
            menuFichier.add(menuItemNouveau);
            menuFichier.add(menuItemOuvrir);
            menuFichier.add(menuEnregistrer);
            menuFichier.add(menuEnregistrerSous);
            menuFichier.add(menuFermer);
            menuFichier.add(menuQuitter);
            
            menuItemNouveau.addActionListener(this);
            menuItemOuvrir.addActionListener(this);
            menuEnregistrer.addActionListener(this);
            menuEnregistrerSous.addActionListener(this);
            menuFermer.addActionListener(this);
            menuQuitter.addActionListener(this);
            recommencer.addActionListener(this);
            voirSolution.addActionListener(this);
            soumettreRep.addActionListener(this);
            
            menu.add(menuFichier);

        unePartie.nouvellePartie();    
        panneauNord.setLayout(new GridLayout(1,3));
        panneauNord.add(nordOuestVide);
        JPanel titreMaster = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titreMaster.add(labelTitreNordCentre);//Evoluez votre esprit
        panneauNord.add(titreMaster);//Centre nord
        panneauNord.add(labelNordEstVide);
        //Fin du panneau nord de this du panneau nord
        this.add(panneauNord,BorderLayout.NORTH);
        
        Color uneCouleur;
        int maxLignesIndice = unePartie.getGrilleIndice().getMaxLignes();
        int maxColonnesIndice = unePartie.getGrilleIndice().getMaxColonnes();
        int indiceGrilleIndice = 0;
        JPanel FLayoutOuest = new JPanel(new BorderLayout());
        JPanel inFLayoutOuest = new JPanel();
        inFLayoutOuest.setLayout(new GridLayout(maxLignesIndice,maxColonnesIndice,10,10));
        
        FLayoutOuest.add(labelNordOuest,BorderLayout.NORTH);
        FLayoutOuest.add(inFLayoutOuest,BorderLayout.CENTER);
        FLayoutOuest.add(whiteSpaceEstOuest,BorderLayout.WEST);
        FLayoutOuest.add(whiteSpaceOuestOuest,BorderLayout.EAST);

        for (int i=0;i< maxLignesIndice; i++ ){
            for(int j = 0; j < maxColonnesIndice; j++) {
               inFLayoutOuest.add(new JButton());
               uneCouleur = unePartie.getGrilleIndice().getJeton(i, j).getCouleur(); 
               inFLayoutOuest.getComponent(indiceGrilleIndice).setBackground(uneCouleur);
               indiceGrilleIndice++;
            }
        }
        this.add(FLayoutOuest,BorderLayout.WEST);
              
        int maxLignesJeu = unePartie.getGrilleJeu().getMaxLignes();
        int maxColonnesJeu = unePartie.getGrilleJeu().getMaxColonnes();
        int indiceGrilleJeu = 0;
        JPanel FLayoutCentre = new JPanel(new BorderLayout());
        JPanel inFLayoutCentre = new JPanel();
        inFLayoutCentre.setLayout(new GridLayout(maxLignesJeu,maxColonnesJeu,10,10));
        
        FLayoutCentre.add(labelNordCentre,BorderLayout.NORTH);
        FLayoutCentre.add(inFLayoutCentre,BorderLayout.CENTER);
        FLayoutCentre.add(whiteSpace,BorderLayout.WEST);
        FLayoutCentre.add(whiteSpaceOuestEst,BorderLayout.EAST);
        
        for (int i=0;i< maxLignesJeu; i++ ){
            for(int j = 0; j < maxColonnesJeu; j++) {
                inFLayoutCentre.add(new JButton());
                uneCouleur = unePartie.getGrilleJeu().getJeton(i, j).getCouleur();
                inFLayoutCentre.getComponent(indiceGrilleJeu).setBackground(uneCouleur);
                indiceGrilleJeu++;
            }    
        }  
        this.add(FLayoutCentre,BorderLayout.CENTER);
        
        int maxLignesSelection = unePartie.getGrilleSelection().getMaxLignes();
        int maxColonnesSelection = unePartie.getGrilleSelection().getMaxColonnes();
        int maxSelection = maxLignesSelection * maxColonnesSelection;
        int indiceGrilleSelection = 0;
        JPanel FLayoutEst = new JPanel(new BorderLayout());
        JPanel inFLayoutEst = new JPanel();
        inFLayoutEst.setLayout(new GridLayout(maxLignesSelection,maxColonnesSelection,10,10));
        
        FLayoutEst.add(labelNordEst,BorderLayout.NORTH);
        FLayoutEst.add(inFLayoutEst,BorderLayout.CENTER);
        FLayoutEst.add(whiteSpace,BorderLayout.WEST);
        FLayoutEst.add(whiteSpaceOuestEst,BorderLayout.EAST);
        
        boutonsSelection = new JButton[maxSelection];
        ligneReponse = new Grille(1, unePartie.getGrilleJeu().getMaxColonnes());
                
        for (int i=0;i< maxLignesSelection; i++ ){
            for(int j = 0; j < maxColonnesSelection; j++) {
               boutonsSelection[indiceGrilleSelection] = new JButton();
               boutonsSelection[indiceGrilleSelection].addMouseListener(new MouseAdapter()
               {
                    public void mousePressed(MouseEvent e)
                    {
                        JButton bouton = (JButton) e.getSource();
                        Color uneCouleur = bouton.getBackground();

                        Rectangle unRectangle = bouton.getBounds();
                        Point unPoint = bouton.getLocation();
                        int col = unPoint.x / unRectangle.width;

                        int ligneActuelle = unePartie.getLigneActuelle();
                        int positionXY = ligneActuelle * maxColonnesSelection + col;
                        inFLayoutCentre.getComponent(positionXY).setBackground(uneCouleur);
                        ligneReponse.getJeton(0, col);
                    }
               }); 
               uneCouleur = unePartie.getGrilleSelection().getJeton(i, j).getCouleur();
               boutonsSelection[indiceGrilleSelection].setBackground(uneCouleur);
               
               inFLayoutEst.add(boutonsSelection[indiceGrilleSelection]);
               indiceGrilleSelection++;
            }
        }  
        this.add(FLayoutEst,BorderLayout.EAST);
        
        JPanel panneauSud = new JPanel();
        panneauSud.setLayout(new GridLayout(2,3));        
        
        JPanel esb1 = new JPanel();
        esb1.add(score);
        esb1.add(textScore);

        JPanel solution = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        solution.add(labelSolution);
        panneauSud.add(solution);
        JPanel grilleSolution = new JPanel();
        
        grilleSolution.setLayout(new GridLayout(1,4,10,10));
        grilleSolution.add(boutonSolution1);
        grilleSolution.add(boutonSolution2);
        grilleSolution.add(boutonSolution3);
        grilleSolution.add(boutonSolution4);
        panneauSud.add(grilleSolution);
        panneauSud.add(esb1);
        
        panneauSud.add(recommencer);
        panneauSud.add(voirSolution);
        panneauSud.add(soumettreRep);
        
        this.add(panneauSud,BorderLayout.SOUTH);

    setSize(600,450);
    setLocation(100,100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    this.setVisible(true);
}
    public void voirSolution() {
        boutonSolution1.setBackground(unePartie.getGrilleSolution().getJeton(0, 0).getCouleur());
        boutonSolution2.setBackground(unePartie.getGrilleSolution().getJeton(0, 1).getCouleur());
        boutonSolution3.setBackground(unePartie.getGrilleSolution().getJeton(0, 2).getCouleur());
        boutonSolution4.setBackground(unePartie.getGrilleSolution().getJeton(0, 3).getCouleur()); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JComponent source = (JComponent)e.getSource();
        
        if(source == recommencer) {
           // unePartie.recommencerPartie();
        }
            
        else if(source == voirSolution) {
            this.voirSolution();
        }
            
        else if(source == soumettreRep) {
           if(unePartie.soumettreReponse(ligneReponse)) {
              this.voirSolution();
              //textScore.setText(""+unePartie.getJoueur().getPoints());
           }
        }
        
        else if(source == menuItemNouveau) {
            unePartie.nouvellePartie();
        }
                
/*         else if(source == menuItemOuvrir) {
            
           // Classe Fichier avec une méthode recupererFichier qui retourne un objet Partie.
        }
                
        else if(source == menuEnregistrer) {
            // Classe Fichier avec une méthode sauvegarderFichier avec un paramètre objet Partie.
        }      
                
        else if(source == menuEnregistrerSous) {
            // Classe Fichier avec une méthode sauvegarderFichier avec un paramètre objet Partie.
        }
                
        else if(source == menuFermer) {
            ? Fermer le panneau Partie si on décide de le mettre dans la vue.
        }
        
       else if(source == debutant) {
            Partie unePartie = new Partie();
        }
            
        else if(source == intermediaire) {
            Partie unePartie = new Partie(10, 4, new Joueur(), true);
        }
        
        else if(source == expert) {
            Partie unePartie = new Partie(10, 6, new Joueur(), true);
        }*/
        
        else if(source == menuQuitter) {
            System.exit(0);
        }
    }
}
