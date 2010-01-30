package openopoly.util;

import openopoly.err.GameException;
import openopoly.err.PlaceDoesntExistsException;

/**
 *
 * @author Lucas
 * @author Sergio
 */
public class Card {

    private int index;
    private String description;
    private Type type;
    private int effParam1, effParam2;

    public enum Type {

        GOTO, MODIFY, GOUNTIL, PRISON, PRISONCARD_CHANCE, PRISONCARD_CHEST, REPAIR, BACK, NEXTUTIL, NEXTRAIL, PAYTOALL, COLLECTFROMALL
    }

    public Card(int index, String description, Type type, int effParam1, int effParam2) {
        this.index = index;
        this.description = description;
        this.type = type;
        this.effParam1 = effParam1;
        this.effParam2 = effParam2;
    }

    public void effect() throws PlaceDoesntExistsException, GameException {
        if (type == Type.GOTO) {
            CardEffects.getInstance().goTo(effParam1);
        } else if (type == Type.GOUNTIL) {
            CardEffects.getInstance().goUntilReach(effParam1);
        } else if (type == Type.MODIFY) {
            CardEffects.getInstance().modifyCash(effParam1);
        } else if (type == Type.PRISON) {
            CardEffects.getInstance().goToPrison();
        } else if (type == Type.PRISONCARD_CHANCE) {
            CardEffects.getInstance().prisonCardChance();
        } else if (type == Type.PRISONCARD_CHEST) {
            CardEffects.getInstance().prisonCardChest();
        } else if (type == Type.REPAIR) {
            CardEffects.getInstance().repair(effParam1, effParam2);
        } else if (type == Type.BACK) {
            CardEffects.getInstance().back();
        } else if (type == Type.PAYTOALL) {
            CardEffects.getInstance().payToAll(effParam1);
        } else if (type == Type.COLLECTFROMALL) {
            CardEffects.getInstance().collectFromAll(effParam1);
        } else if (type == Type.NEXTRAIL) {
            CardEffects.getInstance().nextRailroad();
        } else if (type == Type.NEXTUTIL) {
            CardEffects.getInstance().nextUtility();
        }
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
}
