package openopoly.util;

import java.util.Random;
import openopoly.err.GameException;

/**Classe que representa os dados
 *
 * @author Lucas
 * @author Sergio
 */
public class Dice {

    private Dice() {
    }

    /**
     * Este método rola um dado
     * @return um numero entre 1-6
     */
    public static int roll() {
        Random r = new Random();
        return (r.nextInt(6) + 1);
    }

    /**
     * Este método simula uma jogada de dado com o numero solicitado
     * @param dieNum numero solicitado
     * @return um numero entre 1-6
     * @throws GameException quando o numero solicitado esta fora dos limites permitidos para o numero do dado
     */
    public static int roll(int dieNum) throws GameException {
        if(dieNum < 1 || dieNum > 6){
            throw new GameException("Invalid die result");
        }
        return dieNum;
    }
    
}
