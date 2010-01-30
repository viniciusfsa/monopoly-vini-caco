package openopoly.err;

/** Essa classe representa a exceção executada quando
 * não existe um jogador na lista
 * 
 * @author Lucas
 * @author Sergio
 */
public class PlayerDoesntExistException extends GameException{

    /**
     * O construtor da classe configura uma mensagem especifica da exceção
     */
    public PlayerDoesntExistException() {
        super("Player doesn't exist");
    }

}
