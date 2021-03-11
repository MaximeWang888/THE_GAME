package duel;

import static org.junit.Assert.*;
import org.junit.Test;

public class JoueurTest {

    @Test
    public void testJoueur() {
        Joueur jN = new Joueur(Noms.NORD);
        Joueur jS = new Joueur(Noms.SUD);
        assertFalse(jN.toString().equals(jS.toString()));
    }

    @Test
    public void testGetNom() {
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);
        assertFalse(jN.getNom().equals(jS.getNom()));
    }

    @Test
    public void testGetAscendant() {
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);
        int carteAscendantNord = jN.getAscendant().getValeur();
        int carteAscendantSud = jS.getAscendant().getValeur();
        assertTrue(carteAscendantNord==carteAscendantSud && jN.getAscendant().getValeur()==1);
    }

    @Test
    public void testGetDescendant() {
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);
        assertTrue(jN.getDescendant().getValeur().equals(jS.getDescendant().getValeur())
                && jN.getDescendant().getValeur()==60);
    }

    @Test
    public void testGetNbDeMaPioche() {
        Joueur jN = new Joueur(Noms.NORD, true);
        assertTrue(jN.getNbDeMaPioche()==52);
    }

    @Test
    public void testGetNbDeMesCartes() {
        Joueur jN = new Joueur(Noms.NORD, true);
        assertTrue(jN.getNbDeMesCartes()==6);
    }

    @Test
    public void testTourDeJouer() {
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);
        assertTrue(jN.aMonTourDeJouer(jS) && jS.aMonTourDeJouer(jN));
        assertFalse(jS.aMonTourDeJouer(jN));
    }

    @Test
    public void testADesCartes() {
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);
        assertTrue(jN.aDesCartes() && jS.aDesCartes());
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