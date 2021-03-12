package duel;

import java.util.*;

/**
 * Modélise un joueur du jeu.
    * @author  Fabien Rondan, Maxime Wang
    * @version 1.0
 */
public class Joueur {

    /** Les cartes en main du joueur */
    private Paquet mesCartes;

    /** La pioche du joueur */
    private Paquet maPioche;

    /** La carte descendante du joueur */
    private Carte descendant;

    /** La carte ascendante du joueur */
    private Carte ascendant;

    /** Le nom du joueur */
    private Noms nom;

    /** Le coup du joueur */
    private Coup coup;

    /** Permet de savoir si c'est notre tour de jeu */
    private boolean monTourDeJouer;

    /**
     * Constructeur à partir de Noms (enumération)
     * @param nom détermine le nom du joueur
     * @param monTourDeJouer détermine la priorité du jeu
     */
    public Joueur(Noms nom, boolean monTourDeJouer){
        // Création des données de bases
        mesCartes = new Paquet();
        maPioche = new Paquet(true);
        descendant = new Carte(60);
        ascendant = new Carte(1);
        coup = new Coup();

        // Ajoute 6 cartes à ma main de cartes
        for (int i = 0; i < 6; i++){
            if (!maPioche.estVide())
                ajoute(maPioche.piocher());
        }
        // Mélange le paquet de cartes dans ma main
        mesCartes.getCartes().sort(Comparator.comparing(Carte::getValeur));
        // Passage des valeurs passés en paramètre aux attributs de la classe
        this.nom = nom;
        this.monTourDeJouer = monTourDeJouer;
    }

    /** Constructeur d'un joueur n'ayant pas la priorité de jeu */
    public Joueur(Noms nom){
        this(nom, false);
    }

    public Paquet getMesCartes() {
        return mesCartes;
    }

    public Paquet getMaPioche() {
        return maPioche;
    }

    /**
     *
     * @return
     */
    public Coup getCoup() { return coup; }

    /**
     * Annonce la carte ascendante
     * @return la carte de la pile ascendant
     */
    public Carte getAscendant() {
        return ascendant;
    }

    /**
     * Annonce la carte descendante
     * @return la carte de la pile descendant
     */
    public Carte getDescendant() {
        return descendant;
    }

    /**
     * Annonce le nombre de carte dans la pioche
     * @return le nombre de carte dans la pioche
     */
    public int getNbDeMaPioche() {
        return maPioche.getNbCartes();
    }

    /**
     * Annonce le nombre de carte dans la main
     * @return le nombre de carte dans la main
     */
    public int getNbDeMesCartes() {
        return mesCartes.getNbCartes();
    }

    /**
     * Permet de connaître le nom du joueur
     * @return le nom du Joueur
     */
    public Noms getNom() {
        return nom;
    }

    /**
     * Permet d'avoir une représentation des cartes en mains du joueur
     * sous la forme d'une chaîne de caractères.
     * @return la représentation textuelle des cartes en mains
     */
    @Override
    public String toString() {
        return "cartes " + nom + " { " + mesCartes + '}';
    }

    /**
     * Ajoute une carte à ma main
     * @param c la carte auquel on ajoute à notre main
     */
    public void ajoute(Carte c){
        mesCartes.getCartes().add(c);
    }

    /**
     * Permet de connaître à chaque début de tour de jeu si le joueur
     * possède au moins deux cartes valables pour continuer à jouer
     * @return TRUE si on peut poser aux moins deux cartes, FALSE dans le cas contraire
     */
    public boolean peutPoserDesCartes(Joueur adversaire) {
        // Si ce n'est même pas à ce joueur de joué alors il retourne directement TRUE
        if (!monTourDeJouer)
            return true;

        // Dans le cas contraire, il regarde si son paquet de cartes est posable
        return mesCartes.paquetDeCartesPosable(descendant, ascendant, adversaire.descendant, adversaire.ascendant);
    }

    /**
     * Permet de savoir à qui le tour de jouer
     * @param adversaire le joueur adversaire auquel on modifie son boolean "monTourDeJouer"
     * @return TRUE si c'est au tour du joueur, FALSE dans le cas contraire
     */
    public boolean aMonTourDeJouer(Joueur adversaire) {
        // Si c'est à mon tour de jouer se met en false et met à true l'autre joueur puis renvoi true
        if (this.monTourDeJouer){
            this.monTourDeJouer = false;
            adversaire.monTourDeJouer = true;
            return true;
        }
        // Renvoi false dans le cas contraire
        return false;
    }

    /**
     * Permet de savoir si des cartes existent dans ma main
     * @return TRUE si ma main a des cartes, FALSE dans le cas contraire
     */
    public boolean aDesCartes(){
        return !mesCartes.estVide();
    }


    /**
     * Permet de vérifier si la saisie a un format valide et si toutes les cartes peuvent être posée
     * @param saisieDesCartesJouer la saisie des cartes à jouer
     * @param adversaire le joueur adverse
     * @return TRUE si la saisie est valide, FALSE dans le cas contraire
     */
    public boolean estUneSaisiValide(String[] saisieDesCartesJouer, Joueur adversaire) {
        coup.setCoups(new ArrayList<>(Arrays.asList(saisieDesCartesJouer)));

        if (!coup.estUnCoupValide(mesCartes))
            return false;
        if (!coup.peutEtrePoser(this, adversaire, false))
            return false;
        coup.peutEtrePoser(this, adversaire,true);
        return true;
    }

}


