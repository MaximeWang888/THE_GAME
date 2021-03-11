package duel;


import java.util.ArrayList;

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

}
