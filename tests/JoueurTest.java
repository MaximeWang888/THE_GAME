import duel.carte.Carte;
import duel.interfaces.IJoueur;
import duel.joueur.JoueurNormal;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class JoueurTest {

    @Test
    public void testJoueur() {
        // GIVEN
        IJoueur jN = new JoueurNormal("Maxime");
        IJoueur jS = new JoueurNormal("Sana");

        // THEN
        assertNotEquals("Le contenu des joueurs doit être différent", jN.toString(), jS.toString());
    }

    @Test
    public void testGetNom() {
        // GIVEN
        IJoueur jN = new JoueurNormal("Maxime", true);
        IJoueur jS = new JoueurNormal("Sana");

        // THEN
        assertNotEquals("Les noms des joueurs doivent être différents", jN.getNom(), jS.getNom());
    }

    @Test
    public void testGetCoup() {
        // GIVEN
        IJoueur jS = new JoueurNormal("Sana");
        IJoueur jN = new JoueurNormal("Maxime");

        // THEN
        assertEquals(jS.getCoup().getCoups(), jN.getCoup().getCoups());

        // WHEN
        jS.getCoup().getCoups().add("555");

        // THEN
        assertNotEquals(jS.getCoup().getCoups(), jN.getCoup().getCoups());
    }

    @Test
    public void testGetMaPioche() {
        // GIVEN
        IJoueur jS = new JoueurNormal("Sana");
        IJoueur jN = new JoueurNormal("Maxime");

        // THEN
        assertNotEquals(jS.getMaPioche(), jN.getMaPioche());
    }

    @Test
    public void testGetAscendant() {
        // GIVEN
        IJoueur jN = new JoueurNormal("Maxime", true);
        IJoueur jS = new JoueurNormal("Sana");

        // THEN
        assertEquals("La valeur de la carte ascendente doit être identique à celle de l'adversaire" +
                "en début de partie", jN.getAscendant().getValeur(), jS.getAscendant().getValeur());
    }

    @Test
    public void testGetDescendant() {
        // GIVEN
        IJoueur jN = new JoueurNormal("Maxime", true);
        IJoueur jS = new JoueurNormal("Sana");

        // THEN
        assertEquals("La valeur de la carte descendante doit être identique à celle de l'adversaire" +
                "en début de partie", jN.getDescendant().getValeur(), jS.getDescendant().getValeur());
    }

    @Test
    public void testGetNbDeMaPioche() {
        // GIVEN
        IJoueur jN = new JoueurNormal("Maxime", true);

        // THEN
        assertEquals("Le nombre de cartes de la pioche du joueur doit être égal à 52",
                jN.getNbDeMaPioche(), 52);
    }

    @Test
    public void testGetNbDeMesCartes() {
        // GIVEN
        IJoueur jN = new JoueurNormal("Maxime", true);

        // THEN
        assertEquals("Le nombre en cartes en main du joueur doit être égal à 6",
                jN.getNbDeMesCartes(), 6);
    }

    @Test
    public void testAMonTourDeJouer() {
        // GIVEN
        IJoueur jN = new JoueurNormal("Maxime", true);
        IJoueur jS = new JoueurNormal("Sana");

        // THEN
        assertEquals(false, jS.aMonTourDeJouer(jN));
        assertEquals(true, jN.aMonTourDeJouer(jS));
        assertEquals(true, jS.aMonTourDeJouer(jN));
        assertEquals(true, jN.aMonTourDeJouer(jS));
    }

    @Test
    public void testADesCartes() {
        // GIVEN
        JoueurNormal jS = new JoueurNormal("Sana");

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
        // GIVEN
        IJoueur jN = new JoueurNormal("Maxime", true);
        IJoueur jS = new JoueurNormal("Sana");

        // THEN
        assertEquals("Début de partie donc on devrait toujours pouvoir posé des cartes",
                true, jN.peutPoserDesCartes(jS));
        assertEquals("Début de partie donc on devrait toujours pouvoir posé des cartes",
                true, jS.peutPoserDesCartes(jN));

        // WHEN
        // Retire toutes ses cartes
        int nbCartes = jS.getMesCartes().getNbCartes();
        for (int i = 0; i < nbCartes; i++)
            jS.getMesCartes().removeCarte(0);
        jN.ajoute(new Carte(40));
        jN.ajoute(new Carte(25));
        jN.ajoute(new Carte(20));

        jN.getAscendant().setValeur(60);
        jN.getDescendant().setValeur(1);

        // THEN
        assertEquals(false, jN.peutPoserDesCartes(jS));
    }

    @Test
    public void testEstUneSaisiValide() {
        // GIVEN
        JoueurNormal jN = new JoueurNormal("Maxime", true);
        JoueurNormal jS = new JoueurNormal("Sana");
        jN.ajoute(new Carte(5));
        jN.ajoute(new Carte(6));

        // WHEN
        String[] saisiPasValide = "05^' 06^".split(" ");

        // THEN
        assertEquals(false, jN.estUneSaisiValide(saisiPasValide, jS));

        // WHEN
        String[] saisiNimporteQuoi = "opfjezojfzeop dsfds".split(" ");

        // THEN
        assertEquals(false, jN.estUneSaisiValide(saisiNimporteQuoi, jS));

        // WHEN
        String[] saisiValide = "05^ 06^".split(" ");

        // THEN
        assertEquals(true, jN.estUneSaisiValide(saisiValide, jS));


    }

    @Test
    public void testAjoute() {
        // GIVEN
        JoueurNormal jN = new JoueurNormal("Maxime", true);
        JoueurNormal jS = new JoueurNormal("Sana");
        // Retire toutes ses cartes
        int nbCartes = jS.getMesCartes().getNbCartes();
        for (int i = 0; i < nbCartes; i++) {
            jN.getMesCartes().removeCarte(0);
            jS.getMesCartes().removeCarte(0);
        }

        // WHEN
        jN.ajoute(new Carte(900));
        jS.ajoute(new Carte(900));

        // THEN
        assertEquals(jS.getMesCartes().getCarte(0).getValeur(), jN.getMesCartes().getCarte(0).getValeur());
    }

}