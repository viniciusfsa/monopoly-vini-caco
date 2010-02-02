package openopoly.board;

import openopoly.*;
import openopoly.err.UnmortgageablePlaceException;
/**
 *
 * @author Lucas
 * @author Sergio
 */
public class FreeParking implements Block {

    private int posGBoard;
    private String propName, group;
    private boolean goToJail = false;

    
    /**
     * O construtor da classe recebe a posição no tabuleiro, o nome do lugar
     * e o grupo ao qual ele pertence.
     * @param posGBoard posição no tabuleiro
     * @param propName nome do lugar
     * @param group grupo
     */
    public FreeParking(int posGBoard, String propName, String group) {
        this.posGBoard = posGBoard;
        this.propName = propName;
        this.group = group;
    }


    /**
     * O construtor da classe recebe a posição no tabuleiro, o nome do lugar, 
     * o grupo ao qual ele pertence e se é o goToJail.
     * @param posGBoard posição no tabuleiro
     * @param propName nome do lugar
     * @param group grupo
     * @param goToJail propriedade que indica que é um goToJail
     */
    public FreeParking(int posGBoard, String propName, String group, boolean goToJail) {
        this.posGBoard = posGBoard;
        this.propName = propName;
        this.group = group;
        this.goToJail = goToJail;
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
        return goToJail;
    }

    public boolean isMortgageable() {
        return false;
    }

    public boolean isMortgaged() throws UnmortgageablePlaceException {
        throw new UnmortgageablePlaceException();
    }

    public boolean isOwnerAPlayer(){
        return false;
    }

}
