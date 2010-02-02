package openopoly.facade;

import openopoly.Openopoly;
import openopoly.control.game.GameChanceStack;
import openopoly.control.game.GameChestStack;
import openopoly.err.GameException;
import openopoly.err.PlaceDoesntExistsException;
import openopoly.err.PlayerDoesntExistsException;

/**
 *
 * @author Lucas
 * @author Sergio
 */
public class OpenopolyFacade {

    private Openopoly openopoly = null;

    public OpenopolyFacade() {
        openopoly = new Openopoly();
    }

    //Milestone 1
    public void createGame(int numPlayers, String playerNames, String tokenColor) throws GameException {
        openopoly.createGame(numPlayers, playerNames, tokenColor);
    }

    public int getNumberOfPlayers() {
        return openopoly.getGameControl().getNumberOfPlayers();
    }

    public String getPlayerToken(String playerName) throws PlayerDoesntExistsException {
        return openopoly.getGameControl().getPlayerColor(playerName);
    }

    public int getPlayerMoney(String playerName) throws PlayerDoesntExistsException {
        return openopoly.getGameControl().getPlayerMoney(playerName);
    }

    public int getPlayerPosition(String playerName) throws PlayerDoesntExistsException, GameException {
        return openopoly.getGameControl().getPlayerPosition(playerName);
    }

    public String getPlayerDeeds(String playerName) throws PlayerDoesntExistsException {
        return openopoly.getGameControl().getPlayerDeeds(playerName);
    }

    public String getCurrentPlayer() {
        return openopoly.getGameControl().getCurrentPlayerName();
    }

    public String getPlaceName(int placeID) throws PlaceDoesntExistsException {
        return openopoly.getGameBoard().getBlockName(placeID);
    }

    public String getPlaceGroup(int placeID) throws PlaceDoesntExistsException {
        return openopoly.getGameBoard().getBlockGroup(placeID);
    }

    public String getPlaceOwner(int placeID) throws PlaceDoesntExistsException, GameException {
        return openopoly.getGameBoard().getBlockOwnerName(placeID);
    }

    public int getPropertyRent(int placeID) throws PlaceDoesntExistsException, GameException {
        if(openopoly.getGameControl().isActivateBuild()){
            return openopoly.getGameBoard().getPropertyRentBuildRules(placeID);
        }else{
            return openopoly.getGameBoard().getPropertyRent(placeID);
        }
    }

    public int getPlacePrice(int placeID) throws PlaceDoesntExistsException, GameException {
        return openopoly.getGameBoard().getPlacePrice(placeID);
    }

    public void rollDice(int firstDieResult, int secondDieResult) throws GameException {
        openopoly.getGameControl().roll(firstDieResult, secondDieResult);
        openopoly.getGameControl().removeBankruptedPlayer();
        openopoly.getGameControl().nextPlayer();
    }

    public String getCommands() {
        return openopoly.getGameControl().getCommands();
    }

    public void setAutomaticBuying(boolean auto) {
        openopoly.getGameControl().setAutomaticBuy(auto);
    }

    public boolean gameIsOver() {
        return openopoly.getGameControl().getGameIsOver();
    }

    public void buy() throws GameException {
        openopoly.getGameControl().buy();
    }

    public void quitGame() throws GameException {
        openopoly.quitGame();
    }


    //Milestone 2
    
    //User Story 5
    public void activateChancePlaces(boolean cardShuffle){
        openopoly.getGameControl().activateChancePlaces();
        GameChanceStack.getInstance().setShuffle(cardShuffle);
    }

    public void forceNextChanceCard(int cardId) throws GameException{
        GameChanceStack.getInstance().forceNextChanceCard(cardId);
    }

    public int getCurrentChanceCardNumber() throws GameException{
        return GameChanceStack.getInstance().getCurrentCard().getIndex();
    }

    public String getCurrentChanceCardDescription() throws GameException{
        return GameChanceStack.getInstance().getCurrentCard().getDescription();
    }

    public void activateChestPlaces(boolean cardShuffle){
        openopoly.getGameControl().activateChestPlaces();
        GameChestStack.getInstance().setShuffle(cardShuffle);
    }

    public void forceNextChestCard(int cardId) throws GameException{
        GameChestStack.getInstance().forceNextChestCard(cardId);
    }

    public int getCurrentChestCardNumber() throws GameException{
        return GameChestStack.getInstance().getCurrentCard().getIndex();
    }

    public String getCurrentChestCardDescription() throws GameException{
        return GameChestStack.getInstance().getCurrentCard().getDescription();
    }

    //User Story 6
    public void activateJail() throws PlaceDoesntExistsException{
        openopoly.getGameControl().activateJail();
    }

    public boolean playerIsOnJail(String playerName) throws PlayerDoesntExistsException{
        return openopoly.getGameControl().getPlayer(playerName).isJailed();
    }

    public void pay() throws GameException{
        openopoly.getGameControl().pay();
    }

    public void activateDoublesRule(){
        openopoly.getGameControl().activateDoublesRule();
    }

    public void useCard(String cardType) throws GameException{
        openopoly.getGameControl().useCard(cardType);
    }

    //User Story 7
    public void activateUtilityPlaces(){
        openopoly.getGameControl().activateUtilityPlaces();
    }

    //User Story 8
    public void activateBuild(){
        openopoly.getGameControl().activateBuild();
    }

    public void build(int propertyID) throws GameException{
        openopoly.getGameControl().build(propertyID);
    }

    public void giveCashToPlayer(String playerName, int cash) throws PlayerDoesntExistsException{
        int oldCash = openopoly.getGameControl().getPlayer(playerName).getCash();
        openopoly.getGameControl().getPlayer(playerName).setCash(oldCash + cash);
    }

    //User Story 9
    public void activateSell(){
        openopoly.getGameControl().activateSell();
    }

    public void sell(int propertyID) throws GameException{
        openopoly.getGameControl().sell(propertyID);
    }


    //user story 10
    public void activateMortgage(){
        openopoly.getGameControl().activateMortgage();
    }

    public void mortgage(int placeID) throws GameException{
        openopoly.getGameControl().mortgage(placeID);
    }

    public boolean isMortgaged(int placeID) throws GameException{
        return openopoly.getGameControl().isMortagged(placeID);
    }

    public void nextPlayer(){
        this.openopoly.getGameControl().nextPlayer();
    }

}
