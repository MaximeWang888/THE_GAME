package duel.paquet;

import duel.carte.Carte;
import duel.interfaces.ICarte;

import java.util.ArrayList;
import java.util.List;

/**
 * Modélise un paquet de cartes.
    * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
    * @version 1.1
 */
public class Paquet extends APaquet {

    public Paquet(boolean complet) {
        super(complet);
    }

    public Paquet() {
        super();
    }

    @Override
    public boolean paquetDeCartesPosable(ICarte selfD, ICarte selfA, ICarte adversaireD, ICarte adversaireA){
        return true;
    }

}
