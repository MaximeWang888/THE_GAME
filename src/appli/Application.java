package appli;

import duel.interfaces.ICarte;
import duel.interfaces.IJoueur;
import duel.joueur.Joueur;

import java.util.*;

/**
 * Modélise l'application lancant une partie entre deux joueurs.
 *
 * @author Maxime Wang, Sanaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public class Application {

    /** Déclare un objet et initialise avec un objet d'entrée standard prédéfini */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Creation et lancement du jeu
     */
    public static void main(String[] args) {
        menu();
    }

    // Déclaration des couleurs pour l'affichage sur terminal
    // ANSI_RESET => Pour remettre la couleur de base
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_VERT = "\u001B[32m";
    public static ArrayList<ICarte> oldCartes = new ArrayList<>();

    private static void menu() {
        String choix;
        System.out.println(ANSI_YELLOW + "Bienvenue dans “The game - le duel” \n" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Pour une meilleure expérience de jeu," +
                " veuillez agrandir votre fenêtre de la console.\n(ou bien l'épingler sur le" +
                " côté droit de votre IDE) \n" + ANSI_RESET);
        boolean estTypeAttendu = false;

        while (!estTypeAttendu) {
            System.out.println("\033[0;31m" + "MENU " + ANSI_RESET + "(faites votre choix) ");
            System.out.println("1 : règles du jeu \n");
            System.out.println("2 : lancer une partie \n");
            System.out.println("3 : quitter \n");

            System.out.print("Votre choix : ");
            choix = sc.nextLine();

            switch (choix) {
                case "1": {
                    System.out.println(ANSI_VERT + "\n---------------------------" +
                            "----------------------------------------------------" +
                            "----------------------------------------------------" +
                            "--------------------------------------" + ANSI_RESET);
                    System.out.println("Règles du jeu : \nLe jeu “The game - le duel” est un" +
                            " jeu de cartes qui se joue entre deux joueurs, chacun a 60 cartes " +
                            "et une pile ascendante et descendante. \n ");
                    System.out.println(
                            "Le but du jeu est de vider ses cartes mais vous gagnez aussi si votre " +
                                    "adversaire ne peut plus jouer.\n");
                    System.out.println(
                            "Chaque joueur commence avec six cartes en main et récupère entre deux et " +
                                    "six cartes selon le coup qu’il a joué.");
                    System.out.println("Il y a plusieurs règles de pose mais la principale et " +
                            "que sur le tas ascendant vous pouvez mettre que des cartes strictement " +
                            "croissantes et inversement sur l’autre tas.\n");
                    System.out.println(
                            "On doit jouer au minimum deux cartes minimum et au maximum six cartes\n" +
                                    "Pour jouer la carte n°2 sur la pile montante : 02^\n" +
                                    "Pour jouer la carte n°2 sur la pile descendante : 02v\n");
                    System.out.println("^[01] représente le tas de cartes montante \nv[60] " +
                            "représente le tas de cartes descendante\n");
                    System.out.println(ANSI_RED + "Cas spécial" + ANSI_RESET + " : Le joueur peut " +
                            "poser sa carte dans la adverse si et seulement si la carte du joueur" +
                            " est : \n- une dizaine au dessus si c'est une pile montante\n- " +
                            "une dizaine en dessous si c'est une pile descendante");
                    System.out.println("Exemple : Si sur la pile montante de l'adversaire il y a " +
                            "la carte 08 posée et que le joueur possède la carte 18, " +
                            "\nle joueur peut alors poser sa carte sur la pile montante de l'adversaire");
                    System.out.println("Pour poser la carte n°18 sur la pile montante de l'adversaire, il" +
                            " faut ajouter une apostrophe : 18^' ");
                    System.out.println(ANSI_VERT + "---------------------------" +
                            "----------------------------------------------------" +
                            "----------------------------------------------------" +
                            "--------------------------------------\n" + ANSI_RESET);
                    break;
                }
                case "2": {
                    String nomJoueur1, nomJoueur2;
                    System.out.println(ANSI_VERT + "\n---------------------------" +
                            "----------------------------------------------------" +
                            "----------------------------------------------------" +
                            "--------------------------------------" + ANSI_RESET);
                    System.out.print("La partie commence (amusez-vous bien !) \n\n\n");

                    System.out.print("Veuillez saisir le nom du joueur n°1 : ");
                    nomJoueur1 = sc.nextLine();
                    System.out.println("Nom du joueur '" + nomJoueur1 + "' bien enregistré !\n");

                    System.out.print("Veuillez saisir le nom du joueur n°2 : ");
                    nomJoueur2 = sc.nextLine();
                    System.out.println("Nom du joueur '" + nomJoueur2 + "' bien enregistré !\n");

                    // Création des joueurs NORD et SUD
                    IJoueur jN = new Joueur(nomJoueur1, true);
                    IJoueur jS = new Joueur(nomJoueur2);

                    // Tant que le jeu n'est pas terminé
                    while ((jN.aDesCartes() && jS.aDesCartes()) &&
                            (jN.peutPoserDesCartes(jS) && jS.peutPoserDesCartes(jN))) {
                        oldCartes = getOldCartes(jN, jS);
                        afficherLesPilesDesJoueurs(jN, jS, oldCartes);
                        if (jN.aMonTourDeJouer(jS)) {
                            System.out.println("\nC'est au tour de " + jN.getNom() + " de jouer\n");
                            System.out.println(jN);
                            joue(jN, jS);
                        } else if (jS.aMonTourDeJouer(jN)) {
                            System.out.println("\nC'est au tour de " + jS.getNom() + " de jouer\n");
                            System.out.println(jS);
                            joue(jS, jN);
                        }
                    }
                    sc.close();

                    // Affiche le nom du gagnant à la fin du jeu
                    System.out.println(leGagnant(jN, jS));
                    System.exit(0);
                }
                case "3":
                    System.out.println("Nous allons quitter de l'application...");
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    /**
     * Joue un coup
     *
     * @param j           Le joueur qui joue son coup
     * @param jAdversaire Le joueur adverse qui ne joue pas
     */
    private static void joue(IJoueur j, IJoueur jAdversaire) {
        // Dans le cas général, un affichage plus une demande de saisie au joueur
        System.out.print("\nFaites votre jeu => ");
        String cartesJouer = sc.nextLine();
        String[] saisieDesCartesJouer = cartesJouer.split(" ");

        // Dans le cas où la saisie du joueur n'est pas une saisie valide
        while (!j.estUneSaisiValide(saisieDesCartesJouer, jAdversaire)) {
            System.out.print("#> ");
            String reJouer = sc.nextLine();
            saisieDesCartesJouer = reJouer.split(" ");
        }
        int nbDeCarteAPiocher = j.getCoup().calculCartePiocher(j.getMaPioche(), j.getMesCartes());
        for (int i = 0; i < nbDeCarteAPiocher; i++) {
            if (!j.getMaPioche().estVide() && j.getMesCartes().getNbCartes() < 7)
                j.getMesCartes().getCartes().add(j.getMaPioche().piocher());
        }
        j.getMesCartes().getCartes().sort(Comparator.comparing(ICarte::getValeur));
        System.out.println(j.getCoup().getCoups().size() + " cartes posées, " +
                nbDeCarteAPiocher + " cartes piochées ");
    }

    private static ArrayList<ICarte> getOldCartes(IJoueur jN, IJoueur jS) {
        ArrayList listOldCartes = new ArrayList<ICarte>();
        listOldCartes.add(jN.getAscendant());
        listOldCartes.add(jN.getDescendant());
        listOldCartes.add(jS.getAscendant());
        listOldCartes.add(jS.getDescendant());

        return listOldCartes;
    }

    /**
     * Permet d'avoir une réprésentation textuelle des piles des joueurs
     *
     * @param jN le joueur NORD
     * @param jS le joueur SUD
     */
    private static void afficherLesPilesDesJoueurs(IJoueur jN, IJoueur jS, List<ICarte> oldCartes) {
        Formatter fmt = new Formatter();
        Formatter fm = new Formatter();
        int nbSpace = jN.getNom().length() + jS.getNom().length();

        System.out.println("\n\n-------------------------------------------------------------------------------");
        System.out.println("Présentation des piles de cartes de chaque joueur (ascendante et descendante)");

        // Affichage des piles de cartes (Joueur 1)
        System.out.print(fmt.format("\n%-10s", jN.getNom()) + " : ^[" + jN.getAscendant() + "] "
                + "v[" + jN.getDescendant() + "]");
        for (int i = 0; i < nbSpace; i++) {
            System.out.print(" ");
        }
        System.out.println("(" + jN.getNbDeMesCartes() +
                " cartes en main ET " + jN.getNbDeMaPioche() + " cartes dans ma pioche)");

        // Affichage des piles de cartes (joueur 2)
        System.out.print(fm.format("%-10s", jS.getNom()) + " : ^[" + jS.getAscendant() + "] "
                + "v[" + jS.getDescendant() + "]");
        for (int i = 0; i < nbSpace; i++) {
            System.out.print(" ");
        }
        System.out.print("(" + jS.getNbDeMesCartes() +
                " cartes en main ET " + jS.getNbDeMaPioche() + " cartes dans ma pioche)");

        System.out.println("\n\n-------------------------------------------------------------------------------");
    }

    /**
     * Permet d'avoir une représentation du joueur gagnant
     * sous la forme d'une chaîne de caractères.
     *
     * @param jN le joueur NORD
     * @param jS le joueur SUD
     * @return la représentation textuelle du gagnant de la partie
     */
    private static StringBuilder leGagnant(IJoueur jN, IJoueur jS) {
        // Construit un StringBuilder pour l'affichage du gagnant de la partie
        StringBuilder phraseDuGagnant = new StringBuilder();

        if (!jN.aDesCartes() || !jS.peutPoserDesCartes(jN)) {
            afficherLesPilesDesJoueurs(jN, jS, oldCartes);
            phraseDuGagnant.append(jS);
            phraseDuGagnant.append("\n");
            phraseDuGagnant.append("partie finie, ")
                    .append(jN.getNom());
        } else {
            afficherLesPilesDesJoueurs(jN, jS, oldCartes);
            phraseDuGagnant.append(jN);
            phraseDuGagnant.append("\n");
            phraseDuGagnant.append("partie finie, ")
                    .append(jS.getNom());
        }

        phraseDuGagnant.append(" a gagné");
        return phraseDuGagnant;
    }
}
