package fabriques;

import duel.coup.Coup;
import duel.interfaces.ICoup;
import duel.interfaces.fabriques.IFabriqueCoup;

/**
 * Modélise une fabrique d'un coup
 * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public class FabriqueCoup implements IFabriqueCoup {
    @Override
    public ICoup fabriqueCoup(String type) {
        switch (type) {
            case "coup": {
                return new Coup();
            }
            default:
                throw new IllegalArgumentException("Type n'est pas defini");
        }
    }
}
