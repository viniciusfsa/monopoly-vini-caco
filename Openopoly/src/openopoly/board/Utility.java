package openopoly.board;

import java.util.ArrayList;
import openopoly.Player;

/**Classe que representa um serviço publico
 *
 * @author Sergio
 * @author Lucas
 */
public class Utility implements Block {

    private int posGBoard;
    private String propName, group;
    private Player owner;
    private int price, mortgage;

    /**
     * o construtor da classe tem a função de inicializar
     * os atributos dos serviços publicos
     * @param posGBoard posição no tabuleiro
     * @param propName nome do lugar
     * @param owner Player proprietário
     * @param group grupo
     */

    public Utility(int posGBoard, String propName, Player owner, String group) {
        this.posGBoard = posGBoard;
        this.propName = propName;
        this.owner = owner;
        this.price = 150;
        this.mortgage = 75;
        this.group = group;
    }



    //Getters and Setters
    public int getPrice() {
        return price;
    }

    public int getMultiplier(){
        ArrayList<Block> t =  owner.getTitles();
        int numberOfUtilities = 0;
        for (Block block : t) {
            if(block.getGroup().equals("utility")){
                numberOfUtilities++;
            }
        }
        if(numberOfUtilities == 1){
            return 4;
        }else if(numberOfUtilities == 2){
            return 10;
        } else{
            return 0;
        }
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
