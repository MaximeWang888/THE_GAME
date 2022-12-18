package appli;

import duel.interfaces.ICarte;
import duel.interfaces.IJoueur;
import duel.joueur.Joueur;
import duel.joueur.JoueurOrdinateur;

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
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static ArrayList<ICarte> oldCartes = new ArrayList<>();

    private static void menu() {
        String choix;
        System.out.println(ANSI_YELLOW + "Bienvenue dans “The game - le duel” \n" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "[Info] Pour une meilleure expérience de jeu," +
                " veuillez agrandir votre fenêtre de la console.\n[Info] (ou bien l'épingler sur le" +
                " côté droit de votre IDE) \n" + ANSI_RESET);
        boolean estTypeAttendu = false, estTypeAttendu2 = false;

        while (!estTypeAttendu) {
            System.out.println(ANSI_CYAN + "-------------------------------------" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "[DISPLAY] MENU " + "(faites votre choix) |" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "                                    |" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "[DISPLAY] 1 : règles du jeu         |" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "[DISPLAY] 2 : lancer une partie     |" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "[DISPLAY] 3 : quitter               |" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "-------------------------------------" + ANSI_RESET);

            System.out.print(ANSI_PURPLE + "\n[JOUER] Votre choix : " + ANSI_RESET);
            choix = sc.nextLine();

            switch (choix) {
                case "1": {
                    System.out.println(ANSI_VERT + "[SUCCESS] Votre choix " +
                            choix + " est bien enregistré " + ANSI_RESET);
                    System.out.println(ANSI_VERT + "\n---------------------------" +
                            "----------------------------------------------------" +
                            "-------------------------------------" + ANSI_RESET);
                    System.out.println(ANSI_BLUE + "[INFO] Règles du jeu : \n" + ANSI_RESET +
                            "Le jeu “The game - le duel” est un" +
                            " jeu de cartes qui se joue entre deux joueurs, \nchacun a 60 cartes " +
                            "et une pile ascendante et descendante. \n ");
                    System.out.println(
                            "Le but du jeu est de vider ses cartes mais vous gagnez aussi si votre " +
                                    "adversaire ne peut plus jouer.\n");
                    System.out.println(
                            "Chaque joueur commence avec six cartes en main et récupère entre deux et " +
                                    "six cartes selon le coup qu’il a joué.");
                    System.out.println("Il y a plusieurs règles de pose mais la principale à retenir est " +
                            "que sur le tas ascendant \nvous pouvez mettre que des cartes strictement " +
                            "croissantes et inversement sur l’autre tas.\n");
                    System.out.println(ANSI_PURPLE + "[JOUER] Comment y jouer ?" + ANSI_RESET);
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
                    System.out.println("Exemple : Si sur la pile montante de l'adversaire, il y a " +
                            "la carte 08 posée et que le joueur possède la carte 18, " +
                            "\nle joueur peut alors poser sa carte sur la pile montante de l'adversaire");
                    System.out.println("Pour poser la carte n°18 sur la pile montante de l'adversaire, il" +
                            " faut ajouter une apostrophe : 18^' ");
                    System.out.println(ANSI_VERT + "---------------------------" +
                            "----------------------------------------------------" +
                            "-------------------------------------\n" + ANSI_RESET);
                    break;
                }
                case "2": {
                    String choix2;
                    String nomJoueur1, nomJoueur2;
                    IJoueur jN = null, jS = null;

                    System.out.println(ANSI_VERT + "[SUCCESS] Votre choix " +
                            choix + " est bien enregistré \n" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "------------------------------" +
                            "---------------------------------------" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "[DISPLAY] Mode de jeu (faites votre choix)" +
                            "                          |" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "                                 " +
                            "                                   |" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "[DISPLAY] 1 : avec un(e) ami(e)   " +
                            "                                  |" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "[DISPLAY] 2 : avec un ordinateur   " +
                            "                                 |" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "-------------------------------------" +
                            "--------------------------------" + ANSI_RESET);

                    System.out.print(ANSI_PURPLE + "\n[JOUER] Votre choix : " + ANSI_RESET);
                    while (!estTypeAttendu2) {
                        choix2 = sc.nextLine();

                        switch (choix2) {
                            case "1": {
                                System.out.println(ANSI_VERT + "[SUCCESS] Votre choix " +
                                        choix2 + " est bien enregistré \n" + ANSI_RESET);
                                System.out.println(ANSI_VERT + "\n---------------------------" +
                                        "----------------------------------------------------" +
                                        "-------------------------------------" + ANSI_RESET);
                                System.out.print(ANSI_BLUE + "[INFO] La partie commence" +
                                        " (amusez-vous bien !) \n\n\n" + ANSI_RESET);

                                System.out.print(ANSI_CYAN + "[JOUER] Veuillez saisir le" +
                                        " nom du joueur n°1 : " + ANSI_RESET);
                                nomJoueur1 = sc.nextLine(); nomJoueur1 = nomJoueur1.trim();
                                System.out.println(ANSI_VERT + "[SUCCESS] Nom du joueur '" + nomJoueur1 +
                                        "' bien enregistré !\n" + ANSI_RESET);

                                System.out.print(ANSI_CYAN + "[JOUER] Veuillez saisir le" +
                                        " nom du joueur n°2 : " + ANSI_RESET);
                                nomJoueur2 = sc.nextLine(); nomJoueur2 = nomJoueur2.trim();
                                System.out.println(ANSI_VERT + "[SUCCESS] Nom du joueur '" + nomJoueur2 +
                                        "' bien enregistré !\n" + ANSI_RESET);
                                jN = new Joueur(nomJoueur1, true);
                                jS = new Joueur(nomJoueur2);
                                jeu(jN, jS);

                                sc.close();

                                // Affiche le nom du gagnant à la fin du jeu
                                System.out.println(leGagnant(jN, jS));
                                System.exit(0);
                                break;
                            }
                            case "2": {
                                System.out.println(ANSI_VERT + "[SUCCESS] Votre choix " +
                                        choix2 + " est bien enregistré \n" + ANSI_RESET);
                                System.out.println(ANSI_VERT + "\n---------------------------" +
                                        "----------------------------------------------------" +
                                        "-------------------------------------" + ANSI_RESET);
                                System.out.print(ANSI_BLUE + "[INFO] La partie commence" +
                                        " (amusez-vous bien !) \n\n\n" + ANSI_RESET);

                                System.out.print(ANSI_CYAN + "[JOUER] Veuillez saisir le" +
                                        " nom du joueur n°1 : " + ANSI_RESET);
                                nomJoueur1 = sc.nextLine(); nomJoueur1 = nomJoueur1.trim();
                                System.out.println(ANSI_VERT + "[SUCCESS] Nom du joueur '" + nomJoueur1 +
                                        "' bien enregistré !\n" + ANSI_RESET);
                                jN = new Joueur(nomJoueur1, true);
                                jS = new JoueurOrdinateur();
                                jeu(jN, jS);

                                sc.close();

                                // Affiche le nom du gagnant à la fin du jeu
                                System.out.println(leGagnant(jN, jS));
                                System.exit(0);
                                break;
                            }
                            default:
                                System.out.println(ANSI_RED + "[ERROR] Ce choix '" + choix2 +
                                        "' n'existe pas. Veuillez resaisir à nouveau. \n" + ANSI_RESET);
                                System.out.print(ANSI_PURPLE + "\n[JOUER] Votre choix : " + ANSI_RESET);
                                break;
                        }
                    }
                }
                case "3":
                    System.out.println("Nous allons quitter de l'application...");
                    System.exit(0);
                default:
                    System.out.println(ANSI_RED + "[ERROR] Ce choix '" + choix +
                            "' n'existe pas. Veuillez resaisir à nouveau. \n" + ANSI_RESET);
                    break;
            }
        }
    }

    private static void jeu(IJoueur jN, IJoueur jS) {
        // Tant que le jeu n'est pas terminé
        while ((jN.aDesCartes() && jS.aDesCartes()) &&
                (jN.peutPoserDesCartes(jS) && jS.peutPoserDesCartes(jN))) {
            oldCartes = getOldCartes(jN, jS);
            afficherLesPilesDesJoueurs(jN, jS, oldCartes);
            if (jN.aMonTourDeJouer(jS)) {
                System.out.println(ANSI_BLUE + "\n[INFO] C'est au tour de '"
                        + jN.getNom() + "' de jouer\n" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "[DISPLAY] " + jN + ANSI_RESET);
                joue(jN, jS);
            } else if (jS.aMonTourDeJouer(jN)) {
                System.out.println(ANSI_BLUE + "\n[INFO] C'est au tour de '"
                        + jS.getNom() + "' de jouer\n" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "[DISPLAY] " + jS + ANSI_RESET);
                joue(jS, jN);
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
        String[] choixTas = {"^", "v", "^'", "v'"};
        if (j.getNom() != "Ordinateur") {
            // Dans le cas général, un affichage plus une demande de saisie au joueur
            System.out.print(ANSI_PURPLE + "\n[JOUER] Faites votre jeu => " + ANSI_RESET);
            String cartesJouer = sc.nextLine();
            String[] saisieDesCartesJouer = cartesJouer.split(" ");

            // Dans le cas où la saisie du joueur n'est pas une saisie valide
            while (!j.estUneSaisiValide(saisieDesCartesJouer, jAdversaire)) {
                System.out.println(ANSI_RED + "\n[ERROR] Votre coup '" + cartesJouer +
                        "' n'est pas valide, veuillez rejouer ...\n" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "[INFO] Rappel une saisie valide comprend deux cartes à poser " +
                        "aux minimums et des espaces entre chaque cartes" + ANSI_RESET);
                System.out.println("Exemples: ");
                System.out.println("02^ 59v" + "     (si vous souhaitez jouer dans vos tas)");
                System.out.println("02^' 59v" + "     (si vous souhaitez jouer la carte 2 dans le tas de l'autre)");
                System.out.println("02^' 59v'" + "     (si vous souhaitez jouer la carte 2 " +
                        "et la carte 59 dans le tas de l'autre)");
                System.out.println(ANSI_BLUE + "[INFO] Ou bien vérifier que les cartes" +
                        " posées soient bien posables sur la(les) pile(s) voulue(s) " + ANSI_RESET);
                System.out.println("Exemples: (sur un cas de jeu concret)");
                System.out.println("^[01] v[20]" + "     (les piles sur lesquelles on pose nos cartes)");
                System.out.println("Faites votre jeu => 36v 21v" + "     (les cartes jouées)");
                System.out.println("Problème vu que ni la carte 36 et ni la carte 21 peut" +
                        " être posée \nsur la pile de carte descendante qui est actuellement à 20");
                System.out.println(ANSI_CYAN + "\n[DISPLAY] " + j + ANSI_RESET);
                System.out.print(ANSI_PURPLE + "\n[JOUER] Faites votre jeu => " + ANSI_RESET);
                cartesJouer = sc.nextLine();
                saisieDesCartesJouer = cartesJouer.split(" ");
            }
            int nbDeCarteAPiocher = getNbDeCarteAPiocher(j);
            // Trier les cartes de manière croissantes
            j.getMesCartes().getCartes().sort(Comparator.comparing(ICarte::getValeur));
            System.out.println(ANSI_VERT + "[SUCCESS] Votre jeu est bien enregistré " +
                    saisieDesCartesJouer[0] + " " + saisieDesCartesJouer[1] + ANSI_RESET);
            System.out.println(ANSI_VERT + "[SUCCESS] " + j.getCoup().getCoups().size() + " cartes posées, " +
                    nbDeCarteAPiocher + " cartes piochées " + ANSI_RESET);
        } else {
            j.getMesCartes().getCartes().sort(Comparator.comparing(ICarte::getValeur));

            List<String> saisieDesCartesJouer = new ArrayList<>();

            // je regarde si mes cartes en main et
            // si oui alors le poser au hasard dans l'ordre de choixTas
            for (String choix : choixTas) {
                for (ICarte carte : j.getMesCartes().getCartes()) {
                    saisieDesCartesJouer.add(carte + choix);
                }
            }
            joueAuHasardBot(j, jAdversaire, saisieDesCartesJouer);
            int nbDeCarteAPiocher = getNbDeCarteAPiocher(j);
            System.out.println(ANSI_VERT + "[SUCCESS] " + j.getCoup().getCoups().size() + " cartes posées, " +
                    nbDeCarteAPiocher + " cartes piochées " + ANSI_RESET);
        }
    }

    private static int getNbDeCarteAPiocher(IJoueur j) {
        // Piocher
        int nbDeCarteAPiocher = j.getCoup().calculCartePiocher(j.getMaPioche(), j.getMesCartes());
        for (int i = 0; i < nbDeCarteAPiocher; i++) {
            if (!j.getMaPioche().estVide() && j.getMesCartes().getNbCartes() < 7)
                j.getMesCartes().getCartes().add(j.getMaPioche().piocher());
        }
        return nbDeCarteAPiocher;
    }

    private static void joueAuHasardBot(IJoueur j, IJoueur jAdversaire, List<String> saisieDesCartesJouer) {
        String[] test = new String[2];
        for (int i = 0; i < saisieDesCartesJouer.size(); i++) {
            if (i == saisieDesCartesJouer.size() - 2)// c'est l'avant dernier
                break;
            else {
                test[0] = saisieDesCartesJouer.get(i);
                for (int k = 1; k < saisieDesCartesJouer.size(); k++) {
                    test[1] = saisieDesCartesJouer.get(k);
                    if (j.estUneSaisiValide(test, jAdversaire)) {
                        System.out.println(ANSI_VERT + "[SUCCESS] L'ordinateur a joué : "
                                + test[0] + " " + test[1] + ANSI_RESET);
                        return;
                    }
                    else test[1] = "";
                }
            }
        }
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

        System.out.println(ANSI_BLUE + "\n\n--------------------------------------" +
                "--------------------------------------------------------");
        System.out.print("[INFO] Présentation des piles de cartes" +
                " de chaque joueur (ascendante et descendante)\n");
        // Affichage des piles de cartes (Joueur 1)
        System.out.print(fmt.format("\n[INFO:Joueur n°1] %-15s", jN.getNom()) + " : ^[" + jN.getAscendant() + "] "
                + "v[" + jN.getDescendant() + "]");
        for (int i = 0; i < nbSpace; i++) {
            System.out.print(" ");
        }
        System.out.println("(" + jN.getNbDeMesCartes() +
                " cartes en main ET " + jN.getNbDeMaPioche() + " cartes dans ma pioche)");

        // Affichage des piles de cartes (joueur 2)
        System.out.print(fm.format("[INFO:Joueur n°2] %-15s", jS.getNom()) + " : ^[" + jS.getAscendant() + "] "
                + "v[" + jS.getDescendant() + "]");
        for (int i = 0; i < nbSpace; i++) {
            System.out.print(" ");
        }
        System.out.print("(" + jS.getNbDeMesCartes() +
                " cartes en main ET " + jS.getNbDeMaPioche() + " cartes dans ma pioche)");

        System.out.println("\n---------------------------------------" +
                "-------------------------------------------------------" + ANSI_RESET);
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
            phraseDuGagnant.append(ANSI_CYAN + "[DISPLAY] " + jS + ANSI_RESET);
            phraseDuGagnant.append("\n");
            phraseDuGagnant.append(ANSI_VERT + "[GAMEOVER] partie finie, ")
                    .append(jN.getNom() + ANSI_RESET);
        } else {
            afficherLesPilesDesJoueurs(jN, jS, oldCartes);
            phraseDuGagnant.append(ANSI_CYAN + "[DISPLAY] " + jN + ANSI_RESET);
            phraseDuGagnant.append("\n");
            phraseDuGagnant.append(ANSI_VERT + "[GAMEOVER] partie finie, ")
                    .append(jS.getNom() + ANSI_RESET);
        }

        phraseDuGagnant.append(ANSI_VERT + " a gagné" + ANSI_RESET);
        return phraseDuGagnant;
    }
}
