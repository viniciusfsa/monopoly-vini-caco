package openopoly;

import openopoly.board.GameBoard;
import openopoly.control.game.GameChanceStack;
import openopoly.control.game.GameChestStack;
import openopoly.control.game.GameControl;
import openopoly.err.GameException;
import openopoly.util.CardEffects;

/** Classe principal do jogo
 *
 * @author Lucas
 * @author Sergio
 */
public class Openopoly {

    GameControl gameControl = null;

    /**
     * Construtor da classe não recebe nenhum parâmetro.
     */
    public Openopoly() {
    }

//    public static void main(String[] args) throws GameException {
//        Openopoly o = new Openopoly();
//        o.createGame(2, "{player1,player2}", "{black,white}");
//        o.getGameControl().activateBuild();
//        o.getGameControl().activateSell();
//
//        o.getGameControl().getCurrentPlayer().setCash(50000);
//        o.getGameControl().roll(1, 2);
//        o.getGameControl().buy();
//        System.out.println(o.getGameControl().getCommands());
//        System.out.println(o.getGameControl().getCurrentPlayer().getDeeds());
//        o.getGameControl().roll(6, 6);
//        o.getGameControl().roll(6, 6);
//        o.getGameControl().roll(6, 6);
//        o.getGameControl().roll(1, 1);
//        o.getGameControl().buy();
//        System.out.println(o.getGameControl().getCommands());
//        System.out.println(o.getGameControl().getCurrentPlayer().getDeeds());
//        o.getGameControl().build(1);
//        System.out.println(((Property)o.getGameBoard().getBlock(1)).getHouseQtd());
//        System.out.println(o.getGameControl().getCommands());
//    }

    /**
     * Esse método cria um novo jogo.
     *
     * @param numPlayers número de jogadores
     * @param playerNames String no formato {player1,player2,...,player8}
     * @param tokenColor String no formato {color1,color2,...,color8}
     * @throws GameException caso os parâmetros sejam inválidos
     */
    public void createGame(int numPlayers, String playerNames, String tokenColor) throws GameException {

        validNumberPlayers(numPlayers);
        String[] names = toArray(playerNames);
        String[] colors = toArray(tokenColor);
        checkConcordanceName(numPlayers, names.length);
        checkConcordanceColor(numPlayers, colors.length);
        checkDuplicatedData(names, colors);
        checkPlayerNames(names);
        checkColorNames(colors);
        gameControl = new GameControl(numPlayers, names, colors);
        CardEffects.getInstance().setGameControl(gameControl);
    }

    /**
     * Esse método verifica se a quantidade de players é válida.
     * @param num número de players
     * @throws GameException caso o número de player seja inválido
     */
    private void validNumberPlayers(int num) throws GameException {
        if (num < 2 || num > 8) {
            throw new GameException("Invalid number of players");
        }
    }

    /**
     * Verifica se o número de jogadores é correspondente a quantidade de nomes
     * na String de entrada.
     * @param numPlayers número de jogadores
     * @param numNamePlayers String no formato {player1,player2,...,player8}
     * @throws GameException caso os argumentos não sejam correspondentes.
     */
    private void checkConcordanceName(int numPlayers, int numNamePlayers) throws GameException {
        if (numNamePlayers > numPlayers) {
            throw new GameException("Too many player names");
        } else if (numNamePlayers < numPlayers) {
            throw new GameException("Too few player names");
        }
    }

    /**
     * Verifica se o número de jogadores é correspondente a quantidade de cores
     * na String de entrada.
     * @param numPlayers número de jogadores
     * @param numColor String no formato {color1,color2,...,color8}
     * @throws GameException caso os argumentos não sejam correspondentes.
     */
    private void checkConcordanceColor(int numPlayers, int numColor) throws GameException {
        if (numColor > numPlayers) {
            throw new GameException("Too many token colors");
        } else if (numColor < numPlayers) {
            throw new GameException("Too few token colors");
        }
    }

    /**
     * Verifica se há dados duplicados
     * @param tokens vetor de strings com dados
     * @return retorna true caso existam dados duplicados. Caso contrário false.
     */
    private boolean hasDuplicatedData(String[] tokens){
        String temp[] = tokens;
        for (int j = 0; j < temp.length; j++) {
            String aux = temp[j];
            for (int k = j + 1; k < temp.length; k++) {
                String aux2 = temp[k];
                if (aux.equals(aux2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se há algum dado duplicado no array de cores e de nomes.
     * @param names vetor de nomes
     * @param colors vetor de cores
     * @throws GameException caso exista algum dado duplicado.
     */
    private void checkDuplicatedData(String[] names, String[] colors) throws GameException {
        if (hasDuplicatedData(names)) {
            throw new GameException("There mustn't be repeated player names");
        }
        if (hasDuplicatedData(colors)) {
            throw new GameException("There mustn't be repeated token colors");
        }
    }

    /**
     * Verifica se há algum nome de player inválido
     * @param tokens vetor de nomes
     * @throws GameException caso exista algum nome inválido
     */
    private void checkPlayerNames(String[] tokens) throws GameException {
        String temp[] = tokens;
        for (int i = 0; i < temp.length; i++) {
            String name = temp[i];
            if (name.equalsIgnoreCase("bank")) {
                throw new GameException("Invalid player name");
            }
        }
    }

    /**
     * Verifica se há alguma cor inválida
     * @param tokens vetor de cores
     * @throws GameException caso exista alguma cor inválida
     */
    private void checkColorNames(String[] tokens) throws GameException {
        String temp[] = tokens;
        String colors = "black|white|red|green|blue|yellow|orange|pink";

        for (int i = 0; i < temp.length; i++) {
            String name = temp[i];
            if (!colors.contains(name)) {
                throw new GameException("Invalid token color");
            }
        }
    }

    /**
     * Esse método finaliza o jogo.
     * @throws GameException caso não exista nenhum jogo criado
     */
    public void quitGame() throws GameException {
        if (gameControl == null) {
            throw new GameException("There's no game to quit");
        }else{
            gameControl = null;
            getGameBoard().resetGameBoard();
            resetStacksAndEffects();
        }
    }

    /**
     * Separa uma String em um vetor correspondente.
     * @param str String de entrada
     * @return vetor de saída correspondente.
     */
    private String[] toArray(String str) {
        String[] tokens = str.substring(1, str.length() - 1).split(",");
        return tokens;
    }


    //Getters and Setters
    public GameBoard getGameBoard() {
        return GameBoard.getInstance();
    }

    public GameControl getGameControl() {
        return gameControl;
    }

    public void resetStacksAndEffects(){
        GameChanceStack.getInstance().resetChanceStack();
        GameChestStack.getInstance().resetChestStack();
        CardEffects.getInstance().resetEffects();
    }
}
