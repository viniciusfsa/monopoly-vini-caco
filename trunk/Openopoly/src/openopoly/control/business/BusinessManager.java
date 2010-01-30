package openopoly.control.business;

import openopoly.*;
import openopoly.board.GameBoard;
import openopoly.board.Railroad;
import openopoly.board.Block;
import openopoly.board.Property;
import openopoly.board.Tax;
import openopoly.board.Utility;
import openopoly.control.game.GameChanceStack;
import openopoly.control.game.GameChestStack;
import openopoly.err.GameException;
import openopoly.err.PlaceDoesntExistsException;
import openopoly.err.UnavailableCommandException;
import openopoly.err.UnmortgageablePlaceException;

/** Classe que representa o Gerenciador de Negócios, ao qual
 *  controla as transações financeiras do jogo
 *
 * @author Lucas
 * @author Sergio
 */
public class BusinessManager {

    private Player currentPlayer;
    private Property p;
    private Railroad r;
    private Utility u;
    private Tax t;
    private boolean automaticBuy;
    private boolean activeChancePlaces;
    private boolean activeChestPlaces;
    private boolean activeJail;
    private boolean activeUtilityPlaces;
    private boolean activeBuild;
    private boolean activeSell;
    private boolean activeMortgage;
    private Bank bank;
    private boolean extraRREffect;
    private int lastDiceResult;
    private int numHouses = 32;
    private int numHotel = 12;

    /**
     * O construtor da classe cria um Bank
     * @see Bank
     */
    public BusinessManager() {
        bank = new Bank();
    }

    /**
     * Esse método executa os procedimentos quando um jogador
     * situa-se em um novo bloco do tabuleiro
     * @param auto indica se a compra de propriedades esta no automático
     */
    public void toManage(boolean auto) throws GameException {
        setAutomaticBuy(auto);
        if (isProperty() || isRailRoad()) {
            availability();
        } else if (isTax()) {
            toTax();
        } else if (isChance()) {
            if (isActiveChancePlaces()) {
                getChanceCard();
            }
        } else if (isChest()) {
            if (isActiveChestPlaces()) {
                getChestCard();
            }
        } else if (isGoToJail()) {
            if (isActiveJail()) {
                currentPlayer.goToJail();
            }
        } else if (isUtility()) {
            if (isActiveUtilityPlaces()) {
                availability();
            }
        }
    }

    /**
     * Esse método verifica se o jogador passou pelo ou ficou no bloco Go
     * Se verdadeiro ele ganha $200 referente ao salario
     */
    public void checkPlayerPassedByGo() {
        if (getCurrentPlayer().needSallary()) {
            getCurrentPlayer().setCash(getCurrentPlayer().getCash() + 200);
            getCurrentPlayer().setNeedSallary(false);
        }
    }

    /**
     * Esse método verifica se a disponibilidade da propriedade
     */
    private void availability() throws GameException {
        if (hasOwner()) {
            payRent();
        } else if (automaticBuy) {
            requestToBuy();
        }
    }

    /**
     * Esse método executa a solicitação de compra de uma propriedade
     * @throws GameException caso o jogador não tenha dinheiro suficiente
     */
    private void requestToBuy() throws GameException {
        Block b = getGameBoard().getBlock(currentPlayer.getPosGBoard());
        int price = b.getPrice();
        if (hasCash(price)) {
            buyProperty();
        } else {
            throw new GameException("Not enough money");
        }
    }

    /**
     * Esse método verifica se o jogador tem dinheiro suficiente para construir
     * uma habitação. Se ele possuir dinheiro suficiente a habitação é construida
     * @param currentPlayer Player atual
     * @throws GameException caso o jogador não tenha dinheiro suficiente
     */
    private void requestToBuild(Player currentPlayer, int propertyID) throws GameException {
        Property property = (Property) getGameBoard().getBlock(propertyID);
        int cash = currentPlayer.getCash();
        int price = property.getHouse();

        if (property.hasHotel()) {
            throw new GameException("No further buildings on this property");
        } else {
            if (hasCash(price, currentPlayer)) {
                property.addHouses();
                if (activeSell) {
                    currentPlayer.configureSellMenu();
                }
                checkEnoughHabToBuild(property);
                if (currentPlayer.hasBadHabDistribution(property.getGroup())) {
                    property.removeHouses();
                    throw new GameException("Uneven distribution of houses");
                } else {
                    currentPlayer.setCash(cash - price);
                }
            } else {
                throw new GameException("Not enough money");
            }
        }

    }

    /**
     * Esse método verifica se o jogador tem alguma propriedade com
     * habitação para vender. Se ele possuir a habitação é vendida.
     * @param currentPlayer Player atual
     * @throws GameException caso não exista o bloco
     */
    private void requestToSell(Player currentPlayer, int propertyID) throws GameException {
        Property property = (Property) getGameBoard().getBlock(propertyID);
        int cash = currentPlayer.getCash();
        int price = property.getHouse();

        if (property.getHouseQtd() == 0) {
            throw new GameException("No house is built on this property");
        } else {
            if (replacedHousesAndHotels(property)) {
                property.removeHouses();
                if (currentPlayer.hasBadHabDistribution(property.getGroup())) {
                    property.addHouses();
                    throw new GameException("Uneven distribution of houses");
                } else {
                    currentPlayer.setCash(cash + price / 2);
					currentPlayer.configureSellMenu();
                }
            } else {
                throw new GameException("Not enough houses on the bank");
            }
        }
    }

    /**
     * Esse método verifica se há casas ou hoteis suficientes para o jogador
     * @param property propriedade na qual será construida alguma habitação
     * @throws GameException caso não há casas ou hoteis suficientes
     */
    private void checkEnoughHabToBuild(Property property) throws GameException {
        if (property.hasHotel()) {
            numHotel--;
            numHouses += 4;
        } else {
            numHouses--;
        }
        if (numHouses < 0) {
            property.removeHouses();
            numHouses++;
            throw new GameException("No more houses available for sale");
        } else if (numHotel < 0) {
            property.removeHouses();
            numHotel++;
            throw new GameException("No more hotels available for sale");
        }
    }

    /**
     * Esse método repõe casas ou hoteis que foram vendidos.
     * @param property propriedade na qual será vendida alguma habitação
     */
    public boolean replacedHousesAndHotels(Property property) {
        if (property.hasHotel()) {
            if(numHouses < 4){
                return false;
            }else{
                numHotel++;
                numHouses -=4;
                return true;
            }
        } else {
            numHouses++;
            return true;
        }
    }

    /**
     * Esse método executa o pagamento do aluguel do jogador atual
     * ao dono da propriedade
     */
    private void payRent() throws GameException {
        if (!(getCurrentBlock().getOwner() == currentPlayer)) {
            int payerCash = currentPlayer.getCash();
            int paymentValor = 0;
            int paymentModifier = 1;
            if (isProperty()) {
                p = (Property) getCurrentBlock();
                if (isActiveBuild()) {
                    paymentValor = p.getCurrentRentBuildRules();
                } else {
                    paymentValor = p.getCurrentRent();
                }

            } else if (isRailRoad()) {
                if (extraRREffect) {
                    paymentModifier = 2;
                }
                r = (Railroad) getCurrentBlock();
                paymentValor = r.getCurrentRide() * paymentModifier;
                extraRREffect = false;
            } else if (isUtility() && isActiveUtilityPlaces()) {
                u = (Utility) getCurrentBlock();
                paymentValor = lastDiceResult * u.getMultiplier();
            }

            checkBankruptcy(payerCash, paymentValor);

        }

    }

    /**
     * Esse método checa se o pagamento do valor pelo jogador atual
     * o levará a falir
     * @param payerCash o dinheiro do jogador
     * @param paymentValor o valor do pagamento
     */
    private void checkBankruptcy(int payerCash, int paymentValor) throws GameException {
        int receiverCash = getCurrentBlock().getOwner().getCash();

        if (payerCash < paymentValor) {
            getCurrentBlock().getOwner().setCash(receiverCash + payerCash);
            getCurrentPlayer().setCash(payerCash - paymentValor);
        } else {
            currentPlayer.setCash(payerCash - paymentValor);
            getCurrentBlock().getOwner().setCash(receiverCash + paymentValor);
        }
    }

    /**
     * Esse método executa a compra efetiva de uma propriedade.
     */
    private void buyProperty() throws GameException {
        int cash = getCurrentPlayer().getCash();
        Block b = getGameBoard().getBlock(currentPlayer.getPosGBoard());
        int price = b.getPrice();

        getCurrentBlock().setOwner(currentPlayer);
        currentPlayer.addPossession(getCurrentPlayer().getPosGBoard());
        currentPlayer.setCash(cash - price);
    }

    /**
     * Esse método executa verificação da disponibilidade do titulo de
     * qualquer bloco do tabuleiro
     * @throws GameException caso o tiltulo não esteja mais disponivel
     * @throws GameException caso o bloco não haja um titulo pra ser comprado
     */
    public void needToBuy() throws GameException {
        if (isProperty() || isRailRoad() || (isUtility() && isActiveUtilityPlaces())) {
            if (hasOwner()) {
                throw new GameException("Deed for this place is not for sale");
            } else {
                requestToBuy();
                if (activeBuild) {
                    currentPlayer.checkPlayerMonopoly();
                    currentPlayer.configureBuildMenu();
                }
            }
        } else if (isUtility()) {
            throw new GameException("Deed for this place is not for sale");
        } else {
            throw new GameException("Place doesn't have a deed to be bought");
        }
    }

    /**
     * Esse método implementa a funcionalidade de pagar para sair da cadeia
     */
    public void payToGetOut(Player currentPlayer) throws GameException {
        if (currentPlayer.isJailed()) {
            int cash = currentPlayer.getCash();
            currentPlayer.setCash(cash - 50);
            currentPlayer.resetJailCount();
            currentPlayer.setJailed(false);
        } else {
            throw new GameException("player is not on jail");
        }
    }

    /**
     * Esse metodo inicia a compra de uma habitação
     * @param propertyID ID da propriedade
     */
    public void build(Player currentPlayer, int propertyID) throws GameException {
        if (activeBuild) {
            if (checkBuildPreConditions(propertyID, currentPlayer)) {
                requestToBuild(currentPlayer, propertyID);
            }
        }
    }

    /**
     * Esse metodo inicia a compra de uma habitação
     * @param propertyID ID da propriedade
     */
    public void sell(Player currentPlayer, int propertyID) throws GameException {
        if (activeSell) {
            if (checkSellPreConditions(propertyID, currentPlayer)) {
                requestToSell(currentPlayer, propertyID);
            }
        }
    }

    /**
     * Esse método verifica se as condições mínimas para construir
     * habitações estão sendo satisfeitas
     * @param propertyID ID da propriedade
     * @param currentPlayer o player que esta sendo verificado
     * @return true caso todas as condições forem aceitas
     * @throws PlaceDoesntExistsException caso não exista o bloco
     * @throws GameException caso não exista o comando build, caso o jogador deseje construir fora de uma propriedade e caso o jogador não for o dono da propriedade
     */
    public boolean checkBuildPreConditions(int propertyID, Player currentPlayer) throws PlaceDoesntExistsException, GameException {
        if (!currentPlayer.getMenu().isOption("build")) {
            throw new GameException("Unavailable command");
        } else {
            if (!getGameBoard().isProperty(propertyID)) {
                throw new GameException("Can only build on properties");
            } else {
                if (!currentPlayer.getPossessions().contains(getGameBoard().getBlock(propertyID))) {
                    throw new GameException("Player is not the owner of this property");
                } else {
                    if (!currentPlayer.hasMonopolyGroup(getGameBoard().getBlockGroup(propertyID))) {
                        throw new GameException("Doesn't hold monopoly for this group");
                    } else {
                        if (currentPlayer.checkFullBuild(getGameBoard().getBlockGroup(propertyID))) {
                            throw new GameException("Unavailable command");
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Esse método verifica se as condições mínimas para vender
     * habitações estão sendo satisfeitas
     * @param propertyID ID da propriedade
     * @param currentPlayer o player que esta sendo verificado
     * @return true caso todas as condições forem aceitas
     * @throws PlaceDoesntExistsException caso não exista o bloco
     * @throws GameException caso não exista o comando sell, caso o jogador deseje construir fora de uma propriedade e caso o jogador não for o dono da propriedade
     */
    public boolean checkSellPreConditions(int propertyID, Player currentPlayer) throws PlaceDoesntExistsException, GameException {
        if (!currentPlayer.getMenu().isOption("sell")) {
            throw new GameException("Unavailable command");
        } else {
            if (!getGameBoard().isProperty(propertyID)) {
                throw new GameException("Can only sell houses built on properties");
            } else {
                if (!currentPlayer.getPossessions().contains(getGameBoard().getBlock(propertyID))) {
                    throw new GameException("Player is not the owner of this property");
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * Esse método executa a cobrança de um imposto ao jogador
     */
    private void toTax() throws GameException {
        int cash = currentPlayer.getCash();
        t = (Tax) getCurrentBlock();
        int tax = t.getTax(currentPlayer.getPosGBoard());
        currentPlayer.setCash(cash - tax);
    }

    /**
     * Esse método testa se o bloco atual é uma propriedade
     */
    private boolean isProperty() throws PlaceDoesntExistsException {
        return getGameBoard().isProperty(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Esse método testa se o bloco atual é uma ferrovia
     */
    private boolean isRailRoad() throws PlaceDoesntExistsException {
        return getGameBoard().isRailRoad(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Esse método testa se o bloco atual é um imposto
     */
    private boolean isTax() throws PlaceDoesntExistsException {
        return getGameBoard().isTax(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Esse método testa se o bloco atual é um serviço publico
     */
    private boolean isUtility() throws PlaceDoesntExistsException {
        return getGameBoard().isUtility(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Esse método testa se o bloco atual é do tipo Chance
     */
    private boolean isChance() throws PlaceDoesntExistsException {
        return getGameBoard().isChance(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Esse método testa se o bloco atual é do tipo Chest
     */
    private boolean isChest() throws PlaceDoesntExistsException {
        return getGameBoard().isChest(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Esse método testa se o bloco atual é  Cadeia
     */
    private boolean isGoToJail() throws PlaceDoesntExistsException {
        return getGameBoard().isGoToJail(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Esse método retorna o bloco atual de acordo com a posição atual do jogador
     * @return o bloco
     */
    public Block getCurrentBlock() throws GameException {
        return getGameBoard().getBlock(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Esse método verifica se o bloco atual tem dono.
     * @return true caso tiver um dono, caso contrário false
     */
    private boolean hasOwner() {
        return getGameBoard().hasOwner(getCurrentPlayer().getPosGBoard());
    }

    /**
     * Verifica se as regras da cadeia estão ativadas.
     * @return true caso as regras estiverem ativadas, false caso contrário
     */
    public boolean isActiveJail() {
        return activeJail;
    }

    /**
     * Verifica se as regras da cartas(chance, sorte/revés) estão ativadas.
     * @return true caso as regras estiverem ativadas, false caso contrário
     */
    public boolean isActiveChancePlaces() {
        return activeChancePlaces;
    }

    /**
     * Verifica se as regras da cartas(community chest, cartas comunitarias) estão ativadas.
     * @return true caso as regras estiverem ativadas, false caso contrário
     */
    public boolean isActiveChestPlaces() {
        return activeChestPlaces;
    }

    /**
     * Verifica se os Serviços Publicos estão ativadas.
     * @return true caso as regras estiverem ativadas, false caso contrário
     */
    public boolean isActiveUtilityPlaces() {
        return activeUtilityPlaces;
    }

    //Setters and Getters
    public void setActiveJail(boolean activateJail) {
        this.activeJail = activateJail;
    }

    public void setActiveChancePlaces(boolean activeChancePlaces) {
        this.activeChancePlaces = activeChancePlaces;
    }

    public void setActiveChestPlaces(boolean activeChestPlaces) {
        this.activeChestPlaces = activeChestPlaces;
    }

    public void setActiveUtilityPlaces(boolean activeUtilityPlaces) {
        this.activeUtilityPlaces = activeUtilityPlaces;
    }

    public void setActiveMortgage(boolean activeMortgage){
        this.activeMortgage = activeMortgage;
    }

    public GameBoard getGameBoard() {
        return GameBoard.getInstance();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private boolean hasCash(int payment) {
        return (currentPlayer.getCash() >= payment);
    }

    private boolean hasCash(int payment, Player currentPlayer) {
        return (currentPlayer.getCash() >= payment);
    }

    public void setAutomaticBuy(boolean automaticBuy) {
        this.automaticBuy = automaticBuy;
    }

    public Bank getBank() {
        return bank;
    }

    private void getChanceCard() throws GameException {
        GameChanceStack.getInstance().chanceDrawCard().effect();
    }

    private void getChestCard() throws GameException {
        GameChestStack.getInstance().chestDrawCard().effect();
    }

    public void setExtraRREffect(boolean extraEffect) {
        this.extraRREffect = extraEffect;
    }

    public void setActiveBuild(boolean activeBuild) {
        this.activeBuild = activeBuild;
    }

    public boolean isActiveBuild() {
        return activeBuild;
    }

    public void setActiveSell(boolean activeSell) {
        this.activeSell = activeSell;
    }

    public boolean isActiveSell() {
        return activeSell;
    }

    public void setLastDiceResult(int lastDiceResult) {
        this.lastDiceResult = lastDiceResult;
    }


    //Hipoteca


    public void mortgage(int placeID) throws PlaceDoesntExistsException, UnmortgageablePlaceException, UnavailableCommandException{

        Block block = this.getGameBoard().getBlock(placeID);

        if (block.isMortgageable()){
            if (block.getOwner()==currentPlayer){
                throw new UnavailableCommandException();
            }
            else{
                throw new UnavailableCommandException();
            }
        }
        else{
            throw new UnavailableCommandException();
        }

    }



    /**
     * @return the activeMortgage
     */
    public boolean isActiveMortgage() {
        return activeMortgage;
    }
}
