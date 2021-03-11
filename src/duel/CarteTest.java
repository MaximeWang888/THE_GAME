package duel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarteTest {

    @Test
    public void testCarte() {
        Carte c1 = new Carte(5);
        Carte c2 = new Carte(6);
        assertFalse(c1.toString().equals(c2.toString()));
    }

    @Test
    public void testGetValeur() {
        Carte c1 = new Carte(5);
        Carte c2 = new Carte(6);
        assertFalse(c1.getValeur().equals(c2.getValeur()));
    }

    @Test
    public void testSetValeur() {
        Carte c1 = new Carte(5);
        Carte c2 = new Carte(0);
        c2.setValeur(5);
        assertFalse(c1.equals(c2));
    }

    @Test
    public void testEstUneCartePosable() {
        Paquet pVide = new Paquet();
        for (int i = 1; i < 4; i++)
            pVide.getCartes().add(new Carte(i*3));
        Paquet pComplet = new Paquet(true);
        assertTrue(pVide.getCarte(1).estUneCartePosable(new Carte(60),new Carte(1),
                new Carte(60),new Carte(1),pComplet.getCartes(), 1));

        assertTrue(pVide.getCarte(1).estUneCartePosable(new Carte(60),new Carte(60),
                new Carte(60),new Carte(1),pComplet.getCartes(), 1));


        pVide.getCartes().add(new Carte(25));
        assertTrue(pVide.getCarte(1).estUneCartePosable(new Carte(1),new Carte(60),
                new Carte(60),new Carte(30),pComplet.getCartes(), 1));

        pVide.getCartes().add(new Carte(45));
        assertFalse(pVide.getCarte(1).estUneCartePosable(new Carte(1),new Carte(60),
                new Carte(30),new Carte(1),pComplet.getCartes(), 1));
    }
}