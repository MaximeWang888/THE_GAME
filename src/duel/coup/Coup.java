package duel.coup;

import duel.carte.Carte;
import duel.interfaces.ICarte;
import duel.interfaces.IJoueur;
import duel.interfaces.IPaquet;


/**
 * Modélise un coup joué par un joueur.
 * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public class Coup extends ACoup {

    public Coup() {super();}

    @Override
    public boolean estUnCoupValide(IPaquet mesCartes) {
        for (int i = 0; i < getCoups().size(); i++) {
            if (!(2 < getCoups().get(i).length() && getCoups().get(i).length() < 5))
                return false;
            String nombre = "" + getCoups().get(i).charAt(0) + getCoups().get(i).charAt(1);

            if (!estUnFormatValide(i))
                return false;
            if (!mesCartes.isCartePresenteEnMain(nombre))
                return false;
        }

        if (existeDoublon())
            return false;
        if (plusDeUnCoupChezAdversaire())
            return false;
        return getCoups().size() >= 2 && getCoups().size() <= 6;
    }

    /**
     * Vérifie que la taille est bonne et le format aussi regarder si je joueur possède bien les cartes
     * @param i l'indice
     * @return TRUE si c'est le bon format, FALSE dans le cas contraire
     */
    private boolean estUnFormatValide(int i) {
        String signe;
        String adverse = "";
        int longueurDeLaSaisie = getCoups().get(i).length();
        signe = "" + getCoups().get(i).charAt(2);
        if (longueurDeLaSaisie > 3) {
            adverse = "" + getCoups().get(i).charAt(3);
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
        for (int i = 0; i < getCoups().size() ; i++) {
            if(getCoups().get(i).length()==4)
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
        for (int i = 0; i < getCoups().size() - 1; i++) {
            String mot1 = "" + getCoups().get(i).charAt(0) + getCoups().get(i).charAt(1);
            for (int j = i+1; j < getCoups().size(); j++) {
                String mot2 = "" + getCoups().get(j).charAt(0) + getCoups().get(j).charAt(1);
                if(mot1.equals(mot2))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean peutEtrePoser(IJoueur joueur, IJoueur adversaire, boolean peutDoncEtrePoser){
        ICarte mAscendant = new Carte(joueur.getAscendant().getValeur());
        ICarte mDescendant = new Carte(joueur.getDescendant().getValeur());
        ICarte advAscendant = new Carte(adversaire.getAscendant().getValeur());
        ICarte advDescendant = new Carte(adversaire.getDescendant().getValeur());

        for(int i = 0; i < getCoups().size(); i++) { // 04v 05v 06v
            int nombre = Integer.parseInt(getCoups().get(i).substring(0,2));
            if(getCoups().get(i).length() <= 3){ // ton tas
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
    private boolean dansMesPilesDeCartes(IJoueur joueur, boolean peutDoncEtrePoser, ICarte mAscendant,
                                         ICarte mDescendant, int i , int nombre) {
        if (getCoups().get(i).charAt(2) == '^'){ // carte ascendant
            if((mAscendant.getValeur() < nombre || mAscendant.getValeur() - 10  == nombre)){
                mAscendant.setValeur(nombre);
                if(peutDoncEtrePoser){
                    int idx = donneIdxRemove(getCoups().get(i), joueur);
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
                    int idx = donneIdxRemove(getCoups().get(i), joueur);
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
    private boolean dansSesPilesDeCartes(IJoueur adversaire, IJoueur joueur, boolean peutDoncEtrePoser,
                                         ICarte advAscendant, ICarte advDescendant, int i, int nombre) {
        if (getCoups().get(i).charAt(2) == 'v'){ // carte descendant
            if (advDescendant.getValeur() < nombre){
                advDescendant.setValeur(nombre);
                if (peutDoncEtrePoser){
                    int idx = donneIdxRemove(getCoups().get(i), joueur);
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
                    int idx = donneIdxRemove(getCoups().get(i), joueur);
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
    private void poser(int indice, ICarte sensDeLaPile, IJoueur joueur) {
        sensDeLaPile.setValeur(joueur.getMesCartes().getCarte(indice).getValeur());
        joueur.getMesCartes().removeCarte(indice);
    }

    /**
     * Permet de retourner l'indice de la carte a remove
     * @param saisie la saisie d'une carte jouée par le joueur
     * @return l'indice de la carte a remove
     */
    private int donneIdxRemove(String saisie, IJoueur joueur){
        String nombre = "" + saisie.charAt(0) + saisie.charAt(1);
        for (int i = 0; i < joueur.getMesCartes().getNbCartes(); i++) {
            if(Integer.parseInt(nombre) == joueur.getMesCartes().getCarte(i).getValeur())
                return i;
        }
        return -1;
    }

}
