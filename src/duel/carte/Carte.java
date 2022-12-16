package duel.carte;

import duel.interfaces.ICarte;

import java.util.List;

/**
 * Modélise une carte de jeu.
    * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
    * @version 1.1
 */
public class Carte extends ACarte {

    final int REGLEDES10 = 10;

    /**
     * Constructeur à partir d'une valeur
     *
     * @param val la valeur de la nouvelle carte
     */
    public Carte(int val) {
        super(val);
    }

    /**
     * Permet d'avoir une représentation de la carte
     * sous la forme d'une chaîne de caractères.
     * @return la représentation textuelle de la carte
     */
    @Override
    public String toString() {
        return String.format("%02d", getValeur());
    }

    @Override
    public boolean estUneCartePosable(ICarte selfD, ICarte selfA, ICarte adversaireD, ICarte adversaireA,
                                      List<ICarte> mesCartes, int idxCartePiocher) {
        if (estPosableDansLaPileANous(selfD, selfA, mesCartes, idxCartePiocher))
            return true;
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
    private boolean estPosableDansLaPileANous(ICarte selfD, ICarte selfA, List<ICarte> mesCartes, int idxCartePiocher) {
        if(this.estPosableDansMaPileA(selfA)){
            selfA.setValeur(getValeur());
            mesCartes.remove(idxCartePiocher);
            return true;
        }
        if(this.estPosableDansMaPileD(selfD)){
            selfD.setValeur(getValeur());
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
    private boolean estPosableDansLaPileAdverse(ICarte adversaireD, ICarte adversaireA, List<ICarte> mesCartes,
                                                int idxCartePiocher) {
        if(this.estPosableDansSaPileA(adversaireA)){
            setCarteMiseChezAdversaire(true);
            adversaireA.setValeur(getValeur());
            mesCartes.remove(idxCartePiocher);
            return true;
        }
        if(this.estPosableDansSaPileD(adversaireD)){
            setCarteMiseChezAdversaire(true);
            adversaireD.setValeur(getValeur());
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
    private boolean estPosableDansMaPileA(ICarte self){
       return getValeur() > self.getValeur();
    }

    /**
     * Permet de savoir si une nouvelle carte est posable sur une ancienne carte descendante
     * @param self la carte descendante actuelle
     * @return TRUE si c'est une carte posable sur l'ancienne carte descendante, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansMaPileD(ICarte self){
        return getValeur() < self.getValeur();
    }

    /**
     * Permet de savoir si une nouvelle carte est posable sur une ancienne carte ascendante de l'adversaire
     * @param adversaireA la carte ascendante de l'adversaire actuellement
     * @return TRUE si c'est une carte posable sur l'ancienne carte ascendante de l'adversaire, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansSaPileA(ICarte adversaireA) {
        return getValeur() == adversaireA.getValeur() - REGLEDES10 && !isCarteMiseChezAdversaire();
    }

    /**
     * Permet de savoir si une nouvelle carte est posable sur une ancienne carte descendante de l'adversaire
     * @param adversaireD la carte descendante de l'adversaire actuellement
     * @return TRUE si c'est une carte posable sur l'ancienne carte descendante de l'adversaire, FALSE
     * dans le cas contraire
     */
    private boolean estPosableDansSaPileD(ICarte adversaireD) {
        return getValeur() == adversaireD.getValeur() + REGLEDES10 && !isCarteMiseChezAdversaire();
    }
}
