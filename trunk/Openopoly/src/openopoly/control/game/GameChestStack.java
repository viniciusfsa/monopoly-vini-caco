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
public class GameChestStack {

    private CardStack chestStack;
    private static GameChestStack instance;
    private boolean prisonChestDrew;

    public static GameChestStack getInstance(){
        if(instance == null){
            instance = new GameChestStack();
        }
        return instance;
    }

    public GameChestStack() {
        chestStack = new CardStack(fillStack());
    }

    private ArrayList<Card> fillStack() {
        ArrayList<Card> stack = new ArrayList<Card>();
        stack.add(new Card( 1, "Advance To Go - Collect $200", Card.Type.GOUNTIL, 40, 0));
        stack.add(new Card( 2, "Bank Error In Your Favor - Collect $200", Card.Type.MODIFY, 200, 0));
        stack.add(new Card( 3, "Doctor's Fee - Pay $50", Card.Type.MODIFY, -50, 0));
        stack.add(new Card( 4, "Grand Opera Opening - Collect $50 from every player for opening night seats", Card.Type.COLLECTFROMALL, 50, 0));
        stack.add(new Card( 5, "From Sale Out Of Stock - You Get $45", Card.Type.MODIFY, 45, 0));
        stack.add(new Card( 6, "Get Out Of Jail, Free - This card may be kept until needed or sold", Card.Type.PRISONCARD_CHEST, 50, 0));
        stack.add(new Card( 7, "Go To Jail - Go directly to jail - Do not pass go - Do not collect $200", Card.Type.PRISON, 0, 0));
        stack.add(new Card( 8, "Income Tax Refund - Collect $20", Card.Type.MODIFY, 20, 0));
        stack.add(new Card( 9, "Life Insurance Matures - Collect $100", Card.Type.MODIFY, 100, 0));
        stack.add(new Card(10, "Pay Hospital - $100", Card.Type.MODIFY, -100, 0));
        stack.add(new Card(11, "Pay School Tax - $150", Card.Type.MODIFY, -150, 0));
        stack.add(new Card(12, "Receive For Your Services - $25", Card.Type.MODIFY, 25, 0));
        stack.add(new Card(13, "XMAS Fund Matures - Collect $100", Card.Type.MODIFY, 100, 0));
        stack.add(new Card(14, "You Have Won Second Prize In A Beauty Contest - Collect $10", Card.Type.MODIFY, 10, 0));
        stack.add(new Card(15, "You Inherit - $100", Card.Type.MODIFY, 100, 0));
        stack.add(new Card(16, "You Are Assessed For Street Repairs - $40 Per House, $115 Per Hotel", Card.Type.REPAIR, 40, 115));
        return stack;
    }

    public void shuffleCards(){
        chestStack.shuffleCards();
    }

    public void setShuffle(boolean cardShuffle){
        chestStack.setShuffle(cardShuffle);
    }

    public Card getCurrentCard() throws GameException{
        return chestStack.getCurrentCard();
    }

    public Card chestDrawCard() throws GameException{
        return chestStack.drawCard();
    }

    public void resetChestStack() {
        instance = null;
    }

    public void forceNextChestCard(int cardID) throws GameException{
        if(cardID == 6 && prisonChestDrew){
            throw new GameException("This card is already possessed by a player");
        }else {
            chestStack.forceNextCard(cardID);
        }
    }

    public void setPrisonChestDrew(boolean prisonChestDrew) {
        this.prisonChestDrew = prisonChestDrew;
    }

    public void print(){
        chestStack.printCards();
    }
}
