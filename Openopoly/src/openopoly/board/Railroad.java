package openopoly.board;

import openopoly.*;
import openopoly.err.HousesNotBuildableException;


/**Classe que representa a ferrovia
 *
 * @author Lucas
 * @author Sergio
 */
//public class Railroad implements Block{
public class Railroad extends MortgageableProperty{	
    
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
        this.mortgaged = false;
    }


    /**
     * Retorna o valor da corrida atual.
     * @return valor
     */
    public int getCurrentRide(){
        return 25*getOwnerRailRoadNum();
    }


    public int getOwnerRailRoadNum(){
        return getOwner().getRailroadsNum();
    }
    
	@Override
	public boolean hasHousesBuilt() throws HousesNotBuildableException {
		return false;
	}
	

}
