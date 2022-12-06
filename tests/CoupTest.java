import duel.*;
import duel.carte.Carte;
import duel.coup.Coup;
import duel.interfaces.ICoup;
import duel.interfaces.IJoueur;
import duel.interfaces.IPaquet;
import duel.joueur.Joueur;
import duel.paquet.Paquet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Les tests sur la classe Coup
 * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public class CoupTest {

    @Test
    public void testCoup() {
        // GIVEN
        ICoup c1 = new Coup();
        ICoup c2 = new Coup();

        // THEN
        assertEquals(c1.getCoups(), c2.getCoups());

        // WHEN
        c1.setCoups(new ArrayList<>(Collections.singleton("hello")));

        // THEN
        assertNotEquals(c1.getCoups(), c2.getCoups());
    }

    @Test
    public void testEstUnCoupValide() {
        // GIVEN

        // Retire toutes ses cartes
        // Ajoute quelques cartes

        // THEN

        // WHEN
        // THEN

        // WHEN
        // THEN

        // WHEN
        // THEN

        // WHEN
        // THEN

        // WHEN
        // THEN
    }

    @Test
    public void testCalculCartePiocher() {
        // GIVEN

        // WHEN

        // THEN

        // WHEN

        // THEN

        // WHEN
        // Retire toutes les cartes en laissant une carte

        // THEN

        // WHEN

        // THEN
    }

    @Test
    public void testPeutEtrePoser() {
        // GIVEN

        // THEN

        // WHEN


        // THEN

        // WHEN

        // THEN

        // WHEN

        // THEN

        // WHEN


        // THEN

        // WHEN

        // THEN

        // WHEN
        // THEN

        // WHEN


        // THEN

        // WHEN


        // THEN

        // WHEN


        // THEN
    }
}