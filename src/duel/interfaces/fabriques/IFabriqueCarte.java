package duel.interfaces.fabriques;

import duel.interfaces.ICarte;

public interface IFabriqueCarte {

    ICarte fabriqueCarte(String type, Object... attributs);

}
