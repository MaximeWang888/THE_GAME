package duel.interfaces;


public interface IJoueur {

    /**
     * Retourne le coup du joueur
     * @return le coup du joueur
     */
    ICoup getCoup();

    /**
     * Retourne mes cartes en main
     * @return le paquet de cartes en main
     */
    IPaquet getMesCartes();

    /**
     * Retourne ma pioche
     * @return le paquet de cartes de ma pioche
     */
    IPaquet getMaPioche();

    /**
     * Permet de connaître si c'est à mon tour de jouer
     * @return TRUE si mon tour de jouer, FALSE le tour de l'autre de jouer
     */
    boolean isMonTourDeJouer();

    /**
     * Permet d'attribuer une nouvelle valeur TRUE or FALSE pour savoir à qui le tour de jeu
     * @param monTourDeJouer le boolean qui permet de savoir à qui le tour de jouer
     */
    void setMonTourDeJouer(boolean monTourDeJouer);

    /**
     * Annonce la carte ascendante
     * @return la carte de la pile ascendant
     */
    ICarte getAscendant();

    /**
     * Annonce la carte descendante
     * @return la carte de la pile descendant
     */
    ICarte getDescendant();

    /**
     * Annonce le nombre de carte dans la pioche
     * @return le nombre de carte dans la pioche
     */
    int getNbDeMaPioche();

    /**
     * Annonce le nombre de carte dans la main
     * @return le nombre de carte dans la main
     */
    int getNbDeMesCartes();

    /**
     * Permet de connaître le nom du joueur
     * @return le nom du Joueur
     */
    String getNom();

    /**
     * Ajoute une carte à ma main
     * @param c la carte auquel on ajoute à notre main
     */
    void ajoute(ICarte c);

    /**
     * Permet de connaître à chaque début de tour de jeu si le joueur
     * possède au moins deux cartes valables pour continuer à jouer
     * @return TRUE si on peut poser aux moins deux cartes, FALSE dans le cas contraire
     */
    boolean peutPoserDesCartes(IJoueur adversaire);

    /**
     * Permet de savoir à qui le tour de jouer
     * @param adversaire le joueur adversaire auquel on modifie son boolean "monTourDeJouer"
     * @return TRUE si c'est au tour du joueur, FALSE dans le cas contraire
     */
    boolean aMonTourDeJouer(IJoueur adversaire);

    /**
     * Permet de savoir si des cartes existent dans ma main
     * @return TRUE si ma main a des cartes, FALSE dans le cas contraire
     */
    boolean aDesCartes();

    /**
     * Permet de vérifier si la saisie a un format valide et si toutes les cartes peuvent être posée
     * @param saisieDesCartesJouer la saisie des cartes à jouer
     * @param adversaire le joueur adverse
     * @return TRUE si la saisie est valide, FALSE dans le cas contraire
     */
    boolean estUneSaisiValide(String[] saisieDesCartesJouer, IJoueur adversaire);

}
