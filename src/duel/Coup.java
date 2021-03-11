package duel;

import java.util.ArrayList;

/**
 * Modélise un coup joué par un joueur.
 * @author  Fabien Rondan, Maxime Wang
 * @version 1.0
 */
public class Coup {

    private ArrayList<String> coups;

    public Coup() {
        this.coups = new ArrayList<>();
    }

    public ArrayList<String> getCoups() {
        return coups;
    }

    public void setCoups(ArrayList<String> coups) {
        this.coups = coups;
    }


    /**
     * Calcul le nombre de cartes à piocher
     * @return le nombre de cartes à piocher
     */
    public int calculCartePiocher(Paquet maPioche, Paquet mesCartes){

        int nb = coups.get(0).length();
        for (int i = 1; i < coups.size(); i++) {
            if(nb < coups.get(i).length())
                nb = coups.get(i).length();
        }
        if(nb == 3){
            if(maPioche.getNbCartes() > 1) {
                return 2;
            }else
                return maPioche.getNbCartes();
        }else {
            int retenue = 6 - mesCartes.getNbCartes();
            if (retenue <= maPioche.getNbCartes())
                return retenue;
            else
                return maPioche.getNbCartes();
        }
    }

    public boolean estUnCoupValide(Paquet mesCartes) {
        for (int i = 0; i < coups.size(); i++) {
            String nombre = "" + coups.get(i).charAt(0) + coups.get(i).charAt(1);
            String signe = "";

            String adverse = "";
            if (!estUnFormatValide(i, signe, adverse))
                return false;
            if (!mesCartes.isCartePresenteEnMain(nombre))
                return false;
        }

        if (existeDoublon())
            return false;
        if (plusDeUnCoupChezAdversaire())
            return false;
        return estUneLongueurValide();
    }

    /**
     * Vérifie que la taille est bonne et le format aussi regarder si je joueur possède bien les cartes
     * @param i l'indice
     * @param signe le signe ( ça peut-être : ^ ou v)
     * @param adverse le symbole indiquant que la pose s'effectuera sur les cartes adverses
     * @return TRUE si c'est le bon format, FALSE dans le cas contraire
     */
    private boolean estUnFormatValide(int i, String signe, String adverse) {
        int longueurDeLaSaisie = coups.get(i).length();

        if (longueurDeLaSaisie > 2)
            signe = "" + coups.get(i).charAt(2);
        if (longueurDeLaSaisie > 3)
            adverse = "" + coups.get(i).charAt(3);

        if (!(signe.equals("v") || signe.equals("^")))
            return false;
        if (!((longueurDeLaSaisie == 3) || (longueurDeLaSaisie == 4)))
            return false;
        return (adverse.equals("'") || adverse.equals("") );
    }

    /**
     * Vérifie si le joueur a joué deux fois sur les cartes adverses
     * @return TRUE si il a joué deux cartes sur la pile adverse, FALSE
     * dans le cas contraire
     */
    private boolean plusDeUnCoupChezAdversaire() {
        int coupJouerSurLeTasAdverse = 0;
        for (int i = 0; i < coups.size() ; i++) {
            if(coups.get(i).length()>3)
                ++coupJouerSurLeTasAdverse;
            if(coupJouerSurLeTasAdverse > 1)
                return true;
        }
        return false;
    }

    /**
     * Vérifie que la même carte n'est pas été mise deux fois
     * @return TRUE si la carte est présente deux fois, FALSE
     * dans le cas contraire
     */
    private boolean existeDoublon() {
        for (int i = 0; i < coups.size() - 1; i++) {
            String mot1 = "" + coups.get(i).charAt(0) + coups.get(i).charAt(1);
            for (int j = i+1; j < coups.size(); j++) {
                String mot2 = "" + coups.get(j).charAt(0) + coups.get(j).charAt(1);
                if(mot1.equals(mot2))
                    return true;
            }
        }
        return false;
    }

    private boolean estUneLongueurValide() {
        return coups.size() >= 2 && coups.size() <= 6;
    }

    /**
     * Permet de connaître si les cartes saisies sont posables ou
     * pose les cartes saisies, autrement dit si les cartes sont posables alors
     * la fonction peutEtrePoser() sera rappeler avec pour dernier paramètre true
     * pour poser définitivement les cartes
     * @param adversaire le joueur adverse
     * @param peutDoncEtrePoser boolean décidant de la pose des cartes
     * @return TRUE si les cartes peuvent être posé, FALSE dans le cas contraire
     */
    public boolean peutEtrePoser(Joueur joueur, Joueur adversaire, boolean peutDoncEtrePoser){
        Carte mAscendant = new Carte(joueur.getAscendant().getValeur());
        Carte mDescendant = new Carte(joueur.getDescendant().getValeur());
        Carte advAscendant = new Carte(adversaire.getAscendant().getValeur());
        Carte advDescendant = new Carte(adversaire.getDescendant().getValeur());
        return checkCartesSaisie(joueur, adversaire, peutDoncEtrePoser, mAscendant,
                mDescendant, advAscendant, advDescendant);
    }

    /**
     *
     * @param adversaire
     * @param peutDoncEtrePoser
     * @param mAscendant
     * @param mDescendant
     * @param advAscendant
     * @param advDescendant
     * @return
     */
    private boolean checkCartesSaisie(Joueur joueur,Joueur adversaire, boolean peutDoncEtrePoser, Carte mAscendant,
                                      Carte mDescendant, Carte advAscendant, Carte advDescendant) {
        for(int i = 0; i < coups.size(); i++) { // 04v 05v 06v
            int nombre = Integer.parseInt(coups.get(i).substring(0,2));
            if(coups.get(i).length() <= 3){ // ton tas
                if (!dansMesPilesDeCartes(joueur, peutDoncEtrePoser, mAscendant, mDescendant, i, nombre))
                    return false;
            } else { // l'autre tas
                if (!dansSesPilesDeCartes(adversaire, joueur, peutDoncEtrePoser, advAscendant, advDescendant, i, nombre))
                    return false;
            }
        }
        return true;
    }

    /**
     *
     * @param peutDoncEtrePoser
     * @param mAscendant
     * @param mDescendant
     * @param i
     * @param nombre
     * @return
     */
    private boolean dansMesPilesDeCartes(Joueur joueur,boolean peutDoncEtrePoser, Carte mAscendant,
                                         Carte mDescendant, int i, int nombre) {
        if(coups.get(i).charAt(2) == '^'){ // carte ascendant
            if (estDansCarteAscendante(joueur, peutDoncEtrePoser, mAscendant, i, nombre))
                return true;
        }else{ // carte descendant
            if (estDansCarteDescendant(joueur, peutDoncEtrePoser, mDescendant, i, nombre))
                return true;
        }
        return false;
    }

    /**
     *
     * @param adversaire
     * @param peutDoncEtrePoser
     * @param advAscendant
     * @param advDescendant
     * @param i
     * @param nombre
     * @return
     */
    private boolean dansSesPilesDeCartes(Joueur adversaire, Joueur joueur, boolean peutDoncEtrePoser,
                                         Carte advAscendant, Carte advDescendant, int i, int nombre) {
        if (coups.get(i).charAt(2) == 'v'){ // carte descendant
            if(estDansCarteAdvDescendante(adversaire, joueur, peutDoncEtrePoser, advDescendant, i, nombre))
                return true;
        } else { // carte ascendant
            if(estDansCarteAdvAscendant(adversaire, joueur, peutDoncEtrePoser, advAscendant, i, nombre))
                return true;
        }
        return false;
    }

    /**
     *
     * @param adversaire
     * @param peutDoncEtrePoser
     * @param advAscendant
     * @param idx
     * @param nombre
     * @return
     */
    private boolean estDansCarteAdvAscendant(Joueur adversaire, Joueur joueur, boolean peutDoncEtrePoser,
                                             Carte advAscendant, int idx, int nombre) {
        if (advAscendant.getValeur() > nombre){
            advAscendant.setValeur(nombre);
            if (peutDoncEtrePoser)
                poser(coups.get(idx), adversaire.getAscendant(), joueur);
            return true;
        }
        return false;
    }

    /**
     *
     * @param adversaire
     * @param peutDoncEtrePoser
     * @param advDescendant
     * @param idx
     * @param nombre
     * @return
     */
    private boolean estDansCarteAdvDescendante(Joueur adversaire, Joueur joueur, boolean peutDoncEtrePoser,
                                               Carte advDescendant, int idx, int nombre) {
        if (advDescendant.getValeur() < nombre){
            advDescendant.setValeur(nombre);
            if (peutDoncEtrePoser)
                poser(coups.get(idx), adversaire.getDescendant(), joueur);
            return true;
        }
        return false;
    }

    /**
     *
     * @param peutDoncEtrePoser
     * @param mAscendant
     * @param idx
     * @param nombre
     * @return
     */
    private boolean estDansCarteAscendante(Joueur joueur,boolean peutDoncEtrePoser, Carte mAscendant,
                                           int idx, int nombre) {
        if((mAscendant.getValeur() < nombre || mAscendant.getValeur() - 10  == nombre)){
            mAscendant.setValeur(nombre);
            if(peutDoncEtrePoser)
                poser(coups.get(idx), joueur.getAscendant(), joueur);
            return true;
        }
        return false;
    }

    /**
     *
     * @param peutDoncEtrePoser
     * @param mDescendant
     * @param idx
     * @param nombre
     * @return
     */
    private boolean estDansCarteDescendant(Joueur joueur, boolean peutDoncEtrePoser, Carte mDescendant,
                                           int idx, int nombre) {
        if(mDescendant.getValeur() > nombre || (mDescendant.getValeur() + 10) == nombre){
            mDescendant.setValeur(nombre);
            if (peutDoncEtrePoser)
                poser(coups.get(idx), joueur.getDescendant(), joueur);
            return true;
        }
        return false;
    }

    /**
     * Permet de poser la carte sur la bonne pile et enlève la carte de la main du joueur
     * @param saisie la saisie d'une carte jouée par le joueur
     * @param sensDeLaPile le sens de la pile (ascendant, descendant, advAcs, advDesc)
     */
    private void poser(String saisie, Carte sensDeLaPile, Joueur joueur) {
        int indice = donneIdxRemove(saisie, joueur);
        sensDeLaPile.setValeur(joueur.getMesCartes().getCarte(indice).getValeur());
        joueur.getMesCartes().removeCarte(indice);
    }


    /**
     * Permet de revoyer l'indice de la carte a remove
     * @param saisie la saisie d'une carte jouée par le joueur
     * @return l'indice de la carte a remove
     */
    private int donneIdxRemove(String saisie, Joueur joueur){
        String nombre = "" + saisie.charAt(0) + saisie.charAt(1);
        for (int i = 0; i < joueur.getMesCartes().getNbCartes(); i++) {
            if(Integer.parseInt(nombre) == joueur.getMesCartes().getCarte(i).getValeur())
                return i;
        }
        throw new RuntimeException("ne doit jamais arriver ici");
    }

}
