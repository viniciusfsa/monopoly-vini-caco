package openopoly.control.game;

import java.util.ArrayList;
import openopoly.err.GameException;
import openopoly.util.Card;
import openopoly.util.CardStack;

/**
 *
 * @author Lucas
 * @author Sergio
 */
public class GameChanceStack {

    private CardStack chanceStack;
    private static GameChanceStack instance;
    private boolean prisonChanceDrew;

    public static GameChanceStack getInstance(){
        if(instance == null){
            instance = new GameChanceStack();
        }
        return instance;
    }

    public GameChanceStack() {
        chanceStack = new CardStack(fillStack());
    }

    private ArrayList<Card> fillStack() {
        ArrayList<Card> stack = new ArrayList<Card>();
        stack.add(new Card( 1, "Advance To Go - Collect $200", Card.Type.GOUNTIL, 40, 0));
        stack.add(new Card( 2, "Advance To - Illinois Avenue", Card.Type.GOTO, 24, 0));
        stack.add(new Card( 3, "Advance To St. Charles Place - If you pass Go, Collect $200", Card.Type.GOUNTIL, 11, 0));
        stack.add(new Card( 4, "Advance Token To Nearest Utility - If unowned you may buy it from the bank. If owned, throw dice and pay owner a total ten times the amount thrown.", Card.Type.NEXTUTIL, 0, 0));
        stack.add(new Card( 5, "Advance Token To The Nearest Railroad - Pay Owner Twice The Rental To Which He Is Otherwise entitled. If Railroad Is Unowned, You May Buy It From The Bank.", Card.Type.NEXTRAIL, 0, 0));
        stack.add(new Card( 6, "Bank Pays You Dividend Of - $50", Card.Type.MODIFY, 50, 0));
        stack.add(new Card( 7, "Go Back 3 Spaces", Card.Type.BACK, 0, 0));
        stack.add(new Card( 8, "Go Directly To Jail - Do Not Pass Go, Do Not Collect $200", Card.Type.PRISON, 0, 0));
        stack.add(new Card( 9, "Make General Repairs On All Your Property - For Each House Pay $25, For Each Hotel $100", Card.Type.REPAIR, 25, 100));
        stack.add(new Card(10, "Pay Poor Tax Of - $15", Card.Type.MODIFY, -15, 0));
        stack.add(new Card(11, "This Card May Be Kept Until Needed Or Sold - Get Out Of Jail Free", Card.Type.PRISONCARD_CHANCE, 0, 0));
        stack.add(new Card(12, "Take A Ride On The Reading - If You Pass Go Collect $200", Card.Type.GOUNTIL, 5, 0));
        stack.add(new Card(13, "Take A Walk On The Board Walk - Advance Token To Board Walk", Card.Type.GOUNTIL, 39, 0));
        stack.add(new Card(14, "You Have Been Elected Chairman Of The Board - Pay Each Player $50", Card.Type.PAYTOALL, 50, 0));
        stack.add(new Card(15, "Your Building And Loan Matures - Collect $150", Card.Type.MODIFY, 150, 0));
        stack.add(new Card(16, "Advance Token To The Nearest Railroad - Pay Owner Twice The Rental To Which He Is Otherwise entitled. If Railroad Is Unowned, You May Buy It From The Bank.", Card.Type.NEXTRAIL, 0, 0));
        return stack;
    }

    public void shuffleCards(){
        chanceStack.shuffleCards();
    }

    public void setShuffle(boolean cardShuffle){
        chanceStack.setShuffle(cardShuffle);
    }

    public Card getCurrentCard() throws GameException{
        return chanceStack.getCurrentCard();
    }

    public Card chanceDrawCard() throws GameException{
        return chanceStack.drawCard();
    }

    public void forceNextChanceCard(int cardID) throws GameException{
        if(cardID == 11 && prisonChanceDrew){
            throw new GameException("This card is already possessed by a player");
        }else {
            chanceStack.forceNextCard(cardID);
        }
    }

    public void printCards(){
        chanceStack.printCards();
    }

    public void resetChanceStack() {
        instance = null;
    }

    public void setPrisonChanceDrew(boolean prisonChanceDrew) {
        this.prisonChanceDrew = prisonChanceDrew;
    }

}
