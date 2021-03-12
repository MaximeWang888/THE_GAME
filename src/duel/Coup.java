package duel;

import java.util.ArrayList;

/**
 * Modélise un coup joué par un joueur.
 * @author  Fabien Rondan, Maxime Wang
 * @version 1.0
 */
public class Coup {

    /** La liste de coup (par ex: "05v 04") */
    private ArrayList<String> coups;

    /**
     * Constructeur qui créé une liste de coup vide
     */
    public Coup() {
        this.coups = new ArrayList<>();
    }

    /**
     * Retourne la liste de coup
     * @return la liste de coup
     */
    public ArrayList<String> getCoups() {
        return coups;
    }

    /**
     * Permet de fixer le coup
     * @param coups le nouveau coup
     */
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
            int nbDeCartesAPiocher = 6 - mesCartes.getNbCartes();
            if (nbDeCartesAPiocher <= maPioche.getNbCartes())
                return nbDeCartesAPiocher;
            else
                return maPioche.getNbCartes();
        }
    }

    public boolean estUnCoupValide(Paquet mesCartes) {
        for (int i = 0; i < coups.size(); i++) {
            if (!(2 < coups.get(i).length() && coups.get(i).length() < 5))
                return false;
            String nombre = "" + coups.get(i).charAt(0) + coups.get(i).charAt(1);

            if (!estUnFormatValide(i))
                return false;
            if (!mesCartes.isCartePresenteEnMain(nombre))
                return false;
        }

        if (existeDoublon())
            return false;
        if (plusDeUnCoupChezAdversaire())
            return false;
        return coups.size() >= 2 && coups.size() <= 6;
    }

    /**
     * Vérifie que la taille est bonne et le format aussi regarder si je joueur possède bien les cartes
     * @param i l'indice
     * @return TRUE si c'est le bon format, FALSE dans le cas contraire
     */
    private boolean estUnFormatValide(int i) {
        String signe;
        String adverse = "";
        int longueurDeLaSaisie = coups.get(i).length();
        signe = "" + coups.get(i).charAt(2);
        if (longueurDeLaSaisie > 3) {
            adverse = "" + coups.get(i).charAt(3);
        }

        if (!(signe.equals("v") || signe.equals("^"))) {
            return false;
        }
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
            if(coups.get(i).length()==4)
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



    /**
     * Permet de connaître si les cartes saisies sont posables ou
     * pose les cartes saisies, autrement dit si les cartes sont posables alors
     * la fonction peutEtrePoser() sera rappeler avec pour dernier paramètre true
     * pour poser définitivement les cartes
     * @param joueur le joueur actuelle
     * @param adversaire le joueur adverse
     * @param peutDoncEtrePoser boolean décidant de la pose des cartes
     * @return TRUE si les cartes peuvent être posé, FALSE dans le cas contraire
     */
    public boolean peutEtrePoser(Joueur joueur, Joueur adversaire, boolean peutDoncEtrePoser){
        Carte mAscendant = new Carte(joueur.getAscendant().getValeur());
        Carte mDescendant = new Carte(joueur.getDescendant().getValeur());
        Carte advAscendant = new Carte(adversaire.getAscendant().getValeur());
        Carte advDescendant = new Carte(adversaire.getDescendant().getValeur());

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
     * Permet de tester si les cartes qui ont été joué sont bien posables sur la piles qui a été choisie
     * @param joueur jouer qui joue
     * @param peutDoncEtrePoser boolean qui permet de choisir si on remplace la pile avec des copie ou avec les vrai valeurs
     * @param mAscendant la pile ascendant du joueur qui joue
     * @param mDescendant la pile descendant du joueur qui joue
     * @param i indice du coup qui est a tester
     * @param nombre valeur de la carte
     * @return true si la carte et posable sinon return false
     */
    private boolean dansMesPilesDeCartes(Joueur joueur,boolean peutDoncEtrePoser, Carte mAscendant,
                                         Carte mDescendant, int i , int nombre) {
        if (coups.get(i).charAt(2) == '^'){ // carte ascendant
            if((mAscendant.getValeur() < nombre || mAscendant.getValeur() - 10  == nombre)){
                mAscendant.setValeur(nombre);
                if(peutDoncEtrePoser){
                    int idx = donneIdxRemove(coups.get(i), joueur);
                    if (idx == -1)
                        return false;
                    poser(idx, joueur.getAscendant(), joueur);
                }
                return true;
            }
            return false;
        }else{ // carte descendant
            if(mDescendant.getValeur() > nombre || (mDescendant.getValeur() + 10) == nombre){
                mDescendant.setValeur(nombre);
                if (peutDoncEtrePoser){
                    int idx = donneIdxRemove(coups.get(i), joueur);
                    if (idx == -1)
                        return false;
                    poser(idx, joueur.getDescendant(), joueur);
                }
                return true;
            }
            return false;
        }
    }

    /**
     * Permet de tester si les cartes qui ont été joué sont bien posables sur la piles qui a été choisie
     * @param adversaire le joueur adversaire
     * @param joueur jouer qui joue
     * @param peutDoncEtrePoser boolean qui permet de choisir si on remplace la pile avec des copie ou avec les vrai valeurs
     * @param advAscendant la pile ascendant du joueur adverse
     * @param advDescendant la pile descendant du joueur adverse
     * @param i indice du coup qui est a tester
     * @param nombre valeur de la carte
     * @return true si la carte et posable sinon return false
     */
    private boolean dansSesPilesDeCartes(Joueur adversaire, Joueur joueur, boolean peutDoncEtrePoser,
                                         Carte advAscendant, Carte advDescendant, int i, int nombre) {
        if (coups.get(i).charAt(2) == 'v'){ // carte descendant
            if (advDescendant.getValeur() < nombre){
                advDescendant.setValeur(nombre);
                if (peutDoncEtrePoser){
                    int idx = donneIdxRemove(coups.get(i), joueur);
                    if (idx == -1)
                        return false;
                    poser(idx, adversaire.getDescendant(), joueur);
                }
                return true;
            }
            return false;
        } else { // carte ascendant
            if (advAscendant.getValeur() > nombre){
                advAscendant.setValeur(nombre);
                if (peutDoncEtrePoser){
                    int idx = donneIdxRemove(coups.get(i), joueur);
                    if (idx == -1)
                        return false;
                    poser(idx, adversaire.getAscendant(), joueur);
                }
                return true;
            }
            return false;
        }
    }


    /**
     * Permet de poser la carte sur la bonne pile et enlève la carte de la main du joueur
     * @param indice l'indice de la carte
     * @param sensDeLaPile le sens de la pile (ascendant, descendant, advAcs, advDesc)
     * @param joueur le joueur qui joue
     */
    private void poser(int indice, Carte sensDeLaPile, Joueur joueur) {
        sensDeLaPile.setValeur(joueur.getMesCartes().getCarte(indice).getValeur());
        joueur.getMesCartes().removeCarte(indice);
    }

    /**
     * Permet de retourner l'indice de la carte a remove
     * @param saisie la saisie d'une carte jouée par le joueur
     * @return l'indice de la carte a remove
     */
    private int donneIdxRemove(String saisie, Joueur joueur){
        String nombre = "" + saisie.charAt(0) + saisie.charAt(1);
        for (int i = 0; i < joueur.getMesCartes().getNbCartes(); i++) {
            if(Integer.parseInt(nombre) == joueur.getMesCartes().getCarte(i).getValeur())
                return i;
        }
        return -1;
    }

}
