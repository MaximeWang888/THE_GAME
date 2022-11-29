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
       return true;
    }

    /**
     * Vérifie que la taille est bonne et le format aussi regarder si je joueur possède bien les cartes
     * @param i l'indice
     * @return TRUE si c'est le bon format, FALSE dans le cas contraire
     */
    private boolean estUnFormatValide(int i) {
       return true;
    }

    /**
     * Vérifie si le joueur a joué deux fois sur les cartes adverses
     * @return TRUE si il a joué deux cartes sur la pile adverse, FALSE
     * dans le cas contraire
     */
    private boolean plusDeUnCoupChezAdversaire() {
        return false;
    }

    /**
     * Vérifie que la même carte n'est pas été mise deux fois
     * @return TRUE si la carte est présente deux fois, FALSE
     * dans le cas contraire
     */
    private boolean existeDoublon() {
        return false;
    }

    @Override
    public boolean peutEtrePoser(IJoueur joueur, IJoueur adversaire, boolean peutDoncEtrePoser){
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
            return false;
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
            return false;
    }

    /**
     * Permet de poser la carte sur la bonne pile et enlève la carte de la main du joueur
     * @param indice l'indice de la carte
     * @param sensDeLaPile le sens de la pile (ascendant, descendant, advAcs, advDesc)
     * @param joueur le joueur qui joue
     */
    private void poser(int indice, ICarte sensDeLaPile, IJoueur joueur) {

    }

    /**
     * Permet de retourner l'indice de la carte a remove
     * @param saisie la saisie d'une carte jouée par le joueur
     * @return l'indice de la carte a remove
     */
    private int donneIdxRemove(String saisie, IJoueur joueur){
        return -1;
    }

}
