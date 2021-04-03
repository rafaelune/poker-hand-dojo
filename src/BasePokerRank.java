import java.util.List;

public abstract class BasePokerRank implements PokerRank {

    protected PokerRank nextHandler;

    public void setNextHandler(PokerRank nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (nextHandler != null)
            return nextHandler.handle(cards);
        return PokerHandResult.HIGH_CARD;
    }

    /**
     * Checks count of card number occurrences in collection
     * @param cards poker hand cards collection
     * @param numberOfKind number of card occurrences
     * @return
     */
    protected boolean hasNumberOfKind(List<Card> cards, Integer numberOfKind) {
        for (int index = 0; index < cards.size(); index++) {
            Card firstCard = cards.get(index);
            Integer counter = this.getCardCount(firstCard, cards);
            if (counter == numberOfKind) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns number of occurrences for a reference card number.
     * @param referenceCard number used in the search
     * @param cards poker hand cards collection
     * @return
     */
    protected Integer getCardCount(Card referenceCard, List<Card> cards) {
        Integer counter = 0;
        for (Card item : cards) {
            if (referenceCard.getNumber() == item.getNumber()) {
                counter++;
            }
        }
        return counter;
    }
}
