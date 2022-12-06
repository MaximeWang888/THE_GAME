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
        IJoueur j = new Joueur(Nom.NORD);

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

        // THEN
    }

    @Test
    public void testEstVide() {
        // GIVEN

        // THEN
    }

    @Test
    public void testRemoveCarte() {
        // GIVEN

        // THEN
    }

    @Test
    public void testPaquetDeCartesPosable() {
        // GIVEN
        // Retire toutes ses cartes
        // Ajoute quelques cartes

        // THEN
    }

    @Test
    public void testIsCarteEnMain() {
        // GIVEN

        // WHEN

        // THEN
    }
}