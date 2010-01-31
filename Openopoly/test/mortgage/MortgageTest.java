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
    int unmortgageablePlaces[] = {2, 4, 7, 10, 17, 20, 22, 30, 33, 36, 38, 40};
    int mortgageablePlaces[] = {1, 3, 6, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 21,
                            23, 24, 25, 26, 27, 28, 29, 31, 32, 34, 35, 37, 39 };
    
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

    protected void moveCurrentPlayer(int pos1, int pos2) throws GameException{
        this.game.roll(pos1, pos2);
    }

    protected void buyCurrentPlayerPosition() throws GameException{
        this.game.buy();
    }

    protected void nextPlayer(){
        this.game.nextPlayer();
    }

    protected void mortgageCurrentPlace() throws GameException {
        this.game.mortgage(this.game.getCurrentPlayer().getPosGBoard());
    }


    protected void mortgagePlaceAtStart(int placeID, String message){
        this.startGame2PLayers();

        String exceptionMessage = null;
        try{
            this.game.mortgage(placeID);
        }
        catch (GameException ge){
            exceptionMessage = ge.getMessage();
        }
        
        assertEquals(exceptionMessage, message);
    }
    
    
    protected void mortgagePlacesAtStart(int [] placeIDs, String message){
        for (int i = 0; i<placeIDs.length;i++){
            this.mortgagePlaceAtStart(placeIDs[i], message);
        }
    }


   

    public void testMortgageLeftBounds(){
        this.mortgagePlaceAtStart(0, "Place doesn't exist");
    }

    public void testMortgageRightBounds(){
        this.mortgagePlaceAtStart(41, "Place doesn't exist");
    }

    public void testMortgageNegative(){
        this.mortgagePlaceAtStart(-1, "Place doesn't exist");
    }


    public void testMorgageNoDeed(){
        this.mortgagePlaceAtStart(1, "Unavailable command");
    }

    public void testAllUnmortgage(){
        this.mortgagePlacesAtStart(this.unmortgageablePlaces, "Unavailable command");
    }

    public void testMortgageNoDeed(){
        this.mortgagePlaceAtStart(1, "Unavailable command");
    }

    public void testAllMortgageNoDeeds(){
        this.mortgagePlacesAtStart(this.mortgageablePlaces, "Unavailable command");
    }



    //testes com compra de terreno

    public void testMortgageBuy() throws GameException{
        this.startGame2PLayers();
        this.moveCurrentPlayer(1,2);
        this.buyCurrentPlayerPosition();
        this.mortgageCurrentPlace();
        this.nextPlayer();
        
    }







    

}
