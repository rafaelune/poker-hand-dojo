import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoPairsRank extends BasePokerRank implements PokerRank {
    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (this.isTwoPairs(cards)) {
            return PokerHandResult.TWO_PAIRS;
        }
        return super.handle(cards);
    }
    private boolean isTwoPairs(List<Card> cards) {
        Map<Integer, Integer> mapCartas = new HashMap<Integer, Integer>();
        Integer pairCount = 0;

        for (int index = 0; index < cards.size(); index++) {
            Card card = cards.get(index);
            if (!mapCartas.containsKey(card.getNumber())) {
                Integer count = super.getCardCount(card, cards);
                mapCartas.put(card.getNumber(), count);

                if (count == 2 && pairCount == 1) {
                    return true;
                } else if (count == 2) {
                    pairCount++;
                }
            }
        }

        return false;
    }
}
