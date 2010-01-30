package openopoly.util;

import java.util.ArrayList;
import java.util.Random;
import openopoly.err.GameException;

/** Classe que representa uma pilha de carta
 *
 * @author Lucas
 * @author Sergio
 */
public class CardStack {

    private int stackSize;
    private ArrayList<Card> unshuffledStack;
    private ArrayList<Card> shuffledStack;
    private boolean shuffle = false;
    private int currentCardIndex = 0;

    /**
     * O construtor da classe tem a função de inicializar a pilha de cartas não embaralhadas
     * e construir uma pilha embaralhada também
     * @param unshuffledStack pilha de cartas não embaralhadas.
     */
    public CardStack(ArrayList<Card> unshuffledStack) {
        this.stackSize = unshuffledStack.size();
        this.unshuffledStack = unshuffledStack;
        shuffledStack = new ArrayList<Card>();
        shuffleCards();
    }

    /**
     * Este método é responsável por embaralhar a pilha de carta
     */
    public void shuffleCards() {
        ArrayList<Card> auxStack = (ArrayList<Card>) unshuffledStack.clone();
        Random r = new Random();
        int leftCards = stackSize;
        for (int i = 0; i < stackSize; i++) {
            int index = r.nextInt(leftCards);
            shuffledStack.add(auxStack.get(index));
            auxStack.remove(index);
            leftCards--;
        }
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public Card getCard(int cardID) throws GameException {
        if (cardID < 1 || cardID > stackSize) {
            throw new GameException("Card doesn't exist");
        } else {
            Card c = null;
            for (Card card : unshuffledStack) {
                if (card.getIndex() == cardID) {
                    c = card;
                }
            }
            return c;
        }
    }

    public void forceNextCard(int cardID) throws GameException {
        Card aux = getCard(cardID);
        currentCardIndex = getCurrentCardStack().indexOf(aux);
    }

    public Card getCurrentCard() throws GameException {
        return getCurrentCardStack().get(currentCardIndex);
    }

    public Card drawCard() throws GameException {
        Card draw = getCurrentCardStack().get(currentCardIndex);
        currentCardIndex++;
        if (currentCardIndex == stackSize) {
            currentCardIndex = 0;
        }
        return draw;
    }

    private ArrayList<Card> getCurrentCardStack() {
        if (shuffle) {
            return shuffledStack;
        } else {
            return unshuffledStack;
        }
    }

    public void printCards() {
//        for (Card card : unshuffledStack) {
//            System.out.println("Pilha desembaralhada: "+card.getDescription());
//        }
        for (Card card : shuffledStack) {
            System.out.println("Pilha embaralhada: " + card.getDescription());
        }
    }
}
