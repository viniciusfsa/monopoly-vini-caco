package openopoly.board;

import openopoly.*;
import openopoly.err.HousesNotBuildableException;
import openopoly.err.UnmortgageablePlaceException;


/**Classe que representa um bloco do tabuleiro
 *
 * @author Lucas
 * @author Sergio
 */
public interface Block {

    /**
     * Implementa o retorno da posição do bloco
     * @return posição do bloco do tabuleiro
     */
    public int getPosGB();

    /**
     * Implementa o retorno do nome bloco
     * @return O nome do bloco
     */
    public String getPropName();

    /**
     * Implementa o retorno do grupo do bloco
     * @return nome do grupo
     */
    public String getGroup();

   /**
     * Implementa o retorno do nome do dono do bloco
     * @return retorna o nome do bloco
     */
    public Player getOwner();

    /**
     * Implementa o retorno do valor do bloco
     * @return retorna o preço
     */
    public int getPrice();

    /**
     * Retorna a propriedade se o bloco é um goToJail
     * @return true caso seja um goToJail, false caso contrário
     */
    public boolean isGoToJail();


    /**
     * Cofigura o dono do bloco
     * @param p dono do bloco
     */
    public void setOwner(Player p);

    /**
     * Cofigura o grupo do bloco
     * @param p grupo do bloco
     */
    public void setGroup(String group);


    /**
     * Se o bloco é hipotecavel ou nao
     * @return true se for hipotecavel, false caso contrario
     */
    public boolean isMortgageable();

    /**
     * Se o bloco esta hipotecada ou nao
     * @return true se for hipotecavel, false caso contrario
     */
    public boolean isMortgaged() throws UnmortgageablePlaceException;

    public boolean isOwnerAPlayer();

	public void setMortgaged(boolean mortgaged) throws UnmortgageablePlaceException;
	
	public boolean hasHousesBuilt() throws HousesNotBuildableException;

	public int getMortgagePrice();
	
	public int getUnmortgagePrice();
	
}
    