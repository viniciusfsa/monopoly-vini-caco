package openopoly.board;

import openopoly.Player;

/**Classe que representa o imposto
 *
 * @author Lucas
 * @author Sergio
 */
public class Tax implements Block{

    private int posGBoard;
    private String propName, group;

   /**
     * O construtor da classe tem a função de inicializar
     * os atributos dos impostos
     * @param posGBoard posição no tabuleiro
     * @param propName nome do lugar
     * @param group grupo
     */
    public Tax(int posGBoard, String propName, String group) {
        this.posGBoard = posGBoard;
        this.propName = propName;
        this.group = group;
    }

    /**
     * Retorna o valor do imposto correspondente a posição no tabuleiro
     * @param posGB posição no tabuleiro
     * @return valor do imposto
     */
    public int getTax(int posGB){
        if(posGB == 4){
            return 200;
        }else if(posGB == 38){
            return 75;
        }else{
            return 0;
        }
    }

    //Getters and Setters
    public String getPropName() {
        return propName;
    }

    public int getPosGB() {
        return posGBoard;
    }

    public Player getOwner() {
        return null;
    }

    public void setOwner(Player p) {
    }

    public String getGroup() {
        return group;
    }

    public int getPrice() {
        return -1;
    }

    public void setGroup(String group) {
       this.group = group;
    }

    public boolean isGoToJail() {
        return false;
    }

    public boolean isMortgageable() {
        return false;
    }
}
