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

    public Joueur(Nom nom, boolean monTourDeJouer) {
        super(nom, monTourDeJouer);
    }

    public Joueur(Nom nom) {
        super(nom);
    }

    @Override
    public boolean peutPoserDesCartes(IJoueur adversaire) {
        // Dans le cas contraire, il regarde si son paquet de cartes est posable
        return true;
    }

    @Override
    public boolean estUneSaisiValide(String[] saisieDesCartesJouer, IJoueur adversaire) {
        return true;
    }

}


