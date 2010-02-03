package openopoly.board;

import java.util.Hashtable;
import openopoly.Player;
import openopoly.control.business.Bank;
import openopoly.err.GameException;
import openopoly.err.PlaceDoesntExistsException;
import openopoly.err.UnmortgageablePlaceException;

/**Classe que representa o tabuleiro de jogo
 *
 * @author Lucas
 * @author Sergio
 */
public class GameBoard {

    private Hashtable<Integer, Block> slots;
    private static GameBoard instance = new GameBoard();

    /**
     * O construtor da classe preenche o tabuleiro.
     */
    private GameBoard() {
        slots = new Hashtable<Integer, Block>();
        fillGameBoard();
    }


    /**
     * Esse método acessa a unica instancia do Tabuleiro
     * do jogo
     * @return a instancia do tabuleiro
     */
    public static GameBoard getInstance() {
        if (GameBoard.instance == null) {
            instance = new GameBoard();
        }
        return instance;
    }

    /**
     * Esse método preenche os blocos do Tabuleiro
     * com as propriedades, ferrovias, impostos e serviços públicos
     * com suas repectivas caracteristicas
     */
    private void fillGameBoard(){
        Player bank = Bank.getBank();
        slots.put( 1, new Property      ( 1, "Mediterranean Avenue", bank, 60, 2, 10, 30, 90, 160, 250, 30, 50, "purple"));
        slots.put( 2, new FreeParking   ( 2, "Community Chest 1", "chest"));
        slots.put( 3, new Property      ( 3, "Baltic Avenue", bank, 60, 4, 20, 60, 180, 320, 450, 30, 50, "purple"));
        slots.put( 4, new Tax           ( 4, "Income Tax", "tax"));
        slots.put( 5, new Railroad      ( 5, "Reading Railroad", bank, "railroad"));
        slots.put( 6, new Property      ( 6, "Oriental Avenue", bank, 100, 6, 30, 90, 270, 400, 550, 50, 50, "light blue"));
        slots.put( 7, new FreeParking   ( 7, "Chance 1", "chance"));
        slots.put( 8, new Property      ( 8, "Vermont Avenue", bank, 100, 6, 30, 90, 270, 400, 550, 50, 50, "light blue"));
        slots.put( 9, new Property      ( 9, "Connecticut Avenue", bank, 120, 8, 40, 100, 300, 450, 600, 60, 50, "light blue"));
        slots.put(10, new FreeParking   (10, "Jail - Just Visiting", "corner"));
        slots.put(11, new Property      (11, "St. Charles Place", bank, 140, 10, 50, 150, 450, 625, 750, 70, 100, "pink"));
        slots.put(12, new Utility       (12, "Electric Company", bank, "utility"));
        slots.put(13, new Property      (13, "States Avenue", bank, 140, 10, 50, 150, 450, 625, 750, 70, 100, "pink"));
        slots.put(14, new Property      (14, "Virginia Avenue", bank, 160, 12, 60, 180, 500, 700, 900, 80, 100, "pink"));
        slots.put(15, new Railroad      (15, "Pennsylvania Railroad", bank, "railroad"));
        slots.put(16, new Property      (16, "St. James Place", bank, 180, 14, 70, 200, 550, 750, 950, 90, 100, "orange"));
        slots.put(17, new FreeParking   (17, "Community Chest 2", "chest"));
        slots.put(18, new Property      (18, "Tennessee Avenue", bank, 180, 14, 70, 200, 550, 750, 950, 90, 100, "orange"));
        slots.put(19, new Property      (19, "New York Avenue", bank, 200, 16, 80, 220, 600, 800, 1000, 100, 100, "orange"));
        slots.put(20, new FreeParking   (20, "Free Parking", "corner"));
        slots.put(21, new Property      (21, "Kentucky Avenue", bank, 220, 18, 90, 250, 700, 875, 1050, 110, 150, "red"));
        slots.put(22, new FreeParking   (22, "Chance 2", "chance"));
        slots.put(23, new Property      (23, "Indiana Avenue", bank, 220, 18, 90, 250, 700, 875, 1050, 110, 150, "red"));
        slots.put(24, new Property      (24, "Illinois Avenue", bank, 240, 20, 100, 300, 750, 925, 1100, 120, 150, "red"));
        slots.put(25, new Railroad      (25, "B & O Railroad", bank, "railroad"));
        slots.put(26, new Property      (26, "Atlantic Avenue", bank, 260, 22, 110, 330, 800, 975, 1150, 130, 150, "yellow"));
        slots.put(27, new Property      (27, "Ventnor Avenue", bank, 260, 22, 110, 330, 800, 975, 1150, 130, 150, "yellow"));
        slots.put(28, new Utility       (28, "Water Works", bank, "utility"));
        slots.put(29, new Property      (29, "Marvin Gardens", bank, 280, 24, 120, 360, 850, 1025, 1200, 140, 150, "yellow"));
        slots.put(30, new FreeParking   (30, "Go To Jail", "corner", true));
        slots.put(31, new Property      (31, "Pacific Avenue", bank, 300, 26, 130, 390, 900, 1100, 1275, 150, 200, "green"));
        slots.put(32, new Property      (32, "North Carolina Avenue", bank, 300, 26, 130, 390, 900, 1100, 1275, 150, 200, "green"));
        slots.put(33, new FreeParking   (33, "Community Chest 3", "chest"));
        slots.put(34, new Property      (34, "Pennsylvania Avenue", bank, 320, 28, 150, 450, 1000, 1200, 1400, 160, 200, "green"));
        slots.put(35, new Railroad      (35, "Short Line Railroad", bank, "railroad"));
        slots.put(36, new FreeParking   (36, "Chance 3", "chance"));
        slots.put(37, new Property      (37, "Park Place", bank, 350, 35, 175, 500, 1100, 1300, 1500, 175, 200, "indigo"));
        slots.put(38, new Tax           (38, "Luxury Tax", "tax"));
        slots.put(39, new Property      (39, "Boardwalk", bank, 400, 50, 200, 600, 1400, 1700, 2000, 200, 200, "indigo"));
        slots.put(40, new FreeParking   (40, "Go", "corner"));
    }

    /**
     * Esse método solicita ao tabuleiro o nome do bloco
     * referente à posição indicada.
     * @param pos posiçao do tabuleiro solicitada
     * @return o nome do bloco
     */
    public String getBlockName(int pos) throws PlaceDoesntExistsException {
        return getBlock(pos).getPropName();
    }

    /**
     * Esse método solicita ao tabuleiro o grupo do bloco
     * referente à posição indicada.
     * @param pos posiçao do tabuleiro solicitada
     * @return o grupo
     */
    public String getBlockGroup(int pos) throws PlaceDoesntExistsException {
        return getBlock(pos).getGroup();
    }


    /**
     * Esse método solicita ao tabuleiro o nome do dono do bloco
     * referente à posição indicada.
     * @param pos posiçao do tabuleiro solicitada
     * @return o nome do dono do bloco
     * @throws GameException caso o bloco não possa ter dono
     */
    public String getBlockOwnerName(int pos) throws PlaceDoesntExistsException, GameException{
        if(cannotBeBought(pos) || isTax(pos)){
            throw new GameException("This place can't be owned");
        }else{
            return getBlock(pos).getOwner().getPlayerName();
        }
    }

    /**
     * Esse método solicita ao tabuleiro o aluguel atual do bloco
     * referente à posição indicada.
     * @param pos posiçao do tabuleiro solicitada
     * @return o valor do aluguel
     * @throws GameException caso o bloco não tenha um aluguel
     */
    public int getPropertyRent(int pos) throws PlaceDoesntExistsException, GameException{
        if(!isProperty(pos)){
            throw new GameException("This place doesn't have a rent");
        }else{
            Property p = (Property)getBlock(pos);
            return p.getCurrentRent();
        }
    }

    /**
     * Esse método solicita ao tabuleiro o aluguel atual do bloco
     * referente à posição indicada. As regras de construção são aplicadas ao aluguel
     * @param pos posiçao do tabuleiro solicitada
     * @return o valor do aluguel
     * @throws GameException caso o bloco não tenha um aluguel
     */
    public int getPropertyRentBuildRules(int pos) throws PlaceDoesntExistsException, GameException{
        if(!isProperty(pos)){
            throw new GameException("This place doesn't have a rent");
        }else{
            Property p = (Property)getBlock(pos);
            return p.getCurrentRentBuildRules();
        }
    }

    /**
     * Esse método solicita ao tabuleiro o preço do bloco
     * referente à posição indicada.
     * @param pos posiçao do tabuleiro solicitada
     * @return o valor do preço
     * @throws GameException caso o bloco não possa ser vendido
     */
    public int getPlacePrice(int pos) throws PlaceDoesntExistsException, GameException{
        if(getBlock(pos).getPrice()== -1){
            throw new GameException("This place can't be sold");
        }else{
            return getBlock(pos).getPrice();
        }
    }

    /**
     * Esse método retorna o bloco
     * referente à posição indicada.
     * @param pos posiçao do tabuleiro solicitada
     * @return o bloco
     * @throws PlaceDoesntExistsException caso o lugar referente a posição solicitada não exista
     */
    public Block getBlock(int pos) throws PlaceDoesntExistsException{
        if(pos > 0 && pos < 41){
            return slots.get(pos);
        }else{
            throw new PlaceDoesntExistsException();
        }
    }

    /**
     * Esse método testa se é uma ferrovia
     * na posição indicada.
     * @param pos posição do tabuleiro solicitada
     */
    public boolean isRailRoad(int pos) throws PlaceDoesntExistsException{
        return getBlock(pos).getGroup().equals("railroad");
    }

    /**
     * Esse método testa se é uma propriedade
     * na posição indicada.
     * @param pos posição do tabuleiro solicitada
     */
    public boolean isProperty(int pos) throws PlaceDoesntExistsException{
        boolean check = false;
        if(!isRailRoad(pos) && !isTax(pos) && !isUtility(pos) && !cannotBeBought(pos)){
            check = true;
        }
        return check;
    }

    /**
     * Esse método testa se é um imposto
     * na posição indicada.
     * @param pos posição do tabuleiro solicitada
     */
    public boolean isTax(int pos) throws PlaceDoesntExistsException{
        return getBlock(pos).getGroup().equals("tax");
    }
    /**
     * Esse método testa se é um serviço publico
     * na posição indicada.
     * @param pos posição do tabuleiro solicitada
     */
    public boolean isUtility(int pos) throws PlaceDoesntExistsException{
        return getBlock(pos).getGroup().equals("utility");
    }

    /**
     * Esse método testa se é uma parada livre
     * na posição indicada.
     * @param pos posição do tabuleiro solicitada
     */
    public boolean cannotBeBought(int pos) throws PlaceDoesntExistsException{
        boolean check = false;
        if(getBlock(pos).getGroup().equals("corner") || getBlock(pos).getGroup().equals("chance") || getBlock(pos).getGroup().equals("chest")){
            check = true;
        }
        return check;
    }

     /**
     * Esse método testa se é um "Corner"
     * na posição indicada.
     * @param pos posição do tabuleiro solicitada
     * @return true caso o lugar seja um "Go to Jail", false caso contrário
     * @throws PlaceDoesntExistsException caso a posição solicitada não exista.
     */
    public boolean isCorner(int pos) throws PlaceDoesntExistsException{
        return getBlock(pos).getGroup().equals("corner");
    }
    
    /**
     * Esse método testa se é um "Go to Jail"
     * na posição indicada.
     * @param pos posição do tabuleiro solicitada
     * @return true caso o lugar seja um "Go to Jail", false caso contrário
     * @throws PlaceDoesntExistsException caso a posição solicitada não exista.
     */
    public boolean isGoToJail(int pos)throws PlaceDoesntExistsException{
        return getBlock(pos).isGoToJail();
    }

    public boolean isChance(int pos) throws PlaceDoesntExistsException {
        return getBlock(pos).getGroup().equals("chance");
    }

    public boolean isChest(int pos) throws PlaceDoesntExistsException{
        return getBlock(pos).getGroup().equals("chest");
    }

    /**
     * Esse método testa de o bloco possui dono diferente do banco
     * na posição indicada
     * @param pos posição do tabuleiro solicitada
     * @return retorna true caso o bloco tenha um dono diferente do banco, retorna false caso contrário
     */
    public boolean hasOwner(int pos) {
        return !slots.get(pos).getOwner().getPlayerName().equals("bank");
    }

    /**
     * Este método reseta a unica instancia do Tabuleiro do jogo
     */
    public void resetGameBoard(){
        instance = null;
    }

    public int searchBlock(String group, int actualPos) throws PlaceDoesntExistsException{
        for(int i = actualPos; ;i++){
            if(i > 40){
                i = i - 40;
            }
            if(getBlock(i).getGroup().equals(group)){
                return i;
            }
        }
    }

    public int nextRailroad(int actualPos) throws PlaceDoesntExistsException{
        return searchBlock("railroad", actualPos);
    }

    public int nextUtility(int actualPos) throws PlaceDoesntExistsException{
        return searchBlock("utility", actualPos);
    }

    public boolean isMortgaged(int placeID) throws PlaceDoesntExistsException, UnmortgageablePlaceException {
        Block b = GameBoard.getInstance().getBlock(placeID);
        return b.isMortgaged();
    }


	public boolean hasMortgagedPropertiesInGroup(String group) {
			for (int i=0; i<slots.size();i++){
				Block b = this.slots.get(new Integer(i));
				try{
					if (b.isMortgaged()&&b.getGroup().equals(group)){
						return true;
					}
				}
				catch (Exception e){
					//do nothing
				}
				
	        }
			return false;
    }


    


    
}
