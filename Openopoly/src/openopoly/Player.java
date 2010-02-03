package openopoly;

import openopoly.control.game.GameControl;
import openopoly.control.business.BusinessManager;
import openopoly.board.GameBoard;
import openopoly.board.Block;
import java.util.ArrayList;
import openopoly.board.Property;
import openopoly.control.game.GameOptions;
import openopoly.err.GameException;
import openopoly.err.PlaceDoesntExistsException;
import openopoly.err.UnmortgageablePlaceException;

/**Classe que representa o jogador
 *
 * @author Lucas
 * @author Sergio
 */
public class Player {

    private String playerName, color;
    private int cash;
    private int posGBoard;
    private int railroadsNum;
    private boolean isAtGo = true;
    private boolean needSallary = false;
    private boolean bankrupt = false;
    private ArrayList<Block> possessions = new ArrayList<Block>();
    private ArrayList<Property> listProperty = new ArrayList<Property>();
    private boolean jailed = false;
    private GameOptions options;
    private boolean chancePrisonCard = false;
    private boolean chestPrisonCard = false;
    private int jailCount = 1;
    private int extraTurnCount = 1;
    private boolean extraTurn = false;
    private boolean purpleMonopoly = false;
    private boolean lightBlueMonopoly = false;
    private boolean yellowMonopoly = false;
    private boolean pinkMonopoly = false;
    private boolean orangeMonopoly = false;
    private boolean redMonopoly = false;
    private boolean greenMonopoly = false;
    private boolean indigoMonopoly = false;
    private boolean isBank = false;
    
    

    /**
     * O construtor da classe tem a função de inicializar
     * os valores iniciais do Jogador
     * @param playerName nome do jogador
     * @param color cor do jogador
     */
    public Player(String playerName, String color) {
        this.playerName = playerName;
        this.color = color;
        setCash(1500);
        setRailroadsNum(0);
        setPosGameBoard(40);
        options = new GameOptions();
    }

    /**
     * Esse método faz uma chamada pra imprimir os titulos
     * de propriedades do jogador
     * @return String com os títulos que o jogador possui
     */
    public String getDeeds() {
        String deeds = "{";
        if (!possessions.isEmpty()) {
            for (Block block : possessions) {
                deeds = deeds.concat(block.getPropName() + ",");
            }
            deeds = deeds.substring(0, deeds.length() - 1);
        }
        return deeds.concat("}");
    }

    /**
     * Esse método adiciona à lista de titulos do jogador
     * @param pos posição do titulo no tabuleiro
     */
    public void addPossession(int pos) throws GameException {
        if (getGameBoard().isRailRoad(pos)) {
            railroadsNum++;
        }
        possessions.add(getGameBoard().getBlock(pos));
    }

    /**
     * Esse método move a posição do jogador no tabuleiro
     * e verifica se o peão passou ou chegou ao inicio
     * @param numBlocks numero de casas a ser movido
     * @see GameControl
     * @see BusinessManager
     */
    public void move(int numBlocks) {
        int newPos = getPosGBoard() + numBlocks;
        if (newPos > 40) {
            newPos -= 40;
            if (!isAtGo()) {
                setNeedSallary(true);
            }
            setIsAtGo(false);
        } else if (newPos == 40) {
            setNeedSallary(true);
            setIsAtGo(true);
        }
        setPosGameBoard(newPos);
    }

    /**
     * Esse método move o jogador para a cadeia.
     */
    public void goToJail() {
        setJailed(true);
        options.addPayOption();
        setPosGameBoard(10);
    }

    /**
     * Esse método acessa o Tabuleiro
     * @return a instancia do tabuleiro
     */
    public GameBoard getGameBoard() {
        return GameBoard.getInstance();
    }

    /**
     * Esse método obtem a quantidade de casas que o jogador possui
     * @return a quantidade de casas
     * @throws PlaceDoesntExistsException caso o bloco não exista
     */
    public int getPlayersHouse() throws PlaceDoesntExistsException {
        int numHouses = 0;
        Property p;
        for (Block block : possessions) {
            if (getGameBoard().isProperty(block.getPosGB())) {
                p = (Property) block;
                if (!p.hasHotel()) {
                    numHouses += p.getHouseQtd();
                }
            }
        }
        return numHouses;
    }

    /**
     * Esse método obtem a quantidade de hoteis que o jogador possui
     * @return a quantidade de hoteis
     * @throws PlaceDoesntExistsException caso o bloco não exista
     */
    public int getPlayersHotel() throws PlaceDoesntExistsException {
        int numHotels = 0;
        Property p;
        for (Block block : possessions) {
            if (getGameBoard().isProperty(block.getPosGB())) {
                p = (Property) block;
                if (p.hasHotel()) {
                    numHotels += 1;
                }
            }
        }
        return numHotels;
    }

    /**
     * Incrementa o contador da quantidade de rodadas que o jogador esta na prisão
     */
    public void incrementJailCount() {
        jailCount++;
    }

    /**
     * Incrementa o contador de quantidade de vezes que o jogador pode ter turnos extras
     */
    public void incrementExtraTurnsCount() {
        extraTurnCount++;
    }

    /**
     * Esse método verifica se o jogador (dado o grupo e a quantidade de
     * propriedades daquele grupo) possui o monopolio daquele grupo.
     * @param group o tipo do grupo
     * @param numProperty a quantidade de propriedades por grupo
     * @return true caso o jogador tenha o monopolio do grupo, false caso contrário
     * @throws PlaceDoesntExistsException caso o lugar não exista
     */
    private boolean checkGroupMonopoly(String group, int numProperty) throws PlaceDoesntExistsException {
        Property p;
        int countProperty = 0;

        for (Block block : possessions) {
            if (getGameBoard().isProperty(block.getPosGB())) {
                p = (Property) block;
                if (p.getGroup().equals(group)) {
                    countProperty = countProperty + 1;
                }
            }
        }

        if (numProperty == countProperty) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Esse método verifica se o jogador possui algum monopoly
     * @throws PlaceDoesntExistsException caso lugar não exista.
     */
    public void checkPlayerMonopoly() throws PlaceDoesntExistsException {
        purpleMonopoly = checkGroupMonopoly("purple", 2);
        lightBlueMonopoly = checkGroupMonopoly("light blue", 3);
        pinkMonopoly = checkGroupMonopoly("pink", 3);
        yellowMonopoly = checkGroupMonopoly("yellow", 3);
        redMonopoly = checkGroupMonopoly("red", 3);
        orangeMonopoly = checkGroupMonopoly("orange", 3);
        greenMonopoly = checkGroupMonopoly("green", 3);
        indigoMonopoly = checkGroupMonopoly("indigo", 2);
    }

    /**
     * Esse método verifica se o player tem o monopolio de um grupo especifico
     * @param group o tipo do grupo
     * @return true caso o jogador tenha o monopolio sobre o grupo, false caso contrario.
     */
    public boolean hasMonopolyGroup(String group) {
        if (group.equals("purple")) {
            return hasPurpleMonopoly();
        } else if (group.equals("light blue")) {
            return hasLightBlueMonopoly();
        } else if (group.equals("pink")) {
            return hasPinkMonopoly();
        } else if (group.equals("yellow")) {
            return hasYellowMonopoly();
        } else if (group.equals("red")) {
            return hasRedMonopoly();
        } else if (group.equals("orange")) {
            return hasOrangeMonopoly();
        } else if (group.equals("green")) {
            return hasGreenMonopoly();
        } else if (group.equals("indigo")) {
            return hasIndigoMonopoly();
        } else {
            return false;
        }
    }

    /**
     * Esse método retorna a maior quantidade de casas que existe nas propriedades
     * de um grupo
     * @param group grupo a ser pesquisada
     * @return a maior quantidade de casas entre o grupo
     * @throws PlaceDoesntExistsException caso o bloco não exista
     */
    private int getMaxNumHouses(String group) throws PlaceDoesntExistsException {
        int maxNumHouses = 0;
        for (Block block : possessions) {
            if (GameBoard.getInstance().isProperty(block.getPosGB())) {
                Property p = (Property) block;
                if (p.getGroup().equals(group)) {
                    if (p.getHouseQtd() > maxNumHouses) {
                        maxNumHouses = p.getHouseQtd();
                    }
                }
            }
        }
        return maxNumHouses;
    }

    /**
     * Esse método verifica se existe má distribuição das habitações entre as
     * propriedades de um grupo
     * @param group grupo a ser verificado
     * @return true caso exista má distribuição
     * @throws PlaceDoesntExistsException caso o bloco não exista
     */
    public boolean hasBadHabDistribution(String group) throws PlaceDoesntExistsException {
        int maxNumHouses = getMaxNumHouses(group);

        for (Block block : possessions) {
            if (GameBoard.getInstance().isProperty(block.getPosGB())) {
                Property p = (Property) block;
                if (p.getGroup().equals(group)) {
                    if ((maxNumHouses - p.getHouseQtd()) > 1) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    /**
     * Esse método verifica se o jogador ja fez todas as construções possíveis
     * em um determinado grupo.
     * @param group o grupo a ser pesquisado
     * @throws PlaceDoesntExistsException caso o bloco não exista
     */
    public boolean checkFullBuild(String group) throws PlaceDoesntExistsException {
        ArrayList<Property> list = getListPossessionsGroup(group);
        int countHotel = 0;
        for (int i = 0; i < list.size(); i++) {
            Property property = list.get(i);
            if (property.hasHotel()) {
                countHotel++;
            }
        }

        if (countHotel == list.size()) {
            options.removeBuildOption();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Esse método retorna uma lista de propriedades do mesmo grupo
     * @param group o grupo a ser pesquisado
     * @return lista de priedades do mesmo grupo
     * @throws PlaceDoesntExistsException caso o bloco não exista
     */
    public ArrayList<Property> getListPossessionsGroup(String group) throws PlaceDoesntExistsException {
        listProperty.clear();

        for (Block block : possessions) {
            if (GameBoard.getInstance().isProperty(block.getPosGB())) {
                Property p = (Property) block;
                if (p.getGroup().equals(group)) {
                    listProperty.add(p);
                }
            }
        }
        return listProperty;
    }

    /**
     * Configura a opção de construir na lista de opções
     */
    public void configureBuildOption() {
        if (hasGreenMonopoly() || hasIndigoMonopoly() || hasLightBlueMonopoly() ||
                hasOrangeMonopoly() || hasPinkMonopoly() || hasPurpleMonopoly() ||
                hasYellowMonopoly() || hasRedMonopoly()) {

            options.addBuildOption();

        } else {
            options.removeBuildOption();
        }
    }


    public void configureMortgageOption(){
        //adicionar opção de mortgage
    	try{
            if (hasUnmortgagedProperties()){
                this.options.addMortgageOption();
            }
            else{
                this.options.removeMortgageOption();
            }

    	}
    	catch(Exception e){
    		//:D
    	}
    }



    /**
     * Configura a opção de vender na lista de opções
     */
    public void configureSellOption() throws PlaceDoesntExistsException {
        if (ownHousesBuilt()) {
            options.addSellOption();
        } else {
            options.removeSellOption();
        }
    }

    /**
     * Reseta o contador
     */
    public void resetJailCount() {
        jailCount = 1;
    }

    /**
     * Reseta o contador
     */
    public void resetExtraTurnsCount() {
        extraTurnCount = 1;
    }

    //Getters and Setters
    public int getJailCount() {
        return jailCount;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getPosGBoard() {
        return posGBoard;
    }

    public void setPosGameBoard(int posGB) {
        this.posGBoard = posGB;
    }

    public int getRailroadsNum() {
        return railroadsNum;
    }

    public void setRailroadsNum(int railroadsNum) {
        this.railroadsNum = railroadsNum;
    }

    public String getColor() {
        return color;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isAtGo() {
        return isAtGo;
    }

    public void setIsAtGo(boolean firstPlay) {
        this.isAtGo = firstPlay;
    }

    public boolean needSallary() {
        return needSallary;
    }

    public void setNeedSallary(boolean passedTheBeginning) {
        this.needSallary = passedTheBeginning;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public ArrayList<Block> getPossessions() {
        return possessions;
    }

    public GameOptions getOptions(){
    	
//    	try{
//    		System.out.println("Hipotecaveis: " + getMortgageableProperties());
//    		System.out.println("Hipotecadas : " + getMortgagedProperties());
//    	}
//    	catch (Exception e){
//    		//:D
//    	}
    	
        return options;
    }


	public boolean isJailed() {
        return jailed;
    }

    public boolean haveChancePrisonCard() {
        return chancePrisonCard;
    }

    public boolean haveChestPrisonCard() {
        return chestPrisonCard;
    }

    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    public void setChancePrisonCard(boolean chancePrisonCard) {
        this.chancePrisonCard = chancePrisonCard;
    }

    public void setChestPrisonCard(boolean chestPrisonCard) {
        this.chestPrisonCard = chestPrisonCard;
    }

    public boolean hasExtraTurn() {
        return extraTurn;
    }

    public void setExtraTurn(boolean extraTurn) {
        this.extraTurn = extraTurn;
    }

    public int getExtraTurnCount() {
        return extraTurnCount;
    }

    public boolean hasGreenMonopoly() {
        return greenMonopoly;
    }

    public void setGreenMonopoly(boolean greenMonopoly) {
        this.greenMonopoly = greenMonopoly;
    }

    public boolean hasIndigoMonopoly() {
        return indigoMonopoly;
    }

    public void setIndigoMonopoly(boolean indigoMonopoly) {
        this.indigoMonopoly = indigoMonopoly;
    }

    public boolean hasLightBlueMonopoly() {
        return lightBlueMonopoly;
    }

    public void setLightBlueMonopoly(boolean lightBlueMonopoly) {
        this.lightBlueMonopoly = lightBlueMonopoly;
    }

    public boolean hasOrangeMonopoly() {
        return orangeMonopoly;
    }

    public void setOrangeMonopoly(boolean orangeMonopoly) {
        this.orangeMonopoly = orangeMonopoly;
    }

    public boolean hasPinkMonopoly() {
        return pinkMonopoly;
    }

    public void setPinkMonopoly(boolean pinkMonopoly) {
        this.pinkMonopoly = pinkMonopoly;
    }

    public boolean hasPurpleMonopoly() {
        return purpleMonopoly;
    }

    public void setPurpleMonopoly(boolean purpleMonopoly) {
        this.purpleMonopoly = purpleMonopoly;
    }

    public boolean hasRedMonopoly() {
        return redMonopoly;
    }

    public void setRedMonopoly(boolean redMonopoly) {
        this.redMonopoly = redMonopoly;
    }

    public boolean hasYellowMonopoly() {
        return yellowMonopoly;
    }

    public void setYellowMonopoly(boolean yellowMonopoly) {
        this.yellowMonopoly = yellowMonopoly;
    }

    private boolean ownHousesBuilt() throws PlaceDoesntExistsException {
        int house = 0;
        for (Block block : possessions) {
            if (GameBoard.getInstance().isProperty(block.getPosGB())) {
                Property p = (Property) block;
                house += p.getHouseQtd();
            }
        }
        if (house > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public ArrayList<String> getMortgageableProperties() throws UnmortgageablePlaceException{
    	ArrayList<String> mortgageableProps = new ArrayList<String>();
    	for (Block possession: possessions){
            if (!possession.isMortgaged()){
            	mortgageableProps.add(possession.getPropName());
            }
        }

        return mortgageableProps;
    }
    

    private ArrayList<String> getMortgagedProperties() throws UnmortgageablePlaceException {
    	ArrayList<String> mortgageableProps = new ArrayList<String>();
    	for (Block possession: possessions){
            if (possession.isMortgaged()){
            	mortgageableProps.add(possession.getPropName());
            }
        }

        return mortgageableProps;
	}

    public boolean hasUnmortgagedProperties() throws UnmortgageablePlaceException {

        for (Block possession: possessions){
            if (possession.isMortgageable()&&!possession.isMortgaged()){
                return true;
            }
        }

        return false;
    }
    
    public boolean hasMortgageableProperties() throws UnmortgageablePlaceException {

        for (Block possession: possessions){
            if (possession.isMortgageable()){
                return true;
            }
        }

        return false;
    }

    public void setIsBank(boolean b) {
        this.isBank = true;
    }

    public boolean isBank(){
        return this.isBank;
    }

	public void addCash(int extraCash) {
		this.cash += extraCash;
	}
	
	public ArrayList<String> getMonopolies() throws PlaceDoesntExistsException{
		ArrayList<String> monops = new ArrayList<String>();
		checkPlayerMonopoly();
	    //burrice
		if(purpleMonopoly) monops.add ("purple");
	    if(lightBlueMonopoly) monops.add ("light");
	    if(yellowMonopoly) monops.add ("monops");
	    if(pinkMonopoly) monops.add ("pink");
	    if(orangeMonopoly) monops.add ("orange");
	    if(redMonopoly) monops.add ("red");
	    if(greenMonopoly) monops.add ("green");
	    if(indigoMonopoly) monops.add ("indigo");
		return monops;
	}
	
	public boolean allPlayerMonopoliesHasMortgagedProperties() throws PlaceDoesntExistsException{
		
		ArrayList<String> monopolyGroups = getMonopolies();
		int monopolyCount = 0;
		for (String group: monopolyGroups){
            if (groupHasMortgagedProperties(group)){
            	monopolyCount++;
            }
        }
		
		return (monopolyCount==monopolyGroups.size());
	}
	
	public boolean groupHasMortgagedProperties(String groupName){
		for (Block possession: possessions){
			if (possession.getGroup().equals(groupName)){
				return true;
			}
        }
		return false;
	}

	public void configureUnmortgageOption() throws UnmortgageablePlaceException {
        //adicionar opção de mortgage
        if (hasMortgagedProperties()){
            this.options.addUnmortgageOption();
        }
        else{
            this.options.removeUnmortgageOption();
        }
		
	}

	private boolean hasMortgagedProperties() throws UnmortgageablePlaceException {
		for (Block possession: possessions){
            if (possession.isMortgaged()){
                return true;
            }
        }
		return false;
	}

	public void removeCash(int debitCash) {
		this.cash -= debitCash;
	}
	
	

}
