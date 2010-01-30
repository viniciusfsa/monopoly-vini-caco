package openopoly.control.game;

import openopoly.control.business.BusinessManager;
import openopoly.*;
import openopoly.err.GameException;
import openopoly.util.Dice;
import java.util.ArrayList;
import openopoly.board.Block;
import openopoly.control.business.Bank;
import openopoly.err.PlayerDoesntExistsException;
import openopoly.util.CardEffects;

/**Classe que representa o controle principal do jogo
 *
 * @author Lucas
 * @author Sergio
 */
public class GameControl {

    private ArrayList<Player> players;
    private ArrayList<String> bankruptedPlayers;
    private boolean gameIsOver = false;
    private int currentPlayerIndex = 0;
    private Player currentPlayer;
    private BusinessManager businessManager;
    private boolean automaticBuy = false;
    private boolean doublesRule = false;
    

    /**
     * O construtor da classe tem a função de preencher a lista
     * das cores do jogador, a lista dos comandos e instanciar a lista de players.
     */
    public GameControl(int numPlayers, String[] names, String[] colors) {
        businessManager = new BusinessManager();
        players = new ArrayList<Player>();
        bankruptedPlayers = new ArrayList<String>();
        initPlayers(numPlayers, names, colors);
        businessManager.setCurrentPlayer(currentPlayer);
    }

    /**
     * Esse método instancia os jogadores
     */
    private void initPlayers(int numPlayers, String[] names, String[] colors) {
        for (int i = 0; i < numPlayers; i++) {
            Player p = new Player(names[i], colors[i]);
            players.add(p);
        }
        currentPlayer = players.get(currentPlayerIndex);
    }

    /**
     * Esse método passa a jogada para o proximo jogador da lista
     * @see Player
     */
    public void nextPlayer() {
        if (isDoublesRule()) {
            nextPlayerDoubleRules();
        } else {
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) {
                currentPlayerIndex = 0;
            }
            if (players.size() == 1) {
                setGameIsOver(true);
            }
            currentPlayer = players.get(currentPlayerIndex);
        }
    }

    /**
     * Esse método passa a jogada para o proximo jogador da lista com as regras
     * de dados iguais
     * @see Player
     */
    public void nextPlayerDoubleRules() {
        if (!currentPlayer.hasExtraTurn()) {
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) {
                currentPlayerIndex = 0;
            }
            if (players.size() == 1) {
                setGameIsOver(true);
            }
            currentPlayer = players.get(currentPlayerIndex);
        } else {
            currentPlayer.setExtraTurn(false);
        }
    }

    /**
     * Esse método lança a jogada dos dados aleatoriamente.
     */
    public void roll() throws GameException {
        int firstDice = Dice.roll();
        int secondDice = Dice.roll();

        if (!currentPlayer.isJailed() && businessManager.isActiveJail()) {
            movePlay(firstDice + secondDice);
        } else {
            movePlay(firstDice + secondDice);
        }

    }

    /**
     * Esse método lança a jogada dos dados de acordo com os valores do parametro.
     * @param dieOne valor do primeiro dado solicitado
     * @param dieTwo valor do segundo dado solicitado
     * @throws GameException caso os valores dos dados sejam inválidos
     */
    public void roll(int dieOne, int dieTwo) throws GameException {
        int firstDie = Dice.roll(dieOne);
        int secondDie = Dice.roll(dieTwo);

        if (businessManager.isActiveJail() || isDoublesRule()) {
            checkDoubles(firstDie, secondDie);
        } else {
            movePlay(dieOne + dieTwo);
        }
    }

    /**
     * Esse método implementa as regras quando o jogador tira dois dados iguais
     * @param dieOne valor do primeiro dado solicitado
     * @param dieTwo valor do segundo dado solicitado
     * @throws GameException caso os valores dos dados sejam inválidos
     */
    private void checkDoubles(int dieOne, int dieTwo) throws GameException {
        if (dieOne == dieTwo) {
            if (currentPlayer.isJailed()) {
                currentPlayer.setJailed(false);
                currentPlayer.resetJailCount();
                movePlay(dieOne + dieTwo);
            } else if (isDoublesRule()) {
                checkExtraTurns(dieOne + dieTwo);
            } else {
                movePlay(dieOne + dieTwo);
            }
        } else {
            currentPlayer.resetExtraTurnsCount();
            if (!currentPlayer.isJailed()) {
                movePlay(dieOne + dieTwo);
            } else {
                checkNumTurnsOnJail(dieOne + dieTwo);
            }
        }

    }

    /**
     * Esse método implementa a regra que quando um jogador tira dados iguais
     * três vezes seguidas ele deve ir direto para a prisão
     * @param dieTwo valor do segundo dado solicitado
     * @throws GameException caso os valores dos dados sejam inválidos
     */
    private void checkExtraTurns(int qtdMovement) throws GameException {
        if (currentPlayer.getExtraTurnCount() == 3) {
            currentPlayer.goToJail();
            currentPlayer.resetExtraTurnsCount();
        } else {
            currentPlayer.setExtraTurn(true);
            currentPlayer.incrementExtraTurnsCount();
            movePlay(qtdMovement);
        }
    }

    /**
     * Esse método implementa a funcionalidade de forçar o pagamento (caso seja
     * a terceira jogada seguida na prisão) para sair da prisão.
     * @param qtdMovement a quantidade de blocos a serem percorridos no tabuleiro
     * @throws GameException caso o jogador não estiver na prisão
     */
    private void checkNumTurnsOnJail(int qtdMovement) throws GameException {
        if (currentPlayer.getJailCount() == 3) {
            businessManager.payToGetOut(currentPlayer);
            movePlay(qtdMovement);
        } else {
            currentPlayer.incrementJailCount();
        }
    }

    /**
     * Esse método executa as ações automáticas decorrente do movimento do peão no tabuleiro
     * @param qtdMovement a quantidade de blocos a serem percorridos no tabuleiro
     */
    public void movePlay(int qtdMovement) throws GameException {
        currentPlayer.move(qtdMovement);
        businessManager.setCurrentPlayer(currentPlayer);
        businessManager.checkPlayerPassedByGo();
        businessManager.setLastDiceResult(qtdMovement);
        manage();
    }

    /**
     * Esse método chama o Gerenciador de negocios para verificações financeiras
     */
    public void manage() throws GameException {
        businessManager.toManage(automaticBuy);
    }

    /**
     * Esse método chama o Gerenciador de negocios para comprar alguma propriedade
     */
    public void buy() throws GameException {
        businessManager.needToBuy();
    }

    /**
     * Esse método chama o Gerenciador de negocios para construir habitações
     */
    public void build(int propertyID) throws GameException {
        businessManager.build(currentPlayer, propertyID);
    }

    /**
     * Esse método chama o Gerenciador de negocios para vender habitações
     * @param propertyID
     */
    public void sell(int propertyID) throws GameException {
        businessManager.sell(currentPlayer, propertyID);
    }

    /**
     * Esse método verifica se há algum jogador falido e retira da lista de jogadores ativos.
     */
    public void removeBankruptedPlayer() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            if (player.getCash() < 0) {
                freeProperties(player);
                players.remove(player);
                bankruptedPlayers.add(player.getPlayerName());
                currentPlayerIndex--;
            }
        }
    }

    /**
     * Devolve ao banco as propriedades de um jogador falido
     * @param player jogador falido
     */
    private void freeProperties(Player player) {
        ArrayList<Block> titles = player.getPossessions();
        for (Block b : titles) {
            b.setOwner(Bank.getBank());
        }
    }

    public Player getPlayer(String name) throws PlayerDoesntExistsException {
        Player p = null;

        for (Player player : players) {
            if (player.getPlayerName().equals(name)) {
                p = player;
            }
        }

        if (p != null) {
            return p;
        } else {
            throw new PlayerDoesntExistsException();
        }

    }

    /**
     * Este método solicita a cor do jogador de acordo com o nome
     * @param name nome do jogador solicitado
     * @return retorna a cor
     * @throws PlayerDoesntExistException caso o jogador não exista
     */
    public String getPlayerColor(String name) throws PlayerDoesntExistsException {
        String colorToken = null;
        for (Player player : players) {
            if (player.getPlayerName().equals(name)) {
                colorToken = player.getColor();
            }
        }
        if (colorToken == null) {
            throw new PlayerDoesntExistsException();
        }
        return colorToken;
    }

    /**
     * Este método solicita o dinheiro do jogador de acordo com o nome
     * @param name nome do jogador
     * @return o dinheiro do jogador
     * @throws PlayerDoesntExistException caso o jogador não exista
     */
    public int getPlayerMoney(String name) throws PlayerDoesntExistsException {
        int cash = 0;
        boolean exists = false;
        for (Player player : players) {
            if (player.getPlayerName().equals(name)) {
                cash = player.getCash();
                exists = true;
            }
        }
        if (!exists) {
            throw new PlayerDoesntExistsException();
        }
        return (int) cash;
    }

    /**
     * Este método solicita a posição do jogador de acordo com o nome
     * @param name nome do jogador
     * @return a posição do jogador
     * @throws PlayerDoesntExistException caso o jogador não exista
     * @throws GameException caso o jogador não esteja mais no jogo
     */
    public int getPlayerPosition(String name) throws PlayerDoesntExistsException, GameException {
        int pos = 0;
        if (bankruptedPlayers.contains(name)) {
            throw new GameException("Player no longer in the game");
        } else {
            boolean exists = false;
            for (Player player : players) {
                if (player.getPlayerName().equals(name)) {
                    pos = player.getPosGBoard();
                    exists = true;
                }
            }
            if (!exists) {
                throw new PlayerDoesntExistsException();
            }
        }
        return (int) pos;
    }

    /**
     * Este método solicita a lista de titulos do jogador de acordo com o nome
     * @param name nome do jogador
     * @return a lista de titulos do jogador
     * @throws PlayerDoesntExistException caso o jogador não exista
     */
    public String getPlayerDeeds(String name) throws PlayerDoesntExistsException {
        String deeds = null;
        for (Player player : players) {
            if (player.getPlayerName().equals(name)) {
                deeds = player.getDeeds();
            }
        }
        if (deeds == null) {
            throw new PlayerDoesntExistsException();
        }
        return deeds;
    }

    /**
     * O método verifica se o jogador está preso
     * @return true caso o jogador esteja preso, caso contrário false.
     */
    public boolean isJailed() {
        return currentPlayer.isJailed();
    }

    /**
     * O método implementa a funcionalidade de usar uma das cartas para sair da
     * prisão
     * @param cardType tipo da carta
     * @throws GameException caso o jogador não esteja preso ou caso não possua uma das cartas
     */
    public void useCard(String cardType) throws GameException {
        if (currentPlayer.isJailed()) {
            if (cardType.equals("chance")) {
                if (getCurrentPlayer().haveChancePrisonCard()) {
                    getCurrentPlayer().setChancePrisonCard(false);
                    getCurrentPlayer().setJailed(false);
                    CardEffects.getInstance().setPrisonChanceDrew(false);
                    GameChanceStack.getInstance().setPrisonChanceDrew(false);
                } else {
                    throw new GameException("Player doesn't have this card to use");
                }
            } else if (cardType.equals("chest")) {
                if (getCurrentPlayer().haveChestPrisonCard()) {
                    getCurrentPlayer().setChestPrisonCard(false);
                    getCurrentPlayer().setJailed(false);
                    CardEffects.getInstance().setPrisonChestDrew(false);
                    GameChestStack.getInstance().setPrisonChestDrew(false);
                } else {
                    throw new GameException("Player doesn't have this card to use");
                }
            }
        } else {
            throw new GameException("player is not on jail");
        }
    }

    /**
     * Esse método serve para tentar pagar pra sair da cadeia
     * @throws GameException caso o jogador não esteja preso.
     */
    public void pay() throws GameException {
        businessManager.payToGetOut(currentPlayer);
    }

    /**
     * Ativa as regras de sorte/revés
     */
    public void activateChancePlaces() {
        businessManager.setActiveChancePlaces(true);
    }

    /**
     * Ativa as regras de cofres comunitarios
     */
    public void activateChestPlaces() {
        businessManager.setActiveChestPlaces(true);
    }

    /**
     * Esse método ativa as regras da cadeia no jogo.
     */
    public void activateJail() {
        businessManager.setActiveJail(true);
    }

    /**
     * Esse método ativa as regras de compra de habitações no jogo.
     */
    public void activateBuild() {
        businessManager.setActiveBuild(true);
    }

    /**
     * Esse método ativa as regras de venda de habitações no jogo.
     */
    public void activateSell() {
        businessManager.setActiveSell(true);
    }

    /**
     * Esse método ativa as regras de dados iguais para cair na prisao
     * (três vezes jogadas iguais = prisao)
     */
    public void activateDoublesRule() {
        this.doublesRule = true;
    }

    /**
     * Ativa as regras do serviço publico
     */
    public void activateUtilityPlaces() {
        businessManager.setActiveUtilityPlaces(true);
    }

    //Getters and Setters
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public String getCurrentPlayerName() {
        return currentPlayer.getPlayerName();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getCommands() {
        return currentPlayer.getMenu().getMenu();
    }

    public boolean getGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }

    public void setAutomaticBuy(boolean automaticBuy) {
        this.automaticBuy = automaticBuy;
    }

    public boolean isAutomaticBuy() {
        return automaticBuy;
    }

    public boolean isActivateBuild() {
        return businessManager.isActiveBuild();
    }

    public boolean isDoublesRule() {
        return doublesRule;
    }

    public void setExtraRailroadEffect() {
        businessManager.setExtraRREffect(true);
    }

    public void activateMortgage() {
        this.businessManager.setActiveMortgage(true);
    }

    public void mortgage(int placeID) throws GameException, PlayerDoesntExistsException {
        this.businessManager.mortgage(placeID);
    }


    
}
