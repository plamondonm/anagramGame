
package com.plamondonlemire.mastermind.controleur;

import com.plamondonlemire.mastermind.modele.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
// import com.plamondonlemire.mastermind.vues;

public class MasterMindGraphique extends JFrame implements ActionListener
{ 
    
    private JLabel nordOuestVide = new JLabel();
    private JLabel labelTitreNordCentre = new JLabel("Evoluer votre esprit!");
    private JLabel labelNordEstVide = new JLabel();
    private JLabel score = new JLabel("Score: ");
    private JTextField textScore = new JTextField("0",10);
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
    private JButton boutonsSelection[][];
    private Partie unePartie = new Partie();
    private JComboBox listeSelection = new JComboBox();
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
        
         JMenuBar menu = new JMenuBar();
            JMenu menuFichier = new JMenu("Fichier");
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
        inFLayoutOuest.setLayout(new GridLayout(maxLignesIndice,maxColonnesIndice,18,18));
        
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
        inFLayoutCentre.setLayout(new GridLayout(maxLignesJeu,maxColonnesJeu,18,18));
        
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
        //JPanel FLayoutEst = new JPanel(new BorderLayout());
        JPanel inFLayoutEst = new JPanel();
        inFLayoutEst.setLayout(new FlowLayout(FlowLayout.CENTER));
        
 /*       FLayoutEst.add(labelNordEst,BorderLayout.NORTH);
        FLayoutEst.add(inFLayoutEst,BorderLayout.CENTER);
        FLayoutEst.add(whiteSpace,BorderLayout.WEST);
        FLayoutEst.add(whiteSpaceOuestEst,BorderLayout.EAST);*/
        
        boutonsSelection = new JButton[maxLignesSelection][maxColonnesSelection];
            String chaine[] = new String[3] ;
            chaine[0] = new String("Bleu");

            chaine[1] = new String("Rouge");

            chaine[2] = new String("Orange");
            
            JComboBox c = new JComboBox(chaine);

        int indiceSelection = 0;
        
        int ligneActuelle = unePartie.getLigneActuelle();
                
        for (int i=0;i< maxLignesSelection; i++ ){
            for(int j = 0; j < maxColonnesSelection; j++) {
               c[indiceSelection] = new JComboBox(chaine);
               uneCouleur = unePartie.getGrilleSelection().getJeton(i, j).getCouleur();
               //boutonsSelection[i][j].setBackground(uneCouleur);
               //inFLayoutEst.add(listeSelection[i][j]);
               indiceSelection++;
            }
        }  
        this.add(FLayoutEst,BorderLayout.EAST);
        
        JPanel panneauSud = new JPanel();
        panneauSud.setLayout(new GridLayout(2,3));        
        
        JPanel esb1 = new JPanel();
        textScore.setText("" + unePartie.getJoueur().getPoints());
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
    @Override
    public void actionPerformed(ActionEvent e) {



        throw new UnsupportedOperationException("Not supported yet.");
    }
}
