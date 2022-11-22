package fabriques;

import duel.interfaces.IPaquet;
import duel.interfaces.fabriques.IFabriquePaquet;
import duel.paquet.Paquet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FabriquePaquet implements IFabriquePaquet {

    @Override
    public IPaquet fabriquePaquet(String type, Object... attributs) {
        if (attributs == null)
            throw new IllegalArgumentException("Les attributs ne sont pas définis ! ! !");

        switch (type) {
            case "paquet": {
                List<Object> list = getAttributs(attributs);
                return new Paquet((boolean) list.get(0));
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
