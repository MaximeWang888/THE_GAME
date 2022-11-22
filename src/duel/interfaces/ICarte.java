package duel.interfaces;

import java.util.List;

public interface ICarte {

    /**
     *  Permet de savoir la valeur de la carte
     * @return la valeur de la carte
     */
    int getValeur();

    /**
     * Permet de fixer une nouvelle valeur à la carte
     * @param valeur la nouvelle valeur de la carte
     */
    void setValeur(int valeur);

    /**
     * Permet de vérifier si c'est une carte posable
     * dans son propre tas ou dans le tas adverse
     * @param selfD la carte descendant de notre tas
     * @param selfA la carte ascendant de notre tas
     * @param adversaireD la carte descendant de l'adversaire
     * @param adversaireA la carte ascendant de l'adversaire
     * @param mesCartes mes cartes en mains
     * @param idxCartePiocher l'index de la carte piocher
     * @return TRUE si c'est une carte posable sur les cartes à nous ou sur les cartes adverses,
     * FALSE dans le cas contraire
     */
    boolean estUneCartePosable(ICarte selfD, ICarte selfA, ICarte adversaireD, ICarte adversaireA,
                               List<ICarte> mesCartes, int idxCartePiocher);

}
