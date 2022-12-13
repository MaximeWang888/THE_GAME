package duel.joueur;

import duel.Nom;
import duel.interfaces.IJoueur;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Modélise un joueur du jeu.
    * @author  Maxime Wang
    * @version 1.1
 */
public class Joueur extends AJoueur {

    public Joueur(String nom, boolean monTourDeJouer) {
        super(nom, monTourDeJouer);
    }

    public Joueur(String nom) {
        super(nom);
    }

    @Override
    public boolean peutPoserDesCartes(IJoueur adversaire) {
        // Si ce n'est même pas à ce joueur de joué alors il retourne directement TRUE
        if (!isMonTourDeJouer())
            return true;

        // Dans le cas contraire, il regarde si son paquet de cartes est posable
        return getMesCartes().paquetDeCartesPosable(getDescendant(), getAscendant(),
                adversaire.getDescendant(), adversaire.getAscendant());
    }

    @Override
    public boolean estUneSaisiValide(String[] saisieDesCartesJouer, IJoueur adversaire) {
        getCoup().setCoups(new ArrayList<>(Arrays.asList(saisieDesCartesJouer)));

        if (!getCoup().estUnCoupValide(getMesCartes()))
            return false;
        if (!getCoup().peutEtrePoser(this, adversaire, false))
            return false;
        getCoup().peutEtrePoser(this, adversaire,true);
        return true;
    }

}


