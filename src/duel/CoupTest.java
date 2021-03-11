package duel;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CoupTest {

    @Test
    public void testCoup() {
        Coup c1 = new Coup();
        Coup c2 = new Coup();
        assertTrue(c1.getCoups().equals(c2.getCoups()));
    }

    @Test
    public void testSetCoups() {
        Coup c1 = new Coup();
        Coup c2 = new Coup();
        c1.setCoups(c2.getCoups());
        assertTrue(c1.getCoups().equals(c2.getCoups()));
    }

    @Test
    public void testEstUnCoupValide() {
        Paquet p = new Paquet();
        Coup c1 = new Coup();
        assertFalse(c1.estUnCoupValide(p));
    }

}