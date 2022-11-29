package appli;

import duel.interfaces.IJoueur;

import java.util.Scanner;

/**
 * Modélise l'application lancant une partie entre deux joueurs.
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


        // Tant que le jeu n'est pas terminé


        // Affiche le nom du gagnant à la fin du jeu

        System.exit(0);
    }

    /**
     * Joue un coup
     * @param j Le joueur qui joue son coup
     * @param jAdversaire Le joueur adverse qui ne joue pas
     */
    private static void joue(IJoueur j, IJoueur jAdversaire){
        // Dans le cas général, un affichage plus une demande de saisie au joueur


        // Dans le cas où la saisie du joueur n'est pas une saisie valide

    }

    /**
     * Permet d'avoir une réprésentation textuelle des piles des joueurs
     * @param jN le joueur NORD
     * @param jS le joueur SUD
     */
    private static void afficherLesPilesDesJoueurs(IJoueur jN, IJoueur jS) {

    }

    /**
     * Permet d'avoir une représentation du joueur gagnant
     * sous la forme d'une chaîne de caractères.
     * @param jN le joueur NORD
     * @param jS le joueur SUD
     * @return la représentation textuelle du gagnant de la partie
     */
    private static StringBuilder leGagnant(IJoueur jN, IJoueur jS) {
        return null;
    }
}



