package duel.paquet;

import duel.carte.Carte;
import duel.interfaces.ICarte;
import duel.interfaces.IPaquet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Modélise un paquet en général
 * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public abstract class APaquet implements IPaquet {

    /** Les cartes du paquet */
    private List<ICarte> cartes;

    /**
     * Constructeur à partir d'un boolean
     * @param complet le boolean indiquant si le paquet
     *                sera rempli de 60 cartes ou non
     */
    public APaquet(boolean complet){
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
    public APaquet(){
        this(false);
    }

    @Override
    public List<ICarte> getCartes() {
        return cartes;
    }

    @Override
    public ICarte getCarte(int idx){
        return cartes.get(idx);
    }


    @Override
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
        StringBuilder tmp = new StringBuilder();
        for (ICarte c:cartes) {
            tmp.append(c).append(" ");
        }
        return tmp.toString();
    }

    @Override
    public ICarte piocher() {
        return cartes.remove(cartes.size()-1);
    }

    @Override
    public boolean estVide(){
        return cartes.isEmpty();
    }

    @Override
    public ICarte removeCarte(int idx){
        return cartes.remove(idx);
    }

    @Override
    public boolean isCartePresenteEnMain(String valeur) {
        // Le boolean indiquant si la carte est dans notre main
        boolean cartePresenteEnMain = false;

        for (int j = 0; j <= this.getNbCartes()-1 ; j++) {
            if (Integer.parseInt(valeur) == this.getCarte(j).getValeur())
                cartePresenteEnMain = true;
        }

        return cartePresenteEnMain;
    }

}
