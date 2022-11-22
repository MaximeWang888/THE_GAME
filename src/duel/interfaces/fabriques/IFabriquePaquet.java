package duel.interfaces.fabriques;

import duel.interfaces.IPaquet;

public interface IFabriquePaquet {

    IPaquet fabriquePaquet(String type, Object... attributs);

}
