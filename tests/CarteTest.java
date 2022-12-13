import duel.carte.Carte;
import duel.interfaces.ICarte;
import duel.interfaces.IJoueur;
import duel.joueur.Joueur;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Les tests sur la classe Carte
 * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public class CarteTest {

    @Test
    public void testCarte() {
        // GIVEN
        ICarte c1 = new Carte(6);

        // THEN
        assertEquals("Les cartes comparées doivent être identiques", c1.toString(), c1.toString());
    }

    @Test
    public void testGetValeur() {
        // GIVEN
        ICarte c1 = new Carte(5);
        ICarte c2 = new Carte(6);

        // THEN
        assertNotEquals("Les deux cartes ne doivent pas avoir la même valeur", c1.getValeur(), c2.getValeur());
    }

    @Test
    public void testSetValeur() {
        // GIVEN
        ICarte c1 = new Carte(5);
        ICarte c2 = new Carte(5);

        // THEN
        assertEquals("Les deux cartes doivent avoir la même valeur de carte", c1.getValeur(), c2.getValeur());

        // WHEN
        c2.setValeur(0);

        // THEN
        assertNotEquals("Les deux cartes ne doivent pas avoir la même valeur de carte",
                c1.getValeur(), c2.getValeur());
    }

    @Test
    public void testEstUneCartePosable() {
        // GIVEN
        IJoueur j = new Joueur("Maxime");
        // Retire toutes ses cartes
        int nbCartes = j.getMesCartes().getNbCartes();
        for (int i = 0; i < nbCartes; i++)
            j.getMesCartes().removeCarte(0);
        // Ajoute quelques cartes
        j.getMesCartes().getCartes().add(new Carte(25));
        j.getMesCartes().getCartes().add(new Carte(42));
        j.getMesCartes().getCartes().add(new Carte(55));

        // THEN
        assertEquals(j + "\nLa carte 55 devrait normalement être posable sur sa carte ascendante et \n" +
                        "la carte 25 devrait aussi être posable sur la carte descendante du tas adverse",
                true, j.getMesCartes().paquetDeCartesPosable(new Carte(20), new Carte(50),
                        new Carte(10), new Carte(20)));

        assertEquals("\nLa carte 55 devrait normalement être posable sur sa carte ascendante et \n" +
                        "la carte 25 devrait aussi être posable sur la carte ascendante du tas adverse",
                true, j.getMesCartes().paquetDeCartesPosable(new Carte(20), new Carte(50),
                        new Carte(56), new Carte(41)));

        assertEquals("\nLa carte 25 devrait normalement être posable sur sa carte descendante et \n" +
                        "la carte 55 devrait aussi être posable sur sa carte ascendante",
                true, j.getMesCartes().paquetDeCartesPosable(new Carte(60), new Carte(50),
                        new Carte(56), new Carte(41)));
    }
}