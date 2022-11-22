import duel.*;
import duel.carte.Carte;
import duel.coup.Coup;
import duel.joueur.Joueur;
import duel.paquet.Paquet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;


public class CoupTest {

    @Test
    public void testCoup() {
        // GIVEN
        Coup c1 = new Coup();
        Coup c2 = new Coup();

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
        Joueur j = new Joueur(Nom.NORD);
        Coup c1 = new Coup();
        // Retire toutes ses cartes
        int nbCartes = j.getMesCartes().getNbCartes();
        for (int i = 0; i < nbCartes; i++)
            j.getMesCartes().removeCarte(0);
        // Ajoute quelques cartes
        j.getMesCartes().getCartes().add(new Carte(25));
        j.getMesCartes().getCartes().add(new Carte(42));
        j.getMesCartes().getCartes().add(new Carte(55));

        // THEN
        assertFalse(c1.estUnCoupValide(j.getMesCartes()));

        // WHEN
        String cartesJouer = "25^ 42";
        String[] saisiIsValidCoup = cartesJouer.split(" ");
        c1.setCoups(new ArrayList<>(Arrays.asList(saisiIsValidCoup)));
        // THEN
        assertFalse(c1.estUnCoupValide(j.getMesCartes()));

        // WHEN
        String cartesJouer2 = "255 420";
        String[] saisieEstUnFormatValide = cartesJouer2.split(" ");
        c1.setCoups(new ArrayList<>(Arrays.asList(saisieEstUnFormatValide)));
        // THEN
        assertFalse(c1.estUnCoupValide(j.getMesCartes()));

        // WHEN
        String cartesJouer3 = "26^ 42v";
        String[] saisieIsCarteEnMain = cartesJouer3.split(" ");
        c1.setCoups(new ArrayList<>(Arrays.asList(saisieIsCarteEnMain)));
        // THEN
        assertFalse(c1.estUnCoupValide(j.getMesCartes()));

        // WHEN
        String cartesJouer4 = "25^ 25^";
        String[] saisieExisteDoublon = cartesJouer4.split(" ");
        c1.setCoups(new ArrayList<>(Arrays.asList(saisieExisteDoublon)));
        // THEN
        assertFalse(c1.estUnCoupValide(j.getMesCartes()));

        // WHEN
        String cartesJouer5 = "25^' 42^'";
        String[] saisiePlusDunCoupChezAdv = cartesJouer5.split(" ");
        c1.setCoups(new ArrayList<>(Arrays.asList(saisiePlusDunCoupChezAdv)));
        // THEN
        assertFalse(c1.estUnCoupValide(j.getMesCartes()));
    }

    @Test
    public void testCalculCartePiocher() {
        // GIVEN
        Coup c = new Coup();
        Paquet pioche = new Paquet(true);
        Paquet mesCartes = new Paquet();

        // WHEN
        String cartesJouer = "42^ 25^'";
        String[] saisie = cartesJouer.split(" ");
        c.setCoups(new ArrayList<>(Arrays.asList(saisie)));

        // THEN
        assertEquals(6, c.calculCartePiocher(pioche, mesCartes));

        // WHEN
        String cartesJouer2 = "42^ 25^";
        String[] saisie2 = cartesJouer2.split(" ");
        c.setCoups(new ArrayList<>(Arrays.asList(saisie2)));

        // THEN
        assertEquals(2, c.calculCartePiocher(pioche, mesCartes));

        // WHEN
        // Retire toutes les cartes en laissant une carte
        int nbCartes = pioche.getNbCartes();
        for (int i = 0; i < nbCartes - 1; i++)
            pioche.removeCarte(0);

        // THEN
        assertEquals(1, c.calculCartePiocher(pioche, mesCartes));

        // WHEN
        String cartesJouer3 = "42^' 25^";
        String[] saisie3 = cartesJouer3.split(" ");
        c.setCoups(new ArrayList<>(Arrays.asList(saisie3)));

        // THEN
        assertEquals(1, c.calculCartePiocher(pioche, mesCartes));
    }

    @Test
    public void testPeutEtrePoser() {
        // GIVEN
        Joueur jS = new Joueur(Nom.SUD);
        Joueur jN = new Joueur(Nom.NORD);

        // THEN
        assertTrue(jS.getCoup().peutEtrePoser(jS, jN, false));

        // WHEN
        String cartesJouer = "42^ 25^";
        String[] saisie = cartesJouer.split(" ");
        jS.getCoup().setCoups(new ArrayList<>(Arrays.asList(saisie)));
        jS.ajoute(new Carte(42));
        jS.ajoute(new Carte(25));

        // THEN
        assertFalse(jS.getCoup().peutEtrePoser(jS, jN, true));

        // WHEN
        String cartesJouer2 = "45^ 29v";
        String[] saisie2 = cartesJouer2.split(" ");
        jS.getCoup().setCoups(new ArrayList<>(Arrays.asList(saisie2)));

        // THEN
        assertFalse(jS.getCoup().peutEtrePoser(jS, jN, true));

        // WHEN
        String cartesJouer6 = "45v 29v";
        String[] saisie6 = cartesJouer6.split(" ");
        jS.getCoup().setCoups(new ArrayList<>(Arrays.asList(saisie6)));

        // THEN
        assertFalse(jS.getCoup().peutEtrePoser(jS, jN, true));

        // WHEN
        String cartesJouer8 = "42v' 25v";
        String[] saisie8 = cartesJouer8.split(" ");
        jS.getCoup().setCoups(new ArrayList<>(Arrays.asList(saisie8)));

        // THEN
        assertFalse(jS.getCoup().peutEtrePoser(jS, jN, true));

        // WHEN
        String cartesJouer3 = "42v 25v";
        String[] saisie3 = cartesJouer3.split(" ");
        jS.getCoup().setCoups(new ArrayList<>(Arrays.asList(saisie3)));
        jS.ajoute(new Carte(42));

        // THEN
        assertTrue(jS.getCoup().peutEtrePoser(jS, jN, true));

        // WHEN
        jS.getAscendant().setValeur(60);
        jN.getDescendant().setValeur(1);

        // THEN
        assertFalse(jS.getCoup().peutEtrePoser(jS, jN, true));

        // WHEN
        jS.getAscendant().setValeur(1);
        jN.getDescendant().setValeur(60);
        jS.ajoute(new Carte(42));
        jS.ajoute(new Carte(25));
        String cartesJouer10 = "42^' 25v";
        String[] saisie10 = cartesJouer10.split(" ");
        jS.getCoup().setCoups(new ArrayList<>(Arrays.asList(saisie10)));
        jN.getAscendant().setValeur(60);
        jS.getAscendant().setValeur(30);
        jS.getCoup().peutEtrePoser(jS, jN, true);

        // THEN
        assertFalse(jS.getCoup().peutEtrePoser(jS, jN, true));

        // WHEN
        String cartesJouer11 = "04^' 50v'";
        String[] saisie11 = cartesJouer11.split(" ");
        jS.getCoup().setCoups(new ArrayList<>(Arrays.asList(saisie11)));

        // THEN
        assertFalse(jS.getCoup().peutEtrePoser(jS, jN, true));

        // WHEN
        jS.ajoute(new Carte(5));
        jS.ajoute(new Carte(50));
        jN.getDescendant().setValeur(2);
        String cartesJouer12 = "05v' 50v'";
        String[] saisie12 = cartesJouer12.split(" ");
        jS.getCoup().setCoups(new ArrayList<>(Arrays.asList(saisie12)));

        // THEN
        assertTrue(jS.getCoup().peutEtrePoser(jS, jN, true));
    }
}