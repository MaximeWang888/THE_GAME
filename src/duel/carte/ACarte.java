package duel.carte;

import duel.interfaces.ICarte;

public abstract class ACarte implements ICarte {

    /**
     * Numéro/valeur de la carte de 1 à 60 (1,2,3,...,60)
     */
    private int valeur;

    /**
     * Indique si une carte d'un paquet a déjà été mise dans le tas adverse
     */
    private static boolean carteMiseChezAdversaire = false;

    /**
     * Constructeur à partir d'une valeur
     * @param val la valeur de la nouvelle carte
     */
    public ACarte(int val){
        this.valeur = val;
    }

    @Override
    public int getValeur() {
        return valeur;
    }

    @Override
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Retourne un boolean qui indique si la carte est mise chez l'adversaire
     * @return True si c'est le cas, False dans le cas contraire
     */
    public static boolean isCarteMiseChezAdversaire() {
        return carteMiseChezAdversaire;
    }

    /**
     * Un boolean indiquant si la carte a déjà été mise chez l'adverversaire
     * @param value True or False
     */
    public static void setCarteMiseChezAdversaire(boolean value) {
        carteMiseChezAdversaire = value;
    }
}
