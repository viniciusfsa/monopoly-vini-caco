package openopoly.util;

import java.util.ArrayList;
import openopoly.Player;
import openopoly.board.GameBoard;
import openopoly.control.game.GameChanceStack;
import openopoly.control.game.GameChestStack;
import openopoly.control.game.GameControl;
import openopoly.err.GameException;
import openopoly.err.PlaceDoesntExistsException;

/**
 *
 * @author Lucas
 * @author Sergio
 */
public class CardEffects {
    
    private static CardEffects instance;
    private GameControl control;
    private boolean prisonChanceDrew, prisonChestDrew;
    
    private CardEffects(){
    }
    
    public static CardEffects getInstance(){
        if(instance == null){
            instance = new CardEffects();
        }
        return instance;
    }

    public void goTo(int posGB) throws GameException{
        control.getCurrentPlayer().setPosGameBoard(posGB);
        control.manage();
    }

    public void modifyCash(int cash){
        control.getCurrentPlayer().setCash(control.getCurrentPlayer().getCash() + cash);
    }
    
    public void prisonCardChance() throws GameException{
        if(prisonChanceDrew){
            throw new GameException("This card is already possessed by a player");
        }else{
            control.getCurrentPlayer().setChancePrisonCard(true);
            GameChanceStack.getInstance().setPrisonChanceDrew(true);
            setPrisonChanceDrew(true);
        }
    }

    public void prisonCardChest() throws GameException{
        if(prisonChestDrew){
            throw new GameException("This card is already possessed by a player");
        }else{
            control.getCurrentPlayer().setChestPrisonCard(true);
            GameChestStack.getInstance().setPrisonChestDrew(true);
            setPrisonChestDrew(true);
        }
    }

    public void payToAll(int cash){
        ArrayList<Player> list = control.getPlayers();
        int totalCash = (list.size()-1)*cash;
        modifyCash(-totalCash);
        //checkAndSolveBankrupt(player, cash);
        for (Player player : list) {
            if(player != control.getCurrentPlayer()){
                player.setCash(player.getCash() + cash);
            }
        }
    }

    public void collectFromAll(int cash){
        ArrayList<Player> list = control.getPlayers();
        int totalCash = (list.size()-1)*cash;
        for (Player player : list) {
            if(player != control.getCurrentPlayer()){
                player.setCash(player.getCash() - cash);
            }
        }
        modifyCash(totalCash);
    }

    public void goUntilReach(int posGB) throws GameException{
        int actualPos = control.getCurrentPlayer().getPosGBoard();
        int moveBlocks;
        if(actualPos > posGB){
             moveBlocks = posGB + 40 - actualPos;
        }else{
            moveBlocks = posGB - actualPos;
        }
        control.movePlay(moveBlocks);
    }

    public void repair(int house, int hotel) throws PlaceDoesntExistsException{
        modifyCash(-(house*control.getCurrentPlayer().getPlayersHouse()));
        modifyCash(-(hotel*control.getCurrentPlayer().getPlayersHotel()));
    }

    public void goToPrison() throws GameException{
        goTo(10);
        control.getCurrentPlayer().setJailed(true);
    }

    public void back() throws GameException{
        control.movePlay(-3);
    }

    public void nextRailroad() throws GameException{
        int nextRRpos = GameBoard.getInstance().nextRailroad(control.getCurrentPlayer().getPosGBoard());
        control.getCurrentPlayer().setPosGameBoard(nextRRpos);
        control.setExtraRailroadEffect();
        control.manage();
    }

    public void nextUtility() throws GameException{
        int nextUtilityPos = GameBoard.getInstance().nextUtility(control.getCurrentPlayer().getPosGBoard());
        control.getCurrentPlayer().setPosGameBoard(nextUtilityPos);
        if(GameBoard.getInstance().hasOwner(nextUtilityPos)){
            int result = Dice.roll();
            result += Dice.roll();
            modifyCash(-result*10);
            //checkAndSolveBankrupt(player, cash);
            GameBoard.getInstance().getBlock(nextUtilityPos).getOwner().setCash(result*10);
        }else{
            control.manage();
        }
    }

    public void setGameControl(GameControl control){
        this.control = control;
    }

    public void setPrisonChanceDrew(boolean prisonChanceDrew) {
        this.prisonChanceDrew = prisonChanceDrew;
    }

    public void setPrisonChestDrew(boolean prisonChestDrew) {
        this.prisonChestDrew = prisonChestDrew;
    }

    public void resetEffects(){
        instance = null;
    }
}
