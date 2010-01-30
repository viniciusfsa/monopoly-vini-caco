package openopoly.err;

/** Essa classe representa a exceção executada quando
 * não existe um bloco do tabuleiro
 * 
 * @author Lucas
 * @author Sergio
 */
public class PlaceDoesntExistException extends GameException {

    /**
     * O construtor da classe configura uma mensagem especifica da exceção
     */
    public PlaceDoesntExistException() {
        super("Place doesn't exist");
    }

}
