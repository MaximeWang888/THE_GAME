package duel.interfaces;

import java.util.List;

public interface IPaquet {

    /**
     * Retourne le paquet de cartes
     * @return le paquet de cartes
     */
    List<ICarte> getCartes();

    /** Permet de connaître une carte du paquet
     * @param idx l'indice de la carte dans le paquet
     */
    ICarte getCarte(int idx);

    /** Permet de connaître le nombre de Cartes dans le paquet
     * @return le nombre de Cartes
     */
    int getNbCartes();

    /**
     * Piocher la première carte du paquet de cartes
     * @return la première carte du paquet de cartes ou NULL
     * si le paquet est vide
     */
    ICarte piocher();

    /**
     * Vérifie si le paquet est vide
     * @return TRUE si le paquet est vide, FALSE dans le cas contraire
     */
    boolean estVide();

    /**
     * Retire une carte du paquet
     * @param idx l'indice de la carte à retirer dans le paquet
     * @return la carte du paquet à l'indice idx
     */
    ICarte removeCarte(int idx);

    /**
     * Vérifie les cartes du paquet si elles sont posables ou non
     * @param selfD la carte Descendante du joueur (self)
     * @param selfA la carte Ascendante du joueur (self)
     * @param adversaireD la carte Descendante du joueur adversaire
     * @param adversaireA la carte Ascendante du joueur adversaire
     * @return TRUE si 2 cartes du paquet sont posables, FALSE
     * dans le cas contraire.
     */
    boolean paquetDeCartesPosable(ICarte selfD, ICarte selfA, ICarte adversaireD, ICarte adversaireA);

    /**
     * Vérifie si le joueur possède dans sa main la carte jouée
     * @param valeur la valeur de la carte auquel nous vérifions s'il existe dans nos cartes
     * @return TRUE si la carte est en main, FALSE dans le cas contraire
     */
    boolean isCartePresenteEnMain(String valeur);

}
