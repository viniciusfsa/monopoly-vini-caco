package openopoly.control.business;

import openopoly.Player;

/** Classe que representa o banco como um Proprietario
 *
 * @author Lucas
 * @author Sergio
 */
public class Bank {
    private static Player bank;

    /**
     * O construtor tem a função de instanciar um Jogador de nome "bank"
     */
    public Bank() {
        bank = new Player("bank", "");
        bank.setIsBank(true);
    }

    public static Player getBank() {
        return bank;
    }


    

    
}
