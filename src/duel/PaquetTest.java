package duel;

import static org.junit.Assert.*;
import org.junit.Test;

public class PaquetTest {

    @Test
    public void testPaquet() {
        Paquet pVide = new Paquet();
        Paquet pComplet = new Paquet(true) ;
        assertFalse(pVide.toString().equals(pComplet.toString()));
    }

    @Test
    public void testGetCarte() {
        Paquet pComplet = new Paquet(true);
        int valeur1 = pComplet.getCarte(0).getValeur();
        int valeur2 = pComplet.getCarte(1).getValeur();
        assertFalse(valeur1 == valeur2);
    }

    @Test
    public void testGetNbDeCarte() {
        Paquet pComplet = new Paquet(true);
        assertTrue(pComplet.getNbCartes()==58);
    }

    @Test
    public void testPiocher() {
        Paquet pComplet = new Paquet(true);
        Paquet pVide = new Paquet();
        assertTrue(pVide.estVide());
        int nbDeCartesAvantPioche = pComplet.getNbCartes();
        pComplet.piocher();
        assertTrue(nbDeCartesAvantPioche-1 == pComplet.getNbCartes());
    }

    @Test
    public void testEstVide() {
        Paquet pVide = new Paquet();
        assertTrue(pVide.estVide());
    }

    @Test
    public void testRemoveCarte() {
        Paquet pVide = new Paquet();
        for (int i = 1; i < 4; i++)
            pVide.getCartes().add(new Carte(i*3));
        for (int y = 0; !pVide.estVide(); y++){
            assertFalse(pVide.estVide());
            pVide.removeCarte(pVide.getNbCartes() - 1);
        }
        assertTrue(pVide.estVide());
    }

    @Test
    public void testPaquetDeCartesPosable() {
        Joueur jN = new Joueur(Noms.NORD, true);
        Joueur jS = new Joueur(Noms.SUD);
        assertTrue(jS.peutPoserDesCartes(jN));
        Paquet p = new Paquet(true);
        assertTrue(p.paquetDeCartesPosable(new Carte(5),new Carte(60),new Carte(5),new Carte(60),
                true));
        Paquet pVide = new Paquet();
        for (int i = 1; i < 4; i++)
            pVide.getCartes().add(new Carte(i*3));
        assertFalse(pVide.paquetDeCartesPosable(new Carte(1),new Carte(60),new Carte(60),new Carte(1),
                true));
        Paquet peutEtrePoser = new Paquet();
        peutEtrePoser.getCartes().add(new Carte(5));
        peutEtrePoser.getCartes().add(new Carte(6));
        String[] saisi = "55^ 99^ 80^".split(" ");
        assertFalse(jN.estUneSaisiValide(saisi, jS));
    }

}