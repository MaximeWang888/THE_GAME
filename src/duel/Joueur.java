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
        return mesCartes.paquetDeCartesPosable(descendant, ascendant,
                adversaire.descendant, adversaire.ascendant, monTourDeJouer);
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
        if (!peutEtrePoser(adversaire, false))
            return false;
        peutEtrePoser(adversaire,true);
        int nbDeCarteAPiocher = coup.calculCartePiocher(maPioche, mesCartes);
        for (int i = 0; i < nbDeCarteAPiocher; i++){
            if (!maPioche.estVide() && mesCartes.getNbCartes() < 7)
                mesCartes.getCartes().add(maPioche.piocher());
        }
        mesCartes.getCartes().sort(Comparator.comparing(Carte::getValeur));
        System.out.println( coup.getCoups().size()  + " cartes posées, " +
                nbDeCarteAPiocher + " cartes piochées ");
        return true;
    }


    /**
     * Permet de connaître si les cartes saisies sont posables ou
     * pose les cartes saisies, autrement dit si les cartes sont posables alors
     * la fonction peutEtrePoser() sera rappeler avec pour dernier paramètre true
     * pour poser définitivement les cartes
     * @param adversaire le joueur adverse
     * @param peutDoncEtrePoser boolean décidant de la pose des cartes
     * @return TRUE si les cartes peuvent être posé, FALSE dans le cas contraire
     */
    private boolean peutEtrePoser(Joueur adversaire, boolean peutDoncEtrePoser){
        Carte mAscendant = new Carte(ascendant.getValeur());
        Carte mDescendant = new Carte(descendant.getValeur());
        Carte advAscendant = new Carte(adversaire.ascendant.getValeur());
        Carte advDescendant = new Carte(adversaire.descendant.getValeur());
        return checkCartesSaisie(adversaire, peutDoncEtrePoser, mAscendant,
                mDescendant, advAscendant, advDescendant);
    }

    /**
     *
     * @param adversaire
     * @param peutDoncEtrePoser
     * @param mAscendant
     * @param mDescendant
     * @param advAscendant
     * @param advDescendant
     * @return
     */
    private boolean checkCartesSaisie(Joueur adversaire, boolean peutDoncEtrePoser, Carte mAscendant,
                                      Carte mDescendant, Carte advAscendant, Carte advDescendant) {
        for(int i = 0; i < coup.getCoups().size(); i++) { // 04v 05v 06v
            int nombre = Integer.parseInt(coup.getCoups().get(i).substring(0,2));
            if(coup.getCoups().get(i).length() <= 3){ // ton tas
                if (!dansMesPilesDeCartes(peutDoncEtrePoser, mAscendant, mDescendant, i, nombre))
                    return false;
            } else { // l'autre tas
                if (!dansSesPilesDeCartes(adversaire, peutDoncEtrePoser, advAscendant, advDescendant, i, nombre))
                    return false;
            }
        }
        return true;
    }

    /**
     *
     * @param peutDoncEtrePoser
     * @param mAscendant
     * @param mDescendant
     * @param i
     * @param nombre
     * @return
     */
    private boolean dansMesPilesDeCartes(boolean peutDoncEtrePoser, Carte mAscendant,
                                         Carte mDescendant, int i, int nombre) {
        if(coup.getCoups().get(i).charAt(2) == '^'){ // carte ascendant
            if(estDansCarteAscendante(peutDoncEtrePoser, mAscendant, i, nombre))
                return true;
        }else{ // carte descendant
            if(estDansCarteDescendant(peutDoncEtrePoser, mDescendant, i, nombre))
                return true;
        }
        return false;
    }

    /**
     *
     * @param adversaire
     * @param peutDoncEtrePoser
     * @param advAscendant
     * @param advDescendant
     * @param i
     * @param nombre
     * @return
     */
    private boolean dansSesPilesDeCartes(Joueur adversaire, boolean peutDoncEtrePoser,
                                         Carte advAscendant, Carte advDescendant, int i, int nombre) {
        if (coup.getCoups().get(i).charAt(2) == 'v'){ // carte descendant
            if(estDansCarteAdvDescendante(adversaire, peutDoncEtrePoser, advDescendant, i, nombre))
                return true;
        } else { // carte ascendant
            if(estDansCarteAdvAscendant(adversaire, peutDoncEtrePoser, advAscendant, i, nombre))
                return true;
        }
        return false;
    }

    /**
     *
     * @param adversaire
     * @param peutDoncEtrePoser
     * @param advAscendant
     * @param idx
     * @param nombre
     * @return
     */
    private boolean estDansCarteAdvAscendant(Joueur adversaire, boolean peutDoncEtrePoser,
                                          Carte advAscendant, int idx, int nombre) {
        if (advAscendant.getValeur() > nombre){
            advAscendant.setValeur(nombre);
            if (peutDoncEtrePoser)
                poser(coup.getCoups().get(idx), adversaire.ascendant);
            return true;
        }
        return false;
    }

    /**
     *
     * @param adversaire
     * @param peutDoncEtrePoser
     * @param advDescendant
     * @param idx
     * @param nombre
     * @return
     */
    private boolean estDansCarteAdvDescendante(Joueur adversaire, boolean peutDoncEtrePoser,
                                               Carte advDescendant, int idx, int nombre) {
        if (advDescendant.getValeur() < nombre){
            advDescendant.setValeur(nombre);
            if (peutDoncEtrePoser)
                poser(coup.getCoups().get(idx), adversaire.descendant);
            return true;
        }
        return false;
    }

    /**
     *
     * @param peutDoncEtrePoser
     * @param mAscendant
     * @param idx
     * @param nombre
     * @return
     */
    private boolean estDansCarteAscendante(boolean peutDoncEtrePoser, Carte mAscendant,
                                           int idx, int nombre) {
        if((mAscendant.getValeur() < nombre || mAscendant.getValeur() - 10  == nombre)){
            mAscendant.setValeur(nombre);
            if(peutDoncEtrePoser)
                poser(coup.getCoups().get(idx), ascendant);
            return true;
        }
        return false;
    }

    /**
     *
     * @param peutDoncEtrePoser
     * @param mDescendant
     * @param idx
     * @param nombre
     * @return
     */
    private boolean estDansCarteDescendant(boolean peutDoncEtrePoser, Carte mDescendant,
                                           int idx, int nombre) {
        if(mDescendant.getValeur() > nombre || (mDescendant.getValeur() + 10) == nombre){
            mDescendant.setValeur(nombre);
            if (peutDoncEtrePoser)
                poser(coup.getCoups().get(idx), descendant);
            return true;
        }
        return false;
    }

    /**
     * Permet de poser la carte sur la bonne pile et enlève la carte de la main du joueur
     * @param saisie la saisie d'une carte jouée par le joueur
     * @param sensDeLaPile le sens de la pile (ascendant, descendant, advAcs, advDesc)
     */
    private void poser(String saisie, Carte sensDeLaPile) {
        int indice = donneIdxRemove(saisie);
        sensDeLaPile.setValeur(mesCartes.getCarte(indice).getValeur());
        mesCartes.removeCarte(indice);
    }


    /**
     * Permet de revoyer l'indice de la carte a remove
     * @param saisie la saisie d'une carte jouée par le joueur
     * @return l'indice de la carte a remove
     */
    private int donneIdxRemove(String saisie){
        String nombre = "" + saisie.charAt(0) + saisie.charAt(1);
        for (int i = 0; i < this.mesCartes.getNbCartes(); i++) {
            if(Integer.parseInt(nombre) == mesCartes.getCarte(i).getValeur())
                return i;
        }
        if (true) throw new AssertionError("ne doit jamais arriver ici");
        return -5;
    }

}


