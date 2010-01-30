package openopoly.err;

/** Essa classe é a classe que gera exceções durante o jogo
 *
 * @author Lucas
 * @author Sergio
 */
public class GameException extends Exception {

    /**
     * O contrutor da classe que configura uma mensagem de erro
     * para a exceção
     * @param errorMsg a mensagem de erro a ser exibido
     */
    public GameException(String errorMsg) {
        super(errorMsg);
    }
}
