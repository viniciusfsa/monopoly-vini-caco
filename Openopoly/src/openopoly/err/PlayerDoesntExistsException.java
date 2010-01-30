package openopoly.err;

/** Essa classe representa a exceção executada quando
 * não existe um jogador na lista
 * 
 * @author Lucas
 * @author Sergio
 */
public class PlayerDoesntExistsException extends GameException{

    /**
     * O construtor da classe configura uma mensagem especifica da exceção
     */
    public PlayerDoesntExistsException() {
        super("Player doesn't exist");
    }

}
