/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mortgage;

import junit.framework.TestCase;
import openopoly.control.game.GameControl;
import openopoly.err.GameException;

/**
 *
 * @author amferraz
 */
public class MortgageTest extends TestCase {

    GameControl game = null;
    
    public MortgageTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    protected void startGame(int numPlayers, String[] names, String[] colors){
        game = new GameControl(numPlayers, names, colors);
    }

    protected void startGame2PLayers(){
        String[] players= {"player1","player2"};
        String[] colors = {"black","white"};
        startGame(2,players, colors);
    }


    protected void mortgagePlace(int placeID, String message){
        this.startGame2PLayers();

        try{
            this.game.mortgage(placeID);
        }
        catch (GameException ge){
            assertEquals(ge.getMessage(), message);
        }
        
    }

    public void testMortgageLeftBounds(){
        this.mortgagePlace(0, "Place doesn't exist");
    }

    public void testMortgageRightBounds(){
        this.mortgagePlace(41, "Place doesn't exist");
    }

    public void testMortgageNegative(){
        this.mortgagePlace(-1, "Place doesn't exist");
    }

    public void testUnmortgageble(){
        int unmortgageble[] = {2, 4, 7, 10, 17, 20, 22, 30, 33, 36, 38, 40};

        for (int i = 0; i<unmortgageble.length;i++){
            this.mortgagePlace(i, "This place can't be mortgaged");
        }
    }





    

}
