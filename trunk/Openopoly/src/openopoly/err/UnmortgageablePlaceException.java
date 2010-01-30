/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package openopoly.err;

/**
 *
 * @author amferraz
 */
public class UnmortgageablePlaceException extends GameException{

    /**
     * O construtor da classe configura uma mensagem especifica da exceção
     */
    public UnmortgageablePlaceException() {
        super("This place can't be mortgaged");
    }

}
