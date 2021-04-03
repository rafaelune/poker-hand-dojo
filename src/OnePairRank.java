import java.util.List;

public class OnePairRank extends BasePokerRank implements PokerRank {
    @Override
    public PokerHandResult handle(List<Card> cards) {
        if (super.hasNumberOfKind(cards, 2)) {
            return PokerHandResult.ONE_PAIR;
        }
        return super.handle(cards);
    }
}
