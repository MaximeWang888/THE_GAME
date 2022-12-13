import duel.carte.Carte;
import duel.interfaces.IJoueur;
import duel.interfaces.IPaquet;
import duel.joueur.Joueur;
import duel.Nom;
import duel.paquet.Paquet;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaquetTest {

    @Test
    public void testPaquet() {
        // GIVEN
        IPaquet pVide = new Paquet();
        IPaquet pComplet = new Paquet(true);

        // THEN
        assertNotEquals("Le contenu du paquet doit être différent", pVide.toString(), pComplet.toString());
    }

    @Test
    public void testGetCarte() {
        // GIVEN
        IJoueur j = new Joueur("Sana");

        // WHEN
        j.getMesCartes().getCartes().add(new Carte(500));

        // THEN
        assertEquals("La valeur des cartes comparées doit être équivalue",
                new Carte(500).getValeur(), j.getMesCartes().getCarte(6).getValeur());
    }

    @Test
    public void testGetNbDeCarte() {
        // GIVEN
        IPaquet pComplet = new Paquet(true);

        // THEN
        assertEquals("Le nombre de cartes d'un paquet complet doit être égal à 58",
                pComplet.getNbCartes(), 58);
    }

    @Test
    public void testPiocher() {
        // GIVEN
        IPaquet p = new Paquet(true);

        // THEN
        assertNotEquals("Les cartes piochées doivent être différentes", p.piocher(), p.piocher());
    }

    @Test
    public void testEstVide() {
        // GIVEN
        IPaquet pVide = new Paquet();

        // THEN
        assertTrue(pVide.estVide());
    }

    @Test
    public void testRemoveCarte() {
        // GIVEN
        IPaquet pComplet = new Paquet(true);

        // THEN
        assertNotEquals("Les cartes retirés du paquet doivent avoir une valeur différente",
                pComplet.removeCarte(0), pComplet.removeCarte(0));
    }

    @Test
    public void testPaquetDeCartesPosable() {
        // GIVEN
        IJoueur j = new Joueur("Sana");
        // Retire toutes ses cartes
        int nbCartes = j.getMesCartes().getNbCartes();
        for (int i = 0; i < nbCartes; i++)
            j.getMesCartes().removeCarte(0);
        // Ajoute quelques cartes
        j.getMesCartes().getCartes().add(new Carte(25));
        j.getMesCartes().getCartes().add(new Carte(42));
        j.getMesCartes().getCartes().add(new Carte(55));

        // THEN
        assertEquals("Qu'une seule carte serait posable dans le tas adverse, on s'attend " +
                "qu'il nous retourne FALSE", false, j.getMesCartes().paquetDeCartesPosable(
                new Carte(1), new Carte(60), new Carte(1), new Carte(60)));
        assertEquals("\nLa carte 55 devrait normalement être posable sur sa carte ascendante et \n" +
                        "la carte 25 devrait aussi être posable sur la carte descendante du tas adverse",
                true, j.getMesCartes().paquetDeCartesPosable(new Carte(20), new Carte(50),
                        new Carte(10), new Carte(20)));
    }

    @Test
    public void testIsCarteEnMain() {
        // GIVEN
        IJoueur jV = new Joueur("Sana");

        // WHEN
        jV.ajoute(new Carte(100));
        jV.ajoute(new Carte(101));

        // THEN
        assertTrue(jV.getMesCartes().isCartePresenteEnMain("100"));
        assertTrue(jV.getMesCartes().isCartePresenteEnMain("101"));
        assertFalse(jV.getMesCartes().isCartePresenteEnMain("1001"));
    }
}