package duel.coup;

import duel.interfaces.ICoup;
import duel.interfaces.IPaquet;

import java.util.ArrayList;
import java.util.List;

/**
 * Modélise un coup en général
 * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public abstract class ACoup implements ICoup
{
    /** La liste de coup (par ex: "05v 04") */
    private List<String> coups;

    /**
     * Constructeur qui créé une liste de coup vide
     */
    public ACoup() {
        this.coups = new ArrayList<>();
    }

    @Override
    public List<String> getCoups() {
        return coups;
    }

    @Override
    public void setCoups(List<String> coups) {
        this.coups = coups;
    }

    @Override
    public int calculCartePiocher(IPaquet maPioche, IPaquet mesCartes){

        int nb = getCoups().get(0).length();
        for (int i = 1; i < getCoups().size(); i++) {
            if(nb < getCoups().get(i).length())
                nb = getCoups().get(i).length();
        }
        if(nb == 3){
            if(maPioche.getNbCartes() > 1) {
                return 2;
            }else
                return maPioche.getNbCartes();
        }else {
            int nbDeCartesAPiocher = 6 - mesCartes.getNbCartes();
            if (nbDeCartesAPiocher <= maPioche.getNbCartes())
                return nbDeCartesAPiocher;
            else
                return maPioche.getNbCartes();
        }
    }

}
