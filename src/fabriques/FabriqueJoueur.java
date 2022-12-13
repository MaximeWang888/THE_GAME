package fabriques;

import duel.Nom;
import duel.interfaces.IJoueur;
import duel.interfaces.fabriques.IFabriqueJoueur;
import duel.joueur.Joueur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Modélise une fabrique d'un joueur
 * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public class FabriqueJoueur implements IFabriqueJoueur {

    @Override
    public IJoueur fabriqueJoueur(String type, Object... attributs) {
        if (attributs == null)
            throw new IllegalArgumentException("Les attributs ne sont pas définis ! ! !");

        switch (type) {
            case "joueur": {
                List<Object> list = getAttributs(attributs);
                    return new Joueur((String) list.get(0), (boolean) list.get(1));
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
