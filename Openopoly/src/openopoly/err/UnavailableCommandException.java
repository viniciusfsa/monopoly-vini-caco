/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openopoly.err;

/**
 *
 * @author amferraz
 */
public class UnavailableCommandException extends GameException{

    /**
     * O construtor da classe configura uma mensagem especifica da exceção
     */
    public UnavailableCommandException() {
        super("Unavailable command");
    }

}
