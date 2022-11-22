package fabriques;

import duel.carte.Carte;
import duel.interfaces.ICarte;
import duel.interfaces.fabriques.IFabriqueCarte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FabriqueCarte implements IFabriqueCarte {

    @Override
    public ICarte fabriqueCarte(String type, Object... attributs) {
        if (attributs == null)
            throw new IllegalArgumentException("Les attributs ne sont pas définis ! ! !");

        switch (type) {
            case "carte": {
                List<Object> list = getAttributs(attributs);
                return new Carte((int) list.get(0));
            }
            default:
                throw new IllegalArgumentException("Type n'est pas defini");
        }
    }

    /**
     * Permet d'extraire les donnees passees en parametre
     * @param attributs les attributs a extraire
     * @return une liste d'attributs
     */
    private List<Object> getAttributs(Object[] attributs) {
        return new ArrayList<>(Arrays.asList(attributs));
    }
}
