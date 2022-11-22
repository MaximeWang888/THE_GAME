package duel.interfaces.fabriques;

import duel.interfaces.IJoueur;

public interface IFabriqueJoueur {

    IJoueur fabriqueJoueur(String type, Object... attributs);

}
