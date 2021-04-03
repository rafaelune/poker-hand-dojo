import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class StraightFlushRank extends BasePokerRank implements PokerRank{
    public StraightFlushRank() {
    }

    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (isFlush(cards) && isStraight(cards)) {
            return PokerHandResult.STRAIGHT_FLUSH;
        }
        return super.handle(cards);
    }

    protected boolean isStraight(List<Card> cards) {
        TreeSet<Card> treeSet = new TreeSet<Card>(cards);
        Iterator<Card> iterator = treeSet.iterator();
        Card firstCard = iterator.next();
        int nextNumberReference = firstCard.getNumber() + 1;

        while (iterator.hasNext()) {
            Card carta = iterator.next();
            if (carta.getNumber() != nextNumberReference) {
                if (!iterator.hasNext() &&
                        firstCard.getNumber() == 2 &&
                        carta.getNumber() == 14) {
                    return true;
                } else {
                    return false;
                }
            } else {
                nextNumberReference = carta.getNumber() + 1;
            }
        }

        return true;
    }

    protected boolean isFlush(List<Card> cards) {
        Card referenceCard = cards.get(0);

        for (Card card : cards) {
            if (card.getSuit() != referenceCard.getSuit())
                return false;
        }

        return true;
    }
}
