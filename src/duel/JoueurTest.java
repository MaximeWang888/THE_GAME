package duel;

import static org.junit.Assert.*;
import org.junit.Test;

public class JoueurTest {

    @Test
    public void testJoueur() {
        // GIVEN
        Joueur jN = new Joueur(Noms.NORD);
        Joueur jS = new Joueur(Noms.SUD);

        // THEN
        assertNotEquals("Le contenu des joueurs doit être différent", jN.toString(), jS.toString());
    }

    @Test
    public void testGetNom() {
        // GIVEN
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);

        // THEN
        assertNotEquals("Les noms des joueurs doivent être différents", jN.toString(), jS.toString());
    }

    @Test
    public void testGetAscendant() {
        // GIVEN
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);

        // THEN
        assertEquals("La valeur de la carte ascendente doit être identique à celle de l'adversaire" +
                "en début de partie", jN.getAscendant().getValeur(), jS.getAscendant().getValeur());
    }

    @Test
    public void testGetDescendant() {
        // GIVEN
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);

        // THEN
        assertEquals("La valeur de la carte descendante doit être identique à celle de l'adversaire" +
                "en début de partie", jN.getDescendant().getValeur(), jS.getDescendant().getValeur());
    }

    @Test
    public void testGetNbDeMaPioche() {
        // GIVEN
        Joueur jN = new Joueur(Noms.NORD, true);

        // THEN
        assertEquals("Le nombre de cartes de la pioche du joueur doit être égal à 52",
                jN.getNbDeMaPioche(), 52);
    }

    @Test
    public void testGetNbDeMesCartes() {
        // GIVEN
        Joueur jN = new Joueur(Noms.NORD, true);

        // THEN
        assertEquals("Le nombre en cartes en main du joueur doit être égal à 6",
                jN.getNbDeMesCartes(), 6);
    }

    @Test
    public void testAMonTourDeJouer() {
        // GIVEN
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);

        // THEN
        assertEquals(false, jS.aMonTourDeJouer(jN));
        assertEquals(true, jN.aMonTourDeJouer(jS));
        assertEquals(true, jS.aMonTourDeJouer(jN));
        assertEquals(true, jN.aMonTourDeJouer(jS));
    }

    @Test
    public void testADesCartes() {
        // GIVEN
        Joueur jS = new Joueur(Noms.SUD);

        // THEN
        assertEquals(true, jS.aDesCartes());

        // WHEN
        // Retire toutes ses cartes
        int nbCartes = jS.getMesCartes().getNbCartes();
        for (int i = 0; i < nbCartes; i++)
            jS.getMesCartes().removeCarte(0);

        // THEN
        assertEquals("On vient de supprimer toutes ces cartes donc " +
                "il ne doit plus avoir de cartes en main", false, jS.aDesCartes());
    }

    @Test
    public void testPeutPoserDesCartes() {
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);
        assertTrue(jN.peutPoserDesCartes(jS) && jS.peutPoserDesCartes(jN));
    }

    @Test
    public void testEstUneSaisiValide() {
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);
        jN.ajoute(new Carte(5));
        jN.ajoute(new Carte(6));

        String[] saisiDoublon = "05^ 05v".split(" ");
        assertFalse(jN.estUneSaisiValide(saisiDoublon,jS));

        String[] saisiPlusUnCoupAdv = "05^' 06^'".split(" ");
        assertFalse(jN.estUneSaisiValide(saisiPlusUnCoupAdv,jS));


        String[] saisiF = "05 1515 fff 66".split(" ");
        assertFalse(jN.estUneSaisiValide(saisiF,jS));
    }

    @Test
    public void testAjoute() {
        Joueur jN = new Joueur(Noms.NORD, true);
        String avant = jN.toString();
        jN.ajoute(new Carte(99));
        String apres = jN.toString();
        assertTrue (!(avant.equals(apres)));
    }

}