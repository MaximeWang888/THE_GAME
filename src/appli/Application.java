package appli;

import duel.Nom;
import duel.interfaces.ICarte;
import duel.joueur.Joueur;

import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Modélise l'application lancant une
 * partie entre deux joueurs.
    * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
    * @version 1.1
 */
public class Application {

    /** Déclare un objet et initialise avec un objet d'entrée standard prédéfini */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Creation et lancement du jeu
     */
    public static void main(String[] args) {
        // Création des joueurs NORD et SUD
        Joueur jN = new Joueur(Nom.NORD, true);
        Joueur jS = new Joueur(Nom.SUD);

        // Tant que le jeu n'est pas terminé
        while ((jN.aDesCartes() && jS.aDesCartes()) &&
                (jN.peutPoserDesCartes(jS) && jS.peutPoserDesCartes(jN))){
            afficherLesPilesDesJoueurs(jN, jS);
            if (jN.aMonTourDeJouer(jS)){
                System.out.println(jN);
                joue(jN,jS);
            }
            else if (jS.aMonTourDeJouer(jN)){
                System.out.println(jS);
                joue(jS,jN);
            }
        }
        sc.close();

        // Affiche le nom du gagnant à la fin du jeu
        System.out.println(leGagnant(jN, jS));
        System.exit(0);
    }

    /**
     * Joue un coup
     * @param j Le joueur qui joue son coup
     * @param jAdversaire Le joueur adverse qui ne joue pas
     */
    private static void joue(Joueur j, Joueur jAdversaire){
        // Dans le cas général, un affichage plus une demande de saisie au joueur
        System.out.print("> ");
        String cartesJouer = sc.nextLine();
        String[] saisieDesCartesJouer = cartesJouer.split(" ");

        // Dans le cas où la saisie du joueur n'est pas une saisie valide
        while(!j.estUneSaisiValide(saisieDesCartesJouer, jAdversaire)){
            System.out.print("#> ");
            String reJouer = sc.nextLine();
            saisieDesCartesJouer = reJouer.split(" ");
        }
        int nbDeCarteAPiocher = j.getCoup().calculCartePiocher(j.getMaPioche(), j.getMesCartes());
        for (int i = 0; i < nbDeCarteAPiocher; i++){
            if (!j.getMaPioche().estVide() && j.getMesCartes().getNbCartes() < 7)
                j.getMesCartes().getCartes().add(j.getMaPioche().piocher());
        }
        j.getMesCartes().getCartes().sort(Comparator.comparing(ICarte::getValeur));
        System.out.println( j.getCoup().getCoups().size()  + " cartes posées, " +
                nbDeCarteAPiocher + " cartes piochées ");
    }

    /**
     * Permet d'avoir une réprésentation textuelle des piles des joueurs
     * @param jN le joueur NORD
     * @param jS le joueur SUD
     */
    private static void afficherLesPilesDesJoueurs(Joueur jN, Joueur jS) {
        Formatter fmt = new Formatter();
        Formatter fm = new Formatter();

        // Affichage des piles de cartes des joueurs NORD et SUD
        System.out.println(fmt.format("%-4s", jN.getNom()) + " ^[" + jN.getAscendant() + "] "
                + "v[" + jN.getDescendant() + "] (m" + jN.getNbDeMesCartes() + "p" + jN.getNbDeMaPioche() + ")");

        System.out.println(fm.format("%-4s", jS.getNom()) + " ^[" + jS.getAscendant() + "] "
                + "v[" + jS.getDescendant() + "] (m" + jS.getNbDeMesCartes() + "p" + jS.getNbDeMaPioche() + ")");
    }

    /**
     * Permet d'avoir une représentation du joueur gagnant
     * sous la forme d'une chaîne de caractères.
     * @param jN le joueur NORD
     * @param jS le joueur SUD
     * @return la représentation textuelle du gagnant de la partie
     */
    private static StringBuilder leGagnant(Joueur jN, Joueur jS) {
        // Construit un StringBuilder pour l'affichage du gagnant de la partie
        StringBuilder phraseDuGagnant = new StringBuilder();

        if (!jN.aDesCartes() || !jS.peutPoserDesCartes(jN)){
            afficherLesPilesDesJoueurs(jN,jS);
            phraseDuGagnant.append(jS);
            phraseDuGagnant.append("\n");
            phraseDuGagnant.append("partie finie, ")
                            .append(jN.getNom());
        } else{
            afficherLesPilesDesJoueurs(jN,jS);
            phraseDuGagnant.append(jN);
            phraseDuGagnant.append("\n");
            phraseDuGagnant.append("partie finie, ")
                .append(jS.getNom());
        }

        phraseDuGagnant.append(" a gagné");
        return phraseDuGagnant;
    }
}



