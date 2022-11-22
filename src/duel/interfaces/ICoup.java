package duel.interfaces;

import java.util.List;

public interface ICoup {

    /**
     * Retourne la liste de coup
     * @return la liste de coup
     */
    List<String> getCoups();

    /**
     * Permet de fixer le coup
     * @param coups le nouveau coup
     */
    void setCoups(List<String> coups);

    /**
     * Calcul le nombre de cartes à piocher
     * @param maPioche mon paquet de cartes
     * @param mesCartes mon paquet de cartes en main
     * @return le nombre de cartes à piocher
     */
    int calculCartePiocher(IPaquet maPioche, IPaquet mesCartes);

    /**
     * Permet de déterminer si le coup est un coup valide ou pas
     * @param mesCartes mon paquet de cartes en main
     * @return TRUE si c'est un coup valide, FALSE dans le cas contraire
     */
    boolean estUnCoupValide(IPaquet mesCartes);


    /**
     * Permet de connaître si les cartes saisies sont posables dans le tas de jeu
     * à nous ou de l'adversaire
     * @param joueur le joueur actuelle
     * @param adversaire le joueur adverse
     * @param peutDoncEtrePoser boolean décidant de la pose des cartes
     * @return TRUE si les cartes peuvent être posé, FALSE dans le cas contraire
     */
    boolean peutEtrePoser(IJoueur joueur, IJoueur adversaire, boolean peutDoncEtrePoser);

}
