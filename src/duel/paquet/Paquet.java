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
        int nbTotalDeCartesJouables = 0;
        ICarte cartePiocher;
        List<ICarte> copieDesCartes = new ArrayList<>(getCartes());
        ICarte selfDesc = new Carte(selfD.getValeur());
        ICarte selfAscend = new Carte(selfA.getValeur());
        ICarte advDesc = new Carte(adversaireD.getValeur());
        ICarte advAscend = new Carte(adversaireA.getValeur());
        for (int idx = 0; idx < copieDesCartes.size(); idx++) {
            cartePiocher = copieDesCartes.get(idx);
            if (cartePiocher.estUneCartePosable(selfDesc, selfAscend, advDesc, advAscend, copieDesCartes, idx)){
                ++nbTotalDeCartesJouables;
                idx = -1;  // Une carte est posable alors nous allons regarder à nouveau toutes nos cartes
                resetCardsValuesToCurrentValues(selfD, selfA, adversaireD, adversaireA,
                        selfDesc, selfAscend, advDesc, advAscend);
                if (nbTotalDeCartesJouables==2){
                    Carte.setCarteMiseChezAdversaire(false);
                    return true;
                }
            }
        }
        Carte.setCarteMiseChezAdversaire(false);
        return false;
    }

    private void resetCardsValuesToCurrentValues(ICarte selfD, ICarte selfA,
                                                 ICarte adversaireD, ICarte adversaireA,
                                                 ICarte selfDesc, ICarte selfAscend,
                                                 ICarte advDesc, ICarte advAscend) {
        selfDesc.setValeur(selfD.getValeur());
        selfAscend.setValeur(selfA.getValeur());
        advDesc.setValeur(adversaireD.getValeur());
        advAscend.setValeur(adversaireA.getValeur());
    }

}
