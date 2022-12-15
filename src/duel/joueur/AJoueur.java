package duel.joueur;

import duel.Nom;
import duel.carte.Carte;
import duel.coup.Coup;
import duel.interfaces.ICarte;
import duel.interfaces.ICoup;
import duel.interfaces.IJoueur;
import duel.interfaces.IPaquet;
import duel.paquet.Paquet;

import java.util.Comparator;

/**
 * Modélise un joueur en général
 * @author  Maxime Wang, Senaa Abdellah, Minh-Tri NGUYEN
 * @version 1.1
 */
public abstract class AJoueur implements IJoueur {

    /** Les cartes en main du joueur */
    private IPaquet mesCartes;

    /** La pioche du joueur */
    private IPaquet maPioche;

    /** La carte descendante du joueur */
    private ICarte descendant;

    /** La carte ascendante du joueur */
    private ICarte ascendant;

    /** Le nom du joueur */
    private String nom;

    /** Le coup du joueur */
    private ICoup coup;

    /** Permet de savoir si c'est notre tour de jeu */
    private boolean monTourDeJouer;


    /**
     * Constructeur à partir de Noms (enumération)
     * @param nom détermine le nom du joueur
     * @param monTourDeJouer détermine la priorité du jeu
     */
    public AJoueur(String nom, boolean monTourDeJouer){
        // Création des données de bases
        mesCartes = new Paquet();
        maPioche = new Paquet(true);
        descendant = new Carte(60);
        ascendant = new Carte(1);
        coup = new Coup();

        // Ajoute 6 cartes à ma main de cartes
        for (int i = 0; i < 6; i++){
            if (!maPioche.estVide())
                ajoute(maPioche.piocher());
        }
        // Mélange le paquet de cartes dans ma main
        mesCartes.getCartes().sort(Comparator.comparing(ICarte::getValeur));
        // Passage des valeurs passés en paramètre aux attributs de la classe
        this.nom = nom;
        this.monTourDeJouer = monTourDeJouer;
    }

    /** Constructeur d'un joueur n'ayant pas la priorité de jeu */
    public AJoueur(String nom){
        this(nom, false);
    }
    public AJoueur(){
        this("Ordinateur", true);
    }

    @Override
    public IPaquet getMesCartes() {
        return mesCartes;
    }

    @Override
    public IPaquet getMaPioche() {
        return maPioche;
    }

    @Override
    public ICoup getCoup() { return coup; }


    @Override
    public ICarte getAscendant() {
        return ascendant;
    }


    @Override
    public ICarte getDescendant() {
        return descendant;
    }


    @Override
    public int getNbDeMaPioche() {
        return maPioche.getNbCartes();
    }


    @Override
    public int getNbDeMesCartes() {
        return mesCartes.getNbCartes();
    }


    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public boolean isMonTourDeJouer() {
        return monTourDeJouer;
    }

    @Override
    public void setMonTourDeJouer(boolean monTourDeJouer) {
        this.monTourDeJouer = monTourDeJouer;
    }

    /**
     * Permet d'avoir une représentation des cartes en mains du joueur
     * sous la forme d'une chaîne de caractères.
     * @return la représentation textuelle des cartes en mains
     */
    @Override
    public String toString() {
        return "Vos cartes sont : " + mesCartes ;
    }


    @Override
    public void ajoute(ICarte c){
        mesCartes.getCartes().add(c);
    }


    @Override
    public boolean aMonTourDeJouer(IJoueur adversaire) {
        // Si c'est à mon tour de jouer se met en false et met à true l'autre joueur puis renvoi true
        if (this.monTourDeJouer){
            this.monTourDeJouer = false;
            adversaire.setMonTourDeJouer(true);
            return true;
        }
        // Renvoi false dans le cas contraire
        return false;
    }

    @Override
    public boolean aDesCartes(){
        return !mesCartes.estVide();
    }

}
