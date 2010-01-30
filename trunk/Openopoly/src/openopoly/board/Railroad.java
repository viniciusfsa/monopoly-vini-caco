package openopoly.board;

import openopoly.*;


/**Classe que representa a ferrovia
 *
 * @author Lucas
 * @author Sergio
 */
public class Railroad implements Block{

    private int posGBoard;
    private String propName, group;
    private Player owner;
    private int price, mortgage;
    
    /**
     * O construtor da classe tem a função de inicializar
     * os atributos da ferrovia
     * @param posGBoard posição no tabuleiro
     * @param propName nome do lugar
     * @param owner Player proprietário
     * @param group grupo
     */
    public Railroad(int posGBoard, String propName, Player owner, String group) {
        this.posGBoard = posGBoard;
        this.propName = propName;
        this.owner = owner;
        this.price = 200;
        this.mortgage = 100;
        this.group = group;
    }


    /**
     * Retorna o valor da corrida atual.
     * @return valor
     */
    public int getCurrentRide(){
        return 25*getOwnerRailRoadNum();
    }

    //Getters and Setters
    public int getPrice() {
        return price;
    }

    public int getMortgage() {
        return mortgage;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getOwnerRailRoadNum(){
        return getOwner().getRailroadsNum();
    }

    public String getPropName() {
        return propName;
    }

    public int getPosGB() {
        return posGBoard;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
       this.group = group;
    }

    public boolean isGoToJail() {
        return false;
    }

}
