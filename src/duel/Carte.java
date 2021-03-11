package duel;

import java.util.List;

/**
 * Modélise une carte de jeu.
    * @author  Fabien Rondan, Maxime Wang
    * @version 1.0
 */
public class Carte {

    /**
     * Numéro/valeur de la carte de 1 à 60 (1,2,3,...,60)
     */
    private Integer valeur;

    /**
     * Indique si une carte d'un paquet a déjà été mise dans le tas adverse
     */
    private static boolean carteMiseChezAdversaire = false;

    /**
     * Constructeur à partir d'une valeur
     * @param val la valeur de la nouvelle carte
     */
    public Carte(int val){
        this.valeur = val;
    }

    /**
     *  Permet de savoir la valeur de la carte
     * @return la valeur de la carte
     */
    public Integer getValeur() {
        return valeur;
    }

    /**
     * Permet de fixer une nouvelle valeur à la carte
     * @param valeur la nouvelle valeur de la carte
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Un boolean indiquant si la carte a déjà été mise chez l'adverversaire
     * @param value True or False
     */
    public static void setCarteMiseChezAdversaire(boolean value) {
        carteMiseChezAdversaire = value;
    }

    /**
     * Permet d'avoir une représentation de la carte
     * sous la forme d'une chaîne de caractères.
     * @return la représentation textuelle de la carte
     */
    @Override
    public String toString() {
        return String.format("%02d", valeur);
    }

    /**
     * Permet de vérifier si c'est une carte posable
     * dans son propre tas ou dans le tas adverse
     * @param selfD la carte descendant de notre tas
     * @param selfA la carte ascendant de notre tas
     * @param adversaireD la carte descendant de l'adversaire
     * @param adversaireA la carte ascendant de l'adversaire
     * @return TRUE si c'est une carte posable sur les cartes à nous ou sur les cartes adverses,
     * FALSE dans le cas contraire
     */
    public boolean estUneCartePosable(Carte selfD, Carte selfA, Carte adversaireD, Carte adversaireA,
                                      List<Carte> mesCartes, int idxCartePiocher) {
        // dd
        if (estPosableDansLaPileANous(selfD, selfA, mesCartes, idxCartePiocher))
            return true;
        // dd
        return estPosableDansLaPileAdverse(adversaireD, adversaireA, mesCartes, idxCartePiocher);
    }

    /**
     * Permet de vérifier si une carte est posable sur nos cartes
     * @param selfD la carte descendante de notre tas
     * @param selfA la carte ascendante de notre tas
     * @param mesCartes les cartes en main
     * @param idxCartePiocher l'indice de la carte à piocher
     * @return TRUE si c'est une carte posable sur nos cartes, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansLaPileANous(Carte selfD, Carte selfA, List<Carte> mesCartes, int idxCartePiocher) {
        if(this.estPosableDansMaPileA(selfA)){
            selfA.setValeur(this.valeur);
            mesCartes.remove(idxCartePiocher);
            return true;
        }
        if(this.estPosableDansMaPileD(selfD)){
            selfD.setValeur(this.valeur);
            mesCartes.remove(idxCartePiocher);
            return true;
        }
        return false;
    }

    /**
     * Permet de connaître si une carte est posable sur les cartes adverses
     * @param adversaireD la carte descendant de l'adversaire
     * @param adversaireA la carte ascendant de l'adversaire
     * @param mesCartes les cartes en main
     * @param idxCartePiocher l'indice de la carte à piocher
     * @return TRUE si c'est une carte posable sur les cartes adverses, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansLaPileAdverse(Carte adversaireD, Carte adversaireA, List<Carte> mesCartes,
                                                int idxCartePiocher) {
        if(this.estPosableDansSaPileA(adversaireA)){
            setCarteMiseChezAdversaire(true);
            adversaireA.setValeur(this.valeur);
            mesCartes.remove(idxCartePiocher);
            return true;
        }
        if(this.estPosableDansSaPileD(adversaireD)){
            setCarteMiseChezAdversaire(true);
            adversaireD.setValeur(this.valeur);
            mesCartes.remove(idxCartePiocher);
            return true;
        }
        return false;
    }

    /**
     * Permet de savoir si une nouvelle carte est posable sur une ancienne carte ascendante
     * @param self la carte ascendante actuelle
     * @return TRUE si c'est une carte posable sur l'ancienne carte ascendante, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansMaPileA(Carte self){
       return this.valeur > self.valeur || this.valeur + 10 == self.valeur;
    }

    /**
     * Permet de savoir si une nouvelle carte est posable sur une ancienne carte descendante
     * @param self la carte descendante actuelle
     * @return TRUE si c'est une carte posable sur l'ancienne carte descendante, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansMaPileD(Carte self){
        return this.valeur < self.valeur || this.valeur - 10 == self.valeur;
    }

    /**
     * Permet de savoir si une nouvelle carte est posable sur une ancienne carte ascendante de l'adversaire
     * @param adversaireA la carte ascendante de l'adversaire actuellement
     * @return TRUE si c'est une carte posable sur l'ancienne carte ascendante de l'adversaire, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansSaPileA(Carte adversaireA) {
        return this.valeur < adversaireA.valeur && !carteMiseChezAdversaire;
    }

    /**
     * Permet de savoir si une nouvelle carte est posable sur une ancienne carte descendante de l'adversaire
     * @param adversaireD la carte descendante de l'adversaire actuellement
     * @return TRUE si c'est une carte posable sur l'ancienne carte descendante de l'adversaire, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansSaPileD(Carte adversaireD) {
        return this.valeur > adversaireD.valeur && !carteMiseChezAdversaire;
    }
}
