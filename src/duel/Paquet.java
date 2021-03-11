package duel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Modélise un paquet de cartes.
    * @author  Fabien Rondan, Maxime Wang
    * @version 1.0
 */
public class Paquet {

    /** Les cartes du paquet */
    private ArrayList<Carte> cartes;

    /**
     * Constructeur à partir d'un boolean
     * @param complet le boolean indiquant si le paquet
     *                sera rempli de 60 cartes ou non
     */
    public Paquet(boolean complet){
        cartes = new ArrayList<>();
        if (complet){
            for (int i = 2; i < 60; i++)
                cartes.add(new Carte(i));
            Collections.shuffle(cartes);
        }
    }

    /**
     * Constructeur d'un paquet vide
     */
    public Paquet(){
        this(false);
    }

    /**
     * Retourne le paquet de cartes
     * @return le paquet de cartes
     */
    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    /** Permet de connaître une carte du paquet
     * @param idx l'indice de la carte dans le paquet
     */
    public Carte getCarte(int idx){
        return cartes.get(idx);
    }


    /** Permet de connaître le nombre de Cartes dans le paquet
     * @return le nombre de Cartes
     */
    public int getNbCartes() {
        return cartes.size();
    }

    /**
     * Permet d'avoir une représentation des éléments du paquet
     * sous la forme d'une chaîne de caractères.
     * @return la représentation textuelle des éléments du paquet
     * */
    @Override
    public String toString() {
        String tmp = "";
        for (Carte c:cartes) {
            tmp += c + " " ;
        }
        return tmp;
    }

    /**
     * Piocher la première carte du paquet de cartes
     * @return la première carte du paquet de cartes ou NULL
     * si le paquet est vide
     */
    public Carte piocher() {
        return cartes.remove(cartes.size()-1);
    }

    /**
     * Vérifie si le paquet est vide
     * @return TRUE si le paquet est vide, FALSE dans le cas contraire
     */
    public boolean estVide(){
        return cartes.isEmpty();
    }

    /**
     * Retire une carte du paquet
     * @param idx l'indice de la carte à retirer dans le paquet
     * @return la carte du paquet à l'indice idx
     */
    public Carte removeCarte(int idx){
        return cartes.remove(idx);
    }

    /**
     * Vérifie les cartes du paquet si elles sont posables ou non
     * @param selfD la carte Descendante du joueur (self)
     * @param selfA la carte Ascendante du joueur (self)
     * @param adversaireD la carte Descendante du joueur adversaire
     * @param adversaireA la carte Ascendante du joueur adversaire
     * @return TRUE si 2 cartes du paquet sont posables, FALSE
     * dans le cas contraire.
     */
    public boolean paquetDeCartesPosable(Carte selfD, Carte selfA, Carte adversaireD, Carte adversaireA,
                                         Boolean monTourDeJouer){
        if(!Boolean.TRUE.equals(monTourDeJouer))
            return true;
        Carte cartePiocher;
        int nbTotalDeCartesJouables = 0;
        ArrayList<Carte> copieDesCartes = new ArrayList<>(cartes);
        Carte selfDesc = new Carte(selfD.getValeur());
        Carte selfAscend = new Carte(selfA.getValeur());
        Carte advDesc = new Carte(adversaireD.getValeur());
        Carte advAscend = new Carte(adversaireA.getValeur());
        for (int idx = 0; idx < copieDesCartes.size(); idx++) {
            cartePiocher = copieDesCartes.get(idx);
            if (cartePiocher.estUneCartePosable(selfDesc, selfAscend, advDesc, advAscend, copieDesCartes, idx)){
                ++nbTotalDeCartesJouables;
                idx = -1;
                if (nbTotalDeCartesJouables==2)
                    return true;
            }
        }
        Carte.setCarteMiseChezAdversaire(false);
        return false;
    }

    /**
     * Vérifie si le joeur possède la carte joué
     * @param nombre le nombre auquel nous vérifons s'il existe dans nos cartes
     * @return vrai si la carte est en main
     */
    public boolean isCartePresenteEnMain(String nombre) {
        boolean cartePresenteEnMain = false;
        for (int j = 0; j <= this.getNbCartes()-1 ; j++) {
            if (Integer.parseInt(nombre)== this.getCarte(j).getValeur())
                cartePresenteEnMain = true;
        }
        return cartePresenteEnMain;
    }
}
